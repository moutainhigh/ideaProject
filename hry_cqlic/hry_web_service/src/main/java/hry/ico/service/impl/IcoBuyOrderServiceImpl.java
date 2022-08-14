/**
 * Copyright:   
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-01-15 15:40:55 
 */
package hry.ico.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import hry.bean.FrontPage;
import hry.bean.ObjectUtil;
import hry.ico.dao.IcoBuyOrderDao;
import hry.ico.model.IcoBuyOrder;
import hry.ico.model.util.RecommenderOrder;
import hry.ico.remote.model.RemoteIcoBuyOrder;
import hry.ico.remote.model.RemoteRecommend;
import hry.ico.service.IcoBuyOrderService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;

import hry.util.StringUtil;
import org.springframework.stereotype.Service;
import util.BigDecimalUtil;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * <p> IcoBuyOrderService </p>
 * @author:         lzy
 * @Date :          2019-01-15 15:40:55  
 */
@Service("icoBuyOrderService")
public class IcoBuyOrderServiceImpl extends BaseServiceImpl<IcoBuyOrder, Long> implements IcoBuyOrderService{
	
	@Resource(name="icoBuyOrderDao")
	@Override
	public void setDao(BaseDao<IcoBuyOrder, Long> dao) {
		super.dao = dao;
	}

	@Override
	public FrontPage findPageBySql(Map<String,String > hashMap) {
		String offset=hashMap.get("page")==null?"0":hashMap.get("page");
		String limit=hashMap.get("pageSize")==null?"10":hashMap.get("pageSize");
		if(Integer.parseInt(limit)>100){
			limit="10";
		}
		Page<IcoBuyOrder> page = PageHelper.startPage(Integer.parseInt(offset), Integer.parseInt(limit));
		List<IcoBuyOrder> list =((IcoBuyOrderDao)dao).findPageBySql(hashMap);
		List<RemoteIcoBuyOrder> result= ObjectUtil.beanList(list,RemoteIcoBuyOrder.class);
		return new FrontPage(result, page.getTotal(), page.getPages(), page.getPageSize());
	}

	/**
	 * 查询推荐列表
	 * @param hashMap
	 * @return
	 */
	@Override
	public FrontPage finRecommendBySql(Map<String, String> hashMap) {
		Long customerId=Long.valueOf(hashMap.get("customerId"));

		String offset=hashMap.get("page")==null?"0":hashMap.get("page");
		String limit=hashMap.get("pageSize")==null?"10":hashMap.get("pageSize");
		if(Integer.parseInt(limit)>100){
			limit="10";
		}
		Page<RemoteRecommend> page = PageHelper.startPage(Integer.parseInt(offset), Integer.parseInt(limit));
		List<RemoteRecommend> list =((IcoBuyOrderDao)dao).finRecommendBySql(customerId);
		String keepDigit=hashMap.get("keepDigit");//保留位数
		if(StringUtil.isNull(keepDigit)){
			if(list!=null&&list.size()>0){
				for (RemoteRecommend recommend:list) {
					String bNum=recommend.getBuyNumber();
					if(bNum!=null){
						recommend.setBuyNumber(BigDecimalUtil.bigDecimalScaleDigitToString(new BigDecimal(bNum),Integer.valueOf(keepDigit).intValue()));
					}
				}
			}
		}
		return new FrontPage(list, page.getTotal(), page.getPages(), page.getPageSize());
	}
	/**
	 * 查询上级是否有过交易
	 * @return
	 */
	@Override
	public RecommenderOrder finSuperiorCountByCustomerId(Long customerId) {
		return ((IcoBuyOrderDao)dao).finSuperiorCountByCustomerId(customerId);
	}

	/**
	 * 查询推荐列表
	 * @return
	 */
	@Override
	public  List<IcoBuyOrder> findBuyTotal() {
		List<IcoBuyOrder> list =((IcoBuyOrderDao)dao).findBuyTotal();
		return list;
	}

}
