/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年6月27日 上午11:34:23
 */
package hry.exchange.product.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import hry.core.mvc.model.BaseModel;

/**
 * <p> TODO</p>
 * @author:         Wu Shuiming
 * @Date :          2016年6月27日 上午11:34:23 
 */


@SuppressWarnings("serial")
public class ProductReview extends BaseModel {
	
	// 主键id
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	// 评论星级(1-5分 分别代表一颗星 -- 5颗星)
	@Column(name = "reviewRank")
	private Integer reviewRank;
	
	// 评论技术(1-5分 分别代表一颗星 -- 5颗星)
	@Column(name = "reviewSkill")
	private Integer reviewSkill;
	
	// 评论应用(1-5分 分别代表一颗星 -- 5颗星)
	@Column(name = "reviewApply")
	private Integer reviewApply;
	
	// 评论前景(1-5分 分别代表一颗星 -- 5颗星)
	@Column(name = "reviewVista")
	private Integer reviewVista;
	
	// 评论内容
	@Column(name = "reviewContent")
	private String reviewContent;
	
	// '产品id'
	@Column(name = "productId")
	private Long productId;

	// '用户id'
	@Column(name = "customerId")
	private Long customerId;
	
	// '用户姓名'
	@Column(name = "customerName")
	private String customerName;
	
	@Column(name = "trueName")
	private String trueName;
	
	// '产品名称'
	@Column(name = "productName")
	private String productName;
	
	// '赞的数量'
	@Column(name = "praise")
	private Integer praise;
	
	// '批评数量'
	@Column(name = "criticize")
	private Integer criticize;
	
	
	// '状态( 1 表示已通过审核    0 表示未审核   3表示已删除)'
	@Column(name = "status")
	private Integer status;
	
	
	// 类型(类型表示  1 表示币种评论    2表示 。。。。)
	@Column(name = "type")
	private Integer type;


	public ProductReview() {
		super();
	}
	
	public String getTrueName() {
		return trueName;
	}


	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}


	public ProductReview(Long id, Integer reviewRank, Integer reviewSkill,
			Integer reviewApply, Integer reviewVista, String reviewContent,
			Long productId, Long customerId, String customerName,
			String productName, Integer praise, Integer criticize,
			Integer status, Integer type) {
		super();
		this.id = id;
		this.reviewRank = reviewRank;
		this.reviewSkill = reviewSkill;
		this.reviewApply = reviewApply;
		this.reviewVista = reviewVista;
		this.reviewContent = reviewContent;
		this.productId = productId;
		this.customerId = customerId;
		this.customerName = customerName;
		this.productName = productName;
		this.praise = praise;
		this.criticize = criticize;
		this.status = status;
		this.type = type;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Integer getReviewRank() {
		return reviewRank;
	}


	public void setReviewRank(Integer reviewRank) {
		this.reviewRank = reviewRank;
	}


	public Integer getReviewSkill() {
		return reviewSkill;
	}


	public void setReviewSkill(Integer reviewSkill) {
		this.reviewSkill = reviewSkill;
	}


	public Integer getReviewApply() {
		return reviewApply;
	}


	public void setReviewApply(Integer reviewApply) {
		this.reviewApply = reviewApply;
	}


	public Integer getReviewVista() {
		return reviewVista;
	}


	public void setReviewVista(Integer reviewVista) {
		this.reviewVista = reviewVista;
	}


	public String getReviewContent() {
		return reviewContent;
	}


	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}


	public Long getProductId() {
		return productId;
	}


	public void setProductId(Long productId) {
		this.productId = productId;
	}


	public Long getCustomerId() {
		return customerId;
	}


	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}


	public String getCustomerName() {
		return customerName;
	}


	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public Integer getPraise() {
		return praise;
	}


	public void setPraise(Integer praise) {
		this.praise = praise;
	}


	public Integer getCriticize() {
		return criticize;
	}


	public void setCriticize(Integer criticize) {
		this.criticize = criticize;
	}


	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}


	public Integer getType() {
		return type;
	}


	public void setType(Integer type) {
		this.type = type;
	}


	@Override
	public String toString() {
		return "ProductReview [id=" + id + ", reviewRank=" + reviewRank
				+ ", reviewSkill=" + reviewSkill + ", reviewApply="
				+ reviewApply + ", reviewVista=" + reviewVista
				+ ", reviewContent=" + reviewContent + ", productId="
				+ productId + ", customerId=" + customerId + ", customerName="
				+ customerName + ", productName=" + productName + ", praise="
				+ praise + ", criticize=" + criticize + ", status=" + status
				+ ", type=" + type + "]";
	}
	


}
