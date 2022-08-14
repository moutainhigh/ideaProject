/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-07-31 15:43:26 
 */
package hry.cm.dividend.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import hry.cm.customer.model.CmCustomer;
import hry.cm.deal.CmDealUtil;
import hry.cm.dividend.dao.CmFeeRecordDao;
import hry.cm.dividend.model.CmDividendConfig;
import hry.cm.dividend.model.CmDividendRecord;
import hry.cm.dividend.model.CmFeeRecord;
import hry.cm.dividend.service.CmDividendConfigService;
import hry.cm.dividend.service.CmDividendRecordService;
import hry.cm.dividend.service.CmFeeRecordService;
import hry.cm.dto.Accountadd;
import hry.cm.dto.CmAccountRedis;
import hry.cm.enums.CmAccountRemarkEnum;
import hry.cm.log.service.CmTaskLogService;
import hry.cm.mq.model.CoinRewardMessage;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.mq.producer.DealMsgUtil;
import hry.mq.producer.service.MessageProducer;
import hry.util.SpringUtil;
import hry.util.idgenerate.IdGenerate;
import hry.utils.BaseConfUtil;

/**
 * <p> cmFeeRecordService </p>
 * @author:         zhouming
 * @Date :          2019-07-31 15:43:26  
 */
@Service("cmFeeRecordService")
public class CmFeeRecordServiceImpl extends BaseServiceImpl<CmFeeRecord, Long> implements CmFeeRecordService {
	
	@Resource(name="cmFeeRecordDao")
	@Override
	public void setDao(BaseDao<CmFeeRecord, Long> dao) {
		super.dao = dao;
	}
	
	@Resource
    public CmDividendConfigService cmDividendConfigService;
	
	@Resource
    public CmDividendRecordService cmDividendRecordService;
	@Resource
	public MessageProducer messageProducer;

	@Override
	public void fafangBonus(String message) {
		// TODO Auto-generated method stub
		Date startDate = new Date();
		CmTaskLogService cmTaskLogService = SpringUtil.getBean("cmTaskLogService");
		String ids = message;
		if(ids==null||ids.equals("")){
			return;
		}
		List<CmDividendConfig> configList = cmDividendConfigService.findAll();
		Integer type = 0;
        if (configList != null && configList.size() > 0) {
            // 获取平台币
            String platCoin = BaseConfUtil.getConfigSingle("platCoin", "configCache:basefinanceConfig");
            // 获取分红配置信息 --- fe
            CmDividendConfig config = configList.get(0);
            // ratio分红比例（%）
            /*String ratio = config.getRatio();
            if(ratio==null||ratio.equals("")){
            	Date endDate = new Date();
        		String functionName = "fafangBonus:ratio="+ratio;
        		cmTaskLogService.saveLog(functionName, 0, startDate, endDate);
            	return;
            }*/
            //需求改动，分红比例不要了
            String ratio = "100";
            // type 1：果树领取 2：自动发放
            type = config.getType();

            // 获取开启分红的用户
            List<CmCustomer> cmCustomerList = cmDividendRecordService.findCmCustomer();
            if(cmCustomerList==null||cmCustomerList.size()<=0){
            	Date endDate = new Date();
        		String functionName = "fafangBonus:personNum="+cmCustomerList.size();
        		cmTaskLogService.saveLog(functionName, 0, startDate, endDate);
            	return;
            }
            /**查询分红手续费总数，计算没人分红数量**/
            Map<String, Object> map = new HashMap<String, Object>();
    		String[] strarray = ids.split(",");
    		List<String> strsToList = Arrays.asList(strarray);
    		map.put("ids", strsToList);
    		//查询选中id手续费总数
    		BigDecimal feeSum = ((CmFeeRecordDao)dao).findFeeSumByIds(map);
            if(feeSum==null||feeSum.compareTo(new BigDecimal(0))<=0){
            	Date endDate = new Date();
        		String functionName = "fafangBonus:feeSum=0";
        		cmTaskLogService.saveLog(functionName, 0, startDate, endDate);
            	return;
            }
            //分红人数
            int personNum = cmCustomerList.size();
            //分红比例
            BigDecimal ratioBd = new BigDecimal(ratio).multiply(new BigDecimal(0.01));
            //每人获取 = （手续费总数*分红比例）/分红人数
            BigDecimal obtainNum = feeSum.multiply(ratioBd).divide(new BigDecimal(personNum), 4, BigDecimal.ROUND_HALF_UP);
            //发放手续费分红
            for(CmCustomer cmCustomer:cmCustomerList){
            	// 生成发放记录
                CmDividendRecord record = new CmDividendRecord();
                record.setCustomerId(cmCustomer.getCustomerId());
                record.setCmCustomerId(cmCustomer.getId());
                record.setCoinCode(platCoin);
                record.setDividendGross(feeSum);
                record.setRatio(ratio);
                record.setDividend(obtainNum);
                Integer status = 1;
                if (type == 2) {
                    // 发放分红
                    boolean flag = healeCount(cmCustomer.getCustomerId(), platCoin, obtainNum);
                    if (flag) {
                        status = 2;
                    }
                }else{
                	/**果树领取*/
    				//3.发送消息到果树
    				CoinRewardMessage coinRewardMessage = new CoinRewardMessage();
    				coinRewardMessage.setBusinessNum("");
    				coinRewardMessage.setBusinessType("");
    				coinRewardMessage.setCoinCode(platCoin);
    				coinRewardMessage.setCustomerId(cmCustomer.getCustomerId());
    				coinRewardMessage.setRewardNum(obtainNum);
    				coinRewardMessage.setRewardType(12);
    				messageProducer.toSocialRewardKey(JSON.toJSONString(coinRewardMessage));
                }
                record.setStatus(status);
                cmDividendRecordService.save(record);
            }
        }
        
        
        Date endDate = new Date();
		String functionName = "fafangBonus:type="+type;
		cmTaskLogService.saveLog(functionName, 0, startDate, endDate);
	}
	
    /**
     * 发放--操作子账户
     * @param customerId 用户Id
     * @param coinCode 币种编码
     * @param dividend 分红数量
     * */
    private boolean healeCount(Long customerId, String coinCode, BigDecimal dividend) {
        boolean flag = false;
        try {
            /** 查询用户平台币账户信息 */
            CmAccountRedis cmAccountRedis = CmDealUtil.getCmAccount(customerId, coinCode);
            List<Accountadd> accountaddList = new ArrayList<>();
            Accountadd accountadd = new Accountadd(customerId, coinCode, cmAccountRedis.getId(),
                    dividend, 1, CmAccountRemarkEnum.TYPE6.getIndex(),
                    IdGenerate.transactionNum("DI"));
            accountaddList.add(accountadd);
            DealMsgUtil.sendAccountaddList(accountaddList);
            flag = true;
        } catch (Exception e) {
            System.out.println("操作子账户失败");
        }
        return flag;

    }
	

}
