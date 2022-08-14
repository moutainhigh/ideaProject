package hry.mq.producer.service;

import com.alibaba.fastjson.JSON;
import hry.redis.common.utils.RedisService;
import hry.trade.redis.model.Accountadd;
import hry.util.SpringUtil;
import org.apache.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

@Service
public class MessageProducer {
	private Logger logger = Logger.getLogger(MessageProducer.class);

    @Resource(name="amqpTemplate")
    private AmqpTemplate amqpTemplate;


    /**
     * 发送账户操作信息
     * @param message
     */
    public void toAccount(Object message)  {
		List<Accountadd> accountaddlist = JSON.parseArray(message.toString(), Accountadd.class);
		for(Accountadd accountadd:accountaddlist){
			if(accountadd.getMoney().compareTo(new BigDecimal("999999999"))==1
					||accountadd.getMoney().compareTo(new BigDecimal("-999999999"))==-1){
				throw new RuntimeException("资金数据太大");
			}
		}
		/*RedisService redisService = SpringUtil.getBean("redisService");
		String isStop = redisService.get("deal:stop");
		if(org.apache.commons.lang3.StringUtils.isNotEmpty(isStop)){
			throw new RuntimeException("资金通道发送堵塞请不要进行资金操作");
		}*/

		try {
			amqpTemplate.convertAndSend("toAccountKey", message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    /**
     * 发送委托信息
     * @param message
     */
    public void toTrade(Object message)  {
    	try {
            amqpTemplate.convertAndSend("toTradeKey", message);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    /**
     * 发送委托信息
     * @param message
     */
    public void toLendRepay(Object message)  {
    	try {
    		amqpTemplate.convertAndSend("toLendRepayKey", message);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    /**
     * 发送委托信息
     * @param message
     */
    public void toLendPing(Object message)  {
    	try {
    		amqpTemplate.convertAndSend("toLendPingKey", message);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

	/**
	 * 快乐购发奖励toAwardKey
	 */
	public void toAward(Object message)  {
		try {
			amqpTemplate.convertAndSend("toAwardKey", message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 释放锁仓队列
	 */
	public void toReleaseOrder(Object message)  {
		try {
			amqpTemplate.convertAndSend("toReleaseOrderKey", message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 推荐奖励发送
	 * @param message
	 */
	public void toRecommendReward(Object message)  {
		try {
			amqpTemplate.convertAndSend("toRecommendRewardKey", message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 平台币
	 * @param message
	 */
	public void toPlatformCurrency(Object message)  {
		try {
			amqpTemplate.convertAndSend("toPlatformCurrencyKey", message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//消息
	public void toMessageWarn(Object message)  {
		try {
			amqpTemplate.convertAndSend("toMessageWarnKey", message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//自动锁仓
	public void toLockStorage(Object message)  {
		try {
			amqpTemplate.convertAndSend("toLockStorageKey", message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
     * 发送KLG买方付款消息
     * @param message
     */
    public void toKlgBuyPay(Object message)  {
    	try {
            amqpTemplate.convertAndSend("toKlgBuyPayKey", message);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    /**
     * 发送KLG抢单消息
     * @param message
     */
    public void toRobBuyTransaction(Object message)  {
    	try {
    		amqpTemplate.convertAndSend("toRobBuyTransactionKey", message);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    /**
	 * 发送清理订单消息
	 * @param message
	 */
	public void toBuySellAccount(Object message)  {
		try {
			amqpTemplate.convertAndSend("toBuySellAccountKey", message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 发送升级级差消息
	 * @param message
	 */
	public void toKlgGradation(Object message)  {
		try {
			amqpTemplate.convertAndSend("toKlgGradationKey", message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 李超项目---出局更新用户父级用户等级
	 * */
	public void toUserUpGrade(Object message) {
		try {
			amqpTemplate.convertAndSend("toUserUpGradeKey", message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 李超项目---更新outInfo表数据
	 * */
	public void toUpdateOutInfo(Object message) {
		try {
			amqpTemplate.convertAndSend("toUpdateOutInfoKey", message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * 李超项目---更新用户社区等级
	 * */
	public void toUserUpTeamGrade(Object message) {
		try {
			amqpTemplate.convertAndSend("toUserUpTeamGradeKey", message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 李超项目---发送管理奖
	 * **/
	public void toOutManageAward(Object message) {
		try {
			amqpTemplate.convertAndSend("toOutManageAwardKey", message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 李超项目---发送见点奖
	 * **/
	public void toOutSeeAward(Object message) {
		try {
			amqpTemplate.convertAndSend("toOutSeeAwardKey", message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 李超项目---静态收益发放
	 * **/
	public void toOutStaticAward(Object message) {
		try {
			amqpTemplate.convertAndSend("toOutStaticAwardKey", message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 李超项目---USDT兑换
	 * **/
	public void toSurplusUSDTAward(Object message) {
		try {
			amqpTemplate.convertAndSend("toSurplusUSDTAwardKey", message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 李超项目---USDT兑换
	 * **/
	public void toSurplusBBGOAward(Object message) {
		try {
			amqpTemplate.convertAndSend("toSurplusBBGOAwardKey", message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 李超项目---USDT兑换撤回
	 * **/
	public void toSurplusUSDTAwardTwo(Object message) {
		try {
			amqpTemplate.convertAndSend("toSurplusUSDTAwardTwoKey", message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 李超项目---USDT兑换撤回
	 * **/
	public void toSurplusBBGOAwardTwo(Object message) {
		try {
			amqpTemplate.convertAndSend("toSurplusBBGOAwardTwoKey", message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 李超项目---周期释放
	 * **/
	public void teamAwardRule(Object message) {
		try {
			amqpTemplate.convertAndSend("teamAwardRuleKey", message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
