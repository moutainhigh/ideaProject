/**
 * Copyright:   北京互融时代软件有限公司
 *
 * @author: Wu Shuiming
 * @version: V1.0
 * @Date: 2016年3月24日 下午2:04:29
 */
package hry.trade.entrust;

import hry.bean.JsonResult;
import hry.core.constant.StringConstant;
import hry.customer.commend.dao.AppCommendTradeDao;
import hry.customer.commend.service.AppCommendTradeService;
import hry.customer.remote.RemoteAppTradeFactorageService;
import hry.redis.common.utils.RedisService;
import hry.trade.entrust.model.ExOrderInfo;
import hry.trade.entrust.service.ExOrderInfoService;
import hry.util.QueryFilter;
import hry.util.sys.ContextUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import javax.annotation.Resource;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

/**
 * <p>
 * TODO
 * </p>
 *
 * @author: Gao Mimi
 * @Date : 2016年4月12日 下午4:45:50
 */

public class ExOrderInfoServiceNoTrImpl implements ExOrderInfoServiceNoTr {
    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ExOrderInfoServiceNoTrImpl.class);
    @Resource
    public ExOrderInfoService exOrderInfoService;
    @Resource
    public AppCommendTradeDao appCommendTradeDao;
    
    @Resource
    public AppCommendTradeService appCommendTradeService;
    private static final Logger log = LoggerFactory.getLogger(ExOrderInfoServiceNoTrImpl.class);
    @Resource
    private RedisService redisService;
    @Override
    public void timingCulAtferMoney() {
    	appCommendTradeDao.updateIsCulStatus();
        QueryFilter qf = new QueryFilter(ExOrderInfo.class);
        qf.addFilter("isCulAtferMoney=", 0);
        List<ExOrderInfo> list = exOrderInfoService.find(qf);
        logger.error("开始生成佣金.......................:"+list.size()+"记录");
        for (ExOrderInfo l : list) {
            log.info("接收到交易佣金订单号为：{}",l.getOrderNum());
            RemoteAppTradeFactorageService remoteAppTradeFactorageService = (RemoteAppTradeFactorageService) ContextUtil.getBean("remoteAppTradeFactorageService");
            try{
                Boolean flag = remoteAppTradeFactorageService.saveTrade(l.getOrderNum());
                if (flag) {
                    log.info("交易佣金保存成功：{}",l.getOrderNum());
                    l.setIsCulAtferMoney(1);
                    exOrderInfoService.update(l);
                } else {
                    l.setIsCulAtferMoney(2);
                    exOrderInfoService.update(l);
                    log.warn("交易佣金保存失败：{}",l.getOrderNum());
                }
            }catch(Exception e){
                l.setIsCulAtferMoney(3);
                exOrderInfoService.update(l);
                log.error("交易佣金保存异常：{}",l.getOrderNum(),e);
            }
        }
    }

	@Override
	public void payCommissions() {
		 	Map<String, Object> map = new HashMap<>();
	        long current=System.currentTimeMillis();//当前时间毫秒数
	        long zero=current/(1000*3600*24)*(1000*3600*24)-TimeZone.getDefault().getRawOffset();//今天零点零分零秒的毫秒数
	        SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        map.put("beginTime",fm.format(lastDayWholePointDate(new Date())));//前一天的0点0时0分时间
	        map.put("endTime",fm.format(new Date(zero)));//当天0点0时0分
	        List<Map<String,Object>> resultList = appCommendTradeDao.countCommendMoney(map);
	        
	        if(resultList != null && resultList.size()>0){
	        	String fixPriceCoinCode = "DFC";
	        	String standardsLimit = "500";
	    		String string=redisService.get(StringConstant.CONFIG_CACHE+":commendConfig");
	    		JSONArray obj=JSON.parseArray(string);
	    		for (Object o : obj) {
	    			JSONObject oo=JSON.parseObject(o.toString());
	    			if(oo.getString("configkey").equals("maxHierarchy")){
	    				fixPriceCoinCode=oo.getString("value");
	    				continue;
	    			}
	    			if(oo.getString("configkey").equals("minHierarchy")){
	    				standardsLimit=oo.getString("value");
	    				continue;
	    			}
				}
	    		map.put("fixPriceCoinCode", fixPriceCoinCode);//限制交易达标币种
	    		map.put("standardsLimit", standardsLimit);//交易达标额度
	        	for(Map<String,Object> map1 : resultList){
		            BigDecimal sumRewardmoney = new BigDecimal(map1.get("sumRewardmoney").toString());//当天累计佣金
		            String custromerId =  map1.get("custromerId").toString();
		            String coinCode =  map1.get("coinCode").toString();
		            map.put("sumRewardmoney",sumRewardmoney);
		            map.put("custromerId",custromerId);
		            map.put("coinCode",coinCode);
		            JsonResult jsonResult = appCommendTradeService.payCommissionsMoney(map);
		            log.info(jsonResult.getMsg());
		        }
	        }else{
	        	log.info("没有可派发的佣金");
	        }
	        
	}
	
	 /**
     * 计算前一天0点0时0分
     * @param date
     * @return
     */
    public static Date lastDayWholePointDate(Date date) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        if ((gc.get(gc.HOUR_OF_DAY) == 0) && (gc.get(gc.MINUTE) == 0)
                && (gc.get(gc.SECOND) == 0)) {
            return new Date(date.getTime() - (24 * 60 * 60 * 1000));
        } else {
            Date date2 = new Date(date.getTime() - gc.get(gc.HOUR_OF_DAY) * 60 * 60
                    * 1000 - gc.get(gc.MINUTE) * 60 * 1000 - gc.get(gc.SECOND)
                    * 1000 - 24 * 60 * 60 * 1000);
            return date2;
        }
    }

	@Override
	public void buyBackOrder() {
		QueryFilter queryFilter = new QueryFilter(ExOrderInfo.class);
		queryFilter.addFilter("coinCode=", "DFTB");
		queryFilter.addFilter("fixPriceCoinCode=", "DFC");
		queryFilter.addFilter("isBuyBack=", 0);
		List<ExOrderInfo> exOrderInfoList = exOrderInfoService.find(queryFilter);
		String string=redisService.get(StringConstant.CONFIG_CACHE+":basefinanceConfig");
		JSONArray obj=JSON.parseArray(string);
		BigDecimal buyBackProportion = new BigDecimal(30);//回购比例
		for (Object o : obj) {
			JSONObject oo=JSON.parseObject(o.toString());
			if(oo.getString("configkey").equals("buyBackProportion")){
				String val = oo.getString("value");
				if(val != null && !"".equals(val)){
					buyBackProportion = new BigDecimal(val);
				}
				break;
			}
		}
		if(exOrderInfoList != null && exOrderInfoList.size()>0){
			 RemoteAppTradeFactorageService remoteAppTradeFactorageService = (RemoteAppTradeFactorageService) ContextUtil.getBean("remoteAppTradeFactorageService");
			 for (ExOrderInfo exOrderInfo : exOrderInfoList) {
				JsonResult result = remoteAppTradeFactorageService.buyBackOrder(exOrderInfo,buyBackProportion);
				if(result.getSuccess()){
					exOrderInfo.setIsBuyBack(1);//回购下单成功
				}else{
					exOrderInfo.setIsBuyBack(2);//回购下单失败
				}
				exOrderInfoService.update(exOrderInfo);
			}
			 
		}else{
			log.info("没有回购单");
		}
		
	}
}
