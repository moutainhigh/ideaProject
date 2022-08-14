package hry.web.article.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import hry.core.mvc.dao.base.BaseDao;
import hry.bean.PageResult;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.util.QueryFilter;
import hry.web.app.model.AppCategory;
import hry.web.app.model.AppCategoryLangname;
import hry.web.app.service.AppCategoryLangnameService;
import hry.web.article.dao.AppCategoryDao;
import hry.web.article.service.AppCategoryService;
import hry.web.dictionary.model.AppDicOnelevel;
import hry.web.dictionary.service.AppDicOnelevelService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("appCategoryService")
public class AppCategoryServiceImpl extends BaseServiceImpl<AppCategory, Long>
		implements AppCategoryService {
	private static Logger logger = Logger.getLogger(AppCategoryServiceImpl.class);
	@Resource
	private AppDicOnelevelService dicOnelevelService;

	@Resource
	private AppCategoryLangnameService langnameService;

	@Resource(name = "appCategoryDao")
	@Override
	public void setDao(BaseDao<AppCategory, Long> dao) {
		super.dao = dao;
	}

	@Override
	public List<AppCategory> selectlist(HttpServletRequest request) {
		String isPage = request.getParameter("isPage");
		if (isPage.isEmpty()) { // 未传isPage时，默认为0
			isPage = "0";
		}

		QueryFilter filter = new QueryFilter(AppCategory.class, request);
		filter.addFilter("state=", 0);
		if ("2".equals(isPage)) {
			filter.addFilter("isPage=", isPage);
		} else {
			filter.addFilter("isPage=", "2");
		}
		filter.addFilter("preateId=", 0);
		filter.setOrderby("sort");
		List<AppCategory> find = this.find(filter);
		List<AppCategory> result = new ArrayList<>();
		if (!"2".equals(isPage)) {
			praseTree(find, result);
		} else {
			result.addAll(find);
		}
		logger.error(result.toString());
		return result;
	}
	
	private void praseTree (List<AppCategory> find, List<AppCategory> result){
		if(find != null && find.size() > 0){
			for(AppCategory ac : find){
				if(ac.getPreateId() == 0){
					//result.add(ac);
				}

				// 查询子文章类别
				QueryFilter filter = new QueryFilter(AppCategory.class);
				filter.addFilter("state=", 0);
				filter.addFilter("isPage=",0);
				filter.addFilter("preateId=", ac.getId());
				filter.setOrderby("sort");
				List<AppCategory> findChild = this.find(filter);
				if (findChild != null && findChild.size() > 0) {
					dataHandler(findChild, ac.getName());
					result.addAll(findChild);
					praseTree(findChild, result);
				}
			}
		}
	}

	private void dataHandler(List<AppCategory> data, String pName){
		if (data != null && data.size() > 0) {
			for (AppCategory ac : data) {
				ac.setName(pName + "--" + ac.getName());
			}
		}
	}

	@Override
	public PageResult findPageBySql (QueryFilter filter) {
		// ----------------------分页查询头部外壳------------------------------
		// 创建PageResult对象
		PageResult pageResult = new PageResult();
		Page<AppCategory> page = null;
		if (filter.getPageSize().compareTo(Integer.valueOf(-1)) == 0) {
			// pageSize = -1 时
			// pageHelper传pageSize参数传0查询全部
			page = PageHelper.startPage(filter.getPage(), 0);
		} else {
			page = PageHelper.startPage(filter.getPage(), filter.getPageSize());
		}
		// ----------------------分页查询头部外壳------------------------------

		// ----------------------查询开始------------------------------

		Map<String, Object> map = new HashMap<String, Object>();
		/*// 邮箱
		String email = filter.getRequest().getParameter("email");
		// 手机号
		String mobilePhone = filter.getRequest().getParameter("mobilePhone");
		// 邮箱
		if (!StringUtils.isEmpty(email)) {
			map.put("email", "%" + email + "%");
		}
		// 手机号
		if (!StringUtils.isEmpty(mobilePhone)) {
			map.put("mobilePhone", "%" + mobilePhone + "%");
		}*/
		map.put("preateId", "0");
		List<Map<String, Object>> rlist = ((AppCategoryDao)dao).findPageBySql(map);
		List<Map<String, Object>> list = new ArrayList<>();
		sortList(rlist, list);
		try {
			if(list != null && list.size() > 0){
				for (Map<String, Object> resultMap : list) {
					// 查询系统语言
					QueryFilter levelFilter = new QueryFilter(AppDicOnelevel.class);
					levelFilter.addFilter("pDicKey=", "sysLanguage");
					levelFilter.setOrderby("orderNo");
					List<AppDicOnelevel> langList = dicOnelevelService.find(levelFilter);
					if(langList != null && langList.size() > 0) {
						for (AppDicOnelevel onelevel : langList) {
							// 查询文章名称
							QueryFilter langNameFilter = new QueryFilter(AppCategoryLangname.class);
							langNameFilter.addFilter("categoryId=", resultMap.get("id"));
							langNameFilter.addFilter("dicId=", onelevel.getId());
							AppCategoryLangname langname = langnameService.get(langNameFilter);
							if(langname == null){
								resultMap.put(onelevel.getDicKey(),"");
							}else{
								resultMap.put(onelevel.getDicKey(),langname.getLangName());
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 设置分页数据
		pageResult.setRows(list);
		// 设置总记录数
		pageResult.setRecordsTotal((long) list.size());
		pageResult.setDraw(filter.getDraw());
		pageResult.setPage(filter.getPage());
		pageResult.setPageSize(filter.getPageSize());

		return pageResult;
	}

	private void sortList(List<Map<String, Object>> list, List<Map<String, Object>> resultList){
		if (list != null && list.size() > 0) {
			for (Map<String, Object> map : list) {
				if ("0".equals(map.get("preateId").toString())){
					resultList.add(map);
				}
				Map<String, Object> pmap = new HashMap<String, Object>();
				pmap.put("preateId", map.get("id"));
				List<Map<String, Object>> clist = ((AppCategoryDao)dao).findPageBySql(pmap);
				if (clist != null && clist.size() > 0) {
					resultList.addAll(clist);
					sortList(clist, resultList);
				}
			}
		}
	}

	@Override
	public Map<String, Object> getCategoryById(Long id) {
		Map<String, String> map = new HashMap<>();
		map.put("id", id.toString());
		Map<String, Object> category = ((AppCategoryDao) dao).getCategoryById(map);
		// 查询系统语言
		QueryFilter levelFilter = new QueryFilter(AppDicOnelevel.class);
		levelFilter.addFilter("pDicKey=", "sysLanguage");
		levelFilter.setOrderby("orderNo");
		List<AppDicOnelevel> langList = dicOnelevelService.find(levelFilter);
		if(langList != null && langList.size() > 0) {
			for (AppDicOnelevel onelevel : langList) {
				QueryFilter langNameFilter = new QueryFilter(AppCategoryLangname.class);
				langNameFilter.addFilter("categoryId=", id);
				langNameFilter.addFilter("dicId=", onelevel.getId());
				AppCategoryLangname langname = langnameService.get(langNameFilter);
				if(langname == null){
					category.put(onelevel.getDicKey(),"");
				}else{
					category.put(onelevel.getDicKey(),langname.getLangName());
				}
			}
		}
		return category;
	}

	/**
	 * 加载文章分类列表
	 * @param langCode
	 * @param pCategoryId
	 * @return
	 */
	@Override
	public List<Map<String, Object>> findArticleCategory (String langCode, String pCategoryId) {
		Map<String, String> map = new HashMap<>();
		map.put("langCode", langCode);
		map.put("pCategoryId", pCategoryId);
		return ((AppCategoryDao) dao).findArticleCategory(map);
	}
}
