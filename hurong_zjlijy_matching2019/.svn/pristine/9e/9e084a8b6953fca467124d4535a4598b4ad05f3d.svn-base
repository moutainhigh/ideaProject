package hry.trade.mq;

import hry.minigrobot.robot.MiningRobotJob;
import hry.trade.robot.service.RobotDepthService;
import hry.util.sys.ContextUtil;
import org.apache.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;


public class MessageMimingCustomerRobo implements MessageListener {
	private Logger logger = Logger.getLogger(MessageMimingCustomerRobo.class);

	@Override
	public void onMessage(Message message) {
        MiningRobotJob miningRobotJob = (MiningRobotJob) ContextUtil.getBean("miningRobotJob");
		try {
            miningRobotJob.executeRobot();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
