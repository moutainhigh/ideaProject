/**
 * Copyright:    
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-04-18 19:00:43 
 */
package hry.admin.klg.limit.controller;


import hry.admin.klg.limit.service.KlgDesignatedRewardecordService;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.MyRequiresPermissions;
import hry.core.annotation.base.MethodName;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.admin.klg.limit.model.KlgDesignatedRewardecord;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Copyright:   互融云
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-04-18 19:00:43 
 */
@Controller
@RequestMapping("/klg/limit/klgdesignatedrewardecord")
public class KlgDesignatedRewardecordController extends BaseController<KlgDesignatedRewardecord, Long> {
	
	@Resource(name = "klgDesignatedRewardecordService")
	@Override
	public void setService(BaseService<KlgDesignatedRewardecord, Long> service) {
		super.service = service;
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(KlgDesignatedRewardecord.class,request);
		return  ((KlgDesignatedRewardecordService)service).findPageBySql(filter);
	}

	
}
