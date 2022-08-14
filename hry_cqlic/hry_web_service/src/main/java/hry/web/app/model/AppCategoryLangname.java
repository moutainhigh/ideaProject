package hry.web.app.model;

import hry.core.mvc.model.BaseModel;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static javax.persistence.GenerationType.IDENTITY;

@SuppressWarnings("serial")
@Table(name = "app_article_category_langname")
public class AppCategoryLangname extends BaseModel {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	// 文章类别表的id
	@Column(name = "categoryId")
	private Long categoryId;

	// 数据字典“系统语种”，例如：英文
	@Column(name = "langType")
	private String langType;

	// 指定语种下的文章栏目名称
	@Column(name = "langName")
	private String langName;

	// 如果是单页面栏目，即isPage为1，用以存放页面内容。如果isPage为0，字段为空
	@Column(name = "langContent")
	private String langContent;

	// 语种对应的字典值
	@Column(name = "dicKey")
	private String dicKey;

	@Column(name = "dicId")
	private Long dicId;

	public Long getDicId () {
		return dicId;
	}

	public void setDicId (Long dicId) {
		this.dicId = dicId;
	}

	public String getDicKey () {
		return dicKey;
	}

	public void setDicKey (String dicKey) {
		this.dicKey = dicKey;
	}

	public Long getId () {
		return id;
	}

	public void setId (Long id) {
		this.id = id;
	}

	public Long getCategoryId () {
		return categoryId;
	}

	public void setCategoryId (Long categoryId) {
		this.categoryId = categoryId;
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

	public String getLangContent () {
		return langContent;
	}

	public void setLangContent (String langContent) {
		this.langContent = langContent;
	}

	public AppCategoryLangname () {
		super();
	}

	public AppCategoryLangname (Long categoryId, String langType, String langName, String langContent, String dicKey, Long dicId) {
		this.categoryId = categoryId;
		this.langType = langType;
		this.langName = langName;
		this.langContent = langContent;
		this.dicKey = dicKey;
		this.dicId = dicId;
	}

	@Override
	public String toString () {
		return "AppCategoryLangname{" +
				"id=" + id +
				", categoryId=" + categoryId +
				", langType='" + langType + '\'' +
				", dicId='" + dicId + '\'' +
				", dicKey='" + dicKey + '\'' +
				", langName='" + langName + '\'' +
				", langContent='" + langContent + '\'' +
				'}';
	}
}
