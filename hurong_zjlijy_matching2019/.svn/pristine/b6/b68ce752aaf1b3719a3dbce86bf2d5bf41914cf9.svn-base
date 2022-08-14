package hry.cm2.runnable;

import com.alibaba.fastjson.JSON;
import hry.cm2.customer.model.Cm2Customer;
import hry.cm2.customer.service.Cm2CustomerService;
import hry.cm2.grade.model.Cm2GradeMinercamps;
import hry.cm2.grade.service.Cm2GradeMinercampsService;
import hry.cm2.log.service.Cm2ExceptionLogService;
import hry.cm2.profitone.service.Cm2ProfitOneService;
import hry.cm2.profittwo.model.Cm2ProfitTwo;
import hry.cm2.profittwo.service.Cm2ProfitTwoService;
import hry.cm2.team.model.Cm2Teamlevel;
import hry.cm2.team.service.Cm2TeamlevelService;
import hry.util.idgenerate.IdGenerate;
import hry.util.sys.ContextUtil;
import org.apache.log4j.Logger;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/***
 * 统计矿场收益记录
 * 
 * @author lenovo
 *
 */
public class ProfitTwoRunnable implements Runnable {

	private final Logger log = Logger.getLogger(ProfitTwoRunnable.class);

	private String paramStr;

	public ProfitTwoRunnable(String paramStr) {
		super();
		this.paramStr = paramStr;
	}

