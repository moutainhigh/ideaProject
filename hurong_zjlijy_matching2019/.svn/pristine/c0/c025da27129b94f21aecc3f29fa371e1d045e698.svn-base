/**
 * Copyright:
 *
 * @author: javalx
 * @version: V1.0
 * @Date: 2019-06-03 20:11:45
 */
package hry.social.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import hry.bean.JsonResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;

import javax.annotation.Resource;

import hry.core.thread.ThreadPool;
import hry.manage.remote.RemoteManageService;
import hry.manage.remote.model.User;
import hry.mq.consumer.social.user.UserMessage;
import hry.redis.common.utils.RedisService;
import hry.social.manage.remote.api.shake.RemoteShakeService;
import hry.social.manage.remote.api.task.RemoteTaskService;
import hry.social.manage.remote.model.force.SocialCalculateForce;
import hry.social.manage.remote.model.friend.SocialFriendRelation;
import hry.social.manage.remote.model.runnable.TaskRewardRunnable;
import hry.social.manage.remote.model.task.SocialTaskReward;
import hry.social.user.dao.SocialCalculateForceDao;
import hry.social.user.dao.SocialUserDao;
import hry.social.user.service.SocialUserService;
import hry.util.QrCodeUtils;
import hry.util.YunXinUtil;
import hry.util.properties.PropertiesUtils;
import hry.util.sys.ContextUtil;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p> SocialFriendRelationService </p>
 *
 * @author: javalx
 * @Date :          2019-06-03 20:11:45
 */
@Service("socialUserService")
public class SocialUserServiceImpl extends BaseServiceImpl<SocialFriendRelation,Long> implements SocialUserService {

    @Resource(name = "socialUserDao")
    @Override
    public void setDao(BaseDao<SocialFriendRelation,Long> dao) {
        super.dao = dao;
    }

