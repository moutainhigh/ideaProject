/**
 * Copyright:
 *
 * @author: javalx
 * @version: V1.0
 * @Date: 2019-06-12 11:24:44
 */
package hry.social.vip.service.impl;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;

import javax.annotation.Resource;

import hry.social.force.service.SocialCalculateForceService;
import hry.social.manage.remote.model.vip.SocialCustomerVip;
import hry.social.manage.remote.model.vip.SocialVipInfo;
import hry.social.vip.dao.SocialVipInfoDao;
import hry.social.vip.service.SocialVipInfoService;
import org.apache.commons.math3.analysis.function.Add;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p> SocialVipInfoService </p>
 *
 * @author: javalx
 * @Date :          2019-06-12 11:24:44
 */
@Service("socialVipInfoService")
public class SocialVipInfoServiceImpl extends BaseServiceImpl<SocialVipInfo,Long> implements SocialVipInfoService {

    @Resource(name = "socialVipInfoDao")
    @Override
    public void setDao(BaseDao<SocialVipInfo,Long> dao) {
        super.dao = dao;
    }

    @Resource
    SocialVipInfoDao socialVipInfoDao;

    /**
     * 查询可购买的会员信息
     *
     * @param socialCustomerVip
     * @return
     */
    @Override
    public List<SocialVipInfo> getAvailableVip(SocialCustomerVip socialCustomerVip) {
        BigDecimal additionRatio = socialCustomerVip.getAdditionRatio();
        BigDecimal taskForce = socialCustomerVip.getTaskForce();
        BigDecimal millForce = socialCustomerVip.getMillForce();
        BigDecimal millCoin = socialCustomerVip.getMillCoin();

        BigDecimal taskForceOldAdd = socialCustomerVip.getTaskForceAdd();
        BigDecimal millForceOldAdd = socialCustomerVip.getMillForceAdd();
        BigDecimal millCoinOldAdd = socialCustomerVip.getMillCoinAdd();
        List<SocialVipInfo> vips = socialVipInfoDao.getAvailableVip(additionRatio);
        BigDecimal divNum = new BigDecimal(100);
        for (SocialVipInfo svi : vips) {
            BigDecimal adr = svi.getAdditionRatio();
            BigDecimal divide = adr.divide(divNum);
            BigDecimal tfMultiply = taskForce.multiply(divide);
            BigDecimal mfMultiply = millForce.multiply(divide);
            BigDecimal mcMultiply = millCoin.multiply(divide);

            // 当前加成
            svi.setTaskForceOldAdd(taskForceOldAdd);
            svi.setMillForceOldAdd(millForceOldAdd);
            svi.setMillCoinOldAdd(millCoinOldAdd);

            // 升级后加成
            svi.setTaskForceNewAdd(tfMultiply);
            svi.setMillForceNewAdd(mfMultiply);
            svi.setMillCoinNewAdd(mcMultiply);

            // 升级后加成
            svi.setTaskForceUp(tfMultiply.subtract(taskForceOldAdd));
            svi.setMillForceUp(mfMultiply.subtract(millForceOldAdd));
            svi.setMillCoinUp(mcMultiply.subtract(millCoinOldAdd));
        }

        return vips;
    }
}
