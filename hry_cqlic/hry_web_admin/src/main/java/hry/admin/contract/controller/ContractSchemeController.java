/**
 * Copyright:
 *
 * @author: hec
 * @version: V1.0
 * @Date: 2018-12-27 16:37:41
 */
package hry.admin.contract.controller;


import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.MyRequiresPermissions;
import hry.core.annotation.base.MethodName;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.BeanUtil;
import hry.util.QueryFilter;
import hry.admin.contract.model.ContractScheme;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Copyright:   互融云
 * @author: hec
 * @version: V1.0
 * @Date: 2018-12-27 16:37:41
 */
@Controller
@RequestMapping("/contract/contractscheme")
public class ContractSchemeController extends BaseController<ContractScheme, Long> {

    @Resource(name = "contractSchemeService")
    @Override
    public void setService(BaseService<ContractScheme, Long> service) {
        super.service = service;
    }


    @RequestMapping(value = "/see/{id}")
    public ModelAndView see(@PathVariable Long id) {
        ContractScheme contractScheme = service.get(id);
        ModelAndView mav = new ModelAndView("/admin/contract/contractschemesee");
        mav.addObject("model", contractScheme);
        return mav;
    }


    @RequestMapping(value = "/add")
    @ResponseBody
    public JsonResult add(HttpServletRequest request, ContractScheme contractScheme) {
        return super.save(contractScheme);
    }

    @RequestMapping(value = "/modifysee/{id}")
    public ModelAndView modifysee(@PathVariable Long id) {
        ContractScheme contractScheme = service.get(id);
        ModelAndView mav = new ModelAndView("/admin/contract/contractschememodify");
        mav.addObject("model", contractScheme);
        return mav;
    }

    @RequestMapping(value = "/modify")
    @ResponseBody
    public JsonResult modify(HttpServletRequest request, ContractScheme contractScheme) {
        return super.update(contractScheme);
    }


    @RequestMapping(value = "/remove")
    @ResponseBody
    public JsonResult remove(String ids) {
        return super.deleteBatch(ids);
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public PageResult list(HttpServletRequest request) {
        QueryFilter filter = new QueryFilter(ContractScheme.class, request);
        return super.findPage(filter);
    }


}
