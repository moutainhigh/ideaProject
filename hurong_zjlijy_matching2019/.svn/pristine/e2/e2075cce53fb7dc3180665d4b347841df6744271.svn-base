package hry.mall.platform.remote;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;

import hry.bean.FrontPage;
import hry.bean.JsonResult;
import hry.util.PageFactory;
import hry.util.QueryFilter;
import hry.mall.integral.model.CustomerIntegral;
import hry.mall.integral.service.CustomerIntegralService;
import hry.mall.platform.model.Advertisement;
import hry.mall.platform.model.AdvertisementRecord;
import hry.mall.platform.model.MallConfig;
import hry.mall.platform.remote.model.SummarData;
import hry.mall.platform.service.AdvertisementRecordService;
import hry.mall.platform.service.AdvertisementService;
import hry.mall.platform.service.MallConfigService;
import hry.mq.producer.service.MessageProducer;

public class RemoteMallAdvertisementServiceImpl implements RemoteMallAdvertisementService {
	@Resource
	public  AdvertisementService advertisementService;
	@Resource
	public MallConfigService mallConfigService;
	@Resource
	public AdvertisementRecordService advertisementRecordService;
	@Resource
	public CustomerIntegralService customerIntegralService;
	@Resource
	public MessageProducer messageProducer;

	@Override
	public FrontPage mallAdvertisementList(Map<String, String> map) {
		// TODO Auto-generated method stub
		Page<Advertisement> page=PageFactory.getPage(map);
		List<Advertisement> list=advertisementService.findByPage(map);
		return new FrontPage(list, page.getTotal(), page.getPages(), page.getPageSize());
	}

	@Override
	public JsonResult findSummarData(Map<String, String> map) {
		// TODO Auto-generated method stub
		SummarData data=new SummarData();
		//1、查询历史点击和浏览量
		Map<String, String> map1=new HashMap<String,String>();
		map1.put("type", "1");
		Long c1=advertisementService.findCount(map1);
		data.setTotalClickCount(c1);
		Map<String, String> map2=new HashMap<String,String>();
		map2.put("type", "2");
		Long b1=advertisementService.findCount(map2);
		data.setTotalBrowseCount(b1);
		//2、查询今日点击和浏览量
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    String day=sdf.format(new Date());
		Map<String, String> map3=new HashMap<String,String>();
		map3.put("type", "1");
		map3.put("startTime", day+" 00:00:00");
		map3.put("endTime", day+" 23:59:59");
		Long c2=advertisementService.findCount(map3);
		data.setTodayClickCount(c2);
		Map<String, String> map4=new HashMap<String,String>();
		map4.put("type", "2");
		map4.put("startTime", day+" 00:00:00");
		map4.put("endTime", day+" 23:59:59");
		Long b2=advertisementService.findCount(map4);
		data.setTodayBrowseCount(b2);
		//3、查询用户获得奖励数
		BigDecimal count=advertisementService.findGetCount(map);
		data.setGetCount(count);
		List<MallConfig> list=mallConfigService.findAll();
		if(null!=list && list.size()>0){
			MallConfig config=list.get(0);
			data.setCoinCode(config.getAdvertiseCoinCode());
		}
		return new JsonResult().setSuccess(true).setObj(data).setMsg("caozuochenggong");
	}

	@Override
	public JsonResult handAdvertisement(Map<String, String> map) {
		// TODO Auto-generated method stub
		String memberId=map.get("memberId");//用户id
		String type=map.get("type");//类型，1点击，2浏览
		String advertisementId=map.get("advertisementId");//广告id
		QueryFilter fiter=new QueryFilter(AdvertisementRecord.class);
		fiter.addFilter("memberId=", memberId);
		fiter.addFilter("type=", type);
		fiter.addFilter("advertisementId=", advertisementId);
		List<AdvertisementRecord> list= advertisementRecordService.find(fiter);
		if(null!=list && list.size()>0){
		}else{
			//1、保存奖励记录
			AdvertisementRecord record=new AdvertisementRecord();
			record.setMemberId(Long.valueOf(memberId));
			record.setType(Integer.valueOf(type));
			record.setAdvertisementId(Long.valueOf(advertisementId));
			Advertisement adverise=advertisementService.get(Long.valueOf(advertisementId));
			if(null!=adverise){
			  if("2".equals(type)){
				  record.setCoinCount(adverise.getBrowseCount());
			  }else{
				  record.setCoinCount(adverise.getClickCount()); 
			  }
			  record.setCoinCode(adverise.getCoinCode());
			}else{
				 record.setCoinCount(new BigDecimal("0"));
				 List<MallConfig> list1=mallConfigService.findAll();
					if(null!=list1 && list1.size()>0){
						MallConfig config=list1.get(0);
						record.setCoinCode(config.getAdvertiseCoinCode());
					}
			}
			advertisementRecordService.save(record);
			//2、保存积分信息
			//查询用户账户
			QueryFilter queryFilter = new QueryFilter(CustomerIntegral.class);
			queryFilter.addFilter("memberId=", memberId);
			//与用户一对一
			CustomerIntegral customerIntegral = customerIntegralService.find(queryFilter).get(0);
			Map<String, String> map1 = new HashMap<>();
			map1.put("id", customerIntegral.getId().toString());
			map1.put("type", CustomerIntegral.TYPE_ADD);
			map1.put("integralCount", record.getCoinCount().toString());
			map1.put("tradingDetail", "广告奖励");
			map1.put("businessType", "9");//广告奖励收入
			map1.put("requestNo","GGJL"+record.getId().toString());//流水号
			customerIntegralService.updateInteger(map1);
			//3、广告奖励的操作币账户----在积分明细统一处理了
	/*		if(record.getCoinCount().compareTo(new BigDecimal("0"))>0){
				String platCoin = BaseConfUtil.getConfigSingle("platCoin", "configCache:basefinanceConfig");//平台币币种
				//封装参数，操作币业务流水和账户
				Map<String,String> map2=new HashMap<String,String>();
				map2.put("coinCode", platCoin);
				map2.put("memberId", record.getMemberId().toString());
				map2.put("transactionType", "1");// 交易类型(1币收入 ，2币支出)
				map2.put("coinCount", record.getCoinCount().toString());
				map2.put("OrderNo", "GGJL"+record.getId().toString());
				map2.put("optType", "514");//广告奖励
				customerIntegralService.handCoin(map2);
			}*/
		}
		return new JsonResult().setSuccess(true).setMsg("caozuochenggong");
	}
}
