/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0 
 * @Date:        2016年5月13日 上午11:36:12
 */
package hry.web.quartz;

import hry.core.quartz.QuartzJob;
import hry.core.quartz.QuartzManager;
import hry.core.quartz.ScheduleJob;
import hry.util.QueryFilter;
import hry.util.sys.ContextUtil;
import hry.web.quartz.model.AppQuartzJob;
import hry.web.quartz.service.AppQuartzJobService;

import java.util.List;

import org.apache.log4j.Logger;
import org.quartz.Job;

/**
 * <p> TODO</p>
 * @author:         Liu Shilei 
 * @Date :          2016年5月13日 上午11:36:12 
 */
public class QuartzEngine {
	private static Logger logger = Logger.getLogger(QuartzEngine.class);
	/**
	 * 启动定时任务
	 * <p> TODO</p>
	 * @author:         Liu Shilei
	 * @param:    @param job
	 * @return: void 
	 * @Date :          2016年5月13日 下午1:21:22   
	 * @throws:
	 */
	public static void startJob(AppQuartzJob job){
		ScheduleJob job1 = new ScheduleJob();
		job1.setBeanClass(job.getBeanClass());
		job1.setMethodName(job.getMethodName());
		QuartzManager.addJob(job.getBeanClass()+job.getId(), job1,QuartzJob.class, job.getQuarzTime());  
	}
	
	/**
	 * 结束定时任务
	 * <p> TODO</p>
	 * @author:         Liu Shilei
	 * @param:    @param job
	 * @return: void 
	 * @Date :          2016年5月13日 下午1:21:34   
	 * @throws:
	 */
	public static void endJob(AppQuartzJob job){
		QuartzManager.removeJob(job.getBeanClass()+job.getId());
	}

	/**
	 * 开机启动方法
	 * <p> TODO</p>
	 * @author:         Liu Shilei
	 * @param:    
	 * @return: void 
	 * @Date :          2016年5月13日 下午12:15:37   
	 * @throws:
	 */
	public static void startUp() {
		AppQuartzJobService  jobService = (AppQuartzJobService) ContextUtil.getBean("appQuartzJobService");
		List<AppQuartzJob> find = jobService.findAll();
		
		for(AppQuartzJob job : find){
			logger.error("启动定时器："+job.getName());
			startJob(job);
			job.setStart(1);
			jobService.update(job);
		}
		
	}
	
	
}
