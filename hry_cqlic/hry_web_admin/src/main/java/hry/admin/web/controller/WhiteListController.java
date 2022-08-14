/**
 * Copyright:    
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-07-11 14:27:21 
 */
package hry.admin.web.controller;


import com.alibaba.fastjson.JSONArray;
import hry.admin.customer.model.AppCustomer;
import hry.admin.customer.model.AppPersonInfo;
import hry.admin.customer.service.AppCustomerService;
import hry.admin.customer.service.AppPersonInfoService;
import hry.admin.web.model.WhiteList;
import hry.admin.web.service.WhiteListService;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.CommonLog;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright:   互融云
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-07-11 14:27:21 
 */
@Controller
@RequestMapping("/web/whitelist")
public class WhiteListController extends BaseController<WhiteList, Long> {
	
	@Resource(name = "whiteListService")
	@Override
	public void setService(BaseService<WhiteList, Long> service) {
		super.service = service;
	}

	@Resource
	private AppPersonInfoService appPersonInfoService;

	@Resource
	private AppCustomerService appCustomerService;
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		WhiteList whiteList = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/web/whitelistsee");
		mav.addObject("model", whiteList);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,WhiteList whiteList){
		return super.save(whiteList);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		WhiteList whiteList = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/web/whitelistmodify");
		mav.addObject("model", whiteList);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,WhiteList whiteList){
		return super.update(whiteList);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	@CommonLog(name = "移除白名单")
	public JsonResult remove(String ids){
		JsonResult jsonResult = new JsonResult();
		if (!StringUtils.isEmpty(ids)) {
			String[] idArr = ids.split(",");
			try {
				for (String id : idArr) {
                    WhiteList white = service.get(new Long(id));
                    AppCustomer appCustomer = appCustomerService.get(white.getUserId());
                    appCustomer.setIsBlacklist(0); // 取消白名单
                    appCustomerService.update(appCustomer);
                }
				return super.deleteBatch(ids);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				jsonResult.setSuccess(false);
			}
		}
		jsonResult.setSuccess(false);
		return jsonResult;
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		Map<String, String> paraMap = new HashMap<>();
		String offset = request.getParameter("offset");
		String limit = request.getParameter("limit");
		String email = request.getParameter("email_like");
		String mobilePhone = request.getParameter("tel_like");
		String trueName = request.getParameter("trueName_like");
		String ip = request.getParameter("ip_like");
		String type = request.getParameter("type_like");
		paraMap.put("offset", offset);
		paraMap.put("limit", limit);
		paraMap.put("email", "%" + email + "%");
		paraMap.put("tel", "%" + mobilePhone + "%");
		paraMap.put("trueName", "%" + trueName + "%");
		paraMap.put("ip", ip);
		paraMap.put("type", type);
		paraMap.put("isBlacklist", "0");
		return ((WhiteListService)service).findWhiteListBySql(paraMap);
	}
	
	@RequestMapping(value = "/customList")
	@ResponseBody
	public PageResult customList(HttpServletRequest request) {
		Map<String, String> paraMap = new HashMap<>();
		String offset = request.getParameter("offset");
		String limit = request.getParameter("limit");

		String email = request.getParameter("email_like");
		String mobilePhone = request.getParameter("mobilePhone_like");
		String trueName = request.getParameter("trueName_like");
		String cardId = request.getParameter("cardId");
		paraMap.put("offset", offset);
		paraMap.put("limit", limit);
		paraMap.put("email", "%" + email + "%");
		paraMap.put("mobilePhone", "%" + mobilePhone + "%");
		paraMap.put("trueName", "%" + trueName + "%");
		paraMap.put("cardId", "%" + cardId);
		paraMap.put("isBlacklist", "0");
		return ((WhiteListService)service).findCustomListByPage(paraMap);
	}

	@RequestMapping(value = "/addWhithOfCustom")
	@ResponseBody
	public JsonResult addWhithOfCustom(HttpServletRequest request) {
		String whiteData = request.getParameter("whiteData");
		JsonResult jsonResult = new JsonResult();
		jsonResult.setSuccess(false);
		if (!StringUtils.isEmpty(whiteData)) {
			List<Map> whiteList = JSONArray.parseArray(whiteData, Map.class);
			if (whiteList != null && whiteList.size() > 0) {
				for (Map map : whiteList) {
					WhiteList white = new WhiteList();
					Long id = Long.valueOf(String.valueOf(map.get("id")));
					AppCustomer appCustomer = appCustomerService.get(id);
					AppPersonInfo appPersonInfo = appPersonInfoService.get(new QueryFilter(AppPersonInfo.class).addFilter("customerId=", id));
					white.setUserId(id);
					white.setUserName(appCustomer.getUsername());
					white.setTel(appPersonInfo.getMobilePhone());
					white.setEmail(appPersonInfo.getEmail());
					String userName = appPersonInfo.getSurname() != null ? appPersonInfo.getSurname() + (appPersonInfo.getTrueName() != null ? appPersonInfo.getTrueName() : "") : "";
 					white.setTrueName(userName);
					white.setIp(map.get("loginIp") == null ? "" : map.get("loginIp").toString());
					white.setLoginNum(Long.valueOf(String.valueOf(map.get("loginNum"))));
					white.setType(1); // 手动添加
					white.setCreated(new Date());
					white.setLoginLast(null);
					if (map.get("loginLastTime") != null) {
						String loginLastTime = map.get("loginLastTime").toString();
						try {
							Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(loginLastTime);
							white.setLoginLast(date);
						} catch (ParseException e) {
							e.printStackTrace();
						}
					}

					appCustomer.setIsBlacklist(2); // 白名单
					//appCustomer.setSafeLoginType(2);
					appCustomerService.update(appCustomer);

					super.save(white);
				}
				jsonResult.setSuccess(true);
			}
		}
		return jsonResult;
	}
	
}
