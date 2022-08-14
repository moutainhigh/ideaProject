/**
 * Copyright:   北京互融时代软件有限公司
 *
 * @author: menwei
 * @version: V1.0
 * @Date: 2017-11-28 17:40:59
 */
package hry.customer.commend.service.impl;

import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.change.remote.exEntrust.RemoteExExOrderService;
import hry.core.constant.StringConstant;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.customer.commend.dao.AppCommendTradeDao;
import hry.customer.commend.model.AppCommendDeploy;
import hry.customer.commend.model.AppCommendMoney;
import hry.customer.commend.model.AppCommendRank;
import hry.customer.commend.model.AppCommendRebat;
import hry.customer.commend.model.AppCommendTrade;
import hry.customer.commend.model.AppCommendUser;
import hry.customer.commend.service.AppCommendDeployService;
import hry.customer.commend.service.AppCommendMoneyService;
import hry.customer.commend.service.AppCommendRankService;
import hry.customer.commend.service.AppCommendRebatService;
import hry.customer.commend.service.AppCommendTradeService;
import hry.customer.commend.service.AppCommendUserService;
import hry.customer.person.model.AppPersonInfo;
import hry.customer.user.service.AppCustomerService;
import hry.front.redis.model.UserRedis;
import hry.mq.producer.service.MessageProducer;
import hry.redis.common.dao.RedisUtil;
import hry.redis.common.utils.RedisService;
import hry.trade.entrust.dao.ExOrderInfoDao;
import hry.trade.entrust.model.ExOrderInfo;
import hry.trade.entrust.service.ExOrderInfoService;
import hry.trade.redis.model.Accountadd;
import hry.util.QueryFilter;
import hry.util.idgenerate.IdGenerate;
import hry.util.properties.PropertiesUtils;
import hry.util.sys.ContextUtil;
import hry.web.util.BaseConfUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonFormat.Value;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * <p> AppCommendTradeService </p>
 *
 * @author: menwei
 * @Date :          2017-11-28 17:40:59
 */
@Service("appCommendTradeService")
public class AppCommendTradeServiceImpl extends BaseServiceImpl<AppCommendTrade, Long> implements AppCommendTradeService {
    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(AppCommendTradeServiceImpl.class);
    @Resource(name = "appCommendTradeDao")
    @Override
    public void setDao(BaseDao<AppCommendTrade, Long> dao) {
        super.dao = dao;
    }
    public static final String APP_COMMOD_REBAT = "21";
    
    String appName = PropertiesUtils.APP.getProperty("app.appName");

    @Resource(name = "appCommendTradeDao")
    public AppCommendTradeDao appCommendTradeDao;
    
    @Resource(name="exOrderInfoDao")
    public ExOrderInfoDao exOrderInfoDao;
    
    @Resource(name = "appCustomerService")
    private AppCustomerService appCustomerService;

    @Resource(name = "appCommendTradeService")
    private AppCommendTradeService appCommendTradeService;

    @Resource(name = "appCommendUserService")
    public AppCommendUserService appCommendUserService;

    @Resource(name = "appCommendDeployService")
    public AppCommendDeployService appCommendDeployService;

    @Resource(name = "appCommendMoneyService")
    public AppCommendMoneyService appCommendMoneyService;

    @Resource(name = "exOrderInfoService")
    public ExOrderInfoService exOrderInfoService;

    @Resource(name = "appCommendRankService")
    public AppCommendRankService appCommendRankService;
    
    @Resource(name="appCommendRebatService")
    public AppCommendRebatService appCommendRebatService;
    
    @Resource
    private MessageProducer messageProducer;
    
    @Resource
    private RedisService redisService;
    private static final Logger log = LoggerFactory.getLogger(AppCommendTradeServiceImpl.class);

    @Override
    public List<AppCommendTrade> selectCommendTrade(String custromerName) {
        // TODO Auto-generated method stub
        return appCommendTradeDao.selectCommendTrade(custromerName);
    }

    @Override
    public List<AppCommendTrade> findByUids(List<Long> pids) {
        return appCommendTradeDao.findByUids(pids);
    }

