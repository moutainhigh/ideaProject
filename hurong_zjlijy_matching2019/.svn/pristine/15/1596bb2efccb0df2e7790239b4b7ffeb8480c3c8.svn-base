package hry.cm2.runnable;

import hry.cm2.deal.CmDealUtil;
import hry.cm2.dto.CmAccountRedis;
import hry.cm2.dto.Accountadd;
import hry.cm2.enums.CmAccountRemarkEnum;
import hry.cm2.profittwo.model.Cm2ProfitTwo;
import hry.cm2.profittwo.service.Cm2ProfitTwoService;
import hry.mq.producer.DealMsgUtil;
import hry.util.sys.ContextUtil;
import hry.utils.BaseConfUtil;
import org.apache.log4j.Logger;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 发放矿场收益
 * @author lenovo
 *
 */
public class GrantProfitTwoRunnable implements Runnable {

	private final Logger log = Logger.getLogger(ProfitTwoRunnable.class);
	
	private Cm2ProfitTwo cmProfitTwo;

	public GrantProfitTwoRunnable(Cm2ProfitTwo cmProfitTwo) {
		super();
		this.cmProfitTwo = cmProfitTwo;
		// TODO Auto-generated constructor stub
	}
	@Resource
	private  static Cm2ProfitTwoService cmProfitTwoService = (Cm2ProfitTwoService) ContextUtil.getBean("cm2ProfitTwoService");

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Long customerId = cmProfitTwo.getCustomerId();
		BigDecimal profit = cmProfitTwo.getProfit3();
		/** 查询平台币Code */
		String platCoin = BaseConfUtil.getConfigSingle("platCoin", "configCache:basefinanceConfig");
		//币账户加币
		/** 查询用户平台币账户信息 */
	    CmAccountRedis cmAccountRedis = CmDealUtil.getCmAccount(customerId, platCoin);
	    List<Accountadd> accountaddList = new ArrayList<>();
	    Accountadd accountadd = new Accountadd(customerId, platCoin, cmAccountRedis.getId(),
	    		profit, 1, CmAccountRemarkEnum.TYPE9.getIndex(),
	      "CM"+profit);
	    accountaddList.add(accountadd);
	    DealMsgUtil.sendAccountaddList(accountaddList);
		
	    /*更新未发放收益为已发放**/
	    cmProfitTwoService.updateStatus(customerId);
	}

}
