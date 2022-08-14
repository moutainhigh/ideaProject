package hry.mq.consumer;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class MessageConsumer implements MessageListener {
   private Logger logger = Logger.getLogger(MessageConsumer.class);  

   @Override
   public void onMessage(Message message) {
        logger.info("consumer receive message------->"+ new String(message.getBody())); 
        	int i = 10/0;
			
//        messageProducer.toTrade(message);
//        messageProducer.toAccount(message);
   }

}
