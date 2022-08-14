/**
 * Copyright:   
 * @author:      yaozhuo
 * @version:     V1.0 
 * @Date:        2019-04-18 14:58:50 
 */
package hry.klg.transaction.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import hry.bean.FrontPage;
import hry.bean.ObjectUtil;
import hry.klg.remote.model.RemoteSellTransaction;
import org.springframework.stereotype.Service;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.klg.transaction.dao.KlgSellTransactionDao;
import hry.klg.transaction.model.KlgSellTransaction;
import hry.klg.transaction.service.KlgSellTransactionService;

/**
 * <p> KlgSellTransactionService </p>
 * @author:         yaozhuo
 * @Date :          2019-04-18 14:58:50  
 */
@Service("klgSellTransactionService")
public class KlgSellTransactionServiceImpl extends BaseServiceImpl<KlgSellTransaction, Long> implements KlgSellTransactionService{
	
	@Resource(name="klgSellTransactionDao")
	@Override
	public void setDao(BaseDao<KlgSellTransaction, Long> dao) {
		super.dao = dao;
	}

	@Override
	public void updateStatusByIds(Map<String, Object> map) {
		// TODO Auto-generated method stub
		((KlgSellTransactionDao)dao).updateStatusByIds(map);
	}

	@Override
	public FrontPage findPageBySql(Map<String, String> hashMap) {
		String offset=hashMap.get("page")==null?"0":hashMap.get("page");
		String limit=hashMap.get("pageSize")==null?"10":hashMap.get("pageSize");
		if(Integer.parseInt(limit)>100){
			limit="10";
		}
		Page<KlgSellTransaction> page = PageHelper.startPage(Integer.parseInt(offset), Integer.parseInt(limit));
		List<KlgSellTransaction> list =((KlgSellTransactionDao)dao).findPageBySql(hashMap);
		List<RemoteSellTransaction> result= ObjectUtil.beanList(list,RemoteSellTransaction.class);
		return new FrontPage(result, page.getTotal(), page.getPages(), page.getPageSize());

	}

	@Override
	public BigDecimal getSellProfitSum(Long customerId) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<>();
		map.put("customerId", customerId);
		return ((KlgSellTransactionDao)dao).getSellProfitSum(map);
	}


}
