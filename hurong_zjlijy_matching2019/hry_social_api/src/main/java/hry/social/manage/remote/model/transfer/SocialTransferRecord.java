/**
 * Copyright:
 * @author:      javalx
 * @version:     V1.0
 * @Date:        2019-06-12 16:51:43
 */
package hry.social.manage.remote.model.transfer;


import hry.bean.BaseModel;

import java.math.BigDecimal;

import javax.persistence.*;

/**
 * <p> SocialTransferRecord </p>
 * @author:         javalx
 * @Date :          2019-06-12 16:51:43
 */
@Table(name="social_transfer_record")
public class SocialTransferRecord extends BaseModel {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //序号

	@Column(name= "orderNum")
	private String orderNum;  //交易ID

	@Column(name= "formId")
	private Long formId;  //付款方ID

	@Column(name= "coinCode")
	private String coinCode;  //转账币种

	@Column(name= "num")
	private BigDecimal num;  //转账数量

	@Column(name= "toId")
	private Long toId;  //收款方ID

	@Column(name= "serviceFee")
	private BigDecimal serviceFee;  //手续费

	@Column(name= "remark")
	private String remark;  //备注

	@Transient
	private String fromEmail;  //转出人邮箱

	@Transient
	private String fromMobile;  //转出人手机

	@Transient
	private String toEmail;  //转入人邮箱

	@Transient
	private String toMobile;  //转入人手机

	@Transient
	private String fromName;  //转入昵称

	@Transient
	private String toName;  //转出昵称

	public BigDecimal getServiceFee() {
		return serviceFee;
	}

	public void setServiceFee(BigDecimal serviceFee) {
		this.serviceFee = serviceFee;
	}

	public String getFromName() {
		return fromName;
	}

	public void setFromName(String fromName) {
		this.fromName = fromName;
	}

	public String getToName() {
		return toName;
	}

	public void setToName(String toName) {
		this.toName = toName;
	}

	public String getFromEmail() {
		return fromEmail;
	}

	public void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
	}

	public String getFromMobile() {
		return fromMobile;
	}

	public void setFromMobile(String fromMobile) {
		this.fromMobile = fromMobile;
	}

	public String getToEmail() {
		return toEmail;
	}

	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}

	public String getToMobile() {
		return toMobile;
	}

	public void setToMobile(String toMobile) {
		this.toMobile = toMobile;
	}

	/**
	 * <p>序号</p>
	 * @author:  javalx
	 * @return:  Long
	 * @Date :   2019-06-12 16:51:43
	 */
	public Long getId() {
		return id;
	}

	/**
	 * <p>序号</p>
	 * @author:  javalx
	 * @param:   @param id
	 * @return:  void
	 * @Date :   2019-06-12 16:51:43
	 */
	public void setId(Long id) {
		this.id = id;
	}


	/**
	 * <p>交易ID</p>
	 * @author:  javalx
	 * @return:  String
	 * @Date :   2019-06-12 16:51:43
	 */
	public String getOrderNum() {
		return orderNum;
	}

	/**
	 * <p>交易ID</p>
	 * @author:  javalx
	 * @param:   @param orderNum
	 * @return:  void
	 * @Date :   2019-06-12 16:51:43
	 */
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}


	/**
	 * <p>付款方ID</p>
	 * @author:  javalx
	 * @return:  Long
	 * @Date :   2019-06-12 16:51:43
	 */
	public Long getFormId() {
		return formId;
	}

	/**
	 * <p>付款方ID</p>
	 * @author:  javalx
	 * @param:   @param formId
	 * @return:  void
	 * @Date :   2019-06-12 16:51:43
	 */
	public void setFormId(Long formId) {
		this.formId = formId;
	}


	/**
	 * <p>转账币种</p>
	 * @author:  javalx
	 * @return:  String
	 * @Date :   2019-06-12 16:51:43
	 */
	public String getCoinCode() {
		return coinCode;
	}

	/**
	 * <p>转账币种</p>
	 * @author:  javalx
	 * @param:   @param coinCode
	 * @return:  void
	 * @Date :   2019-06-12 16:51:43
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}


	/**
	 * <p>转账数量</p>
	 * @author:  javalx
	 * @return:  BigDecimal
	 * @Date :   2019-06-12 16:51:43
	 */
	public BigDecimal getNum() {
		return num;
	}

	/**
	 * <p>转账数量</p>
	 * @author:  javalx
	 * @param:   @param num
	 * @return:  void
	 * @Date :   2019-06-12 16:51:43
	 */
	public void setNum(BigDecimal num) {
		this.num = num;
	}


	/**
	 * <p>收款方ID</p>
	 * @author:  javalx
	 * @return:  Long
	 * @Date :   2019-06-12 16:51:43
	 */
	public Long getToId() {
		return toId;
	}

	/**
	 * <p>收款方ID</p>
	 * @author:  javalx
	 * @param:   @param toId
	 * @return:  void
	 * @Date :   2019-06-12 16:51:43
	 */
	public void setToId(Long toId) {
		this.toId = toId;
	}


	/**
	 * <p>备注</p>
	 * @author:  javalx
	 * @return:  String
	 * @Date :   2019-06-12 16:51:43
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * <p>备注</p>
	 * @author:  javalx
	 * @param:   @param remark
	 * @return:  void
	 * @Date :   2019-06-12 16:51:43
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}



}
