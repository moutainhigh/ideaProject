/**
 * Copyright:   
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-04-18 14:48:45 
 */
package hry.admin.klg.limit.service.impl;

import com.alibaba.fastjson.JSON;
import hry.admin.klg.limit.model.KlgAmountLimitation;
import hry.admin.klg.limit.model.KlgAmountLimitationRecord;
import hry.admin.klg.limit.service.KlgAmountLimitationRecordService;
import hry.bean.BaseManageUser;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> KlgAmountLimitationRecordService </p>
 * @author:         lzy
 * @Date :          2019-04-18 14:48:45  
 */
@Service("klgAmountLimitationRecordService")
public class KlgAmountLimitationRecordServiceImpl extends BaseServiceImpl<KlgAmountLimitationRecord, Long> implements KlgAmountLimitationRecordService {
	
	@Resource(name="klgAmountLimitationRecordDao")
	@Override
	public void setDao(BaseDao<KlgAmountLimitationRecord, Long> dao) {
		super.dao = dao;
	}
	/**
	 * 添加操作日志
	 * @param
	 */
	@Override
	public void saveLimitationRecord(BaseManageUser user, KlgAmountLimitation amountLimitation) {
		KlgAmountLimitationRecord limitationRecord=new KlgAmountLimitationRecord();
		Long operatorId=0l;
		String operatorName="系统";
		if(user!=null){
			operatorId=Long.valueOf(user.getId().toString());
			operatorName=user.getUsername();
		}
		String remark= JSON.toJSONString(amountLimitation);
		limitationRecord.setRemark(remark);
		limitationRecord.setOperatorId(operatorId);
		limitationRecord.setOperatorName(operatorName);
		limitationRecord.setMoney(amountLimitation.getMoney());
		limitationRecord.setType(amountLimitation.getType());
		limitationRecord.setState(amountLimitation.getState());
		super.save(limitationRecord);
	}
}
