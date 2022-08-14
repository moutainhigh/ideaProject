/**
 * Copyright:   
 * @author:      liuchenghui
 * @version:     V1.0 
 * @Date:        2018-06-26 14:46:47 
 */
package hry.admin.web.service.impl;

import com.github.pagehelper.Page;
import hry.admin.dic.model.AppDic;
import hry.admin.dic.service.AppDicService;
import hry.admin.web.dao.AppCategoryDao;
import hry.admin.web.model.AppCategory;
import hry.admin.web.model.AppCategoryLangname;
import hry.admin.web.service.AppCategoryLangnameService;
import hry.admin.web.service.AppCategoryService;
import hry.bean.PageResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.util.PageFactory;
import hry.util.QueryFilter;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p> AppCategoryService </p>
 * @author:         liuchenghui
 * @Date :          2018-06-26 14:46:47  
 */
@Service("appCategoryService")
public class AppCategoryServiceImpl extends BaseServiceImpl<AppCategory, Long> implements AppCategoryService{
	
	@Resource(name="appCategoryDao")
	@Override
	public void setDao(BaseDao<AppCategory, Long> dao) {
		super.dao = dao;
	}

	@Resource
	private AppDicService appDicService;

	@Resource
	private AppCategoryLangnameService langnameService;

	/**
	 * 查询分页列表数据
	 *
	 * @param request
	 * @return
	 */
	@Override
	public PageResult findPageBySql (HttpServletRequest request) {

		//创建PageResult对象
		QueryFilter filter = new QueryFilter(AppCategory.class, request);
		Page<AppCategory> page = PageFactory.getPage(filter);

		//----------------------查询开始------------------------------
		Map<String,Object> map = new HashMap<String,Object>();
		// 分类id
		String categoryId = request.getParameter("categoryId");
		if (!StringUtils.isEmpty(categoryId)) {
			map.put("categoryId", categoryId);
		}

		List<Map<String, Object>> rlist = ((AppCategoryDao)dao).findPageBySql(map);
        try {
            if(rlist != null && rlist.size() > 0){
                for (Map<String, Object> resultMap : rlist) {
                    // 查询系统语言
                    QueryFilter levelFilter = new QueryFilter(AppDic.class);
                    levelFilter.addFilter("pkey=", "sysLanguage");
                    levelFilter.setOrderby("created");
                    List<AppDic> langList = appDicService.find(levelFilter);
                    if(langList != null && langList.size() > 0) {
                        for (AppDic appDic : langList) {
                            // 查询文章名称
                            QueryFilter langNameFilter = new QueryFilter(AppCategoryLangname.class);
                            langNameFilter.addFilter("categoryId=", resultMap.get("id"));
                            langNameFilter.addFilter("dicId=", appDic.getId());
                            AppCategoryLangname langname = langnameService.get(langNameFilter);
                            if(langname == null){
                                resultMap.put(appDic.getValue(),"");
                            }else{
                                resultMap.put(appDic.getValue(),langname.getLangName());
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        PageResult pageResult = new PageResult(page, filter.getPage(),filter.getPageSize());
       /* // 设置分页数据
        pageResult.setRows(listPaging(rlist, filter.getPage(), filter.getPageSize()));
        // 设置总记录数
        pageResult.setTotal((long) list.size());
        pageResult.setRecordsTotal((long) list.size());
        pageResult.setDraw(filter.getDraw());
        pageResult.setPage(filter.getPage());
        pageResult.setPageSize(filter.getPageSize());*/
		return pageResult;
	}

	/**
	 * list 分页处理
	 * @param list
	 * @param pageNum 第几页
	 * @param pageSize 每页显示条数
	 * @return
	 */
	private List listPaging(List list, Integer pageNum, Integer pageSize) {
		try {
			if (list != null && list.size() > 0 ) {
                //总记录数
                Integer totalCount = list.size();
                Integer fromIndex = (pageNum - 1) * pageSize;
                //如果总数少于PAGE_SIZE,为了防止数组越界,toIndex直接使用totalCount即可
                int toIndex = Math.min(totalCount, pageNum * pageSize);
                return list.subList(fromIndex, toIndex);
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 将文章分类列表进行父子层级排序
	 * @param list
	 * @param resultList
	 */
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

	/**
	 * 文章父类别下拉框根据层级拼接显示
	 * @param request
	 * @return
	 */
	@Override
	public List<Map<String, Object>> selectlist(HttpServletRequest request) {
		// 页面类别 category：文章类别，article：文章内容
		String isPage = request.getParameter("pageType");
		// 语种value值-暂未用到
		String lang = request.getParameter("lang");
		// 先查询父级分类
		Map<String, Object> paraMap = new HashMap<>();
		paraMap.put("preateId", 0);
		paraMap.put("isPage_eq", "2");
		paraMap.put("lang", "zh_CN"); // 默认简体中文
		paraMap.put("state", 0);
		List<Map<String, Object>> fList = ((AppCategoryDao)dao).getCategoryByCondition(paraMap);
		List<Map<String, Object>> result = new ArrayList<>();
		// 调用递归查询子分类并拼接
		if (fList != null && fList.size() > 0) {
			// 如果是文章列表，只查询isPage是2的，如果是文章内容，则先查父再查子
			if ("category".equals(isPage)) {
				result.addAll(fList);
			} else if("article".equals(isPage)) {
				praseTree(fList, result);
			}
		}
		//System.out.println(result.toString());
		return result;
	}

	/**
	 * 查询文章类别层级
	 * @param find
	 * @param result
	 */
	private void praseTree (List<Map<String, Object>> find, List<Map<String, Object>> result){
		for(Map<String, Object> acMap : find){
			// 查询子文章类别
			Map<String, Object> paraMap = new HashMap<>();
			paraMap.put("preateId", acMap.get("categoryId"));
			paraMap.put("isPage_eq", "0");
			paraMap.put("lang", "zh_CN"); // 默认简体中文
			paraMap.put("state", 0);
			List<Map<String, Object>> findChild = ((AppCategoryDao)dao).getCategoryByCondition(paraMap);
			if (findChild != null && findChild.size() > 0) {
				dataHandler(findChild, acMap.get("langName").toString());
				result.addAll(findChild);
				praseTree(findChild, result);
			}
		}
	}

	/**
	 * 将父子类别拼接
	 * @param data
	 * @param pName
	 */
	private void dataHandler(List<Map<String, Object>> data, String pName){
		if (data != null && data.size() > 0) {
			for (Map<String, Object> acMap : data) {
				acMap.put("langName", pName + "-->" + acMap.get("langName").toString());
			}
		}
	}

	@Override
	public Map<String, Object> getCategoryById(Long id) {
		Map<String, String> map = new HashMap<>();
		map.put("id", id.toString());
		// 查询文章分类
		Map<String, Object> category = ((AppCategoryDao) dao).getCategoryById(map);
		// 将语种信息
		QueryFilter filter = new QueryFilter(AppDic.class);
		filter.addFilter("pkey=", "sysLanguage");
		filter.setOrderby("created");
		List<AppDic> appDicList = appDicService.find(filter);
		if (appDicList != null && appDicList.size() > 0) {
			List<Map<String, Object>> langList = new ArrayList<>();
			for (AppDic dic : appDicList) {
				Map<String, Object> langMap = new HashMap<>();
				langMap.put("langType", dic.getName());
				langMap.put("dicKey", dic.getValue());
				QueryFilter langNameFilter = new QueryFilter(AppCategoryLangname.class);
				langNameFilter.addFilter("categoryId=", id);
				langNameFilter.addFilter("dicKey=", dic.getValue());
				AppCategoryLangname langname = langnameService.get(langNameFilter);
				if(langname != null) {
					langMap.put("langName", langname.getLangName());
					langList.add(langMap);
				} else {
					langMap.put("langName", "");
					langList.add(langMap);
				}
			}
			category.put("langInfo", langList);
		}
		return category;
	}

    /**
     * 根据分类加载文章类型列表数据
     * @param request
     * @return
     */
    @Override
    public List<Map<String, Object>> selectlistByCategory (HttpServletRequest request) {
        // 分类id
        String cid = request.getParameter("cid");

		List<Map<String, Object>> result = new ArrayList<>();
		String pname = "";
		if ("0".equals(cid)) {
			pname = "文章分类管理";
		} else {
			AppCategory category = this.get(new Long(cid));
			if (category != null) {
				pname = category.getName();
			} else {
				return result;
			}
		}
		achiveFindChild(cid, pname, result);
		return result;
    }

	/**
	 * 查询文章类别层级
	 * @param cid 当前节点分类id
	 * @param result 总数据集合
	 */
	private void achiveFindChild (String cid, String pname, List<Map<String, Object>> result){
		// 文章分类管理-->公告动态-->平台公告
		// 先查询该分类下的子级分类
		Map<String, Object> paraMap = new HashMap<>();
		paraMap.put("preateId", cid);
		paraMap.put("lang", "zh_CN"); // 默认简体中文
		paraMap.put("state", 0);
		List<Map<String, Object>> upList = ((AppCategoryDao)dao).getCategoryByCondition(paraMap);
		if (upList != null && upList.size() > 0) {
			for(Map<String, Object> acMap : upList){
				acMap.put("langName", pname + "-->" + acMap.get("categoryName").toString());
				achiveFindChild(acMap.get("categoryId").toString(), acMap.get("langName").toString(), result);
			}
		} else {
			Map<String, Object> paraMap2 = new HashMap<>();
			paraMap2.put("categoryId", cid);
			paraMap2.put("lang", "zh_CN"); // 默认简体中文
			paraMap2.put("isPage_eq", "0");
			paraMap2.put("state", 0);
			List<Map<String, Object>> nextList = ((AppCategoryDao)dao).getCategoryByCondition(paraMap2);
			if (nextList != null && nextList.size() > 0) {
				for(Map<String, Object> acMap : nextList){
					acMap.put("langName", pname);
				}
				result.addAll(nextList);
			}
		}
	}

}
