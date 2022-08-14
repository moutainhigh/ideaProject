/**
 * Copyright:
 *
 * @author: zhouming
 * @version: V1.0
 * @Date: 2019-08-01 13:37:58
 */
package hry.cm.dividend.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import hry.bean.JsonResult;
import hry.cm.customer.model.CmCustomer;
import hry.cm.deal.CmDealUtil;
import hry.cm.dividend.dao.CmDividendRecordDao;
import hry.cm.dividend.model.CmDividendConfig;
import hry.cm.dividend.model.CmDividendRecord;
import hry.cm.dividend.service.CmDividendConfigService;
import hry.cm.dividend.service.CmDividendRecordService;
import hry.cm.dto.Accountadd;
import hry.cm.dto.CmAccountRedis;
import hry.cm.enums.CmAccountRemarkEnum;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.mq.producer.DealMsgUtil;
import hry.util.idgenerate.IdGenerate;
import hry.utils.BaseConfUtil;

/**
 * <p> CmDividendRecordService </p>
 * @author: zhouming
 * @Date :          2019-08-01 13:37:58  
 */
@Service("cmDividendRecordService")
public class CmDividendRecordServiceImpl extends BaseServiceImpl<CmDividendRecord, Long> implements CmDividendRecordService {

    @Resource
    public CmDividendConfigService cmDividendConfigService;

    @Resource(name = "cmDividendRecordDao")
    @Override
    public void setDao(BaseDao<CmDividendRecord, Long> dao) {
        super.dao = dao;
    }

    @Override
    public void giveOutDividend() {
        List<CmDividendConfig> configList = cmDividendConfigService.findAll();
        if (configList != null && configList.size() > 0) {
            // 获取平台币
            String platCoin = BaseConfUtil.getConfigSingle("platCoin", "configCache:basefinanceConfig");
            // 获取分红配置信息 --- feeType 手续费类型 1.OTC 2.提币 3.币币交易 4.c2c交易
            CmDividendConfig config = configList.get(0);
            // ratio分红比例（%）
            String ratio = config.getRatio();
            // type 1：果树领取 2：自动发放
            Integer type = config.getType();
            String feeType = config.getFeeType();
            // 获取开启分红的用户
            List<CmCustomer> cmCustomerList = ((CmDividendRecordDao) dao).findCmCustomer();
            // 获取用户在各业务昨天产生的手续费
            for (CmCustomer cmCustomer : cmCustomerList) {
                List<Map<String, Object>> mapList = ((CmDividendRecordDao) dao).findUserFee(cmCustomer.getCustomerId(), platCoin);
                // 获取用户配置的手续费总量
                BigDecimal allFee = new BigDecimal("0");
                if (!StringUtils.isEmpty(feeType)) {
                    String[] feeTypes = feeType.split(",");
                    for (Map<String, Object> map : mapList) {
                        for (String s : feeTypes) {
                            if (s.equals(map.get("feeType"))) {
                                BigDecimal fee = map.get("fee") != null ? new BigDecimal(map.get("fee").toString()) : new BigDecimal("0");
                                allFee = allFee.add(fee);
                            }
                        }
                    }
                }
                BigDecimal dividend = new BigDecimal("0");
                // 手续费不为0则发放分红
                if (allFee.compareTo(BigDecimal.ZERO) != 0) {
                    // 应分红数
                    dividend = allFee.multiply(new BigDecimal(ratio)).divide(new BigDecimal("100"));
                    // 生成发放记录
                    CmDividendRecord record = new CmDividendRecord();
                    record.setCustomerId(cmCustomer.getCustomerId());
                    record.setCmCustomerId(cmCustomer.getId());
                    record.setCoinCode(platCoin);
                    record.setDividendGross(allFee);
                    record.setRatio(ratio);
                    record.setDividend(dividend);
                    Integer status = 1;
                    if (type == 2) {
                        // 发放分红
                        boolean flag = healeCount(cmCustomer.getCustomerId(), platCoin, dividend);
                        if (flag) {
                            status = 2;
                        }
                    }
                    record.setStatus(status);
                    super.save(record);
                }
            }
        }
    }

    @Override
    public JsonResult handelGiveOutDividend(Long dividendId) {
        CmDividendRecord record = super.get(dividendId);
        if (record != null) {
            Boolean flag = this.healeCount(record.getCustomerId(),record.getCoinCode(),record.getDividend());
            if (flag) {
                record.setStatus(2);
                super.update(record);
                return new JsonResult(true).setMsg("领取成功");
            } else {
                return new JsonResult(false).setMsg("操作子账户失败").setCode("1001");
            }
        } else {
            return new JsonResult(false).setMsg("无分红记录").setCode("1000");
        }
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

	@Override
	public List<CmCustomer> findCmCustomer() {
		// TODO Auto-generated method stub
		return ((CmDividendRecordDao) dao).findCmCustomer();
	}
}
