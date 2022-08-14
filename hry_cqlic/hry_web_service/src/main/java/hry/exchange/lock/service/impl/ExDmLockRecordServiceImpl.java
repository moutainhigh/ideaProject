/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      liuchenghui
 * @version:     V1.0 
 * @Date:        2018-04-27 16:33:26 
 */
package hry.exchange.lock.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import hry.exchange.lock.model.ExDmLockRecord;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import hry.core.mvc.dao.base.BaseDao;
import hry.bean.PageResult;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.util.QueryFilter;
import hry.exchange.lend.model.ExDmLend;
import hry.exchange.lock.dao.ExDmLockRecordDao;
import hry.exchange.lock.service.ExDmLockRecordService;
import hry.exchange.transaction.model.ExDmTransaction;

/**
 * <p> ExDmLockRecordService </p>
 * @author:         liuchenghui
 * @Date :          2018-04-27 16:33:26  
 */
@Service("exDmLockRecordService")
public class ExDmLockRecordServiceImpl extends BaseServiceImpl<ExDmLockRecord, Long> implements ExDmLockRecordService{
	
	@Resource(name="exDmLockRecordDao")
	@Override
	public void setDao(BaseDao<ExDmLockRecord, Long> dao) {
		super.dao = dao;
	}

	@Override
	public PageResult findPageBySql(QueryFilter filter) {
		// ----------------------分页查询头部外壳------------------------------
		// 创建PageResult对象
		PageResult pageResult = new PageResult();
		Page<ExDmLend> page = null;
		if (filter.getPageSize().compareTo(Integer.valueOf(-1)) == 0) {
			// pageSize = -1 时
			// pageHelper传pageSize参数传0查询全部
			page = PageHelper.startPage(filter.getPage(), 0);
		} else {
			page = PageHelper.startPage(filter.getPage(), filter.getPageSize());
		}
		// ----------------------分页查询头部外壳------------------------------

		// ----------------------查询开始------------------------------
		
		// 邮箱
		String email = filter.getRequest().getParameter("email");
		// 手机号
		String mobilePhone = filter.getRequest().getParameter("mobilePhone");
		// 订单号
		String transactionNum = filter.getRequest().getParameter("transactionNum");

		Map<String, Object> map = new HashMap<String, Object>();
		
		// 邮箱
		if (!StringUtils.isEmpty(email)) {
			map.put("email", "%" + email + "%");
		}
		// 手机号
		if (!StringUtils.isEmpty(mobilePhone)) {
			map.put("mobilePhone", "%" + mobilePhone + "%");
		}
		
		// 订单号
		if (!StringUtils.isEmpty(transactionNum)) {
			map.put("transactionNum", "%" + transactionNum + "%");
		}

		
		((ExDmLockRecordDao) dao).findPageBySql(map);
		// 设置分页数据
		pageResult.setRows(page.getResult());
		// 设置总记录数
		pageResult.setRecordsTotal(page.getTotal());
		pageResult.setDraw(filter.getDraw());
		pageResult.setPage(filter.getPage());
		pageResult.setPageSize(filter.getPageSize());

		return pageResult;
	}

	/**
	 * 获取今天释放的记录
	 * @return
	 */
	@Override
	public List<ExDmLockRecord> getRecordBycurdate() {
		Map<String, Object> map = new HashMap<String, Object>();
		return ((ExDmLockRecordDao) dao).getRecordBycurdate(map);
	}

}
