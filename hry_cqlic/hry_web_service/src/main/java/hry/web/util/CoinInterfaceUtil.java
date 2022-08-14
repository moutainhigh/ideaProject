/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Zhang Xiaofang
 * @version:      V1.0
 * @Date:        2016年8月15日 下午8:41:03
 */
package hry.web.util;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import hry.core.constant.StringConstant;
import hry.util.StringUtil;
import hry.util.http.HttpConnectionUtil;
import hry.util.http.HttpsRequest;

import hry.util.properties.PropertiesUtils;
import hry.util.sys.ContextUtil;
import hry.redis.common.utils.RedisService;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;

/**
 * <p> TODO</p>
 * @author:         Zhang Xiaofang
 * @Date :          2016年8月15日 下午8:41:03
 */
public class CoinInterfaceUtil {
	private static Logger logger = Logger.getLogger(CoinInterfaceUtil.class);


	/**
	 * 创建币的地址
	 * <p> TODO</p>
	 * @author:         Zhang Xiaofang
	 * @param:    @param userName
	 * @param:    @param type
	 * @param:    @return
	 * @return: String
	 * @Date :          2016年9月5日 下午4:57:51
	 * @throws:
	 */
	public static String  create(String userName,String type) {
		CoinInterfaceUtil util=new   CoinInterfaceUtil();
		String ss="";
		if(util.getIsOpen().equals("0")){
			String url=PropertiesUtils.APP.getProperty("app.coinip");
			logger.error("开通币地址地址："+url+"/coinsecurity/coin/create?userName="+userName+"&type="+type);
			ss=HttpsRequest.post(url+"/coinsecurity/coin/create?userName="+userName+"&type="+type);
			logger.error("钱包调用返回值="+ss);
		}else{
			Map<String, String> map = new HashMap<String, String>();
			map.put("address", "");
			map.put("code", "error");
			Object obj=  JSONObject.toJSON(map);
			ss=obj.toString();
		}

		return ss;
	}


	/**
	 * <p> 通过coinCode查询该币所有 交易记录</p>
	 * @author:         Zhang Xiaofang
	 * @param:    @param type
	 * @param:    @return
	 * @return: String
	 * @Date :          2016年8月26日 上午11:01:39
	 * @throws:
	 */
	public static String  list(String type) {
		CoinInterfaceUtil util=new   CoinInterfaceUtil();
		String ss="";
		if(util.getIsOpen().equals("0")){
			String url=PropertiesUtils.APP.getProperty("app.coinip");
			ss=HttpsRequest.post(url+"/coin/allList?type="+type);

		}else{

			ss="[]";
		}

		return ss;
	}

	/**
	 * <p> 通过coinCode查询该币所有 交易记录</p>
	 * @author:         Zhang Xiaofang
	 * @param:    @param type
	 * @param:    @return
	 * @return: String
	 * @Date :          2016年8月26日 上午11:01:39
	 * @throws:
	 */
	public static String  listForUser(String type,String userName) {
		CoinInterfaceUtil util=new   CoinInterfaceUtil();
		String ss="";
		if(util.getIsOpen().equals("0")){
			String url=PropertiesUtils.APP.getProperty("app.coinip");
			ss=HttpsRequest.post(url+"/coin/list?type="+type+"&userName="+userName);
		}else{
			ss="[]";
		}
		return ss;
	}




	/**
	 * 从一个地址转出币到另一个地址
	 * <p> TODO</p>
	 * @author:         Zhang Xiaofang
	 * @param:    @param type
	 * @param:    @return
	 * @return: String
	 * @Date :          2016年9月3日 下午3:50:57
	 * @throws:
	 */
	public static String  sendTo(String account,String address,String amount,String coinCode) {
		CoinInterfaceUtil util=new   CoinInterfaceUtil();
		String ss="";
		if(util.getIsOpen().equals("0")){
			String url=PropertiesUtils.APP.getProperty("app.coinip");
			ss=HttpsRequest.post(url+"/coin/sendFrom?type="+coinCode+"&"+"account="+account+"&"+"address="+address+"&"+"amount="+amount);
		}else{
			ss="{code=8,msg=接口没打开}";
		}

		return ss;
	}

