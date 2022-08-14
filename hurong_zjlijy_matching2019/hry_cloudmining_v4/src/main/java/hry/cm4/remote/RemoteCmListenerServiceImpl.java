package hry.cm4.remote;

import com.alibaba.fastjson.JSON;
import hry.cm4.customer.model.Cm4Customer;
import hry.cm4.customer.service.Cm4CustomerService;
import hry.cm4.customerminer.model.Cm4CustomerMiner;
import hry.cm4.customerminer.service.Cm4CustomerMinerService;
import hry.cm4.log.service.Cm4ExceptionLogService;
import hry.cm4.log.service.Cm4TaskLogService;
import hry.cm4.order.service.Cm4OrderService;
import hry.mq.producer.service.MessageProducer;
import hry.redis.common.utils.RedisService;
import hry.util.QueryFilter;
import hry.util.SpringUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RemoteCmListenerServiceImpl implements RemoteCmListenerService {

	@Resource
	private Cm4CustomerMinerService cmCustomerMinerService;
	@Resource
	private MessageProducer messageProducer;
	@Resource
	private Cm4OrderService cmOrderService;
	@Resource
	private RedisService redisService;
	@Resource
	private Cm4ExceptionLogService cmExceptionLogService;
	@Resource
	private Cm4CustomerService cmCustomerService;


	@Override
	public void minerCoinageByDay() {
		// TODO Auto-generated method stub
		Date startDate = new Date();
		Cm4TaskLogService cmTaskLogService = SpringUtil.getBean("cm4TaskLogService");
		/** 查询所有正在运行的矿机 */
		QueryFilter filterCmCustomerMiner = new QueryFilter(Cm4CustomerMiner.class);
		filterCmCustomerMiner.addFilter("status=", 2);// 运行中的矿机
		List<Cm4CustomerMiner> list = cmCustomerMinerService.find(filterCmCustomerMiner);
		if (list != null && list.size() > 0) {
			for (Cm4CustomerMiner cmCustomerMiner : list) {
				/** 发送mq消息到service中处理 */
				messageProducer.toMinerCoinageByDay(JSON.toJSONString(cmCustomerMiner));
			}
		}
		Date endDate = new Date();
		String functionName = "minerCoinageByDay";
		cmTaskLogService.saveLog(functionName, 0, startDate, endDate);

	}

	@Override
	public void minerWaitAndCloseTiming() {
		// TODO Auto-generated method stub
		
		Boolean lock = redisService.lock("CM:MINERCLOSETIMING");
		if (!lock) {
			return;
		}
		Date startDate = new Date();
		Cm4TaskLogService cmTaskLogService = SpringUtil.getBean("cm4TaskLogService");
		
		try {
			//查询持有到期矿机的用户
			List<Long> closeCustomerIdList = new ArrayList<>();
			closeCustomerIdList = cmOrderService.findCloseMinerCustomerId();
			//查询持有待运行状态矿机的用户
			List<Long> waitCustomerIdList = new ArrayList<>();
			waitCustomerIdList = cmOrderService.findWaitMinerCustomerId();
			
			//更新所有到期订单和到期矿机
			cmOrderService.updateCloseOrder();
			cmCustomerMinerService.updateCloseMiner();
			
			//更新所有待运行矿机和订单为运行中
			cmOrderService.updateWaitOrder();
			cmCustomerMinerService.updateWaitMiner();
			
			//获取到期和待运行用户的并集
			closeCustomerIdList.removeAll(waitCustomerIdList);
			closeCustomerIdList.addAll(closeCustomerIdList);
			List<Long> listAll = closeCustomerIdList;
			//1.所有用户倒序排列
			QueryFilter filterCmCustomer = new QueryFilter(Cm4Customer.class);
			filterCmCustomer.setOrderby("created desc");
			List<Cm4Customer> listCustomer = cmCustomerService.find(filterCmCustomer);
			if(listCustomer!=null&&listCustomer.size()>0){
				for(Cm4Customer cmCustomer:listCustomer){
					/** 发送等级升级消息*/
					messageProducer.toCmUpdateGrade(cmCustomer.getCustomerId().toString());
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			redisService.unLock("CM:MINERCLOSETIMING");
			e.printStackTrace();
			cmExceptionLogService.insertlog(e, "RemoteCmListenerServiceImpl.minerCloseTiming",
					"");
		}
		
		redisService.unLock("CM:MINERCLOSETIMING");
		Date endDate = new Date();
		String functionName = "minerCloseTiming";
		cmTaskLogService.saveLog(functionName, 0, startDate, endDate);
	}

	@Override
	public void profitone() {
		// TODO Auto-generated method stub
		Cm4TaskLogService cmTaskLogService = SpringUtil.getBean("cm4TaskLogService");
		Date startDate = new Date();
		
		/**查询所有用户  ，注册时间倒序排序*/
		QueryFilter filterCmCustomer = new QueryFilter(Cm4Customer.class);
		filterCmCustomer.setOrderby("created desc");
		List<Cm4Customer> listCustomer = cmCustomerService.find(filterCmCustomer);
		if(listCustomer!=null&&listCustomer.size()>0){
			for(Cm4Customer cmCustomer:listCustomer){
				/** 发送mq消息，发放矿工动态收益 */
				messageProducer.toCmProfit1Key(JSON.toJSONString(cmCustomer));
			}
		}
		
		Date endDate = new Date();
		String functionName = "profitone";
		cmTaskLogService.saveLog(functionName, 0, startDate, endDate);
	}

}
