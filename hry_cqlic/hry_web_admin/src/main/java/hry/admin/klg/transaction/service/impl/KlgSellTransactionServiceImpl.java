/**
 * Copyright:   
 * @author:      yaozhuo
 * @version:     V1.0 
 * @Date:        2019-04-16 14:25:18 
 */
package hry.admin.klg.transaction.service.impl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.pagehelper.Page;

import hry.admin.klg.transaction.dao.KlgBuyTransactionDao;
import hry.admin.klg.transaction.dao.KlgSellTransactionDao;
import hry.admin.klg.transaction.model.KlgSellTransaction;
import hry.admin.klg.transaction.model.vo.KlgBuyTransactionVo;
import hry.admin.klg.transaction.model.vo.KlgSellTransactionVo;
import hry.admin.klg.transaction.service.KlgSellTransactionService;
import hry.bean.PageResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.util.PageFactory;
import hry.util.QueryFilter;

/**
 * <p> KlgSellTransactionService </p>
 * @author:         yaozhuo
 * @Date :          2019-04-16 14:25:18  
 */
@Service("klgSellTransactionService")
public class KlgSellTransactionServiceImpl extends BaseServiceImpl<KlgSellTransaction, Long> implements KlgSellTransactionService{
	
	@Resource(name="klgSellTransactionDao")
	@Override
	public void setDao(BaseDao<KlgSellTransaction, Long> dao) {
		super.dao = dao;
	}
	@Override
	public PageResult findPageBySql(QueryFilter filter) {
		// TODO Auto-generated method stub
		//----------------------分页查询头部外壳------------------------------
		Page<KlgSellTransactionVo> page = PageFactory.getPage(filter);
		//----------------------分页查询头部外壳------------------------------

		//----------------------查询开始------------------------------

		String mobilePhone = filter.getRequest().getParameter("mobilePhone");
		String sellDay = filter.getRequest().getParameter("sellDay");
		String email = filter.getRequest().getParameter("email");
		String trueName = filter.getRequest().getParameter("trueName");
		String surName = filter.getRequest().getParameter("surName");
		String status = filter.getRequest().getParameter("status");
		String transactionNum = filter.getRequest().getParameter("transactionNum");
		String modified_s = filter.getRequest().getParameter("created_GT");
		String modified_e = filter.getRequest().getParameter("created_LT");
		String modified_sm = filter.getRequest().getParameter("created_GTM");
		String modified_em = filter.getRequest().getParameter("created_LTM");
		String modified_sme = filter.getRequest().getParameter("created_GTME");
		String modified_eme = filter.getRequest().getParameter("created_LTME");
		
		Map<String,Object> map = new HashMap<String,Object>();
		if(!StringUtils.isEmpty(transactionNum)){
			map.put("transactionNum", "%"+transactionNum+"%");
		}
		if(!StringUtils.isEmpty(mobilePhone)){
			map.put("mobilePhone", "%"+mobilePhone+"%");
		}
		if(!StringUtils.isEmpty(email)){
			map.put("email", "%"+email+"%");
		}
		if(!StringUtils.isEmpty(trueName)){
			map.put("trueName", "%"+trueName+"%");
		}
		if(!StringUtils.isEmpty(surName)){
			map.put("surName", "%"+surName+"%");
		}
		if(!StringUtils.isEmpty(status)){
			map.put("status", Integer.valueOf(status));
		}
		if(!StringUtils.isEmpty(sellDay)){
			map.put("sellDay", Integer.valueOf(sellDay));
		}
		if(!StringUtils.isEmpty(modified_s)){
			map.put("modified_s", modified_s);
		}
		if(!StringUtils.isEmpty(modified_e)){
			map.put("modified_e", modified_e);
		}
		if (!StringUtils.isEmpty(modified_sm)) {
			map.put("modified_sm", modified_sm);
		}
		if (!StringUtils.isEmpty(modified_em)) {
			map.put("modified_em", modified_em);
		}
		if (!StringUtils.isEmpty(modified_sme)) {
			map.put("modified_sme", modified_sme);
		}
		if (!StringUtils.isEmpty(modified_eme)) {
			map.put("modified_eme", modified_eme);
		}
		((KlgSellTransactionDao)dao).findPageBySql(map);
		
		return new PageResult(page, filter.getPage(),filter.getPageSize());
	}
	@Override
	public List<KlgSellTransactionVo> findListBySql(QueryFilter filter) {
		// TODO Auto-generated method stub
		//----------------------查询开始------------------------------
		Map<String,Object> map = new HashMap<String,Object>();
		if(filter.getRequest()==null){
			map.put("status", 1);
			map.put("endstatus", 1);
			return ((KlgSellTransactionDao) dao).findPageBySql(map);
		}
		String mobilePhone = filter.getRequest().getParameter("mobilePhone");
		String email = filter.getRequest().getParameter("email");
		String trueName = filter.getRequest().getParameter("trueName");
		String surName = filter.getRequest().getParameter("surName");
		String status = filter.getRequest().getParameter("status");
		String transactionNum = filter.getRequest().getParameter("transactionNum");
		String modified_s = filter.getRequest().getParameter("created_GT");
		String modified_e = filter.getRequest().getParameter("created_LT");
		
		if(!StringUtils.isEmpty(transactionNum)){
			map.put("transactionNum", "%"+transactionNum+"%");
		}
		if(!StringUtils.isEmpty(mobilePhone)){
			map.put("mobilePhone", "%"+mobilePhone+"%");
		}
		if(!StringUtils.isEmpty(email)){
			map.put("email", "%"+email+"%");
		}
		if(!StringUtils.isEmpty(trueName)){
			map.put("trueName", "%"+trueName+"%");
		}
		if(!StringUtils.isEmpty(surName)){
			map.put("surName", "%"+surName+"%");
		}
		if(!StringUtils.isEmpty(status)){
			map.put("status", Integer.valueOf(status));
		}
		if(!StringUtils.isEmpty(modified_s)){
			map.put("modified_s", modified_s);
		}
		if(!StringUtils.isEmpty(modified_e)){
			map.put("modified_e", modified_e);
		}
		return ((KlgSellTransactionDao)dao).findPageBySql(map);
	}
	@Override
	public KlgSellTransactionVo getSmeMoneySumAndUsdtMoneyByStatus(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return ((KlgSellTransactionDao)dao).getSmeMoneySumAndUsdtMoneyByStatus(map);
	}
	@Override
	public void updateStatus(Map<String, Object> map) {
		// TODO Auto-generated method stub
		((KlgSellTransactionDao)dao).updateStatus(map);
	}
	@Override
	public List<KlgSellTransactionVo> findSellTransactionByIdINIds(List<Long> array) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("ids", array);
		return ((KlgSellTransactionDao)dao).findSellTransactionByIdINIds(map);
	}
	@Override
	public List<KlgSellTransactionVo> findSellTransactionByIdINIdsStr(List<String> array) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("ids", array);
		return ((KlgSellTransactionDao)dao).findSellTransactionByIdINIds(map);
	}
	@Override
	public void updateStatusByIds(Map<String, Object> map) {
		// TODO Auto-generated method stub
		((KlgSellTransactionDao)dao).updateStatusByIds(map);
	}
	@Override
	public BigDecimal getSellTransactionByIdINIds(String ids) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		String[] strarray=ids.split(","); 
		List<String> strsToList= Arrays.asList(strarray);
		map.put("ids", strsToList);
		return ((KlgSellTransactionDao)dao).getSellTransactionByIdINIds(map);
	}
	@Override
	public void updateCandySmeMoneyById(Long id,BigDecimal candySmeMoney) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("candySmeMoney", candySmeMoney);
		((KlgSellTransactionDao)dao).updateCandySmeMoneyById(map);
		
	}
	@Override
	public List<KlgSellTransactionVo> findBeyondList() {
		// TODO Auto-generated method stub
		return ((KlgSellTransactionDao)dao).findBeyondList();
	}
	@Override
	public PageResult findPageGroupBydaySql(QueryFilter filter) {
		// TODO Auto-generated method stub
		//----------------------分页查询头部外壳------------------------------
		Page<KlgSellTransactionVo> page = PageFactory.getPage(filter);
		//----------------------分页查询头部外壳------------------------------
		((KlgSellTransactionDao)dao).findSellListGroupByDay();
		
		return new PageResult(page, filter.getPage(),filter.getPageSize());
	}

}
