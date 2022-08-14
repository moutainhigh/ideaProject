/**
 * Copyright:   
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-13 11:12:40 
 */
package hry.admin.exchange.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import hry.admin.exchange.dao.ExEntrustDao;
import hry.admin.exchange.model.ExEntrust;
import hry.admin.exchange.model.ExOrderInfo;
import hry.admin.exchange.service.ExDmPingService;
import hry.admin.exchange.service.ExEntrustService;
import hry.admin.exchange.service.ExOrderInfoService;
import hry.admin.mq.producer.service.MessageProducer;
import hry.bean.PageResult;
import hry.core.constant.CodeConstant;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.trade.redis.model.EntrustTrade;
import hry.util.PageFactory;
import hry.util.QueryFilter;
import hry.util.properties.PropertiesUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.util.StringUtil;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p> ExEntrustService </p>
 * @author:         liushilei
 * @Date :          2018-06-13 11:12:40  
 */
@Service("exEntrustService")
public class ExEntrustServiceImpl extends BaseServiceImpl<ExEntrust, Long> implements ExEntrustService{
	
	@Resource(name="exEntrustDao")
	@Override
	public void setDao(BaseDao<ExEntrust, Long> dao) {
		super.dao = dao;
	}

	@Resource
	private MessageProducer messageProducer;
	@Resource
	private ExDmPingService exDmPingService;
	@Resource
	private ExOrderInfoService exOrderInfoService;

	@Override
	public String getHeader(String coinCode, String fixPriceCoinCode) {
		String header = "cn:cny:" + coinCode + "_" + fixPriceCoinCode;
		return header;
	}

	@Override
	public PageResult findPageBySql(QueryFilter filter) {


		//创建PageResult对象
		Page<ExEntrust> page = PageFactory.getPage(filter);

		//----------------------查询开始------------------------------

		Map<String,Object> map = new HashMap<String,Object>();

		String email = filter.getRequest().getParameter("email");
		String mobilePhone = filter.getRequest().getParameter("mobilePhone");
		String trueName = filter.getRequest().getParameter("trueName");
		String surname = filter.getRequest().getParameter("surname");

		String type=filter.getRequest().getParameter("type");
		String entrustType=filter.getRequest().getParameter("entrustType");
		String entrustWay=filter.getRequest().getParameter("entrustWay");
		String coinCode=filter.getRequest().getParameter("coinCode");
		String gentrustTime=filter.getRequest().getParameter("entrustTime_GT");
		String lentrustTime=filter.getRequest().getParameter("entrustTime_LT");
		String fixPriceCoinCode=filter.getRequest().getParameter("fixPriceCoinCode");
		String source=filter.getRequest().getParameter("source");
		String entrustNum = filter.getRequest().getParameter("entrustNum");


		if(!StringUtil.isEmpty(source)){
			map.put("source", source);
		}
		if(!StringUtil.isEmpty(type)){
			map.put("type", type);
		}
		if(!StringUtil.isEmpty(entrustWay)){
			map.put("entrustWay", entrustWay);
		}
		if(!StringUtil.isEmpty(entrustType)){
			map.put("entrustType", entrustType);
		}
		if(!StringUtil.isEmpty(coinCode)){
			map.put("coinCode", coinCode);
		}
		if(!StringUtil.isEmpty(gentrustTime)){
			map.put("gentrustTime", gentrustTime);
		}
		if(!StringUtil.isEmpty(lentrustTime)){
			map.put("lentrustTime", lentrustTime);
		}
		if(!StringUtil.isEmpty(fixPriceCoinCode)){
			map.put("fixPriceCoinCode", fixPriceCoinCode);
		}
		if(!StringUtil.isEmpty(entrustNum)){
			map.put("entrustNum", entrustNum);
		}

		if(!StringUtils.isEmpty(email)){
			map.put("email", "%"+email+"%");
		}
		if(!StringUtils.isEmpty(mobilePhone)){
			map.put("mobilePhone", "%"+mobilePhone+"%");
		}
		if(!StringUtils.isEmpty(trueName)){
			map.put("trueName", "%"+trueName+"%");
		}
		if(!StringUtils.isEmpty(surname)){
			map.put("surname", "%"+surname+"%");
		}

		((ExEntrustDao)dao).findPageBySql(map);
		return new PageResult(page, filter.getPage(),filter.getPageSize());
	}

