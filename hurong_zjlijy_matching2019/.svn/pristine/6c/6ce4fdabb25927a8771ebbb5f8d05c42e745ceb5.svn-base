/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-05-21 16:26:01 
 */
package hry.mall.retailstore.service.impl;
import hry.bean.JsonResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.mall.retailstore.model.ActivityTime;
import hry.mall.retailstore.model.TimeTemplate;
import hry.mall.retailstore.service.ActivityGoodsService;
import hry.mall.retailstore.service.ActivityTimeService;
import hry.mall.retailstore.service.TimeTemplateService;
import hry.util.QueryFilter;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * <p> TimeTemplateService </p>
 * @author:         zhouming
 * @Date :          2019-05-21 16:26:01  
 */
@Service("timeTemplateService")
public class TimeTemplateServiceImpl extends BaseServiceImpl<TimeTemplate, Long> implements TimeTemplateService {

	@Resource
	private ActivityTimeService activityTimeService;
	@Resource
	private ActivityGoodsService activityGoodsService;


	@Resource(name="timeTemplateDao")
	@Override
	public void setDao(BaseDao<TimeTemplate, Long> dao) {
		super.dao = dao;
	}

	@Override
	public JsonResult delTemplate(String ids) {
		QueryFilter filter = new QueryFilter(ActivityTime.class);
		filter.addFilter("timeTempId_in", ids);
		List<ActivityTime> activityTimeList = activityTimeService.find(filter);
		// 循环删除绑定的商品
		for (ActivityTime list : activityTimeList) {
			QueryFilter filter2 = new QueryFilter(ActivityTime.class);
			filter2.addFilter("activityTimeId=", list.getId());
			activityGoodsService.delete(filter2);
			activityTimeService.delete(list.getId());
		}
		String[] idList = ids.split(",");
		for (int i=0; i<idList.length; i++){
			TimeTemplate timeTemplate = new TimeTemplate();
			timeTemplate.setId(Long.parseLong(idList[i]));
			super.dao.delete(timeTemplate);
		}
		return new JsonResult(true).setMsg("删除成功");
	}
}
