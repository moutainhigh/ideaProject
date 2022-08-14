/**
 * Copyright:   
 * @author:      luyue
 * @version:     V1.0 
 * @Date:        2019-05-16 20:33:22 
 */
package hry.mall.retailstore.service.impl;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.mall.retailstore.model.CustomerGrowth;
import hry.mall.retailstore.model.GrowthConfig;
import hry.mall.retailstore.model.MemberLevel;
import hry.mall.retailstore.service.CustomerGrowthService;
import hry.mall.retailstore.service.GrowthConfigService;
import hry.mall.retailstore.service.MemberLevelService;
import hry.util.QueryFilter;
import java.math.BigDecimal;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> CustomerGrowthService </p>
 * @author:         luyue
 * @Date :          2019-05-16 20:33:22  
 */
@Service("customerGrowthService")
public class CustomerGrowthServiceImpl extends BaseServiceImpl<CustomerGrowth, Long> implements CustomerGrowthService {
	@Resource
	public GrowthConfigService growthConfigService;
	
	@Resource
	public MemberLevelService memberLevelService;
	
	@Resource(name="customerGrowthDao")
	@Override
	public void setDao(BaseDao<CustomerGrowth, Long> dao) {
		super.dao = dao;
	}

	@Override
	public void saveCustomerGrowth(Long memberId) {
		// TODO Auto-generated method stub
		CustomerGrowth growth=new CustomerGrowth();
		BigDecimal ling=new BigDecimal("0");
		growth.setMemberId(memberId);
		growth.setAvailablelGrowth(ling);
		growth.setFreezeGrowth(ling);
		growth.setTotalGrowth(ling);
	      String growthName="";
          List<GrowthConfig>  list=growthConfigService.findAll();
             if(null!=list && list.size()>0){
          	   growthName=list.get(0).getName();
             }
         growth.setGrowthName(growthName);
           //查询默认会员等级
          QueryFilter filter=new QueryFilter(MemberLevel.class);
          filter.addFilter("isDefault=", 1);
          List<MemberLevel>  mlist=memberLevelService.find(filter);
          Long levelId=new Long("0");
          String levelName="";
          if(null!=mlist && mlist.size()>0){
      	   levelId=mlist.get(0).getId();
      	   levelName=mlist.get(0).getName();
          }else{
              QueryFilter filter1=new QueryFilter(MemberLevel.class);
	            filter.setOrderby("id desc");;
	            List<MemberLevel>  mlist1=memberLevelService.find(filter1);
	            if(null!=mlist1 && mlist1.size()>0){
	            	   levelId=mlist1.get(0).getId();
	            	   levelName=mlist1.get(0).getName();
		            }
           }
          growth.setLevelId(levelId);
          growth.setLevelName(levelName);
		  this.save(growth);
	}
	
}
