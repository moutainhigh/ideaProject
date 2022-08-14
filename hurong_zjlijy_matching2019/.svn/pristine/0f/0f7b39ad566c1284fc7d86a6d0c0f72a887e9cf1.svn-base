package hry.app.core.aop;

import com.alibaba.fastjson.JSONObject;
import hry.app.jwt.TokenUtil;
import hry.bean.ApiJsonResult;
import hry.manage.remote.model.User;
import hry.util.IpUtil;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.nutz.json.Json;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Aspect
@Component
public class OtcLogAop {

    Logger logger = Logger.getLogger(OtcLogAop.class);

    @Resource
    private JdbcTemplate jdbcTemplate;

    private static ExecutorService executors = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    @Pointcut("@annotation(hry.app.core.annotation.LogAop)")
    public void log () {

    }

    @AfterReturning(pointcut = "log()", returning = "rvt")
    public void doAfterReturning (final JoinPoint joinPoint, final Object rvt) throws NoSuchMethodException, SecurityException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        User user = TokenUtil.getUser(request);
        final String ip = IpUtil.getIp(request);
        final String requestHeader = request.getHeader("user-agent");

        Runnable r = new Runnable() {
            @Override
            public void run () {
                try {
                    ApiJsonResult jsonResult = (ApiJsonResult) rvt;
                    if (jsonResult.getSuccess()) {
                        // 处理完请求，返回内容
                        logger.info("-------------进入AOP切面,开始处理数据------------");

                        logger.info("AfterReturning增强：获织入增强的目标方法为：" + joinPoint.getSignature().getName());
                        logger.info("AfterReturning增强：目标方法的参数为：" + Arrays.toString(joinPoint.getArgs()));
                        logger.info("AfterReturning增强：被织入增强处理的目标对象为：" + joinPoint.getTarget());

                        String methodName = joinPoint.getSignature().getName();

                        boolean flag = IpUtil.isMobileDevice(requestHeader);
                        String type = "";
                        if (flag) {
                            type = "phone";
                        } else {
                            type = "pc";
                        }
                        String[] result = getMethod(methodName, joinPoint,jsonResult);

                        jdbcTemplate.update("insert into otc_log(transactionId, type,content, created,modified, saasId) values ("
                                + "\"" + result[2] + "\","
                                + "\"" + result[0] + "\","
                                + "\"" + result[1] + "\","
                                + "\"" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "\","
                                + "\"" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) +"\",\"1\")");

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        executors.execute(r);
    }

    private String[] getMethod(String methodName,JoinPoint joinPoint,ApiJsonResult jsonResult){
        String [] result = new String[3];
        String transactionId = "";
        String content = "";
        Object[] args1 = joinPoint.getArgs();
        String type = "";
        switch (methodName) {
            case "tradedetail":
                String transactionMode = args1[6].toString();
                type = transactionMode;
                if("1".equals(transactionMode)){
                    content = "买家摘单";
                }else{
                    content = "卖家摘单";
                }
                String s = Json.toJson(jsonResult.getObj());
                JSONObject object = JSONObject.parseObject(s);
                String tradeNum = object.get("tradeNum").toString();
                transactionId = getTransactionId(tradeNum);
                break;
            case "orderPayment":
                type = "3";
                content = "买家确认付款";
                transactionId = getTransactionId(args1[1].toString());
                break;
            case "orderCompleted":
                type = "4";
                content = "卖家确认收款";
                transactionId = getTransactionId(args1[1].toString());
                break;
            case "cancleOrder"://撤销订单
                type = "5";
                content = "买家撤销订单";
                transactionId = getTransactionId(args1[1].toString());
                break;
            case "addAppeal"://申诉
                String transactionMode2 = args1[1].toString();
                transactionId = getTransactionId(args1[2].toString());
                if("1".equals(transactionMode2)){
                    type = "6";
                    content = "买家申诉";
                }else{
                    type = "7";
                    content = "卖家申诉";
                }
                break;
            default:
                content = "";
        }
        result[0] = type;
        result[1] = content;
        result[2] = transactionId;
        return result;
    }

    private String getTransactionId(String tradeNum){
        Map<String,Object> otc = jdbcTemplate.queryForMap("select * from otc_app_transaction where transactionNum = '"+tradeNum+"'");
        return otc.get("id").toString();
    }

}
