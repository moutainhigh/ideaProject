/**
 * Copyright:
 *
 * @author: tianpengyu
 * @version: V1.0
 * @Date: 2018-08-08 17:02:30
 */
package hry.admin.exchange.controller;


import hry.admin.dic.model.AppDic;
import hry.admin.dic.service.AppDicService;
import hry.admin.exchange.model.ExErc20;
import hry.bean.BaseManageUser;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.CommonLog;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.CoinInterfaceUtil;
import hry.util.QueryFilter;
import hry.util.sys.ContextUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * Copyright:   互融云
 * @author: tianpengyu
 * @version: V1.0
 * @Date: 2018-08-08 17:02:30
 */
@Controller
@RequestMapping("/exchange/exerc20")
public class ExErc20Controller extends BaseController<ExErc20, Long> {

    @Resource(name = "exErc20Service")
    @Override
    public void setService(BaseService<ExErc20, Long> service) {
        super.service = service;
    }

    @Resource
    private AppDicService appDicService;


    @RequestMapping(value = "/see/{id}")
    public ModelAndView see(@PathVariable Long id) {
        ExErc20 exErc20 = service.get(id);
        ModelAndView mav = new ModelAndView("/admin/exchange/exerc20see");
        mav.addObject("model", exErc20);
        return mav;
    }


    //@RequestMapping(value = "/add")
    @ResponseBody
    public JsonResult add(HttpServletRequest request, ExErc20 exErc20) {
        JsonResult jsonResult = new JsonResult();
        QueryFilter queryFilter = new QueryFilter(ExErc20.class);
        queryFilter.addFilter("contractAddress=", exErc20.getContractAddress());
        List<ExErc20> list = service.find(queryFilter);
        if (list.size() > 0) {
            return new JsonResult().setSuccess(false).setMsg("合约地址已经存在");
        }

        try {
            CoinInterfaceUtil coinInterfaceUtil = new CoinInterfaceUtil();
            JsonResult jsonResult1 = coinInterfaceUtil.getCoinDecimals(exErc20.getContractAddress());
            if (jsonResult1.getSuccess()) {
                List<String> list1 = (List<String>) jsonResult1.getObj();
                if (list1 != null && list1.size() == 2) {
               /*     if (!list1.get(0).equals(exErc20.getName())) {
                        jsonResult.setMsg("币代码和合约地址不匹配");
                        jsonResult.setSuccess(false);
                        return jsonResult;
                    }*/
                    QueryFilter filter = new QueryFilter(AppDic.class, request);
                    filter.addFilter("pkey=", "coinprecision");
                    filter.addFilter("value=", exErc20.getMyprecision());
                    List<AppDic> appDics = appDicService.find(filter);


                    if (!list1.get(1).equals(appDics.get(0).getName())) {
                        jsonResult.setMsg("币的精度不正确");
                        jsonResult.setSuccess(false);
                        return jsonResult;
                    }
                }


            } else {
                jsonResult.setMsg("合约地址不存在");
                jsonResult.setSuccess(false);
                return jsonResult;
            }

        } catch (IOException e) {
            e.printStackTrace();
            jsonResult.setMsg("合约地址不存在");
            jsonResult.setSuccess(false);
            return jsonResult;
        }

        BaseManageUser user = ContextUtil.getCurrentUser();
        exErc20.setOperator(user.getUsername());

        return super.save(exErc20);
    }

    //@RequestMapping(value = "/modifysee/{id}")
    public ModelAndView modifysee(@PathVariable Long id) {
        ExErc20 exErc20 = service.get(id);
        ModelAndView mav = new ModelAndView("/admin/exchange/exerc20modify");
        mav.addObject("model", exErc20);
        return mav;
    }

    //@RequestMapping(value = "/modify")
    @ResponseBody
    @CommonLog(name = "上架币种-编辑")
    public JsonResult modify(HttpServletRequest request, ExErc20 exErc20) {
        return super.update(exErc20);
    }


    //@RequestMapping(value = "/remove")
    @ResponseBody
    @CommonLog(name = "上架币种-删除")
    public JsonResult remove(String ids) {
        return super.deleteBatch(ids);
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public PageResult list(HttpServletRequest request) {
        QueryFilter filter = new QueryFilter(ExErc20.class, request);
        return super.findPage(filter);
    }


}
