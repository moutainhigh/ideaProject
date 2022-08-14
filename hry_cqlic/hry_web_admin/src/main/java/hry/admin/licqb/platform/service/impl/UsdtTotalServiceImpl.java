/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2020-06-11 12:01:59 
 */
package hry.admin.licqb.platform.service.impl;

import com.github.pagehelper.Page;
import hry.admin.exchange.service.ExDmTransactionService;
import hry.admin.licqb.platform.dao.UsdtTotalDao;
import hry.admin.licqb.platform.model.UsdtTotal;
import hry.admin.licqb.platform.service.UsdtTotalService;
import hry.bean.PageResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.util.PageFactory;
import hry.util.QueryFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p> UsdtTotalService </p>
 * @author:         zhouming
 * @Date :          2020-06-11 12:01:59  
 */
@Service("usdtTotalService")
public class UsdtTotalServiceImpl extends BaseServiceImpl<UsdtTotal, Long> implements UsdtTotalService{

	@Autowired
	private ExDmTransactionService exDmTransactionService;

	@Resource(name="usdtTotalDao")
	@Override
	public void setDao(BaseDao<UsdtTotal, Long> dao) {
		super.dao = dao;
	}

	@Override
	public PageResult findPageUserTotalUsdt(QueryFilter filter) {
		Page<UsdtTotal> page = PageFactory.getPage(filter);
		//参数集合
		Map<String,Object> map = new HashMap<>();
		String email = filter.getRequest().getParameter("email");
		String mobilePhone = filter.getRequest().getParameter("mobilePhone");
		if(!StringUtils.isEmpty(email)){
			map.put("email", "%"+email+"%");
		}
		if(!StringUtils.isEmpty(mobilePhone)){
			map.put("mobilePhone", "%"+mobilePhone+"%");
		}
		List<UsdtTotal> totalList = ((UsdtTotalDao)dao).findUsdtTotalList(map);
		/*totalList.stream().forEach((total) -> {
			total.setSurplusMoney(total.getPayMoney().subtract(total.getExtractMoney()));
		});*/
		return new PageResult(page, filter.getPage(),filter.getPageSize());
	}

	@Override
	public PageResult findUserDetailPageList(QueryFilter filter) {
		Page<UsdtTotal> page = PageFactory.getPage(filter);
		// 交易订单号
		String transactionNum = filter.getRequest().getParameter("transactionNum");
		// 交易类型
		String transactionType = filter.getRequest().getParameter("transactionType");
		// 用户Id
		String customerId = filter.getRequest().getParameter("customerId");
		// 交易类型
		if (!org.apache.commons.lang3.StringUtils.isEmpty(transactionType)) {
			filter.addFilter("transactionType=", transactionType);
		}
		// 交易订单号
		if (!org.apache.commons.lang3.StringUtils.isEmpty(transactionNum)) {
			filter.addFilter("transactionNum_like","%" + transactionNum + "%");
		}
		filter.addFilter("status=", 2);
		filter.addFilter("customerId=", customerId);
		exDmTransactionService.find(filter);
		return new PageResult(page, filter.getPage(),filter.getPageSize());
	}
}
