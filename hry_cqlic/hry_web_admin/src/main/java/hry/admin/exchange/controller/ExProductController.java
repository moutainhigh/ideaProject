/**
 * Copyright:
 *
 * @author: liushilei
 * @version: V1.0
 * @Date: 2018-06-12 15:44:37
 */
package hry.admin.exchange.controller;


import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import hry.admin.exchange.DifCustomer;
import hry.admin.exchange.StartupEntrust;
import hry.admin.exchange.model.ExProduct;
import hry.admin.exchange.service.ExCointoCoinService;
import hry.admin.exchange.service.ExDigitalmoneyAccountService;
import hry.admin.exchange.service.ExProductService;
import hry.admin.web.model.AppConfig;
import hry.admin.web.service.AppConfigService;
import hry.bean.BaseManageUser;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.CommonLog;
import hry.core.annotation.MyRequiresPermissions;
import hry.core.annotation.base.MethodName;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.redis.common.utils.RedisService;
import hry.util.QueryFilter;
import hry.util.sys.ContextUtil;

/**
 * Copyright:   互融云
 * @author: liushilei
 * @version: V1.0
 * @Date: 2018-06-12 15:44:37
 */
@Controller
@RequestMapping("/exchange/exproduct")
public class ExProductController extends BaseController<ExProduct, Long> {

    @Resource(name = "exProductService")
    @Override
    public void setService(BaseService<ExProduct, Long> service) {
        super.service = service;
    }

    @Resource
    private ExCointoCoinService exCointoCoinService;

    @Resource
    private AppConfigService appConfigService;

    @Resource
    private ExDigitalmoneyAccountService exDigitalmoneyAccountService;

    @RequestMapping(value = "/see/{id}")
    public ModelAndView see(@PathVariable Long id) {
        ExProduct exProduct = service.get(id);
        ModelAndView mav = new ModelAndView("/admin/exchange/exproductsee");
        mav.addObject("model", exProduct);
        return mav;
    }


    @RequestMapping(value = "/add")
    @ResponseBody
    @CommonLog(name = "币种资料管理-添加")
    public JsonResult add(HttpServletRequest request, ExProduct exProduct) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setSuccess(false);

        String isERC20 = request.getParameter("isERC20");
        if(!StringUtils.isEmpty(isERC20)){
            exProduct.setIsERC20(isERC20);
            BaseManageUser user = ContextUtil.getCurrentUser();
            exProduct.setOperator(user.getUsername());
        }

