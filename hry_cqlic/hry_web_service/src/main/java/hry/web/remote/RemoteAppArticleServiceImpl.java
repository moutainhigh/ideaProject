package hry.web.remote;

import hry.util.QueryFilter;
import hry.util.StringUtil;
import hry.util.date.DateUtil;
import hry.manage.remote.RemoteAppArticleService;
import hry.manage.remote.model.Article;
import hry.manage.remote.model.ArticleCategory;
import hry.manage.remote.model.FriendLink;
import hry.manage.remote.model.base.FrontPage;
import hry.util.properties.PropertiesUtils;
import hry.web.app.model.AppArticle;
import hry.web.app.model.AppCategory;
import hry.web.app.model.AppCategoryLangname;
import hry.web.app.model.AppFriendLink;
import hry.web.app.service.AppCategoryLangnameService;
import hry.web.article.dao.AppArticleDao;
import hry.web.article.service.AppArticleService;
import hry.web.article.service.AppCategoryService;
import hry.web.article.service.AppFriendLinkService;
import hry.web.dictionary.model.AppDic;
import hry.web.dictionary.service.AppDicOnelevelService;
import hry.web.dictionary.service.AppDicService;
import org.springframework.util.StringUtils;
import org.springframework.web.util.HtmlUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author denghf
 * @date 2017年7月8日16:45:22
 */
public class RemoteAppArticleServiceImpl implements RemoteAppArticleService{

	@Resource
	private AppArticleDao appArticleDao;
	
	@Resource
	private AppArticleService appArticleService;
	
	@Resource
	private AppCategoryService  appCategoryService;

	@Resource
	private AppCategoryLangnameService langnameService;

	@Resource
	private AppDicService appDicService;

	@Resource
	private AppDicOnelevelService onelevelService;
	
	
	// 查询指定类别下的所有的文章
	@Override
	public List<Article> findArticListByIdLimit(Long categoryId,Integer size,String language){
		List<AppArticle> list_app = appArticleDao.findArticListByIdLimit(categoryId, size,language);
		List<Article> list_ = new ArrayList<Article>();
		for(AppArticle app:list_app){
			Article article = new Article();
			article.setId(app.getId());
			article.setTitle(app.getTitle());
			article.setWriter(app.getWriter());
			article.setHits(app.getHits());
			article.setModified(DateUtil.dateToString(app.getModified(), "yyyy-MM-dd"));
			article.setContent(app.getContent());
			article.setCreated(app.getCreated());
			list_.add(article);
		}
		return list_;
	}
	
	@Resource
	private AppFriendLinkService appFriendLinkService;

	@Override
	public List<FriendLink> findAllFriendLink() {
		QueryFilter qf = new QueryFilter(AppFriendLink.class);
		qf.addFilter("status=", 0);
		List<AppFriendLink> list_app = appFriendLinkService.find(qf);
		List<FriendLink> list_ = new ArrayList<FriendLink>();
		for(AppFriendLink app:list_app){
			FriendLink friendLink = new FriendLink();
			friendLink.setId(app.getId());
			friendLink.setIsPicture(app.getIsPicture());
			friendLink.setLinkUrl(app.getLinkUrl());
			friendLink.setName(app.getName());
			friendLink.setPicturePath(app.getPicturePath());
			friendLink.setStatus(app.getStatus());
			friendLink.setWebsite(app.getWebsite());
			list_.add(friendLink);
		}
		
		
		return list_;
	}

	@Override
	public FrontPage findAritcByType(Map<String, String> params) {
		return appArticleService.findFrontPage(params);
	}

