/**
 * Copyright:   
 * @author:      javalx
 * @version:     V1.0 
 * @Date:        2019-06-10 11:13:59 
 */
package hry.social.miningreward.service.impl;

import com.github.pagehelper.Page;
import hry.bean.PageResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;

import hry.social.manage.remote.model.miningreward.SocialMiningGatherRecord;
import hry.social.miningreward.dao.SocialMiningGatherRecordDao;
import hry.social.miningreward.service.SocialMiningGatherRecordService;
import hry.util.PageFactory;
import hry.util.QueryFilter;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * <p> SocialMiningGatherRecordService </p>
 * @author:         javalx
 * @Date :          2019-06-10 11:13:59  
 */
@Service("socialMiningGatherRecordService")
public class SocialMiningGatherRecordServiceImpl extends BaseServiceImpl<SocialMiningGatherRecord, Long> implements SocialMiningGatherRecordService {
	
	@Resource(name="socialMiningGatherRecordDao")
	@Override
	public void setDao(BaseDao<SocialMiningGatherRecord, Long> dao) {
		super.dao = dao;
	}

	/**
	 * 分页查询
	 *
	 * @param filter
	 * @return
	 */
	@Override
	public PageResult findPageList(QueryFilter filter) {
		//----------------------分页查询头部外壳------------------------------
		Page<SocialMiningGatherRecord> page = PageFactory.getPage(filter);
		//----------------------分页查询头部外壳------------------------------
		//----------------------查询开始------------------------------
		String customerId = filter.getRequest().getParameter("customerId_EQ");
		String gatherId = filter.getRequest().getParameter("gatherId_EQ");
		String gatherType = filter.getRequest().getParameter("gatherType_EQ");
		String coinCode = filter.getRequest().getParameter("coinCode");
		String mobilePhone = filter.getRequest().getParameter("mobilePhone_like");
		String surname = filter.getRequest().getParameter("surname_like");
		String trueName = filter.getRequest().getParameter("trueName_like");
		String gatherTimeGT = filter.getRequest().getParameter("gatherTime_GT");
		String gatherTimeLT = filter.getRequest().getParameter("gatherTime_LT");
		String createdGT = filter.getRequest().getParameter("created_GT");
		String createdLT = filter.getRequest().getParameter("created_LT");
		Map<String,Object> map = new HashMap<String,Object>();

		if (!StringUtils.isEmpty(customerId)) {
			map.put("customerId", customerId);
		}
		if (!StringUtils.isEmpty(gatherId)) {
			map.put("gatherId", gatherId);
		}
		if (!StringUtils.isEmpty(gatherType)) {
			map.put("gatherType", gatherType);
		}
		if (!StringUtils.isEmpty(coinCode)) {
			map.put("coinCode", coinCode);
		}
		if (!StringUtils.isEmpty(mobilePhone)) {
			map.put("mobilePhone", "%" + mobilePhone + "%");
		}
		if (!StringUtils.isEmpty(surname)) {
			map.put("surname", "%" + surname + "%");
		}
		if (!StringUtils.isEmpty(trueName)) {
			map.put("trueName", "%" + trueName + "%");
		}
		if (!StringUtils.isEmpty(gatherTimeGT)) {
			map.put("gatherTimeGT", gatherTimeGT);
		}
		if (!StringUtils.isEmpty(gatherTimeLT)) {
			map.put("gatherTimeLT", gatherTimeLT);
		}
		if (!StringUtils.isEmpty(createdGT)) {
			map.put("createdGT", createdGT);
		}
		if (!StringUtils.isEmpty(createdLT)) {
			map.put("createdLT", createdLT);
		}
		((SocialMiningGatherRecordDao) dao).findPageList(map);
		return new PageResult(page, filter.getPage(), filter.getPageSize());
	}

	@Override
	public SocialMiningGatherRecord footing(QueryFilter filter) {
		SocialMiningGatherRecord str = null;
		//----------------------查询开始------------------------------
		String customerId = filter.getRequest().getParameter("customerId_EQ");
		String gatherId = filter.getRequest().getParameter("gatherId_EQ");
		String gatherType = filter.getRequest().getParameter("gatherType_EQ");
		String coinCode = filter.getRequest().getParameter("coinCode");
		String mobilePhone = filter.getRequest().getParameter("mobilePhone_like");
		String surname = filter.getRequest().getParameter("surname_like");
		String trueName = filter.getRequest().getParameter("trueName_like");
		String gatherTimeGT = filter.getRequest().getParameter("gatherTime_GT");
		String gatherTimeLT = filter.getRequest().getParameter("gatherTime_LT");
		String createdGT = filter.getRequest().getParameter("created_GT");
		String createdLT = filter.getRequest().getParameter("created_LT");
		Map<String,Object> map = new HashMap<String,Object>();

		if (!StringUtils.isEmpty(customerId)) {
			map.put("customerId", customerId);
		}
		if (!StringUtils.isEmpty(gatherId)) {
			map.put("gatherId", gatherId);
		}
		if (!StringUtils.isEmpty(gatherType)) {
			map.put("gatherType", gatherType);
		}
		if (!StringUtils.isEmpty(coinCode)) {
			map.put("coinCode", coinCode);
		}
		if (!StringUtils.isEmpty(mobilePhone)) {
			map.put("mobilePhone", "%" + mobilePhone + "%");
		}
		if (!StringUtils.isEmpty(surname)) {
			map.put("surname", "%" + surname + "%");
		}
		if (!StringUtils.isEmpty(trueName)) {
			map.put("trueName", "%" + trueName + "%");
		}
		if (!StringUtils.isEmpty(gatherTimeGT)) {
			map.put("gatherTimeGT", gatherTimeGT);
		}
		if (!StringUtils.isEmpty(gatherTimeLT)) {
			map.put("gatherTimeLT", gatherTimeLT);
		}
		if (!StringUtils.isEmpty(createdGT)) {
			map.put("createdGT", createdGT);
		}
		if (!StringUtils.isEmpty(createdLT)) {
			map.put("createdLT", createdLT);
		}
		str = ((SocialMiningGatherRecordDao) dao).footing(map);
		return str;
	}

	/**
	 * 查询收取记录
	 *
	 * @param customerId 收取用户ID
	 * @param pickId     果园奖励ID
	 * @return
	 */
	@Override
	public int hasPick(Long customerId, Long pickId) {
		return ((SocialMiningGatherRecordDao) dao).hasPick(customerId, pickId);
	}


}
