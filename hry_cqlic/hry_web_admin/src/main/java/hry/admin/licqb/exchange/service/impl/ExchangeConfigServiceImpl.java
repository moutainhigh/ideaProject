/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2020-12-17 16:32:12 
 */
package hry.admin.licqb.exchange.service.impl;

import hry.admin.licqb.exchange.model.ExchangeConfig;
import hry.admin.licqb.exchange.model.ExchangeItem;
import hry.admin.licqb.exchange.service.ExchangeConfigService;
import hry.admin.licqb.exchange.service.ExchangeItemService;
import hry.bean.JsonResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;

import hry.util.QueryFilter;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p> ExchangeConfigService </p>
 * @author:         zhouming
 * @Date :          2020-12-17 16:32:12  
 */
@Service("exchangeConfigService")
public class ExchangeConfigServiceImpl extends BaseServiceImpl<ExchangeConfig, Long> implements ExchangeConfigService{

	@Resource
	private ExchangeItemService exchangeItemService;

	@Resource(name="exchangeConfigDao")
	@Override
	public void setDao(BaseDao<ExchangeConfig, Long> dao) {
		super.dao = dao;
	}

	@Override
	public JsonResult handleConfig(Long id, String type) {
		ExchangeConfig config = super.get(id);
		// 设置当前期数
		Integer periodsNum = 0;
		// 查询该配置项目是否存在
		QueryFilter filter = new QueryFilter(ExchangeItem.class);
		filter.addFilter("itemConfigId=", id);
		filter.setOrderby("created DESC");
		ExchangeItem thisItem = exchangeItemService.get(filter);
		periodsNum = thisItem != null ? thisItem.getPeriodsNum() : 0;
		// type 1 开启 2 关闭
		if ("1".equals(type)) {
			if (config.getStatus() == 1) {
				return new JsonResult(false).setMsg("项目配置已启动");
			}
			// 添加项目启动期数
			config.setStatus(1);
			ExchangeItem item = new ExchangeItem();
			item.setItemConfigId(config.getId());
			item.setItemName(config.getItemName());
			item.setPayCoinCode(config.getPayCoinCode());
			item.setGainCoinCode(config.getGainCoinCode());
			item.setRatio(config.getRatio());
			item.setTotalNum(config.getTotalNum());
			item.setSingleMinNum(config.getSingleMinNum());
			item.setSoloMaxNum(config.getSoloMaxNum());
			item.setItemStartDate(config.getItemStartDate());
			item.setPeriodsNum(periodsNum + 1);
			exchangeItemService.save(item);
		} else {
			if (config.getStatus() == 0) {
				return new JsonResult(false).setMsg("项目配置已关闭");
			}
			config.setStatus(0);
			thisItem.setStatus(1);
			thisItem.setItemEndDate(new Date());
			exchangeItemService.update(thisItem);
		}
		super.update(config);
		return new JsonResult(true);
	}

}
