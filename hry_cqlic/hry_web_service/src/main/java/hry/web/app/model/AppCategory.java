package hry.web.app.model;

import hry.core.mvc.model.BaseModel;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static javax.persistence.GenerationType.IDENTITY;

@SuppressWarnings("serial")
@Table(name = "app_artic_category")
public class AppCategory extends BaseModel {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	// 父类别的id
	@Column(name = "preateId")
	private Long preateId;
	// 父类别名称
	@Column(name = "preateName")
	private String preateName;
	// 类别名称
	@Column(name = "name")
	private String name;
	// 文章类型
	@Column(name = "type")
	private String type;
	// soe 标识
	@Column(name = "seoFication")
	private String seoFication;
	// 页脚Url
	@Column(name = "footerUrl")
	private String footerUrl;
	// 状态 (0表示有效 1 表示无效 2 表示已删除)
	@Column(name = "state")
	private Integer state;
	// 排序字段
	@Column(name = "isShow")
	private Integer isShow;
	// 排序字段
	@Column(name = "sort")
	private Integer sort;

	@Column(name = "describes")
	private String describes;
	
	// 区分中国站(cn表示中国站  en表示国际站)
	@Column(name = "website")
	private String website;

	// 是否单页面内容容器,0-文章列表，1-单页面, 2-父节点
	@Column(name = "isPage")
	private Integer isPage;

	public Integer getIsPage () {
		return isPage;
	}

	public void setIsPage (Integer isPage) {
		this.isPage = isPage;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPreateId() {
		return preateId;
	}

	public String getDescribes() {
		return describes;
	}

	public void setDescribes(String describes) {
		this.describes = describes;
	}

	public String getFooterUrl() {
		return footerUrl;
	}

	public void setFooterUrl(String footerUrl) {
		this.footerUrl = footerUrl;
	}

	public Integer getIsShow() {
		return isShow;
	}

	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}

	public void setPreateId(Long preateId) {
		this.preateId = preateId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPreateName() {
		return preateName;
	}

	public void setPreateName(String preateName) {
		this.preateName = preateName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSeoFication() {
		return seoFication;
	}

	public void setSeoFication(String seoFication) {
		this.seoFication = seoFication;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public AppCategory(Long id, Long preateId, String name, String type,
			String seoFication, Integer state, Integer sort, String describes) {
		super();
		this.id = id;
		this.preateId = preateId;
		this.name = name;
		this.type = type;
		this.seoFication = seoFication;
		this.state = state;
		this.sort = sort;
		this.describes = describes;
	}

	public AppCategory() {
		super();
	}

	@Override
	public String toString() {
		return "AppCategory [id=" + id + ", preateId=" + preateId
				+ ", preateName=" + preateName + ", name=" + name + ", type="
				+ type + ", seoFication=" + seoFication + ", footerUrl="
				+ footerUrl + ", state=" + state + ", isShow=" + isShow
				+ ", sort=" + sort + ", describes=" + describes + "]";
	}

	
}