        String coinCode = exProduct.getCoinCode();
        ExProductService exProductService = (ExProductService) service;
        boolean b = exProductService.findByCoinCode(coinCode);
        if (!b) {
            int i = null==exProduct.getIssueState() ? 0 : exProduct.getIssueState();
            if (i == 1) {
                exProduct.setIssueTime(new Date());
            }
            exProduct.setIssueState(0);
            // 设置发行方id
            exProduct.setIssueId(88l);

            jsonResult = super.save(exProduct);

            String key = "currecyType:" +exProduct.getCoinCode();
            String key2 = "paceCurrecy:" +exProduct.getCoinCode();
            String key3 = "retentionNumber:" +exProduct.getCoinCode();//币的保留位数
            RedisService redisService = (RedisService) ContextUtil.getBean("redisService");
            redisService.save(key, exProduct.getPaceType());
            redisService.save(key2, exProduct.getPaceCurrecy());
            redisService.save(key3, exProduct.getKeepDecimalForCoin()+"");
            // 更新缓存
            exProductService.updateProductToRedis(coinCode);

            exProductService.initToRedis();

            return jsonResult;
        }
        jsonResult.setMsg("该币种已经存在");
        return jsonResult;
    }

    @RequestMapping(value = "/modifysee/{id}")
    public ModelAndView modifysee(@PathVariable Long id) {
        ExProduct exProduct = service.get(id);
        ModelAndView mav = new ModelAndView("/admin/exchange/exproductmodify");
        mav.addObject("model", exProduct);
        return mav;
    }

    @RequestMapping(value = "/parammodifysee/{id}")
    public ModelAndView parammodifysee(@PathVariable Long id) {
        ExProduct exProduct = service.get(id);
        ModelAndView mav = new ModelAndView("/admin/exchange/exproductparamodify");
        mav.addObject("model", exProduct);
        return mav;
    }

    @RequestMapping(value = "/configmodifysee/{id}")
    public ModelAndView configmodifysee(@PathVariable Long id) {
        ExProduct exProduct = service.get(id);
        ModelAndView mav = new ModelAndView("/admin/exchange/exproductconfigmodify");
        mav.addObject("model", exProduct);
        return mav;
    }


    @MethodName(name = "修改数据")
    @RequestMapping("/modifytwo")
    @ResponseBody
    @CommonLog(name = "币种资料管理-修改")
    public JsonResult modifytwo(ExProduct exProduct) {

        ExProductService exProductService = (ExProductService) service;
        exProduct.setCoinCode(exProductService.get(exProduct.getId()).getCoinCode());

        ExProduct product = exProductService.findByCoinCode(exProduct.getCoinCode(), "");


        boolean b = exProductService.findByCoinCode(exProduct.getCoinCode());
        if(!b){
            JsonResult jResult = new JsonResult();
            jResult.setSuccess(false);
            jResult.setMsg("币的代码不能修改");
            return jResult;
        }
        JsonResult jsonResult = super.update(exProduct);
        String key = "currecyType:" +exProduct.getCoinCode();
        String key2 = "paceCurrecy:" +exProduct.getCoinCode();
        String key3 = "retentionNumber:" +exProduct.getCoinCode();//币的保留位数
        RedisService redisService = (RedisService) ContextUtil.getBean("redisService");
        redisService.save(key, exProduct.getPaceType());
        redisService.save(key2, exProduct.getPaceCurrecy());
        redisService.save(key3, exProduct.getKeepDecimalForCoin().toString());
		/*if(exProduct.getPaceType().equals("2")){
			redisService.save(key2, exProduct.getPaceCurrecy());

		}else{
			redisService.save(key2, exProduct.getPaceFeeRate().toString());

		}*/
        // 更新缓存
        exProductService.updateProductToRedis("");
        exCointoCoinService.initRedisCode();
        exProductService.initToRedis();

        //发布所有缓存
        StartupEntrust.Entrustinit();
        exProductService.updateProductToRedis("");

        if(jsonResult.getSuccess()){
            if(product != null && product.getTransactionType() != exProduct.getTransactionType()){
                DifCustomer.clearOnlyentrustNum(exProduct.getCoinCode());
            }
        }
        return jsonResult;
    }


    @RequestMapping(value = "/modify")
    @ResponseBody
    public JsonResult modify(HttpServletRequest request, ExProduct exProduct) {

        ExProductService exProductService = (ExProductService) service;

        exProduct.setCoinCode(exProductService.get(exProduct.getId()).getCoinCode());

        ExProduct product = exProductService.findByCoinCode(exProduct.getCoinCode()," ");

        boolean b = exProductService.findByCoinCode(exProduct.getCoinCode());
        if(!b){
            JsonResult jResult = new JsonResult();
            jResult.setSuccess(false);
            jResult.setMsg("币的代码不能修改");
            return jResult;
        }
        JsonResult jsonResult = super.update(exProduct);

        // 更新缓存
        exProductService.updateProductToRedis(exProduct.getCoinCode());
        exCointoCoinService.initRedisCode();
        exProductService.initToRedis();

        if(jsonResult.getSuccess()){
            if(product != null && product.getTransactionType() != exProduct.getTransactionType()){
                DifCustomer.clearOnlyentrustNum(exProduct.getCoinCode());
            }
        }
        return jsonResult;
    }


    @RequestMapping(value = "/remove")
    @ResponseBody
    public JsonResult remove(String ids) {
        return super.deleteBatch(ids);
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public PageResult list(HttpServletRequest request) {
        QueryFilter filter = new QueryFilter(ExProduct.class, request);
        String isisERC20 = request.getParameter("isERC20");
        String issueState = request.getParameter("issueState");
        if(!StringUtils.isEmpty(isisERC20)){
            filter.addFilter("isERC20=",isisERC20);
        }
        if (!StringUtils.isEmpty(issueState)) {
            filter.addFilter("issueState=", issueState);
        } else {
            filter.addFilter("issueState!=", "2");
        }

        return super.findPage(filter);
    }


    @RequestMapping(value = "/findall")
    @ResponseBody
    public List<ExProduct> findall(HttpServletRequest request) {
        QueryFilter qf = new QueryFilter(ExProduct.class);
        String issueState_neq = request.getParameter("issueState_neq");
        if (!StringUtils.isEmpty(issueState_neq)) {
            qf.addFilter("issueState_NEQ", issueState_neq);
        }
        return super.find(qf);
    }


    //查找发行状态的币
    @RequestMapping(value = "/findPublished")
    @ResponseBody
    public List<ExProduct> findPublished(HttpServletRequest request) {
        QueryFilter queryFilter = new QueryFilter(ExProduct.class);
        queryFilter.addFilter("issueState=","1");
        queryFilter.setOrderby("coinCode asc");
        return super.find(queryFilter);
    }



    @MethodName(name = "发布一个产品   并且同步给所有的用户")
    @RequestMapping(value = "/publishProduct/{ids}", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult publishProduct(@PathVariable Long[] ids) {

        QueryFilter filter = new QueryFilter(AppConfig.class);
        filter.addFilter("configkey=", "language_code");
        AppConfig appConfig = appConfigService.get(filter);


        ExProductService exProductService = (ExProductService)service;
        exProductService.initToRedis();

        JsonResult result = exProductService.publishProduct(ids,appConfig.getValue());
        //发布所有缓存
        StartupEntrust.Entrustinit();
        exProductService.updateProductToRedis("");
        exProductService.setCoinStatus(Long.valueOf(ids[0]),1);

        return result ;
    }

    @MethodName(name = "将一个产品下架")
    @RequestMapping(value = "/delistProduct/{ids}", method = RequestMethod.GET)
    @ResponseBody
    @MyRequiresPermissions
    public JsonResult delistProduct(@PathVariable Long ids) {
        JsonResult result = ((ExProductService)service).delishProduct(ids);
        return result ;
    }

    @MethodName(name = "还原已下架币种")
    @RequestMapping(value = "/reductionCoin/{ids}", method = RequestMethod.GET)
    @ResponseBody
    @MyRequiresPermissions
    public JsonResult reductionCoin(@PathVariable Long ids) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setSuccess(false);
        ExProduct exProduct = service.get(ids);
        if (exProduct != null) {
            if(exProduct.getIssueState().intValue() ==0){
                return jsonResult.setMsg("已经是上架状态了");
            }

            exProduct.setIssueState(0);
            exProduct.setModified(new Date());
            try {
                service.update(exProduct);
                jsonResult.setSuccess(true);
            } catch (Exception e) {
                e.printStackTrace();
                jsonResult.setSuccess(false);
            }
        }
        return jsonResult;
    }

    @RequestMapping(value = "/otcList")
    @ResponseBody
    public PageResult otcList(HttpServletRequest request) {
        QueryFilter filter = new QueryFilter(ExProduct.class, request);
        filter.addFilter("otcCoinCode=", 1);
        return super.findPage(filter);
    }

    @RequestMapping(value = "/otcAdd")
    @ResponseBody
    public JsonResult otcAdd(HttpServletRequest request, ExProduct exProduct) {
        QueryFilter filter = new QueryFilter(ExProduct.class, request);
        filter.addFilter("coinCode=", exProduct.getCoinCode());
        JsonResult jsonResult = super.get(filter);
        if(jsonResult.getSuccess()){
            ExProduct ex1 = (ExProduct)jsonResult.getObj();
            ex1.setEatFee(exProduct.getEatFee());
            ex1.setEatFeeType(exProduct.getEatFeeType());
            ex1.setCoinPercent(exProduct.getCoinPercent());
            ex1.setOtcState(exProduct.getOtcState());
            ex1.setOtcMinPercent(exProduct.getOtcMinPercent());
            ex1.setOtcMaxPercent(exProduct.getOtcMaxPercent());
            ex1.setOtcCoinCode(1);
            super.update(ex1);

            QueryFilter qd = new QueryFilter(ExProduct.class);
            qd.addFilter("otcCoinCode=", 1);
            qd.addFilter("otcState=", 1);
            List<ExProduct> list = super.find(qd);
            RedisService redisService = (RedisService) ContextUtil.getBean("redisService");
            redisService.save("otc:coinCodeList", JSON.toJSONString(list));

            return new JsonResult().setSuccess(true);
        }
        return new JsonResult().setSuccess(false);
    }

    @RequestMapping(value = "/otcModifySee/{id}")
    public ModelAndView otcModifySee(@PathVariable Long id) {
        ExProduct exProduct = service.get(id);
        ModelAndView mav = new ModelAndView("/admin/exchange/exproductotcmodify");
        mav.addObject("model", exProduct);
        return mav;
    }
}
