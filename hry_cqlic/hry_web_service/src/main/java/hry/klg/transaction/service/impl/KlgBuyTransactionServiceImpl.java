/**
 * Copyright:   
 * @author:      yaozhuo
 * @version:     V1.0 
 * @Date:        2019-04-18 15:00:12 
 */
package hry.klg.transaction.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import hry.bean.FrontPage;
import hry.bean.ObjectUtil;
import hry.calculate.util.DateUtil;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.klg.remote.RemoteKlgService;
import hry.klg.remote.model.RemoteBuyTransaction;
import hry.klg.transaction.dao.KlgBuyTransactionDao;
import hry.klg.transaction.model.KlgBuyTransaction;
import hry.klg.transaction.service.KlgBuyTransactionService;
import hry.util.QueryFilter;

/**
 * <p> KlgBuyTransactionService </p>
 * @author:         yaozhuo
 * @Date :          2019-04-18 15:00:12  
 */
@Service("klgBuyTransactionService")
public class KlgBuyTransactionServiceImpl extends BaseServiceImpl<KlgBuyTransaction, Long> implements KlgBuyTransactionService{
	//预约购买限制天数
	private  final static Integer limitDay=6;

	@Resource(name="klgBuyTransactionDao")
	@Override
	public void setDao(BaseDao<KlgBuyTransaction, Long> dao) {
		super.dao = dao;
	}
	@Resource
	private RemoteKlgService remoteKlgService;


	/**
	 *  获取用户预约单数
	 * @param customerId
	 * @return
	 */
	@Override
	public int getBuyTransactionCountByCustomerId(Long customerId) {
		QueryFilter queryFilter=new QueryFilter(KlgBuyTransaction.class);
		queryFilter.addFilter("customerId=",customerId);
		Long count= super.count(queryFilter);
		return count.intValue();
	}

	@Override
	public FrontPage findPageBySql(Map<String, String> hashMap) {
		String offset=hashMap.get("page")==null?"0":hashMap.get("page");
		String limit=hashMap.get("pageSize")==null?"10":hashMap.get("pageSize");
		if(Integer.parseInt(limit)>100){
			limit="10";
		}
		Page<KlgBuyTransaction> page = PageHelper.startPage(Integer.parseInt(offset), Integer.parseInt(limit));
		List<KlgBuyTransaction> list =((KlgBuyTransactionDao)dao).findPageBySql(hashMap);
		List<RemoteBuyTransaction> result= ObjectUtil.beanList(list,RemoteBuyTransaction.class);
		return new FrontPage(result, page.getTotal(), page.getPages(), page.getPageSize());

	}
	@Override
	public boolean checkBuyInterval(Long customerId) {
		/*// 验证用户购买间隔是否大于6天
		QueryFilter queryFilter=new QueryFilter(KlgBuyTransaction.class);
		queryFilter.addFilter("customerId=",customerId);
		queryFilter.addFilter("rushOrders=",1);
		queryFilter.setOrderby("created desc");
		KlgBuyTransaction klgBuyTransaction=super.get(queryFilter);
		if(klgBuyTransaction!=null){
			Date date=klgBuyTransaction.getCreated();//时间倒序 最后一条的预约时间
			Date now =new Date();//当前时间
			try {
				Integer limitDayConfig =Integer.valueOf((String)remoteKlgService.getPlatformRule1sConfig("lineUpTime"));
				int day=DateUtil.daysBetween(date,now);
				if(day>=limitDayConfig){
					return true;
				}else{
					return false;
				}
			} catch (ParseException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;*/
		Integer days=getBuyInterval(customerId);
		if(days==null){
			return true;
		}
		Integer limitDayConfig =Integer.valueOf((String)remoteKlgService.getPlatformRule1sConfig("lineUpTime"));
		if(days>=limitDayConfig){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean checkIntervalByday(Long customerId, Integer limitday) {
		Integer days=getBuyInterval(customerId);
		if(days==null){//没有买过
			return false;
		}
		if(days>=limitday){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public Integer getBuyInterval(Long customerId) {
		Integer days=null;
		// 验证用户购买间隔是否大于6天
		QueryFilter queryFilter=new QueryFilter(KlgBuyTransaction.class);
		queryFilter.addFilter("customerId=",customerId);
		queryFilter.addFilter("rushOrders=",1);
		queryFilter.setOrderby("created desc");
		KlgBuyTransaction klgBuyTransaction=super.get(queryFilter);
		if(klgBuyTransaction!=null){
			Date date=klgBuyTransaction.getCreated();//时间倒序 最后一条的预约时间
			Date now =new Date();//当前时间
			try {
				int day=DateUtil.daysBetween(date,now);
				return day;
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return days;
	}

	@Override
	public BigDecimal getBuyInterestSum(Long customerId) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		map.put("customerId", customerId);
		return ((KlgBuyTransactionDao)dao).getBuyInterestSum(map);
	}
	
	@Override
	public BigDecimal getBuyFirstMoneySum(Long customerId) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		map.put("customerId", customerId);
		return ((KlgBuyTransactionDao)dao).getBuyFirstMoneySum(map);
	}
}
