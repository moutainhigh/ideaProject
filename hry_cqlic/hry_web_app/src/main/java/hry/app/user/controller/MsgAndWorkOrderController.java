package hry.app.user.controller;

import hry.app.jwt.TokenUtil;
import hry.bean.ApiJsonResult;
import hry.bean.JsonResult;
import hry.core.annotation.CommonLog;
import hry.core.mvc.model.page.HttpServletRequestUtils;
import hry.manage.remote.RemoteOamessageService;
import hry.manage.remote.RemoteWorkOrderService;
import hry.manage.remote.model.AppWorkOrder;
import hry.manage.remote.model.AppWorkOrderCategory;
import hry.manage.remote.model.Oamessage;
import hry.manage.remote.model.User;
import hry.manage.remote.model.base.FrontPage;
import hry.util.common.SpringUtil;
import hry.util.springmvcPropertyeditor.DateTimePropertyEditorSupport;
import hry.util.springmvcPropertyeditor.StringPropertyEditorSupport;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 个人中心-消息工单
 *
 * @author lch
 * <p>
 * 2017年7月19日
 */
@Controller
@RequestMapping("/msgAndWorkOrder")
@Api(value = "个人中心-消息工单", description = "个人中心-消息工单", tags = "个人中心-消息工单")
public class MsgAndWorkOrderController {

    /**
     * 注册类型属性编辑器
     *
     * @param binder
     */
    @InitBinder
    public void initBinder (ServletRequestDataBinder binder) {

        // 系统注入的只能是基本类型，如int，char，String

        /**
         * 自动转换日期类型的字段格式
         */
        binder.registerCustomEditor(Date.class, new DateTimePropertyEditorSupport());

        /**
         * 防止XSS攻击，并且带去左右空格功能
         */
        binder.registerCustomEditor(String.class, new StringPropertyEditorSupport(true, false));
    }

    @Resource
    private RemoteOamessageService remoteOamessageService;

    @Resource
    private RemoteWorkOrderService remoteWorkOrderService;

    /**
     * 个人中心-我的消息-获取用户消息分页列表
     *
     * @param request
     * @return 2017年7月19日
     */
    @ApiOperation(value = "个人中心-我的消息-获取用户消息分页列表", httpMethod = "POST", response = ApiJsonResult.class, notes = "个人中心-我的消息-获取用户消息分页列表")
    @PostMapping("/user/userMessagelist")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    @RequiresAuthentication
    public FrontPage userMessagelist (
            @ApiParam(name = "limit", value = "每页条数", required = true) @RequestParam("limit") String limit,
            @ApiParam(name = "offset", value = "(页码-1)*每页条数,如第1页，每次10条侧传(1-1)*10=0", required = true) @RequestParam("offset") String offset,
            @ApiParam(name = "langCode", value = "默认语种", required = false) @RequestParam(value = "langCode",required = false) String langCode,
            HttpServletRequest request) {
        User user = TokenUtil.getUser(request);
        if (user != null) {
            Map<String, String> params = HttpServletRequestUtils.getParams(request);
            params.put("customerName", user.getUsername());
            if(langCode!=null){
                params.put("langCode", langCode);
            }
            return remoteOamessageService.findOamessage(params);
        }
        return null;
    }

    /**
     * 个人中心-我的消息-获取用户消息详情
     * 并且将消息设置为已读
     *
     * @param request
     * @return 2017年7月21日
     */
    @ApiOperation(value = "个人中心-我的消息-获取用户消息详情", httpMethod = "POST", response = Map.class, notes = "个人中心-我的消息-获取用户消息详情")
    @RequestMapping("/user/getMessageDetail")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    @RequiresAuthentication
    public Map<String, Object> getMessageDetail (
            @ApiParam(name = "sid", value = "消息id", required = true) @RequestParam("sid") Long sid,
            HttpServletRequest request) {
        Map<String, Object> returnMap = new HashMap<>();
        User user = TokenUtil.getUser(request);
        if (user != null) {//如果登录了
            if (sid != null) {
                //获取远程调用service
                Oamessage oamessage = remoteOamessageService.get(sid);
                //是否本人操作
                if (oamessage.getCustomerId().longValue() == user.getCustomerId().longValue()) {
                    Oamessage read = remoteOamessageService.read(sid);
                    returnMap.put("content", read.getContent());
                } else {
                    returnMap.put("content", "xitongcuowuqingchongshi");//系统错误请重试
                }
            }
        }
        return returnMap;
    }