    @Override
    public List<AppCommendTrade> findByUsername(String username) {
        return appCommendTradeDao.findByUsername(username);
    
    }
    public Boolean culBuy(ExOrderInfo order, AppCommendDeploy deploy){
            try {
                /**
                 * 买单用户
                 */
                if(null==order.getTransactionBuyFee()){
                    //没有手续费
                    return true;
                }
            	if(order.getTransactionBuyFee().compareTo(new BigDecimal("0"))<=0){
            		 //没有手续费
            		 return true;
            	}
                //查出买方的AppCommendUser
                Long buyCustomId = order.getBuyCustomId();
                QueryFilter buyUserFilter = new QueryFilter(AppCommendUser.class);
                buyUserFilter.addFilter("uid=", buyCustomId);
                AppCommendUser buyUser = appCommendUserService.get(buyUserFilter);
                if (buyUser == null) {
                	  log.error("找不到买单用户：{}，订单号：{}", order.getBuyUserName(), order.getOrderNum());
                	  return false;
                }else{
                	 if (null!=buyUser.getIsFrozen()&&buyUser.getIsFrozen() == 1) {
                		   log.info("该用户已被冻结：{}，订单号：{}", order.getBuyUserName(), order.getOrderNum());
                		   return true;
                	 }
                }
                String sid = buyUser.getSid();//上级树（1-4234,2-432,3-324）
            	if(null==sid){
                	  log.info("该用户没有推荐人");
                	 return true;
                }
                if( ! "".equals(",") && sid.startsWith(",")){
                    sid=sid.substring(1);
                }
               
                String[] checkr = checkSeachUser( order, deploy, buyCustomId, buyUser, order.getBuyUserName());
                if (checkr[1] != null) {
                	   log.info("已经到达返佣的上限，不予再奖励");
            		   return true;
                }
                
            	
                Integer minHierarchy = deploy.getMinHierarchy(); //最小奖励级数
                Integer maxHierarchy = deploy.getMaxHierarchy();//最大奖励级数
                if (StringUtils.isEmpty(minHierarchy)) minHierarchy = 0;
                if (StringUtils.isEmpty(maxHierarchy)) maxHierarchy =100;//如果没设置最大奖励级别默认无限级
                //如果最大奖励层级大于当前最大层级，则最大奖励层级等于当前最大层级
                String[] split = sid.split(",");
                if (split.length < maxHierarchy) {
                    maxHierarchy = split.length;
                }
                if(minHierarchy==0){
                	minHierarchy=1;
                }
    
                //开始轮询,买手续费是收交易币 
                Integer fixPriceType=1;//虚拟币
                Integer type= 1;//1买2卖
                excuteSaveCommission(buyUser,order, deploy,fixPriceType,order.getCoinCode(), minHierarchy, maxHierarchy, split, type);
  
            } catch (Exception e) {
                log.error("买单用户佣金计算异常：{}，订单号：{} ，错误信息：", order.getBuyUserName(), order.getOrderNum(), e);
                return false;
            }

	
        
        return true;
    }
    