	public static String  row(String orderNo,String type) {
		CoinInterfaceUtil util=new   CoinInterfaceUtil();
		String ss="";
		if(util.getIsOpen().equals("0")){
			String url=PropertiesUtils.APP.getProperty("app.coinip");
			ss=HttpsRequest.post(url+"/coin/row?orderNo="+orderNo+"&type="+type);

		}else{

			ss="{hex=01000000ccd7d05701ee9716e87079b4cedd396a74c6401c69e0c7bb01af797b5f157baa0803cb1b99010000006b483045022100b642774e84d8a39e639a97825f4aa3d26276f395b07b04c19401e9599be38d32022010afda908c27992ca22e6be5bef9c1854fd6f645eb54a1ee94a3cb3867598c33012103a513c33a5bfd449e52e84f0cd167ab62e46c71b02ad8d1a6326b791755e2c0ceffffffff02705d1e00000000001976a914fbc4770629446effcbf56f8760e8b2a09f19389b88ac40420f00000000001976a91423304b0de958648b128ab92aa7a944067a7049fd88ac00000000, txid=40ea06a53850c50973ce3363f99748a30e8445e3185e89c0d93a1b2a9c4ba4e0, version=1, time=1473304545, locktime=0, vin=[{txid=991bcb0308aa7b155f7b79af01bbc7e0691c40c6746a39ddceb47970e81697ee, vout=1, scriptSig={asm=3045022100b642774e84d8a39e639a97825f4aa3d26276f395b07b04c19401e9599be38d32022010afda908c27992ca22e6be5bef9c1854fd6f645eb54a1ee94a3cb3867598c3301 03a513c33a5bfd449e52e84f0cd167ab62e46c71b02ad8d1a6326b791755e2c0ce, hex=483045022100b642774e84d8a39e639a97825f4aa3d26276f395b07b04c19401e9599be38d32022010afda908c27992ca22e6be5bef9c1854fd6f645eb54a1ee94a3cb3867598c33012103a513c33a5bfd449e52e84f0cd167ab62e46c71b02ad8d1a6326b791755e2c0ce}, sequence=4294967295}], vout=[{value=1.99, n=0, scriptPubKey={asm=OP_DUP OP_HASH160 fbc4770629446effcbf56f8760e8b2a09f19389b OP_EQUALVERIFY OP_CHECKSIG, hex=76a914fbc4770629446effcbf56f8760e8b2a09f19389b88ac, reqSigs=1, type=pubkeyhash, addresses=[DsRvV942wkRrVLoackTMNNBn7BVLUAQ7D5]}}, {value=1.0, n=1, scriptPubKey={asm=OP_DUP OP_HASH160 23304b0de958648b128ab92aa7a944067a7049fd OP_EQUALVERIFY OP_CHECKSIG, hex=76a91423304b0de958648b128ab92aa7a944067a7049fd88ac, reqSigs=1, type=pubkeyhash, addresses=[DXgm2YruUYPRuk9yi65y3JCxSAxYnGYif8]}}], blockhash=000004a7a9045191116ca846dfdf4a1217ba9cff3f2f0bd2da4914162e0998da, confirmations=174, blocktime=1473304545}";
		}

		return ss;
	}


	public static String  balance(String account,String coinCode) {
		CoinInterfaceUtil util=new   CoinInterfaceUtil();
		String ss="";
		if(util.getIsOpen().equals("0")){
			String url=PropertiesUtils.APP.getProperty("app.coinip");
			ss=HttpsRequest.post(url+"/coin/balance?userName="+account+"&type="+coinCode);
		}else{
			ss="0.00";
		}
		return ss;
	}




