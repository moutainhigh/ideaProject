package hry.exchange.product.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import hry.exchange.product.model.ExRobot;
import hry.exchange.product.service.impl.ConmonApiService;
import hry.redis.common.utils.RedisService;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
@Service("conmonApiService")
public class ConmonApiServiceImpl implements  ConmonApiService{
	private static Logger logger = Logger.getLogger(ConmonApiServiceImpl.class);
	@Resource
	private RedisService redisService;
	public String  getKkcoinCurrentPriceByApi(ExRobot exRobot) {
		CloseableHttpClient client = HttpClients.createDefault();
		CloseableHttpResponse response = null;
			String coinCode = exRobot.getCoinCode();
			String fixPriceCoinCode = exRobot.getFixPriceCoinCode();
			String parm = coinCode+"_"+fixPriceCoinCode;
			String priceSource=exRobot.getPriceSource();//kkcoin
			try {
				String url =CommenApiUrl.kkcoin_urlPrice;
				url =url+parm;
				HttpGet request = new HttpGet(url);
				response = client.execute(request);
				if(response.getEntity()!=null){
					String responseContent = EntityUtils.toString(response.getEntity());
					JSONArray jsonv5 = JSON.parseArray(responseContent);
					if(jsonv5.size()==0){
						  logger.error(parm+"----kkcoin没有此交易对");
						  return null;
					}else{
						Object a=jsonv5.get(0);
						String pricestr=a.toString().split(",")[1];
						String price=pricestr.substring(1,pricestr.length()-1);
					    logger.error(parm+"==price=="+price);
					    return price;

					}
					
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
		        try {
		         response.close();
					client.close();
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    }
			
			return null;
		}
	
	public String  getOkcoinCurrentPriceByApi(ExRobot exRobot) {
		CloseableHttpClient client = HttpClients.createDefault();
		CloseableHttpResponse response = null;
			String coinCode = exRobot.getCoinCode();
			String fixPriceCoinCode = exRobot.getFixPriceCoinCode();
			String parm = coinCode+"_"+fixPriceCoinCode;
			String priceSource=exRobot.getPriceSource();//okcoin
			try {
				String url =CommenApiUrl.okcoin_urlPrice;
				url =url+parm.toLowerCase();
				HttpGet request = new HttpGet(url);
				response = client.execute(request);
				if(response.getEntity()!=null){
					String responseContent = EntityUtils.toString(response.getEntity());
					JSONObject jsonv5 = JSON.parseObject(responseContent);
					if(jsonv5.get("error_code").toString().equals("1007")){
						  logger.error(parm+"---okcoin没有此交易对");
						  return null;
					}else{
						String pricestr=jsonv5.get("last").toString();
					    logger.error(parm+"==price=="+pricestr);
					    return pricestr;
					}
					
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
		        try {
		         response.close();
					client.close();
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    }
			return null;
		}
	public String  getBittrexCurrentPriceByApi(ExRobot exRobot) {
		CloseableHttpClient client = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		String str=null;
			String coinCode = exRobot.getCoinCode();
			String fixPriceCoinCode = exRobot.getFixPriceCoinCode();
			String parm = fixPriceCoinCode+"-"+coinCode;
			try {
				String url=CommenApiUrl.bittrex_urlPrice;
				HttpGet request = new HttpGet(url+parm);
				response = client.execute(request);
				if(response.getEntity()!=null){
					String responseContent = EntityUtils.toString(response.getEntity());
					JSONObject data = JSONObject.parseObject(responseContent);
					if("true".equals(data.getString("success"))){
						JSONObject dataobj = data.getJSONObject("result");
						String pricestr=dataobj.getString("Last");
						   logger.error(parm+"==bittrex--price=="+pricestr);
						   return pricestr;
						//result.setObj(list);
					}else{
						  logger.error(parm+"---bittrex没有此交易对");
						  return null;
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
		        try {
					response.close();
					client.close();
				} catch (Exception e) {
		            e.printStackTrace();
		        }
		    }
			return null;
	
	}
	public void  getOkexCurrentPriceByApi(ExRobot exRobot) {
		CloseableHttpClient client = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		String coinCode = exRobot.getCoinCode();
		String fixPriceCoinCode = exRobot.getFixPriceCoinCode();
		String parm = fixPriceCoinCode+"_"+coinCode;
		try {
			String url=CommenApiUrl.okex_urlPrice;
			HttpGet request = new HttpGet(url+parm);

			response = client.execute(request);
			if(response.getEntity()!=null){
				String responseContent = EntityUtils.toString(response.getEntity());
				JSONObject data = JSONObject.parseObject(responseContent);
				if(!StringUtils.isEmpty(data.getString("ticker"))){
					JSONObject dataobj = data.getJSONObject("ticker");
					String pricestr=dataobj.getString("last");
					redisService.save(coinCode+"_"+fixPriceCoinCode+":thirdApi:currentExchangPrice", dataobj.getString("last"));
					   logger.error(parm+"==okex--price=="+pricestr);
					//result.setObj(list);
				}else{
					  logger.error(parm+"---okex没有此交易对");
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
	        try {
	         client.close();
	         response.close();
	       
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	
	}
	@Override
	public String getCurrentPriceByApi(ExRobot exRobot){
			if(null!=exRobot.getPriceSource()){
				if(exRobot.getPriceSource().equals("okcoin")){
				 return	getOkcoinCurrentPriceByApi(exRobot);
				}else if(exRobot.getPriceSource().equals("kkcoin")){
					return getKkcoinCurrentPriceByApi(exRobot);
				}else if(exRobot.getPriceSource().equals("bittrex")){
					return getBittrexCurrentPriceByApi(exRobot);
				}else if(exRobot.getPriceSource().equals("okex")){
					getOkexCurrentPriceByApi(exRobot);
				}
			}
			return null;
			
			
		}
		
		


}
