/**
 * Copyright:   
 * @author:      luyue
 * @version:     V1.0 
 * @Date:        2019-05-31 14:56:41 
 */
package hry.mall.integral.service.impl;

import hry.bean.JsonResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.mall.integral.dao.LevelRecordDao;
import hry.mall.integral.model.CustomerIntegral;
import hry.mall.integral.model.IntegralLevel;
import hry.mall.integral.model.LevelRecord;
import hry.mall.integral.service.*;
import hry.mall.member.commend.service.AppCommendUserService;
import hry.util.QueryFilter;
import hry.util.SNUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * <p> LevelRecordService </p>
 * @author:         luyue
 * @Date :          2019-05-31 14:56:41  
 */
@Service("levelRecordService")
public class LevelRecordServiceImpl extends BaseServiceImpl<LevelRecord, Long> implements LevelRecordService{
	
	@Resource(name="levelRecordDao")
	@Override
	public void setDao(BaseDao<LevelRecord, Long> dao) {
		super.dao = dao;
	}


	@Resource
	private IntegralAccountService integralAccountService;
	@Resource
	private CustomerIntegralService customerIntegralService;
	@Resource
	private IntegralConfigService integralConfigService;
	@Resource
	private IntegralDetailService integralDetailService;
	@Resource
	private IntegralLevelService integralLevelService;
	@Resource
	private AppCommendUserService appCommendUserService;

	@Override
	public JsonResult updateLevelRecord(){
		JsonResult jsonResult = new JsonResult();
		try {
			List<LevelRecord> all = this.findAll();
			for (LevelRecord levelRecord:all) {
				//维护结束日期 或 分红计划分发时间为空的数据
				if (StringUtils.isEmpty(levelRecord.getEndDate()) || StringUtils.isEmpty(levelRecord.getDistributeDate())){
					System.out.println("为购买记录id为："+levelRecord.getId()+"的数据添加到期时间");
					//查询购买的等级
					IntegralLevel integralLevel = integralLevelService.get(levelRecord.getLevelId());
					if(null != integralLevel){
						Date created = levelRecord.getCreated();
						levelRecord.setStartDate(created);
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(created);
						calendar.add(Calendar.YEAR, integralLevel.getValidityPeriod());
						Date time = calendar.getTime();
						levelRecord.setEndDate(time);
						levelRecord.setDistributeDate(new Date());
						this.update(levelRecord);
					}else {
						System.out.println("购买记录id为："+levelRecord.getId()+"的记录的等级查询为空");
					}
				}
			}
			jsonResult.setSuccess(true);
			jsonResult.setMsg("添加到期时间和分红时间成功");
		}catch (Exception e){
			e.printStackTrace();
			jsonResult.setSuccess(false);
			jsonResult.setMsg("添加到期时间和分红时间失败");
		}
		return jsonResult;
	}

	@Override
	public JsonResult addLevelRecord() {
		JsonResult jsonResult = new JsonResult();
		try {
			//查询出导入的用户
			QueryFilter queryFilter = new QueryFilter(CustomerIntegral.class);
			queryFilter.addFilter("levelNumber=",2);
			List<CustomerIntegral> customerIntegrals = customerIntegralService.find(queryFilter);
			System.out.println("导入数据的条数为："+customerIntegrals.size());
			//获取默认的导入等级
			QueryFilter qfIntegralLevel = new QueryFilter(IntegralLevel.class);
			qfIntegralLevel.addFilter("number=",2);
			IntegralLevel integralLevel = integralLevelService.get(qfIntegralLevel);
			if(null != integralLevel){
				for (CustomerIntegral customerIntegral:customerIntegrals) {
					QueryFilter queryFilter1 = new QueryFilter(LevelRecord.class);
					queryFilter1.addFilter("memberId=",customerIntegral.getMemberId());
					queryFilter1.addFilter("remark=","购买会员-导入");
					Long count = this.count(queryFilter1);
					if(count>0L){
						continue;
					}
					//5.添加用户升级记录
					LevelRecord levelRecord = new LevelRecord();
					//判断用户是否已有会员
					String requestNo = SNUtil.create15();
					levelRecord.setNumber(requestNo);
					levelRecord.setMemberId(customerIntegral.getMemberId());
					levelRecord.setLevelId(integralLevel.getId());
					levelRecord.setLevelNumber(integralLevel.getNumber());
					levelRecord.setMoney(new BigDecimal("10000"));//默认购买金额
					levelRecord.setStatus(1);
					levelRecord.setRemark("购买会员-导入");
					levelRecord.setStartDate(customerIntegral.getStartDate());//开始时间
//                Calendar calendar = Calendar.getInstance();
//                calendar.add(Calendar.YEAR, integralLevel.getValidityPeriod());
//                Date time = calendar.getTime();
					levelRecord.setEndDate(customerIntegral.getEndDate());//会员到期时间
					//设置分红时间  客户要求分红时间为1天
					levelRecord.setDistributeDate(new Date());
					this.save(levelRecord);
					System.out.println("用户id为："+customerIntegral.getMemberId()+"添加购买会员-导入记录成功");
				}
				jsonResult.setSuccess(true);
				jsonResult.setMsg("添加购买记录成功");

			}else {
				jsonResult.setSuccess(false);
				jsonResult.setMsg("没有对应等级。");
			}

		}catch (Exception e){
			e.printStackTrace();
			jsonResult.setSuccess(false);
			jsonResult.setMsg("异常");
		}
		return jsonResult;
	}



	@Override
	public BigDecimal getUserPerformance(Long customerId){
		BigDecimal bigDecimal = ((LevelRecordDao) dao).getUserPerformance(customerId);
		return bigDecimal;
	}



	@Override
	public BigDecimal getUserTeamPerformance(Long customerId, Integer series){
		//获取下级id集合
		List<Long> subordinateIdBySeries = appCommendUserService.getSubordinateIdBySeries(customerId, series);
		BigDecimal bigDecimal = new BigDecimal("0");
		if(subordinateIdBySeries.size()>0){
			bigDecimal = ((LevelRecordDao) dao).getTeamPerformance(subordinateIdBySeries);
		}
		System.out.println("用户："+customerId+"一共有："+subordinateIdBySeries.size()+"个下级;不含自身的团队业绩为："+bigDecimal);
		return bigDecimal;
	}



}
