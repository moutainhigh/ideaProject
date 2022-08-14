/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2020-06-05 16:37:53 
 */
package hry.admin.licqb.ecology.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import hry.admin.licqb.ecology.dao.EcologEnterDao;
import hry.admin.licqb.ecology.model.EcologEnter;
import hry.admin.licqb.ecology.model.EcologPay;
import hry.admin.licqb.ecology.service.EcologEnterService;
import hry.admin.licqb.ecology.service.EcologPayService;
import hry.admin.licqb.platform.RulesConfig;
import hry.admin.mq.producer.service.MessageProducer;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.message.model.MessageType;
import hry.message.model.RemoteMessage;
import hry.util.PageFactory;
import hry.util.QueryFilter;
import hry.util.RedisStaticUtil;
import hry.util.date.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p> EcologEnterService </p>
 * @author:         zhouming
 * @Date :          2020-06-05 16:37:53  
 */
@Service("ecologEnterService")
public class EcologEnterServiceImpl extends BaseServiceImpl<EcologEnter, Long> implements EcologEnterService{

	@Autowired
	private EcologPayService ecologPayService;
	@Resource
	private MessageProducer messageProducer;

	@Resource(name="ecologEnterDao")
	@Override
	public void setDao(BaseDao<EcologEnter, Long> dao) {
		super.dao = dao;
	}

	@Override
	public PageResult findPageEcologEnterList(QueryFilter filter) {

		Page<EcologEnter> page = PageFactory.getPage(filter);
		//参数集合
		Map<String,Object> map = new HashMap<>();
		String orderNum = filter.getRequest().getParameter("orderNum");
		String email = filter.getRequest().getParameter("email");
		String mobilePhone = filter.getRequest().getParameter("mobilePhone");
		String startTime = filter.getRequest().getParameter("startTime");
		String endTime = filter.getRequest().getParameter("endTime");
		String status = filter.getRequest().getParameter("status");
		String enterStatus = filter.getRequest().getParameter("enterStatus");
		if(!StringUtils.isEmpty(orderNum)){
			map.put("orderNum", "%"+orderNum+"%");
		}
		if(!StringUtils.isEmpty(email)){
			map.put("email", "%"+email+"%");
		}
		if(!StringUtils.isEmpty(mobilePhone)){
			map.put("mobilePhone", "%"+mobilePhone+"%");
		}
		if(!StringUtils.isEmpty(startTime)){
			map.put("startTime", startTime);
		}
		if(!StringUtils.isEmpty(endTime)){
			map.put("endTime", endTime);
		}
		if(!StringUtils.isEmpty(status)){
			if ("1".equals(status)) {
				map.put("status", 1);
			} else if ("5".equals(status)) {
				map.put("status", 5);
			} else {
				if (!StringUtils.isEmpty(enterStatus)) {
					if ("5".equals(enterStatus)) {
						map.put("status", 5);
					} else {
						map.put("enterStatus", enterStatus);
					}
				}
			}
		}
		((EcologEnterDao)dao).findEcologEnterList(map);
		return new PageResult(page, filter.getPage(),filter.getPageSize());
	}

	@Override
	public EcologEnter getEcologEnter(Long id) {
		Map<String,Object> map = new HashMap<>();
		map.put("id", id);
		List<EcologEnter> list = ((EcologEnterDao)dao).findEcologEnterList(map);
		EcologEnter ecologEnter = list != null && list.size() > 0 ? list.get(0) : null;
		// 正常支付 和 续费支付
		if (ecologEnter != null && (ecologEnter.getEnterStatus() == 5 || ecologEnter.getRenewStatus() == 1) ) {
			// 填写 收付款地址
			QueryFilter filter = new QueryFilter(EcologPay.class);
			filter.addFilter("enterId=", ecologEnter.getId());
			filter.setOrderby("id desc");
			EcologPay ecologPay = ecologPayService.get(filter);
			ecologEnter.setAcceptAddress(ecologPay.getAcceptAddress());
			ecologEnter.setPaymentAddress(ecologPay.getPaymentAddress());
		}
		return ecologEnter;
	}

