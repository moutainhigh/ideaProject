/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-08-14 15:22:23 
 */
package hry.admin.licqb.record.service.impl;

import com.github.pagehelper.Page;
import hry.admin.commend.model.AppCommendUser;
import hry.admin.commend.service.AppCommendUserService;
import hry.admin.customer.model.AppPersonInfo;
import hry.admin.customer.service.AppPersonInfoService;
import hry.admin.licqb.award.model.OutInfo;
import hry.admin.licqb.award.service.OutInfoService;
import hry.admin.licqb.platform.model.CustomerLevel;
import hry.admin.licqb.record.dao.DealRecordDao;
import hry.admin.licqb.record.model.AppTeamLevel;
import hry.admin.licqb.record.model.DealRecord;
import hry.admin.licqb.record.model.UserTotal;
import hry.admin.licqb.record.service.DealRecordService;
import hry.bean.PageResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.redis.common.utils.RedisService;
import hry.util.PageFactory;
import hry.util.QueryFilter;
import hry.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p> DealRecordService </p>
 * @author:         zhouming
 * @Date :          2019-08-14 15:22:23  
 */
@Service("dealRecordService")
public class DealRecordServiceImpl extends BaseServiceImpl<DealRecord, Long> implements DealRecordService{

	@Resource
	private AppPersonInfoService appPersonInfoService;
	@Resource
	private OutInfoService outInfoService;
	@Resource
	private RedisService redisService;
	@Resource
	private AppCommendUserService appCommendUserService;


	@Resource(name="dealRecordDao")
	@Override
	public void setDao(BaseDao<DealRecord, Long> dao) {
		super.dao = dao;
	}

	@Override
	public PageResult findDealRecordList(QueryFilter filter) {
		//----------------------分页查询头部外壳------------------------------
		//参数集合
		Map<String,Object> map = new HashMap<>();
		//----------------------查询条件获取------------------------------
		String oneDealType = filter.getRequest().getParameter("oneDealType");
		String dealType = filter.getRequest().getParameter("dealType");
		String email = filter.getRequest().getParameter("email");
		String mobilePhone = filter.getRequest().getParameter("mobilePhone");
		String startTime = filter.getRequest().getParameter("startTime");
		String endTime = filter.getRequest().getParameter("endTime");
		String customerId = filter.getRequest().getParameter("customerId");
		String coinCode = filter.getRequest().getParameter("coinCode");
		String transactionNum = filter.getRequest().getParameter("transactionNum");

		if(!StringUtils.isEmpty(transactionNum)){
			map.put("transactionNum", transactionNum+"%");
		}

		if(!StringUtils.isEmpty(email)){
			map.put("email", email+"%");
		}
		if(!StringUtils.isEmpty(mobilePhone)){
			map.put("mobilePhone", mobilePhone+"%");
		}
		if (StringUtils.isEmpty(oneDealType)) {
			if(!StringUtils.isEmpty(dealType)){
				String[] dealTypes = dealType.split(",");
				if (dealTypes.length > 1) {
					map.put("dealTypes", dealTypes);
				} else {
					map.put("dealType", dealType);
				}
			}
		} else {
			map.put("dealType", oneDealType);
		}

		if(!StringUtils.isEmpty(startTime)){
			map.put("startTime", startTime);
		}
		if(!StringUtils.isEmpty(endTime)){
			endTime = endTime.split(" ")[0] + " 23:59:59";
			map.put("endTime", endTime);
		}
		if(!StringUtils.isEmpty(customerId)){
			map.put("customerId", customerId);
		}
		if(!StringUtils.isEmpty(coinCode)){
			map.put("coinCode", coinCode);
		}
		long pageNum = filter.getPage();
		long length = filter.getPageSize();
		long start = (pageNum-1)*length;
		long end = pageNum*length;
		map.put("offset", start);
		map.put("limit", end);
		long s1 = System.currentTimeMillis();
		List<DealRecord> list = ((DealRecordDao)dao).findPageBySql(map);
		Long count = ((DealRecordDao)dao).findPageBySqlCount(map);
		System.out.println("lc_deal_record查询---耗时："+ (System.currentTimeMillis()-s1)+"ms");
		return new PageResult(list, count, filter.getPage(),filter.getPageSize());
	}

