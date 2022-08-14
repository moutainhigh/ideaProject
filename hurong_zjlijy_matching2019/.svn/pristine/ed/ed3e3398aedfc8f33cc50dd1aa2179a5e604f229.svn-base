package hry.social.manage.remote.model.runnable;

import com.alibaba.fastjson.JSONObject;
import hry.bean.JsonResult;
import hry.manage.remote.RemoteManageService;
import hry.manage.remote.model.RemoteResult;
import hry.manage.remote.model.User;
import hry.redis.common.utils.RedisService;
import hry.social.manage.remote.api.friend.RemoteFriendService;
import hry.social.manage.remote.api.shake.RemoteShakeService;
import hry.social.manage.remote.api.task.RemoteTaskService;
import hry.social.manage.remote.model.task.SocialTaskReward;
import hry.util.YunXinUtil;
import hry.util.sys.ContextUtil;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 掌柜邀请粉丝(注册云信帐号，建群，加好友)异步处理
 */
public class InviteFansRunnable implements Runnable {

    private final Logger log = Logger.getLogger(InviteFansRunnable.class);

    private User user;

    private InviteFansRunnable() {
    }

    public InviteFansRunnable(User user) {
        this.user = user;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(2000);    //延时2秒

            System.out.println("==============开始邀请注册异步处理：");
            System.out.println(this.toString());
            //1.互添云信&本地好友------------------------------
            String accid = user.getAccid();
            String nickName = user.getNickName();
            String headPortrait = user.getHeadPortrait();
//            JsonResult registerTow = YunXinUtil.registerTow(accid, nickName, headPortrait);
//            if (!registerTow.getSuccess()){
//                System.out.println("    ====>    注册云信失败 吸粉失败");
//                return;
//            }
            Long customerId = user.getCustomerId();
            String commendCode = user.getCommendCode();
            // 如果云信注册失败或者推荐人ID为空则返回
            if (StringUtils.isEmpty(commendCode)) {
                System.out.println("    ====>    注册邀请码错误 吸粉失败");
                return;
            }
            RemoteManageService remoteManageService = (RemoteManageService) ContextUtil.getBean("remoteManageService");
            RemoteFriendService remoteFriendService = (RemoteFriendService) ContextUtil.getBean("remoteFriendService");
            RemoteShakeService remoteShakeService = (RemoteShakeService) ContextUtil.getBean("remoteShakeService");
            RemoteTaskService remoteTaskService = (RemoteTaskService) ContextUtil.getBean("remoteTaskService");
            RedisService redisService = (RedisService) ContextUtil.getBean("redisService");
            User inviteUser = remoteManageService.getUserByReferralCodeCode(commendCode);
            // 如果推荐人信息错误则返回
            if (null == inviteUser || StringUtils.isEmpty(inviteUser.getAccid()) || StringUtils.isEmpty(inviteUser.getToken())) {
                System.out.println("    ====>    注册邀请人不存在 吸粉失败");
                return;
            }
            Long inviteUserId = inviteUser.getCustomerId();
            String inviteUserAccid = inviteUser.getAccid();
            RemoteResult remoteResult = remoteFriendService.addFriendRun(inviteUserId, customerId, 1);
            if (!remoteResult.getSuccess()) {
                System.out.println("    ====>    互添云信&本地好友失败 吸粉失败");
                return;
            } else {
                //2.建群拉粉---------------------------------
                //条件判断(如果邀请人有线下店铺，则建群拉粉)
                int hasOffShop = remoteShakeService.hasOffShop(inviteUserId);
                if (hasOffShop != 1) {
                    System.out.println("    ====>    邀请人没有线下店铺 仅添加好友");
                    return;
                }
                /** 任务奖励处理 */
                Map<String,String> taskMap = redisService.getMap("DIC:task_mark");
                String invite = taskMap.get("invite");
                SocialTaskReward socialTaskReward = remoteTaskService.dealTaskReward(inviteUserId, invite);
                System.out.println("    ====>    邀请任务奖励处理完成：" + socialTaskReward);

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
                        Map<String,Object> json = (Map<String,Object>) remoteResult.getObj();
                        fansGroupId = (String) json.get("tid");
                        remoteFriendService.updateFansGruop(fansGroupId, inviteUserId);
                        System.out.println("    ====>    建群拉粉 成功");
                    }
                } else {
                    //粉丝群ID不为空,拉粉入群
                    JsonResult jsonResult = YunXinUtil.addToGroup(fansGroupId, inviteUserAccid, fans, "0", "invite to fansGroup", null);
                    if (!jsonResult.getSuccess()) {
                        String code = remoteResult.getCode();
                        boolean objectResult = "801".equalsIgnoreCase(code);// 粉丝群人数上限则新建群
                        if (objectResult) {
                            System.out.println("    ====>    掌柜邀请注册吸粉(拉粉入群) 失败    ====>    粉丝群人数达上限");
                            //粉丝群ID为空,新建群拉人
                            JsonResult group = YunXinUtil.createGroup("fansGroup", inviteUserAccid, fans, "fansGroup", "fansGroup", "invite to fansGroup", "0", "0", null, null, "1", "1", "1", "1", null);
                            if (!group.getSuccess()) {
                                System.out.println("    ====>    掌柜邀请注册吸粉(新建粉丝群拉人) 失败");
                                return;
                            } else {
                                Map<String,Object> json = (Map<String,Object>) remoteResult.getObj();
                                fansGroupId = (String) json.get("tid");
                                remoteFriendService.updateFansGruop(fansGroupId, inviteUserId);
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


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "InviteFansRunnable{" + "user=" + user + '}';
    }
}
