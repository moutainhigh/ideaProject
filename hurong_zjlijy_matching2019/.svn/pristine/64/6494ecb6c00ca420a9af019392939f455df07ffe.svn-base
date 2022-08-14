/**
 * Copyright:
 *
 * @author: javalx
 * @version: V1.0
 * @Date: 2019-06-03 20:11:45
 */
package hry.social.friend.service.impl;

import com.github.pagehelper.Page;
import hry.bean.JsonResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;

import javax.annotation.Resource;

import hry.manage.remote.model.base.FrontPage;
import hry.social.friend.dao.SocialFriendRelationDao;
import hry.social.friend.service.SocialFriendRelationService;
import hry.social.manage.remote.model.friend.SocialFriendRelation;
import hry.util.PageFactory;
import hry.util.YunXinUtil;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * <p> SocialFriendRelationService </p>
 *
 * @author: javalx
 * @Date :          2019-06-03 20:11:45
 */
@Service("socialFriendRelationService")
public class SocialFriendRelationServiceImpl extends BaseServiceImpl<SocialFriendRelation,Long> implements SocialFriendRelationService {

    @Resource(name = "socialFriendRelationDao")
    @Override
    public void setDao(BaseDao<SocialFriendRelation,Long> dao) {
        super.dao = dao;
    }

    /**
     * 手动添加好友
     *
     * @param customerId 加好友-发起者ID
     * @param friendId   加好友-接收者ID
     * @param type       类型:1直接加好友，2请求加好友，3同意加好友，4拒绝加好友
     * @return
     */
    @Override
    public JsonResult addFriend(Long customerId, Long friendId, int type) {
        JsonResult jsonResult = new JsonResult();
        String accid = ((SocialFriendRelationDao) dao).getAccidByCustomerId(customerId);
        String faccid = ((SocialFriendRelationDao) dao).getAccidByCustomerId(friendId);
        JsonResult afResult = YunXinUtil.addFriend(accid, faccid, type);
        if (afResult.getSuccess() && (type==1 || type==3)) {
            JsonResult alfResult = addLocalFriend(customerId, friendId, null);
            if (alfResult.getSuccess()) {
                jsonResult.setSuccess(true);
            }
        }
        return jsonResult;
    }

    /**
     * 添加本地好友
     *
     * @param customerId 加好友-发起者ID
     * @param friendId   加好友-接收者ID
     * @return
     */
    @Override
    public JsonResult addLocalFriend(Long customerId, Long friendId, String friendsNote) {
        JsonResult jsonResult = new JsonResult();
        SocialFriendRelation sfrOne = new SocialFriendRelation();
        sfrOne.setCustomerId(customerId);
        sfrOne.setFriendId(friendId);
        if (StringUtils.isEmpty(friendsNote)) {
            sfrOne.setFriendsNote(friendsNote);
        }
        try {
            dao.insertSelective(sfrOne);
        } catch (DuplicateKeyException e) {
            System.err.println("ERR----------不能重复添加好友关系----------ERR");
        } catch (Exception e) {
            e.printStackTrace();
            //添加异常直接返回
            return jsonResult;
        }
        // 账户Two添加账户One为好友
        SocialFriendRelation sfrTwo = new SocialFriendRelation();
        sfrTwo.setCustomerId(friendId);
        sfrTwo.setFriendId(customerId);
        try {
            dao.insertSelective(sfrTwo);
        } catch (DuplicateKeyException e) {
            System.err.println("ERR----------不能重复添加好友关系----------ERR");
        } catch (Exception e) {
            e.printStackTrace();
            //添加异常，删除上一条添加的好友关系再返回
            Long id = sfrOne.getId();
            dao.deleteByPrimaryKey(id);
            return jsonResult;
        }
        jsonResult.setSuccess(true);
        return jsonResult;
    }

    /**
     * 查询好友列表
     *
     * @param customerId
     * @return
     */
    @Override
    public List<SocialFriendRelation> findPalList(Long customerId) {
        return ((SocialFriendRelationDao) dao).findPalList(customerId);
    }

    /**
     * 我的好友列表中根据昵称、备注、邮箱和手机号搜索好友
     *
     * @param name
     * @param customerId
     * @return
     */
    @Override
    public List<SocialFriendRelation> searchMyFriend(String name, Long customerId) {
        return ((SocialFriendRelationDao) dao).searchMyFriend(name, customerId);
    }

