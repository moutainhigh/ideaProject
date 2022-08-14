/**
 * Copyright:   
 * @author:      kongdebiao
 * @version:     V1.0 
 * @Date:        2018-12-04 16:17:26 
 */
package hry.mall.platform.service.impl;

import javax.annotation.Resource;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.mall.platform.model.Transport;
import hry.mall.platform.model.TransportExtend;
import hry.mall.platform.service.TransportExtendService;
import hry.mall.platform.service.TransportService;
import hry.util.QueryFilter;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * <p> TransportService </p>
 * @author:         kongdebiao
 * @Date :          2018-12-04 16:17:26  
 */
@Service("transportService")
public class TransportServiceImpl extends BaseServiceImpl<Transport, Long> implements TransportService {
	
	@Resource(name="transportDao")
	@Override
	public void setDao(BaseDao<Transport, Long> dao) {
		super.dao = dao;
	}

	@Resource
	private TransportExtendService transportExtendService;
	@Resource
	private TransportService transportService;

	@Override
	public List<Transport> findPageBySql(QueryFilter filter) {

        List<Transport> transportList = transportService.find(filter);

        if (transportList != null) {

			// 查询出各运费模板相关的运费模板扩展表数据
			for (Transport transport : transportList) {
				QueryFilter filter1 = new QueryFilter(TransportExtend.class);
				if (transport != null && transport.getId() != null) {
                    filter1.addFilter("transportId=",transport.getId());
                    List<TransportExtend> transportExtendList = transportExtendService.find(filter1);
                    transport.setTransportExtendList(transportExtendList);
				}
			}
			return transportList;
		}
		return null;
	}

}
