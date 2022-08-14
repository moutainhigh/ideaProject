/**
 * Copyright:   
 * @author:      luyue
 * @version:     V1.0 
 * @Date:        2018-11-27 11:56:50 
 */
package hry.mall.order.model;
import hry.bean.BaseModel;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p> EvaluateGoods </p>
 * @author:         luyue
 * @Date :          2018-11-27 11:56:50  
 */
@Table(name="shop_evaluate_goods")
public class EvaluateGoods extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //主键id
	
	@Column(name= "orderId")
	private Long orderId;  //订单id
	
	@Column(name= "orderSn")
	private String orderSn;  //订单编号
	
	@Column(name= "orderGoodsId")
	private Long orderGoodsId;  //订单商品表id
	
	@Column(name= "goodsId")
	private Long goodsId;  //商品id
	
	@Column(name= "goodsName")
	private String goodsName;  //商品名称
	
	@Column(name= "gcId")
	private Long gcId;  //商品分类id
	
	@Column(name= "gcName")
	private String gcName;  //商品分类名称
	
	@Column(name= "goodsPrice")
	private BigDecimal goodsPrice;  //商品价格
	
	@Column(name= "memberId")
	private Long memberId;  //前台账号id
	
	@Column(name= "userName")
	private String userName;  //昵称
	
	@Column(name= "scores")
	private Integer scores;  //1-5分
	
	@Column(name= "content")
	private String content;  //信誉评价内容
	
	@Column(name= "isanonymous")
	private Integer isanonymous;  //是否匿名评价，1是0否
	
	@Column(name= "state")
	private Integer state;  //评价信息的状态 0为正常 1为禁止显示
	
	@Column(name= "remark")
	private String remark;  //管理员对评价的处理备注
	
	@Column(name= "specInfo")
	private String specInfo;  //规格描述
	
	@Column(name= "image")
	private String image;  //晒单图片
	
	@Column(name= "isDel")
	private Integer isDel;  //0:未删除;1.已删除
	
	@Column(name= "addcontent")
	private String addcontent;  //追加评论内容
	
	@Column(name= "addimage")
	private String addimage;  //追加评论图片
	
	@Column(name= "addtime")
	private Date addtime;  //追加评论时间
	
	@Column(name= "replyContent")
	private String replyContent;  //商家回复内容
	
	@Column(name= "replyTime")
	private Date replyTime;  //商家回复时间
	
	@Column(name= "replyStoreId")
	private Long replyStoreId;  //回复人账号
	
	@Column(name= "replyStorename")
	private String replyStorename;  //回复人名称
	
	@Column(name= "isReply")
	private Integer isReply;  //是否恢复 1是0否
	
	@Column(name= "isWarn")
	private Integer isWarn;  //是否提醒 1是0否
	
	@Column(name= "ipAddress")
	private String ipAddress;  //IP地址

	@Transient
	private String headPortrait; // 评论者头像 注：该字段取 app_person_info中字段

	@Transient
	private OrderGoods orderGoods;

	public OrderGoods getOrderGoods() {
		return orderGoods;
	}

	public void setOrderGoods(OrderGoods orderGoods) {
		this.orderGoods = orderGoods;
	}

	public String getHeadPortrait() {
		return headPortrait;
	}

	public void setHeadPortrait(String headPortrait) {
		this.headPortrait = headPortrait;
	}

	/**
	 * <p>主键id</p>
	 * @author:  luyue
	 * @return:  Long 
	 * @Date :   2018-11-27 11:56:50    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>主键id</p>
	 * @author:  luyue
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-11-27 11:56:50   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>订单id</p>
	 * @author:  luyue
	 * @return:  Long 
	 * @Date :   2018-11-27 11:56:50    
	 */
	public Long getOrderId() {
		return orderId;
	}
	
	/**
	 * <p>订单id</p>
	 * @author:  luyue
	 * @param:   @param orderId
	 * @return:  void 
	 * @Date :   2018-11-27 11:56:50   
	 */
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	
	
	/**
	 * <p>订单编号</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2018-11-27 11:56:50    
	 */
	public String getOrderSn() {
		return orderSn;
	}
	
	/**
	 * <p>订单编号</p>
	 * @author:  luyue
	 * @param:   @param orderSn
	 * @return:  void 
	 * @Date :   2018-11-27 11:56:50   
	 */
	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}
	
	
	/**
	 * <p>订单商品表id</p>
	 * @author:  luyue
	 * @return:  Long 
	 * @Date :   2018-11-27 11:56:50    
	 */
	public Long getOrderGoodsId() {
		return orderGoodsId;
	}
	
	/**
	 * <p>订单商品表id</p>
	 * @author:  luyue
	 * @param:   @param orderGoodsId
	 * @return:  void 
	 * @Date :   2018-11-27 11:56:50   
	 */
	public void setOrderGoodsId(Long orderGoodsId) {
		this.orderGoodsId = orderGoodsId;
	}
	
	
	/**
	 * <p>商品id</p>
	 * @author:  luyue
	 * @return:  Long 
	 * @Date :   2018-11-27 11:56:50    
	 */
	public Long getGoodsId() {
		return goodsId;
	}
	
	/**
	 * <p>商品id</p>
	 * @author:  luyue
	 * @param:   @param goodsId
	 * @return:  void 
	 * @Date :   2018-11-27 11:56:50   
	 */
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}
	
	
	/**
	 * <p>商品名称</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2018-11-27 11:56:50    
	 */
	public String getGoodsName() {
		return goodsName;
	}
	
	/**
	 * <p>商品名称</p>
	 * @author:  luyue
	 * @param:   @param goodsName
	 * @return:  void 
	 * @Date :   2018-11-27 11:56:50   
	 */
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	
	
	/**
	 * <p>商品分类id</p>
	 * @author:  luyue
	 * @return:  Long 
	 * @Date :   2018-11-27 11:56:50    
	 */
	public Long getGcId() {
		return gcId;
	}
	
	/**
	 * <p>商品分类id</p>
	 * @author:  luyue
	 * @param:   @param gcId
	 * @return:  void 
	 * @Date :   2018-11-27 11:56:50   
	 */
	public void setGcId(Long gcId) {
		this.gcId = gcId;
	}
	
	
	/**
	 * <p>商品分类名称</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2018-11-27 11:56:50    
	 */
	public String getGcName() {
		return gcName;
	}
	
	/**
	 * <p>商品分类名称</p>
	 * @author:  luyue
	 * @param:   @param gcName
	 * @return:  void 
	 * @Date :   2018-11-27 11:56:50   
	 */
	public void setGcName(String gcName) {
		this.gcName = gcName;
	}
	
	
	/**
	 * <p>商品价格</p>
	 * @author:  luyue
	 * @return:  BigDecimal 
	 * @Date :   2018-11-27 11:56:50    
	 */
	public BigDecimal getGoodsPrice() {
		return goodsPrice;
	}
	
	/**
	 * <p>商品价格</p>
	 * @author:  luyue
	 * @param:   @param goodsPrice
	 * @return:  void 
	 * @Date :   2018-11-27 11:56:50   
	 */
	public void setGoodsPrice(BigDecimal goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	
	
	/**
	 * <p>前台账号id</p>
	 * @author:  luyue
	 * @return:  Long 
	 * @Date :   2018-11-27 11:56:50    
	 */
	public Long getMemberId() {
		return memberId;
	}
	
	/**
	 * <p>前台账号id</p>
	 * @author:  luyue
	 * @param:   @param memberId
	 * @return:  void 
	 * @Date :   2018-11-27 11:56:50   
	 */
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	
	
	/**
	 * <p>昵称</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2018-11-27 11:56:50    
	 */
	public String getUserName() {
		return userName;
	}
	
	/**
	 * <p>昵称</p>
	 * @author:  luyue
	 * @param:   @param userName
	 * @return:  void 
	 * @Date :   2018-11-27 11:56:50   
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	/**
	 * <p>1-5分</p>
	 * @author:  luyue
	 * @return:  Integer 
	 * @Date :   2018-11-27 11:56:50    
	 */
	public Integer getScores() {
		return scores;
	}
	
	/**
	 * <p>1-5分</p>
	 * @author:  luyue
	 * @param:   @param scores
	 * @return:  void 
	 * @Date :   2018-11-27 11:56:50   
	 */
	public void setScores(Integer scores) {
		this.scores = scores;
	}
	
	
	/**
	 * <p>信誉评价内容</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2018-11-27 11:56:50    
	 */
	public String getContent() {
		return content;
	}
	
	/**
	 * <p>信誉评价内容</p>
	 * @author:  luyue
	 * @param:   @param content
	 * @return:  void 
	 * @Date :   2018-11-27 11:56:50   
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	
	/**
	 * <p>是否匿名评价，1是0否</p>
	 * @author:  luyue
	 * @return:  Integer 
	 * @Date :   2018-11-27 11:56:50    
	 */
	public Integer getIsanonymous() {
		return isanonymous;
	}
	
	/**
	 * <p>是否匿名评价，1是0否</p>
	 * @author:  luyue
	 * @param:   @param isanonymous
	 * @return:  void 
	 * @Date :   2018-11-27 11:56:50   
	 */
	public void setIsanonymous(Integer isanonymous) {
		this.isanonymous = isanonymous;
	}
	
	
	/**
	 * <p>评价信息的状态 0为正常 1为禁止显示</p>
	 * @author:  luyue
	 * @return:  Integer 
	 * @Date :   2018-11-27 11:56:50    
	 */
	public Integer getState() {
		return state;
	}
	
	/**
	 * <p>评价信息的状态 0为正常 1为禁止显示</p>
	 * @author:  luyue
	 * @param:   @param state
	 * @return:  void 
	 * @Date :   2018-11-27 11:56:50   
	 */
	public void setState(Integer state) {
		this.state = state;
	}
	
	
	/**
	 * <p>管理员对评价的处理备注</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2018-11-27 11:56:50    
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * <p>管理员对评价的处理备注</p>
	 * @author:  luyue
	 * @param:   @param remark
	 * @return:  void 
	 * @Date :   2018-11-27 11:56:50   
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getSpecInfo() {
		return specInfo;
	}

	public void setSpecInfo(String specInfo) {
		this.specInfo = specInfo;
	}

	/**
	 * <p>晒单图片</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2018-11-27 11:56:50    
	 */
	public String getImage() {
		return image;
	}
	
	/**
	 * <p>晒单图片</p>
	 * @author:  luyue
	 * @param:   @param image
	 * @return:  void 
	 * @Date :   2018-11-27 11:56:50   
	 */
	public void setImage(String image) {
		this.image = image;
	}
	
	
	/**
	 * <p>0:未删除;1.已删除</p>
	 * @author:  luyue
	 * @return:  Integer 
	 * @Date :   2018-11-27 11:56:50    
	 */
	public Integer getIsDel() {
		return isDel;
	}
	
	/**
	 * <p>0:未删除;1.已删除</p>
	 * @author:  luyue
	 * @param:   @param isDel
	 * @return:  void 
	 * @Date :   2018-11-27 11:56:50   
	 */
	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}
	
	
	/**
	 * <p>追加评论内容</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2018-11-27 11:56:50    
	 */
	public String getAddcontent() {
		return addcontent;
	}
	
	/**
	 * <p>追加评论内容</p>
	 * @author:  luyue
	 * @param:   @param addcontent
	 * @return:  void 
	 * @Date :   2018-11-27 11:56:50   
	 */
	public void setAddcontent(String addcontent) {
		this.addcontent = addcontent;
	}
	
	
	/**
	 * <p>追加评论图片</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2018-11-27 11:56:50    
	 */
	public String getAddimage() {
		return addimage;
	}
	
	/**
	 * <p>追加评论图片</p>
	 * @author:  luyue
	 * @param:   @param addimage
	 * @return:  void 
	 * @Date :   2018-11-27 11:56:50   
	 */
	public void setAddimage(String addimage) {
		this.addimage = addimage;
	}
	
	
	/**
	 * <p>追加评论时间</p>
	 * @author:  luyue
	 * @return:  Date 
	 * @Date :   2018-11-27 11:56:50    
	 */
	public Date getAddtime() {
		return addtime;
	}
	
	/**
	 * <p>追加评论时间</p>
	 * @author:  luyue
	 * @param:   @param addtime
	 * @return:  void 
	 * @Date :   2018-11-27 11:56:50   
	 */
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
	
	
	/**
	 * <p>商家回复内容</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2018-11-27 11:56:50    
	 */
	public String getReplyContent() {
		return replyContent;
	}
	
	/**
	 * <p>商家回复内容</p>
	 * @author:  luyue
	 * @param:   @param replyContent
	 * @return:  void 
	 * @Date :   2018-11-27 11:56:50   
	 */
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	
	
	/**
	 * <p>商家回复时间</p>
	 * @author:  luyue
	 * @return:  Date 
	 * @Date :   2018-11-27 11:56:50    
	 */
	public Date getReplyTime() {
		return replyTime;
	}
	
	/**
	 * <p>商家回复时间</p>
	 * @author:  luyue
	 * @param:   @param replyTime
	 * @return:  void 
	 * @Date :   2018-11-27 11:56:50   
	 */
	public void setReplyTime(Date replyTime) {
		this.replyTime = replyTime;
	}
	
	
	/**
	 * <p>回复人账号</p>
	 * @author:  luyue
	 * @return:  Long 
	 * @Date :   2018-11-27 11:56:50    
	 */
	public Long getReplyStoreId() {
		return replyStoreId;
	}
	
	/**
	 * <p>回复人账号</p>
	 * @author:  luyue
	 * @param:   @param replyStoreId
	 * @return:  void 
	 * @Date :   2018-11-27 11:56:50   
	 */
	public void setReplyStoreId(Long replyStoreId) {
		this.replyStoreId = replyStoreId;
	}
	
	
	/**
	 * <p>回复人名称</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2018-11-27 11:56:50    
	 */
	public String getReplyStorename() {
		return replyStorename;
	}
	
	/**
	 * <p>回复人名称</p>
	 * @author:  luyue
	 * @param:   @param replyStorename
	 * @return:  void 
	 * @Date :   2018-11-27 11:56:50   
	 */
	public void setReplyStorename(String replyStorename) {
		this.replyStorename = replyStorename;
	}
	
	
	/**
	 * <p>是否恢复 1是0否</p>
	 * @author:  luyue
	 * @return:  Integer 
	 * @Date :   2018-11-27 11:56:50    
	 */
	public Integer getIsReply() {
		return isReply;
	}
	
	/**
	 * <p>是否恢复 1是0否</p>
	 * @author:  luyue
	 * @param:   @param isReply
	 * @return:  void 
	 * @Date :   2018-11-27 11:56:50   
	 */
	public void setIsReply(Integer isReply) {
		this.isReply = isReply;
	}
	
	
	/**
	 * <p>是否提醒 1是0否</p>
	 * @author:  luyue
	 * @return:  Integer 
	 * @Date :   2018-11-27 11:56:50    
	 */
	public Integer getIsWarn() {
		return isWarn;
	}
	
	/**
	 * <p>是否提醒 1是0否</p>
	 * @author:  luyue
	 * @param:   @param isWarn
	 * @return:  void 
	 * @Date :   2018-11-27 11:56:50   
	 */
	public void setIsWarn(Integer isWarn) {
		this.isWarn = isWarn;
	}
	
	
	/**
	 * <p>IP地址</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2018-11-27 11:56:50    
	 */
	public String getIpAddress() {
		return ipAddress;
	}
	
	/**
	 * <p>IP地址</p>
	 * @author:  luyue
	 * @param:   @param ipAddress
	 * @return:  void 
	 * @Date :   2018-11-27 11:56:50   
	 */
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	
	

}
