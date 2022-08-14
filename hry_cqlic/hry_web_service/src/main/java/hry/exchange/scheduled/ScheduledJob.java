package hry.exchange.scheduled;


import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
public class ScheduledJob {

    static {
        System.out.println("*************************测试定时器器********************");
        System.out.println("*************************激光激光激光********************");
        System.out.println("*************************中国中国中国********************");
    }

    /**
     * 测试定时器
     */
    @Scheduled(cron = "0 0/1 * * * ?")
    public void pushOrder(){
        System.out.println("*************************测试定时器器********************");
        System.out.println("*************************激光激光激光********************");
        System.out.println("*************************中国中国中国********************");
        //KlineEngine.generateKlineData(1);
    }


}