	@Override
	public List<ArticleCategory> findArticCategory(String website,String parentName) {
		QueryFilter queryFilter = new QueryFilter(AppCategory.class);
		queryFilter.addFilter("website=", website);
		queryFilter.setOrderby("sort");
		queryFilter.addFilter("preateName=", "no");
		//queryFilter.addFilter("name!=", parentName);
		queryFilter.addFilter("name!=", "帮助中心");
		queryFilter.addFilter("name!=", "关于我们");
		queryFilter.addFilter("name!=", "Help");
		queryFilter.addFilter("name!=", "About");
		queryFilter.setOrderby(" id ");
		List<AppCategory> find = appCategoryService.find(queryFilter);
		ArrayList<ArticleCategory> list =new ArrayList<ArticleCategory>();
		if(find!=null&&find.size()>0){
			for(AppCategory category : find){
				ArticleCategory articleCategory = new ArticleCategory();
				articleCategory.setId(category.getId());
				articleCategory.setName(category.getName());
				articleCategory.setSort(articleCategory.getSort());
				list.add(articleCategory);
			}
		}
		return list;
		
		
	}

	@Override
	public Map<String,Article> getArtic(String lang, String id, String pId) {
		// 查询语种id
		QueryFilter filter = new QueryFilter(AppDic.class);
		filter.addFilter("pkey=", "sysLanguage");
		filter.addFilter("value=", lang);
		AppDic appDic = appDicService.get(filter);

		Map<String,Article>  map= new HashMap<String,Article>();
		
		AppArticle appArticle = appArticleService.get(Long.valueOf(id));
		if(appArticle==null){
			return map;
		}
		//查询上一条
		QueryFilter lastQuery = new QueryFilter(AppArticle.class);
		lastQuery.addFilter("created>", appArticle.getCreated());
		lastQuery.addFilter("categoryId=", appArticle.getCategoryId());
		lastQuery.addFilter("langId=", appDic.getId());
		lastQuery.setOrderby("created");
		AppArticle lastArticle = appArticleService.get(lastQuery);
		if(lastArticle!=null){
			Article a = new Article();
			a.setId(lastArticle.getId());
			a.setTitle(HtmlUtils.htmlUnescape(lastArticle.getTitle()));
			a.setpCategoryId(new Long(pId));
			a.setsId(lastArticle.getCategoryId());
			map.put("lastArticle", a);
		}else{
			map.put("lastArticle", null);
		}
		
		//查询当前这一条
		
		Article b = new Article();
		b.setCategoryId(appArticle.getCategoryId());
		b.setCategoryName(appArticle.getCategoryName());
		b.setSeoTitle(appArticle.getSeoTitle());
		b.setSeoDescribe(appArticle.getSeoDescribe());
		b.setContent(appArticle.getContent());
		b.setWriter(appArticle.getWriter());
		b.setHits(appArticle.getHits());
		b.setTitle(appArticle.getTitle());
		b.setModified(DateUtil.dateToString(appArticle.getModified(), "yyyy-MM-dd"));
		b.setOutLink(appArticle.getOutLink());
		b.setLangType(appArticle.getLangType());
		b.setLangId(appArticle.getLangId());
		// 根据语种查询文章菜单名称
		QueryFilter articleLang = new QueryFilter(AppCategoryLangname.class);
		articleLang.addFilter("dicKey=", lang);
		articleLang.addFilter("categoryId=", appArticle.getCategoryId());
		AppCategoryLangname langname = langnameService.get(articleLang);
		b.setLangName(langname.getLangName());
		map.put("article", b);
		
		//查询下一条
		QueryFilter nextQuery = new QueryFilter(AppArticle.class);
		nextQuery.addFilter("created<", appArticle.getCreated());
		nextQuery.addFilter("langId=", appDic.getId());
		nextQuery.addFilter("categoryId=", appArticle.getCategoryId());
		nextQuery.setOrderby("created desc");
		AppArticle nextArticle = appArticleService.get(nextQuery);
		if(nextArticle!=null){
			Article a = new Article();
			a.setId(nextArticle.getId());
			a.setTitle(HtmlUtils.htmlUnescape(nextArticle.getTitle()));
			a.setpCategoryId(new Long(pId));
			a.setsId(nextArticle.getCategoryId());
			map.put("nextArticle", a);
		}else{
			map.put("nextArticle", null);
		}
		
		
		return map;
	}

