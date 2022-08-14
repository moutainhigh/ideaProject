/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      liuchenghui
 * @version:     V1.0 
 * @Date:        2018-04-25 11:46:57 
 */
package hry.exchange.lock.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import hry.core.mvc.dao.base.BaseDao;
import hry.bean.PageResult;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.util.QueryFilter;
import hry.util.properties.PropertiesUtils;
import hry.exchange.lock.dao.ExDmLockDao;
import hry.exchange.lock.model.ExDmLock;
import hry.exchange.lock.service.ExDmLockService;
import hry.exchange.product.model.ExProduct;

/**
 * <p> ExDmLockService </p>
 * @author:         liuchenghui
 * @Date :          2018-04-25 11:46:57  
 */
@Service("exDmLockService")
public class ExDmLockServiceImpl extends BaseServiceImpl<ExDmLock, Long> implements ExDmLockService{
	
	@Resource(name="exDmLockDao")
	@Override
	public void setDao(BaseDao<ExDmLock, Long> dao) {
		super.dao = dao;
	}

	@Override
	public PageResult findPageBySql(QueryFilter filter) {
		// ----------------------分页查询头部外壳------------------------------
		// 创建PageResult对象
		PageResult pageResult = new PageResult();
		Page<ExDmLock> page = null;
		if (filter.getPageSize().compareTo(Integer.valueOf(-1)) == 0) {
			// pageSize = -1 时
			// pageHelper传pageSize参数传0查询全部
			page = PageHelper.startPage(filter.getPage(), 0);
		} else {
			page = PageHelper.startPage(filter.getPage(), filter.getPageSize());
		}
		// ----------------------分页查询头部外壳------------------------------
		
		// ----------------------查询开始------------------------------
		//币种代码
		String coinCode = filter.getRequest().getParameter("coinCode");
		//锁仓结束时间
		String lockEndTime = filter.getRequest().getParameter("lockEndTime");
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
		if (!StringUtils.isEmpty(lockEndTime)) {
			map.put("lockEndTime", lockEndTime);
		}
		// 锁仓开关
		if (!StringUtils.isEmpty(isLock)) {
			map.put("isLock", isLock);
		}
		
		((ExDmLockDao) dao).findPageBySql(map);
		// 设置分页数据
		pageResult.setRows(page.getResult());
		// 设置总记录数
		pageResult.setRecordsTotal(page.getTotal());
		pageResult.setDraw(filter.getDraw());
		pageResult.setPage(filter.getPage());
		pageResult.setPageSize(filter.getPageSize());

		return pageResult;
	}

	@Override
	public boolean findByCoinCode(String coinCode) {
		QueryFilter filter = new QueryFilter(ExDmLock.class);
		filter.addFilter("coinCode=", coinCode);
		String saasId = PropertiesUtils.APP.getProperty("app.saasId");
		filter.setSaasId(saasId);
		List<ExDmLock> list = super.find(filter);
		if (list != null && list.size() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public ExDmLock findByCoinCodeInfo(String coinCode) {
		QueryFilter filter = new QueryFilter(ExDmLock.class);
		filter.addFilter("coinCode=", coinCode);
		return super.get(filter);
	}
	
}
