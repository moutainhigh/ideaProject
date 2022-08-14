package hry.cm.remote;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSON;

import hry.cm.customer.model.CmCustomer;
import hry.cm.customer.service.CmCustomerService;
import hry.cm.customerminer.model.CmCustomerMiner;
import hry.cm.customerminer.service.CmCustomerMinerService;
import hry.cm.dividend.service.CmDividendRecordService;
import hry.cm.log.service.CmExceptionLogService;
import hry.cm.log.service.CmTaskLogService;
import hry.cm.order.service.CmOrderService;
import hry.cm.profittwo.service.CmProfitTwoService;
import hry.mq.producer.service.MessageProducer;
import hry.redis.common.utils.RedisService;
import hry.util.QueryFilter;
import hry.util.SpringUtil;

public class RemoteCmListenerServiceImpl implements RemoteCmListenerService {

	@Resource
	private CmCustomerMinerService cmCustomerMinerService;
	@Resource
	private MessageProducer messageProducer;
	@Resource
	private CmOrderService cmOrderService;
	@Resource
	private RedisService redisService;
	@Resource
	private CmExceptionLogService cmExceptionLogService;
	@Resource
	private CmDividendRecordService cmDividendRecordService;
	@Resource
	private CmCustomerService cmCustomerService;
	@Resource
	private CmProfitTwoService cmProfitTwoService;


	@Override
	public void minerCoinageByDay() {
		// TODO Auto-generated method stub
		Date startDate = new Date();
		CmTaskLogService cmTaskLogService = SpringUtil.getBean("cmTaskLogService");
		/** 查询所有正在运行的矿机 */
		QueryFilter filterCmCustomerMiner = new QueryFilter(CmCustomerMiner.class);
		filterCmCustomerMiner.addFilter("status=", 2);// 运行中的矿机
		filterCmCustomerMiner.addFilter("generateCycle=", 1);// 按天产币
		List<CmCustomerMiner> list = cmCustomerMinerService.find(filterCmCustomerMiner);
		if (list != null && list.size() > 0) {
			for (CmCustomerMiner cmCustomerMiner : list) {
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
		CmTaskLogService cmTaskLogService = SpringUtil.getBean("cmTaskLogService");
		
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
			//遍历集合，发送更新用户等级消息
			/**
			 * 此处产生的mq数据量较大 ，所有换种写发。改为更新所有用户等级，按照用户注册顺序倒序
			 * @auther: yaozh
			 * @date: 2019/10/17 11:13
			 */
			/*if(listAll!=null&&listAll.size()>0){
				for(Long customerId:listAll){
					//messageProducer.toCmUpdateGrade(customerId.toString());
					messageProducer.toCmBuyAndcloseMinerUpdateGrade(customerId.toString());
				}
			}*/

			//1.所有用户倒序排列
			QueryFilter filterCmCustomer = new QueryFilter(CmCustomer.class);
			filterCmCustomer.setOrderby("created desc");
			List<CmCustomer> listCustomer = cmCustomerService.find(filterCmCustomer);
			if(listCustomer!=null&&listCustomer.size()>0){
				for(CmCustomer cmCustomer:listCustomer){
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
		CmTaskLogService cmTaskLogService = SpringUtil.getBean("cmTaskLogService");
		Date startDate = new Date();
		cmDividendRecordService.giveOutDividend();
		Date endDate = new Date();
		String functionName = "minercampsByDay";
		cmTaskLogService.saveLog(functionName, 0, startDate, endDate);
	}

	@Override
	public void profitone() {
		// TODO Auto-generated method stub
		CmTaskLogService cmTaskLogService = SpringUtil.getBean("cmTaskLogService");
		Date startDate = new Date();
		
		/**查询所有用户  ，注册时间倒序排序*/
		QueryFilter filterCmCustomer = new QueryFilter(CmCustomer.class);
		filterCmCustomer.setOrderby("created desc");
		List<CmCustomer> listCustomer = cmCustomerService.find(filterCmCustomer);
		if(listCustomer!=null&&listCustomer.size()>0){
			for(CmCustomer cmCustomer:listCustomer){
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
		CmTaskLogService cmTaskLogService = SpringUtil.getBean("cmTaskLogService");
		Date startDate = new Date();
		
		cmProfitTwoService.grantProfit();
		
		Date endDate = new Date();
		String functionName = "grantProfit";
		cmTaskLogService.saveLog(functionName, 0, startDate, endDate);
	}
}
