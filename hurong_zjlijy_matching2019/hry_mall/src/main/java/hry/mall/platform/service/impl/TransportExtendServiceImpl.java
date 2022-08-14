/**
 * Copyright:   
 * @author:      kongdebiao
 * @version:     V1.0 
 * @Date:        2018-12-04 16:17:57 
 */
package hry.mall.platform.service.impl;

import com.github.pagehelper.Page;
import hry.bean.PageResult;
import javax.annotation.Resource;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.mall.platform.dao.TransportExtendDao;
import hry.mall.platform.model.Transport;
import hry.mall.platform.model.TransportExtend;
import hry.mall.platform.service.TransportExtendService;
import hry.mall.platform.service.TransportService;
import hry.util.PageFactory;
import hry.util.QueryFilter;
import org.springframework.stereotype.Service;
import java.util.Date;


/**
 * <p> TransportExtendService </p>
 * @author:         kongdebiao
 * @Date :          2018-12-04 16:17:57  
 */
@Service("transportExtendService")
public class TransportExtendServiceImpl extends BaseServiceImpl<TransportExtend, Long> implements TransportExtendService {

	//private static final Object JsonUtils = ;

	@Resource(name="transportExtendDao")
	@Override
	public void setDao(BaseDao<TransportExtend, Long> dao) {
		super.dao = dao;
	}
	@Resource
	TransportService transportService;



	@Override
	public PageResult findPageBySql(QueryFilter filter) {
		Page<TransportExtend> page = PageFactory.getPage(filter);
		((TransportExtendDao) dao).findPageBySql();
		return new PageResult(page, filter.getPage(), filter.getPageSize());
	}

	@Override
	public void saveExtend(String title, String tranStr,String type,String isDefault) {
		Transport transport = new Transport();
		transport.setIsDefault(0);
		transport.setType(Integer.parseInt(type));
		if("1".equals(isDefault)){
			QueryFilter queryFilter = new QueryFilter(Transport.class);
			queryFilter.addFilter("isDefault=",isDefault);
			Transport transport1 = transportService.get(queryFilter);
			if(null != transport1){
				transport1.setIsDefault(0);
				transportService.update(transport1);
			}
		}
		transport.setIsDefault(Integer.parseInt(isDefault));
		transport.setTitle(title);
		transport.setModified(new Date());
		transportService.save(transport);
		saveTransportExtend(tranStr,title,transport.getId());
	}

	@Override
	public void editExtend(String title, String tranStr, String type, String transportId, String isDefault) {
		Transport transport = transportService.get(Long.parseLong(transportId));
		transport.setIsDefault(0);
		transport.setType(Integer.parseInt(type));
		if("1".equals(isDefault)){
			QueryFilter queryFilter = new QueryFilter(Transport.class);
			queryFilter.addFilter("isDefault=",isDefault);
			Transport transport1 = transportService.get(queryFilter);
			if(null != transport1){
				transport1.setIsDefault(0);
				transportService.update(transport1);
			}
		}
		transport.setIsDefault(Integer.parseInt(isDefault));
		transport.setTitle(title);
		transport.setModified(new Date());
		transportService.update(transport);
		QueryFilter queryFilter =new QueryFilter(TransportExtend.class);
		queryFilter.addFilter("transportId=",transportId);
		super.delete(queryFilter);
		saveTransportExtend(tranStr,title,transport.getId());
	}

	private void saveTransportExtend(String tranStr, String title, long transportId){

	/*	List<Object> list = JsonUtils.readJsonList(tranStr, TransportExtend.class);

		for(Object obj : list){
			TransportExtend transportExtend = (TransportExtend) obj;
			if(transportExtend.getAreaId() == null || transportExtend.getAreaName() == null){
				transportExtend.setAreaId("");
				transportExtend.setAreaName("全国");
			}
			transportExtend.setTransportTitle(title);
			transportExtend.setTransportId(transportId);
			//保存模板扩展
			super.save(transportExtend);
		}*/

	}
	public static void main(String[] args) {
		//String st = "[{"ddd":1,"b":"weqe"}]";
		//String st ="[{"snum":"1","sprice":"11","xnum":"1","xprice":"11"}]";
	}
}

