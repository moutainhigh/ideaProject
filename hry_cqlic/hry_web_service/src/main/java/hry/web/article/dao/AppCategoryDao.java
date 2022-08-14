/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年4月29日 下午3:40:00
 */
package hry.web.article.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.web.app.model.AppCategory;
import hry.web.app.model.AppCategoryVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * TODO
 * </p>
 * 
 * @author: Wu Shuiming
 * @Date : 2016年4月29日 下午3:40:00
 */
public interface AppCategoryDao extends BaseDao<AppCategory, Long> {
	
	public List<AppCategoryVo> findFooterCategory();
	
	List<Map<String, Object>> findPageBySql(Map<String, Object> map);

	Map<String, Object> getCategoryById(Map<String, String> map);

    List<Map<String, Object>> findArticleCategory (Map<String, String> map);
}