    @Resource
    private SocialCalculateForceDao socialCalculateForceDao;

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
        String accid = ((SocialUserDao) dao).getAccidByCustomerId(customerId);
        String faccid = ((SocialUserDao) dao).getAccidByCustomerId(friendId);
        JsonResult afResult = YunXinUtil.addFriend(accid, faccid, type);
        if (afResult.getSuccess() && (type == 1 || type == 3)) {
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
     * 查询是否存在好友关系，0否1是
     *
     * @param customerId
     * @param friendId
     * @return
     */
    @Override
    public int hasExistence(Long customerId, Long friendId) {
        return ((SocialUserDao) dao).hasExistence(customerId, friendId);
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
        ((SocialUserDao) dao).updateFriendsNote(customerId, friendId, remark);
    }

    /**
     * 更新云信账户密码（tokem）
     *
     * @param accid
     * @param token
     */
    @Override
    public void updateYunxinToken(String accid, String token) {
        ((SocialUserDao) dao).updateYunxinToken(accid, token);
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

    @Override
    public void updateFansGruop(String fansGroupId, Long inviteUserId) {
        ((SocialUserDao) dao).updateFansGruop(fansGroupId, inviteUserId);
    }

    /**
     * 用户注册 init
     *
     * @param userMsg
     */
    @Override
    public void registDeal(String userMsg) {
        System.out.println("======== 用户注册-init-MQ信息 ==========>>> " + userMsg);
        if (null == userMsg) {
            return;
        }
        RedisService redisService = (RedisService) ContextUtil.getBean("redisService");
        UserMessage userMessage = JSON.parseObject(userMsg, UserMessage.class);
        RemoteTaskService remoteTaskService = (RemoteTaskService) ContextUtil.getBean("remoteTaskService");
        String accid = userMessage.getAccid();
        String nickName = userMessage.getNickName();
        String headPortrait = userMessage.getHeadPortrait();
        String token = userMessage.getToken();
        Long customerId = userMessage.getCustomerId();
        String referralCode = userMessage.getReferralCode();
        String commendCode = userMessage.getCommendCode();
        String username = userMessage.getUsername();

        try {
            // 0. 注册用户初始化邀请二维码
            String content = PropertiesUtils.APP.getProperty("app.vueurl");
            if (StringUtils.isEmpty(content)) {
                return;
            }
            content += "?inviteCode=" + referralCode;
            QrCodeUtils.generateCode(content, null, username, false, false);
            System.out.println("=============================== 初始化邀请二维码:成功 ===============================");

            /**==============-------社交-------==============**/
            Map<String,String> moduleMap = redisService.getMap("APP:MODULE");
            if (moduleMap != null && "true".equalsIgnoreCase(moduleMap.get("app.module.social"))) {
                // 1. 取默认头像,注册网易云信账户
                JsonResult register = YunXinUtil.register(accid, token, nickName, headPortrait);
                if (register.getSuccess()) {
                    System.out.println("===================================================================================");
                    System.out.println("================================= 网易云信注册:成功 =================================");
                    // 2. 注册初始化好友关系&算力账户信息
                    /** 初始化好友关系 **/
                    SocialFriendRelation socialFriendRelation = new SocialFriendRelation();
                    socialFriendRelation.setCustomerId(customerId);
                    socialFriendRelation.setFriendId(customerId);
                    dao.insertSelective(socialFriendRelation);
                    System.out.println("================================ 初始化好友关系:成功 ================================");
                    /** 初始化算力账户信息 **/
                    SocialCalculateForce scf = new SocialCalculateForce();
                    scf.setCustomerId(customerId);
                    scf.setPerpetualForce(BigDecimal.ZERO);
                    scf.setTerminableForce(BigDecimal.ZERO);
                    scf.setAdditionForce(BigDecimal.ZERO);
                    scf.setTotalForce(BigDecimal.ZERO);
                    socialCalculateForceDao.insertSelective(scf);
                    System.out.println("============================== 初始化算力账户信息:成功 ==============================");
//                    // 3. 注册用户初始化邀请二维码
//                    String content = PropertiesUtils.APP.getProperty("app.vueurl");
//                    if (StringUtils.isEmpty(content)) {
//                        return;
//                    }
//                    content += "?inviteCode=" + referralCode;
//                    QrCodeUtils.generateCode(content, null, username, false, false);
//                    System.out.println("=============================== 初始化邀请二维码:成功 ===============================");
                    /** 注册任务奖励处理 */
                    Map<String,String> taskMap = redisService.getMap("DIC:task_mark");
                    String appReg = taskMap.get("appReg");

                    //注册任务奖励处理--异步
                    ThreadPool.exe(new TaskRewardRunnable(customerId, appReg));
//                SocialTaskReward socialTaskReward = remoteTaskService.dealTaskReward(customerId, appReg);
//                System.out.println("    ====>    邀请任务奖励处理完成：" + socialTaskReward);

                    // 4. 邀请注册处理
                    if (commendCode != null) {
                        //4.1 互添云信&本地好友------------------------------
                        RemoteManageService remoteManageService = (RemoteManageService) ContextUtil.getBean("remoteManageService");
                        RemoteShakeService remoteShakeService = (RemoteShakeService) ContextUtil.getBean("remoteShakeService");
                        User inviteUser = remoteManageService.getUserByReferralCodeCode(commendCode);
                        // 如果推荐人信息错误则返回
                        if (null == inviteUser || StringUtils.isEmpty(inviteUser.getAccid()) || StringUtils.isEmpty(inviteUser.getToken())) {
                            System.out.println("    ====>    注册邀请人不存在 吸粉失败");
                            return;
                        }
                        Long inviteUserId = inviteUser.getCustomerId();
                        String inviteUserAccid = inviteUser.getAccid();
                        //4.1.1 互添云信好友------------------------------
                        JsonResult afResult = YunXinUtil.addFriend(inviteUserAccid, accid, 1);
                        if (afResult.getSuccess()) {
                            System.out.println("=============================== 云信推荐好友关系:成功 ===============================");
                            //4.1.2 互添本地好友------------------------------
                            // 账户One添加账户Two为好友（本地）
                            SocialFriendRelation sfrOne = new SocialFriendRelation();
                            sfrOne.setCustomerId(customerId);
                            sfrOne.setFriendId(inviteUserId);
                            try {
                                dao.insertSelective(sfrOne);
                            } catch (DuplicateKeyException e) {
                                System.err.println("ERR----------不能重复添加好友关系----------ERR");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            // 账户Two添加账户One为好友（本地）
                            SocialFriendRelation sfrTwo = new SocialFriendRelation();
                            sfrTwo.setCustomerId(inviteUserId);
                            sfrTwo.setFriendId(customerId);
                            try {
                                dao.insertSelective(sfrTwo);
                            } catch (DuplicateKeyException e) {
                                System.err.println("ERR----------不能重复添加好友关系----------ERR");
                            } catch (Exception e) {
                                System.err.println("ERR--好友关系添加异常，删除上一条添加的好友关系--ERR");
                                Long id = sfrOne.getId();
                                dao.deleteByPrimaryKey(id);
                            }
                            System.out.println("=============================== 本地推荐好友关系:成功 ===============================");
                            //4.2 建群拉粉---------------------------------
                            //条件判断(如果邀请人有线下店铺，则建群拉粉)
                            int hasOffShop = remoteShakeService.hasOffShop(inviteUserId);
                            if (hasOffShop == 1) {
                                // 最新粉丝群ID
                                String fansGroupId = inviteUser.getFansGroupId();
                                // 粉丝ID
                                List<String> fansList = new ArrayList<>();
                                fansList.add(accid);
                                String fans = JSONObject.toJSONString(fansList);
                                if (StringUtils.isEmpty(fansGroupId)) {
                                    //粉丝群ID为空,新建群拉人
                                    JsonResult group = YunXinUtil.createGroup("fansGroup", inviteUserAccid, fans, "fansGroup", "fansGroup", "invite to fansGroup", "0", "0", null, null, "1", "1", "1", "1", null);
                                    if (!group.getSuccess()) {
                                        System.out.println("    ====>    掌柜邀请注册吸粉(新建粉丝群拉人) 失败");
                                        return;
                                    } else {
                                        Map<String,Object> json = (Map<String,Object>) group.getObj();
                                        fansGroupId = (String) json.get("tid");
                                        ((SocialUserDao) dao).updateFansGruop(fansGroupId, inviteUserId);
                                        System.out.println("    ====>    建群拉粉 成功");
                                    }
                                } else {
                                    //粉丝群ID不为空,拉粉入群
                                    JsonResult jsonResult = YunXinUtil.addToGroup(fansGroupId, inviteUserAccid, fans, "0", "invite to fansGroup", null);
                                    if (!jsonResult.getSuccess()) {
                                        String code = jsonResult.getCode();
                                        boolean objectResult = "801".equalsIgnoreCase(code);// 粉丝群人数上限则新建群
                                        if (objectResult) {
                                            System.out.println("    ====>    掌柜邀请注册吸粉(拉粉入群) 失败    ====>    粉丝群人数达上限");
                                            //粉丝群ID为空,新建群拉人
                                            JsonResult group = YunXinUtil.createGroup("fansGroup", inviteUserAccid, fans, "fansGroup", "fansGroup", "invite to fansGroup", "0", "0", null, null, "1", "1", "1", "1", null);
                                            if (!group.getSuccess()) {
                                                System.out.println("    ====>    掌柜邀请注册吸粉(新建粉丝群拉人) 失败");
                                                return;
                                            } else {
                                                Map<String,Object> json = (Map<String,Object>) jsonResult.getObj();
                                                fansGroupId = (String) json.get("tid");
                                                ((SocialUserDao) dao).updateFansGruop(fansGroupId, inviteUserId);
                                                System.out.println("    ====>    建群拉粉 成功");
                                            }
                                        } else {
                                            System.out.println("    ====>    建群拉粉 失败");
                                        }
                                    } else {
                                        System.out.println("    ====>    建群拉粉(加好友，建群拉粉) 成功");
                                    }
                                }
                            }
                        }
                        System.out.println("=============================== 邀请注册处理:成功 ===============================");
                        /** 邀请任务奖励处理 */
                        String invite = taskMap.get("invite");

                        //邀请任务奖励处理--异步
                        ThreadPool.exe(new TaskRewardRunnable(inviteUserId, invite));
//                    socialTaskReward = remoteTaskService.dealTaskReward(inviteUserId, invite);
//                    System.out.println("    ====>    邀请任务奖励处理完成：" + socialTaskReward);
                    }
                }
                System.out.println("===================================================================================");
            }
        } catch (Exception e) {
            /**锁定用户**/
            ((SocialUserDao) dao).lockUser(customerId);
            e.printStackTrace();
        }
    }
}
