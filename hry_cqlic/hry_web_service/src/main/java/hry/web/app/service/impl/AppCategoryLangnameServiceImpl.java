/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      liuchenghui
 * @version:     V1.0 
 * @Date:        2018-06-12 17:53:39 
 */
package hry.web.app.service.impl;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.util.QueryFilter;
import hry.web.app.dao.AppCategoryLangnameDao;
import hry.web.app.model.AppCategory;
import hry.web.app.model.AppCategoryLangname;
import hry.web.app.service.AppCategoryLangnameService;
import hry.web.article.service.AppCategoryService;
import hry.web.dictionary.model.AppDic;
import hry.web.dictionary.service.AppDicOnelevelService;
import hry.web.dictionary.service.AppDicService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p> AppCategoryLangnameService </p>
 * @author:         liuchenghui
 * @Date :          2018-06-12 17:53:39  
 */
@Service("appCategoryLangnameService")
public class AppCategoryLangnameServiceImpl extends BaseServiceImpl<AppCategoryLangname, Long> implements AppCategoryLangnameService{

	@Resource
	private AppDicOnelevelService dicOnelevelService;

	@Resource
	private AppDicService appDicService;

	@Resource
	private AppCategoryService categoryService;

	@Resource(name="appCategoryLangnameDao")
	@Override
	public void setDao(BaseDao<AppCategoryLangname, Long> dao) {
		super.dao = dao;
	}

	/**
	 * 加载资讯中心子菜单
	 * @param lang
	 * @return
	 */
	@Override
	public List<Map<String, Object>> loadNewsChildsMenu (String lang) {
		Map<String, Object> paraMap = new HashMap<>();
		paraMap.put("lang", lang);
		paraMap.put("preateId", "0");
		List<Map<String, Object>> rList = loadCategoryByLangAndPid(paraMap);
		if (rList != null && rList.size() > 0) {
			for (Map<String, Object> map : rList) {
				map.put("pageType", map.get("isPage"));
				QueryFilter qf = new QueryFilter(AppCategory.class);
				qf.addFilter("preateId=", map.get("categoryId"));
				qf.setOrderby("sort");
				List<AppCategory> acList = categoryService.find(qf);
				if (acList != null && acList.size() > 0) {
					AppCategory ac = acList.get(0);
					map.put("cId", ac.getId());
					// 子分类是单页面
					if ("1".equals(ac.getIsPage().toString())) {
						map.put("pageType", ac.getIsPage());
						QueryFilter lqf = new QueryFilter(AppCategoryLangname.class);
						lqf.addFilter("categoryId=", ac.getId());
						lqf.addFilter("dicKey=", lang);
						AppCategoryLangname langname = this.get(lqf);
						if (langname != null) {
 							map.put("langNameId", langname.getId());
						}
					}
				} else {
					map.put("cId", map.get("categoryId"));
				}
			}
		}
		return rList;
	}

	/**
	 * 加载站点地图信息-适合两级
	 * @param lang
	 */
	@Override
	public List<Map<String, Object>> loadSiteMapInfo (String lang) {
		Map<String, Object> paraMap = new HashMap<>();
		paraMap.put("lang", lang);
		paraMap.put("preateId", "0");
		// 查询所有父级标题
		List<Map<String, Object>> pList = loadCategoryByLangAndPid(paraMap);
		if (pList != null && pList.size() > 0) {
			// 循环查询父级标题下的子级集合
			for (Map<String, Object> pmap : pList) {
				if (pmap.get("categoryId") != null) {
					String categoryId = pmap.get("categoryId").toString();
					paraMap.put("preateId", categoryId);
					List<Map<String, Object>> cList = loadCategoryByLangAndPid(paraMap);
					pmap.put("clist", cList);
					/*if (cList != null && cList.size() > 4) {
						pmap.put("clist", cList.subList(0,4));
					}*/
				} else {
					pmap.put("clist", null);
				}
			}
			return pList;
		}
		return null;
	}


	/**
	 * 加载某个类别的文章信息
	 * @param lang
	 * @return
	 */
	@Override
	public List<Map<String, Object>> loadNoticeInfo (String lang, String categoryName) {
		// 查询语种主键
		AppCategory category = new AppCategory();

		QueryFilter filter = new QueryFilter(AppDic.class);
		filter.addFilter("pkey=", "sysLanguage");
		filter.addFilter("value=", lang);
		AppDic appDic = appDicService.get(filter);
		Map<String, Object> paraMap = new HashMap<>();
		paraMap.put("langId", appDic.getId());
		paraMap.put("langName", categoryName);
		List<Map<String, Object>> list = ((AppCategoryLangnameDao) dao).getNoticeInfo(paraMap);
		if (list != null && list.size() > 0) {
			for (Map<String, Object> map : list) {
				if (map.get("modified") != null) {
					String modified = map.get("modified").toString();
					map.put("modified", modified.substring(0,10));
				}
			}
		}
		return list;
	}

	/**
	 * 查询资讯中心左侧菜单-数据
	 * @param lang
	 * @param categoryid
	 * @return
	 */
	@Override
	public List<Map<String, Object>> findArticCategoryListById (String lang, Long categoryid) {
		Map<String, Object> paraMap = new HashMap<>();
		paraMap.put("categoryId", categoryid);
		paraMap.put("lang", lang);
		return ((AppCategoryLangnameDao) dao).findArticCategoryListById(paraMap);
	}

	/**
	 * 根据语种和类别id加载数据
	 * @return
	 */
	private List<Map<String, Object>> loadCategoryByLangAndPid (Map<String, Object> paraMap) {
		return ((AppCategoryLangnameDao) dao).loadNewsChildsMenu(paraMap);
	}

	/**
	 * 根据分类id查询部分信息
	 * @param lang
	 * @param categoryId
	 * @return
	 */
	@Override
	public Map<String, Object> findCategoryById(String lang, Long categoryId) {
		Map<String, Object> paraMap = new HashMap<>();
		paraMap.put("id", categoryId);
		paraMap.put("lang", lang);
		List<Map<String, Object>> list = ((AppCategoryLangnameDao) dao).findArticCategoryListById(paraMap);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
 }
