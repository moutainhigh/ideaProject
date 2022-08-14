/**
 * Copyright:
 *
 * @author: javalx
 * @version: V1.0
 * @Date: 2019-06-03 17:49:59
 */
package hry.social.manage.remote.apply;

import hry.manage.remote.model.AppDic;
import hry.social.apply.dao.SocialApplyItemDao;
import hry.social.manage.remote.api.apply.RemoteApplyService;
import hry.social.manage.remote.model.apply.SocialApplyItem;
import hry.social.manage.remote.model.apply.SocialApplyType;
import hry.social.task.dao.SocialTaskItemDao;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p> RemoteApplyServiceImpl </p>
 *
 * @author: javalx
 * @Date :          2019-06-03 17:49:59
 */
public class RemoteApplyServiceImpl implements RemoteApplyService {

    @Resource
    public SocialTaskItemDao socialTaskItemDao;
    @Resource
    public SocialApplyItemDao socialApplyItemDao;

    /*
    *  获取应用类型列表
    * */
    @Override
    public List<AppDic> applyList() {
        List<AppDic> appDics = socialTaskItemDao.categoryList("applyType");
        return appDics;
    }

    /*
     *  根据应用类型查询应用列表
     * */
    @Override
    public List<SocialApplyItem> getApplys(String typeKey) {
        SocialApplyItem socialApplyItem = new SocialApplyItem();
        socialApplyItem.setTypeKey(typeKey);
        socialApplyItem.setStates(1);
        List<SocialApplyItem> socialApplyItems = socialApplyItemDao.select(socialApplyItem);
        return socialApplyItems;
    }
}
