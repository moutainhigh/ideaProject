/**
 * Copyright:
 *
 * @author: liuchenghui
 * @version: V1.0
 * @Date: 2018-06-26 14:55:28
 */
package hry.admin.web.controller;


import hry.admin.dic.model.AppDic;
import hry.admin.dic.service.AppDicService;
import hry.admin.web.model.AppArticle;
import hry.admin.web.model.AppCategory;
import hry.admin.web.service.AppArticleService;
import hry.admin.web.service.AppCategoryService;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.JsoupUtil;
import hry.util.QueryFilter;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Copyright:   互融云
 * @author: liuchenghui
 * @version: V1.0
 * @Date: 2018-06-26 14:55:28
 */
@Controller
@RequestMapping("/web/apparticle")
public class AppArticleController extends BaseController<AppArticle, Long> {

    @Resource(name = "appArticleService")
    @Override
    public void setService (BaseService<AppArticle, Long> service) {
        super.service = service;
    }

    @Resource
    private AppDicService appDicService;

    @Resource
    private AppCategoryService appCategoryService;

    @RequestMapping(value = "/see/{id}")
    public ModelAndView see (@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("/admin/web/apparticlesee");
        AppArticle appArticle = service.get(id);
        if (appArticle != null) {
            AppDic appDic = appDicService.get(appArticle.getLangId());
            mav.addObject("sysLangId", appDic.getValue());
        }
        mav.addObject("model", appArticle);
        return mav;
    }


    @RequestMapping(value = "/add")
    @ResponseBody
    public JsonResult add (HttpServletRequest request, AppArticle appArticle) {
        String sysLangId = request.getParameter("sysLangId");
        String editorValue = request.getParameter("editorValue");
        appArticle.setContent(JsoupUtil.clean(editorValue));

        QueryFilter qf = new QueryFilter(AppDic.class);
        qf.addFilter("pkey=", "sysLanguage");
        qf.addFilter("value=", sysLangId);
        AppDic appDic = appDicService.get(qf);

        Long id = appArticle.getCategoryId();
        AppCategory category = appCategoryService.get(id);
        String name = category.getName();
        appArticle.setCategoryName(name);
        appArticle.setLangName(name);
        appArticle.setLangType(appDic.getName());
        appArticle.setLangId(appDic.getId());
        appArticle.setShortContent(JsoupUtil.clean(appArticle.getShortContent()));
        JsonResult jsonResult = new JsonResult();
        if (appArticle.getShortContent() != null && appArticle.getShortContent().length() > 300) {
            jsonResult.setSuccess(false);
            jsonResult.setMsg("文章简介不能超过300字符");
        } else {
            jsonResult = super.save(appArticle);
        }
        return jsonResult;
    }

    @RequestMapping(value = "/modifysee/{id}")
    public ModelAndView modifysee (@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("/admin/web/apparticlemodify");
        AppArticle appArticle = service.get(id);
        if (appArticle != null) {
            AppDic appDic = appDicService.get(appArticle.getLangId());
            mav.addObject("sysLangId", appDic.getValue());
        }
        mav.addObject("model", appArticle);
        return mav;
    }

    @RequestMapping(value = "/modify")
    @ResponseBody
    public JsonResult modify (HttpServletRequest request, AppArticle appArticle) {
        JsonResult jsoreult = new JsonResult();
        if (appArticle.getShortContent() != null && appArticle.getShortContent().length() > 300) {
            jsoreult.setSuccess(false);
            jsoreult.setMsg("文章简介不能超过300字符");
        } else {
            String sysLangId = request.getParameter("sysLangId");
            String editorValue = request.getParameter("editorValue");
            appArticle.setContent(JsoupUtil.clean(editorValue));
            appArticle.setShortContent(JsoupUtil.clean(appArticle.getShortContent()));

            QueryFilter qf = new QueryFilter(AppDic.class);
            qf.addFilter("pkey=", "sysLanguage");
            qf.addFilter("value=", sysLangId);
            AppDic appDic = appDicService.get(qf);

            Long id = appArticle.getCategoryId();
            AppCategory category = appCategoryService.get(id);
            appArticle.setLangId(appDic.getId());
            appArticle.setLangType(appDic.getName());
            appArticle.setLangName(category.getName());
            jsoreult = super.update(appArticle);
        }
        return jsoreult;
    }


    @RequestMapping(value = "/remove")
    @ResponseBody
    public JsonResult remove (String ids) {
        AppArticleService aservice = (AppArticleService) service;
        JsonResult jsonResult = new JsonResult();
        String s = aservice.removeAll(ids);
        if ("OK".equals(s)) {
            jsonResult.setSuccess(true);
            jsonResult.setMsg("删除成功");
        } else {
            jsonResult.setSuccess(false);
            jsonResult.setMsg("删除失败");
        }
        return jsonResult;
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public PageResult list (HttpServletRequest request) {
        String sysLangId = request.getParameter("sysLangId");
        String langName = request.getParameter("langName");
        String title = request.getParameter("title");
        String categoryId = request.getParameter("categoryId");
        String cids = "";
        if (!StringUtils.isEmpty(categoryId) && !"0".equals(categoryId)) {
            // 根据分类获取子类id
            QueryFilter cfilter = new QueryFilter(AppCategory.class);
            if (!StringUtils.isEmpty(categoryId)) {
                cfilter.addFilter("preateId=", categoryId);
            }
            List<AppCategory> categoryList = appCategoryService.find(cfilter);
            if (categoryList != null && categoryList.size() > 0) {
                for (AppCategory ac : categoryList) {
                    cids += "," + ac.getId();
                }
                cids = cids.substring(1);
            } else {
                cids = categoryId;
            }
        }

        QueryFilter filter = new QueryFilter(AppArticle.class, request);
        filter.addFilter("status=", 0);
        if (!StringUtils.isEmpty(cids)) {
            filter.addFilter("categoryId_in", cids);
        }

        if (!StringUtils.isEmpty(sysLangId)) {
            QueryFilter qf = new QueryFilter(AppDic.class);
            qf.addFilter("pkey=", "sysLanguage");
            qf.addFilter("value=", sysLangId);
            AppDic appDic = appDicService.get(qf);
            if (appDic != null) {
                filter.addFilter("langId=", appDic.getId());
            }
        }

        if (!StringUtils.isEmpty(langName)) {
            filter.addFilter("categoryId=", langName);
        }

        if (!StringUtils.isEmpty(title)) {
            filter.addFilter("title_like", "%" + title + "%");
        }

        filter.setOrderby("created  desc");
        return super.findPage(filter);
    }


}
