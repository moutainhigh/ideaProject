/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-08-12 17:44:07 
 */
package hry.admin.licqb.platform.service.impl;

import com.github.pagehelper.Page;
import hry.admin.licqb.platform.dao.ApplyTeamAwardDao;
import hry.admin.licqb.platform.model.ApplyTeamAward;
import hry.admin.licqb.platform.model.CustomerLevel;
import hry.admin.licqb.platform.service.ApplyTeamAwardService;
import hry.admin.licqb.platform.service.CustomerLevelService;
import hry.admin.licqb.record.model.DealRecord;
import hry.admin.licqb.record.model.ReleaseRuleDetails;
import hry.admin.licqb.record.service.ReleaseRuleDetailsService;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.util.PageFactory;
import hry.util.QueryFilter;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p> ApplyTeamAwardService </p>
 * @author:         zhouming
 * @Date :          2019-08-12 17:44:07  
 */
@Service("applyTeamAwardService")
public class ApplyTeamAwardServiceImpl extends BaseServiceImpl<ApplyTeamAward, Long> implements ApplyTeamAwardService{

	@Resource
	private ReleaseRuleDetailsService releaseRuleDetailsService;
	@Resource
	private CustomerLevelService customerLevelService;

	@Resource(name="applyTeamAwardDao")
	@Override
	public void setDao(BaseDao<ApplyTeamAward, Long> dao) {
		super.dao = dao;
	}

	@Override
	public JsonResult updateReleaseRule(Long applyId,Long customerId) {
		//此时不更新团队业绩 只更新亮灯的开始时间、结束时间、释放状态
		QueryFilter filter = new QueryFilter(ReleaseRuleDetails.class);
		filter.addFilter("customerId=",customerId);
		ReleaseRuleDetails details = releaseRuleDetailsService.get(filter);
		Date now = new Date();
		details.setStartTime(now);
		details.setEndTime(DateUtils.addDays(now, 31));
		releaseRuleDetailsService.update(details);

		// 修改释放状态
		QueryFilter userFilter = new QueryFilter(CustomerLevel.class);
		userFilter.addFilter("customerId=",customerId);
		CustomerLevel level = customerLevelService.get(userFilter);
		level.setIsTeamAward(1);
		customerLevelService.update(level);

		// 修改申请状态
		ApplyTeamAward applyTeamAward = super.get(applyId);
		applyTeamAward.setAuditStatus(1);
		super.update(applyTeamAward);

		return new JsonResult(true);
	}

	@Override
	public PageResult findApplyPage(QueryFilter filter) {
		//----------------------分页查询头部外壳------------------------------
		//创建PageResult对象
		Page<ApplyTeamAward> page = PageFactory.getPage(filter);
		Map<String, String> map = new HashMap<>();
		String customerId = filter.getRequest().getParameter("customerId");
		String email = filter.getRequest().getParameter("email");
		String mobilePhone = filter.getRequest().getParameter("mobilePhone");
		String socialType = filter.getRequest().getParameter("socialType");
		String applyStatus = filter.getRequest().getParameter("applyStatus");
		String auditStatus = filter.getRequest().getParameter("auditStatus");
		if(!StringUtils.isEmpty(customerId)){
			map.put("customerId", customerId);
		}
		if(!StringUtils.isEmpty(email)){
			map.put("email", "%"+email+"%");
		}
		if(!StringUtils.isEmpty(mobilePhone)){
			map.put("mobilePhone", "%"+mobilePhone+"%");
		}
		if(!StringUtils.isEmpty(socialType)){
			map.put("socialType", socialType);
		}
		if(!StringUtils.isEmpty(applyStatus)){
			map.put("applyStatus", applyStatus);
		}
		if(!StringUtils.isEmpty(auditStatus)){
			map.put("auditStatus", auditStatus);
		}
		((ApplyTeamAwardDao)dao).findApplyPage(map);
		return new PageResult(page, filter.getPage(),filter.getPageSize());
	}

	@Override
	public ApplyTeamAward getApplyTeamAward(Long id) {
		Map<String, String> map = new HashMap<>();
		map.put("id", id.toString());
		List<ApplyTeamAward> list = ((ApplyTeamAwardDao)dao).findApplyPage(map);
		return list != null && list.size() > 0?list.get(0):null;
	}
}
