package hry.klg.aop;

import com.alibaba.fastjson.JSON;
import hry.bean.JsonResult;
import hry.bean.ObjectUtil;
import hry.klg.level.service.KlgCustomerLevelService;
import hry.klg.limit.model.AmountLimitType;
import hry.klg.limit.service.KlgAmountLimitationService;
import hry.klg.remote.RemoteKlgService;
import hry.klg.transaction.service.KlgBuyTransactionService;
import hry.message.model.MessageType;
import hry.message.model.RemoteMessage;
import hry.mq.producer.service.MessageProducer;
import hry.util.SpringUtil;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Aspect
@Component
public class RemoteKlgAccountAspect {
    private final Logger logger = Logger.getLogger(RemoteKlgAccountAspect.class);
    @Resource
    private MessageProducer messageProducer;
    @Resource
    private RemoteKlgService remoteKlgService;
    @Resource
    private KlgAmountLimitationService klgAmountLimitationService;
    @Resource
    private KlgBuyTransactionService klgBuyTransactionService;
    @Resource
    private KlgCustomerLevelService klgCustomerLevelService;

    private static  ExecutorService executor = Executors.newCachedThreadPool();

    //预约购买
    @Around(value = "execution(* hry.klg.remote.RemoteKlgServiceImpl.appointmentPurchase(..))&&args(map)")
    public Object  purchase(ProceedingJoinPoint joinPoint, HashMap<String, String> map) throws Throwable {
        JsonResult result=null;
        JsonResult jsonResult= remoteKlgService.isCheckOpenTime();
          if(jsonResult.getSuccess()){
              Long customerId=Long.valueOf(map.get("customerId"));
              BigDecimal number=new BigDecimal(map.get("number"));
              // 验证用户购买间隔市场
              boolean checkbuy=klgBuyTransactionService.checkBuyInterval(customerId);
              if(!checkbuy){
                    return new JsonResult().setSuccess(false).setMsg("klg_caochugoumaijiange");//超出购买间隔
               }
              boolean flag=remoteKlgService.isCheckLimitMoney(customerId,number);
              if(!flag){
                return new JsonResult().setSuccess(false).setMsg("klg_caochujinexianzhi");//超出金额限制
              }
              AmountLimitType limitType=AmountLimitType.Subscribe;
              int count=klgBuyTransactionService.getBuyTransactionCountByCustomerId(customerId);
              if(count==0){//为0说明第一次预约 为新人
                  limitType=AmountLimitType.NewPeople;
              }
              boolean flg= klgAmountLimitationService.isCheckNum(limitType,number);
              //额度是否购买
              if(!flg){
                return new JsonResult().setSuccess(false).setMsg("klg_dangtianeduyiwan");//当天限制额度已用完
              }
              result=(JsonResult)joinPoint.proceed();
                if(result.getSuccess()){//预约成功

                    // 减少限制额度
                    boolean f= klgAmountLimitationService.reduceLimitQuota(limitType,number);
                    if(!f){
                        System.out.println("减少限制额度 异常"+limitType+"====="+number);
                    }
                    //增加用户奖励额度
                    //2019年7月9日 获取的额度 分两阶段  1 预约买入获得的20% 付尾款80%
                    klgCustomerLevelService.bookingRewardQuotaAdd(customerId,number);
                }
            }else{
                return new JsonResult().setSuccess(false).setMsg("klg_nokaipanshijian");//当前非开盘时间
          }
          return  result;
    }

    //会员升级
    @Around(value = "execution(* hry.klg.remote.RemoteKlgServiceImpl.upgradeUserLevel(..))&&args(map)")
    public Object  upgrade(ProceedingJoinPoint joinPoint, HashMap<String, String> map) throws Throwable {
        JsonResult result=null;
        Long customerId=Long.valueOf(map.get("customerId"));
        Integer day= klgBuyTransactionService.getBuyInterval(customerId);//预约购买间隔天数
        if(day==null){//为空 说明没预约过 不可升级操作
            return new JsonResult().setSuccess(false).setMsg("klg_qingxianyuyuemairu");//请先预约买入
        }
        RemoteKlgService klgService= SpringUtil.getBean("remoteKlgService");
        Integer limitday= Integer.valueOf((String)klgService.getPlatformRule1sConfig("downgradeDays"));//间隔
        if(day>=limitday){
            return new JsonResult().setSuccess(false).setMsg("klg_qingxianyuyuemairu");//请先预约买入
        }
        result=(JsonResult)joinPoint.proceed();
        return  result;
    }
   /* //返回通知，在方法正常结束之后执行的代码
    //返回通知是可以访问到方法的返回值的
    @AfterReturning(value = "execution(* hry.klg.remote.RemoteKlgServiceImpl.appointmentPurchase(..))||" +
            "execution(* hry.klg.remote.RemoteKlgServiceImpl.appointmentSell(..))||" +
            "execution(* hry.klg.remote.RemoteKlgServiceImpl.transferAccounts(..))",returning = "result")
    public void sendMessage(JoinPoint joinPoint, Object result){
        String methodName = joinPoint.getSignature().getName();
        JsonResult jsonResult= (JsonResult)result;
        if(jsonResult.getSuccess()){
            Object [] objects=joinPoint.getArgs();
            if(objects!=null&&objects.length>0){
                HashMap<String, String> hashMap=(HashMap<String, String>) objects[0];
                String customerId=hashMap.get("customerId");//用户Id
                String number= hashMap.get("number");//金额
                //TODO 发送短信提醒   //TODO 发送消息提醒
                RemoteMessage message=new RemoteMessage();
                message.setMsgKey(methodName);//消息类型 暂定方法名称
                message.setMsgType("1,2");// 1.站内信，2.短信,
                message.setUserId(customerId);
                messageProducer.toMessageWarn(JSON.toJSONString(message));//消息提醒

            }
        }
    }*/
}
