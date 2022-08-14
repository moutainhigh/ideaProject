package hry.cm5.remote;

import hry.cm5.log.service.Cm5TaskLogService;
import hry.cm5.miner.model.Cm5MinerOrder;
import hry.cm5.miner.service.Cm5AwardRecordService;
import hry.cm5.miner.service.Cm5MinerOrderService;
import hry.mq.producer.service.MessageProducer;
import hry.redis.common.utils.RedisService;
import hry.util.QueryFilter;
import hry.util.SpringUtil;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

public class RemoteCmListenerServiceImpl implements RemoteCmListenerService {

	@Resource
	private MessageProducer messageProducer;

	@Resource
	private RedisService redisService;

	@Resource
	private Cm5MinerOrderService cm5MinerOrderService;

	@Resource
	private Cm5AwardRecordService cm5AwardRecordService;

	@Override
	public void minerCoinageByDay() {
		// TODO Auto-generated method stub
		Date startDate = new Date();
		Cm5TaskLogService cmTaskLogService = SpringUtil.getBean("cm5TaskLogService");
		// 查询正在运行的矿机订单
		QueryFilter filter = new QueryFilter(Cm5MinerOrder.class);
		filter.addFilter("status=", 1);
		filter.setOrderby("customerId desc");
		List<Cm5MinerOrder> minerOrderList = cm5MinerOrderService.find(filter);
		for (Cm5MinerOrder minerOrder : minerOrderList) {
			cm5AwardRecordService.giveOutAward(minerOrder);
		}
		Date endDate = new Date();
		String functionName = "minerCoinageByDay";
		cmTaskLogService.saveLog(functionName, 0, startDate, endDate);
	}

}
