/**
 * Copyright:   
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-01-14 14:51:32 
 */
package hry.admin.ico.account.service.impl;

import com.github.pagehelper.Page;
import hry.admin.ico.account.dao.IcoBuyOrderDao;
import hry.admin.ico.account.model.IcoBuyOrder;
import hry.admin.ico.account.service.IcoBuyOrderService;
import hry.bean.PageResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;

import hry.util.PageFactory;
import hry.util.QueryFilter;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * <p> IcoBuyOrderService </p>
 * @author:         lzy
 * @Date :          2019-01-14 14:51:32  
 */
@Service("icoBuyOrderService")
public class IcoBuyOrderServiceImpl extends BaseServiceImpl<IcoBuyOrder, Long> implements IcoBuyOrderService{
	
	@Resource(name="icoBuyOrderDao")
	@Override
	public void setDao(BaseDao<IcoBuyOrder, Long> dao) {
		super.dao = dao;
	}


	@Override
	public PageResult findPageBySql(QueryFilter filter) {

		//----------------------分页查询头部外壳------------------------------
		//创建PageResult对象
		Page<IcoBuyOrder> page = PageFactory.getPage(filter);
		//----------------------分页查询头部外壳------------------------------
		//----------------------查询开始------------------------------
		String email = filter.getRequest().getParameter("email");
		String mobilePhone = filter.getRequest().getParameter("mobilePhone");
		String payCoinCode = filter.getRequest().getParameter("payCoinCode");
		String startTime = filter.getRequest().getParameter("startTime");
		String endTime = filter.getRequest().getParameter("endTime");
		Map<String,Object> map = new HashMap<String,Object>();
		if(!StringUtils.isEmpty(email)){
			map.put("email", "%"+email+"%");
		}
		if(!StringUtils.isEmpty(mobilePhone)){
			map.put("mobilePhone", "%"+mobilePhone+"%");
		}
		if(!StringUtils.isEmpty(endTime)){
			map.put("endTime",endTime);
		}if(!StringUtils.isEmpty(startTime)){
			map.put("startTime",startTime);
		}if(!StringUtils.isEmpty(payCoinCode)){
			map.put("payCoinCode",payCoinCode);
		}
		((IcoBuyOrderDao)dao).findPageBySql(map);
		//----------------------分页查询底部外壳------------------------------
		return new PageResult(page, filter.getPage(),filter.getPageSize());
	}
}
