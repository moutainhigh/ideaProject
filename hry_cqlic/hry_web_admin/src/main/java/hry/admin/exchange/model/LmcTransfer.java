package hry.admin.exchange.model;

import java.io.Serializable;

/**
 * 邻萌宝转账回调参数接收
 * <p>
 * TODO
 * </p>
 * @author: Shangxl
 * @Date : 2017年7月28日 下午6:55:42
 */
public class LmcTransfer implements Serializable{
	//app_key
	private String app_key;
	// 验证秘钥
	private String auth_code;
	// 本次转账交易ID
	private String transfer_id;
	// 交易处理结果 1:等待,2:成功,其他:失败
	private String status;
	// 如果系统外转账会有txid可在链上查询
	private String txid;
	// 应用请求时间戳
	private String request_time;
	// 失败原因
	private String reason;
	// 转账币种
	private String symbol;
	// 转出钱包地址
	private String from;
	// 转入钱包地址
	private String to;
	// 转账币数
	private String coins;
	// 手续费
	private String fee;
	// I 转入，O 转出
	private String transfer_type;
	//用户open平台的id（电话号码）
	private String account_id;
	//转账参数
	//可自定义的转账类型，默认为1。系统外转入为-1。
	private String type;
	//验证类型
	private String validation_type;
	//系统外大额转账及其他需要验证码
	private String validation_code;
	//验证电话号
	private String validation_phone;
	
	//获取账单总和
	//开始时间
	private String start_time;
	//结束时间
	private String end_time;
	
	
	public String getApp_key() {
		return app_key;
	}

	public void setApp_key(String app_key) {
		this.app_key = app_key;
	}

	public String getAuth_code() {
		return auth_code;
	}

	public void setAuth_code(String auth_code) {
		this.auth_code = auth_code;
	}

	public String getTransfer_id() {
		return transfer_id;
	}

	public void setTransfer_id(String transfer_id) {
		this.transfer_id = transfer_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTxid() {
		return txid;
	}

	public void setTxid(String txid) {
		this.txid = txid;
	}

	public String getRequest_time() {
		return request_time;
	}

	public void setRequest_time(String request_time) {
		this.request_time = request_time;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getCoins() {
		return coins;
	}

	public void setCoins(String coins) {
		this.coins = coins;
	}

	public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}

	public String getTransfer_type() {
		return transfer_type;
	}

	public void setTransfer_type(String transfer_type) {
		this.transfer_type = transfer_type;
	}

	public String getAccount_id() {
		return account_id;
	}

	public void setAccount_id(String account_id) {
		this.account_id = account_id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValidation_type() {
		return validation_type;
	}

	public void setValidation_type(String validation_type) {
		this.validation_type = validation_type;
	}

	public String getValidation_code() {
		return validation_code;
	}

	public void setValidation_code(String validation_code) {
		this.validation_code = validation_code;
	}

	public String getValidation_phone() {
		return validation_phone;
	}

	public void setValidation_phone(String validation_phone) {
		this.validation_phone = validation_phone;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
}
