/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0 
 * @Date:        2016年5月24日 上午9:36:36
 */
package hry.mall.integral.service.impl;

import hry.bean.JsonResult;
import hry.mall.integral.service.IntegralLevelService;
import hry.mall.integral.service.ShopTeamLevelService;
import hry.util.SpringUtil;
import org.apache.log4j.Logger;

import java.math.BigDecimal;


/**
 * 
 * @author gaomm
 *
 */
public class UserByLevelRunable implements Runnable {

	private final static Logger logger = Logger.getLogger(UserByLevelRunable.class);

	private Long memberId; //用户id
	private Long levelId;  //购买等级id
	private BigDecimal money;  //购买等级花费金额
	private Integer activateRebateSeries; //返佣层级
	private Integer updateTeamLevelSeries; //维护上级身份的上级层数

	public UserByLevelRunable(Long memberId, Long levelId,BigDecimal money,Integer activateRebateSeries,Integer updateTeamLevelSeries ){
		this.memberId=memberId;
		this.levelId=levelId;
		this.money=money;
		this.activateRebateSeries=activateRebateSeries;
		this.updateTeamLevelSeries=updateTeamLevelSeries;
	}
	@Override
	public void run() {
		IntegralLevelService integralLevelService = (IntegralLevelService) SpringUtil.getBean("integralLevelService");
		ShopTeamLevelService shopTeamLevelService = (ShopTeamLevelService) SpringUtil.getBean("shopTeamLevelService");
		//8.给一级二级返佣
		JsonResult jsonResult = integralLevelService.activateRebate(memberId, levelId,money,activateRebateSeries);
		if(!jsonResult.getSuccess()){
			System.out.println("返佣失败，参数是：memberId："+memberId+", levelId:"+levelId+",money:"+money+",activateRebateSeries:"+activateRebateSeries);
		}
		//9.维护5级内的上级的身份   （暂不维护。定时器维护）
//		Boolean aBoolean2 = shopTeamLevelService.updateTeamLevel(memberId, updateTeamLevelSeries);
//		if(aBoolean2){
//			System.out.println("维护上级等级失败，参数是：memberId："+memberId+",updateTeamLevelSeries:"+updateTeamLevelSeries);
//		}

	}




}
