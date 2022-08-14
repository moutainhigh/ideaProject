package hry.core.mq.service;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MessageProducer {
	private Logger logger = Logger.getLogger(MessageProducer.class);

    @Resource(name="amqpTemplate")  
    private AmqpTemplate amqpTemplate;  


    
    public void sendChongbiCallBack(Object message)  {  
    	try {  
    		logger.info("to send message:{}"+message);  
    		amqpTemplate.convertAndSend("chongbiCallBackKey", message);  
			
		} catch (Exception e) {
			e.printStackTrace();
		}
     
    } 
    
}
