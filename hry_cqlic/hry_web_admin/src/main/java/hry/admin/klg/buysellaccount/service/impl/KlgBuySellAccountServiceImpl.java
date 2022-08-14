/**
 * Copyright:   
 * @author:      yaozhuo
 * @version:     V1.0 
 * @Date:        2019-04-22 17:02:32 
 */
package hry.admin.klg.buysellaccount.service.impl;

import hry.admin.klg.buysellaccount.model.KlgBuySellAccount;
import hry.admin.klg.buysellaccount.service.KlgBuySellAccountService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> KlgBuySellAccountService </p>
 * @author:         yaozhuo
 * @Date :          2019-04-22 17:02:32  
 */
@Service("klgBuySellAccountService")
public class KlgBuySellAccountServiceImpl extends BaseServiceImpl<KlgBuySellAccount, Long> implements KlgBuySellAccountService{
	
	@Resource(name="klgBuySellAccountDao")
	@Override
	public void setDao(BaseDao<KlgBuySellAccount, Long> dao) {
		super.dao = dao;
	}
	

}
