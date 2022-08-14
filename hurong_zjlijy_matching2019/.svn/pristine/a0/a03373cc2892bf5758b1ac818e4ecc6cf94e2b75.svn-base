/**
 * Copyright:   
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-10-15 09:46:51 
 */
package hry.cm2.profittwo.service.impl;

import com.alibaba.fastjson.JSON;
import hry.cm2.deal.CmDealUtil;
import hry.cm2.dto.Accountadd;
import hry.cm2.dto.CmAccountRedis;
import hry.cm2.enums.CmAccountRemarkEnum;
import hry.cm2.log.service.Cm2ExceptionLogService;
import hry.cm2.profittwo.dao.Cm2ProfitTwoDao;
import hry.cm2.profittwo.model.Cm2ProfitTwo;
import hry.cm2.profittwo.service.Cm2ProfitTwoService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;

import hry.mq.producer.DealMsgUtil;
import hry.utils.BaseConfUtil;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * <p> Cm2ProfitTwoService </p>
 * @author:         yaozh
 * @Date :          2019-10-15 09:46:51  
 */
@Service("cm2ProfitTwoService")
public class Cm2ProfitTwoServiceImpl extends BaseServiceImpl<Cm2ProfitTwo, Long> implements Cm2ProfitTwoService{
	
	@Resource(name="cm2ProfitTwoDao")
	@Override
	public void setDao(BaseDao<Cm2ProfitTwo, Long> dao) {
		super.dao = dao;
	}

	@Resource
	private Cm2ExceptionLogService cmExceptionLogService;

	@Override
	public void grantProfit() {
		// TODO Auto-generated method stub
		/***
		 * 1.统计每个用户所有未发放收益的总数
		 * 2.发放收益
		 */
		List<Cm2ProfitTwo> list = ((Cm2ProfitTwoDao)dao).findProfitSum();
		if(list!=null&&list.size()>0){
			/**此处经常发生数据库死锁的问题题，改为不用线程***/
			/*ThreadPoolTaskExecutor taskExecutor= SpringUtil.getBean("serverManagementTaskExecutor");
			for(CmProfitTwo cmProfitTwo:list){
	        	taskExecutor.execute(new GrantProfitTwoRunnable(cmProfitTwo));
			}*/
			for(Cm2ProfitTwo cmProfitTwo:list){
				try {
					Long customerId = cmProfitTwo.getCustomerId();
					BigDecimal profit = cmProfitTwo.getProfit3();
					/** 查询平台币Code */
					String platCoin = BaseConfUtil.getConfigSingle("platCoin", "configCache:basefinanceConfig");
					//币账户加币
					/** 查询用户平台币账户信息 */
					CmAccountRedis cmAccountRedis = CmDealUtil.getCmAccount(customerId, platCoin);
					List<Accountadd> accountaddList = new ArrayList<>();
					Accountadd accountadd = new Accountadd(customerId, platCoin, cmAccountRedis.getId(),
							profit, 1, CmAccountRemarkEnum.TYPE9.getIndex(),
							"CM"+profit);
					accountaddList.add(accountadd);
					DealMsgUtil.sendAccountaddList(accountaddList);

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
		((Cm2ProfitTwoDao)dao).updateStatus(customerId);
	}

	@Override
	public BigDecimal getTodayProfitSum(Long customerId) {
		// TODO Auto-generated method stub
		return ((Cm2ProfitTwoDao)dao).getTodayProfitSum(customerId);
	}

	@Override
	public BigDecimal getTotalProfitSum(Long customerId) {
		// TODO Auto-generated method stub
		return ((Cm2ProfitTwoDao)dao).getTotalProfitSum(customerId);
	}



}
