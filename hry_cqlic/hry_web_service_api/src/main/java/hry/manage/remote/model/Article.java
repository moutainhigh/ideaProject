package hry.manage.remote.model;


import java.io.Serializable;
import java.util.Date;



public class Article implements Serializable{

	private Long id;
	// 文章标题
	private String title;
	// 文章类别名称
	private String categoryName;
	// 文章类别id
	private Long categoryId;
	// 文章状态 (0表示有效 1 表示无效 2 表示已删除)
	private Integer status;
	// seo文章标题
	private String seoTitle;
	// seo文章关键字
	private String seoKeyword;
	// seo文章描述
	private String seoDescribe;
	// 区分中国站(cn表示中国站  en表示国际站)
	private String website;

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	// 排序字段
	private Integer sort;
	// 是否置顶 (0表示否 1 表示yes)默认是0
	private Integer isStick;
	// 文章正文
	private String content;
	// 是否外链 (0表示false 1表示true)默认0
	private Integer isOutLink;
	// 外链地址
	private String outLink;
	// 图片标题地址
	private String titleImage;
	// 文章简介
	private String shortContent;

	// 作者
	private String writer;
	// 来源
	private String source;
	// 点击量
	private Integer hits;
	
	private String modified;
	
	private Date created;

	// 文章分类父级id
	private Long pCategoryId;

	// 文章上级菜单id
	private Long sId;

	// 语种名称
	private String langType;

	// 语种对应的文章栏目名称
	private String langName;

	// 语种主键
	private Long langId;

	public Long getsId () {
		return sId;
	}

	public void setsId (Long sId) {
		this.sId = sId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getSeoTitle() {
		return seoTitle;
	}

	public void setSeoTitle(String seoTitle) {
		this.seoTitle = seoTitle;
	}

	public String getSeoKeyword() {
		return seoKeyword;
	}

	public void setSeoKeyword(String seoKeyword) {
		this.seoKeyword = seoKeyword;
	}

	public String getSeoDescribe() {
		return seoDescribe;
	}

	public void setSeoDescribe(String seoDescribe) {
		this.seoDescribe = seoDescribe;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getIsStick() {
		return isStick;
	}

	public void setIsStick(Integer isStick) {
		this.isStick = isStick;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getIsOutLink() {
		return isOutLink;
	}

	public void setIsOutLink(Integer isOutLink) {
		this.isOutLink = isOutLink;
	}

	public String getOutLink() {
		return outLink;
	}

	public void setOutLink(String outLink) {
		this.outLink = outLink;
	}

	public String getTitleImage() {
		return titleImage;
	}

	public void setTitleImage(String titleImage) {
		this.titleImage = titleImage;
	}

	public String getShortContent() {
		return shortContent;
	}

	public void setShortContent(String shortContent) {
		this.shortContent = shortContent;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Integer getHits() {
		return hits;
	}

	public void setHits(Integer hits) {
		this.hits = hits;
	}
	
	

	public String getModified() {
		return modified;
	}

	public void setModified(String modified) {
		this.modified = modified;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Long getpCategoryId () {
		return pCategoryId;
	}

	public void setpCategoryId (Long pCategoryId) {
		this.pCategoryId = pCategoryId;
	}

	public String getLangType () {
		return langType;
	}

	public void setLangType (String langType) {
		this.langType = langType;
	}

	public String getLangName () {
		return langName;
	}

	public void setLangName (String langName) {
		this.langName = langName;
	}

	public Long getLangId () {
		return langId;
	}

	public void setLangId (Long langId) {
		this.langId = langId;
	}
}
