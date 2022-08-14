/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年4月29日 下午3:43:55
 */
package hry.web.article.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.manage.remote.model.Article;
import hry.web.app.model.AppArticle;
import hry.web.app.model.AppArticleVo;
import hry.web.app.model.vo.AppArticlePageVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * TODO
 * </p>
 * 
 * @author: Wu Shuiming
 * @Date : 2016年4月29日 下午3:43:55
 */
public interface AppArticleDao extends BaseDao<AppArticle, Long> {

	// 查询分页根据类型的id 查出所有的文章信息(不包括文章内容)
	List<AppArticle> findPageBySql(@Param("categoryId") Long categoryId, @Param("website") String website);

	// 查询上一页
	AppArticle findUpActicle(@Param("acticleId") Long acticleId);

	// 查询下一页
	AppArticle findNextActicle(@Param("acticleId") Long acticleId);

	List<AppArticleVo> findArtricAndCategory();
	
	List<AppArticle> findArticList(@Param("categoryName") String categoryName,@Param("size") int size);
	
	
	List<AppArticle> findArticListById(@Param("id") Long id, @Param("size") Integer size);

	AppArticle firstnews();
	
	public AppArticlePageVo findArticListByPage(@Param(value = "id") Long id, @Param(value = "stat") Integer stat, @Param(value = "end") Integer end);
	
	List<AppArticle> findArticListByIdLimit(@Param("categoryId") Long categoryId,@Param("size") Integer size,@Param("language") String language);
	
	/**
	 * 前台分页方法
	 * @param params
	 * @return
	 */
	List<Article> findFrontPageBySql(Map<String, String> params);

	List<Article> findFrontPageByCondition(Map<String, String> params);
}
