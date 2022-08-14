/**
 * Copyright:   
 * @author:      luyue
 * @version:     V1.0 
 * @Date:        2018-12-04 19:08:23 
 */
package hry.mall.platform.service.impl;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.mall.platform.model.Express;
import hry.mall.platform.service.ExpressService;
import hry.util.QueryFilter;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> ExpressService </p>
 * @author:         luyue
 * @Date :          2018-12-04 19:08:23  
 */
@Service("expressService")
public class ExpressServiceImpl extends BaseServiceImpl<Express, Long> implements ExpressService {
	
	@Resource(name="expressDao")
	@Override
	public void setDao(BaseDao<Express, Long> dao) {
		super.dao = dao;
	}

	@Override
	public String createNum() {
		// TODO Auto-generated method stub
		String knum="";
		//1、查找最大的快递编号
		QueryFilter filter = new QueryFilter(Express.class);
		filter.setOrderby("id desc");
		List<Express>  list=this.find(filter);
		if(null!=list && list.size()>0){
			Express  e=list.get(0);
			Integer num = new Integer(e.getNumber());
			num+=1;
			knum=num.toString();
		}else{
			knum="1001";
		}
		return knum;
	}
	

}
