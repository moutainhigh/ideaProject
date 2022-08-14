package hry.util;

import com.alibaba.fastjson.JSONObject;
import hry.redis.common.utils.RedisService;
import hry.util.sys.ContextUtil;

import java.util.Map;

/**
 * 消息模版KEY
 */
public class NoticeTemplateUtil {


    /**
     * 推送地址前缀
     */
    public final static String NODE_PUSH_URL = "NODE:PUSHURL:";


    /**
     * 注册成功
     */
    public final static int REGIST_SUCCESS = 1;

    /**
     * 登录密码修改成功
     */
    public final static int PWD_MOD_SUCCESS = 2;

    /**
     * 登录密码重置成功
     */
    public final static int PWD_RES_SUCCESS = 3;

    /**
     * 实名认证成功
     */
    public final static int REALNAME_SUCCESS = 4;

    /**
     * 实名认证失败
     */
    public final static int REALNAME_FAILE = 5;

    /**
     * 充币成功
     */
    public final static int COIN_IN_SUCCESS = 6;

    /**
     * 充币失败
     */
    public final static int COIN_IN_FAILE = 7;

    /**
     * 提币成功
     */
    public final static int COIN_OUT_SUCCESS = 8;

    /**
     * 提币失败
     */
    public final static int COIN_OUT_FAILE = 9;

    /**
     * 转账
     */
    public final static int COIN_TO_SUCCESS = 10;

    /**
     * 支付
     */
    public final static int COIN_PAY_SUCCESS = 11;

    /**
     * 资金密码设置成功
     */
    public final static int ACPWD_SET_SUCCESS = 12;

    /**
     * 资金密码修改成功
     */
    public final static int ACPWD_MOD_SUCCESS = 13;

    /**
     * 资金密码重置成功
     */
    public final static int ACPWD_RES_SUCCESS = 14;

    /**
     * 好友申请请求
     */
    public final static int FRIEND_APPLY = 15;

    /**
     * 好友申请接受
     */
    public final static int FRIEND_AGREE = 16;

    /**
     * 好友申请拒绝
     */
    public final static int FRIEND_REJECT = 17;

    /**
     * 平台币交易
     */
    public final static int COIN_TRADE_SUCCESS = 18;

    /**
     * 矿机购买
     */
    public final static int MILL_TRADE_SUCCESS = 19;

    /**
     * 会员升级
     */
    public final static int VIP_UP_SUCCESS = 20;

    /**
     * 会员续购
     */
    public final static int VIP_RENEW_SUCCESS = 21;

    /**
     * 朋友圈打赏
     */
    public final static int FRIEND_REWARD_SUCCESS = 22;

    /**
     * 许愿墙上链
     */
    public final static int WISH_WALL_SUCCESS = 23;

    /**
     * 好友删除
     */
    public final static int FRIEND_DELETE = 24;


    /**
     * 替换HRY_
     *
     * @param content
     * @param params
     * @return
     */
    public static String replaceKey(String content, Map<String,String> params) {
        for (Map.Entry<String,String> entry : params.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            content = content.replace(key, value);
        }
        return content;
    }

    /**
     * 模版参数用户手机
     */
    public final static String HRY_USER = "HRY_USER";

    /**
     * 模版参数用户手机
     */
    public final static String HRY_PHONE = "HRY_PHONE";

    /**
     * 模版参数提币信息
     */
    public final static String HRY_COIN_OUT = "HRY_COIN_OUT";

    /**
     * 模版参数充币信息
     */
    public final static String HRY_COIN_IN = "HRY_COIN_IN";

    /**
     * 模版参数时间信息
     */
    public final static String HRY_TIME = "HRY_TIME";

    /**
     * 模版参数个数信息
     */
    public final static String HRY_NUM = "HRY_NUM";

    /**
     * 模版参数矿机型号信息
     */
    public final static String HRY_MILL_NUM = "HRY_MILL_NUM";

    /**
     * 模版参数会员等级信息
     */
    public final static String HRY_VIP_NUM = "HRY_VIP_NUM";

    /**
     * 模版参数会员续购时间信息
     */
    public final static String HRY_VIP_TIME = "HRY_VIP_TIME";

    /**
     * 模版参数客服电话
     */
    public final static String HRY_TEL = "HRY_TEL";


    /**
     * 获取客服电话
     *
     * @return
     */
    public static String getHryTel() {
        RedisService redisService = (RedisService) ContextUtil.getBean("redisService");
        String configCache_all = redisService.get("configCache:all");
        String servicePhone = "";
        if (configCache_all != null) {
            JSONObject configCacheJson = JSONObject.parseObject(configCache_all);
            servicePhone = configCacheJson.getString("servicePhone");
        }
        return servicePhone;
    }

    /**
     * 获取推送地址
     *
     * @param customerId
     * @return
     */
    public static String getPushUrl(Long customerId) {
        RedisService redisService = (RedisService) ContextUtil.getBean("redisService");
        String pushUrl = redisService.get(NODE_PUSH_URL + customerId.toString());
        return pushUrl;
    }

}
