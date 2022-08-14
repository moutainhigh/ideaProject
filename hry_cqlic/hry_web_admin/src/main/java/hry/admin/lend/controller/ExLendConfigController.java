/**
 * Copyright:
 *
 * @author: HeC
 * @version: V1.0
 * @Date: 2018-10-18 14:47:26
 */
package hry.admin.lend.controller;


import hry.admin.lend.dao.ExLendConfigDao;
import hry.admin.lend.model.ExLendConfig;
import hry.admin.lend.service.ExLendConfigService;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.lend.constant.DealConstant;
import hry.lend.constant.LendConfig;
import hry.lend.constant.LendConstant;
import hry.lend.remote.RemoteLendConfigService;
import hry.reward.model.page.FrontPage;
import hry.util.QueryFilter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.object.MappingSqlQueryWithParameters;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Copyright:   互融云
 * @author: HeC
 * @version: V1.0
 * @Date: 2018-10-18 14:47:26
 */
@Controller
@RequestMapping("/lend/exlendconfig")
public class ExLendConfigController extends BaseController<ExLendConfig, Long> {

    @Resource(name = "exLendConfigService")
    @Override
    public void setService(BaseService<ExLendConfig, Long> service) {
        super.service = service;
    }

    @Resource
    private RemoteLendConfigService remoteLendConfigService;

    @RequestMapping(value = "/see/{id}")
    public ModelAndView see(@PathVariable Long id) {
        ExLendConfig exLendConfig = service.get(id);
        ModelAndView mav = new ModelAndView("/admin/lend/exlendconfigsee");
        mav.addObject("model", exLendConfig);
        return mav;
    }

    /**
     * 新加杠杆交易对
     * 配置和最新价入缓存
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public JsonResult add(HttpServletRequest request, ExLendConfig exLendConfig) {
        JsonResult result = super.save(exLendConfig);
        try{
            if(result.getSuccess()){
                remoteLendConfigService.cacheConfig(exLendConfig.getCoinCode());
            }
        }catch(Exception e){
            e.printStackTrace();
            super.delete(exLendConfig.getId());
        }
        return result;
    }

    @RequestMapping(value = "/modifysee/{id}")
    public ModelAndView modifysee(@PathVariable Long id) {
        ExLendConfig exLendConfig = service.get(id);
        ModelAndView mav = new ModelAndView("/admin/lend/exlendconfigmodify");
        mav.addObject("model", exLendConfig);
        return mav;
    }

    @RequestMapping(value = "/modify")
    @ResponseBody
    public JsonResult modify(HttpServletRequest request, ExLendConfig exLendConfig) {
        JsonResult result = super.update(exLendConfig);
        if(result.getSuccess()){
            remoteLendConfigService.cacheConfig(exLendConfig.getCoinCode());
        }
        return result;
    }


    /**
     * 入回收站
     */
    @RequestMapping(value = "/remove")
    @ResponseBody
    public JsonResult remove(String ids) {
        String[] split = ids.split(",");
        for (String id : split) {
            ExLendConfig config = service.get(Long.valueOf(id));
            config.setStatus(2);
            JsonResult update = super.update(config);
            if(update.getSuccess()){
                remoteLendConfigService.cacheConfig(config.getCoinCode());
            }
        }
        return new JsonResult(true);
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public FrontPage list(HttpServletRequest request) {
        QueryFilter filter = new QueryFilter(ExLendConfig.class, request);
        return ((ExLendConfigService)service).findList(filter);
    }

    /**
     * 回收站
     */
    @RequestMapping(value = "/rubbishList")
    @ResponseBody
    public PageResult rubbishList(HttpServletRequest request) {
        QueryFilter filter = new QueryFilter(ExLendConfig.class, request);
        filter.addFilter("status=", DealConstant.RUBBISH);
        return super.findPage(filter);
    }

    /**
     * 移出回收站
     */
    @RequestMapping(value = "/outRubbish")
    @ResponseBody
    public JsonResult outRubbish(String ids) {
        String[] split = ids.split(",");
        for (String id : split) {
            JsonResult result = super.get(Long.valueOf(id));
            ExLendConfig exLendConfig = (ExLendConfig)result.getObj();
            exLendConfig.setStatus(DealConstant.CLOSE);
            JsonResult update = super.update(exLendConfig);
            if(update.getSuccess()){
                remoteLendConfigService.cacheConfig(exLendConfig.getCoinCode());
            }
        }
        return new JsonResult(true);
    }

    /**
     * 获得交易区
     */
    @RequestMapping(value = "/getPriCoin")
    @ResponseBody
    public List<Map<String,String>> getPriCoin(){
        return remoteLendConfigService.getCoins(DealConstant.FIXPRICE_COINCODE);
    }

    /**
     * 获得交易币
     */
    @RequestMapping(value = "/getTradeCoin")
    @ResponseBody
    public List<Map<String,String>> getTradeCoin(){
        return remoteLendConfigService.getCoins(DealConstant.TRANSACTION_COINCODE);
    }

    /**
     * 获得所有交易对
     */
    @RequestMapping(value = "/getCoinCodes")
    @ResponseBody
    public List<Map<String,String>> getCoinCodes(){
        return ((ExLendConfigService)service).getCoinCodes();
    }

}
