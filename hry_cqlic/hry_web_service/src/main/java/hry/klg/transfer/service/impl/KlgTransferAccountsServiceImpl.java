/**
 * Copyright:   
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-04-29 19:17:06 
 */
package hry.klg.transfer.service.impl;

import hry.klg.transfer.model.KlgTransferAccounts;
import hry.klg.transfer.service.KlgTransferAccountsService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> KlgTransferAccountsService </p>
 * @author:         lzy
 * @Date :          2019-04-29 19:17:06  
 */
@Service("klgTransferAccountsService")
public class KlgTransferAccountsServiceImpl extends BaseServiceImpl<KlgTransferAccounts, Long> implements KlgTransferAccountsService{
	
	@Resource(name="klgTransferAccountsDao")
	@Override
	public void setDao(BaseDao<KlgTransferAccounts, Long> dao) {
		super.dao = dao;
	}
	

}
