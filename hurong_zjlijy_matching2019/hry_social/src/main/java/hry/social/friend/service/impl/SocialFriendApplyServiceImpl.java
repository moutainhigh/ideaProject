/**
 * Copyright:
 *
 * @author: javalx
 * @version: V1.0
 * @Date: 2019-06-03 20:17:08
 */
package hry.social.friend.service.impl;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;

import javax.annotation.Resource;

import hry.social.friend.dao.SocialFriendApplyDao;
import hry.social.friend.service.SocialFriendApplyService;
import hry.social.manage.remote.model.friend.SocialFriendApply;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p> SocialFriendApplyService </p>
 *
 * @author: javalx
 * @Date :          2019-06-03 20:17:08
 */
@Service("socialFriendApplyService")
public class SocialFriendApplyServiceImpl extends BaseServiceImpl<SocialFriendApply,Long> implements SocialFriendApplyService {

    @Resource(name = "socialFriendApplyDao")
    @Override
    public void setDao(BaseDao<SocialFriendApply,Long> dao) {
        super.dao = dao;
    }

    /**
     * 查看好友申请记录列表
     *
     * @param customerId
     * @return
     */
    @Override
    public List<SocialFriendApply> findApplyList(Long customerId) {
        return ((SocialFriendApplyDao) dao).findApplyList(customerId);
    }

    /**
     * 查看未处理的好友申请记录数
     *
     * @param customerId
     * @return
     */
    @Override
    public int hasNotDeal(Long customerId) {
        return ((SocialFriendApplyDao) dao).hasNotDeal(customerId);
    }

    /**
     * 拒绝好友申请
     *
     * @param id
     */
    @Override
    public void refuseApply(Long id) {
        ((SocialFriendApplyDao) dao).refuseApply(id);
    }

    /**
     * 接受好友申请
     *
     * @param id
     */
    @Override
    public void acceptApply(Long id) {
        ((SocialFriendApplyDao) dao).acceptApply(id);
    }
}
