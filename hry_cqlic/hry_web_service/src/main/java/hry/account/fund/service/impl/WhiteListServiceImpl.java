/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      denghf
 * @version:     V1.0 
 * @Date:        2018-06-15 10:24:41 
 */
package hry.account.fund.service.impl;

import hry.account.fund.model.WhiteList;
import hry.account.fund.service.WhiteListService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> WhiteListService </p>
 * @author:         denghf
 * @Date :          2018-06-15 10:24:41  
 */
@Service("whiteListService")
public class WhiteListServiceImpl extends BaseServiceImpl<WhiteList, Long> implements WhiteListService{
	
	@Resource(name="whiteListDao")
	@Override
	public void setDao(BaseDao<WhiteList, Long> dao) {
		super.dao = dao;
	}
	

}
