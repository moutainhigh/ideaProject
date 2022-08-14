/**
 * Copyright:   
 * @author:      luyue
 * @version:     V1.0 
 * @Date:        2018-12-21 15:28:20 
 */
package hry.mall.platform.service.impl;

import java.util.Date;
import javax.annotation.Resource;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.mall.order.model.Order;
import hry.mall.platform.model.ThirdpayRecord;
import hry.mall.platform.service.ThirdpayRecordService;
import hry.mall.retailstore.model.GroupDetail;
import hry.util.SNUtil;
import org.springframework.stereotype.Service;

/**
 * <p> ThirdpayRecordService </p>
 * @author:         luyue
 * @Date :          2018-12-21 15:28:20  
 */
@Service("thirdpayRecordService")
public class ThirdpayRecordServiceImpl extends BaseServiceImpl<ThirdpayRecord, Long> implements ThirdpayRecordService {
	
	@Resource(name="thirdpayRecordDao")
	@Override
	public void setDao(BaseDao<ThirdpayRecord, Long> dao) {
		super.dao = dao;
	}

	@Override
	public ThirdpayRecord saveRecord(Order order) {
		// TODO Auto-generated method stub
		ThirdpayRecord record=new ThirdpayRecord();
		record.setMemberId(order.getMemberId());
		record.setLoginName(order.getMemberName());
		record.setThirdPayConfig("daiding");
		record.setThirdPayFlagId("hry001");
		record.setInterfaceType("001");
		record.setInterfaceName("购物支付");
		record.setDealMoney(order.getRmbMoney().add(order.getRmbFeeMoney()));
		record.setRequestTime(new Date());
		record.setRequestNum(Integer.valueOf("1"));
		record.setReturnNum(Integer.valueOf("0"));
		record.setStatus(Integer.valueOf("2"));
		record.setCode("PAY_SUCCESS");
		record.setOrderId(order.getId());
		record.setPhone(order.getCellphone());
		record.setRecordNumber(SNUtil.create15());
		this.save(record);
		return record;
	}

	@Override
	public ThirdpayRecord saveRecord(GroupDetail detail) {
		// TODO Auto-generated method stub
		ThirdpayRecord record=new ThirdpayRecord();
		record.setMemberId(detail.getMemberId());
		record.setLoginName(detail.getMemberName());
		record.setThirdPayConfig("daiding");
		record.setThirdPayFlagId("hry001");
		record.setInterfaceType("002");
		record.setInterfaceName("团购支付");
		record.setDealMoney(detail.getRmbMoney().add(detail.getRmbFeeMoney()));
		record.setRequestTime(new Date());
		record.setRequestNum(Integer.valueOf("1"));
		record.setReturnNum(Integer.valueOf("0"));
		record.setStatus(Integer.valueOf("2"));
		record.setCode("PAY_SUCCESS");
		record.setOrderId(detail.getId());
		record.setPhone(detail.getCellphone());
		record.setRecordNumber(SNUtil.create15());
		this.save(record);
		return record;
	}
	

}
