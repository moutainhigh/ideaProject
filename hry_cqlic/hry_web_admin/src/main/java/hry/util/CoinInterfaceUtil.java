/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Zhang Xiaofang
 * @version:      V1.0
 * @Date:        2016年8月15日 下午8:41:03
 */
package hry.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import hry.admin.exchange.model.ExDmCustomerPublickey;
import hry.admin.exchange.model.LmcTransfer;
import hry.admin.exchange.service.ExDmCustomerPublickeyService;
import hry.admin.exchange.service.ExDmTransactionService;
import hry.bean.JsonResult;
import hry.core.thread.ThreadPool;
import hry.listener.ServerManagement;
import hry.redis.common.utils.RedisService;
import hry.util.http.HttpConnectionUtil;
import hry.util.http.HttpsRequest;
import hry.util.properties.PropertiesUtils;
import hry.util.security.Check;
import hry.util.sys.ContextUtil;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * <p>
 * TODO
 * </p>
 *
 * @author: Zhang Xiaofang
 * @Date : 2016年8月15日 下午8:41:03
 */
public class CoinInterfaceUtil {
	private static Logger logger = Logger.getLogger(CoinInterfaceUtil.class);
    ExecutorService executor = Executors.newSingleThreadExecutor();


	/**
	 * 创建币的地址
	 * <p>
	 * TODO
	 * </p>
	 *
	 * @author: Zhang Xiaofang
	 * @param: @param
	 *             userName
	 * @param: @param
	 *             type
	 * @param: @return
	 * @return: String
	 * @Date : 2016年9月5日 下午4:57:51
	 * @throws:
	 */
	/*public static String create(String userName,String accountNumber, String type) {
		String result = "";
		// coin服务配置
		String coinInterFace = PropertiesUtils.APP.getProperty("app.coinInterFace");
		// 邻萌宝接口
		if ("LMC".equals(coinInterFace)) {
			AppCustomerService customerService = (AppCustomerService) ContextUtil.getBean("appCustomerService");
			RemoteThirdInterfaceService interfaceService = (RemoteThirdInterfaceService) ContextUtil.getBean("remoteThirdInterfaceService");
			QueryFilter filter = new QueryFilter(AppCustomer.class);
			filter.addFilter("userName=", userName);
			AppCustomer customer = customerService.get(filter);
			result = interfaceService.createCoinAddress(customer.getId() + "10000", type);
		} else {
			CoinInterfaceUtil util = new CoinInterfaceUtil();
			// 判断钱包接口是否打开
			if ("0".equals(util.getIsOpen())) {
				String url = PropertiesUtils.APP.getProperty("app.coinip");
				logger.error("开通币地址地址：" + url + "/coin/coin/create?userName=" + userName + "&accountNumber=" + accountNumber + "&type=" + type);
				result = HttpsRequest.post(url + "/coin/coin/create?userName=" + userName + "&accountNumber=" + accountNumber + "&type=" + type);
				logger.error("钱包调用返回值=" + result);
			} else {
				Map<String, String> map = new HashMap<String, String>();
				map.put("address", "");
				map.put("code", "error");
				Object obj = JSONObject.toJSON(map);
				result = obj.toString();
			}
		}
		return result;
	}*/

	/**
	 * 根据hash和type刷新以太坊，以太零
	 * @param
	 * @param
	 * @param
	 * @return
	 */
	public static String refreshETC(String hash, String type) {
		CoinInterfaceUtil util = new CoinInterfaceUtil();
		String ss = "";
		if (util.getIsOpen().equals("0")) {
			String url = PropertiesUtils.APP.getProperty("app.coinip");

			ss = HttpsRequest.post(url + "/coin/recaptureGethTx?hash=" + hash + "&type=" + type );

		} else {

			ss = "{\"code\":\"8888\",\"msg\"=\"接口调用失败\"}";
		}

		return ss;
	}

	/**
	 *
	 * <p>
	 * 通过coinCode查询该币所有 交易记录
	 * </p>
	 *
	 * @author: Zhang Xiaofang
	 * @param: @param
	 *             type
	 * @param: @return
	 * @return: String
	 * @Date : 2016年8月26日 上午11:01:39
	 * @throws:
	 */
	public static String list(String type) {
		CoinInterfaceUtil util = new CoinInterfaceUtil();
		String ss = "";
		if (util.getIsOpen().equals("0")) {
			String url = PropertiesUtils.APP.getProperty("app.coinip");
			ss = HttpsRequest.post(url + "/coin/allList?type=" + type);

		} else {

			ss = "[]";
		}

		return ss;
	}