    /**
     * 个人中心-我的消息-批量处理消息
     * @param request
     */
    @ApiOperation(value = "个人中心-我的消息-批量处理消息", httpMethod = "POST", response = Map.class, notes = "个人中心-我的消息-批量处理消息")
    @RequestMapping("/user/updateMessage")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    @RequiresAuthentication
    public JsonResult updateMessage (
            @ApiParam(name = "ids", value = "消息id集合串 用英文逗号拼接", required = true) @RequestParam("ids") String ids,
            @ApiParam(name = "type", value = "处理类型 1 已读 2 删除", required = true) @RequestParam("type") String type,
            HttpServletRequest request) {
        User user = TokenUtil.getUser(request);
        if (user != null) {
            return remoteOamessageService.update(user.getCustomerId(),type,ids);
        } else {
            return new JsonResult().setSuccess(false).setMsg(SpringUtil.diff("before_login"));
        }
    }


    /**
     * 个人中心-我的消息-统计个人未读消息数量
     * @param request
     */
    @ApiOperation(value = "个人中心-我的消息-统计个人未读消息数量", httpMethod = "POST", response = Map.class, notes = "个人中心-我的消息-统计个人未读消息数量")
    @RequestMapping("/user/unreadMessageCount")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    @RequiresAuthentication
    public JsonResult unreadMessageCount (HttpServletRequest request) {
        User user = TokenUtil.getUser(request);
        if (user != null) {
            return remoteOamessageService.count(user.getCustomerId());
        } else {
            return new JsonResult().setSuccess(false).setMsg(SpringUtil.diff("before_login"));
        }
    }

