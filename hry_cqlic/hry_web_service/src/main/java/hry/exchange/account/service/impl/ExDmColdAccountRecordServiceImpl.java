/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年3月28日 下午6:35:56
 */
package hry.exchange.account.service.impl;

import java.util.List;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.util.QueryFilter;
import hry.exchange.account.model.ExDmColdAccountRecord;
import hry.exchange.account.model.ExDmHotAccountRecord;
import hry.exchange.account.model.vo.DigitalmoneyAccountAndProduct;
import hry.exchange.account.service.ExDmColdAccountRecordService;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * <p>
 * TODO
 * </p>
 * 
 * @author: Wu Shuiming
 * @Date : 2016年3月28日 下午6:35:56
 */
@Service("exDmColdAccountRecordService")
public class ExDmColdAccountRecordServiceImpl extends
		BaseServiceImpl<ExDmColdAccountRecord, Long> implements
		ExDmColdAccountRecordService {

	@Resource(name = "exDmColdAccountRecordDao")
	@Override
	public void setDao(BaseDao<ExDmColdAccountRecord, Long> dao) {
		super.dao = dao;
	}

	@Override
	public List<ExDmColdAccountRecord> findColdAccountRecordBytransactionNum(
			String transactionNum) {
		QueryFilter filter = new QueryFilter(ExDmHotAccountRecord.class);
		filter.addFilter("transactionNum=", transactionNum);
		List<ExDmColdAccountRecord> list = super.find(filter);
		return list;
	}

	

}