	@Resource
	private  static Cm2ExceptionLogService cmExceptionLogService = (Cm2ExceptionLogService) ContextUtil.getBean("cm2ExceptionLogService");
	@Resource
	private  static Cm2ProfitOneService cmProfitOneService = (Cm2ProfitOneService) ContextUtil.getBean("cm2ProfitOneService");
	@Resource
	private  static Cm2GradeMinercampsService cmGradeMinercampsService = (Cm2GradeMinercampsService) ContextUtil.getBean("cm2GradeMinercampsService");
	@Resource
	private  static Cm2ProfitTwoService cmProfitTwoService = (Cm2ProfitTwoService) ContextUtil.getBean("cm2ProfitTwoService");
	@Resource
	private  static Cm2TeamlevelService cmTeamlevelService = (Cm2TeamlevelService) ContextUtil.getBean("cm2TeamlevelService");
	@Resource
	private  static Cm2CustomerService cmCustomerService = (Cm2CustomerService) ContextUtil.getBean("cm2CustomerService");

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Cm2Customer cmCustomer = JSON.parseObject(paramStr, Cm2Customer.class);
		Long customerId = cmCustomer.getCustomerId();
		try {
			/**
			 * 1.查询用户直推所有人的总动态收益，如果总收益小于等于0,则return 2.计算本人获取收益 3.按照级差数计算所有上级收益
			 */
			// 1.查询用户直推所有人的总动态收益
			BigDecimal profitOne = cmProfitOneService.getTeamProfitSumByCustomerId(customerId,null);
			if(profitOne==null){
				profitOne = new BigDecimal(0);
			}
			if (profitOne.compareTo(new BigDecimal(0)) <= 0) {
				return;
			}
			// 2.计算本人获取收益
			int grade2 = cmCustomer.getGrade2();// 矿场等级
			// 查询用户矿场等级信息
			Cm2GradeMinercamps cmGradeMinercamps = cmGradeMinercampsService.getCmGradeMinercamps(grade2);
			BigDecimal teamProfitProportion = new BigDecimal(0);
			BigDecimal b = new BigDecimal(0);
			if (cmGradeMinercamps != null) {
				teamProfitProportion = cmGradeMinercamps.getTeamProfitProportion();// 级差比例
				if (teamProfitProportion.compareTo(new BigDecimal(0)) > 0) {
					// 计算收益
					b = profitOne.multiply(teamProfitProportion.multiply(new BigDecimal(0.01))).setScale(8,
							BigDecimal.ROUND_HALF_UP);
					// 插入收益记录
					this.insertProfotTwo(customerId, customerId, cmGradeMinercamps.getGrade(),
							cmGradeMinercamps.getGradeName(), profitOne, new BigDecimal(0), b, teamProfitProportion,
							new BigDecimal(0));
				}
				
			}
			
			// 3.按照级差数计算所有上级收益
			this.teamprofit(customerId, grade2, profitOne, b, teamProfitProportion);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			// 插入异常日志
			cmExceptionLogService.insertlog(e, "ProfitTwoRunnable", paramStr);
		}

	}

	/**
	 * 
	 * @param customerId
	 *            收益人ID
	 * @param customerTeamId
	 *            产出动态收益层级的推荐人id
	 * @param grade收益人矿场等级
	 * @param gradeName收益人等级名称
	 * @param profit1分发收益基数
	 * @param profit2同级收益
	 * @param profit3获取收益
	 * @param proportion收益比例
	 * @param downProportion下级收益比例(减去的收益比例）
	 */
	private void insertProfotTwo(Long customerId, Long customerTeamId, int grade, String gradeName, BigDecimal profit1,
			BigDecimal profit2, BigDecimal profit3, BigDecimal proportion, BigDecimal downProportion) {
		Cm2ProfitTwo cmProfitTwo = new Cm2ProfitTwo();
		cmProfitTwo.setCustomerId(customerId);
		cmProfitTwo.setCustomerTeamId(customerTeamId);
		cmProfitTwo.setGrade(grade);
		cmProfitTwo.setGradeName(gradeName);
		cmProfitTwo.setProfit1(profit1);
		cmProfitTwo.setProfit2(profit2);
		cmProfitTwo.setProfit3(profit3);
		cmProfitTwo.setProportion(proportion);
		cmProfitTwo.setStatus(1);
		cmProfitTwo.setTransactionNum(IdGenerate.transactionNum("CM"));
		cmProfitTwo.setDownProportion(downProportion);

		cmProfitTwoService.save(cmProfitTwo);

	}

	/**
	 * 计算所有上级收益
	 * 
	 * @param customerId
	 *            用户ID
	 * @param grade
	 *            等级
	 * @param profit1
	 *            分发收益基数
	 * @param profit3
	 *            获取收益
	 * @param proportion
	 *            收益比例
	 */
	private void teamprofit(Long customerId, int grade, BigDecimal profit1, BigDecimal profit3, BigDecimal proportion) {
		/***
		 * 查询用户所有上级
		 */
		List<Cm2Teamlevel> teamlevelList = cmTeamlevelService.findUpTeamByCustomerId(customerId);
		if (teamlevelList != null && teamlevelList.size() > 0) {
			int publicGrade = grade;// 记录下级最高等级
			BigDecimal downProfit = profit3;// 记录下级获取的收益
			BigDecimal downProportion = proportion;// 记录下级最高级差比例
			for (Cm2Teamlevel cmTeamlevel : teamlevelList) {
				// 查询用户等级信息
				Cm2Customer cmCustomer = cmCustomerService.getCustomerByCustomerId(cmTeamlevel.getParentId());
				if (cmCustomer == null) {
					continue;
				}
				if (cmCustomer.getTeamProfitProportion().compareTo(new BigDecimal(0)) <= 0) {
					continue;
				}
				if (cmCustomer.getGrade2() == 0) {
					continue;
				}
				if (cmCustomer.getGrade2() < publicGrade) {
					continue;
				}
				if (cmCustomer.getGrade2() == publicGrade) {
					// 当前用户等级与下级用户等级相同，则获取下级矿场收益的10%
					// 计算收益 收益=下级获取收益*10%
					BigDecimal b = downProfit.multiply(new BigDecimal(0.1)).setScale(8, BigDecimal.ROUND_HALF_UP);
					// 插入收益记录
					this.insertProfotTwo(cmCustomer.getCustomerId(), customerId, cmCustomer.getGrade2(),
							cmCustomer.getGradeName2(), profit1, profit3, b, cmCustomer.getTeamProfitProportion(),
							downProportion);
					publicGrade = cmCustomer.getGrade2();// 更改下级最高等级
					downProfit = b;// 更改下级获取的收益
					downProportion = cmCustomer.getTeamProfitProportion();// 更改下级最高级差比例
				}
				if (cmCustomer.getGrade2() > publicGrade) {
					// 当前用户等级大于下级用户等级，则获取 分发收益基数*（当前用户级差比例-下级最高等级用户级差比例）
					// 计算收益
					BigDecimal b = (cmCustomer.getTeamProfitProportion().subtract(downProportion)).multiply(profit1)
							.multiply(new BigDecimal(0.01)).setScale(8, BigDecimal.ROUND_HALF_UP);
					// 插入收益记录
					this.insertProfotTwo(cmCustomer.getCustomerId(), customerId, cmCustomer.getGrade2(),
							cmCustomer.getGradeName2(), profit1, new BigDecimal(0), b,
							cmCustomer.getTeamProfitProportion(), downProportion);

					publicGrade = cmCustomer.getGrade2();// 更改下级最高等级
					downProfit = b;// 更改下级获取的收益
					downProportion = cmCustomer.getTeamProfitProportion();// 更改下级最高级差比例

				}
			}
		}
	}
}
