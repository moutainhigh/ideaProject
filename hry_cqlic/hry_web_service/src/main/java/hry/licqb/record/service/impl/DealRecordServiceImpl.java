/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-08-14 15:21:47 
 */
package hry.licqb.record.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.ico.model.IcoDividendRecord;
import hry.licqb.record.dao.DealRecordDao;
import hry.licqb.record.model.CommendInfo;
import hry.licqb.record.model.DealRecord;
import hry.licqb.record.service.DealRecordService;
import hry.licqb.util.DealEnum;
import hry.util.QueryFilter;
import hry.util.date.DateUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * <p> DealRecordService </p>
 * @author:         zhouming`
 * @Date :          2019-08-14 15:21:47  
 */
@Service("dealRecordService")
public class DealRecordServiceImpl extends BaseServiceImpl<DealRecord, Long> implements DealRecordService{
	
	@Resource(name="dealRecordDao")
	@Override
	public void setDao(BaseDao<DealRecord, Long> dao) {
		super.dao = dao;
	}

	@Override
	public List<DealRecord> findNewDealRecord(Map<String, Object> map) {
		return ((DealRecordDao)dao).findNewDealRecord(map);
	}


	@Override
	public int getCountNewDealRecord(Map<String, Object> map) {
		return ((DealRecordDao)dao).getCountNewDealRecord(map);
	}

	@Override
	public DealRecord findNewlyTeamAsset(Map<String, String> map) {
		List<DealRecord> recordList = ((DealRecordDao)dao).findNewlyTeamAsset(map);
		return (recordList != null && recordList.size() > 0) ? recordList.get(0) : null;
	}

	@Override
	public DealRecord findDateTeamAsset(Map<String, String> map) {
		List<DealRecord> recordList = ((DealRecordDao)dao).findDateTeamAsset(map);
		return (recordList != null && recordList.size() > 0) ? recordList.get(0) : null;
	}

	@Override
	public DealRecord findDateTeamAssetTwo(Map<String, String> map) {
		List<DealRecord> recordList = ((DealRecordDao)dao).findDateTeamAssetTwo(map);
		return (recordList != null && recordList.size() > 0) ? recordList.get(0) : null;
	}

	@Override
	public BigDecimal countDealMoney(Map<String, Object> map) {
		return ((DealRecordDao)dao).countDealMoney(map);
	}

	@Override
	public BigDecimal countActualMoney(Map<String, Object> map) {
		return ((DealRecordDao)dao).countActualMoney(map);
	}

	@Override
	public List<DealRecord> findPageBySql(Map<String, Object> map) {
		return ((DealRecordDao)dao).findPageBySql(map);
	}

	@Override
	public Map<String, Object> countTeamUser(Map<String, String> map) {
		List<Map<String, Object>> maps = ((DealRecordDao)dao).countTeamUser(map);
		if (maps != null && maps.size() > 0){
			Map<String, Object> resultMap = maps.get(0);
			return resultMap;
		} else {
			return null;
		}
	}

	@Override
	public BigDecimal countTeamDealMoney(Map<String, Object> map) {
		return ((DealRecordDao)dao).countTeamDealMoney(map);
	}

	@Override
	public List<CommendInfo> findCommendUserInfo(Map<String, String> map) {
		return ((DealRecordDao)dao).findCommendUserInfo(map);
	}

	@Override
	public Integer findUserLevelNum(Map<String, String> map) {
		return ((DealRecordDao)dao).findUserLevelNum(map);
	}

	@Override
	public List<Map<String, String>> findEarningsTopTen() {
		return ((DealRecordDao)dao).findEarningsTopTen();
	}

	@Override
	public synchronized void addChargeRecord(Map<String,Object> map) {
		DealRecord dealRecord = new DealRecord();
		dealRecord.setCustomerId(Long.valueOf(map.get("customerId").toString()));
		dealRecord.setAccountId(Long.valueOf(map.get("accountId").toString()));
		dealRecord.setTransactionNum(map.get("transactionNum").toString());
		dealRecord.setCoinCode(map.get("coinCode").toString());
		dealRecord.setDealMoney(new BigDecimal(map.get("money").toString()));
		dealRecord.setDealType(Integer.parseInt(DealEnum.TYPE15.getIndex()));
		String remark = DealEnum.SITE15.getName();
		// 1: 链上充币 2：手动充币
		if ("2".equals(map.get("chargeType"))) {
			remark = DealEnum.SITE16.getName();
		}
		dealRecord.setRemark(remark);
		super.save(dealRecord);
	}

	/*
	* ############ lc_deal_record 分表相关操作---目前未用到 ###################
	* */

	@Override
	public void separateTable() {
		// 对lc_deal_record 中created字段按月份分表
		long startDate = System.currentTimeMillis();
		List<String> monthsList = ((DealRecordDao)dao).findGroupByCreated();
		for (int i = 0; i < monthsList.size(); i++) {
			if (i+1 == monthsList.size()) {
				break;
			}
			long start = System.currentTimeMillis();
			String tableName = "lc_deal_record_"+monthsList.get(i).replace("-","");
			String sTime = monthsList.get(i) + "-01";
			String eTime = monthsList.get(i+1)+ "-01";
			// 校验表是否存在
			if (((DealRecordDao)dao).existTable(tableName) == 0) {
				// 创建表
				((DealRecordDao)dao).createNewTable(tableName);
			}
			// 1 迁移指定数据
			((DealRecordDao)dao).backupsData(tableName, sTime, eTime);
			long bTime = System.currentTimeMillis();
			System.out.println(tableName+"数据迁移成功---耗时："+ (bTime-start)+"ms");
			// 2 添加业务相关数据统计
			// 2.1、每月迁移数据 用的dealType中 dealMoney总数统计
			((DealRecordDao)dao).totalMonthsData(sTime, eTime);
			long tTime = System.currentTimeMillis();
			System.out.println(tableName+"统计数据插入成功---耗时："+ (tTime-bTime)+"ms");
			// 2.2、更新用户每月 dealType中 dealMoney总数统计
			// 先清空二次统计表
			((DealRecordDao)dao).delTotalAllData();
			((DealRecordDao)dao).totalAllData();
			System.out.println(tableName+"二次统计数据插入成功");
			// 3 删除迁移的原始数据
			((DealRecordDao)dao).delBatch(sTime, eTime);
			long dTime = System.currentTimeMillis();
			System.out.println(tableName+"原始数据删除成功---耗时"+ (dTime-bTime)+"ms");
		}
		Long endDate = System.currentTimeMillis();
		System.out.println(DateUtil.getNewDate()+"此次数据迁移---耗时"+ (endDate-startDate)+"ms");

	}
}
