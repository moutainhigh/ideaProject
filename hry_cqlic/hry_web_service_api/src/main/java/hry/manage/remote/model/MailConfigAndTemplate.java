/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      sunyujie
 * @version:     V1.0 
 * @Date:        2018-06-04 14:36:50 
 */
package hry.manage.remote.model;


import hry.bean.BaseModel;

import java.io.Serializable;

/**
 * <p> MailConfig </p>
 * @author:         sunyujie
 * @Date :          2018-06-04 14:36:50  
 */
public class MailConfigAndTemplate extends BaseModel implements Serializable {
	

	private Long id;  //id

	private String tempName;  //模板名称

	private String tempKey;  //模板key

	private String tempContent;  //模板内容

	private String remark;  //模板备注

	private Integer tempStatus;  //启用状态   1开启  0 关闭

	private Long mailConfigId;  //邮箱账号id

	private Integer language;//语言 0中文 1英文 不使用了

	private String port;  //端口
	
	private String host;  //服务器
	
	private String protocol;  //协议
	
	private Integer auth;  //开启认证   1开启  0未开启
	
	private String emailUser;  //发送用户
	
	private String agreedUser;  //认证用户
	
	private String agreedPwd;  //认证密码
	
	private String customName;  //自定义发件名称
	
	private Integer sslflag;  //是否开启了ssl验证  1开启 0未开启
	
	private Integer starttls;  //是否开启tls   1开启  0未开启
	
	private Integer status; //启用状态     1开启   0关闭

	private String prefix;  //邮箱标题前缀

	private String languageDic;  //语言


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTempName() {
		return tempName;
	}

	public void setTempName(String tempName) {
		this.tempName = tempName;
	}

	public String getTempKey() {
		return tempKey;
	}

	public void setTempKey(String tempKey) {
		this.tempKey = tempKey;
	}

	public String getTempContent() {
		return tempContent;
	}

	public void setTempContent(String tempContent) {
		this.tempContent = tempContent;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getTempStatus() {
		return tempStatus;
	}

	public void setTempStatus(Integer tempStatus) {
		this.tempStatus = tempStatus;
	}

	public Long getMailConfigId() {
		return mailConfigId;
	}

	public void setMailConfigId(Long mailConfigId) {
		this.mailConfigId = mailConfigId;
	}

	public Integer getLanguage() {
		return language;
	}

	public void setLanguage(Integer language) {
		this.language = language;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public Integer getAuth() {
		return auth;
	}

	public void setAuth(Integer auth) {
		this.auth = auth;
	}

	public String getEmailUser() {
		return emailUser;
	}

	public void setEmailUser(String emailUser) {
		this.emailUser = emailUser;
	}

	public String getAgreedUser() {
		return agreedUser;
	}

	public void setAgreedUser(String agreedUser) {
		this.agreedUser = agreedUser;
	}

	public String getAgreedPwd() {
		return agreedPwd;
	}

	public void setAgreedPwd(String agreedPwd) {
		this.agreedPwd = agreedPwd;
	}

	public String getCustomName() {
		return customName;
	}

	public void setCustomName(String customName) {
		this.customName = customName;
	}

	public Integer getSslflag() {
		return sslflag;
	}

	public void setSslflag(Integer sslflag) {
		this.sslflag = sslflag;
	}

	public Integer getStarttls() {
		return starttls;
	}

	public void setStarttls(Integer starttls) {
		this.starttls = starttls;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getLanguageDic() {
		return languageDic;
	}

	public void setLanguageDic(String languageDic) {
		this.languageDic = languageDic;
	}
}