	@Override
	public List<ArticleCategory> findHelpArticCategory(String website,String parentName) {
		
		//查帮助中心下所有的分类
		QueryFilter queryFilter = new QueryFilter(AppCategory.class);
		queryFilter.addFilter("website=", website);
		queryFilter.addFilter("preateName=", parentName);
		queryFilter.setOrderby("sort");
		List<AppCategory> find = appCategoryService.find(queryFilter);
		
		ArrayList<ArticleCategory> list =new ArrayList<ArticleCategory>();
			
		
		//查分类下的所有文章
		if(find!=null&&find.size()>0){
			for(AppCategory category : find){
				//封装数据
				ArticleCategory articleCategory = new ArticleCategory();
				articleCategory.setId(category.getId());
				articleCategory.setName(category.getName());
				articleCategory.setSort(articleCategory.getSort());
				
				QueryFilter filter = new QueryFilter(AppArticle.class);
				filter.addFilter("categoryId=",category.getId());
				filter.setOrderby("sort");
				List<AppArticle> articleList = appArticleService.find(filter);
				if(articleList!=null&&articleList.size()>0){
					ArrayList<Article> articles = new ArrayList<Article>();
					for(AppArticle appArticle : articleList){
						Article a = new Article();
						a.setId(appArticle.getId());
						a.setTitle(appArticle.getTitle());
						articles.add(a);
					}
					articleCategory.setArticles(articles);
				}
				
				list.add(articleCategory);
			}
		}
		
		
		return list;
	}

	@Override
	public Article getHelpArtic(String id) {
		AppArticle appArticle = appArticleService.get(Long.valueOf(id));		
		Article b = new Article();
		b.setCategoryId(appArticle.getCategoryId());
		b.setCategoryName(appArticle.getCategoryName());
		b.setSeoTitle(appArticle.getSeoTitle());
		b.setSeoDescribe(appArticle.getSeoDescribe());
		b.setContent(appArticle.getContent());
		b.setWriter(appArticle.getWriter());
		b.setHits(appArticle.getHits());
		b.setTitle(appArticle.getTitle());
		b.setModified(DateUtil.dateToString(appArticle.getModified(), "yyyy-MM-dd"));
		b.setOutLink(appArticle.getOutLink());
		//增加点击量
		Integer hits = appArticle.getHits()+1;
		appArticle.setHits(hits);
		appArticleService.update(appArticle);
		return b;
	}
	
	@Override
	public List<ArticleCategory> findAboutsArticCategory(String website,String parentName) {
		
		//查帮助中心下所有的分类
		QueryFilter queryFilter = new QueryFilter(AppCategory.class);
		queryFilter.addFilter("website=", website);
		queryFilter.addFilter("preateName=", parentName);
		queryFilter.setOrderby("sort");
		List<AppCategory> find = appCategoryService.find(queryFilter);
		
		ArrayList<ArticleCategory> list =new ArrayList<ArticleCategory>();
			
		
		//查分类下的所有文章
		if(find!=null&&find.size()>0){
			for(AppCategory category : find){
				//封装数据
				ArticleCategory articleCategory = new ArticleCategory();
				articleCategory.setId(category.getId());
				articleCategory.setName(category.getName());
				articleCategory.setSort(articleCategory.getSort());
				
				QueryFilter filter = new QueryFilter(AppArticle.class);
				filter.addFilter("categoryId=",category.getId());
				filter.setOrderby("sort");
				List<AppArticle> articleList = appArticleService.find(filter);
				if(articleList!=null&&articleList.size()>0){
					ArrayList<Article> articles = new ArrayList<Article>();
					for(AppArticle appArticle : articleList){
						Article a = new Article();
						a.setId(appArticle.getId());
						a.setTitle(appArticle.getTitle());
						articles.add(a);
					}
					articleCategory.setArticles(articles);
				}
				
				list.add(articleCategory);
			}
		}
		
		
		return list;
	}

	@Override
	public List<Map<String, Object>> loadNewsChildsMenu (String lang) {
		return langnameService.loadNewsChildsMenu(lang);
	}

	@Override
	public List<Map<String, Object>> loadSiteMapInfo(String lang) {
		return langnameService.loadSiteMapInfo(lang);
	}

