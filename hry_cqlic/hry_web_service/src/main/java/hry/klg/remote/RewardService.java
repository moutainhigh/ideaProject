/**
 * 111
 */

package hry.klg.remote;

import hry.front.redis.model.UserRedis;
import hry.klg.reward.service.KlgRewardService;
import hry.redis.common.dao.RedisUtil;
import hry.util.SpringUtil;
import java.math.BigDecimal;

public class RewardService implements  Runnable{
    private Long customerId;//用户ID
    private String coinCode;//币种名
    private String transactionNum;//流水号
    private  Long foreignKey;//卖单ID
    private BigDecimal money;//金额
    private Integer type;//类型
    private RedisUtil<UserRedis> redisRedisUtil;

/*
    private Integer level;//见点代数
    private BigDecimal seePointMoney;//见点的额度
    private BigDecimal gradationMoney;//级差的额度
    private BigDecimal gradationRemainMoney;//级差剩余
    private List<KlgTeamlevel> teamlevels;*/

    public RewardService(RedisUtil<UserRedis> redisRedisUtil,String transactionNum,Long foreignKey,Long customerId, String coinCode, BigDecimal money, Integer type) {
        this.redisRedisUtil = redisRedisUtil;
        this.customerId = customerId;
        this.transactionNum = transactionNum;
        this.foreignKey = foreignKey;
        this.coinCode = coinCode;
        this.money = money;
        this.type = type;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCoinCode() {
        return coinCode;
    }

    public void setCoinCode(String coinCode) {
        this.coinCode = coinCode;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public void run() {
        if(money.compareTo(BigDecimal.ZERO)>0){
            KlgRewardService klgRewardService= SpringUtil.getBean("klgRewardService");
            UserRedis userRedis = redisRedisUtil.get(customerId.toString());
            Long accountId = userRedis.getDmAccountId(coinCode);
            klgRewardService.saveKlgRewardRecord(foreignKey,transactionNum,customerId,accountId,money,type);
        }
    }
}
