/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2020-12-17 16:37:09 
 */
package hry.licqb.exchange.service.impl;

import hry.licqb.exchange.dao.ExchangeTotalDao;
import hry.licqb.exchange.model.ExchangeConfig;
import hry.licqb.exchange.model.ExchangeTotal;
import hry.licqb.exchange.service.ExchangeConfigService;
import hry.licqb.exchange.service.ExchangeTotalService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;

import hry.licqb.record.dao.UsdtTotalDao;
import hry.licqb.record.model.UsdtTotal;
import hry.util.QueryFilter;
import hry.util.date.DateUtil;
import hry.util.serialize.Person;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p> ExchangeTotalService </p>
 * @author:         zhouming
 * @Date :          2020-12-17 16:37:09  
 */
@Service("exchangeTotalService")
public class ExchangeTotalServiceImpl extends BaseServiceImpl<ExchangeTotal, Long> implements ExchangeTotalService{
	@Resource
	private ExchangeConfigService exchangeConfigService;

	@Resource(name="exchangeTotalDao")
	@Override
	public void setDao(BaseDao<ExchangeTotal, Long> dao) {
		super.dao = dao;
	}

	@Override
	public void payAndExtractExchangeTotal() {
		// 获取已开启兑换项目配置
		QueryFilter filter = new QueryFilter(ExchangeConfig.class);
		filter.addFilter("status=", 1);
		filter.addFilter("itemStartDate <=", new Date());
		List<ExchangeConfig> configList = exchangeConfigService.find(filter);
		if (configList == null || configList.size() == 0) {
			return;
		}
		// 去重
		List<String> codeList = configList.stream().map(o->o.getGainCoinCode()).distinct().collect(Collectors.toList());
		codeList.forEach(code -> {
			QueryFilter filter1 = new QueryFilter(ExchangeTotal.class);
			filter1.addFilter("coinCode=", code);
			filter1.setOrderby("id DESC");
			ExchangeTotal total = super.get(filter1);
			total = total == null ? new ExchangeTotal() : total;
			String today = DateUtil.getNewDate();
			// 获取当前币昨天总兑换数量
			ExchangeTotal exchangeTotal = ((ExchangeTotalDao)dao).getExchangeNum(code);
			// 获取当昨天提币数量
			ExchangeTotal extractTotal = ((ExchangeTotalDao)dao).getExtractNum(code);
			total.setGainNum(exchangeTotal.getGainNum());
			total.setCoinCode(code);
			total.setExtractNum(extractTotal.getExtractNum());
			if (total.getId() == null) {
				super.save(total);
			} else {
				String created = DateUtil.dateToString(total.getCreated());
				if (created.split(" ")[0].equals(today.split(" ")[0])) {
					super.update(total);
				} else {
					total.setId(null);
					super.save(total);
				}
			}
		});
	}
}
