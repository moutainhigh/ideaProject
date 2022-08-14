/**
 * Copyright:   
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-04-18 19:00:43 
 */
package hry.admin.klg.limit.service.impl;

import com.github.pagehelper.Page;
import hry.admin.klg.limit.dao.KlgDesignatedRewardecordDao;
import hry.admin.klg.limit.model.KlgDesignatedRewardecord;
import hry.admin.klg.limit.service.KlgDesignatedRewardecordService;
import hry.bean.BaseManageUser;
import hry.bean.PageResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;

import hry.util.PageFactory;
import hry.util.QueryFilter;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p> KlgDesignatedRewardecordService </p>
 * @author:         lzy
 * @Date :          2019-04-18 19:00:43  
 */
@Service("klgDesignatedRewardecordService")
public class KlgDesignatedRewardecordServiceImpl extends BaseServiceImpl<KlgDesignatedRewardecord, Long> implements KlgDesignatedRewardecordService{
	
	@Resource(name="klgDesignatedRewardecordDao")
	@Override
	public void setDao(BaseDao<KlgDesignatedRewardecord, Long> dao) {
		super.dao = dao;
	}


	@Override
	public PageResult findPageBySql(QueryFilter filter) {
		Page<KlgDesignatedRewardecord> page = PageFactory.getPage(filter);
		String email = filter.getRequest().getParameter("email");
		String mobilePhone = filter.getRequest().getParameter("mobilePhone");
		String startTime = filter.getRequest().getParameter("startTime");
		String endTime = filter.getRequest().getParameter("endTime");
		Map<String,Object> map = new HashMap<String,Object>();
		if(!StringUtils.isEmpty(email)){
			map.put("email", "%"+email+"%");
		}
		if(!StringUtils.isEmpty(mobilePhone)){
			map.put("mobilePhone", "%"+mobilePhone+"%");
		}if(!StringUtils.isEmpty(endTime)){
			map.put("endTime", endTime);
		}if(!StringUtils.isEmpty(startTime)){
			map.put("startTime",startTime);
		}
		((KlgDesignatedRewardecordDao)dao).findPageBySql(map);
		return new PageResult(page, filter.getPage(),filter.getPageSize());
	}

	@Override
	public void saveLimitationRecord(BaseManageUser user, KlgDesignatedRewardecord designatedRewardecord) {
		Long operatorId=0l;
		String operatorName="系统";
		if(user!=null){
			operatorId=Long.valueOf(user.getId().toString());
			operatorName=user.getUsername();
		}
		designatedRewardecord.setOperatorId(operatorId);
		designatedRewardecord.setOperatorIdName(operatorName);
		super.save(designatedRewardecord);
	}
}
