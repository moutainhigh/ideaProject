/**
 * Copyright:
 *
 * @author: javalx
 * @version: V1.0
 * @Date: 2019-06-12 11:32:00
 */
package hry.social.vip.service.impl;

import com.github.pagehelper.Page;
import hry.bean.PageResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;

import javax.annotation.Resource;

import hry.social.manage.remote.model.vip.SocialVipTradeRecord;
import hry.social.vip.dao.SocialVipTradeRecordDao;
import hry.social.vip.service.SocialVipTradeRecordService;
import hry.util.PageFactory;
import hry.util.QueryFilter;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * <p> SocialVipTradeRecordService </p>
 * @author: javalx
 * @Date :          2019-06-12 11:32:00  
 */
@Service("socialVipTradeRecordService")
public class SocialVipTradeRecordServiceImpl extends BaseServiceImpl<SocialVipTradeRecord,Long> implements SocialVipTradeRecordService {

    @Resource(name = "socialVipTradeRecordDao")
    @Override
    public void setDao(BaseDao<SocialVipTradeRecord,Long> dao) {
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
        Page<SocialVipTradeRecord> page = PageFactory.getPage(filter);

        String orderNum = filter.getRequest().getParameter("orderNum_LIKE");
        String mobilePhone = filter.getRequest().getParameter("mobilePhone_LIKE");
        String email = filter.getRequest().getParameter("email_LIKE");
        String nowVipName = filter.getRequest().getParameter("nowVipName_LIKE");
        String nowVipNum = filter.getRequest().getParameter("nowVipNum_EQ");
        String coinCode = filter.getRequest().getParameter("coinCode_EQ");
//		String created_GT = filter.getRequest().getParameter("created_GT");
//		String created_LT = filter.getRequest().getParameter("created_LT");
        String nowEndTime_GT = filter.getRequest().getParameter("nowEndTime_GT");
        String nowEndTime_LT = filter.getRequest().getParameter("nowEndTime_LT");
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
        if (!StringUtils.isEmpty(nowVipName)) {
            map.put("nowVipName", "%" + nowVipName + "%");
        }
        if (!StringUtils.isEmpty(nowVipNum)) {
            map.put("nowVipNum", nowVipNum);
        }
        if (!StringUtils.isEmpty(coinCode)) {
            map.put("coinCode", coinCode);
        }
//		if (!StringUtils.isEmpty(created_GT)) {
//			map.put("created_GT", created_GT);
//		}
//		if (!StringUtils.isEmpty(created_LT)) {
//			map.put("created_LT", created_LT);
//		}
        if (!StringUtils.isEmpty(nowEndTime_GT)) {
            map.put("nowEndTime_GT", nowEndTime_GT);
        }
        if (!StringUtils.isEmpty(nowEndTime_LT)) {
            map.put("nowEndTime_LT", nowEndTime_LT);
        }
        ((SocialVipTradeRecordDao) dao).findPageList(map);
        return new PageResult(page, filter.getPage(), filter.getPageSize());
    }
}
