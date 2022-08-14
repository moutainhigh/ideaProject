/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2020-12-17 16:38:15 
 */
package hry.licqb.exchange.service.impl;

import hry.licqb.exchange.model.ExchangeImage;
import hry.licqb.exchange.service.ExchangeImageService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> ExchangeImageService </p>
 * @author:         zhouming
 * @Date :          2020-12-17 16:38:15  
 */
@Service("exchangeImageService")
public class ExchangeImageServiceImpl extends BaseServiceImpl<ExchangeImage, Long> implements ExchangeImageService{
	
	@Resource(name="exchangeImageDao")
	@Override
	public void setDao(BaseDao<ExchangeImage, Long> dao) {
		super.dao = dao;
	}
	

}
