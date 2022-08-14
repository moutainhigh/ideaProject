/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年5月17日 上午11:21:53
 */
package hry.web.article.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.web.app.model.AppFriendLink;
import hry.web.article.service.AppFriendLinkService;

/**
 * <p> TODO</p>
 * @author:         Wu Shuiming
 * @Date :          2016年5月17日 上午11:21:53 
 */
@Service("appFriendLinkService")
public class AppFriendLinkServiceImpl extends BaseServiceImpl<AppFriendLink, Long> implements AppFriendLinkService{

	@Resource(name = "appFriendLinkDao")
	@Override
	public void setDao(BaseDao<AppFriendLink, Long> dao) {
		super.dao = dao;
	}

}
