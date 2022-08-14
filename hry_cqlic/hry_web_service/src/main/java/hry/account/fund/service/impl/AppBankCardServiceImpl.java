/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0 
 * @Date:        2016年3月31日 下午6:52:11
 */
package hry.account.fund.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import hry.account.fund.dao.AppBankCardDao;
import hry.account.fund.model.AppBankCard;
import hry.account.fund.service.AppBankCardService;
import hry.bean.PageResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.customer.user.model.AppCustomer;
import hry.manage.remote.model.base.FrontPage;
import hry.util.PageFactory;
import hry.util.QueryFilter;

/**
 * <p> TODO</p>
 * @author:         Liu Shilei 
 * @Date :          2016年3月31日 下午6:52:11 
 */
@Service("appBankCardService")
public class AppBankCardServiceImpl extends BaseServiceImpl<AppBankCard, Long> implements AppBankCardService{
	
	@Resource(name="appBankCardDao")
	@Override
	public void setDao(BaseDao<AppBankCard, Long> dao) {
		super.dao = dao;
	}

	@Override
	public PageResult findPageBySql(QueryFilter filter) {
		
		//----------------------分页查询头部外壳------------------------------
		//创建PageResult对象
		PageResult pageResult = new PageResult();
		Page<AppCustomer> page = null;
		if(filter.getPageSize().compareTo(Integer.valueOf(-1))==0){
			//pageSize = -1 时  
			//pageHelper传pageSize参数传0查询全部
			page= PageHelper.startPage(filter.getPage(), 0);
		}else{
			page = PageHelper.startPage(filter.getPage(), filter.getPageSize());
		}
		//----------------------分页查询头部外壳------------------------------
		
		//----------------------查询开始------------------------------
		
		String email = filter.getRequest().getParameter("email");
		String mobilePhone = filter.getRequest().getParameter("mobilePhone");
		
		Map<String,Object> map = new HashMap<String,Object>();
		if(!StringUtils.isEmpty(email)){
			map.put("email", "%"+email+"%");
		}
		if(!StringUtils.isEmpty(mobilePhone)){
			map.put("mobilePhone", "%"+mobilePhone+"%");
		}
		
		
		
		((AppBankCardDao)dao).findPageBySql(map);
		//----------------------查询结束------------------------------
		
		//----------------------分页查询底部外壳------------------------------
		//设置分页数据
		pageResult.setRows(page.getResult());
		//设置总记录数
		pageResult.setRecordsTotal(page.getTotal());
		pageResult.setDraw(filter.getDraw());
		pageResult.setPage(filter.getPage());
		pageResult.setPageSize(filter.getPageSize());
		//----------------------分页查询底部外壳------------------------------
		
		return pageResult;
	
	
	}
	
	@Override
	public PageResult findPageBySqlList(QueryFilter filter) {
		//----------------------分页查询头部外壳------------------------------
		// 分页参数处理
		String startStr = filter.getRequest().getParameter("start");
		String lengthStr = filter.getRequest().getParameter("length");
		Integer startpage = Integer.valueOf(startStr);
		Integer lengthpage = Integer.valueOf(lengthStr);
		if( lengthpage == null || lengthpage == 0 ){
			lengthpage = 10;
		}
		startpage = startpage/lengthpage;
		// 分页参数处理结束
		
		//创建PageResult对象
		PageResult pageResult = new PageResult();
		pageResult.setPage(startpage);
		pageResult.setPageSize(lengthpage);

		Map<String,Object> map = new HashMap<String,Object>();
	    Integer start = startpage * lengthpage;
		map.put("start", start);
		map.put("end", lengthpage);
		
		//----------------------查询开始------------------------------
		
		String email = filter.getRequest().getParameter("email");
		String mobilePhone = filter.getRequest().getParameter("mobilePhone");
		
		Map<String,Object> mapcustomer = new HashMap<String,Object>();
		if(!StringUtils.isEmpty(email)){
			mapcustomer.put("email", email);
		}
		if(!StringUtils.isEmpty(mobilePhone)){
			mapcustomer.put("mobilePhone", mobilePhone);
		}
		if(mapcustomer.size()>0){
			List<String> listpersoninfo = ((AppBankCardDao)dao).findCustomerByCondition(mapcustomer);
			if(listpersoninfo.size()>0){
				map.put("customerId", listpersoninfo);
			}else{
				List<AppBankCard> list = new ArrayList<AppBankCard>();  
				//设置分页数据
				pageResult.setRows(list);
				//设置总记录数
				pageResult.setRecordsTotal(Long.valueOf("0"));
				return pageResult;
			}
		}

		Long count = ((AppBankCardDao)dao).findPageBySqlCount(map);
		List<AppBankCard>  list = ((AppBankCardDao)dao).findPageBySqlList(map);
		//----------------------查询结束------------------------------

		//----------------------分页查询底部外壳------------------------------
		//设置分页数据
		pageResult.setRows(list);
		//设置总记录数
		pageResult.setRecordsTotal(count);

		//----------------------分页查询底部外壳------------------------------

		return pageResult;
	}
	@Override
	public int findSaveFlag(String cardNumber, Integer type) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cardNumber", cardNumber);
		map.put("type", type);
		return ((AppBankCardDao)dao).findSaveFlag(map);
	}
	
	@Override
	public FrontPage findPageBySql1(Map<String, String> map) {
		Page<AppBankCard> page = PageFactory.getPage(map);
		List<AppBankCard> list = ((AppBankCardDao)dao).findPageBySql1(map);
		return new FrontPage(list, page.getTotal(), page.getPages(), page.getPageSize());
	}

	/**
	 * 根据用户id和类型获得支付信息
	 * customerId 所属人id
	 * type 支付类型  1-银行卡  2-支付宝  3-微信
	 * isDefault 不为空时，查询默认的；
	 */
	@Override
	public List<AppBankCard> getPersonalAsset(Long customerId, String type, String isDefault) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("customerId", customerId);
		map.put("type", type);
		map.put("isDefault", isDefault);
		return ((AppBankCardDao)dao).getPersonalAsset(map);
	}
	
	/**
	 * 根据类型和用户id取消默认
	 */
	@Override
	public void updateIsDefault(Map<String, String> map) {
		((AppBankCardDao)dao).updateIsDefault(map);
	}

	@Override
	public void updateIsDeleteById (Long id) {
		AppBankCard bankCard = super.get(id);
		if( bankCard.getIsDefault() == 1){
			//如果删除的是默认的
			QueryFilter filter = new QueryFilter(AppBankCard.class);
			filter.addFilter("type=",bankCard.getType());
			filter.addFilter("isDelete=", 0);
			filter.addFilter("customerId=", bankCard.getCustomerId());
			filter.addFilter("isDefault=", 0);
			filter.setOrderby("created desc");
			List<AppBankCard> appBankCards = super.find(filter);
			if( appBankCards != null && appBankCards.size() > 0){
				appBankCards.get(0).setIsDefault(1);
				super.update(appBankCards.get(0));
			}

		}
		((AppBankCardDao)dao).updateIsDeleteById(id);
	}
}