	@Override
	public PageResult getUserTotal(QueryFilter filter) {
		List<UserTotal> list = new ArrayList<>();
		UserTotal total = new UserTotal();
		// 总会员人数
		String userTotalStr = redisService.get("UserTotal:userTotal");
		if (StringUtil.isNull(userTotalStr)) {
			total.setUserTotal(Integer.parseInt(userTotalStr));
		} else {
			List<AppPersonInfo> appPersonInfoList = appPersonInfoService.findAll();
			Integer userTotal = appPersonInfoList.size();
			total.setUserTotal(userTotal);
			redisService.save("UserTotal:userTotal", userTotal.toString());
		}
		// 入金会员人数
		String upUserTotalStr = redisService.get("UserTotal:upUserTotal");
		if (StringUtil.isNull(upUserTotalStr)) {
			total.setUpUserTotal(Integer.parseInt(upUserTotalStr));
		} else {
			QueryFilter filter1 = new QueryFilter(OutInfo.class);
			filter1.addFilter("status=", 0);
			List<OutInfo> outInfos = outInfoService.find(filter1);
			Integer upUserTotal = outInfos.size();
			total.setUpUserTotal(upUserTotal);
			redisService.save("UserTotal:upUserTotal", upUserTotal.toString());
		}
		// 未入金会员人数
		int notUpUserTotal = total.getUserTotal() - total.getUpUserTotal();
		total.setNotUpUserTotal(notUpUserTotal);

		// 最大层级数
		String maxTierStr = redisService.get("UserTotal:maxTier");
		if (StringUtil.isNull(maxTierStr)) {
			total.setMaxTier(Integer.parseInt(maxTierStr) + 1);
		} else {
			Integer maxTier = ((DealRecordDao)dao).getUserMaxTier();
			total.setMaxTier(maxTier+1);
			redisService.save("UserTotal:maxTier", maxTier.toString());
		}
		list.add(total);
		return new PageResult(list, 1L, filter.getPage(),filter.getPageSize());
	}

	@Override
	public PageResult findAllUserInfo(QueryFilter filter) {
		Page<AppPersonInfo> page = PageFactory.getPage(filter);
		String email = filter.getRequest().getParameter("email");
		String mobilePhone = filter.getRequest().getParameter("mobilePhone");
		QueryFilter filter1 = new QueryFilter(AppPersonInfo.class);
		if(!StringUtils.isEmpty(email)){
			filter1.addFilter("email=", email);
		}

		if(!StringUtils.isEmpty(mobilePhone)){
			filter1.addFilter("mobilePhone=", mobilePhone);
		}
		appPersonInfoService.find(filter1);
		for (AppPersonInfo info : page.getResult()) {
			AppTeamLevel level = ((DealRecordDao)dao).getAppTeamLevel(info.getCustomerId());
			if (level == null) {
				continue;
			}
			if (level.getParentId() != null && level.getParentId() != 0) {
				QueryFilter filter2 = new QueryFilter(AppPersonInfo.class);
				filter2.addFilter("customerId=", level.getParentId());
				AppPersonInfo personInfo = appPersonInfoService.get(filter2);
				info.setOneParentId(level.getParentId());
				info.setOneParentEmail(personInfo.getEmail());
				info.setOneParentMobile(personInfo.getMobilePhone());
				info.setBelongLevel(level.getLevel() + 1);
				Integer levelTotal = ((DealRecordDao)dao).getUserAllLevel(level.getParentId());
				levelTotal = levelTotal == null ? 0 : levelTotal;
				info.setLevelTotal(levelTotal + 1);
			} else {
				info.setBelongLevel(1);
				Integer levelTotal = ((DealRecordDao)dao).getUserAllLevel(level.getCustomerId());
				levelTotal = levelTotal == null ? 0 : levelTotal;
				info.setLevelTotal(levelTotal + 1);
			}
			// 查询上级推荐人
			QueryFilter filter2 = new QueryFilter(AppCommendUser.class);
			filter2.addFilter("uid=", info.getCustomerId());
			AppCommendUser user = appCommendUserService.get(filter2);
			if (user != null) {
				QueryFilter filter3 = new QueryFilter(AppPersonInfo.class);
				filter3.addFilter("customerId=", user.getPid());
				AppPersonInfo personInfo = appPersonInfoService.get(filter3);
				if (personInfo != null) {
					info.setParentEmail(personInfo.getEmail());
					info.setParentMobile(personInfo.getMobilePhone());
				}
			}
		}
		return new PageResult(page, filter.getPage(),filter.getPageSize());
	}

	@Override
	public PageResult findUserTeamInfo(QueryFilter filter) {
		Page<CustomerLevel> page = PageFactory.getPage(filter);
		//参数集合
		Map<String,Object> map = new HashMap<>();
		String email = filter.getRequest().getParameter("email");
		String mobilePhone = filter.getRequest().getParameter("mobilePhone");
		if(!StringUtils.isEmpty(email)){
			map.put("email", email+"%");
		}
		if(!StringUtils.isEmpty(mobilePhone)){
			map.put("mobilePhone", mobilePhone+"%");
		}
		((DealRecordDao)dao).findUserTeamInfo(map);
		for (CustomerLevel level : page.getResult()) {
			// 社区层级数
			Integer teamTierNum = ((DealRecordDao)dao).getUserAllLevel(level.getCustomerId());
			// 社区总人数
			Integer teamUserNum = ((DealRecordDao)dao).getUserNum(level.getCustomerId());
			level.setTeamTierNum(teamTierNum);
			level.setTeamUserNum(teamUserNum);
		}
		return new PageResult(page, filter.getPage(),filter.getPageSize());
	}

	@Override
	public Long getDealDmMoney(Integer optType) {
		return ((DealRecordDao)dao).getDealDmMoney(optType);
	}
}
