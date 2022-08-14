/**
 * Copyright:   
 * @author:      houzhen
 * @version:     V1.0 
 * @Date:        2019-06-19 19:39:19 
 */
package hry.mall.integral.service.impl;


import hry.bean.JsonResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.mall.integral.dao.ShopWithdrawRecordDao;
import hry.mall.integral.model.ShopWithdrawRecord;
import hry.mall.integral.service.ShopWithdrawRecordService;
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
import java.util.Map;

/**
 * <p> ShopWithdrawRecordService </p>
 * @author:         houzhen
 * @Date :          2019-06-19 19:39:19  
 */
@Service("shopWithdrawRecordService")
public class ShopWithdrawRecordServiceImpl extends BaseServiceImpl<ShopWithdrawRecord, Long> implements ShopWithdrawRecordService {
	
	@Resource(name="shopWithdrawRecordDao")
	@Override
	public void setDao(BaseDao<ShopWithdrawRecord, Long> dao) {
		super.dao = dao;
	}

	@Resource
	private MallConfigService mallConfigService;


	@Override
	public List<Map<String, Object>> getAppDic(String pKey){
		return ((ShopWithdrawRecordDao)dao).getAppDic(pKey);
	}

	@Override
	public JsonResult isWithdraw(Long memberId, BigDecimal applicationAmount){
		JsonResult jsonResult = new JsonResult();
		String reg = "^[1-9]\\d*$";
		if(null == applicationAmount || !applicationAmount.toString().matches(reg)){
			return jsonResult.setSuccess(false).setMsg("dayulingdezhengshu");//请输入大于0的整数
		}

		List<MallConfig> all = mallConfigService.findAll();
		if(null != all && all.size() > 0){
			MallConfig mallConfig = all.get(0);
			BigDecimal singleMinWithdrawalAmount = mallConfig.getSingleMinWithdrawalAmount();//单次最小提现金额
			BigDecimal oneDayMaxWithdrawalAmount = mallConfig.getOneDayMaxWithdrawalAmount();//单日最大提现金额
			if(!StringUtils.isEmpty(singleMinWithdrawalAmount)) {
				//判断提现金额是否过小
				int i = applicationAmount.compareTo(singleMinWithdrawalAmount);
				if(i<0){
					return jsonResult.setSuccess(false).setMsg("tixianjineguoxiao");//提现金额太小
				}
			}
			//查询今日的提现记录
			QueryFilter queryFilter = new QueryFilter(ShopWithdrawRecord.class);
			queryFilter.addFilter("status_in","1,2");
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String format1 = format.format(new Date());
			String s = format1 + " 59:59:59";
			queryFilter.addFilter("created<=", s);
			String s1 = format1 + " 00:00:00";
			queryFilter.addFilter("created>=",s1);
			queryFilter.addFilter("memberId=",memberId);
			queryFilter.addFilter("source=",3);
			List<ShopWithdrawRecord> shopWithdrawRecords = this.find(queryFilter);
			//提现金额累加
			for ( ShopWithdrawRecord shopWithdrawRecord: shopWithdrawRecords) {
				applicationAmount=applicationAmount.add(shopWithdrawRecord.getApplicationAmount());
			}
			if(!StringUtils.isEmpty(oneDayMaxWithdrawalAmount)) {
				//判断提现金额是否过大
				int i = applicationAmount.compareTo(oneDayMaxWithdrawalAmount);
				if(i==1){
					return jsonResult.setSuccess(false).setMsg("jintiantixianeduyiyongwan");//当日提现金额过大
				}
			}

		}
		jsonResult.setSuccess(true);
		return jsonResult;
	}
}
