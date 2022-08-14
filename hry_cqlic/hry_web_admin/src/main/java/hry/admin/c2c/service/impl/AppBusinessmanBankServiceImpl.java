/**
 * Copyright:   
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-13 13:34:09 
 */
package hry.admin.c2c.service.impl;

import hry.admin.c2c.model.AppBusinessmanBank;
import hry.admin.c2c.service.AppBusinessmanBankService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p> appBusinessmanBankService </p>
 * @author:         liushilei
 * @Date :          2018-06-13 13:34:09  
 */
@Service("appBusinessmanBankService")
public class AppBusinessmanBankServiceImpl extends BaseServiceImpl<AppBusinessmanBank, Long> implements AppBusinessmanBankService {
	
	@Resource(name="appBusinessmanBankDao")
	@Override
	public void setDao(BaseDao<AppBusinessmanBank, Long> dao) {
		super.dao = dao;
	}
	

}
