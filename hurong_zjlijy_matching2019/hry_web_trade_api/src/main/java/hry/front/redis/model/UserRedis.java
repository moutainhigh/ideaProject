package hry.front.redis.model;

import java.io.Serializable;
import java.util.Map;

public class UserRedis implements Serializable{
	
	private String id;
	
	private Long accountId;
	
	private Map<String,Long> dmAccountId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public Map<String, Long> getDmAccountId() {
		return dmAccountId;
	}

	public void setDmAccountId(Map<String, Long> dmAccountId) {
		this.dmAccountId = dmAccountId;
	}
	

	/**
	 * 查询币账户id
	 * @param coinCode
	 * @return
	 */
	public Long getDmAccountId(String coinCode){
		if(dmAccountId!=null){
			return dmAccountId.get(coinCode);
		} 
		return null;
	}
	


	
	 
	

}
