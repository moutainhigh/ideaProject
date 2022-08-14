package hry.cm2.remote;

import com.alibaba.fastjson.JSON;
import hry.cm2.customer.model.Cm2Customer;
import hry.cm2.customer.service.Cm2CustomerService;
import hry.cm2.customerminer.model.Cm2CustomerMiner;
import hry.cm2.customerminer.service.Cm2CustomerMinerService;
import hry.cm2.dividend.service.Cm2DividendRecordService;
import hry.cm2.log.service.Cm2ExceptionLogService;
import hry.cm2.log.service.Cm2TaskLogService;
import hry.cm2.order.service.Cm2OrderService;
import hry.cm2.profittwo.service.Cm2ProfitTwoService;
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
	private Cm2CustomerMinerService cmCustomerMinerService;
	@Resource
	private MessageProducer messageProducer;
	@Resource
	private Cm2OrderService cmOrderService;
	@Resource
	private RedisService redisService;
	@Resource
	private Cm2ExceptionLogService cmExceptionLogService;
	@Resource
	private Cm2DividendRecordService cmDividendRecordService;
	@Resource
	private Cm2CustomerService cmCustomerService;
	@Resource
	private Cm2ProfitTwoService cmProfitTwoService;


	@Override
	public void minerCoinageByDay() {
		// TODO Auto-generated method stub
		Date startDate = new Date();
		Cm2TaskLogService cmTaskLogService = SpringUtil.getBean("cm2TaskLogService");
		/** 查询所有正在运行的矿机 */
		QueryFilter filterCmCustomerMiner = new QueryFilter(Cm2CustomerMiner.class);
		filterCmCustomerMiner.addFilter("status=", 2);// 运行中的矿机
		filterCmCustomerMiner.addFilter("generateCycle=", 1);// 按天产币
		List<Cm2CustomerMiner> list = cmCustomerMinerService.find(filterCmCustomerMiner);
		if (list != null && list.size() > 0) {
			for (Cm2CustomerMiner cmCustomerMiner : list) {
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
		Cm2TaskLogService cmTaskLogService = SpringUtil.getBean("cm2TaskLogService");
		
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
			QueryFilter filterCmCustomer = new QueryFilter(Cm2Customer.class);
			filterCmCustomer.setOrderby("created desc");
			List<Cm2Customer> listCustomer = cmCustomerService.find(filterCmCustomer);
			if(listCustomer!=null&&listCustomer.size()>0){
				for(Cm2Customer cmCustomer:listCustomer){
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
	public void minercampsByDay() {
		Cm2TaskLogService cmTaskLogService = SpringUtil.getBean("cm2TaskLogService");
		Date startDate = new Date();
		cmDividendRecordService.giveOutDividend();
		Date endDate = new Date();
		String functionName = "minercampsByDay";
		cmTaskLogService.saveLog(functionName, 0, startDate, endDate);
	}

	@Override
	public void profitone() {
		// TODO Auto-generated method stub
		Cm2TaskLogService cmTaskLogService = SpringUtil.getBean("cm2TaskLogService");
		Date startDate = new Date();
		
		/**查询所有用户  ，注册时间倒序排序*/
		QueryFilter filterCmCustomer = new QueryFilter(Cm2Customer.class);
		filterCmCustomer.setOrderby("created desc");
		List<Cm2Customer> listCustomer = cmCustomerService.find(filterCmCustomer);
		if(listCustomer!=null&&listCustomer.size()>0){
			for(Cm2Customer cmCustomer:listCustomer){
				/** 发送mq消息，发放矿工动态收益 */
				messageProducer.toCmProfit1Key(JSON.toJSONString(cmCustomer));
			}
		}
		
		Date endDate = new Date();
		String functionName = "profitone";
		cmTaskLogService.saveLog(functionName, 0, startDate, endDate);
	}

	@Override
	public void grantProfit() {
		// TODO Auto-generated method stub
		Cm2TaskLogService cmTaskLogService = SpringUtil.getBean("cm2TaskLogService");
		Date startDate = new Date();
		
		cmProfitTwoService.grantProfit();
		
		Date endDate = new Date();
		String functionName = "grantProfit";
		cmTaskLogService.saveLog(functionName, 0, startDate, endDate);
	}
}
