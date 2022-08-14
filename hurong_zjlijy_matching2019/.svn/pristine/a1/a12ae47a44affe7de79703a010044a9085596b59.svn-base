package hry.financail.customer.service.impl;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.financail.customer.model.AppCommendUser;
import hry.financail.customer.service.AppCommendUserService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service("appCommendUserService")
public class AppCommendUserServiceImpl extends BaseServiceImpl<AppCommendUser, Long> implements AppCommendUserService {
	private final static Logger log = Logger.getLogger(AppCommendUserServiceImpl.class);
	@Resource(name="appCommendUserDao")
	@Override
	public void setDao(BaseDao<AppCommendUser, Long> dao) {
		super.dao = dao;
	}


}
