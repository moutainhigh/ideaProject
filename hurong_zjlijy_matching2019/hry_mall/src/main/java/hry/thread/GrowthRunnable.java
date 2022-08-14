package hry.thread;

import hry.bean.JsonResult;
import hry.mall.retailstore.service.GrowthDetailService;
import hry.util.SpringUtil;
import org.apache.log4j.Logger;

import java.util.Map;

public class GrowthRunnable implements Runnable {
	 private final Logger log = Logger.getLogger(GrowthRunnable.class);
	 private Map<String, Object> map;
	 public GrowthRunnable(Map<String, Object> map){
		 this.map=map;
		 
	 }
	 
	@Override
	public void run() {
		// TODO Auto-generated method stub
	     try {
	            Thread.sleep(1000);
	            System.out.println("账号memberId="+map.get("memberId")+"++++++++++++++++++++++进入发放成长值线程");
	            GrowthDetailService growthDetailService = SpringUtil.getBean("growthDetailService");
	            JsonResult jsonResult = growthDetailService.handleGrowth(map);
	            if(jsonResult.getSuccess()){
	                System.out.println("账号memberId="+map.get("memberId")+"+++++++发放成长值成功");
	            }else {
	                System.out.println("账号memberId="+map.get("memberId")+"-------发放成长值失败");
	            }
	        }catch (Exception e){
	            e.printStackTrace();
	        }
	}

}
