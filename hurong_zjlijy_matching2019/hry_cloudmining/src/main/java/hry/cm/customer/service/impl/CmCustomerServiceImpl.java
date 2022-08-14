/**
 * Copyright:   
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-08-01 10:27:19 
 */
package hry.cm.customer.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import hry.cm.customer.dao.CmCustomerDao;
import hry.cm.customer.model.CmCustomer;
import hry.cm.customer.service.CmCustomerService;
import hry.cm.customerminer.service.CmCustomerMinerService;
import hry.cm.grade.model.CmGradeMiner;
import hry.cm.grade.model.CmGradeMinercamps;
import hry.cm.grade.model.CmGradeRecord;
import hry.cm.grade.service.CmGradeMinerService;
import hry.cm.grade.service.CmGradeMinercampsService;
import hry.cm.grade.service.CmGradeRecordService;
import hry.cm.team.model.CmTeamlevel;
import hry.cm.team.service.CmTeamlevelService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.mq.producer.service.MessageProducer;
import hry.redis.common.utils.RedisService;
import hry.util.QueryFilter;

/**
 * <p> CmCustomerService </p>
 * @author:         yaozh
 * @Date :          2019-08-01 10:27:19  
 */
@Service("cmCustomerService")
public class CmCustomerServiceImpl extends BaseServiceImpl<CmCustomer, Long> implements CmCustomerService{
	
	@Resource(name="cmCustomerDao")
	@Override
	public void setDao(BaseDao<CmCustomer, Long> dao) {
		super.dao = dao;
	}
	
	@Resource
	private CmCustomerMinerService cmCustomerMinerService;
	@Resource
	private CmGradeMinerService cmGradeMinerService;
	@Resource
	private CmGradeMinercampsService cmGradeMinercampsService;
	@Resource
	private RedisService redisService;
	@Resource
	private CmGradeRecordService cmGradeRecordService;
	@Resource
	private CmTeamlevelService cmTeamlevelService;
	@Resource
	private MessageProducer messageProducer;

