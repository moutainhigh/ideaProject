/**
 * Copyright:   
 * @author:      liuchenghui
 * @version:     V1.0 
 * @Date:        2018-06-26 14:46:47 
 */
package hry.admin.web.service;

import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.admin.web.model.AppCategory;
import hry.util.QueryFilter;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


/**
 * <p> AppCategoryService </p>
 * @author:         liuchenghui
 * @Date :          2018-06-26 14:46:47 
 */
public interface AppCategoryService  extends BaseService<AppCategory, Long>{

    /**
     * 查询分页列表数据
     * @param filter
     * @return
     */
    PageResult findPageBySql (HttpServletRequest request);

    List<Map<String, Object>> selectlist(HttpServletRequest request);

    Map<String, Object> getCategoryById (Long id);

    List<Map<String,Object>> selectlistByCategory (HttpServletRequest request);
}
