package hry.social.manage.remote.friend;

import com.alibaba.fastjson.JSON;
import hry.bean.JsonResult;
import hry.manage.remote.model.RemoteResult;
import hry.manage.remote.model.base.FrontPage;
import hry.model.social.miningreward.SocialPickRedis;
import hry.redis.common.utils.RedisService;
import hry.social.force.service.SocialCalculateForceService;
import hry.social.friend.service.SocialFriendApplyService;
import hry.social.friend.service.SocialFriendPictureService;
import hry.social.friend.service.SocialFriendRelationService;
import hry.social.manage.remote.api.friend.RemoteFriendService;
import hry.social.manage.remote.model.friend.SocialFriendApply;
import hry.social.manage.remote.model.friend.SocialFriendRelation;
import hry.social.miningreward.service.SocialMiningGatherRecordService;
import hry.util.QueryFilter;
import hry.util.SocialUtil;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RemoteFriendServiceImpl implements RemoteFriendService {

    @Resource
    private SocialFriendRelationService socialFriendRelationService;
    @Resource
    private SocialFriendPictureService socialFriendPictureService;
    @Resource
    private SocialFriendApplyService socialFriendApplyService;

    @Resource
    private SocialCalculateForceService socialCalculateForceService;
    @Resource
    private SocialMiningGatherRecordService socialMiningGatherRecordService;
    @Resource
    private RedisService redisService;

    /**
     * 查询好友列表
     *
     * @param customerId
     * @return
     */
    @Override
    public List<SocialFriendRelation> findPalList(Long customerId) {
        return socialFriendRelationService.findPalList(customerId);
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
        return socialFriendRelationService.searchMyFriend(name, customerId);
    }

    /**
     * 平台内根据昵称、邮箱和手机号搜索用户（用于添加好友）
     *
     * @param customerId
     * @param name
     * @return
     */
    @Override
    public List<SocialFriendRelation> searchAddFriend(Long customerId, String name) {
        List<SocialFriendRelation> list = socialFriendRelationService.searchAddFriend(name);
        for (SocialFriendRelation sfr : list) {
            Long friendId = sfr.getCustomerId();
            int hasExistence = socialFriendRelationService.hasExistence(customerId, friendId);
            sfr.setIsfriend(hasExistence);
        }
        return list;
    }

    /**
     * 申请添加好友
     *
     * @param sfa
     */
    @Override
    public void addFriendApply(SocialFriendApply sfa) {
        socialFriendApplyService.save(sfa);
    }

    /**
     * 查询是否好友关系 OR 已申请添加好友
     * 0:不是好友也未申请添加好友，1:已是好友关系，2:已申请添加好友
     *
     * @param customerId
     * @param friendId
     * @return
     */
    @Override
    public int isFriendOrHasApply(Long customerId, Long friendId) {
        int i = socialFriendRelationService.hasExistence(customerId, friendId);
        if (i == 0) {
            QueryFilter qf = new QueryFilter(SocialFriendApply.class);
            qf.addFilter("applyId=", customerId);
            qf.addFilter("customerId=", friendId);
            qf.addFilter("states=", 0);
            SocialFriendApply sfa = socialFriendApplyService.get(qf);
            if (sfa != null) {
                return 2;
            }
            return 0;
        }
        return 1;
    }

    /**
     * 修改好友备注
     *
     * @param remark
     * @param customerId
     * @param palId
     * @return
     */
    @Override
    public RemoteResult remark(String remark, Long customerId, Long palId) {
        return null;
    }

    /**
     * 查询好友详情
     *
     * @param customerId
     * @param friendId
     * @return
     */
    @Override
    public RemoteResult getPalInfo(Long customerId, Long friendId) {
        RemoteResult remoteResult = new RemoteResult();
        SocialFriendRelation sfr = socialFriendRelationService.getPalInfo(customerId, friendId);
        List<String> pictures = null;
        if (sfr != null) {
            sfr.setIsfriend(1);
            pictures = socialFriendPictureService.findCirclePictures(friendId);
            List<String> images = new ArrayList<>();
            for (String picture : pictures) {
                String[] split = picture.split("\\|");
                if (split != null && split.length >= 1) {
                    images.add(split[0]);
                }
            }
            sfr.setCirclePictures(images);
        }
        return remoteResult.setSuccess(true).setObj(sfr);
    }

    /**
     * 查询好友详情
     *
     * @param customerId
     * @param accid
     * @return
     */
    @Override
    public RemoteResult palInfo(Long customerId, String accid) {
        RemoteResult remoteResult = new RemoteResult();
        SocialFriendRelation sfr = socialFriendRelationService.palInfo(customerId, accid);
        List<String> pictures = null;
        if (sfr != null) {
            sfr.setIsfriend(1);
            pictures = socialFriendPictureService.findCirclePictures(sfr.getFriendId());
            List<String> images = new ArrayList<>();
            for (String picture : pictures) {
                String[] split = picture.split("\\|");
                if (split != null && split.length >= 1) {
                    images.add(split[0]);
                }
            }
            sfr.setCirclePictures(images);
        }
        return remoteResult.setSuccess(true).setObj(sfr);
    }

    /**
     * @param friendId
     * @param customerId
     */
    @Override
    public void deleteFriend(Long friendId, Long customerId) {

    }

    /**
     * 查询好友排行榜记录
     *
     * @param params
     * @param customerId
     * @return
     */
    @Override
    public FrontPage findPalRanking(Map<String,String> params, Long customerId) {
        FrontPage front = socialFriendRelationService.findFrontPageBySql(params, customerId);
        List<SocialFriendRelation> rows = front.getRows();

        for (SocialFriendRelation sfr : rows) {
            int hasGather = 0;
            String friendId = sfr.getFriendId().toString();
            String cacheKey = SocialUtil.SOCIAL_GATHERED_CACHE + friendId;
            Map<String,String> cacheRewardMap = null;
            cacheRewardMap = redisService.getMap(cacheKey);
            if (cacheRewardMap != null) {
                for (Map.Entry<String,String> entry : cacheRewardMap.entrySet()) {
                    if (hasGather > 0) {
                        break;
                    }
                    String entryStr = entry.getValue();
                    if (!StringUtils.isEmpty(entryStr)){
                        SocialPickRedis socialPickRedis = JSON.parseObject(entryStr, SocialPickRedis.class);
                        Long id = socialPickRedis.getId();
                        String onlySelf = socialPickRedis.getOnlySelf();
                        int hasPick = 0;
                        if (!SocialUtil.GATHERE_ONLYSELF.equals(onlySelf)) {     //是否为保底值(是则直接置为不可收取状态 : 0，不是则查询是否有对应的收取记录)
                            //查询是否有对应的收取记录
                            int hasdata = socialMiningGatherRecordService.hasPick(customerId, id);
                            hasPick = 1 - hasdata;
                        }
                        hasGather += hasPick;
                    }
                }
            }
            sfr.setCollectionState(hasGather); // 好友是否有可收取的果园奖励（0 ：否，1 ：是）
        }
        return front;
    }

    /**
     * 添加好友
     * YunXinFriendRunnable 专用
     *
     * @param customerId 根ID
     * @param friendId   好友ID
     * @param type       类型:1直接加好友，2请求加好友，3同意加好友，4拒绝加好友")
     * @return
     */
    @Override
    public RemoteResult addFriendRun(Long customerId, Long friendId, int type) {
        RemoteResult remoteResult = new RemoteResult();
        JsonResult jsonResult = socialFriendRelationService.addFriend(customerId, friendId, type);
        remoteResult.setSuccess(jsonResult.getSuccess());
        return remoteResult;
    }

    /**
     * 删除好友
     *
     * @param customerId 根ID
     * @param friendId   好友ID
     * @return
     */
    @Override
    public RemoteResult delFriendRun(Long customerId, Long friendId) {
        RemoteResult remoteResult = new RemoteResult();
        JsonResult jsonResult = socialFriendRelationService.delFriendRun(customerId, friendId);
        remoteResult.setSuccess(jsonResult.getSuccess());
        return remoteResult;
    }

    /**
     * 查看好友申请记录列表
     *
     * @param customerId
     * @return
     */
    @Override
    public List<SocialFriendApply> findApplyList(Long customerId) {
        return socialFriendApplyService.findApplyList(customerId);
    }

    /**
     * 查看未处理的好友申请记录数
     *
     * @param customerId
     * @return
     */
    @Override
    public int hasNotDeal(Long customerId) {
        return socialFriendApplyService.hasNotDeal(customerId);
    }

    /**
     * 拒绝好友申请
     *
     * @param id
     */
    @Override
    public void refuseApply(Long id) {
        socialFriendApplyService.refuseApply(id);
    }

    /**
     * 接受好友申请
     *
     * @param id
     */
    @Override
    public void acceptApply(Long id) {
        socialFriendApplyService.acceptApply(id);
    }

    /**
     * 修改备注
     *
     * @param customerId
     * @param friendId
     * @param remark
     * @return
     */
    @Override
    public RemoteResult updateFriendsNote(Long customerId, Long friendId, String remark) {
        RemoteResult remoteResult = new RemoteResult();
        socialFriendRelationService.updateFriendsNote(customerId, friendId, remark);
        remoteResult.setSuccess(true);
        return remoteResult;
    }

    /**
     * 注册初始化好友关系&算力账户信息
     *
     * @param customerId
     * @return
     */
    @Override
    public RemoteResult registInit(Long customerId) {
        RemoteResult remoteResult = new RemoteResult();
        socialFriendRelationService.initFrient(customerId);
        socialCalculateForceService.initForce(customerId);
        remoteResult.setSuccess(true);
        return remoteResult;
    }

    @Override
    public void updateFansGruop(String fansGroupId, Long inviteUserId) {
        socialFriendRelationService.updateFansGruop(fansGroupId, inviteUserId);
    }

}

