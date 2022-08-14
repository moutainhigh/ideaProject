/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-05-09 17:38:17 
 */
package hry.mall.retailstore.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.annotation.Resource;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.mall.retailstore.model.Activity;
import hry.mall.retailstore.model.ActivityGoods;
import hry.mall.retailstore.model.ActivityTime;
import hry.mall.retailstore.service.ActivityGoodsService;
import hry.mall.retailstore.service.ActivityService;
import hry.mall.retailstore.service.ActivityTimeService;
import org.springframework.stereotype.Service;

/**
 * <p> ActivityGoodsService </p>
 * @author:         zhouming
 * @Date :          2019-05-09 17:38:17  
 */
@Service("activityGoodsService")
public class ActivityGoodsServiceImpl extends BaseServiceImpl<ActivityGoods, Long> implements ActivityGoodsService {
	@Resource
	public ActivityService activityService;
	@Resource
	public ActivityTimeService activityTimeService;
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	
	
	@Resource(name="activityGoodsDao")
	@Override
	public void setDao(BaseDao<ActivityGoods, Long> dao) {
		super.dao = dao;
	}

	@Override
	public Boolean isValid(ActivityGoods activityGoods) {
		// TODO Auto-generated method stub
		Activity activity=activityService.get(activityGoods.getActivityId());
		Date date=new Date();
		//1、团购活动根据起始时间判断
		if(1==activity.getType()){
			/*if(0==activity.getStatus() ||  activity.getEndTime().compareTo(date)<0 || date.compareTo(activity.getStartTime())<0 ){
				return false;
			}*///该验证方法只用在抢购商品下单，因此该商品是团购，默认无效即可
			return false;
		}else{
	   //2、抢购商品
	   if(0==activity.getStatus()){
		   return false;
	   }
	   //判断当前时间是否在日期有效期内
	   String date1=sdf.format(date);
	   String sdate=sdf.format(activity.getStartTime());
	   String edate=sdf.format(activity.getEndTime());
	   //当前日期小于开始日期或者当前日期大于结束日期，活动无效
	   if(date1.compareTo(sdate)<0 || date1.compareTo(edate)>0){
		   return false;
	   }
	   //判断是否在有效的时间段内
	   ActivityTime time=activityTimeService.get(activityGoods.getActivityTimeId());
	   if(null==time){
		   return false; 
	   }
	   SimpleDateFormat sfm=new SimpleDateFormat("HH:mm:ss");
	   String time1=sfm.format(date);
	   String stime=time.getStartTime();
	   String etime=time.getEndTime();
	   if(time1.compareTo(stime)<0 || time1.compareTo(etime)>0){
		   return false;
	   }
	}
		return true;
		
	}
	

}
