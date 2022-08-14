package hry.ico.aop;

import com.alibaba.fastjson.JSON;
import hry.bean.JsonResult;
import hry.bean.ObjectUtil;
import hry.customer.commend.service.AppCommendUserService;
import hry.ico.model.IcoBuyOrder;
import hry.ico.model.IcoLockError;
import hry.ico.model.util.RecommenderOrder;
import hry.ico.remote.RemoteIcoService;
import hry.ico.remote.model.IcoRulesConfig;
import hry.ico.remote.model.RulesConfig;
import hry.ico.service.IcoBuyOrderService;
import hry.ico.service.IcoLockErrorService;
import hry.message.model.MessageType;
import hry.message.model.RemoteMessage;
import hry.mq.producer.service.MessageProducer;
import hry.util.QueryFilter;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
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
public class RemoteICOAccountAspect {
    private final Logger logger = Logger.getLogger(RemoteICOAccountAspect.class);
    @Resource
    private RemoteIcoService remoteIcoService;
    @Resource
    private IcoBuyOrderService icoBuyOrderService;
    @Resource
    private MessageProducer messageProducer;
    @Resource
    private IcoLockErrorService icoLockErrorService;

    private static  ExecutorService executor = Executors.newCachedThreadPool();

    //买入平台币
    @Around(value = "execution(* hry.ico.remote.RemoteIcoServiceImpl.purchasePlatformAccount(..))&&args(map)")
    public Object  purchase(ProceedingJoinPoint joinPoint, HashMap<String, String> map) throws Throwable {
        JsonResult result=null;
        JsonResult jsonResult= remoteIcoService.isCheckICOTime();//是否Ico设置
          if(jsonResult.getSuccess()){
              //查询我的上级是否购买过
              Long customerId=Long.valueOf(map.get("customerId"));
              BigDecimal number=new BigDecimal(map.get("number"));
              RecommenderOrder recommenderOrder= icoBuyOrderService.finSuperiorCountByCustomerId(customerId);
              recommenderOrder.setNumber(number);
                 result=(JsonResult)joinPoint.proceed();
                 if(result.getSuccess()){
                     RemoteMessage message=new RemoteMessage();
                     message.setMsgKey(MessageType.Message_Buy_Remind.getKey());//消息类型 模板KEY//
                     message.setMsgType("1,2");// 1.站内信，2.短信,
                     message.setUserId(customerId.toString());
                     messageProducer.toMessageWarn(JSON.toJSONString(message));//消息提醒

                     JsonResult ruleResult=remoteIcoService.getPlatformRule(RulesConfig.RulesCoinKey);//
                     if(ruleResult.getSuccess()){
                         logger.info("自动锁仓");
                         IcoRulesConfig rulesConfig= ObjectUtil.bean2bean(jsonResult.getObj(),IcoRulesConfig.class);
                         Integer lockDay=Integer.valueOf(rulesConfig.getIcoLockday());//锁仓天数
                         String coinCode=rulesConfig.getCoinCode();//平台币
                         Map<String,String> messageData=new HashMap();
                         messageData.put("customerId",customerId.toString());
                         messageData.put("lockDay",lockDay.toString());
                         messageData.put("number",number.toString());
                         messageData.put("coinCode",coinCode);
                         messageData.put("isIco","true");
                         messageData.put("orderById",result.getObj().toString());//购买记录Id
                         messageProducer.toLockStorage(JSON.toJSONString(messageData));
                         //上级必须有购买记录才会发奖励
                         if(recommenderOrder.getSuperiorCustomerId()!=null&&recommenderOrder.getSuperiorCustomerId()>0){
                                         messageProducer.toRecommendReward(JSON.toJSONString(recommenderOrder));
                         }
                     }else{
                         logger.error("获取redis  平台币信息错误");
                     }
                 }
            }else{
                return new JsonResult().setSuccess(false).setMsg("feiicobukegoumai");//非ICO阶段不可购买平台币
          }
          return  result;
    }
    //锁仓
    @Around(value = "execution(* hry.ico.remote.RemoteIcoServiceImpl.toLockDepot(..))&&args(map)")
    public Object toLockDepot(ProceedingJoinPoint pjp,HashMap<String, String> map) throws Throwable{
        JsonResult jsonResult= remoteIcoService.isCheckICOTime();//是否Ico设置
        boolean isIco=false;
        if(jsonResult.getSuccess()){
            isIco=true;
        }
        map.put("isIco",String.valueOf(isIco));
        return  pjp.proceed();
    }

}
