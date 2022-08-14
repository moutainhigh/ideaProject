/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2020-06-11 14:27:41 
 */
package hry.licqb.record.service.impl;

import hry.licqb.record.dao.UsdtTotalDao;
import hry.licqb.record.model.PlatformTotal;
import hry.licqb.record.model.UsdtTotal;
import hry.licqb.record.model.UserAccount;
import hry.licqb.record.service.UsdtTotalService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;

import hry.util.QueryFilter;
import hry.util.date.DateUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p> UsdtTotalService </p>
 * @author:         zhouming
 * @Date :          2020-06-11 14:27:41  
 */
@Service("usdtTotalService")
public class UsdtTotalServiceImpl extends BaseServiceImpl<UsdtTotal, Long> implements UsdtTotalService{
	
	@Resource(name="usdtTotalDao")
	@Override
	public void setDao(BaseDao<UsdtTotal, Long> dao) {
		super.dao = dao;
	}

	@Override
	public void payAndExtractTotal() {
		QueryFilter filter = new QueryFilter(UsdtTotal.class);
		filter.setOrderby("id DESC");
		UsdtTotal usdtTotal = super.get(filter);
		usdtTotal = usdtTotal == null ? new UsdtTotal() : usdtTotal;
		String today = DateUtil.getNewDate();
		UsdtTotal yesterdayTotal = ((UsdtTotalDao)dao).getYesterdayTotal();
		usdtTotal.setPayMoney(yesterdayTotal.getPayMoney());
		usdtTotal.setExtractMoney(yesterdayTotal.getExtractMoney());
		usdtTotal.setSurplusMoney(yesterdayTotal.getSurplusMoney());
		usdtTotal.setCoinCode("USDT");
		if (usdtTotal.getId() == null) {
			super.save(usdtTotal);
		} else {
			String created = DateUtil.dateToString(usdtTotal.getCreated());
			if (created.split(" ")[0].equals(today.split(" ")[0])) {
				super.update(usdtTotal);
			} else {
				usdtTotal.setId(null);
				super.save(usdtTotal);
			}
		}
	}

	@Override
	public List<UserAccount> coldBusinessPingZhang() {
		return ((UsdtTotalDao)dao).coldBusinessPingZhang();
	}
}
