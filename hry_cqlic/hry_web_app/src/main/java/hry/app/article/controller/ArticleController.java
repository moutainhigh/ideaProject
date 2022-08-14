package hry.app.article.controller;

import hry.bean.ApiJsonResult;
import hry.core.mvc.model.page.HttpServletRequestUtils;
import hry.manage.remote.RemoteAppArticleService;
import hry.manage.remote.model.Article;
import hry.manage.remote.model.FriendLink;
import hry.manage.remote.model.base.FrontPage;
import hry.util.common.SpringUtil;
import hry.util.springmvcPropertyeditor.DateTimePropertyEditorSupport;
import hry.util.springmvcPropertyeditor.StringPropertyEditorSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/article")
@Api(value = "咨询文章类", description = "咨询文章类", tags = "咨询文章类")
public class ArticleController {

    /**
     * 注册类型属性编辑器
     *
     * @param binder
     */
    @InitBinder
    public void initBinder(ServletRequestDataBinder binder) {

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
    private RemoteAppArticleService remoteAppArticleService;

    /**
     * 查询首页平台公告和行业动态
     * @return
     */
    @ApiOperation(value = "查询首页平台公告和行业动态", httpMethod = "GET", notes = "查询首页平台公告和行业动态")
    @GetMapping("/loadNoticeInfo")
    @ResponseBody
    public List<Map<String, Object>> loadNoticeInfo(
            @ApiParam(name = "langCode", value = "语种", required = true) @RequestParam("langCode") String langCode,
            @ApiParam(name = "typeName", value = "类型（平台公告：pingtaigonggao; 行业动态：hangyedongtai）",example = "pingtaigonggao,hangyedongtai",required = true) @RequestParam("typeName") String typeName
    ) {
        switch (typeName) {
            case "pingtaigonggao":
                typeName = "平台公告";
                break;
            case "hangyedongtai":
                typeName = "行业动态";
                break;
        }
        List<Map<String, Object>> noticeList = remoteAppArticleService.loadNoticeInfo(langCode, typeName);
        if (noticeList != null && noticeList.size() > 0) {
            for (Map<String, Object> map : noticeList) {
                if (map.get("title") != null) {
                    String title = map.get("title").toString();
                    map.put("title", HtmlUtils.htmlUnescape(title));
                }
            }
        }
        return noticeList;
    }


    /**
     * 查询友情连接
     * @return
     */
    @ApiOperation(value = "查询友情连接", httpMethod = "GET", notes = "查询友情连接")
    @GetMapping("/friendlink")
    @ResponseBody
    public ApiJsonResult<List<FriendLink>> friendlink() {
        ApiJsonResult<List<FriendLink>> jsonResult = new ApiJsonResult<List<FriendLink>>();
        // 友情链接
        List<FriendLink> listFriendLink = remoteAppArticleService.findAllFriendLink();
        jsonResult.setSuccess(true);
        jsonResult.setObj(listFriendLink);
        return jsonResult;
    }

    @ApiOperation(value = "首页-资讯中心-查询文章分类列表", httpMethod = "GET", response = List.class, notes = "首页-资讯中心-查询文章分类列表")
    @GetMapping("/findArticleCategory")
    @ResponseBody
    public List<Map<String, Object>> findArticleCategory(
            @ApiParam(name = "langCode", value = "语种", required = true) @RequestParam("langCode") String langCode,
            @ApiParam(name = "pCategoryId", value = "文章分类父id", required = true) @RequestParam("pCategoryId") String pCategoryId,
            HttpServletRequest request) {
        // 调用远程查询对应语种的文章类别名称信息
        return remoteAppArticleService.findArticleCategory(langCode, pCategoryId);
    }

    /**
     * 咨询中心-文章详情内容获取/单页面内容获取
     * @return
     */
    @ApiOperation(value = "咨询中心-文章详情内容获取/单页面内容获取", httpMethod = "POST", response = Map.class, notes = "咨询中心-文章详情内容获取/单页面内容获取")
    @PostMapping("/articleInfo")
    @ResponseBody
    public Map<String, Object> articleInfo (
            @ApiParam(name = "articleId", value = "文章id", required = true) @RequestParam("articleId") String articleId,
            @ApiParam(name = "pId", value = "父菜单id", required = true) @RequestParam("pId") String pId,
            @ApiParam(name = "sId", value = "文章所属菜单id", required = true) @RequestParam("sId") String sId,
            @ApiParam(name = "isPage", value = "文章类型", required = true) @RequestParam("isPage") String isPage,
            @ApiParam(name = "langCode", value = "语种", required = true) @RequestParam("langCode") String langCode,
            HttpServletRequest request) {
        Map<String, Object> returnMap = new HashMap<>();

        // 查询父分类名称
        Map<String, Object> pMap = remoteAppArticleService.findCategoryById(langCode, new Long(pId));
        returnMap.put("pCategoryName", "");
        if (!pMap.isEmpty()) {
            returnMap.put("pCategoryName", pMap.get("langName"));
        }

        // 查询子分类名称
        Map<String, Object> cMap = remoteAppArticleService.findCategoryById(langCode, new Long(sId));
        returnMap.put("cCategoryName", "");
        if (cMap != null && !cMap.isEmpty()) {
            returnMap.put("cCategoryName", cMap.get("langName"));
        }

        if ("1".equals(isPage)) {
            Map<String, Object> paraMaps = new HashMap<>();
            paraMaps.put("langId", articleId);
            paraMaps.put("lang", langCode);
            Map<String, Object> map = remoteAppArticleService.getSinglePageContentByCategory(paraMaps);
            if (map != null) {
                returnMap.put("sCategoryName", map.get("langName"));
                returnMap.put("articleTitle", map.get("langName"));
                returnMap.put("singleContent", map);
            }
        } else {
            //查询文章
            Map<String, Article> map = remoteAppArticleService.getArtic(langCode, articleId, pId);
            Article artic = map.get("article");
            if (artic != null) {
                returnMap.put("sCategoryName", artic.getLangName());
                returnMap.put("articleTitle", artic.getTitle());
                returnMap.put("article", map.get("article"));
                returnMap.put("lastArticle", map.get("lastArticle"));
                returnMap.put("nextArticle", map.get("nextArticle"));
            }
        }
        return returnMap;
    }


    /**
     * 资讯中心-页面显示
     * @param request
     * @return
     */
    @ApiOperation(value = "资讯中心-页面显示-文章列表显示", httpMethod = "POST", response = Map.class, notes = "资讯中心-页面显示")
    @PostMapping("/pageShow")
    @ResponseBody
    public Map<String, Object> pageShow (
            @ApiParam(name = "categoryId", value = "文章分类id", required = true) @RequestParam("categoryId") String categoryId,
            @ApiParam(name = "offset", value = "分页页数", required = true) @RequestParam("offset") String offset,
            @ApiParam(name = "limit", value = "分页显示条数", required = true) @RequestParam("limit") String limit,
            @ApiParam(name = "pId", value = "父分类id", required = true) @RequestParam("pId") String pId,
            @ApiParam(name = "langCode", value = "语种", required = true) @RequestParam("langCode") String langCode,
            HttpServletRequest request) {
        Map<String, Object> returnMap = new HashMap<>();

        // 查询父分类名称
        Map<String, Object> pMap = remoteAppArticleService.findCategoryById(langCode, new Long(pId));
        returnMap.put("pCategoryName", "");
        if (!pMap.isEmpty()) {
            returnMap.put("pCategoryName", pMap.get("langName"));
        }
        // 查询子分类名称
        Map<String, Object> cMap = remoteAppArticleService.findCategoryById(langCode, new Long(categoryId));
        returnMap.put("cCategoryName", "");
        if (!cMap.isEmpty()) {
            returnMap.put("cCategoryName", cMap.get("langName"));
        }

        // 获取文章列表
        //分页查询文章
        Map<String, String> params = HttpServletRequestUtils.getParams(request);
        if (StringUtils.isEmpty(offset)) {
            params.put("offset", "0");
        }
        if (StringUtils.isEmpty(limit)) {
            params.put("limit", "5");
        }
        params.put("categoryid", categoryId);
        params.put("lang", langCode);
        FrontPage frontPage = remoteAppArticleService.findAritcByType(params);
        returnMap.put("frontPage", frontPage);
        returnMap.put("thispage", Integer.valueOf(params.get("offset")) / frontPage.getPageSize() + 1);
        returnMap.put("showColor", "3");
        return returnMap;
    }



    @ApiOperation(value = "APP-常见问题", httpMethod = "GET", response = List.class, notes = "常见问题")
    @GetMapping("/commonProblem")
    @ResponseBody
    public List<Map<String, Object>> commonProblem(
            @ApiParam(name = "langCode", value = "语种", required = true) @RequestParam("langCode") String langCode,
            HttpServletRequest request) {
        // 调用远程查询对应语种的文章类别名称信息
        return remoteAppArticleService.commonProblem(langCode);
    }

    @ApiOperation(value = "APP-常见问题搜索", httpMethod = "POST", response = List.class, notes = "常见问题搜索")
    @PostMapping("/commonProblemPageFind")
    @ResponseBody
    public FrontPage commonProblemPageFind( @ApiParam(name = "offset", value = "分页页数", required = true) @RequestParam("offset") String offset,
                                            @ApiParam(name = "limit", value = "分页显示条数", required = true) @RequestParam("limit") String limit,
                                            @ApiParam(name = "langCode", value = "语种", required = true) @RequestParam("langCode") String langCode,
                                            @ApiParam(name = "title", value = "title", required = false) @RequestParam("title") String title,
                                            HttpServletRequest request){
        Map<String, String> params = HttpServletRequestUtils.getParams(request);
        if (StringUtils.isEmpty(offset)) {
            params.put("offset", "0");
        }
        if (StringUtils.isEmpty(limit)) {
            params.put("limit", "10");
        }
        params.put("lang", langCode);
        FrontPage frontPage = remoteAppArticleService.commonProblemPageFind(params);
        return  frontPage;
    }

}