    public Boolean culSell(ExOrderInfo order, AppCommendDeploy deploy){
    	  /**
         * 卖单用户
         */
        if(null==order.getTransactionBuyFee()){
            //没有手续费
            return true;
        }
        if(order.getTransactionSellFee().compareTo(new BigDecimal("0"))<=0){
   		 //没有手续费
   		 return true;
     	}
       //查出买方的AppCommendUser
       Long sellCustomId = order.getSellCustomId();
       QueryFilter sellUserFilter = new QueryFilter(AppCommendUser.class);
       sellUserFilter.addFilter("uid=", sellCustomId);
       AppCommendUser sellUser = appCommendUserService.get(sellUserFilter);
       if (sellUser == null) {
       	  log.error("找不到卖单用户：{}，订单号：{}", order.getSellUserName(), order.getOrderNum());
       	  return false;
       }else{
       	 if (null!=sellUser.getIsFrozen()&&sellUser.getIsFrozen() == 1) {
       		   log.info("该用户已被冻结：{}，订单号：{}", order.getSellUserName(), order.getOrderNum());
       		   return true;
       	 }
       }
       String sid = sellUser.getSid();//上级树（,1-4234,2-432,3-324）
   	   if(null==sid){
        	  log.info("该用户没有推荐人");
        	 return true;
        }
      if( ! "".equals(",") && sid.startsWith(",")){
          sid=sid.substring(1);
      }
       String[] checkr = checkSeachUser( order, deploy, sellCustomId, sellUser, order.getBuyUserName());
       if (checkr[1] != null) {
       	   log.info("已经到达返佣的上限，不予再奖励");
   		   return true;
       }
       
   	
       Integer minHierarchy = deploy.getMinHierarchy(); //最小奖励级数
       Integer maxHierarchy = deploy.getMaxHierarchy();//最大奖励级数
       if (StringUtils.isEmpty(minHierarchy)) minHierarchy = 0;
       if (StringUtils.isEmpty(maxHierarchy)) maxHierarchy =100;//如果没设置最大奖励级别默认无限级
       
       if(appName != null && "DFC".equals(appName)){
           maxHierarchy = 10;
       }
       
       //如果最大奖励层级大于当前最大层级，则最大奖励层级等于当前最大层级
       String[] split = sid.split(",");
       if (split.length < maxHierarchy) {
           maxHierarchy = split.length;
       }
       if(minHierarchy==0){
       	minHierarchy=1;
       }

       //开始轮询,卖手续费是收定价币
       Integer fixPriceType=order.getFixPriceType();
       Integer type= 2;//1买2卖
       excuteSaveCommission(sellUser,order, deploy,fixPriceType,order.getFixPriceCoinCode(), minHierarchy, maxHierarchy, split, type);
       return true;
    }
    @Override
    public Boolean dealCommission(String orderNum) {

        RemoteExExOrderService remoteOrderServiceService = (RemoteExExOrderService) ContextUtil.getBean("remoteExExOrderService");
        //订单
        List<ExOrderInfo> orderList = remoteOrderServiceService.findByOrderNum(orderNum);
        AppCommendDeploy deploy = appCommendDeployService.getAppCommendDeploy();
        if (null != orderList && orderList.size() > 0) {
            ExOrderInfo order = orderList.get(0);
            Boolean retsell=false;
            Boolean retbuy=false;
            //东方城项目返佣 只给卖单返佣
            if(appName != null && "DFC".equals(appName)){
                 retsell= culSell(order,deploy);
            }else{
                 retsell= culSell(order,deploy);
                 retbuy= culBuy(order,deploy);
            }
            
            
           
            //平台币算佣金
            order.setCoinCode(order.getBuyPlatCoin());
            order.setFixPriceCoinCode(order.getSellPlatCoin());
            order.setTransactionBuyFee(order.getTransactionBuyFeePlat());
            order.setTransactionSellFee(order.getTransactionSellFeePlat());
          //东方城项目返佣 只给卖单返佣
            if(appName != null && "DFC".equals(appName)){
                if(null!=order.getSellPlatCoin()){
                    Boolean retsellPlat= culSell(order,deploy);
                }
            }else{
                if(null!=order.getBuyPlatCoin()){
                    Boolean retbuyPlat= culBuy(order,deploy);
                }
                if(null!=order.getSellPlatCoin()){
                    Boolean retsellPlat= culSell(order,deploy);
                }
            }

            return ( retbuy ||retsell);
        }
        return true;
    	
    	}
 
  


    @Override
    public BigDecimal selectCommissionByMoney(BigDecimal money) {
        QueryFilter qf = new QueryFilter(AppCommendTrade.class);
        qf.addFilter("states=", 1);
        AppCommendDeploy appCommendDeploy = appCommendDeployService.get(qf);

        if (null != appCommendDeploy.getRankRatio()) {

            BigDecimal multiply = money.multiply(appCommendDeploy.getRankRatio());
            return multiply;
        }
        return BigDecimal.ZERO;
    }

    public static void main(String[] args) {
        /*int minHierarchy = 1;
        int num = 0;
        if (minHierarchy == 0) {
            num = 1;
            logger.error(num);
        } else if (minHierarchy > 0) {
            minHierarchy = minHierarchy - 1;
            logger.error(minHierarchy);
        }*/
        int i = 0/1;
        logger.error(i);
        int j = 1/0;
    }


    @Override
    public BigDecimal findOne(String userName, String fixPriceCoinCode) {
        // TODO Auto-generated method stub
        return appCommendTradeDao.findOne(userName, fixPriceCoinCode);
    }


    @Override
    public BigDecimal findTwo(String userName, String fixPriceCoinCode) {
        // TODO Auto-generated method stub
        return appCommendTradeDao.findTwo(userName, fixPriceCoinCode);

    }


    @Override
    public BigDecimal findThree(String userName, String fixPriceCoinCode) {
        // TODO Auto-generated method stub
        return appCommendTradeDao.findThree(userName, fixPriceCoinCode);
    }


    @Override
    public BigDecimal findLater(String userName, String fixPriceCoinCode) {
        // TODO Auto-generated method stub
        return appCommendTradeDao.findLater(userName, fixPriceCoinCode);
    }


