/**
 * Copyright:   
 * @author:      denghf
 * @version:     V1.0 
 * @Date:        2019-01-12 19:48:59 
 */
package hry.ico.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import hry.bean.FrontPage;
import hry.bean.ObjectUtil;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.ico.dao.IcoExperienceDao;
import hry.ico.model.IcoExperience;
import hry.ico.remote.model.RemoteIcoExperience;
import hry.ico.service.IcoExperienceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p> IcoUpgradeConfigService </p>
 * @author:         denghf
 * @Date :          2019-01-12 19:48:59  
 */
@Service("icoExperienceService")
public class IcoExperienceServiceImpl extends BaseServiceImpl<IcoExperience, Long> implements IcoExperienceService{

	@Resource(name="icoExperienceDao")
	@Override
	public void setDao(BaseDao<IcoExperience, Long> dao) {
		super.dao = dao;
	}


	//获取用户经验明细
	@Override
	public FrontPage queryExperiencesDetail(Map<String ,String> hashMap){
		String offset=hashMap.get("page")==null?"0":hashMap.get("page");
		String limit=hashMap.get("pageSize")==null?"10":hashMap.get("pageSize");
		if(Integer.parseInt(limit)>100){
			limit="10";
		}
		Page<IcoExperience> page = PageHelper.startPage(Integer.parseInt(offset), Integer.parseInt(limit));
		List<IcoExperience> icoExperiences = ((IcoExperienceDao) dao).queryExperiencesDetail(hashMap);
		for(int i=0;i<icoExperiences.size();i++){
			IcoExperience icoExperience = icoExperiences.get(i);
			//交易类型（0101 锁仓奖励，0102 注册赠送，0201 锁仓扣除，0202 释放扣除）
			if(icoExperience.getAccount_type().equals("0101")){
				icoExperience.setAccount_type("锁仓奖励");
			}
			if(icoExperience.getAccount_type().equals("0102")){
				icoExperience.setAccount_type("注册赠送");
			}
			if(icoExperience.getAccount_type().equals("0201")){
				icoExperience.setAccount_type("锁仓扣除");
			}
			if(icoExperience.getAccount_type().equals("0202")){
				icoExperience.setAccount_type("释放扣除");
			}
		}
		List<RemoteIcoExperience> result= ObjectUtil.beanList(icoExperiences,RemoteIcoExperience.class);
		return new FrontPage(result, page.getTotal(), page.getPages(), page.getPageSize());

	}


}
