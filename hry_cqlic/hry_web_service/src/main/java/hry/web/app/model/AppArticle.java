package hry.web.app.model;

import hry.core.mvc.model.BaseModel;
import org.springframework.web.util.HtmlUtils;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@SuppressWarnings("serial")
@Table(name = "app_article")
public class AppArticle extends BaseModel {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	// 文章标题
	@Column(name = "title")
	private String title;
	// 文章类别名称
	@Column(name = "categoryName")
	private String categoryName;
	// 文章类别id
	@Column(name = "categoryId")
	private Long categoryId;
	// 文章状态 (0表示有效 1 表示无效 2 表示已删除)
	@Column(name = "status")
	private Integer status;
	// seo文章标题
	@Column(name = "seoTitle")
	private String seoTitle;
	// seo文章关键字
	@Column(name = "seoKeyword")
	private String seoKeyword;
	// seo文章描述
	@Column(name = "seoDescribe")
	private String seoDescribe;
	// 区分中国站(cn表示中国站  en表示国际站)
	@Column(name = "website")
	private String website;

	// 语种名称
	@Column(name = "langType")
	private String langType;

	// 语种对应的文章栏目名称
	@Column(name = "langName")
	private String langName;

	// 语种主键
	@Column(name = "langId")
	private Long langId;

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
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

	// 排序字段
	@Column(name = "sort")
	private Integer sort;
	// 是否置顶 (0表示否 1 表示yes)默认是0
	@Column(name = "isStick")
	private Integer isStick;
	// 文章正文
	@Column(name = "content")
	private String content;
	// 是否外链 (0表示false 1表示true)默认0
	@Column(name = "isOutLink")
	private Integer isOutLink;
	// 外链地址
	@Column(name = "outLink")
	private String outLink;
	// 图片标题地址
	@Column(name = "titleImage")
	private String titleImage;
	// 文章简介
	@Column(name = "shortContent")
	private String shortContent;

	// 作者
	@Column(name = "writer")
	private String writer;
	// 来源
	@Column(name = "source")
	private String source;
	// 点击量
	@Column(name = "hits")
	private Integer hits;

	@Transient
	// 不与数据库映射的字段
	private AppArticle upAppArticle;
	@Transient
	// 不与数据库映射的字段
	private AppArticle nextAppArticle;

	public AppArticle getUpAppArticle() {
		return upAppArticle;
	}

	public void setUpAppArticle(AppArticle upAppArticle) {
		this.upAppArticle = upAppArticle;
	}

	public AppArticle getNextAppArticle() {
		return nextAppArticle;
	}

	public void setNextAppArticle(AppArticle nextAppArticle) {
		this.nextAppArticle = nextAppArticle;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public Integer getStaus() {
		return status;
	}

	public void setStaus(Integer staus) {
		this.status = staus;
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
		return HtmlUtils.htmlUnescape(content);
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

	public AppArticle(Long id, String title, String categoryName,
			Long categoryId, Integer status, String seoTitle,
			String seoKeyword, String seoDescribe, Integer sort,
			Integer isStick, String content, Integer isOutLink, String outLink,
			String titleImage, String shortContent, String writer,
			String source, Integer hits, String langType, String langName, Long langId) {
		super();
		this.id = id;
		this.title = title;
		this.categoryName = categoryName;
		this.categoryId = categoryId;
		this.status = status;
		this.seoTitle = seoTitle;
		this.seoKeyword = seoKeyword;
		this.seoDescribe = seoDescribe;
		this.sort = sort;
		this.isStick = isStick;
		this.content = content;
		this.isOutLink = isOutLink;
		this.outLink = outLink;
		this.titleImage = titleImage;
		this.shortContent = shortContent;
		this.writer = writer;
		this.source = source;
		this.hits = hits;
		this.langName = langName;
		this.langType = langType;
		this.langId = langId;
	}

	public AppArticle() {
		super();
	}

	@Override
	public String toString () {
		return "AppArticle{" +
				"id=" + id +
				", title='" + title + '\'' +
				", categoryName='" + categoryName + '\'' +
				", categoryId=" + categoryId +
				", status=" + status +
				", seoTitle='" + seoTitle + '\'' +
				", seoKeyword='" + seoKeyword + '\'' +
				", seoDescribe='" + seoDescribe + '\'' +
				", website='" + website + '\'' +
				", langType='" + langType + '\'' +
				", langName='" + langName + '\'' +
				", langId='" + langId + '\'' +
				", sort=" + sort +
				", isStick=" + isStick +
				", content='" + content + '\'' +
				", isOutLink=" + isOutLink +
				", outLink='" + outLink + '\'' +
				", titleImage='" + titleImage + '\'' +
				", shortContent='" + shortContent + '\'' +
				", writer='" + writer + '\'' +
				", source='" + source + '\'' +
				", hits=" + hits +
				'}';
	}
}
