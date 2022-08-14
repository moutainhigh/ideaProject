/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      denghf
 * @version:     V1.0 
 * @Date:        2018-06-22 11:39:46 
 */
package hry.otc.releaseAdvertisement.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import hry.bean.FrontPage;
import hry.bean.PageResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.otc.releaseAdvertisement.dao.ReleaseAdvertisementDao;
import hry.otc.releaseAdvertisement.model.ReleaseAdvertisement;
import hry.otc.releaseAdvertisement.service.ReleaseAdvertisementService;
import hry.util.PageFactory;
import hry.util.QueryFilter;

/**
 * <p> ReleaseAdvertisementService </p>
 * @author:         denghf
 * @Date :          2018-06-22 11:39:46  
 */
@Service("releaseAdvertisementService")
public class ReleaseAdvertisementServiceImpl extends BaseServiceImpl<ReleaseAdvertisement, Long> implements ReleaseAdvertisementService{
	
	@Resource(name="releaseAdvertisementDao")
	@Override
	public void setDao(BaseDao<ReleaseAdvertisement, Long> dao) {
		super.dao = dao;
	}

	@Override
	public FrontPage findPageHall(Map<String, String> map) {
		Page<ReleaseAdvertisement> page = PageFactory.getPage(map);
		List<ReleaseAdvertisement> list = ((ReleaseAdvertisementDao)dao).findPageHall(map);
		return new FrontPage(list, page.getTotal(), page.getPages(), page.getPageSize());
	}
	
	@Override
	public FrontPage findPageBySql(Map<String, String> map) {
		Page<ReleaseAdvertisement> page = PageFactory.getPage(map);
		List<ReleaseAdvertisement> list = ((ReleaseAdvertisementDao)dao).findPageBySql(map);
		return new FrontPage(list, page.getTotal(), page.getPages(), page.getPageSize());
	}
	
	@Override
	public String avgTime(Long customerId) {
		String avgTime = ((ReleaseAdvertisementDao)dao).avgTime(customerId);
		if(avgTime==null){
			avgTime = "0";
		}
		return avgTime;
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
		//----------------------分页查询底部外壳------------------------------

		return pageResult;
	}
}