    /**
     * 平台内根据昵称、邮箱和手机号搜索用户
     *
     * @param name
     * @return
     */
    @Override
    public List<SocialFriendRelation> searchAddFriend(String name) {
        return ((SocialFriendRelationDao) dao).searchAddFriend(name);
    }

    /**
     * 查询是否存在好友关系，0否1是
     *
     * @param customerId
     * @param friendId
     * @return
     */
    @Override
    public int hasExistence(Long customerId, Long friendId) {
        return ((SocialFriendRelationDao) dao).hasExistence(customerId, friendId);
    }

    /**
     * 删除好友
     *
     * @param customerId 根ID
     * @param friendId   好友ID
     * @return
     */
    @Override
    public JsonResult delFriendRun(Long customerId, Long friendId) {
        JsonResult jsonResult = new JsonResult();
        String accid = ((SocialFriendRelationDao) dao).getAccidByCustomerId(friendId);
        String faccid = ((SocialFriendRelationDao) dao).getAccidByCustomerId(friendId);
        JsonResult afResult = YunXinUtil.delFriend(accid, faccid);
        JsonResult delResult = delLocalFriend(customerId, friendId);
        if (delResult.getSuccess()) {
            jsonResult.setSuccess(true);
        }
        return jsonResult;
    }


    /**
     * 添加本地好友
     *
     * @param customerId 加好友-发起者ID
     * @param friendId   加好友-接收者ID
     * @return
     */
    @Override
    public JsonResult delLocalFriend(Long customerId, Long friendId) {
        JsonResult jsonResult = new JsonResult();
        ((SocialFriendRelationDao) dao).deleteRelation(customerId, friendId);
        ((SocialFriendRelationDao) dao).deleteRelation(friendId, customerId);
        jsonResult.setSuccess(true);
        return jsonResult;
    }

    /**
     * 修改备注
     *
     * @param customerId
     * @param friendId
     * @param remark
     * @return
     */
    public void updateFriendsNote(Long customerId, Long friendId, String remark) {
        ((SocialFriendRelationDao) dao).updateFriendsNote(customerId, friendId, remark);
    }

    /**
     * 更新云信账户密码（tokem）
     *
     * @param accid
     * @param token
     */
    @Override
    public void updateYunxinToken(String accid, String token) {
        ((SocialFriendRelationDao) dao).updateYunxinToken(accid, token);
    }

    /**
     * 初始化好友关系
     *
     * @param customerId
     */
    @Override
    public void initFrient(Long customerId) {
        SocialFriendRelation socialFriendRelation = new SocialFriendRelation();
        socialFriendRelation.setCustomerId(customerId);
        socialFriendRelation.setFriendId(customerId);
        dao.insertSelective(socialFriendRelation);
    }

    /**
     * 分页查询好友列表
     *
     * @param params
     * @param customerId
     * @return
     */
    @Override
    public FrontPage findFrontPageBySql(Map<String,String> params, Long customerId) {
        Page page = PageFactory.getPage(params);
        // 查询方法
        List<SocialFriendRelation> list = ((SocialFriendRelationDao) dao).findFrontPageBySql(customerId);
        return new FrontPage(list, page.getTotal(), page.getPages(), page.getPageSize());
    }

    /**
     * 查询好友详情
     *
     * @param customerId
     * @param friendId
     * @return
     */
    @Override
    public SocialFriendRelation getPalInfo(Long customerId, Long friendId) {
        return ((SocialFriendRelationDao) dao).getPalInfo(customerId, friendId);
    }

    /**
     * @param customerId
     * @param accid
     * @return
     */
    @Override
    public SocialFriendRelation palInfo(Long customerId, String accid) {
        Long friendId = ((SocialFriendRelationDao) dao).getCustomerIdByAccid(accid);
        return ((SocialFriendRelationDao) dao).getPalInfo(customerId, friendId);
    }

    @Override
    public void updateFansGruop(String fansGroupId, Long inviteUserId) {
        ((SocialFriendRelationDao) dao).updateFansGruop(fansGroupId, inviteUserId);
    }
}
