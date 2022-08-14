package hry.util;

/**
 * @author javal
 * @title: SocialUtil
 * @projectName hry_global_mall_8.0
 * @description: TODO
 * @date 2019/6/1013:51
 */
public class SocialUtil {
    /**
     * 果园配置缓存KEY
     */
    public static final String ORCHARD_CONF = "SOCIAL:CACHE:ORCHARD_CONF";

    /**
     * SNS打赏配置缓存KEY
     */
    public static final String SNS_PAY_CONF = "SOCIAL:CACHE:SNS_PAY_CONF";

    /**
     * 算力数币奖励配置缓存KEY
     * 按coinCode缓存
     */
    public static final String MINING_REWARD_RULE_CONF = "SOCIAL:CACHE:MINING_REWARD_RULE_CONF";

    /**
     * 任务数币奖励配置缓存KEY
     * 按coinCode缓存
     */
    public static final String TASK_REWARD_RULE_CONF = "SOCIAL:CACHE:TASK_REWARD_RULE_CONF";

    /**
     * 矿机数币奖励配置缓存KEY
     * 按coinCode缓存
     */
    public static final String MILL_REWARD_RULE_CONF = "SOCIAL:CACHE:MILL_REWARD_RULE_CONF";

    /**
     * 数币奖励任务KEY
     */
    public static final String JOB_PREFIX_MINING_REWARD = "JOB_NAME_MINING_REWARD";

    /**
     * 币种信息缓存KEY
     */
    public static final String PRODUCT_KEY = "HRY:EXCHANGE:PRODUCT";

    /**
     * 果园奖励缓存KEY前缀
     */
    public static final String SOCIAL_GATHERED_CACHE = "SOCIAL:GATHERED:CACHE:";

    /**
     * 果园奖励已收取缓存KEY前缀
     */
    public static final String GATHERED_CACHE = "SOCIAL:GATHERED:USER";

    /**
     * 仅本人可收取状态
     */
    public static final String GATHERE_ONLYSELF = "1";


    /**
     * 果园奖励昨日产出缓存KEY
     */
    public static final String MINING_REWARD_YESTERDAY_OUT = "SOCIAL:CACHE:MINING_REWARD_YESTERDAY_OUT";

}
