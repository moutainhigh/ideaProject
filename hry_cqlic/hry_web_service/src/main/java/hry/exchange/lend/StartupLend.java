/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Gao Mimi
 * @version:      V1.0 
 * @Date:        2016年8月10日 下午8:06:46
 */
package hry.exchange.lend;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;

import hry.core.quartz.QuartzJob;
import hry.core.quartz.QuartzManager;
import hry.core.quartz.ScheduleJob;
import hry.util.properties.PropertiesUtils;

/**
 * <p> TODO</p>
 * @author:         Gao Mimi 
 * @Date :          2016年8月10日 下午8:06:46 
 */
public class StartupLend {
	public static void LendJob(){
		/*String isLend=PropertiesUtils.APP.getProperty("app.isLend"); //是否启用融资融币
		if(isLend!=null&&!"".equals(isLend)&&isLend.equals("true")){
			
			System.out.println("启动了平仓功能");
			// 定时计算需要平仓的，走的mq=============================================================================================
			ScheduleJob obRunTimeCulPingLendMoney = new ScheduleJob();
			obRunTimeCulPingLendMoney.setSpringId("exDmPingNorService");
			obRunTimeCulPingLendMoney.setMethodName("MqjobRunTimeCulPingLendMoney");
			QuartzManager.addJob("obRunTimeCulPingLendMoney", obRunTimeCulPingLendMoney, QuartzJob.class," 0/10 * * * * ?");// 10秒
			// 定时给平仓的用户还款，走的mq=============================================================================================
			ScheduleJob obRunTimeRepayLendMoney = new ScheduleJob();
			obRunTimeRepayLendMoney.setSpringId("exDmPingNorService");
			obRunTimeRepayLendMoney.setMethodName("MqjobRunTimeRepayLendMoney");
			QuartzManager.addJob("jobRunTimeRepayLendMoney", obRunTimeRepayLendMoney, QuartzJob.class," 0/20 * * * * ?");// 10秒
		
			// =================================================================================================
			// 定时10秒计算如果平仓已经完成相关处理websokect（比如充钱了进来）,结束平仓==============================================================================================
			ScheduleJob obRunTimeCulEndPingLendMoney = new ScheduleJob();
			obRunTimeCulEndPingLendMoney.setSpringId("exDmPingNorService");
			obRunTimeCulEndPingLendMoney.setMethodName("MqjobRunTimeCulEndPingLendMoney");
			QuartzManager.addJob("obRunTimeCulEndPingLendMoney", obRunTimeCulEndPingLendMoney, QuartzJob.class," 0/10 * * * * ?");// 10秒
			// =================================================================================================
			
			// 定时凌晨0点计算融资的利息=============================================================================================
			ScheduleJob jobcalculateLendInterest = new ScheduleJob();
			jobcalculateLendInterest.setSpringId("exDmLendService");
			jobcalculateLendInterest.setMethodName("calculateLendInterest");                //0 0 1 * * ?  这凌晨一点
			QuartzManager.addJob("jobcalculateLendInterest", jobcalculateLendInterest, QuartzJob.class, " 0 0 1 * * ?");// 两秒
			// =================================================================================================

		}*/
	}
}
