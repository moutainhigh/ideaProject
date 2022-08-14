/**
 * Copyright:   
 * @author:      liuchenghui
 * @version:     V1.0 
 * @Date:        2018-06-29 11:44:56 
 */
package hry.admin.lock.service.impl;

import com.github.pagehelper.Page;
import hry.admin.lock.dao.ExDmLockDao;
import hry.admin.lock.model.ExDmLock;
import hry.admin.lock.service.ExDmLockService;
import hry.bean.PageResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.util.PageFactory;
import hry.util.QueryFilter;
import hry.util.properties.PropertiesUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p> ExDmLockService </p>
 * @author:         liuchenghui
 * @Date :          2018-06-29 11:44:56  
 */
@Service("exDmLockService")
public class ExDmLockServiceImpl extends BaseServiceImpl<ExDmLock, Long> implements ExDmLockService {
	
	@Resource(name="exDmLockDao")
	@Override
	public void setDao(BaseDao<ExDmLock, Long> dao) {
		super.dao = dao;
	}

	@Override
	public PageResult findPageBySql(QueryFilter filter) {
		//创建PageResult对象
		Page<ExDmLock> page = PageFactory.getPage(filter);

		// ----------------------查询开始------------------------------
		//币种代码
		String coinCode = filter.getRequest().getParameter("coinCode");
		//释放方式
		String releaseMethod = filter.getRequest().getParameter("releaseMethod");
		//锁仓开关
		String isLock = filter.getRequest().getParameter("isLock");

		Map<String, Object> map = new HashMap<String, Object>();
		// 币种代码
		if (!StringUtils.isEmpty(coinCode)) {
			if (!coinCode.equals("all")) {//如果不等于all(全部)
				map.put("coinCode", coinCode);
			}
		}
		// 锁仓结束时间
		if (!StringUtils.isEmpty(releaseMethod)) {
			map.put("releaseMethod", releaseMethod);
		}
		// 锁仓开关
		if (!StringUtils.isEmpty(isLock)) {
			map.put("isLock", isLock);
		}

		((ExDmLockDao) dao).findPageBySql(map);

		return new PageResult(page, filter.getPage(),filter.getPageSize());
	}

	@Override
	public boolean isExistCoinCode (String coinCode) {
		QueryFilter filter = new QueryFilter(ExDmLock.class);
		filter.addFilter("coinCode=", coinCode);
		filter.addFilter("isValid=", 0);
		List<ExDmLock> list = super.find(filter);
		if (list != null && list.size() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public ExDmLock findByCoinCodeInfo (String coinCode) {
		QueryFilter filter = new QueryFilter(ExDmLock.class);
		filter.addFilter("coinCode=", coinCode);
		return super.get(filter);
	}

}
