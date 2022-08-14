/**
 * Copyright:   
 * @author:      denghf
 * @version:     V1.0 
 * @Date:        2018-10-26 18:12:38 
 */
package hry.admin.exchange.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import hry.admin.exchange.dao.OtcAppTransactionDao;
import hry.admin.exchange.model.OtcAppTransaction;
import hry.admin.exchange.service.OtcAppTransactionService;
import hry.bean.PageResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;

import hry.util.QueryFilter;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * <p> OtcAppTransactionService </p>
 * @author:         denghf
 * @Date :          2018-10-26 18:12:38  
 */
@Service("otcAppTransactionService")
public class OtcAppTransactionServiceImpl extends BaseServiceImpl<OtcAppTransaction, Long> implements OtcAppTransactionService{
	
	@Resource(name="otcAppTransactionDao")
	@Override
	public void setDao(BaseDao<OtcAppTransaction, Long> dao) {
		super.dao = dao;
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
		pageResult.setTotal(page.getTotal());
		//----------------------分页查询底部外壳------------------------------

		return pageResult;

	}
}