	@Override
	public void updateGrade(String messageStr) {
		// TODO Auto-generated method stub
		/***
		 * 1.查询用户投入
		 * 2.查询用户伞下投入
		 * 3.查询用户最大线最小线
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
		//3.查询用户最大线 最小线
		CmCustomer maxAndMin = cmCustomerMinerService.getTeamMaxAndMinByCustomerId(customerId);
		BigDecimal maxTeamTouru = new BigDecimal(0);//最大线
		BigDecimal minTeamTouru =  new BigDecimal(0);//最小线
		if(maxAndMin!=null){
			maxTeamTouru = maxAndMin.getMaxAchievement()==null?new BigDecimal(0):maxAndMin.getMaxAchievement();//最大线
			minTeamTouru = maxAndMin.getMinAchievement()==null?new BigDecimal(0):maxAndMin.getMinAchievement();//最小线

		}
		if(maxTeamTouru==null){
			maxTeamTouru = new BigDecimal(0);
		}
		if(minTeamTouru==null){
			minTeamTouru = new BigDecimal(0);
		}
		//4.查询用户团队人数
		int teamNum = cmCustomerMinerService.getTeamNumByCustomerId(customerId);
		
		//5.查询用户等级信息
		QueryFilter filterCmCustomer = new QueryFilter(CmCustomer.class);
		filterCmCustomer.addFilter("customerId=", customerId);
		CmCustomer cmCustomer = super.get(filterCmCustomer);
		if(cmCustomer==null){
			cmCustomer = new CmCustomer();
			cmCustomer.setCustomerId(customerId);
			cmCustomer.setIsEdit1(1);
			cmCustomer.setIsEdit2(1);
			super.save(cmCustomer);
		}
		cmCustomer.setCustomerId(customerId);
		cmCustomer.setMaxAchievement(maxTeamTouru);
		//小区业绩= 伞下业绩-最大线业绩
		cmCustomer.setMinAchievement(myteamTouru.subtract(maxTeamTouru).setScale(6,BigDecimal.ROUND_HALF_UP));
		cmCustomer.setPersonalAchievement(myTouru);
		cmCustomer.setTeamAchievement(myTouru.add(myteamTouru));
		cmCustomer.setTeamNum(teamNum);
		
		
		//更新矿工等级
		cmCustomer = this.updateGrade1(cmCustomer);
		//更新矿场等级
		cmCustomer = this.updateGrade2(cmCustomer);
		
		//保存信息到redis中，保存1小时
		redisService.save("CM:CUSTOMERGRADE_CUSTOMERID:"+customerId, JSON.toJSONString(cmCustomer),60*60*1);

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
		QueryFilter filterCmTeamlevel = new QueryFilter(CmTeamlevel.class);
		filterCmTeamlevel.addFilter("customerId=", customerId);
		List<CmTeamlevel> list = cmTeamlevelService.find(filterCmTeamlevel);
		if(list!=null&&list.size()>0){
			for(CmTeamlevel cmTeamlevel:list){
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
	private CmCustomer updateGrade1(CmCustomer cmCustomer){
		/**
		 * 1.查询矿工等级配置信息
		 * 2.对比更新
		 */
		//1.查询矿工等级配置信息
		QueryFilter filterGradeMiner = new QueryFilter(CmGradeMiner.class);
		filterGradeMiner.setOrderby("grade desc");
		List<CmGradeMiner> list = cmGradeMinerService.find(filterGradeMiner);
		if(list!=null&&list.size()>0){
			BigDecimal personalAchievement = cmCustomer.getPersonalAchievement();
			boolean b = false;
			CmCustomer cmCustomerOld = new CmCustomer();
			cmCustomerOld.setCustomerId(cmCustomer.getCustomerId());
			cmCustomerOld.setGrade1(cmCustomer.getGrade1());
			cmCustomerOld.setGradeName1(cmCustomer.getGradeName1());
			for(CmGradeMiner cmGradeMiner:list){
				//对比个人投入
				BigDecimal gradeCondition = cmGradeMiner.getGradeCondition();
				if(personalAchievement.compareTo(gradeCondition)>=0){
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
	 * 更新矿场等级
	 * @param cmCustomer
	 * @return
	 */
	private CmCustomer updateGrade2(CmCustomer cmCustomer){
		/**
		 * 1.查询矿场等级配置信息
		 * 2.对比升级
		 */
		
		//1.查询矿场等级配置信息
		QueryFilter filterGradeMinercamps = new QueryFilter(CmGradeMinercamps.class);
		filterGradeMinercamps.setOrderby("grade desc");
		List<CmGradeMinercamps> list = cmGradeMinercampsService.find(filterGradeMinercamps);
		CmCustomer cmCustomerNew = new CmCustomer();
		if(list!=null&& list.size()>0){
			BigDecimal personalAchievement = cmCustomer.getPersonalAchievement();//个人投入
			BigDecimal teamAchievement = cmCustomer.getTeamAchievement();//团队业绩
			BigDecimal xiajiTouru = teamAchievement.subtract(personalAchievement);//伞下投入= 团队业绩-个人投入
			BigDecimal maxAchievement = cmCustomer.getMaxAchievement();//最大线业绩
			BigDecimal noMaxTouru = xiajiTouru.subtract(maxAchievement);//伞下最大线以外投入
			boolean b = false;
			CmCustomer cmCustomerOld = new CmCustomer();
			cmCustomerOld.setCustomerId(cmCustomer.getCustomerId());
			cmCustomerOld.setGrade2(cmCustomer.getGrade2());
			cmCustomerOld.setGradeName2(cmCustomer.getGradeName2());
			for(CmGradeMinercamps cmGradeMinercamps:list){
				BigDecimal thisInvestment = cmGradeMinercamps.getThisInvestment();//自投要求
				if(personalAchievement.compareTo(thisInvestment)<0){
					//自投要求不满足
					continue;
				}
				BigDecimal teamInvestment = cmGradeMinercamps.getTeamInvestment();//伞下投入要求
				if(xiajiTouru.compareTo(teamInvestment)<0){
					//伞下投入不满足
					continue;
				}
				BigDecimal maxTeamInvestment = cmGradeMinercamps.getMaxTeamInvestment();//最大线以外投入
				if(noMaxTouru.compareTo(maxTeamInvestment)<0){
					//最大线以外投入不满足
					continue;
				}
				//判断是否人工修改1否 2是
				if(cmCustomer.getIsEdit2()==2){
					b = true;
					if(cmCustomer.getGrade2()>=cmGradeMinercamps.getGrade()){
						//人工修改等级大于用户实际等级，不做修改
						break;
					}
				}
				//满足等级要求,更新矿场等级
				cmCustomer.setGrade2(cmGradeMinercamps.getGrade());
				cmCustomer.setGradeName2(cmGradeMinercamps.getGradeName());
				cmCustomer.setIsEdit2(1);
				
				
				cmCustomerNew.setId(cmCustomer.getId());
				cmCustomerNew.setGrade2(cmGradeMinercamps.getGrade());
				cmCustomerNew.setGradeName2(cmGradeMinercamps.getGradeName());
				cmCustomerNew.setIsEdit2(1);
				
				b = true;
				//插入升级记录
				this.insertGradeRecord(cmCustomerOld, cmCustomer, 2);
				break;
			}
			if(!b){
				cmCustomer.setGrade2(0);
				cmCustomer.setGradeName2("无等级");
			}
		}
		
		super.update(cmCustomerNew);
		return cmCustomer;
		
	}
	
	/**
	 * 插入升级记录
	 * @param cmCustomerOld 原等级
	 * @param CmCustomerNew 新等级
	 * @param gradeType 等级类型 1矿工等级  2矿场等级
	 */
	private void insertGradeRecord(CmCustomer cmCustomerOld,CmCustomer cmCustomerNew,int gradeType){
		CmGradeRecord cmGradeRecord = new CmGradeRecord();
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
	public CmCustomer getCustomerByCustomerId(Long customerId) {
		// TODO Auto-generated method stub
		return ((CmCustomerDao)dao).getCustomerByCustomerId(customerId);
	}
	

}
