/**
 * Copyright:   
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-08-09 11:44:39 
 */
package hry.cm.profittwo.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import hry.util.idgenerate.IdGenerate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import hry.cm.deal.CmDealUtil;
import hry.cm.dto.Accountadd;
import hry.cm.dto.CmAccountRedis;
import hry.cm.enums.CmAccountRemarkEnum;
import hry.cm.log.service.CmExceptionLogService;
import hry.cm.profittwo.dao.CmProfitTwoDao;
import hry.cm.profittwo.model.CmProfitTwo;
import hry.cm.profittwo.service.CmProfitTwoService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.mq.producer.DealMsgUtil;
import hry.utils.BaseConfUtil;

/**
 * <p> CmProfitTwoService </p>
 * @author:         yaozh
 * @Date :          2019-08-09 11:44:39  
 */
@Service("cmProfitTwoService")
public class CmProfitTwoServiceImpl extends BaseServiceImpl<CmProfitTwo, Long> implements CmProfitTwoService{
	
	@Resource(name="cmProfitTwoDao")
	@Override
	public void setDao(BaseDao<CmProfitTwo, Long> dao) {
		super.dao = dao;
	}
	
	@Resource
	private CmExceptionLogService cmExceptionLogService;

	@Override
	public void grantProfit() {
		// TODO Auto-generated method stub
		/***
		 * 1.统计每个用户所有未发放收益的总数
		 * 2.发放收益
		 */
		List<CmProfitTwo> list = ((CmProfitTwoDao)dao).findProfitSum();
		if(list!=null&&list.size()>0){
			/**此处经常发生数据库死锁的问题题，改为不用线程***/
			/*ThreadPoolTaskExecutor taskExecutor= SpringUtil.getBean("serverManagementTaskExecutor");
			for(CmProfitTwo cmProfitTwo:list){
	        	taskExecutor.execute(new GrantProfitTwoRunnable(cmProfitTwo));
			}*/
			for(CmProfitTwo cmProfitTwo:list){
				try {
					Long customerId = cmProfitTwo.getCustomerId();
					BigDecimal profit = cmProfitTwo.getProfit3();
					/** 查询平台币Code */
					String platCoin = BaseConfUtil.getConfigSingle("platCoin", "configCache:basefinanceConfig");
					/**
					 * 功能描述: 需求变动，收益百分之30进入到冻结账户，等后期功能开发好后再修改回来，然后百分之30进到消费子账户
					 * Accountadd accountadd = new Accountadd(customerId, platCoin, cmAccountRedis.getId(),
					 * 				    		profit, 1, CmAccountRemarkEnum.TYPE9.getIndex(),
					 * 				      "CM"+profit);
					 * accountaddList.add(accountadd);
					 * @auther: yaozh
					 * @date: 2019/11/1 16:24
					 */
					//币账户加币
					/** 查询进入冻结的金额的比例 */
					String cloudminingP = BaseConfUtil.getConfigSingle("cloudminingCProportion", "configCache:cloudminingConfig");
					BigDecimal cp = new BigDecimal(cloudminingP).multiply(new BigDecimal(0.01));
					//计算进入冻结的金额
					BigDecimal jinDongjie = profit.multiply(cp);
					//进可用账户
					BigDecimal jinKeyong = profit.subtract(jinDongjie);

					/** 查询用户平台币账户信息 */
					CmAccountRedis cmAccountRedis = CmDealUtil.getCmAccount(customerId, platCoin);
					List<Accountadd> accountaddList = new ArrayList<>();
					//进可用
				    Accountadd accountaddHot = new Accountadd(customerId, platCoin, cmAccountRedis.getId(),
							jinKeyong, 1, CmAccountRemarkEnum.TYPE9.getIndex(),
							IdGenerate.transactionNum("CM"));
					accountaddList.add(accountaddHot);

					/**查询用户平台币消费账户信息**/
					hry.cmson.dto.CmAccountRedis cmSonAccountRedis = hry.cmson.deal.CmDealUtil.getCmAccount(customerId, platCoin);
					List<hry.cmson.dto.Accountadd> accountSonaddList = new ArrayList<>();
					hry.cmson.dto.Accountadd accountaddCold = new hry.cmson.dto.Accountadd(customerId, platCoin, cmSonAccountRedis.getId(),
							jinDongjie, 1, CmAccountRemarkEnum.TYPE9.getIndex(),
							IdGenerate.transactionNum("CM"));
				    //进冻结
					accountSonaddList.add(accountaddCold);

					//发消息
				    DealMsgUtil.sendAccountaddList(accountaddList);
				    DealMsgUtil.sendSonAccountaddList(accountSonaddList);

				    /*更新未发放收益为已发放**/
				    this.updateStatus(customerId);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					try {
						//插入异常日志
		    			cmExceptionLogService.insertlog(e, "CmProfitTwoServiceImpl.grantProfit", JSON.toJSONString(cmProfitTwo));
					} catch (Exception e2) {
						// TODO: handle exception
						e.printStackTrace();
					}
					
				}
			}
		}
		
	}

	@Override
	public void updateStatus(Long customerId) {
		// TODO Auto-generated method stub
		((CmProfitTwoDao)dao).updateStatus(customerId);
	}

	@Override
	public BigDecimal getTodayProfitSum(Long customerId) {
		// TODO Auto-generated method stub
		return ((CmProfitTwoDao)dao).getTodayProfitSum(customerId);
	}

	@Override
	public BigDecimal getTotalProfitSum(Long customerId) {
		// TODO Auto-generated method stub
		return ((CmProfitTwoDao)dao).getTotalProfitSum(customerId);
	}
	

}
