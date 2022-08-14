/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年4月29日 下午3:48:47
 */
package hry.web.article.service;

import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.web.app.model.AppCategory;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * </p>
 * 
 * @author: Wu Shuiming
 * @Date : 2016年4月29日 下午3:48:47
 */
public interface AppCategoryService extends BaseService<AppCategory, Long> {
	
	/**
	 * 查询分类树
	 * @param request
	 * @return
	 */
	List<AppCategory> selectlist(HttpServletRequest request);

    PageResult findPageBySql(QueryFilter filter);

    Map<String, Object> getCategoryById(Long id);

	/**
	 * 加载文章分类列表
	 * @param langCode
	 * @param pCategoryId
	 * @return
	 */
    List<Map<String, Object>> findArticleCategory (String langCode, String pCategoryId);
}
