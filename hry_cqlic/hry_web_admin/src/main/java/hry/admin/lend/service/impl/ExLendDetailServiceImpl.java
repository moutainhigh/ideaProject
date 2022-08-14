/**
 * Copyright:   
 * @author:      HeC
 * @version:     V1.0 
 * @Date:        2018-10-18 17:58:04 
 */
package hry.admin.lend.service.impl;

import com.github.pagehelper.Page;
import hry.admin.lend.dao.ExLendAccountDao;
import hry.admin.lend.dao.ExLendDetailDao;
import hry.admin.lend.model.ExLendAccount;
import hry.admin.lend.model.ExLendDetail;
import hry.admin.lend.service.ExLendDetailService;
import hry.bean.PageResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;

import hry.reward.model.page.FrontPage;
import hry.util.PageFactory;
import hry.util.QueryFilter;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p> ExLendDetailService </p>
 * @author:         HeC
 * @Date :          2018-10-18 17:58:04  
 */
@Service("exLendDetailService")
public class ExLendDetailServiceImpl extends BaseServiceImpl<ExLendDetail, Long> implements ExLendDetailService{
	
	@Resource(name="exLendDetailDao")
	@Override
	public void setDao(BaseDao<ExLendDetail, Long> dao) {
		super.dao = dao;
	}


	@Override
	public FrontPage findPageByFilter(QueryFilter filter) {
		//创建Page对象
		Page page = PageFactory.getPage(filter);

		Map<String,String> map = new HashMap<>();

		List<ExLendAccount> list = ((ExLendDetailDao)dao).findPageBySql(map);


		return new FrontPage(list, page.getTotal(), page.getPages(), page.getPageSize());
	}
}
