/**
 * Copyright:
 *
 * @author: javalx
 * @version: V1.0
 * @Date: 2019-06-11 18:28:34
 */
package hry.social.mill.service.impl;

import com.github.pagehelper.Page;
import hry.bean.PageResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;

import javax.annotation.Resource;

import hry.social.manage.remote.model.mill.SocialMillTradeRecord;
import hry.social.mill.dao.SocialMillTradeRecordDao;
import hry.social.mill.service.SocialMillTradeRecordService;
import hry.util.PageFactory;
import hry.util.QueryFilter;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * <p> SocialMillTradeRecordService </p>
 *
 * @author: javalx
 * @Date :          2019-06-11 18:28:34
 */
@Service("socialMillTradeRecordService")
public class SocialMillTradeRecordServiceImpl extends BaseServiceImpl<SocialMillTradeRecord,Long> implements SocialMillTradeRecordService {

    @Resource(name = "socialMillTradeRecordDao")
    @Override
    public void setDao(BaseDao<SocialMillTradeRecord,Long> dao) {
        super.dao = dao;
    }


    /**
     * 分页查询
     *
     * @param filter
     * @return
     */
    @Override
    public PageResult findPageList(QueryFilter filter) {
        //----------------------分页查询头部外壳------------------------------
        Page<SocialMillTradeRecord> page = PageFactory.getPage(filter);

        String orderNum = filter.getRequest().getParameter("orderNum_LIKE");
        String mobilePhone = filter.getRequest().getParameter("mobilePhone_LIKE");
        String email = filter.getRequest().getParameter("email_LIKE");
        String serialNum = filter.getRequest().getParameter("serialNum_LIKE");
        String coinCode = filter.getRequest().getParameter("coinCode_EQ");
        String rewardType = filter.getRequest().getParameter("rewardType_EQ");
        String created_GT = filter.getRequest().getParameter("created_GT");
        String created_LT = filter.getRequest().getParameter("created_LT");
        String endTime_GT = filter.getRequest().getParameter("endTime_GT");
        String endTime_LT = filter.getRequest().getParameter("endTime_LT");
        Map<String,Object> map = new HashMap<String,Object>();
        if (!StringUtils.isEmpty(orderNum)) {
            map.put("orderNum", "%" + orderNum + "%");
        }
        if (!StringUtils.isEmpty(email)) {
            map.put("email", "%" + email + "%");
        }
        if (!StringUtils.isEmpty(mobilePhone)) {
            map.put("mobilePhone", "%" + mobilePhone + "%");
        }
        if (!StringUtils.isEmpty(serialNum)) {
            map.put("serialNum", "%" + serialNum + "%");
        }
        if (!StringUtils.isEmpty(coinCode)) {
            map.put("coinCode", coinCode);
        }
        if (!StringUtils.isEmpty(rewardType)) {
            map.put("rewardType", rewardType);
        }
        if (!StringUtils.isEmpty(created_GT)) {
            map.put("created_GT", created_GT);
        }
        if (!StringUtils.isEmpty(created_LT)) {
            map.put("created_LT", created_LT);
        }
        if (!StringUtils.isEmpty(endTime_GT)) {
            map.put("endTime_GT", endTime_GT);
        }
        if (!StringUtils.isEmpty(endTime_LT)) {
            map.put("endTime_LT", endTime_LT);
        }
        ((SocialMillTradeRecordDao) dao).findPageList(map);
        return new PageResult(page, filter.getPage(), filter.getPageSize());
    }

    @Override
    public BigDecimal getCoinReward(String coinCode) {
        return ((SocialMillTradeRecordDao) dao).getCoinReward(coinCode);
    }
}
