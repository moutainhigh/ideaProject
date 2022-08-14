/**
 * Copyright:   
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-07-11 14:27:21 
 */
package hry.admin.web.service.impl;

import com.github.pagehelper.Page;
import hry.admin.customer.model.AppCustomer;
import hry.admin.exchange.dao.AppCoinRewardRecordDao;
import hry.admin.web.dao.WhiteListDao;
import hry.admin.web.model.WhiteList;
import hry.admin.web.service.WhiteListService;
import hry.bean.PageResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.util.PageFactory;
import hry.util.QueryFilter;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * <p> WhiteListService </p>
 * @author:         liushilei
 * @Date :          2018-07-11 14:27:21  
 */
@Service("whiteListService")
public class WhiteListServiceImpl extends BaseServiceImpl<WhiteList, Long> implements WhiteListService{
	
	@Resource(name="whiteListDao")
	@Override
	public void setDao(BaseDao<WhiteList, Long> dao) {
		super.dao = dao;
	}

	@Override
	public PageResult findCustomListByPage (Map<String, String> paraMap) {
		Page page = PageFactory.getPage(paraMap);

		((WhiteListDao)dao).findCustomListByPage(paraMap);

		return new PageResult(page, page.getPageNum(), page.getPageSize());
	}

	@Override
	public PageResult findWhiteListBySql (Map<String, String> paraMap) {
		Page page = PageFactory.getPage(paraMap);
		((WhiteListDao)dao).findWhiteListBySql(paraMap);
		return new PageResult(page, page.getPageNum(), page.getPageSize());
	}
}