	/**
	 * LMC 钱包-钱包转币（提币）
	 * <p>
	 * TODO
	 * </p>
	 *
	 * @author: Shangxl
	 * @param: @param
	 *             account
	 * @param: @param
	 *             address
	 * @param: @param
	 *             amount
	 * @param: @param
	 *             coinCode
	 * @param: @param
	 *             transactionNum
	 * @param: @param
	 *             userName
	 * @param: @return
	 * @return: String
	 * @Date : 2017年8月1日 下午5:29:13
	 * @throws:
	 */
	public static String lmcSendTo(String account, String address, String amount, String coinCode, String transactionNum, String userName) {
		CoinInterfaceUtil util = new CoinInterfaceUtil();
		String result = "{code=0,msg=接口没打开}";
		if (util.getIsOpen().equals("0")) {
			LmcTransfer transfer = new LmcTransfer();
			transfer.setTransfer_type("I");
			transfer.setSymbol(coinCode);
			transfer.setType("-1");
			transfer.setCoins(amount);
			transfer.setFrom(account);
			transfer.setTo(address);
			transfer.setTransfer_id(transactionNum);
			transfer.setValidation_type("md5");
			transfer.setValidation_code("hry");
			transfer.setValidation_phone(userName);
			ExDmTransactionService exDmTransactionService = (ExDmTransactionService)ContextUtil.getBean("exDmTransactionService");
			String[] out = exDmTransactionService.lmcTransfer(transfer);
			result = "{code=" + out[0] + ",msg=" + out[1] + "}";
			logger.error("提币接口调用结果：" + result);
		}
		return result;
	}











	/**
	 * 从一个地址转出币到另一个地址
	 * <p>
	 * TODO
	 * </p>
	 *
	 * @author: Zhang Xiaofang
	 * @param: @param
	 *             type
	 * @param: @return
	 * @return: String
	 * @Date : 2016年9月3日 下午3:50:57
	 * @throws:
	 */
	public static String sendTo(String userName,String toAddress, String amount, String coinCode,String transactionNum) {
		String result = null;
		try {
			CoinInterfaceUtil util = new CoinInterfaceUtil();
			if (util.getIsOpen().equals("0")) {

				RedisService redisService = (RedisService) ContextUtil.getBean("redisService");
				String str = redisService.get("cn:productinfoListall");
				Integer keepDecimalForCoin = 8 ;
				if(!StringUtils.isEmpty(str)){
					JSONArray array = JSON.parseArray(str);
					if(array!=null){
						for(int i =0 ; i < array.size() ;i++){
							JSONObject jsonObject = array.getJSONObject(i);
							if(coinCode.equals(jsonObject.getString("coinCode"))){
								keepDecimalForCoin = jsonObject.getInteger("keepDecimalForCoin");
							}
						}
					}
				}


				String memo = "0";
				QueryFilter filter = new QueryFilter(ExDmCustomerPublickey.class);
				filter.addFilter("publicKey=", toAddress);
				filter.addFilter("currencyType=", coinCode);
				filter.addFilter("publicKeyName=", userName);
				ExDmCustomerPublickeyService exDmCustomerPublicKeyService =  (ExDmCustomerPublickeyService) ContextUtil.getBean("exDmCustomerPublickeyService");
				ExDmCustomerPublickey exDmCustomerPublicKey = exDmCustomerPublicKeyService.get(filter);
				if(exDmCustomerPublicKey!=null){
					memo = exDmCustomerPublicKey.getRemark();
				}

				String url = PropertiesUtils.APP.getProperty("app.coinip");
				//校验码
				String [] params={toAddress,amount,coinCode,transactionNum,keepDecimalForCoin.toString(),memo};
				for(String l:params){
					logger.error("===============:"+l);

				}
				String authcode = Check.authCode(params);
				Map<String,String> map=new HashMap<String,String>();
				url=url+"/coin/sendFrom";
				map.put("type", coinCode);
				map.put("toAddress", toAddress);
				map.put("amount", amount);
				map.put("transactionNum", transactionNum);
				map.put("auth_code", authcode);
				map.put("keepDecimalForCoin", keepDecimalForCoin.toString());
				map.put("memo", memo);
				String param=StringUtil.string2params(map);
				logger.error(url+"?"+param);
				result=HttpConnectionUtil.postSend(url, param);
			} else {
				result="success:'false',msg:'后台接口未打开'";
			}
		} catch (Exception e) {
			result="{success:'false',msg:'请求超时'}";
			e.printStackTrace();
		}
		return result;
	}


	public static String row(String orderNo, String type) {
		CoinInterfaceUtil util = new CoinInterfaceUtil();
		String ss = "";
		if (util.getIsOpen().equals("0")) {
			String url = PropertiesUtils.APP.getProperty("app.coinip");
			ss = HttpsRequest.post(url + "/coin/row?orderNo=" + orderNo + "&type=" + type);

		} else {
			ss = "null";
		}
		return ss;
	}

