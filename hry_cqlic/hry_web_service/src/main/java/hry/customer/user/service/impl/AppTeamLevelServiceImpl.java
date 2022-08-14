/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-08-15 15:30:25 
 */
package hry.customer.user.service.impl;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.customer.user.model.AppCustomer;
import hry.customer.user.model.AppTeamLevel;
import hry.customer.user.service.AppCustomerService;
import hry.customer.user.service.AppTeamLevelService;
import hry.util.QueryFilter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p> AppTeamLevelService </p>
 * @author:         zhouming
 * @Date :          2019-08-15 15:30:25  
 */
@Service("appTeamLevelService")
public class AppTeamLevelServiceImpl extends BaseServiceImpl<AppTeamLevel, Long> implements AppTeamLevelService{

	@Resource
	private AppCustomerService appCustomerService;

	@Resource(name="appTeamLevelDao")
	@Override
	public void setDao(BaseDao<AppTeamLevel, Long> dao) {
		super.dao = dao;
	}

	@Override
	public void saveTeamLevel(AppCustomer customer) {
		// TODO Auto-generated method stub
		try {
			AppCustomer appCustomer =null;
			if(customer.getCommendCode()!=null&&!customer.getCommendCode().equals("")) {//有没有推荐码
				QueryFilter filter=new QueryFilter(AppCustomer.class);
				filter.addFilter("referralCode=", customer.getCommendCode());

				appCustomer = appCustomerService.get(filter);
				if(appCustomer!=null){
					Long parentId=appCustomer.getId();//直推荐人Id
					Long customerId=customer.getId();//注册人Id
					List<AppTeamLevel> saveList=new ArrayList<>();
					List<AppTeamLevel> AppTeamlevels=super.find(new QueryFilter(AppTeamLevel.class).addFilter("customerId=",parentId));
					AppTeamLevel save=new AppTeamLevel();
					save.setCustomerId(customerId);
					save.setParentId(parentId);
					save.setLevel(1);//直推
					save.setCustomerInvitationCode(customer.getReferralCode());
					save.setParentInvitationCode(customer.getCommendCode());
					saveList.add(save);
					for (AppTeamLevel k:AppTeamlevels) {
						if(k.getParentId().intValue()==0){
							continue;
						}
						AppTeamLevel sa=new AppTeamLevel();
						sa.setCustomerId(customerId);
						sa.setParentId(k.getParentId());
						sa.setLevel(k.getLevel()+1);
						sa.setCustomerInvitationCode(customer.getReferralCode());
						sa.setParentInvitationCode(k.getParentInvitationCode());
						saveList.add(sa);
					}
					if(saveList!=null&&saveList.size()>0){
						super.saveAll(saveList);
					}
				}
			}else{//没有推荐码
				AppTeamLevel AppTeamlevel=new AppTeamLevel();
				AppTeamlevel.setCustomerId(customer.getId());
				AppTeamlevel.setLevel(0);//层级
				AppTeamlevel.setParentId(0l);//推荐人
				AppTeamlevel.setCustomerInvitationCode(customer.getReferralCode());
				super.save(AppTeamlevel);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
