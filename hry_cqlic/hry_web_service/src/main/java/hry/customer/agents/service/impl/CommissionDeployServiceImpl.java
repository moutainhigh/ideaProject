package hry.customer.agents.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import hry.account.fund.model.AppTransaction;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.model.AppConfig;
import hry.bean.JsonResult;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.util.QueryFilter;
import hry.util.sys.ContextUtil;
import hry.customer.agents.model.AppAgentsCustromer;
import hry.customer.agents.model.CommissionDeploy;
import hry.customer.agents.service.AppAgentsCustromerService;
import hry.customer.agents.service.CommissionDeployService;
import hry.web.remote.RemoteAppConfigService;

import org.springframework.stereotype.Service;

@Service("commissionDeployService")
public class CommissionDeployServiceImpl extends BaseServiceImpl<CommissionDeploy,Long> implements CommissionDeployService {

	@Resource(name="commissionDeployDao")
	@Override
	public void setDao(BaseDao<CommissionDeploy, Long> dao) {
		super.dao = dao;
	}
	
	@Resource(name="appAgentsCustromerService")
	public AppAgentsCustromerService appAgentsCustromerService;

	/**
	 * 传入充值提现订单查以及所产生的佣金数 
	 * 
	 * rank 表示第几级父  
	 * 
	 * 这个方法主要是为提现订单用的 
	 * 
	 */
	@Override
	public BigDecimal selectMoneyByOrder(AppTransaction transaction ,Integer rank) {

		// 这是手续费实际收了多少钱以后可以从订单中获取
			if(null != transaction && transaction.getStatus() == 2){
				Integer type = transaction.getTransactionType();
				if(type == 2 | type == 4){
					// 此方法的第一个参数  目前写死的 需要跟后台统一  返回佣金比例 rank表示几级父 --- 返回费率 
					BigDecimal rate = this.selectRateByOrder(1, rank);
					BigDecimal money = transaction.getFee();
					BigDecimal newmoney = rate.multiply(money);
					return newmoney;
				}
			}
		return BigDecimal.ZERO;
	}

	/**
	 * 
	 * 通过订单查询三个父 推荐人   返回一个list 
	 * 
	 * index 1 表示 表示 第一级父 
	 * index 2 表示 表示 第二级父 
	 * index 3 表示 表示 第三级父 
	 * 
	 */
	@Override
	public List<AppAgentsCustromer> findByTransaction(String userName) {

		List<AppAgentsCustromer> list = new ArrayList<AppAgentsCustromer>();
		
		if(null != userName){
			AppAgentsCustromer agentsCustromer1 = appAgentsCustromerService.findAgentsByCustromer(userName, 1);
			
			if(null != agentsCustromer1){
				list.add(agentsCustromer1);
			}else{
				return list;
			}
			
			AppAgentsCustromer agentsCustromer2 = appAgentsCustromerService.findAgentsByCustromer(userName, 2);
			if(null != agentsCustromer2){
				list.add(agentsCustromer2);
			}else{
				return list;
			}
			
			AppAgentsCustromer agentsCustromer3 = appAgentsCustromerService.findAgentsByCustromer(userName, 3);
			if(null != agentsCustromer3){
				list.add(agentsCustromer3);
			}else{
				return list;
			}
			return list;
		}
		
		
//		if(null != userName){
//			for(int i = 0;i<3;i++){
//				AppAgentsCustromer agentsCustromer4 = appAgentsCustromerService.findAgentsByCustromer(userName, i);
//				if(null != agentsCustromer4){
//					list.add(agentsCustromer4);
//				}else{
//					return list;
//				}
//			}
//		}
		
		
		
		
		
		return list;
		
	}

	/**
	 * 传入一个佣金费率类型和一个几级会员    返回一个费率
	 * 
	 * costId 表示什么类型
	 * rank 表示几级父代理
	 */
	@Override        
	public BigDecimal selectRateByOrder(Integer costId, Integer rank) {

		QueryFilter filter = new QueryFilter(CommissionDeploy.class);
	
		filter.addFilter("costId=", costId)	;
			// 查询不同的费率出来
		filter.addFilter("states=", 1);
		CommissionDeploy deploy = super.get(filter);
		   
		   if(null != deploy){
			   
			   if(1==rank){
				   return deploy.getOneRankRatio().divide(new BigDecimal(100));
			   }
			   if(2==rank){
				   return deploy.getTwoRankRatio().divide(new BigDecimal(100));
			   }
			   if(3==rank){
				   return deploy.getThreeRankRatio().divide(new BigDecimal(100));
			   }
		   }
			
		return BigDecimal.ZERO;
	}
	
	/**
	 * 通过一个佣金类型 和几级代理商 返回一个佣金配置对象 
	 * 
	 * costId 表示什么类型
	 * 
	 * rank 表示几级父代理
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年8月15日 下午3:44:33
	 */
	@Override
	public CommissionDeploy findCommissionDeploy(Integer costId){
		QueryFilter filter = new QueryFilter(CommissionDeploy.class);
		
		filter.addFilter("costId=", costId)	;
			// 查询不同的费率出来
		filter.addFilter("states=", 1);
		CommissionDeploy deploy = super.get(filter);
		
		return deploy;
	}
	
	
	
	
	
	
	
	
	

	/**
	 * 
	 *  传入一个钱数   一个 commissionType 佣金类型     (1 表示提现    2 表示交易 )
	 *  
	 *  传入 rank 一个几级父    返回一个佣金数量
	 * 
	 */
	@Override
	public BigDecimal selectCommissionByMoney(BigDecimal money,Integer commissionType, Integer rank) {
		
		BigDecimal decimal = this.selectRateByOrder(commissionType, rank);
		
		if(null != decimal){
			
			BigDecimal multiply = money.multiply(decimal);
			return multiply;
		}
		return BigDecimal.ZERO;
	}

	@Override
	public JsonResult SaveCommissionDeployByCostId(CommissionDeploy commissionDeploy) {
		
		
		JsonResult result = new JsonResult();
		result.setSuccess(true);;
		
		QueryFilter filter = new QueryFilter(CommissionDeploy.class);
		filter.addFilter("costId=", commissionDeploy.getCostId());
		filter.addFilter("states=", 1);
		CommissionDeploy commissionDeploy2 = super.get(filter);
		if(null != commissionDeploy2 && commissionDeploy.getStates() == 1){
			commissionDeploy2.setStates(0);
			super.update(commissionDeploy2);
			result.setMsg("修改并保存成功");
		}else{
			result.setMsg("保存成功");
		}
		if(null == commissionDeploy.getId()){
			super.save(commissionDeploy);
		}else{
			super.update(commissionDeploy);
		}
		return result;
		
	}

	/**
	 * 返回派發的佣金数
	 * 
	 * 两种类型中取最大值
	 */
	@Override
	public BigDecimal getStandardMoney() {
		QueryFilter filter = new QueryFilter(CommissionDeploy.class);
		filter.addFilter("states=", 1);
		List<CommissionDeploy> list = super.find(filter);
		if(list.size()>0){
			BigDecimal totalCount = BigDecimal.ZERO;
			for(int i = 0;i<list.size();i++){
				CommissionDeploy deploy = list.get(i);
				BigDecimal count = deploy.getSettlementMoney();
				int j = count.compareTo(totalCount);
				if(j>0){
					totalCount = count;
				}
			}
			return totalCount;
		}

		return BigDecimal.ZERO;
	}

	
	
}






