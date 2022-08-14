/**
 * Copyright:   
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-01-17 15:10:57 
 */
package hry.ico.service.impl;

import hry.ico.dao.IcoTransferAccountsDao;
import hry.ico.model.IcoTransferAccounts;
import hry.ico.service.IcoTransferAccountsService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> IcoTransferAccountsService </p>
 * @author:         lzy
 * @Date :          2019-01-17 15:10:57  
 */
@Service("icoTransferAccountsService")
public class IcoTransferAccountsServiceImpl extends BaseServiceImpl<IcoTransferAccounts, Long> implements IcoTransferAccountsService{
	
	@Resource(name="icoTransferAccountsDao")
	@Override
	public void setDao(BaseDao<IcoTransferAccounts, Long> dao) {
		super.dao = dao;
	}


	@Override
	public IcoTransferAccounts getTransferAccountsInfo(Long keyId) {
		return ((IcoTransferAccountsDao) dao).getTransferAccountsInfo(keyId);
	}
}
