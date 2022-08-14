/**
 * Copyright:   
 * @author:      luyue
 * @version:     V1.0 
 * @Date:        2018-11-16 14:53:24 
 */
package hry.mall.platform.service.impl;

import com.github.pagehelper.Page;
import hry.bean.PageResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.mall.platform.dao.DaddressDao;
import hry.mall.platform.model.Daddress;
import hry.mall.platform.service.DaddressService;
import hry.util.PageFactory;
import hry.util.QueryFilter;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p> DaddressService </p>
 * @author:         luyue
 * @Date :          2018-11-16 14:53:24  
 */
@Service("daddressService")
public class DaddressServiceImpl extends BaseServiceImpl<Daddress, Long> implements DaddressService {
	
	@Resource(name="daddressDao")
	@Override
	public void setDao(BaseDao<Daddress, Long> dao) {
		super.dao = dao;
	}

	@Override
	public PageResult findPageBySql(QueryFilter filter) {
		// TODO Auto-generated method stub
		//----------------------分页查询头部外壳------------------------------
		//创建PageResult对象
		Page<Daddress> page = PageFactory.getPage(filter);


		//----------------------分页查询头部外壳------------------------------

		//----------------------查询开始------------------------------

		String provinceId =(String) filter.getRequest().getParameter("info_province");
		String cityId =(String) filter.getRequest().getParameter("info_city");
		String countyId=(String)filter.getRequest().getParameter("info_county");
		String name=filter.getRequest().getParameter("name");
		String sendName=filter.getRequest().getParameter("sendName");
		String cellphone=filter.getRequest().getParameter("cellphone");
	
		Map<String,Object> map = new HashMap<String,Object>();
		//"100000" 是初始化地区字典列表 时初始化的省的默认空值的key,所以查询时要过滤掉该值
		if(!StringUtils.isEmpty(provinceId) && !"100000".equals(provinceId)){
			map.put("provinceId", provinceId);
		}
		if(!StringUtils.isEmpty(cityId)){
			map.put("cityId", cityId);
		}
		if(!StringUtils.isEmpty(countyId)){
			map.put("countyId", countyId);
		}
		if(!StringUtils.isEmpty(name)){
			map.put("name", "%"+name+"%");
		}
		if(!StringUtils.isEmpty(sendName)){
			map.put("sendName", "%"+sendName+"%");
		}
		if(!StringUtils.isEmpty(cellphone)){
			map.put("cellphone", "%"+cellphone+"%");
		}
		 List<Daddress> list=((DaddressDao)dao).findPageBySql(map);

		//----------------------分页查询底部外壳------------------------------
		return new PageResult(page, filter.getPage(),filter.getPageSize());
	}
	

}
