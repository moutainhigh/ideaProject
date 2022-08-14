/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Zhang Xiaofang
 * @version:      V1.0 
 * @Date:        2016年5月12日 上午11:55:59
 */
package hry.change.remote.account;

import hry.exchange.account.model.ExApiApply;
import java.util.List;
import java.util.Map;

/**
 * <p> TODO</p>
 * @author:         Zhang Xiaofang 
 * @Date :          2016年5月12日 上午11:55:59 
 */
public interface RemoteExApiApplyService {
	
	

	
	  //查询某个用户申请的所有的访问api的key
	  public List<ExApiApply> findList(Long customerId);
	   
	  //为某个用户创建一个访问api的key
	  public Map<String,String> createKey(ExApiApply exApiApply);

	/**
	 * <p> TODO</p>
	 * @author:         Zhang Xiaofang
	 * @param:    @param valueOf
	 * @return: void 
	 * @Date :          2016年5月12日 下午8:13:51   
	 * @throws:
	 */
	public boolean delete(Long valueOf);
	/**
	 * 
	 * <p> 通过公钥获得客户</p>
	 * @author:         Gao Mimi
	 * @param:    @param publicKey
	 * @param:    @param currencyType
	 * @param:    @return
	 * @return: ExDigitalmoneyAccount 
	 * @Date :          2016年5月19日 上午10:16:55   
	 * @throws:
	 */
	public ExApiApply getByPublicKey(String publicKey, String saasId);
	
	public ExApiApply findByKey(String key);
}
