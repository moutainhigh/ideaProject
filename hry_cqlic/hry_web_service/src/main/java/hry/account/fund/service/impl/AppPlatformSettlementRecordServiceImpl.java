package hry.account.fund.service.impl;

import hry.account.fund.dao.AppPlatformSettlementRecordDao;
import hry.account.fund.model.AppPlatformSettlementRecord;
import hry.account.fund.service.AppPlatformSettlementRecordService;
import hry.core.mvc.dao.base.BaseDao;
import hry.bean.PageResult;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.util.QueryFilter;
import hry.customer.user.model.AppCustomer;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * <p> TODO</p>
 * @author:         Zhang Lei 
 * @Date :          2017年3月9日 下午1:45:43
 */
@Service("appPlatformSettlementRecordService")
public class AppPlatformSettlementRecordServiceImpl extends BaseServiceImpl<AppPlatformSettlementRecord, Long> implements AppPlatformSettlementRecordService{
	@Resource(name="appPlatformSettlementRecordDao")
	@Override
	public void setDao(BaseDao<AppPlatformSettlementRecord, Long> dao) {
		super.dao = dao;
	}

	@Override
	public PageResult findPageBySql(QueryFilter filter) {
		
		//----------------------分页查询头部外壳------------------------------
		//创建PageResult对象
		PageResult pageResult = new PageResult();
		Page<AppCustomer> page = null;
		if(filter.getPageSize().compareTo(Integer.valueOf(-1))==0){
			//pageSize = -1 时  
			//pageHelper传pageSize参数传0查询全部
			page= PageHelper.startPage(filter.getPage(), 0);
		}else{
			page = PageHelper.startPage(filter.getPage(), filter.getPageSize());
		}
		//----------------------分页查询头部外壳------------------------------
		
		//----------------------查询开始------------------------------
		
		String trueName = filter.getRequest().getParameter("trueName_like");
		String mobilePhone = filter.getRequest().getParameter("mobilePhone_like");
		
		Map<String,Object> map = new HashMap<String,Object>();
		if(!StringUtils.isEmpty(trueName)){
			map.put("trueName", "%"+trueName+"%");
		}
		
		if(!StringUtils.isEmpty(mobilePhone)){
			map.put("mobilePhone", "%"+mobilePhone+"%");
		}
		((AppPlatformSettlementRecordDao)dao).findPageBySql(map);
		//----------------------查询结束------------------------------
		
		//----------------------分页查询底部外壳------------------------------
		//设置分页数据
		pageResult.setRows(page.getResult());
		//设置总记录数
		pageResult.setRecordsTotal(page.getTotal());
		pageResult.setDraw(filter.getDraw());
		pageResult.setPage(filter.getPage());
		pageResult.setPageSize(filter.getPageSize());
		//----------------------分页查询底部外壳------------------------------
		
		return pageResult;
	}

	/**
	 * 一段时间内的充值总金额
	 * <p> TODO</p>
	 * @author:         Zhang Lei
	 * @param:    @param beginDate
	 * @param:    @param endDate
	 * @param:    @return
	 * @return: BigDecimal 
	 * @Date :          2017年4月7日 下午6:22:33   
	 * @throws:
	 */
	@Override
	public BigDecimal getRechargeMoney(String beginDate, String endDate) {
		return ((AppPlatformSettlementRecordDao)dao).getRechargeMoney(beginDate,endDate);
	}
	/**
	 * 一段时间内的充值总金额
	 * <p> TODO</p>
	 * @author:         Zhang Lei
	 * @param:    @param beginDate
	 * @param:    @param endDate
	 * @param:    @return
	 * @return: BigDecimal 
	 * @Date :          2017年4月7日 下午6:22:33   
	 * @throws:
	 */
	@Override
	public BigDecimal getWithdrawalsMoney(String beginDate, String endDate) {
		return ((AppPlatformSettlementRecordDao)dao).getWithdrawalsMoney(beginDate,endDate);
	}
	/**
	 * 一段时间内的充值总金额
	 * <p> TODO</p>
	 * @author:         Zhang Lei
	 * @param:    @param beginDate
	 * @param:    @param endDate
	 * @param:    @return
	 * @return: BigDecimal 
	 * @Date :          2017年4月7日 下午6:22:33   
	 * @throws:
	 */
	@Override
	public BigDecimal getTradeFeeMoney(String beginDate, String endDate) {
		return ((AppPlatformSettlementRecordDao)dao).getTradeFeeMoney(beginDate,endDate);
	}
	/**
	 * 一段时间内的充值总金额
	 * <p> TODO</p>
	 * @author:         Zhang Lei
	 * @param:    @param beginDate
	 * @param:    @param endDate
	 * @param:    @return
	 * @return: BigDecimal 
	 * @Date :          2017年4月7日 下午6:22:33   
	 * @throws:
	 */
	@Override
	public BigDecimal getTranFeeMoney(String beginDate, String endDate) {
		return ((AppPlatformSettlementRecordDao)dao).getTranFeeMoney(beginDate,endDate);
	}
}
