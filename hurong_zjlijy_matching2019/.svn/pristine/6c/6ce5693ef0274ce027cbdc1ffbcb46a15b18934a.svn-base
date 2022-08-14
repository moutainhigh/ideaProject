package hry.app.test.controller;


import hry.app.test.model.TestAddUser;
import hry.app.test.model.TestUser;
import hry.bean.ApiJsonResult;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@Api(value= "7.0接口开发规范", description ="7.0接口开发规范",tags = "7.0接口开发规范")
@RequestMapping("/test")
public class TestController {


    /**
     * 需要授权访问的方法要求2点
     *      1、路径中包含 /user/
     *      2、方法增加注解@RequiresAuthentication
     * @param aa
     * @param bb
     * @return
     */
    @ApiOperation( httpMethod = "POST", value = "授权系列-->需要登录的方法",notes = "需要授权访问的方法要求两点，其一、路径中包含 /user/。其二、方法增加注解@RequiresAuthentication")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "令牌",required = true,dataType = "String",paramType = "header")
    })
    @PostMapping("/user/haslogin")
    @RequiresAuthentication
    @ResponseBody
    public ApiJsonResult haslogin(
            @ApiParam(name = "aa", value = "aa参数", required = true) @RequestParam String aa,
            @ApiParam(name = "bb", value = "bb参数", required = true) @RequestParam String bb) {
        ApiJsonResult jsonResult = new ApiJsonResult();
        return jsonResult;
    }

    /**
     * 不需要登录的方法
     * @param aa
     * @param bb
     * @return
     */
    @ApiOperation( httpMethod = "POST", value = "授权系列-->不需要登录的方法",notes = "不需要登录的方法")
    @PostMapping("/nologin")
    @ResponseBody
    public ApiJsonResult nologin(
            @ApiParam(name = "aa", value = "aa参数", required = true) @RequestParam String aa,
            @ApiParam(name = "bb", value = "bb参数", required = true) @RequestParam String bb) {
        ApiJsonResult jsonResult = new ApiJsonResult();
        return jsonResult;
    }

    /**
     * 返回ApiJsonResult带独立对象方法
     * @param id
     * @return
     */
    @ApiOperation( httpMethod = "POST", value = "返回参数系列-->ApiJsonResult带独立对象",notes = "为演示，都不进行授权,全是POST请求，具体根据业务情况")
    @PostMapping("/getTestUser")
    @ResponseBody
    public ApiJsonResult<TestUser> getTestUser(
            @ApiParam(name = "id", value = "id", required = true) @RequestParam String id) {
        ApiJsonResult<TestUser> jsonResult = new ApiJsonResult();
        TestUser testUser = new TestUser();
        testUser.setId(1L);
        testUser.setName("小明");
        testUser.setHomeAddress("北京朝阳区紫玉山庄");
        jsonResult.setObj(testUser);
        jsonResult.setSuccess(true);
        return jsonResult;
    }

    /**
     * 返回ApiJsonResult带list对象
     * @param id
     * @return
     */
    @ApiOperation( httpMethod = "POST", value = "返回参数系列-->ApiJsonResult带list对象",notes = "为演示，都不进行授权,全是POST请求，具体根据业务情况")
    @PostMapping("/findTestUser")
    @ResponseBody
    public ApiJsonResult<List<TestUser>> findTestUser(
            @ApiParam(name = "type", value = "类型", required = true) @RequestParam String type) {
        ApiJsonResult<List<TestUser>> jsonResult = new ApiJsonResult();

        List<TestUser> list = new ArrayList<>();

        TestUser testUser = new TestUser();
        testUser.setId(1L);
        testUser.setName("小明");
        testUser.setHomeAddress("北京朝阳区紫玉山庄");

        TestUser testUser2 = new TestUser();
        testUser2.setId(2L);
        testUser2.setName("小花");
        testUser2.setHomeAddress("北京朝阳区紫玉山庄");

        list.add(testUser);
        list.add(testUser2);

        jsonResult.setObj(list);
        jsonResult.setSuccess(true);
        return jsonResult;
    }


    /**
     * 直接返回对象
     * @param id
     * @return
     */
    @ApiOperation( httpMethod = "POST", value = "返回参数系列-->直接返回对象",notes = "为演示，都不进行授权,全是POST请求，具体根据业务情况")
    @PostMapping("/getUser")
    @ResponseBody
    public TestUser getUser(
            @ApiParam(name = "id", value = "id", required = true) @RequestParam String id) {

        TestUser testUser = new TestUser();
        testUser.setId(1L);
        testUser.setName("小明");
        testUser.setHomeAddress("北京朝阳区紫玉山庄");

        return testUser;
    }

    /**
     * 直接返回list泛型对象
     * @param id
     * @return
     */
    @ApiOperation( httpMethod = "POST", value = "返回参数系列-->直接返回list泛型对象",notes = "为演示，都不进行授权,全是POST请求，具体根据业务情况")
    @PostMapping("/findUser")
    @ResponseBody
    public List<TestUser> findUser(
            @ApiParam(name = "type", value = "类型", required = true) @RequestParam String type) {

        List<TestUser> list = new ArrayList<>();

        TestUser testUser = new TestUser();
        testUser.setId(1L);
        testUser.setName("小明");
        testUser.setHomeAddress("北京朝阳区紫玉山庄");

        TestUser testUser2 = new TestUser();
        testUser2.setId(2L);
        testUser2.setName("小花");
        testUser2.setHomeAddress("北京朝阳区紫玉山庄");

        list.add(testUser);
        list.add(testUser2);

        return list;
    }

    /**
     * formData请求参数示例
     * @param id
     * @return
     */
    @ApiOperation( httpMethod = "POST", value = "请求参数系列-->formData请求参数示例",notes = "为演示，都不进行授权,全是POST请求，具体根据业务情况")
    @PostMapping("/addUser")
    @ResponseBody
    public ApiJsonResult addUser(
            @ApiParam(name = "userName", value = "用户名", required = true) @RequestParam String userName ,
            @ApiParam(name = "passWord", value = "密码", required = true) @RequestParam String passWord,
            @ApiParam(name = "referralCode", value = "邀请码", required = false) @RequestParam String referralCode
            ) {

        //service.add(obj)

        return new ApiJsonResult().setSuccess(true);
    }


    /**
     * requestBody请求参数示例
     * @param id
     * @return
     */
    @ApiOperation( httpMethod = "POST", value = "请求参数系列-->requestBody请求参数示例",notes = "为演示，都不进行授权,全是POST请求，具体根据业务情况")
    @PostMapping("/addUser4boy")
    @ResponseBody
    public ApiJsonResult addUser4boy(@RequestBody TestAddUser testAddUser) {

        //service.add(obj)

        return new ApiJsonResult().setSuccess(true);
    }




}
