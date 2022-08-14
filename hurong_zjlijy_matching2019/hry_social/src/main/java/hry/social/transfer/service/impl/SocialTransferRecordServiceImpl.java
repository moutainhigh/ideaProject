/**
 * Copyright:
 *
 * @author: javalx
 * @version: V1.0
 * @Date: 2019-06-12 16:51:43
 */
package hry.social.transfer.service.impl;

import com.github.pagehelper.Page;
import hry.bean.PageResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;

import javax.annotation.Resource;

import hry.manage.remote.model.base.FrontPage;
import hry.social.manage.remote.model.transfer.SocialTransferRecord;
import hry.social.transfer.dao.SocialTransferRecordDao;
import hry.social.transfer.service.SocialTransferRecordService;
import hry.util.PageFactory;
import hry.util.QueryFilter;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p> SocialTransferRecordService </p>
 * @author: javalx
 * @Date :          2019-06-12 16:51:43
 */
@Service("socialTransferRecordService")
public class SocialTransferRecordServiceImpl extends BaseServiceImpl<SocialTransferRecord,Long> implements SocialTransferRecordService {

    @Resource(name = "socialTransferRecordDao")
    @Override
    public void setDao(BaseDao<SocialTransferRecord,Long> dao) {
        super.dao = dao;
    }


    /**
     * 分页查询
     *
     * @param params
     * @return
     */
    @Override
    public FrontPage findPageList(Map<String,String> params) {
        Page<SocialTransferRecord> page = PageFactory.getPage(params);
        //----------------------查询开始------------------------------
        String customerId = params.get("customerId");
        String coinCode = params.get("coinCode");
        String createdGT = params.get("createdGT");
        String createdLT = params.get("createdLT");
        Map<String,Object> map = new HashMap<String,Object>();
        if (!StringUtils.isEmpty(customerId)) {
            map.put("customerId", customerId);
        }
        if (!StringUtils.isEmpty(coinCode)) {
            params.put("coinCode", coinCode);
        }
        if (!StringUtils.isEmpty(createdGT)) {
            params.put("createdGT", createdGT);
        }
        if (!StringUtils.isEmpty(createdLT)) {
            params.put("createdLT", createdLT);
        }
        List<SocialTransferRecord> pageList = ((SocialTransferRecordDao) dao).findPageList(map);
        return new FrontPage(pageList, page.getTotal(), page.getPages(), page.getPageSize());
    }

    /**
     * 查询币地址
     *
     * @param coinCode
     * @param tocustomerId
     * @return
     */
    @Override
    public String getPublicKey(String coinCode, Long tocustomerId) {
        return ((SocialTransferRecordDao) dao).getPublicKey(coinCode, tocustomerId);
    }
}
