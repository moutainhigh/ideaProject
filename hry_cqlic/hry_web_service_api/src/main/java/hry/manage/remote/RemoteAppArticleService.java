package hry.manage.remote;

import hry.manage.remote.model.Article;
import hry.manage.remote.model.ArticleCategory;
import hry.manage.remote.model.FriendLink;
import hry.manage.remote.model.base.FrontPage;

import java.util.List;
import java.util.Map;

public interface RemoteAppArticleService {

	/**
	 * 加载行业动态和平台公告列表内容
	 * @param lang
	 * @param categoryName
	 * @return
	 */
	List<Map<String,Object>> loadNoticeInfo(String lang, String categoryName);

	/**
	 * 前台首页-获取文章分类列表
	 * @param langCode
	 * @param pCategoryId
	 * @return
	 */
	List<Map<String, Object>> findArticleCategory (String langCode, String pCategoryId);

	/**
	 * 根据文章分类id和语种查询内容
	 * @param lang
	 * @param categoryid
	 * @return
	 */
	Map<String, Object> findCategoryById(String lang, Long categoryid);

	/**
	 * 根据类别id查询单页面内容
	 * @param paraMaps
	 * @return
	 */
	Map<String, Object> getSinglePageContentByCategory (Map<String, Object> paraMaps);

	/**
	 * 获取文章详情，返回三个对象  一个上一条，要查的一条，一下条
	 * @param lang
	 * @param id
	 * @param pId
	 * @return
	 */
	Map<String,Article> getArtic(String lang, String id, String pId);

	/**
	 * 分页查询文章
	 * @param params
	 * @return
	 */
	FrontPage findAritcByType(Map<String, String> params);

	/**
	 * 查询所有的友情链接
	 * @return
	 */
	List<FriendLink> findAllFriendLink();

	/**
	 * 获取首页底部站点信息
	 * @param lang
	 * @return
	 */
	List<Map<String, Object>> loadSiteMapInfo(String lang);



	//常见问题
	List<Map<String, Object>> commonProblem(String langCode);
	//常见问题搜索
	FrontPage commonProblemPageFind(Map<String, String> params);








































































	//=======================================分割线===========================================================================================================

	public List<Article> findArticListByIdLimit(Long categoryId, Integer size, String language);

	/**
	 * 查询全部分类
	 * @return
	 */
	public List<ArticleCategory> findArticCategory(String website, String parentName);

	/**
	 * 获取帮助中心文章详情
	 * @return
	 */
	public  Article  getHelpArtic(String id);

	/**
	 * 查询帮助中心树型结构 ,包含文章
	 * @return
	 */
	public List<ArticleCategory> findHelpArticCategory(String website, String parentName);

	/**
	 * 查询关于我们树型结构 ,包含文章
	 * @return
	 */
	public List<ArticleCategory> findAboutsArticCategory(String website, String parentName);

	/**
	 * 根据语种查询资讯中心下拉子菜单
	 * @param lang
	 * @return
	 */
	List<Map<String, Object>> loadNewsChildsMenu(String lang);



    List<Map<String,Object>> findArticCategoryListById(String lang, Long categoryid);

    Map<String,Object> getSinglePageContent(Map<String, Object> paraMaps);

}
