package hry.app.user.controller;

import hry.app.jwt.TokenUtil;
import hry.bean.ApiJsonResult;
import hry.manage.remote.RemoteChatService;
import hry.manage.remote.model.RemoteResult;
import hry.manage.remote.model.User;
import hry.redis.common.utils.RedisService;
import hry.util.common.SpringUtil;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 聊天
 * ChatController.java
 *
 * @author denghf
 * 2018年1月29日 下午2:43:14
 */
@Controller
@RequestMapping("/chat")
@Api(value = "聊天室", description = "聊天室", tags = "聊天室")
public class ChatController {

    @Resource
    private RedisService redisService;

    @Resource
    private RemoteChatService remoteChatService;

    /**
     * 初始化聊天室数据
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "初始化聊天室数据", httpMethod = "POST", notes = "初始化聊天室数据")
    @PostMapping("/user/initChatData")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    @RequiresAuthentication
    public ApiJsonResult<User> initChatData (HttpServletRequest request) {
        ApiJsonResult jsonResult = new ApiJsonResult();
        User user = TokenUtil.getUser(request);
        if (user == null) {
            jsonResult.setSuccess(false);
            jsonResult.setMsg(SpringUtil.diff("before_login"));
            return jsonResult;
        } else {
            String isAdmin = redisService.get("chat:admin:" + user.getUsername());
            if (!StringUtils.isEmpty(isAdmin)) {
                user.setIsAdmin(Integer.valueOf(isAdmin));
                remoteChatService.isAdmin(user.getCustomerId(), isAdmin);
                TokenUtil.updateUser(user);
            }
            jsonResult.setObj(user);
            jsonResult.setSuccess(true);
            return jsonResult;
        }
    }

    /**
     * 获取近三天的聊天记录
     *
     * @return
     */
    @ApiOperation(value = "获取近三天的聊天记录", httpMethod = "POST", notes = "获取近三天的聊天记录")
    @PostMapping("/user/get3daychat")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    @RequiresAuthentication
    public ApiJsonResult get3daychat (HttpServletRequest request) {
        ApiJsonResult jsonResult = new ApiJsonResult();
        User user = TokenUtil.getUser(request);
        if (user != null) {
            List<Map<String, Object>> list = remoteChatService.get3daychat(user.getCustomerId());
            jsonResult.setSuccess(true);
            jsonResult.setObj(list);
            return jsonResult;
        }
        jsonResult.setSuccess(false);
        jsonResult.setMsg(SpringUtil.diff("before_login"));
        return jsonResult;
    }

    /**
     * 查询禁言时间
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "查询禁言时间", httpMethod = "POST", notes = "查询禁言时间")
    @PostMapping("/user/findTimeOfBan")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    @RequiresAuthentication
    public ApiJsonResult findTimeOfBan (HttpServletRequest request) {
        ApiJsonResult jsonResult = new ApiJsonResult();
        User user = TokenUtil.getUser(request);
        if (user != null) {
            Map<String, Object> map = new HashMap<String, Object>();
            Long keyTime = redisService.getKeyTime("chat:gag:" + user.getUsername());
            if (keyTime != null) {
                map.put("keyTime", keyTime);
            }
            map.put("user", user);
            jsonResult.setSuccess(true);
            jsonResult.setObj(map);
            return jsonResult;
        }
        jsonResult.setSuccess(false);
        jsonResult.setMsg(SpringUtil.diff("before_login"));
        return jsonResult;
    }

    /**
     * 禁言时间到，解除禁言
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "禁言时间到，解除禁言", httpMethod = "POST", notes = "禁言时间到，解除禁言")
    @RequestMapping("/user/releaseTimeOfBan")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    @RequiresAuthentication
    public ApiJsonResult releaseTimeOfBan (HttpServletRequest request) {
        ApiJsonResult jsonResult = new ApiJsonResult();
        User user = TokenUtil.getUser(request);
        if (user != null) {
            remoteChatService.releaseGag(user.getCustomerId());
            jsonResult.setSuccess(true);
            return jsonResult;
        }
        jsonResult.setSuccess(false);
        jsonResult.setMsg(SpringUtil.diff("before_login"));
        return jsonResult;
    }

    /**
     * 查询敏感词
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "查询敏感词", httpMethod = "POST", notes = "查询敏感词")
    @RequestMapping("/user/sensitiveWords")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    @RequiresAuthentication
    public ApiJsonResult sensitiveWords (HttpServletRequest request) {
        ApiJsonResult jsonResult = new ApiJsonResult();
        RemoteResult sensitiveWords = remoteChatService.sensitiveWords();
        if (sensitiveWords.getSuccess()) {
            jsonResult.setSuccess(true);
            jsonResult.setObj(sensitiveWords.getObj());
            return jsonResult;
        }
        jsonResult.setSuccess(false);
        return jsonResult;
    }

    /**
     * 保存聊天记录
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "保存聊天记录", httpMethod = "POST", notes = "保存聊天记录")
    @PostMapping("/user/saveChatRecord")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    @RequiresAuthentication
    public ApiJsonResult saveChatRecord (
            @ApiParam(name = "content", value = "聊天内容", required = true) @RequestParam("content") String content,
            HttpServletRequest request) {
        ApiJsonResult jsonResult = new ApiJsonResult();
        User user = TokenUtil.getUser(request);
        if (user != null) {
            String userName = "";
            if (null != user.getEmail() && !"".equals(user.getEmail())) {
                userName = user.getEmail();
            } else {
                userName = user.getMobile();
            }
            remoteChatService.saveChatRecord(user.getCustomerId(), userName, content);
            jsonResult.setSuccess(true);
            return jsonResult;
        }
        jsonResult.setSuccess(false);
        jsonResult.setMsg(SpringUtil.diff("before_login"));
        return jsonResult;
    }
}