	/**
	 * 取消委托单
	 */
	@Override
	public String[] cancelExEntrust(EntrustTrade entrustTrade, String remark) {
		String[] rt= exDmPingService.checkPing(entrustTrade.getCustomerId());
		if(rt[0].equals(CodeConstant.CODE_FAILED)){
			rt[0] = CodeConstant.CODE_SUCCESS;
			rt[1] = "平仓中，不能撤销";
			return rt;
		}
		// 序列化
		String str = JSON.toJSONString(entrustTrade);
		// 发送消息
		messageProducer.toTrade(str);
		String[] relt = new String[2];
		relt[0] = CodeConstant.CODE_SUCCESS;
		relt[1] = "";
		return relt;

	}



	@Override
	public List<ExOrderInfo> getMatchDetail(String entrustNum, String coinCode, String type) {
		String saasId = PropertiesUtils.APP.getProperty("app.saasId");
		QueryFilter filter = new QueryFilter(ExOrderInfo.class);
		filter.setSaasId(saasId);
		filter.addFilter("coinCode=", coinCode);
		if(type.equals("1")){
			filter.addFilter("buyEntrustNum=", entrustNum);
		}else{
			filter.addFilter("sellEntrustNum=", entrustNum);
		}


		List<ExOrderInfo> list = exOrderInfoService.find(filter);
		return list;

	}

	@Override
	public PageResult findLendPageBySql(QueryFilter filter) {

		//创建PageResult对象
		Page<ExEntrust> page = PageFactory.getPage(filter);

		//----------------------查询开始------------------------------

		Map<String,Object> map = new HashMap<String,Object>();

		String email = filter.getRequest().getParameter("email");
		String mobilePhone = filter.getRequest().getParameter("mobilePhone");
		String trueName = filter.getRequest().getParameter("trueName");
		String surname = filter.getRequest().getParameter("surname");

		String type=filter.getRequest().getParameter("type");
		String entrustType=filter.getRequest().getParameter("entrustType");
		String entrustWay=filter.getRequest().getParameter("entrustWay");
		String coinCode=filter.getRequest().getParameter("coinCode");
		String gentrustTime=filter.getRequest().getParameter("entrustTime_GT");
		String lentrustTime=filter.getRequest().getParameter("entrustTime_LT");
		String fixPriceCoinCode=filter.getRequest().getParameter("fixPriceCoinCode");
		String source=filter.getRequest().getParameter("source");
		String entrustNum = filter.getRequest().getParameter("entrustNum");


		if(!StringUtil.isEmpty(source)){
			map.put("source", source);
		}
		if(!StringUtil.isEmpty(type)){
			map.put("type", type);
		}
		if(!StringUtil.isEmpty(entrustWay)){
			map.put("entrustWay", entrustWay);
		}
		if(!StringUtil.isEmpty(entrustType)){
			map.put("entrustType", entrustType);
		}
		if(!StringUtil.isEmpty(coinCode)){
			map.put("coinCode", coinCode);
		}
		if(!StringUtil.isEmpty(gentrustTime)){
			map.put("gentrustTime", gentrustTime);
		}
		if(!StringUtil.isEmpty(lentrustTime)){
			map.put("lentrustTime", lentrustTime);
		}
		if(!StringUtil.isEmpty(fixPriceCoinCode)){
			map.put("fixPriceCoinCode", fixPriceCoinCode);
		}
		if(!StringUtil.isEmpty(entrustNum)){
			map.put("entrustNum", entrustNum);
		}

		if(!StringUtils.isEmpty(email)){
			map.put("email", "%"+email+"%");
		}
		if(!StringUtils.isEmpty(mobilePhone)){
			map.put("mobilePhone", "%"+mobilePhone+"%");
		}
		if(!StringUtils.isEmpty(trueName)){
			map.put("trueName", "%"+trueName+"%");
		}
		if(!StringUtils.isEmpty(surname)){
			map.put("surname", "%"+surname+"%");
		}

		((ExEntrustDao)dao).findLendPageBySql(map);
		return new PageResult(page, filter.getPage(),filter.getPageSize());
	}
}
