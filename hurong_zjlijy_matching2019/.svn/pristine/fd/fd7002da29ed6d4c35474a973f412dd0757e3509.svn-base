/**
 * Copyright:   
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-10-14 16:35:02 
 */
package hry.cm2.customer.service.impl;

import com.alibaba.fastjson.JSON;
import hry.cm2.customer.dao.Cm2CustomerDao;
import hry.cm2.customer.model.Cm2Customer;
import hry.cm2.customer.service.Cm2CustomerService;
import hry.cm2.customerminer.service.Cm2CustomerMinerService;
import hry.cm2.grade.model.Cm2GradeMiner;
import hry.cm2.grade.model.Cm2GradeMinercamps;
import hry.cm2.grade.model.Cm2GradeRecord;
import hry.cm2.grade.service.Cm2GradeMinerService;
import hry.cm2.grade.service.Cm2GradeMinercampsService;
import hry.cm2.grade.service.Cm2GradeRecordService;
import hry.cm2.team.model.Cm2Teamlevel;
import hry.cm2.team.service.Cm2TeamlevelService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;

import hry.mq.producer.service.MessageProducer;
import hry.redis.common.utils.RedisService;
import hry.util.QueryFilter;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p> Cm2CustomerService </p>
 * @author:         yaozh
 * @Date :          2019-10-14 16:35:02  
 */
@Service("cm2CustomerService")
public class Cm2CustomerServiceImpl extends BaseServiceImpl<Cm2Customer, Long> implements Cm2CustomerService{
	
	@Resource(name="cm2CustomerDao")
	@Override
	public void setDao(BaseDao<Cm2Customer, Long> dao) {
		super.dao = dao;
	}

	@Resource
	private Cm2CustomerMinerService cmCustomerMinerService;
	@Resource
	private Cm2GradeMinerService cmGradeMinerService;
	@Resource
	private Cm2GradeMinercampsService cmGradeMinercampsService;
	@Resource
	private RedisService redisService;
	@Resource
	private Cm2GradeRecordService cmGradeRecordService;
	@Resource
	private Cm2TeamlevelService cmTeamlevelService;
	@Resource
	private MessageProducer messageProducer;

	@Override
	public void updateGrade(String messageStr) {
		// TODO Auto-generated method stub
		/***
		 * 1.查询用户投入
		 * 2.查询用户伞下投入
		 * 3.查询用户有效直推人数
		 * 4.查询用户团队人数
		 * 5.查询用户等级信息
		 * 6.更新等级
		 *
		 * 团队业绩= 伞下投入+本人投入
		 *
		 * 团队业绩= 伞下+个人
		 伞下业绩=伞下所有人不包含自己
		 最大线业绩=直推中 团队业绩最大的
		 大区业绩= 最大线业绩
		 小区业绩=伞下业绩-最大线业绩
		 */
		Long customerId = Long.valueOf(messageStr);
		//1.查询用户投入
		BigDecimal myTouru = cmCustomerMinerService.getMinerPriceSumByCustomerId(customerId);
		if(myTouru==null){
			myTouru = new BigDecimal(0);
		}
		//2.查询用户伞下投入
		BigDecimal myteamTouru = cmCustomerMinerService.getTeamMinerPriceSumByCustomerId(customerId);
		if(myteamTouru==null){
			myteamTouru = new BigDecimal(0);
		}

		//3.查询用户有效直推人数
		int effectiveDirectNum = cmCustomerMinerService.getYouxiaoZtNumByCustomerId(customerId);

		//4.查询用户团队人数
		int teamNum = cmCustomerMinerService.getTeamNumByCustomerId(customerId);

		//5.查询用户等级信息
		QueryFilter filterCmCustomer = new QueryFilter(Cm2Customer.class);
		filterCmCustomer.addFilter("customerId=", customerId);
		Cm2Customer cmCustomer = super.get(filterCmCustomer);
		if(cmCustomer==null){
			cmCustomer = new Cm2Customer();
			cmCustomer.setCustomerId(customerId);
			cmCustomer.setIsEdit1(1);
			cmCustomer.setIsEdit2(1);
			super.save(cmCustomer);
		}
		cmCustomer.setCustomerId(customerId);
		cmCustomer.setPersonalAchievement(myTouru);
		cmCustomer.setTeamAchievement(myTouru.add(myteamTouru));
		cmCustomer.setTeamNum(teamNum);
		cmCustomer.setEffectiveDirectNum(effectiveDirectNum);


		//更新矿工等级
		cmCustomer = this.updateGrade1(cmCustomer);

		//保存信息到redis中，保存1小时
		//redisService.save("CM2:CUSTOMERGRADE_CUSTOMERID:"+customerId, JSON.toJSONString(cmCustomer),60*60*1);

	}

