/**
 * Copyright:
 *
 * @author: lixin
 * @version: V1.0
 * @Date: 2018-12-18 15:46:48
 */
package hry.social.wishwall.service.impl;

import com.github.pagehelper.Page;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.manage.remote.model.base.FrontPage;
import hry.social.manage.remote.model.wishwall.SocialWishWall;
import hry.social.wishwall.dao.SocialWishWallDao;
import hry.social.wishwall.service.SocialWishWallService;
import hry.util.PageFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p> SocialWishWallService </p>
 *
 * @author: lixin
 * @Date :          2018-12-18 15:46:48
 */
@Service("socialWishWallService")
public class SocialWishWallServiceImpl extends BaseServiceImpl<SocialWishWall,Long> implements SocialWishWallService {

    @Resource(name = "socialWishWallDao")
    @Override
    public void setDao(BaseDao<SocialWishWall,Long> dao) {
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
        Page page = PageFactory.getPage(params);
        String type = params.get("type");
        String customerId = params.get("customerId_EQ");
        Map<String,Object> map = new HashMap<String,Object>();
        if (!StringUtils.isEmpty(type)) {
            map.put("type", type);
        }
        if (!StringUtils.isEmpty(customerId)) {
            map.put("customerId", customerId);
        }
        List<SocialWishWall> pageList = ((SocialWishWallDao) dao).findPageList(map);
        return new FrontPage(pageList, page.getTotal(), page.getPages(), page.getPageSize());
    }
}