    /**
     * 个人中心-我的工单-查询工单记录
     *
     * @return
     */
    @ApiOperation(value = "个人中心-我的工单-查询工单记录", httpMethod = "POST", response = FrontPage.class, notes = "个人中心-我的工单-查询工单记录")
    @RequestMapping("/user/myWorkOrderList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    @RequiresAuthentication
    public FrontPage myWorkOrderList (
            @ApiParam(name = "limit", value = "每页条数", required = true) @RequestParam("limit") String limit,
            @ApiParam(name = "offset", value = "(页码-1)*每页条数,如第1页，每次10条侧传(1-1)*10=0", required = true) @RequestParam("offset") String offset,
            HttpServletRequest request) {
        User user = TokenUtil.getUser(request);
        if (user != null) {
            Map<String, String> params = HttpServletRequestUtils.getParams(request);
            params.put("customerId", user.getCustomerId().toString());
            return remoteWorkOrderService.findWorkOrder(params);
        }
        return null;
    }

    /**
     * 个人中心-我的工单-删除工单
     *
     * @param ids
     * @param request
     * @return
     * @throws Exception
     */
    @CommonLog(name = "删除工单")
    @ApiOperation(value = "个人中心-我的工单-删除工单", httpMethod = "POST", response = JsonResult.class, notes = "个人中心-我的工单-删除工单")
    @RequestMapping("/user/removeWorkOrder")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    @RequiresAuthentication
    public JsonResult removeWorkOrder (
            @ApiParam(name = "ids", value = "工单id，多个选择时用逗号隔开", required = true) @RequestParam("ids") String ids,
            HttpServletRequest request) throws Exception {
        User user = TokenUtil.getUser(request);
        if (!StringUtils.isEmpty(ids)) {
            String[] idarray = ids.split(",");
            Map<String, String> params = HttpServletRequestUtils.getParams(request);
            params.put("customerId", user.getCustomerId().toString());
            FrontPage findWorkOrder = remoteWorkOrderService.findWorkOrder(params);
            List<HashMap> list = findWorkOrder.getRows();
            boolean flag = true;
            if (list != null && list.size() > 0) {
                for (String id : idarray) {//判断要删除的id是否是这个用户下的
                    boolean flag1 = true;
                    for (HashMap appWorkOrder : list) {
                        if (id.equals(appWorkOrder.get("id").toString())) {
                            flag1 = false;
                            break;
                        }
                    }
                    if (flag1) {//只要有一个id不不是该用户的，就结束循环
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    JsonResult jsonResult = remoteWorkOrderService.deleteBatch(ids);
                    return jsonResult;
                } else {
                    return new JsonResult().setSuccess(false).setMsg("越权");
                }
            }
            return new JsonResult().setSuccess(false).setMsg("失败");
        }
        return new JsonResult().setSuccess(false).setMsg("失败");
    }

    /**
     * 个人中心-我的工单添加-工单类型
     *
     * @param request
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "个人中心-我的工单添加-工单类型列表", httpMethod = "POST", notes = "个人中心-我的工单添加-工单类型列表")
    @PostMapping("/user/findWorkOrderCategoryList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    @RequiresAuthentication
    public ApiJsonResult<List<AppWorkOrderCategory>> findWorkOrderCategoryList (
            @ApiParam(name = "langCode", value = "语种", required = true) @RequestParam("langCode") String langCode,
            HttpServletRequest request) throws Exception {
        ApiJsonResult<List<AppWorkOrderCategory>> jsonResult = new ApiJsonResult<List<AppWorkOrderCategory>>();
        User user = TokenUtil.getUser(request);
        if (user != null) {
            Map<String, String> params = HttpServletRequestUtils.getParams(request);
            params.put("languageDic", langCode);
            List<AppWorkOrderCategory> list = remoteWorkOrderService.findWorkOrderCategoryList(params);
            jsonResult.setSuccess(true);
            jsonResult.setObj(list);
            return jsonResult;
        }
        jsonResult.setSuccess(false);
        jsonResult.setMsg(SpringUtil.diff("before_login"));
        return jsonResult;
    }

    /**
     * 个人中心-我的工单添加-添加提交
     *
     * @param request
     * @return
     * @throws Exception
     */
    @CommonLog(name = "添加工单")
    @ApiOperation(value = "个人中心-我的工单添加-添加提交", httpMethod = "POST", response = JsonResult.class, notes = "个人中心-我的工单添加-添加提交")
    @RequestMapping("/user/addWorkOrder")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    @RequiresAuthentication
    public JsonResult addWorkOrder (
            @ApiParam(name = "workContent", value = "工单内容", required = true) @RequestParam("workContent") String workContent,
            @ApiParam(name = "categoryId", value = "工单类型id", required = true) @RequestParam("categoryId") Long categoryId,
            @ApiParam(name = "langCode", value = "语种", required = true) @RequestParam("langCode") String langCode,
            HttpServletRequest request) throws Exception {
        User user = TokenUtil.getUser(request);
        if (user != null) {
            AppWorkOrder appWorkOrder = new AppWorkOrder();
            appWorkOrder.setWorkContent(workContent);
            appWorkOrder.setCategoryId(categoryId);
            appWorkOrder.setCustomerId(user.getCustomerId());
            appWorkOrder.setLanguageDic(langCode);
            JsonResult jsonResult = remoteWorkOrderService.add(appWorkOrder);
            if (jsonResult.getSuccess()) {
                jsonResult.setMsg(SpringUtil.diff("send_success"));
            } else {
                jsonResult.setMsg(SpringUtil.diff("send_fail"));
            }
            return jsonResult;
        } else {
            return new JsonResult().setSuccess(false).setMsg(SpringUtil.diff("before_login"));
        }
    }

    @ApiOperation(value = "个人中心-我的工单-查看工单详情", httpMethod = "POST", response = JsonResult.class, notes = "个人中心-我的工单-查看工单详情")
    @RequestMapping("/user/getWorkOrderDetil")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    @RequiresAuthentication
    public Map<String, Object> getWorkOrderDetil(
            @ApiParam(name = "orderId", value = "工单id", required = true) @RequestParam("orderId") String orderId,
            HttpServletRequest request) {
        Map<String, Object> returnMap = new HashMap<>();
        User user = TokenUtil.getUser(request);
        if (user != null) {//如果登录了
            if (!StringUtils.isEmpty(orderId)) {
                //获取远程调用service
                AppWorkOrder appWorkOrder = remoteWorkOrderService.get(new Long(orderId));
                //是否本人操作
                if (appWorkOrder.getCustomerId().longValue() == user.getCustomerId().longValue()) {
                    returnMap.put("appWorkOrder", appWorkOrder);
                } else {
                    returnMap.put("appWorkOrder", new AppWorkOrder());
                }
            }
        }
        return returnMap;
    }

}