	@Override
	public JsonResult updateEcologEnter(EcologEnter ecologEnter) {
		String key = null; // 站内信key
		int residueValidityDay = 0; // 上笔剩余天数
		// 校验当前订单是否到期
		Date expireDate = ecologEnter.getExpireDate(); //到期时间
		if (expireDate != null && expireDate.getTime() >= new Date().getTime()) {
			residueValidityDay = DateUtil.getDaysBetweenDate(new Date(), expireDate);
		}
		// 后台支付核实操作 EnterStatus： 7 核实拒绝 6 核实通过
		if (ecologEnter.getEnterStatus() == 6 || ecologEnter.getEnterStatus() == 7) {
			QueryFilter filter = new QueryFilter(EcologPay.class);
			filter.addFilter("enterId=", ecologEnter.getId());
			filter.addFilter("status=", 1);
			EcologPay pay = ecologPayService.get(filter);
			if (pay != null) {
				if (ecologEnter.getEnterStatus() == 7) {
					// 判断该订单是否是续费订单 1 为续费订单 0 未续费订单
					if (ecologEnter.getRenewStatus() == 1) {
						// 如果是续费订单 则订单状态不变
						ecologEnter.setEnterStatus(6);
					}
					pay.setStatus(3);
					pay.setRemark("核实未通过");
				} else {
					// 计算核实通过后 对应相关信息
					// 有效期时间（天）
					String validityDate = RedisStaticUtil.getAppConfigValue(RulesConfig.EcologenterKey, "validityDate");
					Date verifyDate = new Date(); // 核实时间
					pay.setVerifyDate(verifyDate);
					pay.setBaseValidityDay(Integer.parseInt(validityDate)); // 当前规则中有效期天数
					pay.setResidueValidityDay(residueValidityDay);
					// 实际天数
					int newValidityDay = Integer.parseInt(validityDate) + residueValidityDay;
					// 实际到期时间
					Date newExpireDate = DateUtil.addDay(verifyDate, newValidityDay);
					pay.setValidityDay(newValidityDay);
					pay.setStatus(4);
					pay.setRemark("核实已通过");
					ecologEnter.setValidityDay(newValidityDay);
					ecologEnter.setVerifyDate(verifyDate);
					ecologEnter.setExpireDate(newExpireDate);
				}
				// 不管是否核实通过，都将续费改为未续费状态
				ecologEnter.setRenewStatus(0);
				ecologPayService.update(pay);
				// 设置对应支付核实对应key Status： 4 入驻支付核实通过 3 入驻支付核实拒绝
				if (pay.getStatus() == 4) {
					key = MessageType.MESSAGE_LC_ECOENTER_PAY_SUCCESS.getKey();
				} else if (pay.getStatus()  == 3) {
					key = MessageType.MESSAGE_LC_ECOENTER_PAY_REFUSE.getKey();
				}
			}
		}
		// 修改状态
		super.update(ecologEnter);

        // 发送站内信
		// EnterStatus： 3 入驻申请通过  2 入驻申请拒绝
		if (ecologEnter.getEnterStatus() == 3) {
			key = MessageType.MESSAGE_LC_ECOFUND_SUCCESS.getKey();
		} else if (ecologEnter.getEnterStatus() == 2) {
			key = MessageType.MESSAGE_LC_ECOFUND_REFUSE.getKey();
		}
		if (key != null) {
			Map<String, String> paramM = new HashMap<>();
			paramM.put("${orderNum}", ecologEnter.getOrderNum());
			RemoteMessage message=new RemoteMessage();
			message.setParam(paramM);
			message.setMsgKey(key);//消息类型 模板KEY
			message.setMsgType("1");// 1.站内信，2.短信,
			message.setUserId(ecologEnter.getCustomerId().toString());
			messageProducer.toMessageWarn(JSON.toJSONString(message));//消息提醒
		}
		return new JsonResult(true).setMsg("操作成功");
	}

	@Override
	public int countEnter(Map<String, Object> map) {
		return ((EcologEnterDao)dao).countEnter(map);
	}
}
