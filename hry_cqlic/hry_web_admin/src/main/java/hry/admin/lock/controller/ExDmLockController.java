/**
 * Copyright:
 *
 * @author: liuchenghui
 * @version: V1.0
 * @Date: 2018-06-29 11:44:56
 */
package hry.admin.lock.controller;


import com.alibaba.fastjson.JSONObject;
import hry.admin.lock.model.ExDmLock;
import hry.admin.lock.service.ExDmLockService;
import hry.bean.BaseManageUser;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.CommonLog;
import hry.core.annotation.base.MethodName;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.redis.common.utils.RedisService;
import hry.util.QueryFilter;
import hry.util.sys.ContextUtil;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Copyright:   互融云
 * @author: liuchenghui
 * @version: V1.0
 * @Date: 2018-06-29 11:44:56
 */
@Controller
@RequestMapping("/lock/exdmlock")
public class ExDmLockController extends BaseController<ExDmLock, Long> {

    @Resource(name = "exDmLockService")
    @Override
    public void setService (BaseService<ExDmLock, Long> service) {
        super.service = service;
    }

    @Resource
    private ExDmLockService exDmLockService;

    @Resource
    private RedisService redisService;

    @RequestMapping(value = "/see/{id}")
    public ModelAndView see (@PathVariable Long id) {
        ExDmLock exDmLock = service.get(id);
        ModelAndView mav = new ModelAndView("/admin/lock/exdmlocksee");
        mav.addObject("model", exDmLock);
        return mav;
    }

    @RequestMapping("/toAdd")
    public ModelAndView toAdd (HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/admin/lock/exdmlockadd");
        // 查询平台币
        String config = redisService.get("configCache:all");
        if (!StringUtils.isEmpty(config)) {
            JSONObject parseObject = JSONObject.parseObject(config);
            String platCoin = parseObject.getString("platCoin"); // 获取平台币
            mav.addObject("platCoin", platCoin);
        }
        // 查询定时器运行频率
        String miningTimer = redisService.get("Mining:Timer2");
        if (!StringUtils.isEmpty(miningTimer)) {
            JSONObject object = JSONObject.parseObject(miningTimer);
            // 获取执行频率
            String lockfrequency = object.getString("lockfrequency");
            mav.addObject("lockfrequency", lockfrequency);
        }
        return mav;
    }

    @RequestMapping(value = "/modifysee/{id}")
    public ModelAndView modifysee (@PathVariable Long id) {
        ExDmLock exDmLock = service.get(id);
        ModelAndView mav = new ModelAndView("/admin/lock/exdmlockmodify");
        mav.addObject("model", exDmLock);
        // 查询平台币
        String config = redisService.get("configCache:all");
        if (!StringUtils.isEmpty(config)) {
            JSONObject parseObject = JSONObject.parseObject(config);
            String platCoin = parseObject.getString("platCoin"); // 获取平台币
            mav.addObject("platCoin", platCoin);
        }
        return mav;
    }

    @MethodName(name = "添加锁仓记录")
    @RequestMapping(value = "/add")
    @ResponseBody
    @CommonLog(name = "添加锁仓")
    public JsonResult add (HttpServletRequest request, ExDmLock exDmLock) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setSuccess(false);
        BaseManageUser currentUser = ContextUtil.getCurrentUser();
        exDmLock.setOptUser("admin");
        if (currentUser != null) {
            exDmLock.setOptUser(currentUser.getUsername());
        }
        String coinCode = exDmLock.getCoinCode();
        boolean b = ((ExDmLockService) service).isExistCoinCode(coinCode);
        // 判断币种是否存在
        if (!b) {
            return super.save(exDmLock);
        }
        jsonResult.setMsg("币种已存在");
        return jsonResult;
    }


    @MethodName(name = "修改锁仓记录")
    @RequestMapping(value = "/modify")
    @ResponseBody
    @CommonLog(name = "修改锁仓")
    public JsonResult modify (HttpServletRequest request, ExDmLock exDmLock) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setSuccess(false);
        jsonResult = super.update(exDmLock);
        if (jsonResult.getSuccess()) {
            return jsonResult;
        }
        jsonResult.setMsg("修改失败");
        return jsonResult;
    }


    @RequestMapping(value = "/remove")
    @ResponseBody
    public JsonResult remove (String ids) {
        return super.deleteBatch(ids);
    }

    @MethodName(name = "查询锁仓列表")
    @RequestMapping(value = "/list")
    @ResponseBody
    public PageResult list (HttpServletRequest request) {
        QueryFilter filter = new QueryFilter(ExDmLock.class, request);
        PageResult pageResult = ((ExDmLockService) service).findPageBySql(filter);
        return pageResult;
    }

    @MethodName(name = "开启锁仓记录")
    @RequestMapping(value = "/open/{ids}")
    @ResponseBody
    @CommonLog(name = "开启锁仓")
    public JsonResult open (@PathVariable Long ids) {
        ExDmLock exDmLock = service.get(ids);
        exDmLock.setIsLock(1);
        service.update(exDmLock);

        JsonResult jsonResult = new JsonResult();
        jsonResult.setSuccess(true);
        return jsonResult;
    }

    @CommonLog(name = "关闭锁仓")
    @MethodName(name = "关闭锁仓记录")
    @RequestMapping(value = "/close/{ids}")
    @ResponseBody
    public JsonResult close (@PathVariable Long ids) {
        ExDmLock exDmLock = service.get(ids);
        exDmLock.setIsLock(0);
        service.update(exDmLock);

        JsonResult jsonResult = new JsonResult();
        jsonResult.setSuccess(true);
        return jsonResult;
    }

}
