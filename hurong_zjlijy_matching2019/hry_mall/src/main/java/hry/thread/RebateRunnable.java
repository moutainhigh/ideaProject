package hry.thread;

import hry.bean.JsonResult;
import hry.mall.integral.service.IntegralDetailService;
import hry.mall.rebate.service.RebateDetailService;
import hry.util.SpringUtil;
import org.apache.log4j.Logger;

/**
 * @author luyue
 * @date 2019/11/28 17:58
 */
public class RebateRunnable  implements Runnable{
    private final Logger log = Logger.getLogger(Runnable.class);
    private long orderId;
    public RebateRunnable(long orderId){
      this.orderId=orderId;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            System.out.println("订单orderId="+orderId+"++++++++++++++++++++++进入发放返佣线程");
            RebateDetailService rebateDetailService=SpringUtil.getBean("rebateDetailService");
            JsonResult jsonResult = rebateDetailService.addDetail(orderId);
            if(jsonResult.getSuccess()){
                System.out.println("订单orderId="+orderId+"+++++++发放返佣成功");
            }else {
                System.out.println("订单orderId="+orderId+"-------发放返佣失败");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
