/**
 * Copyright:   
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-04-17 14:29:49 
 */
package hry.klg.level.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.customer.user.model.AppCustomer;
import hry.customer.user.service.AppCustomerService;
import hry.klg.level.dao.KlgTeamlevelDao;
import hry.klg.level.model.KlgLevelCount;
import hry.klg.level.model.KlgTeamlevel;
import hry.klg.level.model.vo.KlgTeamlevelVo;
import hry.klg.level.service.KlgTeamlevelService;
import hry.util.QueryFilter;

/**
 * <p> KlgTeamlevelService </p>
 * @author:         lzy
 * @Date :          2019-04-17 14:29:49  
 */
@Service("klgTeamlevelService")
public class KlgTeamlevelServiceImpl extends BaseServiceImpl<KlgTeamlevel, Long> implements KlgTeamlevelService{

	@Resource
	private AppCustomerService appCustomerService;

	@Resource(name="klgTeamlevelDao")
	@Override
	public void setDao(BaseDao<KlgTeamlevel, Long> dao) {
		super.dao = dao;
	}
	/**
	 * 新注册关系维护
	 * @param customer
	 */
	@Override
	public void addUser(AppCustomer customer) {
		AppCustomer appCustomer =null;
		if(customer.getCommendCode()!=null&&!customer.getCommendCode().equals("")) {//有没有推荐码
			QueryFilter filter=new QueryFilter(AppCustomer.class);
			filter.addFilter("referralCode=", customer.getCommendCode());
			appCustomer = appCustomerService.get(filter);
			if(appCustomer!=null){
				Long parentId=appCustomer.getId();//直推荐人Id
				Long customerId=customer.getId();//注册人Id
				List<KlgTeamlevel> saveList=new ArrayList<>();
				List<KlgTeamlevel> klgTeamlevels=super.find(new QueryFilter(KlgTeamlevel.class).addFilter("customerId=",parentId));
				KlgTeamlevel save=new KlgTeamlevel();
				save.setCustomerId(customerId);
				save.setParentId(parentId);
				save.setLevel(1);//直推
				saveList.add(save);
				for (KlgTeamlevel k:klgTeamlevels) {
					if(k.getParentId().intValue()==0){
						continue;
					}
					KlgTeamlevel sa=new KlgTeamlevel();
					sa.setCustomerId(customerId);
					sa.setParentId(k.getParentId());
					sa.setLevel(k.getLevel()+1);
					saveList.add(sa);
				}
				if(saveList!=null&&saveList.size()>0){
					super.saveAll(saveList);
				}
			}
		}else{//没有推荐码
			KlgTeamlevel klgTeamlevel=new KlgTeamlevel();
			klgTeamlevel.setCustomerId(customer.getId());
			klgTeamlevel.setLevel(0);//层级
			klgTeamlevel.setParentId(0l);//推荐人
			super.save(klgTeamlevel);
		}
	}

	/**
	 * 根据用户ID 获取上级用户等级配置（奖励额度/见点代数/级差等）
	 * @param customerId
	 * @return
	 */
	@Override
	public List<KlgTeamlevel> getSuperiorLeveConfig(Long customerId) {
		return ((KlgTeamlevelDao)dao).getSuperiorLeveConfig(customerId);
	}

	@Override
	public List<KlgLevelCount> countSubordinateByCustomer(Long customerId) {
		return ((KlgTeamlevelDao)dao).countSubordinateByCustomer(customerId);
	}
	@Override
	public Integer getStarCount(Long customerId, Integer level) {
		// TODO Auto-generated method stub
		return ((KlgTeamlevelDao)dao).getStarCount(customerId, level);
	}
	@Override
	public Integer getNoStarCount(Long customerId, Integer level) {
		// TODO Auto-generated method stub
		return ((KlgTeamlevelDao)dao).getNoStarCount(customerId, level);
	}
	@Override
	public KlgTeamlevelVo getStarVipCount(Long customerId, Integer level) {
		// TODO Auto-generated method stub
		return ((KlgTeamlevelDao)dao).getStarVipCount(customerId, level);
	}
	@Override
	public KlgTeamlevelVo getBuyMoneyByDate(Long customerId, Integer level) {
		// TODO Auto-generated method stub
		return ((KlgTeamlevelDao)dao).getBuyMoneyByDate(customerId, level);
	}
}
