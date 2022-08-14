/**
 * Copyright:
 *
 * @author: javalx
 * @version: V1.0
 * @Date: 2019-06-12 11:35:57
 */
package hry.social.vip.service.impl;

import com.github.pagehelper.Page;
import hry.bean.PageResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;

import javax.annotation.Resource;

import hry.social.force.service.SocialCalculateForceService;
import hry.social.manage.remote.model.vip.SocialCustomerVip;
import hry.social.mill.dao.SocialMillTradeRecordDao;
import hry.social.vip.dao.SocialCustomerVipDao;
import hry.social.vip.dao.SocialVipInfoDao;
import hry.social.vip.service.SocialCustomerVipService;
import hry.util.PageFactory;
import hry.util.QueryFilter;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * <p> SocialCustomerVipService </p>
 *
 * @author: javalx
 * @Date :          2019-06-12 11:35:57
 */
@Service("socialCustomerVipService")
public class SocialCustomerVipServiceImpl extends BaseServiceImpl<SocialCustomerVip,Long> implements SocialCustomerVipService {

    @Resource(name = "socialCustomerVipDao")
    @Override
    public void setDao(BaseDao<SocialCustomerVip,Long> dao) {
        super.dao = dao;
    }

    @Resource
    SocialCalculateForceService socialCalculateForceService;

    @Resource
    SocialMillTradeRecordDao socialMillTradeRecordDao;

    /**
     * 分页查询
     *
     * @param filter
     * @return
     */
    @Override
    public PageResult findPageList(QueryFilter filter) {
        //----------------------分页查询头部外壳------------------------------
        Page<SocialCustomerVip> page = PageFactory.getPage(filter);

        String mobilePhone = filter.getRequest().getParameter("mobilePhone_LIKE");
        String email = filter.getRequest().getParameter("email_LIKE");
        String vipName = filter.getRequest().getParameter("vipName_LIKE");
        String serialNum = filter.getRequest().getParameter("serialNum_EQ");
        String startTime_GT = filter.getRequest().getParameter("startTime_GT");
        String startTime_LT = filter.getRequest().getParameter("startTime_LT");
        String endTime_GT = filter.getRequest().getParameter("endTime_GT");
        String endTime_LT = filter.getRequest().getParameter("endTime_LT");
        Map<String,Object> map = new HashMap<String,Object>();
        if (!StringUtils.isEmpty(email)) {
            map.put("email", "%" + email + "%");
        }
        if (!StringUtils.isEmpty(mobilePhone)) {
            map.put("mobilePhone", "%" + mobilePhone + "%");
        }
        if (!StringUtils.isEmpty(vipName)) {
            map.put("vipName", "%" + vipName + "%");
        }
        if (!StringUtils.isEmpty(serialNum)) {
            map.put("serialNum", serialNum);
        }
        if (!StringUtils.isEmpty(startTime_GT)) {
            map.put("startTime_GT", startTime_GT);
        }
        if (!StringUtils.isEmpty(startTime_LT)) {
            map.put("startTime_LT", startTime_LT);
        }
        if (!StringUtils.isEmpty(endTime_GT)) {
            map.put("endTime_GT", endTime_GT);
        }
        if (!StringUtils.isEmpty(endTime_LT)) {
            map.put("endTime_LT", endTime_LT);
        }
        ((SocialCustomerVipDao) dao).findPageList(map);
        return new PageResult(page, filter.getPage(), filter.getPageSize());
    }

    /**
     * 查询会员信息
     *
     * @param customerId
     * @return
     */
    @Override
    public SocialCustomerVip getVipInfo(Long customerId) {
        return ((SocialCustomerVipDao) dao).getVipInfo(customerId);
    }

    /**
     * 查询用户会员信息(包含任务算力&矿机算力&数币矿机加成信息)
     *
     * @param customerId
     * @return
     */
    @Override
    public SocialCustomerVip getUserVipInfo(Long customerId) {
        SocialCustomerVip vipInfo = getVipInfo(customerId);
        if (vipInfo == null) {
            vipInfo = new SocialCustomerVip();
            vipInfo.setAdditionRatio(BigDecimal.ZERO);
            vipInfo.setStates(0);
        }else {
            vipInfo.setStates(1);
        }
        BigDecimal additionRatio = vipInfo.getAdditionRatio();
        BigDecimal divNum = new BigDecimal(100);
        BigDecimal taskFroce = socialCalculateForceService.findTaskFroce(customerId);
        BigDecimal millFroce = socialCalculateForceService.findMillFroce(customerId);
        BigDecimal millCoin = socialMillTradeRecordDao.findMillCoin(customerId);
        BigDecimal divide = additionRatio.divide(divNum);
        BigDecimal tf = taskFroce.multiply(divide);
        BigDecimal mf = millFroce.multiply(divide);
        BigDecimal mc = millCoin.multiply(divide);
        vipInfo.setTaskForce(taskFroce);
        vipInfo.setMillForce(millFroce);
        vipInfo.setMillCoin(millCoin);
        vipInfo.setTaskForceAdd(tf);
        vipInfo.setMillForceAdd(mf);
        vipInfo.setMillCoinAdd(mc);
        return vipInfo;
    }

    /**
     * 查询用户的会员加成
     *
     * @param customerId
     * @return
     */
    @Override
    public BigDecimal getVipAdd(Long customerId) {
        return ((SocialCustomerVipDao) dao).getVipAdd(customerId);
    }
}
