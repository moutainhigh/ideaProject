/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      menwei
 * @version:     V1.0 
 * @Date:        2017-11-28 15:30:37 
 */
package hry.manage.remote.model.commend;


import java.io.Serializable;
import java.math.BigDecimal;

	
public class CommendUser implements Serializable {
	
	private Long id;  //id
	
	private Long pid;  //pid
	
	private String username;  //username
	
	private String pname;  //pname
	
	private Long uid;  //pid
	private String sid;  //sid
	
	private String receCode;
	
	
	private int oneNumber;
	
	private int twoNumber;
	
	private int threeNumber;
	
	private int laterNumber;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getReceCode() {
		return receCode;
	}

	public void setReceCode(String receCode) {
		this.receCode = receCode;
	}

	public int getOneNumber() {
		return oneNumber;
	}

	public void setOneNumber(int oneNumber) {
		this.oneNumber = oneNumber;
	}

	public int getTwoNumber() {
		return twoNumber;
	}

	public void setTwoNumber(int twoNumber) {
		this.twoNumber = twoNumber;
	}

	public int getThreeNumber() {
		return threeNumber;
	}

	public void setThreeNumber(int threeNumber) {
		this.threeNumber = threeNumber;
	}

	public int getLaterNumber() {
		return laterNumber;
	}

	public void setLaterNumber(int laterNumber) {
		this.laterNumber = laterNumber;
	}
	
	

	

}