	public static String balance(String account, String coinCode) {
		CoinInterfaceUtil util = new CoinInterfaceUtil();
		String ss = "";
		if (util.getIsOpen().equals("0")) {
			String url = PropertiesUtils.APP.getProperty("app.coinip");
			ss = HttpsRequest.post(url + "/coin/balance?userName=" + account + "&type=" + coinCode);

		} else {

			ss = "0.00";
		}

		return ss;
	}
	/**
	 *
	 * <p>
	 * 刷新用户币交易记录
	 * </p>
	 *
	 * @author: Yuan Zhicheng
	 * @param: @param
	 *             coinCode
	 * @param: @param
	 *             account
	 * @param: @param
	 *             count
	 * @param: @return
	 * @return: String
	 * @Date : 2016年11月21日 下午3:01:46
	 * @throws:
	 */
	public static String refreshUserCoin(String coinCode, String account, String count) {
		CoinInterfaceUtil util = new CoinInterfaceUtil();
		String ss = "";
		if (util.getIsOpen().equals("0")) {
			String url = PropertiesUtils.APP.getProperty("app.coinip");

			ss = HttpsRequest.post(url + "/coin/refreshUserCoin?coinCode=" + coinCode + "&account=" + account + "&count=" + count);

		} else {

			ss = "{\"code\":\"8888\",\"msg\"=\"接口调用失败\"}";
		}

		return ss;
	}

	/**
	 * 查询钱包接口是否关闭
	 * <p>
	 * TODO
	 * </p>
	 *
	 * @author: Zhang Xiaofang
	 * @param: @return
	 * @return: String
	 * @Date : 2016年9月5日 下午4:59:37
	 * @throws:
	 */
	public String getIsOpen() {
		String val = "";
		RedisService redisService = (RedisService) ContextUtil.getBean("redisService");
		String string = redisService.get("configCache:interfaceConfig");
		JSONArray obj = JSON.parseArray(string);
		for (Object o : obj) {
			JSONObject oo = JSON.parseObject(o.toString());
			if (oo.getString("configkey").equals("purseInterface")) {
				val = oo.getString("value");
			}
		}
		return val;
	}

	public static String listByCustomerName(String type, String customerName) {
		CoinInterfaceUtil util = new CoinInterfaceUtil();
		String ss = "";
		if (util.getIsOpen().equals("0")) {
			String url = PropertiesUtils.APP.getProperty("app.coinip");
			ss = HttpsRequest.post(url + "/coin/list?type=" + type + "&userName=" + customerName);
		} else {

			ss = "[]";
		}
		return ss;
	}

	public static void main(String[] args) {
//		提币安全校验失败----toAddress=1CDGZaVQiUbHqwZu8AQq3nXdCYvQ2GgRo9   type=BTC  amount=0.00019899 transactionNum=021802081620060374388  keepDecimalForCoin=8  memo=vghjkl;
		String toAddress="1CDGZaVQiUbHqwZu8AQq3nXdCYvQ2GgRo9";
		String amount="0.00019899";
		String coinCode="BTC";
		String transactionNum="021802081620060374388";
		String keepDecimalForCoin="8";
		String memo="vghjkl;";
		String [] params={toAddress,amount,coinCode,transactionNum,keepDecimalForCoin.toString(),memo};
		String authcode = Check.authCode(params);
		logger.error(authcode);
	}


	//根据合约地址获取精度
	public  JsonResult getCoinDecimals(String url) throws IOException {
		SearchCoin searchCoin = new SearchCoin(url);
        Future<JsonResult> result = executor.submit(searchCoin);
        try {
            return result.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return new JsonResult().setSuccess(false);
    }

	class SearchCoin implements Callable<JsonResult> {
	    private String url;

        public SearchCoin(String url) {
            this.url = url;
        }

        @Override
        public JsonResult call() throws Exception {
            JsonResult jsonResult = new JsonResult();
            List<String> list  = new ArrayList<>();
            Document doc = Jsoup.connect("https://etherscan.io/token/"+url).get();
            String title = doc.title();
            if(title!=null && title.contains("Error")){
                jsonResult.setSuccess(false);
                jsonResult.setMsg("合约地址不存在");
                return jsonResult;
            }
            String code = title.split("\\(")[1].split("\\)")[0];
            list.add(code);

            Element content = doc.getElementById("ContentPlaceHolder1_trDecimals");
            Elements links = content.getElementsByTag("td").eq(1);
            for (Element link : links) {
                String linkText = link.text();
                logger.error(linkText);
                list.add(linkText);
            }

            jsonResult.setObj(list);
            jsonResult.setSuccess(true);

            return jsonResult;
        }
    }
}
