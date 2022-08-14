/**
 * Copyright:   
 * @author:      javalx
 * @version:     V1.0 
 * @Date:        2019-06-11 18:29:25 
 */
package hry.social.mill.service.impl;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;

import hry.manage.remote.model.RemoteResult;
import hry.social.manage.remote.model.mill.SocialMillInfo;
import hry.social.mill.dao.SocialMillTradeRecordDao;
import hry.social.mill.service.SocialMillInfoService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * <p> SocialMillInfoService </p>
 * @author:         javalx
 * @Date :          2019-06-11 18:29:25  
 */
@Service("socialMillInfoService")
public class SocialMillInfoServiceImpl extends BaseServiceImpl<SocialMillInfo, Long> implements SocialMillInfoService {
	
	@Resource(name="socialMillInfoDao")
	@Override
	public void setDao(BaseDao<SocialMillInfo, Long> dao) {
		super.dao = dao;
	}

	@Resource
	SocialMillTradeRecordDao socialMillTradeRecordDao;

	/**
	 * 查看用户矿机购买信息
	 *
	 * @param customerId
	 * @return
	 */
	@Override
	public RemoteResult getMillInfo(Long customerId) {
		RemoteResult remoteResult = new RemoteResult();
		try {
			int forceMill = socialMillTradeRecordDao.hasForceMill(customerId);
			int coinMill = socialMillTradeRecordDao.hasCoinMill(customerId);
			Map<String,Object> map = new HashMap<>();
			map.put("forceMill", forceMill);
			map.put("coinMill", coinMill);
			remoteResult.setSuccess(true).setObj(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return remoteResult;
	}

	/**
	 * 查看用户购买数币矿机总奖励量
	 *
	 * @param customerId
	 * @return
	 */
	@Override
	public BigDecimal findMillCoin(Long customerId) {
		return socialMillTradeRecordDao.findMillCoin(customerId);
	}
}
