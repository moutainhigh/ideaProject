/**
 * Copyright:   
 * @author:      huanggh
 * @version:     V1.0 
 * @Date:        2019-03-28 11:28:10 
 */
package hry.mall.integral.service.impl;

import hry.bean.JsonResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.mall.integral.model.IntegralTransferOrder;
import hry.mall.integral.service.IntegralTransferOrderService;
import hry.mall.platform.model.MallConfig;
import hry.mall.platform.service.MallConfigService;
import hry.util.QueryFilter;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p> IntegralTransferOrderService </p>
 * @author:         huanggh
 * @Date :          2019-03-28 11:28:10  
 */
@Service("integralTransferOrderService")
public class IntegralTransferOrderServiceImpl extends BaseServiceImpl<IntegralTransferOrder, Long> implements IntegralTransferOrderService {
	
	@Resource(name="integralTransferOrderDao")
	@Override
	public void setDao(BaseDao<IntegralTransferOrder, Long> dao) {
		super.dao = dao;
	}
	@Resource
	private MallConfigService mallConfigService;

	@Override
	public JsonResult isTransfer(Long memberId, BigDecimal amount){

		JsonResult jsonResult = new JsonResult();
//		String reg = "^[1-9]\\d*$";
//		if(null == amount || !amount.toString().matches(reg)){
//			return jsonResult.setSuccess(false).setMsg("dayulingdezhengshu");//请输入大于0的整数
//		}

		List<MallConfig> all = mallConfigService.findAll();
		if(null != all && all.size() > 0){
			MallConfig mallConfig = all.get(0);
			BigDecimal singleMinTransferAmount = mallConfig.getSingleMinTransferAmount();//单次最小转让金额
			BigDecimal oneDayMaxTransferAmount = mallConfig.getOneDayMaxTransferAmount();//单日最大转让金额
			if(!StringUtils.isEmpty(singleMinTransferAmount)) {
				//判断提现金额是否过小
				int i = amount.compareTo(singleMinTransferAmount);
				if(i<0){
					return jsonResult.setSuccess(false).setMsg("zhuanrangjineguoxiao");//转让金额太小
				}
			}
			//查询今日的提现记录
			QueryFilter queryFilter = new QueryFilter(IntegralTransferOrder.class);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String format1 = format.format(new Date());
			String s = format1 + " 59:59:59";
			queryFilter.addFilter("created<=", s);
			String s1 = format1 + " 00:00:00";
			queryFilter.addFilter("created>=",s1);
			queryFilter.addFilter("transferMemberId=",memberId);
			queryFilter.addFilter("state=",2);
			List<IntegralTransferOrder> integralTransferOrders = this.find(queryFilter);
			//提现金额累加
			for ( IntegralTransferOrder integralTransferOrder: integralTransferOrders) {
				amount=amount.add(integralTransferOrder.getAmount());
			}
			if(!StringUtils.isEmpty(oneDayMaxTransferAmount)) {
				//判断提现金额是否过大
				int i = amount.compareTo(oneDayMaxTransferAmount);
				if(i==1){
					return jsonResult.setSuccess(false).setMsg("jintianzhuanrangeduyiyongwan");//当日转让额度已用完
				}
			}

		}
		jsonResult.setSuccess(true);
		return jsonResult;
	}

}
