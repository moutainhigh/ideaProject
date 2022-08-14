/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2020-06-05 16:40:12 
 */
package hry.licqb.ecology.service.impl;

import hry.licqb.ecology.dao.EcologEnterDao;
import hry.licqb.ecology.model.EcologEnter;
import hry.licqb.ecology.service.EcologEnterService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p> EcologEnterService </p>
 * @author:         zhouming
 * @Date :          2020-06-05 16:40:12  
 */
@Service("ecologEnterService")
public class EcologEnterServiceImpl extends BaseServiceImpl<EcologEnter, Long> implements EcologEnterService{
	
	@Resource(name="ecologEnterDao")
	@Override
	public void setDao(BaseDao<EcologEnter, Long> dao) {
		super.dao = dao;
	}

	@Override
	public int countEnter(Map<String, Object> map) {
		return ((EcologEnterDao)dao).countEnter(map);
	}
}
