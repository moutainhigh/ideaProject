/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年3月24日 下午2:04:29
 */
package hry.exchange.entrust.service.impl;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.customer.user.model.AppCustomer;
import hry.customer.user.service.AppCustomerService;
import hry.exchange.entrust.service.ExExEntrustService;
import hry.exchange.product.model.ExCointoCoin;
import hry.exchange.product.service.ExCointoCoinService;
import hry.exchange.product.service.ProductCommonService;
import hry.manage.remote.model.Coin;
import hry.trade.entrust.model.ExEntrust;
import hry.trade.entrust.service.ExEntrustService;
import hry.trade.entrust.service.impl.ExEntrustServiceImpl;
import hry.util.QueryFilter;
import hry.util.properties.PropertiesUtils;
import hry.util.sys.ContextUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * <p> TODO</p>
 * @author:         Gao Mimi 
 * @Date :          2016年4月12日 下午4:45:50 
 */
@Service("exExEntrustService")
public class ExExEntrustServiceImpl extends BaseServiceImpl<ExEntrust, Long>
		implements ExExEntrustService {
	private static Logger logger = Logger.getLogger(ExEntrustServiceImpl.class);
	@Resource(name = "exExEntrustDao")
	@Override
	public void setDao(BaseDao<ExEntrust, Long> dao) {
		super.dao = dao;
	}
	@Resource
	public ProductCommonService productCommonService;
	
	@Resource
	public AppCustomerService appCustomerService;
	
	
	
	
	
	
	
	
	
/*	
	//=======================币银网刷单机器人================================================================
	*//**
	 * 自动刷单
	 *//*
	public void autoAddExEntrust() {
		//需要获取配置文件中
		//先读取每种币的机器人是否开启
		//然后循环
		RemoteAppConfigService remoteAppConfigService  = (RemoteAppConfigService) ContextUtil.getBean("remoteAppConfigService");
		RemoteAppCustomerService remoteAppCustomerService = (RemoteAppCustomerService) ContextUtil.getBean("remoteAppCustomerService");
		
		//获取刷单数量和数量浮动比例
		String autoOrder_num= remoteAppConfigService.getValueByKey("autoOrder_num");
		String autoOrder_num_float= remoteAppConfigService.getValueByKey("autoOrder_num_float");
		
		//获取币种刷单是否开启
		String autoOrder_CRTC= remoteAppConfigService.getValueByKey("autoOrder_CRTC");
		String autoOrder_LTC= remoteAppConfigService.getValueByKey("autoOrder_LTC");
		String autoOrder_BTC= remoteAppConfigService.getValueByKey("autoOrder_BTC");
		//获取刷单账号，然后查询appcustomer对象
		String autoOrder_cellphone= remoteAppConfigService.getValueByKey("autoOrder_cellphone");
		RemoteQueryFilter filter=new RemoteQueryFilter(AppCustomer.class);
		filter.addFilter("userName=", autoOrder_cellphone);
		AppCustomer customer=remoteAppCustomerService.getByQueryFilter(filter);
		if(customer==null){
			logger.error("填写的手机号有误，请检查重试！");
			return;
		}
		
		
		if(autoOrder_CRTC!=null && "0".equals(autoOrder_CRTC)){
			//执行CRTC下单
			//获取价格和浮动比例
			String price_CRTC= remoteAppConfigService.getValueByKey("autoOrder_CRTC_price");
			String priceFloat_CRTC= remoteAppConfigService.getValueByKey("autoOrder_CRTC_price_float");
			BigDecimal truePrice_CRTC=new BigDecimal(price_CRTC);
			BigDecimal trueNum_CRTC=new BigDecimal(autoOrder_num);
			
			//生成刷单价格
			truePrice_CRTC=getFloatNum(price_CRTC, priceFloat_CRTC);
			truePrice_CRTC=truePrice_CRTC.setScale(2,BigDecimal.ROUND_HALF_DOWN);
			//生成刷单数量
			trueNum_CRTC=getFloatNum(autoOrder_num, autoOrder_num_float);
			trueNum_CRTC=trueNum_CRTC.setScale(2,BigDecimal.ROUND_HALF_DOWN);
			
			logger.error("联合学分开启刷单，设置刷单价格为："+price_CRTC+"，浮动比例为："+priceFloat_CRTC+"，生成的浮动价格:"+truePrice_CRTC.toString()+
					"，设置数量为："+autoOrder_num+"，数量浮动比例为："+autoOrder_num_float+"，生成浮动数量为："+trueNum_CRTC.toString());
			addExEntrust(1, customer.getId(), truePrice_CRTC, customer.getUserCode(), "CRTC", trueNum_CRTC, "cny", "cn");
			
			//生成刷单价格2
			truePrice_CRTC=getFloatNum(price_CRTC, priceFloat_CRTC);
			truePrice_CRTC=truePrice_CRTC.setScale(2,BigDecimal.ROUND_HALF_DOWN);
			//生成刷单数量2
			trueNum_CRTC=getFloatNum(autoOrder_num, autoOrder_num_float);
			trueNum_CRTC=trueNum_CRTC.setScale(2,BigDecimal.ROUND_HALF_DOWN);
			addExEntrust(2, customer.getId(), truePrice_CRTC, customer.getUserCode(), "CRTC", trueNum_CRTC, "cny", "cn");
		}
		if(autoOrder_LTC!=null && "0".equals(autoOrder_LTC)){
			//执行LTC下单
			String price_LTC= remoteAppConfigService.getValueByKey("autoOrder_LTC_price");
			String priceFloat_LTC= remoteAppConfigService.getValueByKey("autoOrder_LTC_price_float");
			BigDecimal truePrice_LTC=new BigDecimal(price_LTC);
			BigDecimal trueNum_LTC=new BigDecimal(autoOrder_num);
			
			//生成刷单价格
			truePrice_LTC=getFloatNum(price_LTC, priceFloat_LTC);
			truePrice_LTC=truePrice_LTC.setScale(2,BigDecimal.ROUND_HALF_DOWN);
			//生成刷单数量
			trueNum_LTC=getFloatNum(autoOrder_num, autoOrder_num_float);
			trueNum_LTC=trueNum_LTC.setScale(2,BigDecimal.ROUND_HALF_DOWN);
			
			logger.error("莱特币开启刷单，设置刷单价格为："+price_LTC+"，浮动比例为："+priceFloat_LTC+"，生成的浮动价格:"+truePrice_LTC.toString()+
					"，设置数量为："+autoOrder_num+"，数量浮动比例为："+autoOrder_num_float+"，生成浮动数量为："+trueNum_LTC.toString());
			addExEntrust(1, customer.getId(), truePrice_LTC, customer.getUserCode(), "LTC", trueNum_LTC, "cny", "cn");
			
			
			
			//生成刷单价格2
			truePrice_LTC=getFloatNum(price_LTC, priceFloat_LTC);
			truePrice_LTC=truePrice_LTC.setScale(2,BigDecimal.ROUND_HALF_DOWN);
			//生成刷单数量2
			trueNum_LTC=getFloatNum(autoOrder_num, autoOrder_num_float);
			trueNum_LTC=trueNum_LTC.setScale(2,BigDecimal.ROUND_HALF_DOWN);
			addExEntrust(2, customer.getId(), truePrice_LTC, customer.getUserCode(), "LTC", trueNum_LTC, "cny", "cn");
		}
		if(autoOrder_BTC!=null && "0".equals(autoOrder_BTC)){
			//执行BTC下单
			String price_BTC= remoteAppConfigService.getValueByKey("autoOrder_BTC_price");
			String priceFloat_BTC= remoteAppConfigService.getValueByKey("autoOrder_BTC_price_float");
			
			BigDecimal truePrice_BTC=new BigDecimal(price_BTC);
			BigDecimal trueNum_BTC=new BigDecimal(autoOrder_num);
			
			//生成刷单价格
			truePrice_BTC=getFloatNum(price_BTC, priceFloat_BTC);
			truePrice_BTC=truePrice_BTC.setScale(2,BigDecimal.ROUND_HALF_DOWN);
			//生成刷单数量
			trueNum_BTC=getFloatNum(autoOrder_num, autoOrder_num_float);
			trueNum_BTC=trueNum_BTC.setScale(2,BigDecimal.ROUND_HALF_DOWN);
			
			logger.error("莱特币开启刷单，设置刷单价格为："+price_BTC+"，浮动比例为："+priceFloat_BTC+"，生成的浮动价格:"+truePrice_BTC.toString()+
					"，设置数量为："+autoOrder_num+"，数量浮动比例为："+autoOrder_num_float+"，生成浮动数量为："+trueNum_BTC.toString());
			addExEntrust(1, customer.getId(), truePrice_BTC, customer.getUserCode(), "BTC", trueNum_BTC, "cny", "cn");
			
			
			
			//生成刷单价格2
			truePrice_BTC=getFloatNum(price_BTC, priceFloat_BTC);
			truePrice_BTC=truePrice_BTC.setScale(2,BigDecimal.ROUND_HALF_DOWN);
			//生成刷单数量2
			trueNum_BTC=getFloatNum(autoOrder_num, autoOrder_num_float);
			trueNum_BTC=trueNum_BTC.setScale(2,BigDecimal.ROUND_HALF_DOWN);
			addExEntrust(2, customer.getId(), truePrice_BTC, customer.getUserCode(), "BTC", trueNum_BTC, "cny", "cn");
		
		}
		
	}*/
	
	//=======================币银网刷单机器人================================================================
		/**
		 * 自动刷单
		 */
	@Override
		public void autoAddExEntrust() {Map<String, String> mapLoadWeb = PropertiesUtils.getLoadWeb();
		for (String Website : mapLoadWeb.keySet()) {
			String currencyType = mapLoadWeb.get(Website);
			ExCointoCoinService exCointoCoinService = (ExCointoCoinService) ContextUtil.getBean("exCointoCoinService");
			List<ExCointoCoin> listExCointoCoin=exCointoCoinService.getBylist(null,null,1);
			for (ExCointoCoin exCointoCoin : listExCointoCoin) {
				String autoUsername=exCointoCoin.getAutoUsername();
				Long customerId=exCointoCoin.getCustomerId();
				BigDecimal autoCount=exCointoCoin.getAutoCount();
				BigDecimal autoCountFloat=exCointoCoin.getAutoCountFloat();
				BigDecimal autoPrice=exCointoCoin.getAutoPrice();
				BigDecimal autoPriceFloat=exCointoCoin.getAutoPriceFloat();
				Integer isSratAuto=exCointoCoin.getIsSratAuto();
				Integer isFromChbtc=exCointoCoin.getIsFromChbtc();
				if(isSratAuto.equals(1)){
					QueryFilter filter=new QueryFilter(AppCustomer.class);
				/*	filter.addFilter("userName=", autoUsername);
					AppCustomer customer=appCustomerService.get(filter);*/
					
					if(null==customerId){
						filter.addFilter("userName=", autoUsername);
						AppCustomer customer=appCustomerService.get(filter);
						if(null==autoUsername){
							logger.error("填写的手机号有误，请检查重试！");
							return;
						}else{
							customerId=	customer.getId();
							exCointoCoin.setCustomerId(customerId);
							exCointoCoinService.update(exCointoCoin);
						}
					}
					if(isFromChbtc.equals(0)){
						Coin productCommon=productCommonService.getProductCommon(exCointoCoin.getCoinCode(), exCointoCoin.getFixPriceCoinCode());
						
						for(int i=0;i<1;i++){
							//生成刷单价格1
							BigDecimal buyautoPrice=getFloatNum(autoPrice, autoPriceFloat);
							buyautoPrice=buyautoPrice.setScale(productCommon.getKeepDecimalForCurrency(),BigDecimal.ROUND_HALF_DOWN);
							//生成刷单数量1
							BigDecimal buytrueNum=getFloatNum(autoCount, autoCountFloat);
							buytrueNum=buytrueNum.setScale(productCommon.getKeepDecimalForCoin(),BigDecimal.ROUND_HALF_DOWN);
							String coinCodebuy=exCointoCoin.getCoinCode()+"_"+exCointoCoin.getFixPriceCoinCode();
							addExEntrust(1, customerId, buyautoPrice, autoUsername, coinCodebuy, buytrueNum, "cny", "cn");
						
							
							//生成刷单价格2
							BigDecimal sellautoPrice=getFloatNum(autoPrice, autoPriceFloat);
							sellautoPrice=sellautoPrice.setScale(productCommon.getKeepDecimalForCurrency(),BigDecimal.ROUND_HALF_DOWN);
							//生成刷单数量2
							BigDecimal selltrueNum=getFloatNum(autoCount, autoCountFloat);
							selltrueNum=selltrueNum.setScale(productCommon.getKeepDecimalForCoin(),BigDecimal.ROUND_HALF_DOWN);
							String coinCodesell=exCointoCoin.getCoinCode()+"_"+exCointoCoin.getFixPriceCoinCode();
							addExEntrust(2, customerId, sellautoPrice, autoUsername, coinCodesell, selltrueNum, "cny", "cn");
						
						}
						
						
					}else{
						
						
						
						
					}
					
				}
			}
		}
		}
		
		
	
	
	
	/**
	 * 调用该方法
	 * <p> TODO</p>
	 * @author:         Zhang Lei
	 * @param:    @param type          买还是卖类型 1 买    2 卖
	 * @param:    @param customerId    用户ID
	 * @param:    @param price		        价格（机器人随机浮动范围内生成）
	 * @param:    @param userCode      用户code
	 * @param:    @param coinCode      币种
	 * @param:    @param entrustCount  数量
	 * @param:    @param currencyType  固定cny
	 * @param:    @param website       固定cn
	 * @return: void 
	 * @Date :          2017年2月9日 下午4:25:01   
	 * @throws:
	 */
	   public void addExEntrust(Integer type,Long customerId,BigDecimal price,String autoUsername,String coinCode,BigDecimal entrustCount,String currencyType,String website){
		     
	       ExEntrust exEntrust =new ExEntrust();
	       String[] rtd= coinCode.split("_");
		   if(rtd.length==1){
			    return ;
		   }else{
				exEntrust.setFixPriceCoinCode(rtd[1]);
				exEntrust.setCoinCode(rtd[0]); 
		   }
	       exEntrust.setType(type);
	       exEntrust.setEntrustPrice(price);
	       exEntrust.setEntrustCount(entrustCount);
	       exEntrust.setEntrustSum(new BigDecimal("0"));
	       exEntrust.setEntrustWay(1);//1.限价--> 表示以固定的价格 , 2.市价---> 
	       exEntrust.setCustomerId(customerId);
	     //  exEntrust.setUserCode(userCode);
	       exEntrust.setCustomerType(1);
	       exEntrust.setMatchPriority(5);
	       exEntrust.setCurrencyType(currencyType);
	       exEntrust.setWebsite(website);
	       exEntrust.setSource(2);
	       exEntrust.setUserName(autoUsername);
	       exEntrust.setTrueName("机器人"+autoUsername);
	       ExEntrustService exEntrustService = (ExEntrustService) ContextUtil.getBean("exEntrustService");
	       exEntrustService.saveExEntrust(exEntrust);
		}
	   
	   
	   /**
	    * 根据浮动比例获取浮动值
	    * <p> TODO</p>
	    * @author:         Zhang Lei
	    * @param:    @param number     固定值
	    * @param:    @param floatNum   浮动比例
	    * @param:    @return
	    * @return: BigDecimal 
	    * @Date :          2017年2月10日 下午2:57:24   
	    * @throws:
	    */
	   public BigDecimal getFloatNum(BigDecimal number,BigDecimal floatNum){
		   BigDecimal truePrice=number;
		   //获取浮动值       刷币价格 * (浮动比例 * 随机小数 )
			double num=Math.random();//获取随机小数
			BigDecimal fudongNum =number.multiply(floatNum).divide(new BigDecimal("100")).multiply(new BigDecimal(num));
			
			int number1 = (int) (Math.random() * 2);
			if (number1==0) {//这里向下浮动吧
				truePrice=number.subtract(fudongNum);
			}else {//这里向上浮动吧
				truePrice=number.add(fudongNum);
			}
		   
		   return truePrice;
	   }
}
