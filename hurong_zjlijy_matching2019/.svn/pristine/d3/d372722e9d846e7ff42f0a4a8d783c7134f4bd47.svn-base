/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年3月28日 下午6:12:49
 */
package hry.financail.account.service.impl;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.financail.account.model.ExDigitalmoneyAccount;
import hry.financail.account.service.ExDigitalmoneyAccountService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * TODO
 * </p>
 * 
 * @author: Wu Shuiming
 * @Date : 2016年3月28日 下午6:12:49
 */
@Service("exDigitalmoneyAccountService")
public class ExDigitalmoneyAccountServiceImpl extends
		BaseServiceImpl<ExDigitalmoneyAccount, Long> implements
		ExDigitalmoneyAccountService {
	private static Logger logger = Logger.getLogger(ExDigitalmoneyAccountServiceImpl.class);
	

	
	@Resource(name = "exDigitalmoneyAccountDao")
	@Override
	public void setDao(BaseDao<ExDigitalmoneyAccount, Long> dao) {
		super.dao = dao;
	}
	



}
