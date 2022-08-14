package hry.thread;

import hry.bean.JsonResult;
import hry.mall.integral.service.IntegralDetailService;
import hry.util.SpringUtil;
import org.apache.log4j.Logger;

/**
 * 登录后更新用户账户id保存到redis中
 *
 * @author CHINA_LSL
 */
public class IntegralRunnable implements Runnable {

    private final Logger log = Logger.getLogger(IntegralRunnable.class);
    private long orderId;
    private long memberId;
    private String accountKey;
    private String taskKey;
    private int rewardType;

    public IntegralRunnable(long orderId,long memberId,String accountKey,String taskKey,Integer rewardType) {
        // TODO Auto-generated constructor stub
        this.orderId = orderId;
        this.memberId = memberId;
        this.accountKey = accountKey;
        this.taskKey = taskKey;
        this.rewardType = rewardType;
    }

    @Override
    public void run () {
        try {
            Thread.sleep(1000);
            System.out.println("账号memberId="+memberId+"++++++++++++++++++++++进入发放积分线程");
            IntegralDetailService integralDetailService = SpringUtil.getBean("integralDetailService");
          //  JsonResult jsonResult = integralDetailService.addIntegralDetail(orderId, memberId, accountKey, taskKey, rewardType);
            JsonResult jsonResult = integralDetailService.addIntegralDetail1(orderId, memberId, accountKey, taskKey, rewardType);//改为新方法
            if(jsonResult.getSuccess()){
                System.out.println("账号memberId="+memberId+"+++++++发放积分成功");
            }else {
                System.out.println("账号memberId="+memberId+"-------发放积分失败");
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
