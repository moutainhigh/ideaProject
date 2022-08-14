/**
 * Copyright:   互融云
 * @author:      denghf
 * @version:     V1.0 
 * @Date:        2018-01-06 20:13:11 
 */
package hry.otc.remote.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

@ApiModel(value = "OTC申诉实体类")
public class AppAppealRemote implements Serializable{

	@ApiModelProperty(value = "买方诉求")
	private String appeal;

	@ApiModelProperty(value = "买方详细描述")
	private String content;

	@ApiModelProperty(value = "买房附件url")
	private String thingUrl;

	@ApiModelProperty(value = "交易订单号")
	private String transactionNum;

	@ApiModelProperty(value = "平台意见")
	private String platFormContent;

	@ApiModelProperty(value = "买方附件集合")
	private List<String> imgUrl;

	@ApiModelProperty(value = "卖方诉求")
	private String appealSell;

	@ApiModelProperty(value = "卖方 - 详细描述")
	private String contentSell;

	@ApiModelProperty(value = "卖方 - 附件url")
	private String thingUrlSell;

	@ApiModelProperty(value = "卖方附件集合")
	private List<String> imgUrlSell;

	@ApiModelProperty(value = "卖方是否删除")
	private Integer sellIsDeleted; //卖方是否删除

	@ApiModelProperty(value = "买方是否删除")
	private Integer buyIsDeleted; //买方是否删除
	

	public String getAppealSell() {
		return appealSell;
	}

	public void setAppealSell(String appealSell) {
		this.appealSell = appealSell;
	}

	public String getContentSell() {
		return contentSell;
	}

	public void setContentSell(String contentSell) {
		this.contentSell = contentSell;
	}

	public String getThingUrlSell() {
		return thingUrlSell;
	}

	public void setThingUrlSell(String thingUrlSell) {
		this.thingUrlSell = thingUrlSell;
	}

	public List<String> getImgUrlSell() {
		return imgUrlSell;
	}

	public void setImgUrlSell(List<String> imgUrlSell) {
		this.imgUrlSell = imgUrlSell;
	}

	public List<String> getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(List<String> imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getPlatFormContent() {
		return platFormContent;
	}

	public void setPlatFormContent(String platFormContent) {
		this.platFormContent = platFormContent;
	}

	public String getAppeal() {
		return appeal;
	}

	public void setAppeal(String appeal) {
		this.appeal = appeal;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getThingUrl() {
		return thingUrl;
	}

	public void setThingUrl(String thingUrl) {
		this.thingUrl = thingUrl;
	}

	public String getTransactionNum() {
		return transactionNum;
	}

	public void setTransactionNum(String transactionNum) {
		this.transactionNum = transactionNum;
	}

	public Integer getSellIsDeleted() {
		return sellIsDeleted;
	}

	public void setSellIsDeleted(Integer sellIsDeleted) {
		this.sellIsDeleted = sellIsDeleted;
	}

	public Integer getBuyIsDeleted() {
		return buyIsDeleted;
	}

	public void setBuyIsDeleted(Integer buyIsDeleted) {
		this.buyIsDeleted = buyIsDeleted;
	}
	
	
}
