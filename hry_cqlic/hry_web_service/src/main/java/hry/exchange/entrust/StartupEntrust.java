/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Gao Mimi
 * @version:      V1.0
 * @Date:        2016年8月10日 下午8:06:46
 */
package hry.exchange.entrust;

import com.alibaba.fastjson.JSON;
import hry.core.quartz.QuartzJob;
import hry.core.quartz.QuartzManager;
import hry.core.quartz.ScheduleJob;
import hry.exchange.product.service.ExCointoCoinService;
import hry.exchange.product.service.ExProductService;
import hry.trade.entrust.model.ExEntrust;
import hry.trade.entrust.service.ExEntrustService;
import hry.trade.exEntrustOneDay.model.ExentrustOneday;
import hry.trade.exEntrustOneDay.service.ExentrustOnedayService;
import hry.util.QueryFilter;
import hry.util.sys.ContextUtil;

import java.util.List;

/**
 * <p>
 * TODO
 * </p>
 *
 * @author: Gao Mimi
 * @Date : 2016年8月10日 下午8:06:46
 */
public class StartupEntrust {
	public static void Entrustinit() {
		try {
			// 初始化,产品K值
			ExProductService exProductService = (ExProductService) ContextUtil.getBean("exProductService");
			// 初始化redis中cn:productList
			exProductService.initRedisCode();
			// 初始化交易数据==========================================================================================
			ExCointoCoinService exCointoCoinService = (ExCointoCoinService) ContextUtil.getBean("exCointoCoinService");
			exCointoCoinService.initRedisCode();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void initExentrustRedis() {

	}

	public static void initExentrustOnedayAndRadis() {
		ExEntrustService exEntrustService = (ExEntrustService) ContextUtil.getBean("exEntrustService");
		ExentrustOnedayService exentrustOnedayService = (ExentrustOnedayService) ContextUtil.getBean("exentrustOnedayService");
		// 先清空所有的挂单数据
		QueryFilter delfilter = new QueryFilter(ExentrustOneday.class);
		exentrustOnedayService.delete(delfilter);
		// 查询出所有的未成交的委托单status<2
		QueryFilter filter = new QueryFilter(ExEntrust.class);
		filter.addFilter("status<", 2);

		List<ExEntrust> list = exEntrustService.find(filter);
		ExentrustOneday exentrustOneday = null;
		for (ExEntrust l : list) {
			String exentrust = JSON.toJSONString(l);
			exentrustOneday = JSON.parseObject(exentrust, ExentrustOneday.class);
			exentrustOnedayService.save(exentrustOneday);
			// exEntrustService.saveExtrustToRedis(l);
		}


	}

	public static void EntrustJob() {

		ScheduleJob jobRunTimetimingCulAtferMoney = new ScheduleJob();
		jobRunTimetimingCulAtferMoney.setSpringId("exOrderInfoServiceNoTr");
		jobRunTimetimingCulAtferMoney.setMethodName("timingCulAtferMoney");
		QuartzManager.addJob("jobRunTimetimingCulAtferMoney", jobRunTimetimingCulAtferMoney, QuartzJob.class,"0 0/1 * * * ? ");// 10分钟
//		QuartzManager.addJob("jobRunTimetimingCulAtferMoney", jobRunTimetimingCulAtferMoney, QuartzJob.class, "0 0 0 * * ?");// 两秒0 0 24 * * ?0 0/5 * * * ?"0/60 * * * * ?

		// 定时解锁仓功能
		/*ScheduleJob jobRunTimetimingUnlockAccountCold = new ScheduleJob();
		jobRunTimetimingUnlockAccountCold.setSpringId("exDmManualUnlockService");
		jobRunTimetimingUnlockAccountCold.setMethodName("timingUnlockAccountCold");
		QuartzManager.addJob("jobRunTimetimingUnlockAccountCold", jobRunTimetimingUnlockAccountCold, QuartzJob.class,"0 5 0 * * ?");// 每天12点5分触发
*/	}
}
