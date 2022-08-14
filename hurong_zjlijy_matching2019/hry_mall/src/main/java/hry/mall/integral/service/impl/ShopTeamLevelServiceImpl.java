/**
 * Copyright:   
 * @author:      houzhen
 * @version:     V1.0 
 * @Date:        2019-05-31 09:52:20 
 */
package hry.mall.integral.service.impl;

import com.github.pagehelper.Page;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.mall.integral.dao.ShopTeamLevelDao;
import hry.mall.integral.model.CustomerIntegral;
import hry.mall.integral.model.ShopMemberPerformance;
import hry.mall.integral.model.ShopTeamLevel;
import hry.mall.integral.service.CustomerIntegralService;
import hry.mall.integral.service.IntegralConfigService;
import hry.mall.integral.service.ShopMemberPerformanceService;
import hry.mall.integral.service.ShopTeamLevelService;
import hry.mall.member.commend.model.AppCommendUser;
import hry.mall.member.commend.service.AppCommendUserService;
import hry.util.PageFactory;
import hry.util.QueryFilter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p> ShopTeamLevelService </p>
 * @author:         houzhen
 * @Date :          2019-05-31 09:52:20  
 */
@Service("shopTeamLevelService")
public class ShopTeamLevelServiceImpl extends BaseServiceImpl<ShopTeamLevel, Long> implements ShopTeamLevelService {
	
	@Resource(name="shopTeamLevelDao")
	@Override
	public void setDao(BaseDao<ShopTeamLevel, Long> dao) {
		super.dao = dao;
	}

	@Resource
	private IntegralConfigService integralConfigService;
	@Resource
	private CustomerIntegralService customerIntegralService;
	@Resource
	private AppCommendUserService appCommendUserService;
	@Resource
	private ShopMemberPerformanceService shopMemberPerformanceService;

	@Override
	public PageResult findPageBySql(QueryFilter filter) {


		//----------------------分页查询头部外壳------------------------------
		//创建PageResult对象
		Page<Map<String,Object>> page = PageFactory.getPage(filter);

		//----------------------分页查询头部外壳------------------------------

		//----------------------查询开始------------------------------

		Map<String,Object> map = new HashMap<String,Object>();

		((ShopTeamLevelDao)dao).findPageBySql(map);

		//----------------------分页查询底部外壳------------------------------

		return new PageResult(page, filter.getPage(),filter.getPageSize());

	}

	@Override
	public JsonResult updateTeamLevel(Long memberId, Integer series){
		JsonResult jsonResult = new JsonResult();
		try {

			QueryFilter queryFilter = new QueryFilter(AppCommendUser.class);
			queryFilter.addFilter("uid=",memberId);
			List<AppCommendUser> appCommendUsers = appCommendUserService.find(queryFilter);
			AppCommendUser appCommendUser = appCommendUsers.get(0);
			String sid = appCommendUser.getSid();
			//判断是否有上级
			if (null != sid && !sid.equals("")){
				//获取上级数组
				String[] split = sid.split(",");
				//维护所有上级的团队等级
				for (int i = 1; i <split.length ; i++) {
					String s = split[i];
					String[] split1 = s.split("-");
					Integer s1 = Integer.valueOf(split1[0]);
					Long s2 = Long.valueOf(split1[1]);

					JsonResult jsonResult1 = this.updateUserTeamLevel(s2);
					if(!jsonResult1.getSuccess()){//不是true
						return  jsonResult1;
					}

				}

			}
			return jsonResult.setSuccess(true);
		}catch (Exception e){
			e.printStackTrace();
			return jsonResult.setSuccess(false).setMsg(e.toString());
		}
	}

	@Override
	public JsonResult updateUserTeamLevel(Long memberId){
		JsonResult jsonResult = new JsonResult();
		try {

			QueryFilter queryFilter = new QueryFilter(ShopTeamLevel.class);
			queryFilter.setOrderby("number desc");//团队等级倒叙
			List<ShopTeamLevel> shopTeamLevels = this.find(queryFilter);
			BigDecimal add=null;
			Integer integer=null;
			if(null != shopTeamLevels){
				for (ShopTeamLevel shopTeamLevel:shopTeamLevels) {
					//判断是否已是这个团队等级
					QueryFilter queryFilter1 = new QueryFilter(CustomerIntegral.class);
					queryFilter1.addFilter("memberId=",memberId);
					CustomerIntegral customerIntegral = customerIntegralService.get(queryFilter1);
					if(null != customerIntegral.getTeamNumber() && customerIntegral.getTeamNumber() >= shopTeamLevel.getNumber()){ //不会降级
						break;
					}

					BigDecimal teamPerformance = shopTeamLevel.getTeamPerformance();//需要的团队业绩
					Integer directPush = shopTeamLevel.getDirectPush();//直推人数
					Integer integralLevelNumber = shopTeamLevel.getIntegralLevelNumber();//直推会员等级序号
					Integer teamLevelNumber = shopTeamLevel.getTeamLevelNumber();//直推团队等级序号
					Map<String, Object> map = new HashMap<>();
					map.put("memberId",memberId);
					map.put("integralLevelNumber",integralLevelNumber);
					map.put("teamLevelNumber",teamLevelNumber);
					integer = ((ShopTeamLevelDao) dao).countTeanPersonNumByParam(map);//符合团队等级要求的直推人数

					//判断直推人数是否符合要求
					if(directPush>integer){
						continue;
					}
					if (null == add){
						QueryFilter queryFilter2 = new QueryFilter(ShopMemberPerformance.class);
						queryFilter2.addFilter("memberId=",memberId);
						ShopMemberPerformance shopMemberPerformance = shopMemberPerformanceService.get(queryFilter2);
						add = shopMemberPerformance.getTeamAllPerformance();
					}
					int i = teamPerformance.compareTo(add);
					//判断团队业绩是否符合要求
					if(i>0){
						continue;
					}else {
						//符合这个团队等级
						if(null == customerIntegral.getTeamNumber() || customerIntegral.getTeamNumber() < shopTeamLevel.getNumber()){ //不会降级
							//if(null == customerIntegral.getTeamNumber() || customerIntegral.getTeamNumber()!=shopTeamLevel.getNumber()){  //会降级
							customerIntegral.setTeamId(shopTeamLevel.getId());
							customerIntegral.setTeamNumber(shopTeamLevel.getNumber());
							customerIntegralService.update(customerIntegral);
						}
					}
					return jsonResult.setSuccess(true);
				}
			}else{
				System.out.println("没有配置团队等级");
			}

			return jsonResult.setSuccess(true);
		}catch (Exception e){
			System.out.println("维护用户："+memberId+"的团队等级失败");
			e.printStackTrace();
			return  jsonResult.setSuccess(false).setMsg(e.toString());
		}
	}

}
