/**
 * Copyright:  北京互融时代软件有限公司
 * @author:    Gao Mimi
 * @version:   V1.0 
 * @Date:      2015年09月28日  18:10:04
 */
package hry.web.message.dao;

import java.util.List;
import java.util.Map;

import hry.core.mvc.dao.base.BaseDao;
import hry.manage.remote.model.Oamessage;
import hry.web.app.model.MessageAsCustomer;

/**
 * <p> TODO</p>
 * @author:  Gao Mimi        
 * @Date :   2015年09月28日  18:10:04     
 */
public interface MessageAsCustomerDao extends BaseDao<MessageAsCustomer,Long>{
	

	/**
	 * 前台分页查询
	 * @param params
	 * @return
	 */
	List<Oamessage> findFrontPageBySql(Map<String, String> params);
	
}