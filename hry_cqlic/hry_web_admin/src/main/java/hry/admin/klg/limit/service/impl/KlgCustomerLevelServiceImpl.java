/**
 * Copyright:
 * @author:      lzy
 * @version:     V1.0
 * @Date:        2019-04-16 19:48:29
 */
package hry.admin.klg.limit.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.pagehelper.Page;

import hry.admin.klg.level.model.KlgLevelConfig;
import hry.admin.klg.level.service.KlgLevelConfigService;
import hry.admin.klg.limit.dao.KlgCustomerLevelDao;
import hry.admin.klg.limit.model.KlgCustomerLevel;
import hry.admin.klg.limit.model.KlgDesignatedRewardecord;
import hry.admin.klg.limit.service.KlgCustomerLevelService;
import hry.admin.klg.utils.BigDecimalUtil;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.core.thread.ThreadPool;
import hry.util.PageFactory;
import hry.util.QueryFilter;

/**
 * <p> KlgCustomerLevelService </p>
 * @author:         lzy
 * @Date :          2019-04-16 19:48:29
 */
@Service("klgCustomerLevelService")
public class KlgCustomerLevelServiceImpl extends BaseServiceImpl<KlgCustomerLevel, Long> implements KlgCustomerLevelService{

	@Resource(name="klgCustomerLevelDao")
	@Override
	public void setDao(BaseDao<KlgCustomerLevel, Long> dao) {
		super.dao = dao;
	}
	@Resource
	private KlgLevelConfigService klgLevelConfigService;

	/**
	 * 发送奖励
	 * @param hashMap
	 * @return
	 */
	@Override
	public JsonResult addReward(HashMap<String, String> hashMap) {
		String listIds=hashMap.get("listIds");//ids
		BigDecimal gradation=hashMap.get("gradation")==null?null:new BigDecimal(hashMap.get("gradation"));//gradation
		BigDecimal rewardNum=hashMap.get("rewardNum")==null?null:new BigDecimal(hashMap.get("rewardNum"));//rewardNum
		Integer pointAlgebra=hashMap.get("pointAlgebra")==null?null:Integer.parseInt(hashMap.get("pointAlgebra"));//pointAlgebra
		String operatorIdName=hashMap.get("operatorIdName");//operatorIdName
		String operatorId=hashMap.get("operatorId");//operatorId
		String[] ids=listIds.split(",");
		if(ids!=null&&ids.length>0){
			for (String str:ids) {
				KlgCustomerLevel customerLevel=new KlgCustomerLevel();
				Long customerId=Long.parseLong(str);
				customerLevel.setCustomerId(customerId);
//				customerLevel.setGradation(gradation);
				customerLevel.setFixedGradation(gradation);//指定级差
//				customerLevel.setRewardNum(customerLevel.getRewardNum()==null?rewardNum:customerLevel.getRewardNum().add(rewardNum));//累计发送
				customerLevel.setRewardNum(rewardNum);
				customerLevel.setPointAlgebra(pointAlgebra);


				int count=((KlgCustomerLevelDao)dao).customerRewardAdd(customerLevel);
				if(count<0){
					continue;
				}
				KlgDesignatedRewardecord rewardecord=new KlgDesignatedRewardecord();
				rewardecord.setCustomerId(customerId);
				rewardecord.setOperatorIdName(operatorIdName);
				rewardecord.setGradation(gradation);
				rewardecord.setRewardNum(rewardNum);
				rewardecord.setPointAlgebra(pointAlgebra);
				rewardecord.setOperatorId(Long.parseLong(operatorId));
				DesignatedRewardRunable designatedRewardRunable=new DesignatedRewardRunable(rewardecord);
				ThreadPool.exe(designatedRewardRunable);
			}
		}
		return new JsonResult().setSuccess(true).setMsg("操作成功");
	}

	@Override
	public PageResult findPageBySql(QueryFilter filter) {
		Page<KlgCustomerLevel> page = PageFactory.getPage(filter);
		Map<String,Object> map = new HashMap<String,Object>();
		String customer_email = filter.getRequest().getParameter("email");
		String customer_mobile = filter.getRequest().getParameter("mobilePhone");
		String startTime = filter.getRequest().getParameter("startTime");
		String endTime = filter.getRequest().getParameter("endTime");
		if(!StringUtils.isEmpty(customer_email)){
			map.put("email", "%"+customer_email+"%" );
		}
		if(!StringUtils.isEmpty(customer_mobile)){
			map.put("mobilePhone", "%"+customer_mobile+"%");
		}
		if(!StringUtils.isEmpty(startTime)){
			map.put("startTime", startTime);
		}
		if(!StringUtils.isEmpty(endTime)){
			map.put("endTime", endTime);
		}
		((KlgCustomerLevelDao)dao).findPageBySql(map);
		return new PageResult(page, filter.getPage(),filter.getPageSize());

	}

	/**
	 *预约成功获得奖励额度
	 */
	@Override
	public void buyRewardQuotaAdd(Long customerId, BigDecimal buyNum) {
		KlgLevelConfig config=klgLevelConfigService.getLevelConfigByCustomerId(customerId);
		if(config!=null){
			BigDecimal bonusMultiple=config.getBonusMultiple();//奖金倍数
			BigDecimal rewardNum= BigDecimalUtil.bigDecimalScale8(buyNum.multiply(bonusMultiple));//奖金倍数*预约金额
			KlgCustomerLevel customerLevel=new KlgCustomerLevel();
			customerLevel.setCustomerId(customerId);
			customerLevel.setRewardNum(rewardNum);
			int count=((KlgCustomerLevelDao)dao).customerRewardAdd(customerLevel);
			if(count<1){
				System.out.println("预约成功获得奖励额度 error"+customerId+"==="+buyNum);
			}
		}
	}
}