	@Override
	public void buyAndcloseMinerUpdateGrade(String messageStr) {
		// TODO Auto-generated method stub
		/**
		 * 1.发送更新用户等级消息
		 * 2.发送消息更新所有上级等级
		 */
		Long customerId = Long.valueOf(messageStr);
		messageProducer.toCmUpdateGrade(customerId.toString());

		//查询用户所有上级
		QueryFilter filterCmTeamlevel = new QueryFilter(Cm2Teamlevel.class);
		filterCmTeamlevel.addFilter("customerId=", customerId);
		List<Cm2Teamlevel> list = cmTeamlevelService.find(filterCmTeamlevel);
		if(list!=null&&list.size()>0){
			for(Cm2Teamlevel cmTeamlevel:list){
				if(cmTeamlevel.getParentId()==0){
					continue;
				}
				messageProducer.toCmUpdateGrade(cmTeamlevel.getParentId().toString());
			}
		}
	}


	/**
	 * 更新用户矿工等级
	 * @param cmCustomer
	 */
	private Cm2Customer updateGrade1(Cm2Customer cmCustomer){
		/**
		 * 1.查询矿工等级配置信息
		 * 2.对比更新
		 */
		//1.查询矿工等级配置信息
		QueryFilter filterGradeMiner = new QueryFilter(Cm2GradeMiner.class);
		filterGradeMiner.setOrderby("grade desc");
		List<Cm2GradeMiner> list = cmGradeMinerService.find(filterGradeMiner);
		if(list!=null&&list.size()>0){
			BigDecimal personalAchievement = cmCustomer.getPersonalAchievement();
			BigDecimal teamAchievement = cmCustomer.getTeamAchievement();//团队业绩
			BigDecimal xiajiTouru = teamAchievement.subtract(personalAchievement);//伞下投入= 团队业绩-个人投入
			Integer effectiveDirectNum = cmCustomer.getEffectiveDirectNum();
			boolean b = false;
			Cm2Customer cmCustomerOld = new Cm2Customer();
			cmCustomerOld.setCustomerId(cmCustomer.getCustomerId());
			cmCustomerOld.setGrade1(cmCustomer.getGrade1());
			cmCustomerOld.setGradeName1(cmCustomer.getGradeName1());
			for(Cm2GradeMiner cmGradeMiner:list){
				//对比个人投入
				BigDecimal thisInvestment = cmGradeMiner.getGradeCondition();
				if(personalAchievement.compareTo(thisInvestment)<0){
					//自投要求不满足
					continue;
				}
				BigDecimal teamInvestment = cmGradeMiner.getTeamInvestment();//伞下投入要求
				if(xiajiTouru.compareTo(teamInvestment)<0){
					//伞下投入不满足
					continue;
				}
				Integer eDirectNum = cmGradeMiner.getEffectiveDirectNum();//有效直推人数
				if(effectiveDirectNum<eDirectNum){
					//有效直推人数不满足
					continue;
				}
				//判断是否人工修改1否 2是
				if(cmCustomer.getIsEdit1()==2){
					b = true;
					if(cmCustomer.getGrade1()>=cmGradeMiner.getGrade()){
						//人工修改等级大于用户实际等级
						break;
					}
				}

				//满足升级条件
				cmCustomer.setGrade1(cmGradeMiner.getGrade());
				cmCustomer.setGradeName1(cmGradeMiner.getGradeName());
				cmCustomer.setIsEdit1(1);

				b = true;
				//插入升级记录
				this.insertGradeRecord(cmCustomerOld, cmCustomer, 1);
				break;
			}
			if(!b){
				//如果等级都不满足
				cmCustomer.setGrade1(0);
				cmCustomer.setGradeName1("无等级");
			}
		}
		super.update(cmCustomer);
		return cmCustomer;
	}

	/**
	 * 插入升级记录
	 * @param cmCustomerOld 原等级
	 * @param CmCustomerNew 新等级
	 * @param gradeType 等级类型 1矿工等级  2矿场等级
	 */
	private void insertGradeRecord(Cm2Customer cmCustomerOld,Cm2Customer cmCustomerNew,int gradeType){
		Cm2GradeRecord cmGradeRecord = new Cm2GradeRecord();
		cmGradeRecord.setCustomerId(cmCustomerOld.getCustomerId());
		cmGradeRecord.setGradeType(gradeType);
		if(gradeType==1){
			if(cmCustomerNew.getGrade1()==cmCustomerOld.getGrade1()){
				return;
			}
			cmGradeRecord.setNewGrade(cmCustomerNew.getGrade1());
			cmGradeRecord.setNewGradeName(cmCustomerNew.getGradeName1());
			cmGradeRecord.setOldGrade(cmCustomerOld.getGrade1());
			cmGradeRecord.setOldGradeName(cmCustomerOld.getGradeName1());
		}else if(gradeType==2){
			if(cmCustomerNew.getGrade1()==cmCustomerOld.getGrade1()){
				return;
			}
			cmGradeRecord.setNewGrade(cmCustomerNew.getGrade2());
			cmGradeRecord.setNewGradeName(cmCustomerNew.getGradeName2());
			cmGradeRecord.setOldGrade(cmCustomerOld.getGrade2());
			cmGradeRecord.setOldGradeName(cmCustomerOld.getGradeName2());
		}

		cmGradeRecord.setRemark("自动升级");

		cmGradeRecordService.save(cmGradeRecord);
	}

	@Override
	public Cm2Customer getCustomerByCustomerId(Long customerId) {
		// TODO Auto-generated method stub
		return ((Cm2CustomerDao)dao).getCustomerByCustomerId(customerId);
	}



}
