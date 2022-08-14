package hry.mq.integral;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import hry.bean.JsonResult;
import hry.mall.integral.model.LevelRecord;
import hry.mall.integral.model.ShopMqExceptionLog;
import hry.mall.integral.remote.model.LendAccountAdd;
import hry.mall.integral.service.IntegralCommissionService;
import hry.mall.integral.service.IntegralLevelService;
import hry.mall.integral.service.ShopMqExceptionLogService;
import hry.mall.integral.service.ShopTeamLevelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author <a href="mailto:HelloHeSir@gmail.com">Mr_He</a>
 * 发放直推与拓展奖励
 */
public class MessageDistributeRecommendRewards implements ChannelAwareMessageListener {

    private static final String NAME = "distributeRecommendRewards";

    private Logger log = LoggerFactory.getLogger(MessageDistributeRecommendRewards.class);

    @Resource
    private ShopTeamLevelService shopTeamLevelService;

    @Resource
    private IntegralLevelService integralLevelService;
    @Resource
    private IntegralCommissionService integralCommissionService;
    @Resource
    private ShopMqExceptionLogService shopMqExceptionLogService;

    private List<LendAccountAdd> accountadds;

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        Long memberId=null; //用户id
        Long levelId=null;  //购买等级id
        BigDecimal money=null;  //购买等级花费金额
        try {
            System.out.println("mq发放推荐奖励");
            LevelRecord levelRecord = JSON.parseObject(new String(message.getBody(), StandardCharsets.UTF_8), LevelRecord.class);
            memberId=levelRecord.getMemberId(); //用户id
            levelId=levelRecord.getLevelId();  //购买等级id
            money=levelRecord.getMoney();  //购买等级花费金额
            Integer activateRebateSeries=3; //返佣层级
            Integer updateTeamLevelSeries=10; //维护上级身份的上级层数


            //8.给一级二级返佣
            JsonResult jsonResult = integralLevelService.activateRebate(memberId, levelId, money, activateRebateSeries);
            if(!jsonResult.getSuccess()){
                ShopMqExceptionLog shopMqExceptionLog = new ShopMqExceptionLog();
                shopMqExceptionLog.setCustomerId(memberId.toString());
                shopMqExceptionLog.setParam("返佣失败，参数是：memberId："+memberId+", levelId:"+levelId+",money:"+money+",activateRebateSeries:"+activateRebateSeries);
                shopMqExceptionLog.setRemark(jsonResult.getMsg());
                shopMqExceptionLog.setFunctionName("integralLevelService.activateRebate");
                shopMqExceptionLogService.save(shopMqExceptionLog);
                System.out.println("返佣失败，参数是：memberId："+memberId+", levelId:"+levelId+",money:"+money+",activateRebateSeries:"+activateRebateSeries);
            }
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            log.error("推荐奖励发放失败", e);
            ShopMqExceptionLog shopMqExceptionLog = new ShopMqExceptionLog();
            shopMqExceptionLog.setCustomerId(memberId.toString());
            shopMqExceptionLog.setParam("返佣失败，参数是：memberId："+memberId+", levelId:"+levelId+",money:"+money);
            shopMqExceptionLog.setRemark("远程调用异常");
            shopMqExceptionLog.setFunctionName("integralLevelService.activateRebate");
            shopMqExceptionLogService.save(shopMqExceptionLog);

            try {
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
            } catch (Exception e1) {
                log.error("队列：{}  ACK确认失败", NAME, e1);
            }
        }
    }




}