	public static String  sendTo(String address ,String amount,String type) {
		CoinInterfaceUtil util=new   CoinInterfaceUtil();
		String ss="";
		if(util.getIsOpen().equals("0")){
			String url=PropertiesUtils.APP.getProperty("app.coinip");
			ss=HttpsRequest.post(url+"/coin/sendTo?address="+address+"&type="+type+"&amount="+amount);
    	}else{
			ss="{code=8,msg=222222}";
		}

		return ss;
	}


	/**
	 *  从一个币转到 一个币账户
	 *
	 * @param account  发款人
	 * @param address  收款人
	 * @param amount  数量
	 * @param type  类型
	 * @return
	 */
	public static String  sendFrom(String account, String address ,String amount,String type) {
		CoinInterfaceUtil util=new   CoinInterfaceUtil();
		String ss="";
		if(util.getIsOpen().equals("0")){
			String url=PropertiesUtils.APP.getProperty("app.coinip");
			ss=HttpsRequest.post(url+"/coin/sendFrom?account="+account+"&address="+address+"&type="+type+"&amount="+amount);
		}else{
			ss="{code=18,msg=222222}";
		}

		return ss;
	}


	/**
	 *
	 * 查询某种币的所有账户
	 *
	 * @param type
	 * @return
	 */
	public static String  listAccounts(String type) {
		CoinInterfaceUtil util=new   CoinInterfaceUtil();
		String ss="";
		if(util.getIsOpen().equals("0")){
			String url=PropertiesUtils.APP.getProperty("app.coinip");
			ss=HttpsRequest.post(url+"/coin/listAccounts?type="+type);
		}else{
			ss="{code=18,msg=222222}";
		}
		return ss;
	}


	/**
	 *  从一个币转到 一个币账户
	 *
	 * @param account  发款人
	 * @param address  收款人
	 * @param amount  数量
	 * @param type  类型
	 * @return
	 *
	 */
	public static Map<String, Object>  getAllUsers(String type) {
		Map<String, Object> map = new HashMap<String, Object>();
		CoinInterfaceUtil util=new  CoinInterfaceUtil();
		String ss="";
		if(util.getIsOpen().equals("0")){
			String url=PropertiesUtils.APP.getProperty("app.coinip");
			ss=HttpsRequest.post(url+"/coin/getAllUsers?type="+type);
			if("" != ss){
				map = StringUtil.str2map(ss);
				return map;
			}
		}
		return map;
	}








	/**
	 * 查询钱包接口是否关闭
	 * <p> TODO</p>
	 * @author:         Zhang Xiaofang
	 * @param:    @return
	 * @return: String
	 * @Date :          2016年9月5日 下午4:59:37
	 * @throws:
	 */
	 public   String getIsOpen(){
		 	String val="";
		 	RedisService redisService=(RedisService)ContextUtil.getBean("redisService");
		 	String string= redisService.get("configCache:interfaceConfig");
			JSONArray obj=JSON.parseArray(string);
			for(Object o:obj){
			JSONObject	 oo=JSON.parseObject(o.toString());
		//	logger.error(oo);
			if(oo.getString("configkey").equals("purseInterface")){
				val=oo.getString("value");
			}
			}
	    	return val;
	    }


		public static String  listByCustomerName(String type,String customerName) {
			CoinInterfaceUtil util=new   CoinInterfaceUtil();
			String ss="";
			if(util.getIsOpen().equals("0")){
				String url=PropertiesUtils.APP.getProperty("app.coinip");
				//logger.error("====2222");
				ss=HttpsRequest.post(url+"/coin/list?type="+type+"&userName="+customerName);


				//logger.error("====222266");

			}else{

				ss="[]";
			}

			return ss;
		}










	 public static void main(String[] args) {

		String	ss=HttpsRequest.post("https://www.huanqiushuzi.com/coin/coin/allList?type=dsc");
       logger.error(ss);
       String	s=HttpsRequest.post("http://www.1haohb.com/coin/coin/allList?type=dsc");
       logger.error(s);
	 }
}
