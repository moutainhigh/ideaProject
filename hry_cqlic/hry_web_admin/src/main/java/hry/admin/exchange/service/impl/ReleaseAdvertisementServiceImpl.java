/**
 * Copyright:   
 * @author:      denghf
 * @version:     V1.0 
 * @Date:        2018-10-29 13:36:05 
 */
package hry.admin.exchange.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import hry.admin.exchange.dao.ReleaseAdvertisementDao;
import hry.admin.exchange.model.ReleaseAdvertisement;
import hry.admin.exchange.service.ReleaseAdvertisementService;
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
 * <p> ReleaseAdvertisementService </p>
 * @author:         denghf
 * @Date :          2018-10-29 13:36:05  
 */
@Service("releaseAdvertisementService")
public class ReleaseAdvertisementServiceImpl extends BaseServiceImpl<ReleaseAdvertisement, Long> implements ReleaseAdvertisementService{
	
	@Resource(name="releaseAdvertisementDao")
	@Override
	public void setDao(BaseDao<ReleaseAdvertisement, Long> dao) {
		super.dao = dao;
	}

	@Override
	public PageResult findPageAll (QueryFilter filter) {
		//----------------------分页查询头部外壳------------------------------
		//创建PageResult对象
		PageResult pageResult = new PageResult();
		Page<ReleaseAdvertisement> page = null;
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
		/*String mycolumn = filter.getRequest().getParameter("mycolumn");
		String search = filter.getRequest().getParameter("search");
		if(! StringUtils.isEmpty( mycolumn ) && ! StringUtils.isEmpty( search ) ){
			map.put(mycolumn, search);
		}*/
		String email = filter.getRequest().getParameter("email");
		String mobilePhone = filter.getRequest().getParameter("mobilePhone");
		String status = filter.getRequest().getParameter("status");
		if(! StringUtils.isEmpty( email ) ){
			map.put("email",email);
		}
		if(! StringUtils.isEmpty( mobilePhone ) ){
			map.put("mobilePhone",mobilePhone);
		}
		if(! StringUtils.isEmpty( status ) ){
			map.put("status",status);
		}
		((ReleaseAdvertisementDao)dao).findPageByAll(map);
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