	//常见问题  112
	@Override
	public List<Map<String, Object>> commonProblem(String langCode) {
		Map<String, String> params = new HashMap<>();
		params.put("offset", "0");
		params.put("limit", "5");
		params.put("lang", langCode);
		String pCategoryId= PropertiesUtils.APP.getProperty("app.pCategoryId");
		List<Map<String, Object>> reslt=findArticleCategory(langCode,pCategoryId);
		if(reslt!=null&&reslt.size()>0){
			for (Map<String, Object> map:reslt) {
				String categoryId=map.get("id").toString();
				params.put("categoryid",categoryId );
				FrontPage frontPage =findAritcByType(params);
				map.put("child",frontPage.getRows());
			}
		}
		return reslt;
	}

	//常见问题搜索
	@Override
	public FrontPage commonProblemPageFind(Map<String, String> params) {
		String langCode=params.get("lang");
		String title=params.get("title");
		String pCategoryId= PropertiesUtils.APP.getProperty("app.pCategoryId");
		List<Map<String, Object>> reslt=findArticleCategory(langCode,pCategoryId);
		String categoryIds="";
		if(reslt!=null&&reslt.size()>0){
			for (Map<String, Object> map:reslt) {
				String categoryId=map.get("id").toString();
				categoryIds+=categoryId+",";
			}
			if(categoryIds.length()>0){
				categoryIds=categoryIds.substring(0,categoryIds.length()-1);
			}
		}
		if(!StringUtils.isEmpty(title)){
			params.put("title", "%"+title+"%");
		}
		if(StringUtil.isNull(categoryIds)){
			params.put("categoryIds",categoryIds);
		}else{
			Integer offset  =Integer.valueOf(params.get("offset"));
			Integer limit  =Integer.valueOf(params.get("limit"));
			return  new FrontPage(null,0,offset,limit);
		}
		return findAritcByType(params);
	}

	@Override
	public List<Map<String, Object>> loadNoticeInfo (String lang, String categoryName) {
		return langnameService.loadNoticeInfo(lang,categoryName);
	}

	@Override
	public List<Map<String, Object>> findArticCategoryListById (String lang, Long categoryid) {
		return langnameService.findArticCategoryListById(lang, categoryid);
	}

	@Override
	public Map<String, Object> findCategoryById(String lang, Long categoryid) {
		return langnameService.findCategoryById(lang, categoryid);
	}

	@Override
	public Map<String, Object> getSinglePageContentByCategory (Map<String, Object> paraMaps) {
		String langId = paraMaps.get("langId").toString();
		String lang = paraMaps.get("lang").toString();
		QueryFilter qf = new QueryFilter(AppCategoryLangname.class);
		qf.addFilter("categoryId=", langId);
		qf.addFilter("dicKey=", lang);
		AppCategoryLangname langname = langnameService.get(qf);
		Map<String, Object> resultMap = new HashMap<>();
		if (langname != null) {
			resultMap.put("id", langname.getId());
			resultMap.put("categoryId", langname.getCategoryId());
			resultMap.put("dicKey", langname.getDicKey());
			resultMap.put("langName", langname.getLangName());
			resultMap.put("langContent", langname.getLangContent());
			resultMap.put("modified", langname.getModified());
		}
		return resultMap;
	}

	@Override
	public Map<String, Object> getSinglePageContent (Map<String, Object> paraMaps) {
		String langId = paraMaps.get("langId").toString();
		String lang = paraMaps.get("lang").toString();
		AppCategoryLangname langname = langnameService.get(new Long(langId));
		Map<String, Object> resultMap = new HashMap<>();
		if (langname != null) {
			resultMap.put("id", langname.getId());
			resultMap.put("categoryId", langname.getCategoryId());
			resultMap.put("dicKey", langname.getDicKey());
			resultMap.put("langName", langname.getLangName());
			resultMap.put("langContent", langname.getLangContent());
			resultMap.put("modified", langname.getModified());
		}
		return resultMap;
	}

	/**
	 * 加载文章分类列表
	 * @param langCode
	 * @return
	 */
	@Override
	public List<Map<String, Object>> findArticleCategory (String langCode, String pCategoryId) {
		return appCategoryService.findArticleCategory(langCode, pCategoryId);
	}

}
