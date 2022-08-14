/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年3月24日 下午2:04:29
 */
package hry.exchange.lend;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.exchange.lend.model.ExDmLend;
import hry.exchange.lend.model.ExDmPing;
import hry.exchange.lend.service.ExDmLendService;
import hry.exchange.lend.service.ExDmPingService;
import hry.mq.producer.service.MessageProducer;
import hry.redis.common.utils.RedisService;
import hry.util.properties.PropertiesUtils;
import hry.util.sys.ContextUtil;
import org.apache.log4j.Logger;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * TODO
 * </p>
 * 
 * @author: Gao Mimi
 * @Date : 2016年4月12日 下午4:45:50
 */
public class ExDmPingNorServiceImpl extends BaseServiceImpl<ExDmPing, Long> implements ExDmPingNorService {
	private Logger logger = Logger.getLogger(ExDmPing.class);
	@Resource(name = "exDmPingDao")
	@Override
	public void setDao(BaseDao<ExDmPing, Long> dao) {
		super.dao = dao;
	}

	@Resource
	public RedisService redisService;
	@Resource
	private ExDmPingService exDmPingService;
	@Resource
	private ExDmLendService exDmLendService;



	@Override
	public void MqjobRunTimeCulPingLendMoney() {
		MessageProducer messageProducer = (MessageProducer) ContextUtil.getBean("messageProducer");
		messageProducer.toLendPing("11");
	}
	@Override
	public void MqjobRunTimeRepayLendMoney() {
		MessageProducer messageProducer = (MessageProducer) ContextUtil.getBean("messageProducer");
		messageProducer.toLendRepay("11");
	}
	@Override
	public void MqjobRunTimeCulEndPingLendMoney() {
		jobRunTimeCulEndPingLendMoney();
	}
	
	@Override
	public void jobRunTimeCulPingLendMoney() {
		long start=System.currentTimeMillis();
		// System.out.println("平仓定时");
		//定时计算平仓， 这样的好处就是，币的涨跌，充值提，现都不用单独去加是否平仓或解除平仓
		/*Map<String, String> mapLoadWeb = PropertiesUtils.getLoadWeb();
		for (String website : mapLoadWeb.keySet()) {
			String currencyType = mapLoadWeb.get(website);
			pingLend(website, currencyType);
		}*/
		pingLend("cn", "cny");
		long end=System.currentTimeMillis();
		logger.info("平仓耗时=="+(end-start)+"ms");
    }
	// 定时计算需要平仓的
	public void pingLend(String website, String currencyType) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("website", website);
		map.put("currencyType", currencyType);
		map.put("lendCoinType", null);
		List<ExDmLend> list = exDmLendService.getMayPingCustomer(map);
		for (ExDmLend e : list) {
			List<ExDmPing> l = exDmPingService.getBycustomerid(e.getCustomerId(), e.getUserCode(), 1, currencyType, website);// 已进入平仓流程的
			if (null == l || l.size() == 0) {
				// System.out.println("平e.getCustomerId()="+e.getCustomerId());
				exDmPingService.pingByCustomerId(e.getCustomerId(), e.getUserCode(), currencyType, website);
			}
		}
	}
	@Override
	public void jobRunTimeRepayLendMoney() {
		//定时偿还已平仓
		long start=System.currentTimeMillis();
		List<ExDmPing> listp =exDmPingService.getPingBystatus(1, null, null);// 已进入平仓流程的
		for (ExDmPing l : listp) {
			exDmPingService.pingRepayflow(l.getCustomerId());
		}
		long end=System.currentTimeMillis();
		logger.info("定时对平仓还款耗时=="+(end-start)+"ms");
	}
	@Override
	public void jobRunTimeCulEndPingLendMoney() {
		//定时结束平仓， 这样的好处就是，币的涨跌，充值提，现都不用单独去加是否平仓或解除平仓
		Map<String, String> mapLoadWeb = PropertiesUtils.getLoadWeb();
		for (String website : mapLoadWeb.keySet()) {
			String currencyType = mapLoadWeb.get(website);
			endPing(website, currencyType);
		}
	}
	public void endPing(String website, String currencyType) {
		List<ExDmPing> listp =exDmPingService.getPingBystatus(1, currencyType, website);// 已进入平仓流程的
		for (ExDmPing l : listp) {

			Integer isPingWarehouse =exDmPingService.isPingWarehouse(l.getCustomerId(), l.getUserCode(), currencyType, website);
			if (isPingWarehouse == 0) {
				// 如果超过了安全区域就把正在平仓流程结束掉
				// 取消所有的委托
				exDmPingService.endPing(l);
			}
		}

	}
      
}