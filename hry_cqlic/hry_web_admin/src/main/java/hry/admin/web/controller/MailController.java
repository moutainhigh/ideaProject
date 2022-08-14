/**
 * Copyright:    
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-20 14:41:28 
 */
package hry.admin.web.controller;


import hry.admin.web.model.Mail;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.base.MethodName;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.util.email.EmailUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Copyright:   互融云
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-20 14:41:28 
 */
@Controller
@RequestMapping("/web/mail")
public class MailController extends BaseController<Mail, Long> {
	
	@Resource(name = "mailService")
	@Override
	public void setService(BaseService<Mail, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		Mail mail = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/web/mailsee");
		mav.addObject("model", mail);
		return mav;
	}
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,Mail mail){
		return super.save(mail);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		Mail mail = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/web/mailmodify");
		mav.addObject("model", mail);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,Mail mail){
		return super.update(mail);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		String address = request.getParameter("address");
		QueryFilter filter = new QueryFilter(Mail.class,request);
		filter.addFilter("address_like","%"+address+"%");
		filter.setOrderby("created desc");
		return super.findPage(filter);
	}

	@MethodName(name = "重新发送Mail")
	@RequestMapping("/reSend/{ids}")
	@ResponseBody
	public JsonResult reSend(@PathVariable String ids){
		JsonResult jsonResult = new JsonResult();
		QueryFilter filter = new QueryFilter(Mail.class);
		filter.addFilter("id=", ids);
		List<Mail> mails = super.find(filter);
		if(mails.size()>0){
			Mail mail = mails.get(0);
			boolean success = EmailUtil.sendMail(mail.getAddress(), mail.getTitle(), mail.getContent());
			if(success){
				mail.setErrorCode("成功");
			}
			super.update(mail);
			jsonResult.setSuccess(true);
		}else{
			jsonResult.setSuccess(false);
		}
		return jsonResult;
	}
	
	
}
