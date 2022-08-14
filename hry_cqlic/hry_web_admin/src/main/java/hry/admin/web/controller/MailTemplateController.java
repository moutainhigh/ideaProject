/**
 * Copyright:    
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-20 15:40:16 
 */
package hry.admin.web.controller;


import hry.admin.dic.service.AppDicService;
import hry.admin.web.model.MailTemplate;
import hry.admin.web.service.MailTemplateService;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.CommonLog;
import hry.core.annotation.MyRequiresPermissions;
import hry.core.annotation.base.MethodName;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.JsoupUtil;
import hry.util.QueryFilter;
import hry.util.xss.XssHttpServletRequestWrapper;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Copyright:   互融云
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-20 15:40:16 
 */
@Controller
@RequestMapping("/web/mailtemplate")
public class MailTemplateController extends BaseController<MailTemplate, Long> {

	@Resource(name = "mailTemplateService")
	@Override
	public void setService(BaseService<MailTemplate, Long> service) {
		super.service = service;
	}
	@Resource(name = "appDicService")
	private AppDicService appDicService;

	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		MailTemplate mailTemplate = service.get(id);
		mailTemplate.setTempContent(StringEscapeUtils.unescapeHtml4( mailTemplate.getTempContent()));
		ModelAndView mav = new ModelAndView("/admin/web/mailtemplatesee");
		mav.addObject("model", mailTemplate);
		return mav;
	}
	@MethodName(name="增加MailTemplate")
	@RequestMapping(value="/add")
	@MyRequiresPermissions
	@ResponseBody
	public JsonResult add(HttpServletRequest request,MailTemplate mailTemplate){
		if(mailTemplate.getTempStatus()==1){
			MailTemplateService mailTemplateService=(MailTemplateService)service;
			mailTemplateService.updateTemplateStauts(mailTemplate);
		}
		mailTemplate.setTempContent(StringEscapeUtils.unescapeHtml4( mailTemplate.getTempContent()));
		mailTemplate.setTempContent(StringEscapeUtils.unescapeHtml4( mailTemplate.getTempContent()));
		mailTemplate.setTempContent(mailTemplate.getTempContent().replaceAll("&","%3c"));
		mailTemplate.setTempContent(JsoupUtil.clean(mailTemplate.getTempContent()));
		return super.save(mailTemplate);
	}
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		MailTemplate mailTemplate = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/web/mailtemplatemodify");
		mav.addObject("model", mailTemplate);
		return mav;
	}
	@MethodName(name="修改MailTemplate")
	@RequestMapping(value="/modify")
	@MyRequiresPermissions
	@ResponseBody
	@CommonLog(name = "电邮模板-编辑")
	public JsonResult modify(HttpServletRequest request,MailTemplate mailTemplate){
		mailTemplate.setTempContent(StringEscapeUtils.unescapeHtml4( mailTemplate.getTempContent()));
		mailTemplate.setTempContent(StringEscapeUtils.unescapeHtml4( mailTemplate.getTempContent()));
		mailTemplate.setTempContent(mailTemplate.getTempContent().replaceAll("&","%3c"));
		mailTemplate.setTempContent(JsoupUtil.clean(mailTemplate.getTempContent()));
		if(mailTemplate.getTempStatus()==1){
			MailTemplate mailTemplate2 = service.get(mailTemplate.getId());
			if(mailTemplate2.getTempStatus()==1){

				return super.update(mailTemplate);
			}else{
				MailTemplate mailTemplate1=new MailTemplate();
				mailTemplate1.setTempKey(mailTemplate.getTempKey());
				mailTemplate1.setLanguageDic(mailTemplate.getLanguageDic());
				mailTemplate1.setTempStatus(mailTemplate.getTempStatus());
				Long count = service.count(mailTemplate1);
				if(count>0){
					JsonResult jsonResult = new JsonResult();
					jsonResult.setMsg("同一模板不能同时开启");
					jsonResult.setSuccess(false);
					return jsonResult;
				}else{
					return super.update(mailTemplate);
				}
			}
		}else{
			return super.update(mailTemplate);
		}

	}

	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}

	@MethodName(name = "列表MailTemplate")
	@RequestMapping("/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request,MailTemplate mailTemplate){
		QueryFilter filter = new QueryFilter(MailTemplate.class,request);
		MailTemplateService mailTemplateService=(MailTemplateService)service;
		PageResult pageResult =mailTemplateService.queryByPage(filter,mailTemplate);
		return pageResult;
	}





}