    @RequestMapping(value = "/newTransactionPrice", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public BigDecimal newTransactionPrice(String coinCode) {
        logger.error(coinCode);
        BigDecimal price = new BigDecimal(0);
        RedisService redisService = (RedisService) ContextUtil.getBean("redisService");
        if (!StringUtils.isEmpty(coinCode)) {
            try {
                String coin = redisService.get(coinCode + ":currentExchangPrice");
                logger.error("最新价=0" + coin);
                if (!"".equals(coin) && coin != null) {
                    price = new BigDecimal(coin);
                } else {
                    price = new BigDecimal(1);
                }
            } catch (Exception e) {
                price = new BigDecimal(1);
            }

        }
        logger.error(price);
        return price;

    }


    private String[] checkSeachUser( ExOrderInfo order, AppCommendDeploy buyDeploy, Long buyCustomId, AppCommendUser buyUser, String buyUserName) {
        RemoteExExOrderService remoteOrderServiceService = (RemoteExExOrderService) ContextUtil.getBean("remoteExExOrderService");
        
    	String sid = buyUser.getSid();
        String[] result = new String[2];
        result[0] = sid;
        if (StringUtils.isEmpty(sid)) {
            log.info("查找不到用户：{} 的推荐人，订单号：{}", buyUserName, order.getOrderNum());
            result[1] = "0";
            return result;
        }
        
  /*      //todo,加上当日的查询条件，被推荐人当日交易笔数总和如超过15笔，15笔（包含第15笔）内可获得相应奖励，高于15笔以上从第16笔开始不计奖励。
        Long buyOrderCount = remoteOrderServiceService.ListCount(buyCustomId);
        if (buyOrderCount > Long.valueOf(buyDeploy.getTransactionNumber())) {
            log.info("买单用户：{} 超出交易笔数限制，订单号：{}", buyUserName, order.getOrderNum());
            result[1] = "0";
            return result;
        }*/
        return result;
    }
   
  
    
    private void excuteSaveCommission(AppCommendUser appCommendUserself,ExOrderInfo order, AppCommendDeploy deploy,Integer fixPriceType,String coinCode,
    		Integer minHierarchy, Integer maxHierarchy, String[] split, Integer type) {
    	BigDecimal jainbaseMoney=cul(order,deploy,type);//初始化佣金基数
    	int hierarchy=minHierarchy;
    	for (int j = minHierarchy-1; j < maxHierarchy; j++) {
       		String uid=split[j];//1-4234
       		String level = uid.split("-")[0];
    		uid=uid.split("-")[1];
    		//东方城项目先查询用户的一级推荐人总数
            if(appName != null && "dfc".equals(appName)){
               int fristLevelCount = appCommendUserService.countLen("1-"+uid);
               //判断用户的一级推荐人是否小于当前的级数
               if(fristLevelCount < Integer.valueOf(level)){
                   continue;
               }
            }

            QueryFilter buyUserFilter = new QueryFilter(AppCommendUser.class);
            buyUserFilter.addFilter("uid=", uid);
            AppCommendUser appCommendUser = appCommendUserService.get(buyUserFilter);
            //计算总佣金，和单独奖励佣金
            BigDecimal[] commendMoneyarr=culCommendMoney(jainbaseMoney,appCommendUser,deploy);
            BigDecimal totalMoney= commendMoneyarr[0]; //基础奖励+单独奖励
            BigDecimal aloneMoenyMoney=commendMoneyarr[1]; //单独奖励
            jainbaseMoney=totalMoney.subtract(aloneMoenyMoney); //更新佣金基数(只有基础奖励作为下一个父节点的佣金基数)？？
            //更新总的佣金记录
            updateAppCommendMoney(hierarchy,totalMoney,appCommendUser,fixPriceType,coinCode);
            //更新邀请排行榜
            updateAppCommendRank(totalMoney,appCommendUser,coinCode);
            //保存佣金明细
            saveAppCommendTrade(aloneMoenyMoney,totalMoney,hierarchy,type,order,appCommendUser);
            hierarchy++;
            
            /*   //保存佣金表以及邀请排行榜
            List<AppCommendUser> list2 = this.saveAppTradeFactor1(order,customer, j, deploy, type);*/
            
        }
    }
    private BigDecimal[] culCommendMoney(BigDecimal jainbaseMoney,AppCommendUser appCommendUser,AppCommendDeploy appCommendDeploy){
    	BigDecimal[] ret=new BigDecimal[2];
    	//计算基础奖励
    	BigDecimal baseMoney=jainbaseMoney.multiply(appCommendDeploy.getRankRatio()).divide(new BigDecimal("100"));
    	//判断是否有开启单独奖励
    	BigDecimal aloneMoneyMoney=new BigDecimal("0");
        if (appCommendUser.getAloneProportion() != null && appCommendUser.getAloneProportion().compareTo(new BigDecimal(0)) > 0) {
            BigDecimal addRate = appCommendUser.getAloneProportion().divide(new BigDecimal(100));
            aloneMoneyMoney = jainbaseMoney.multiply(addRate);
        }
        BigDecimal totalMoney = baseMoney.add(aloneMoneyMoney);
        ret[0]=totalMoney;
        ret[1]=aloneMoneyMoney;
    	return ret;
    }
    private BigDecimal cul(ExOrderInfo order,AppCommendDeploy appCommendDeploy,int type){
 	   BigDecimal transactionFee=new BigDecimal("0");
 	   if(type==1){ //买
 		   transactionFee=order.getTransactionBuyFee();
 	   }else{
 		   transactionFee=order.getTransactionSellFee();
 	   }

 	   //平台返佣比例
      //  BigDecimal bigDecimalOneRate = appCommendDeploy.getRankRatio().divide(new BigDecimal("100"));
        if(appCommendDeploy.getReserveMoney()==null){
            appCommendDeploy.setReserveMoney(new BigDecimal(0));
        }
        //平台扣除比例
        BigDecimal reserveMoney = appCommendDeploy.getReserveMoney().divide(new BigDecimal("100"));
        //奖励基数
        BigDecimal    oneMoney = transactionFee.multiply((new BigDecimal(1).subtract(reserveMoney)));
        return oneMoney;

    	
    }
   
   //更新总佣金记录
   private void updateAppCommendMoney(Integer j,BigDecimal totalMoney,AppCommendUser appCommendUser,Integer fixPriceType,String coinCode){
	   QueryFilter qfrank = new QueryFilter(AppCommendMoney.class);
       qfrank.addFilter("custromerId=", appCommendUser.getUid());
       qfrank.addFilter("coinCode=", coinCode);
       List<AppCommendMoney> findrank = appCommendMoneyService.find(qfrank);
       if (findrank.size() > 0) {
    	   AppCommendMoney appCommendMoney =findrank.get(0);
    	   appCommendMoney.setMoneyNum(appCommendMoney.getMoneyNum().add(totalMoney));
	       if(j==1){
	    	   appCommendMoney.setCommendOne(appCommendMoney.getCommendOne().add(totalMoney));   
	       }else if(j==2){
	    	   appCommendMoney.setCommendTwo(appCommendMoney.getCommendTwo().add(totalMoney));   
	       }else if(j==3){
	    	   appCommendMoney.setCommendThree(appCommendMoney.getCommendThree().add(totalMoney));   
	       }else {
	    	   appCommendMoney.setCommendLater(appCommendMoney.getCommendLater().add(totalMoney));   
	       }
	       appCommendMoneyService.update(appCommendMoney);
    	   
       }else{
		   AppCommendMoney appCommendMoney = new AppCommendMoney();
	       appCommendMoney.setCustromerId(appCommendUser.getUid());
	       appCommendMoney.setCustromerName(appCommendUser.getUsername());
	       appCommendMoney.setFixPriceType(fixPriceType);
	       appCommendMoney.setCoinCode(coinCode);
	       appCommendMoney.setPaidMoney(BigDecimal.ZERO);
	       appCommendMoney.setMoneyNum(totalMoney);
	       if(j==1){
	    	   appCommendMoney.setCommendOne(totalMoney);   
	       }else if(j==2){
	    	   appCommendMoney.setCommendTwo(totalMoney);   
	       }else if(j==3){
	    	   appCommendMoney.setCommendThree(totalMoney);   
	       }else {
	    	   appCommendMoney.setCommendLater(totalMoney);   
	       }
	       appCommendMoneyService.save(appCommendMoney);
       }
   }
   //更新佣金排行榜
   private void updateAppCommendRank(BigDecimal totalMoney,AppCommendUser appCommendUser,String coinCode){


         String coinMoneyKey = coinCode + "_USDT:currentExchangPrice";
         String coinMoney = redisService.get(coinMoneyKey);
         if (!StringUtils.isEmpty(coinMoney)){ coinMoney = "0";}
        BigDecimal multiply;
         if( "USDT".equals(coinCode) && coinMoney == null){
             multiply = totalMoney;
         }else{
            multiply = totalMoney.multiply(new BigDecimal(coinMoney));//手续费转换为USDT
         }
         QueryFilter qfrank = new QueryFilter(AppCommendRank.class);
        qfrank.addFilter("userId=", appCommendUser.getUid());
        List<AppCommendRank> findrank = appCommendRankService.find(qfrank);
     if (findrank.size() > 0) {
    	 AppCommendRank  appCommendRank = findrank.get(0);
    	 appCommendRank.setFixMoney(appCommendRank.getFixMoney().add(multiply));
    	 appCommendRankService.update(appCommendRank);
     }else{
    	 AppCommendRank appCommendRankone = new AppCommendRank();
         appCommendRankone.setUserId(appCommendUser.getUid());
         appCommendRankone.setUserName(appCommendUser.getUsername());
         appCommendRankone.setCoinCode(coinCode);
         appCommendRankone.setFixCoin(coinCode);//???
         appCommendRankone.setFixMoney(multiply);
         appCommendRankService.save(appCommendRankone);
     }
   }
   //保存佣金明细
   private void saveAppCommendTrade(BigDecimal aloneMoenyMoney,BigDecimal totalMoney, int hierarchy, Integer type,ExOrderInfo orderInfo, AppCommendUser appCommendUser){

       // TODO Auto-generated method stub
       if (null != appCommendUser) {
           AppCommendTrade appCommendTrade = new AppCommendTrade();
          

           if (type == 1) {
               appCommendTrade.setCoinCode(orderInfo.getCoinCode());
               appCommendTrade.setDeliveryId(Integer.valueOf(orderInfo.getBuyCustomId().toString()));
               appCommendTrade.setBasemoney(orderInfo.getTransactionBuyFee());
               appCommendTrade.setPersonType(1);
               appCommendTrade.setDeliveryName(orderInfo.getBuyUserName());
               appCommendTrade.setFixPriceType(1); //交易币肯定是虚拟币
           } else if (type == 2) {
               appCommendTrade.setCoinCode(orderInfo.getFixPriceCoinCode());
               appCommendTrade.setDeliveryId(Integer.valueOf(orderInfo.getSellCustomId().toString()));
               appCommendTrade.setBasemoney(orderInfo.getTransactionSellFee());
               appCommendTrade.setPersonType(2);
               appCommendTrade.setDeliveryName(orderInfo.getSellUserName());
               appCommendTrade.setFixPriceType(orderInfo.getFixPriceType());
           }
           appCommendTrade.setCurrentMoney(orderInfo.getTransactionPrice());
           appCommendTrade.setOrdertNum(orderInfo.getOrderNum());
           appCommendTrade.setCustromerName(appCommendUser.getUsername());
           appCommendTrade.setCustromerId(appCommendUser.getUid());
           appCommendTrade.setUserMoney(aloneMoenyMoney);
           // 设置买方总交的手续费
           appCommendTrade.setFeeamout(orderInfo.getTransactionCount());
           appCommendTrade.setRewardmoney(totalMoney);
           appCommendTrade.setHierarchy(hierarchy);
           appCommendTrade.setTransactionTime(orderInfo.getTransactionTime());
         
           /*String coin = orderInfo.getCoinCode() + "_" + "USDT";  //佣金币与usdt之间的交易对
           BigDecimal price = this.newTransactionPrice(coin);//最新价
           BigDecimal multiply = price.multiply(appCommendUser.getMoneydic());//最新价*佣金
           appCommendTrade.setChangeMoney(multiply);*/
           
           //设置是否有单独奖励1有，2，没有
           if (aloneMoenyMoney.compareTo(new BigDecimal("0"))>0) {
               //个人返佣比例
        	   appCommendTrade.setRatetype("1");
           } else {
        	   appCommendTrade.setRatetype("2");
           }
           appCommendTrade.setRewardmoney(totalMoney);
           appCommendTrade.setUserMoney(aloneMoenyMoney);
           super.save(appCommendTrade);
       } else {
       }
   
   }

    /**
     * sql分页
     * @param map
     * @return
     */
	@Override
	public PageResult findPageBySqlList(String email,String mobilePhone,
			String deliveryEmail,String deliveryMobilePhone
			,int startpage,int lengthpage ){

	
		
		//----------------------分页查询头部外壳------------------------------
		//创建PageResult对象
		PageResult pageResult = new PageResult();
		pageResult.setPage(startpage);
		pageResult.setPageSize(lengthpage);
		//----------------------查询开始------------------------------

		Map<String,Object> map = new HashMap<String,Object>();
	    Integer start=startpage*lengthpage;
		Integer end=(startpage+1)*lengthpage;
		map.put("start", start);
		map.put("end", end);
		
		Map<String,Object> mapcustomer = new HashMap<String,Object>();
		if(!StringUtils.isEmpty(email)){
			
			mapcustomer.put("email", email);
		}
		if(!StringUtils.isEmpty(mobilePhone)){
			mapcustomer.put("mobilePhone", mobilePhone);
		}
		if(mapcustomer.size()>0){
			List<AppPersonInfo> listpersoninfo=appCommendTradeDao.findCustomerIdByemailAndphone(mapcustomer);
			if(listpersoninfo.size()>0){
				map.put("cusomerId", listpersoninfo.get(0).getCustomerId());
			}else{
				List<AppCommendTrade>  list=new ArrayList<AppCommendTrade>();  
				//设置分页数据
				pageResult.setRows(list);
				//设置总记录数
				pageResult.setRecordsTotal(Long.valueOf("0"));
				return pageResult;
				
			}
			
		}
		Map<String,Object> mapdeliverycustomer = new HashMap<String,Object>();
	    if(!StringUtils.isEmpty(deliveryEmail)){
	    	mapdeliverycustomer.put("email", deliveryEmail);
		}
		if(!StringUtils.isEmpty(deliveryMobilePhone)){
			mapdeliverycustomer.put("mobilePhone", deliveryMobilePhone);
		}
		if(mapdeliverycustomer.size()>0){
			List<AppPersonInfo> listeliverypersoninfo=appCommendTradeDao.findCustomerIdByemailAndphone(mapdeliverycustomer);
			if(listeliverypersoninfo.size()>0){
				map.put("deliveryId", listeliverypersoninfo.get(0).getCustomerId());
			}else{
				List<AppCommendTrade>  list=new ArrayList<AppCommendTrade>();  
				//设置分页数据
				pageResult.setRows(list);
				//设置总记录数
				pageResult.setRecordsTotal(Long.valueOf("0"));
				return pageResult;
				
			}
			
		}
		
		
		Long count=appCommendTradeDao.findPageBySqlCount(map);
		List<AppCommendTrade>  list=appCommendTradeDao.findPageBySqlList(map);
		//----------------------查询结束------------------------------
		
		//----------------------分页查询底部外壳------------------------------
		//设置分页数据
		pageResult.setRows(list);
		//设置总记录数
		pageResult.setRecordsTotal(count);
	
		//----------------------分页查询底部外壳------------------------------
		
		return pageResult;
		
	
		
	}
		
		


	@Override
	public PageResult findPageBySql(QueryFilter filter) {
		//----------------------分页查询头部外壳------------------------------
		Map<String,Object> map = new HashMap<String,Object>();
		//创建PageResult对象
		PageResult pageResult = new PageResult();
		Page<AppCommendTrade> page = null;
		if(filter.getPageSize().compareTo(Integer.valueOf(-1))==0){
			//pageSize = -1 时  
			//pageHelper传pageSize参数传0查询全部
			page= PageHelper.startPage(filter.getPage(), 0);
		}else{
			page = PageHelper.startPage(filter.getPage(), filter.getPageSize());
		}
		//----------------------分页查询头部外壳------------------------------
		
		//----------------------查询开始------------------------------
		String email = filter.getRequest().getParameter("email");
		String mobilePhone = filter.getRequest().getParameter("mobilePhone");
		String deliveryEmail = filter.getRequest().getParameter("deliveryEmail");
		String deliveryMobilePhone = filter.getRequest().getParameter("deliveryMobilePhone");
		
		
		if(!StringUtils.isEmpty(email)){
			map.put("email", "%"+email+"%");
		}
		if(!StringUtils.isEmpty(mobilePhone)){
			map.put("mobilePhone", "%"+mobilePhone+"%");
		}
		if(!StringUtils.isEmpty(deliveryEmail)){
			map.put("deliveryEmail", "%"+deliveryEmail+"%");
		}
		if(!StringUtils.isEmpty(deliveryMobilePhone)){
			map.put("deliveryMobilePhone", "%"+deliveryMobilePhone+"%");
		}
		List<AppCommendTrade> pageBySql = appCommendTradeDao.findPageBySql(map);
		//----------------------查询结束------------------------------
		
		//----------------------分页查询底部外壳------------------------------
		//设置分页数据
		pageResult.setRows(pageBySql);
		//设置总记录数
		pageResult.setRecordsTotal(page.getTotal());
		pageResult.setDraw(filter.getDraw());
		pageResult.setPage(filter.getPage());
		pageResult.setPageSize(filter.getPageSize());
		//----------------------分页查询底部外壳------------------------------
		return pageResult;
	}

	@Override
	public JsonResult payCommissionsMoney(Map<String, Object> map) {
        JsonResult result = new JsonResult();
        String sumRewardmoney = map.get("sumRewardmoney").toString();
        String coinCode = map.get("coinCode").toString();
        String custromerId = map.get("custromerId").toString();
      //map.put("fixPriceCoinCode",fixPriceCoinCode);
        String standardsLimit = map.get("standardsLimit").toString();
        //查询指定用户指定时间段指定交易对的交易额
        BigDecimal transactionAmount = exOrderInfoDao.transactionAmount(map);
        Map<String, Object> newMap = new HashMap<>();
        newMap.put("beginTime",map.get("beginTime"));
        newMap.put("endTime",map.get("endTime"));
        newMap.put("coinCode",coinCode);
        newMap.put("custromerId",custromerId);
        List<AppCommendTrade> byCustromerIdList = appCommendTradeDao.findByCustromerIdList(newMap);
        //如果用户当天交易额大于等于指定额度发放开始派发佣金
        if (transactionAmount != null && transactionAmount.compareTo(new BigDecimal(standardsLimit))>=0){
            // 存明细
            AppCommendRebat appCommendRebat = new AppCommendRebat();
          
            try {
                RedisUtil<UserRedis> redisUtil = new RedisUtil<UserRedis>(UserRedis.class);
                UserRedis userRedis = redisUtil.get(custromerId);
                if (null == userRedis) {
                    result.setMsg("此用户的缓存账号为空，登陆会生成缓存账号");
                    result.setSuccess(false);
                    return result;
                }
                appCommendRebat.setCoinCode(coinCode);
                appCommendRebat.setRebatMoney(new BigDecimal(sumRewardmoney));
                // appCommendRebat.setTrueName(appCommendMoney.getCustromerName());
                appCommendRebat.setCustomerId(Integer.valueOf(custromerId));
                appCommendRebat.setTransactionNum(IdGenerate.transactionNum(APP_COMMOD_REBAT));
                String coin = BaseConfUtil.getConfigSingle("configCache:financeConfig", "language_code");
                    if (coinCode.equals(coin)) {
                        // ----发送mq消息----start
                        // 热账户增加
                        Accountadd accountadd = new Accountadd();
                        Long accountId = userRedis.getAccountId();
                        accountadd.setAccountId(accountId);
                        accountadd.setMoney(new BigDecimal(sumRewardmoney));
                        accountadd.setMonteyType(1);
                        accountadd.setRemarks(32);
                        accountadd.setAcccountType(0);
                        accountadd.setTransactionNum(appCommendRebat
                                .getTransactionNum());

                        List<Accountadd> list2 = new ArrayList<Accountadd>();
                        list2.add(accountadd);
                        messageProducer.toAccount(JSON.toJSONString(list2));
                    } else {
                        // ----发送mq消息----start
                        // 热账户增加
                        Accountadd accountadd = new Accountadd();
                        Long accountId = userRedis.getDmAccountId(coinCode);
                        accountadd.setAccountId(accountId);
                        accountadd.setMoney(new BigDecimal(sumRewardmoney));
                        accountadd.setMonteyType(1);
                        accountadd.setRemarks(32);
                        accountadd.setAcccountType(1);
                        accountadd.setTransactionNum(appCommendRebat.getTransactionNum());
                        List<Accountadd> list3 = new ArrayList<Accountadd>();
                        list3.add(accountadd);
                        messageProducer.toAccount(JSON.toJSONString(list3));
                    }
                    appCommendRebat.setStatus(1);
                    result.setMsg("派送成功");
                    result.setSuccess(true);
                //派发成功,修改佣金记录状态
                for (AppCommendTrade appCommendTrade : byCustromerIdList){
                    appCommendTrade.setStatus(1);
                    appCommendTradeService.update(appCommendTrade);
                }

            } catch (Exception e) {
                appCommendRebat.setStatus(0);
                //派发失败,修改佣金记录状态
                for (AppCommendTrade appCommendTrade : byCustromerIdList){
                    appCommendTrade.setStatus(3);
                    appCommendTradeService.update(appCommendTrade);
                }
                result.setMsg("派送失败");
                result.setSuccess(false);
                logger.error("消息发送失败：{}" + custromerId);
            }
            appCommendRebatService.save(appCommendRebat);
        }else{
            //用户交易额没有达到指定额度  佣金失效
            for (AppCommendTrade appCommendTrade : byCustromerIdList){
                appCommendTrade.setStatus(2);
                appCommendTradeService.update(appCommendTrade);
            }
            result.setMsg("用户"+ map.get("fixPriceCoinCode").toString()+"交易额为"+transactionAmount+" ,没有到达派发佣金的条件");
            result.setSuccess(false);
        }
        return result;
    }


   
}
