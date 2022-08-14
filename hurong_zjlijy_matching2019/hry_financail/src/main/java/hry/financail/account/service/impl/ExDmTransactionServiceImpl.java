/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年3月28日 下午7:00:10
 */
package hry.financail.account.service.impl;


import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.financail.account.model.ExDmTransaction;
import hry.financail.account.service.ExDmTransactionService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;


@Service("exDmTransactionService")
public class ExDmTransactionServiceImpl extends BaseServiceImpl<ExDmTransaction, Long> implements ExDmTransactionService {
	private static Logger logger = Logger.getLogger(ExDmTransactionServiceImpl.class);


	@Override
	public void setDao(BaseDao<ExDmTransaction, Long> baseDao) {

	}
}
