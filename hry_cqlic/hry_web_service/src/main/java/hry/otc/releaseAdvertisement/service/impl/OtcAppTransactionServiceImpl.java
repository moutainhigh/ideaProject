/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      denghf
 * @version:     V1.0 
 * @Date:        2018-06-25 18:06:52 
 */
package hry.otc.releaseAdvertisement.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import hry.bean.PageResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.otc.releaseAdvertisement.dao.OtcAppTransactionDao;
import hry.otc.releaseAdvertisement.model.OtcAppTransaction;
import hry.otc.releaseAdvertisement.service.OtcAppTransactionService;
import hry.util.QueryFilter;

/**
 * <p> OtcAppTransactionService </p>
 * @author:         denghf
 * @Date :          2018-06-25 18:06:52  
 */
@Service("otcAppTransactionService")
public class OtcAppTransactionServiceImpl extends BaseServiceImpl<OtcAppTransaction, Long> implements OtcAppTransactionService{
	
	@Resource(name="otcAppTransactionDao")
	@Override
	public void setDao(BaseDao<OtcAppTransaction, Long> dao) {
		super.dao = dao;
	}

	@Override
	public BigDecimal adUserBy30(Long id,String coinCode){
		return ((OtcAppTransactionDao)dao).adUserBy30(id,coinCode);
	}
	
	@Override
	public BigDecimal adUserAll(Long id,String coinCode){
		return ((OtcAppTransactionDao)dao).adUserAll(id, coinCode);
	}

	@Override
	public Integer allTradeCount(Map<String, Object> map) {
		return ((OtcAppTransactionDao)dao).allTradeCount(map);
	}

	@Override
	public PageResult findPageBySql(QueryFilter filter, Integer type) {


		//----------------------分页查询头部外壳------------------------------
		//创建PageResult对象
		PageResult pageResult = new PageResult();
		Page<OtcAppTransaction> page = null;
		if(filter.getPageSize().compareTo(Integer.valueOf(-1))==0){
			//pageSize = -1 时
			//pageHelper传pageSize参数传0查询全部
			page= PageHelper.startPage(filter.getPage(), 0);
		}else{
			page = PageHelper.startPage(filter.getPage(), filter.getPageSize());
		}
		//----------------------分页查询头部外壳------------------------------

		//----------------------查询开始------------------------------


		Map<String,Object> map = new HashMap<String,Object>();
		Integer[] status= null;
		if( type == 2){
			//进行中交易查询
			status=new Integer[]{1,2,3};
		}else if( type==3 ){
			//已完成交易查询
			status=new Integer[]{14};
		}else if( type==4 ){
			//已取消交易查询
			status=new Integer[]{5};
		}else if( type == 5){
			//申诉中交易查询
			status=new Integer[]{4,6,9,10,15,16};
		}
		map.put("status",status);
		String buyEmail = filter.getRequest().getParameter("buyEmail");
		String buyMobilePhone = filter.getRequest().getParameter("buyMobilePhone");
		if(! StringUtils.isEmpty( buyEmail ) ){
			map.put("buyEmail",buyEmail);
		}
		if(! StringUtils.isEmpty( buyMobilePhone ) ){
			map.put("buyMobilePhone",buyMobilePhone);
		}
		String sellEmail = filter.getRequest().getParameter("sellEmail");
		String sellMobilePhone = filter.getRequest().getParameter("sellMobilePhone");
		if(! StringUtils.isEmpty( sellEmail ) ){
			map.put("sellEmail",sellEmail);
		}
		if(! StringUtils.isEmpty( sellMobilePhone ) ){
			map.put("sellMobilePhone",sellMobilePhone);
		}

		((OtcAppTransactionDao)dao).findPageBySql(map);
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
}
