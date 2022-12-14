/**
 * Copyright:   
 * @author:      houz
 * @version:     V1.0 
 * @Date:        2019-01-14 20:56:02 
 */
package hry.ico.service.impl;


import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import hry.bean.FrontPage;
import hry.bean.JsonResult;
import hry.bean.ObjectUtil;
import hry.calculate.util.DateUtil;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.exchange.product.model.ExProduct;
import hry.exchange.product.service.ExProductService;
import hry.front.redis.model.UserRedis;
import hry.ico.dao.IcoDividendRecordDao;
import hry.ico.model.*;
import hry.ico.remote.RemoteIcoService;
import hry.ico.remote.model.IcoRulesConfig;
import hry.ico.remote.model.RemoteIcoCustomerLevel;
import hry.ico.remote.model.RemoteIcoDividendRecord;
import hry.ico.remote.model.RulesConfig;
import hry.ico.service.*;
import hry.mq.producer.service.MessageProducer;
import hry.redis.common.dao.RedisUtil;
import hry.redis.common.utils.RedisService;
import hry.trade.redis.model.Accountadd;
import hry.trade.redis.model.ExDigitalmoneyAccountRedis;
import hry.util.QueryFilter;
import hry.util.SpringUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import util.BigDecimalUtil;
import util.SendHttpUtil;
import util.ToAccountUtil;
import util.UserRedisUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p> IcoDividendRecordService </p>
 * @author:         houz
 * @Date :          2019-01-14 20:56:02  
 */
@Service("icoDividendRecordService")
public class IcoDividendRecordServiceImpl extends BaseServiceImpl<IcoDividendRecord, Long> implements IcoDividendRecordService {
	private  static String url="https://www.bitngel.com/api/user/findSumTransactionFee";
	private  static String accesskey="1db7982f19f5b404ea9c95ea3df029ec";
	@Resource
	private ExProductService exProductService;
	@Resource
	private IcoRuleDetailedService icoRuleDetailedService;
	@Resource
	private RemoteIcoService remoteIcoService;
	@Resource
	private IcoUpgradeConfigService icoUpgradeConfigService;
	@Resource
	private MessageProducer messageProducer;
	@Resource
	private IcoTransactionRecordService icoTransactionRecordService;
	@Resource
	private IcoDividendRecordService icoDividendRecordService;
	@Resource
	private IcoAccountService icoAccountService;



	@Resource(name="icoDividendRecordDao")
	@Override
	public void setDao(BaseDao<IcoDividendRecord, Long> dao) {
		super.dao = dao;
	}

	//????????????????????????
	@Override
	public FrontPage queryDividendRecord(Map<String ,String> hashMap){

		String offset=hashMap.get("page")==null?"0":hashMap.get("page");
		String limit=hashMap.get("pageSize")==null?"10":hashMap.get("pageSize");
		if(Integer.parseInt(limit)>100){
			limit="10";
		}
		Page<IcoDividendRecord> page = PageHelper.startPage(Integer.parseInt(offset), Integer.parseInt(limit));
		List<IcoDividendRecord> icoExperiences = ((IcoDividendRecordDao) dao).queryDividendRecord(hashMap);
		List<RemoteIcoDividendRecord> result= ObjectUtil.beanList(icoExperiences,RemoteIcoDividendRecord.class);
		return new FrontPage(result, page.getTotal(), page.getPages(), page.getPageSize());

	}




	/**
	 * ????????????
	 */
	@Override
	public void issueDividend(){
		System.out.println("?????????????????????  "+ new Date());


		JsonResult getPlatformRule=remoteIcoService.getPlatformRule(RulesConfig.RulesICOKey);//Ico??????
		if(getPlatformRule.getSuccess()){
			IcoRulesConfig rulesConfig= ObjectUtil.bean2bean(getPlatformRule.getObj(),IcoRulesConfig.class);
			String startTime= rulesConfig.getIcoLockStartTime();//????????????
			String endTime= rulesConfig.getIcoLockEndTime();//????????????
			boolean fl= DateUtil.isDateInterval(startTime,endTime);
			//???ico?????????????????????  todo   if(!fl)
			//if(fl){
			if(!fl){
				//?????? POST ??????
				String md5Key=DigestUtils.md5DigestAsHex(accesskey.toString().getBytes());
				String param="accesskey="+md5Key;
				String sr= SendHttpUtil.sendPost(url, param);
				System.out.println("result???"+sr);
				//????????????
				//sr="{\"success\":true,\"msg\":\"\",\"obj\":{\"ETH\": 10000},\"code\":\"10000\"}";
				//String sr="{\"success\":true,\"msg\":\"\",\"obj\":{},\"code\":\"10000\"}";
				JsonResult result = JSON.parseObject(sr, JsonResult.class);
				String code = result.getCode();
				if(code.equals("10000")){
					Map<String,Object> map = (Map<String,Object> ) result.getObj();
					for(String key:map.keySet()){
						System.out.println("keyset???key:"+key+"   value: "+map.get(key));
						//???????????????????????????
						BigDecimal value= new BigDecimal(map.get(key).toString());

						//????????????????????????
						QueryFilter queryFilterExProduct = new QueryFilter(ExProduct.class);
						queryFilterExProduct.addFilter("issueState=",1);
						queryFilterExProduct.addFilter("coinCode=",key);
						List<ExProduct> exProducts = exProductService.find(queryFilterExProduct);
						if(null != exProducts && exProducts.size() > 0){
							//?????????????????????
							ExProduct exProduct = exProducts.get(0);
							//???????????????????????????
							List<IcoAccount> all1 = icoAccountService.findAll();
							if(null != all1 && all1.size() > 0){

								RedisService redisService = (RedisService) SpringUtil.getBean("redisService");
								//?????????????????????
								String str = redisService.get(RulesConfig.PLATFORM_NUMBER);
								if(str==null||str.equals("")){
									str = "0";
								}
								//??????????????????
								List<IcoRuleDetailed> all = icoRuleDetailedService.findAll();
								BigDecimal totalCirculation=new BigDecimal("0");//????????????
								if(null != all && all.size() > 0){
									for (int i=0;i<all.size();i++){
										IcoRuleDetailed icoRuleDetailed = all.get(i);
										if(icoRuleDetailed.getState().equals("1")){   //?????????
											totalCirculation=totalCirculation.add(icoRuleDetailed.getTotalNum());
										}
									}
								}
								//??????????????????
								BigDecimal totalSales = totalCirculation.subtract(new BigDecimal(str));
								//???????????????
								JsonResult platformRule = remoteIcoService.getPlatformRule(RulesConfig.RulesCoinKey);
								if (platformRule.getSuccess()) {
									IcoRulesConfig icoRulesConfig = (IcoRulesConfig) platformRule.getObj();
									totalSales=totalSales.add(new BigDecimal(icoRulesConfig.getSetValue()));
								}
								//????????????????????????
								List<Accountadd>listLock=new ArrayList<>();


								for (int i=0;i<all1.size();i++){
									//IcoBuyOrder icoBuyOrder = all1.get(i);
									IcoAccount icoAccount = all1.get(i);

									//??????????????????
									int size1 = totalSales.compareTo(new BigDecimal("0"));//????????????
									int size2 = value.compareTo(new BigDecimal("0"));//???????????????
									if(size1>0 && size2>0){
										//??????????????????
										JsonResult jsonResult = remoteIcoService.seeCustomerLevelAccount(icoAccount.getCustomerId());
										//????????????
										BigDecimal additionRatio=new BigDecimal("0");
										String additionRatioStr="";
										String customer_level="";
										if(jsonResult.getSuccess()){
											RemoteIcoCustomerLevel remoteIcoCustomerLevel = (RemoteIcoCustomerLevel)jsonResult.getObj();
											if(null!=remoteIcoCustomerLevel.getLevel_id()){
												customer_level=remoteIcoCustomerLevel.getGradeName();
												IcoUpgradeConfig icoUpgradeConfig = icoUpgradeConfigService.get(remoteIcoCustomerLevel.getLevel_id());
												additionRatio=additionRatio.add(new BigDecimal(icoUpgradeConfig.getAdditionRatio()));
												additionRatioStr=icoUpgradeConfig.getAdditionRatio();
											}
										}

										//???????????????=????????????/?????????????????????*(???????????????????????????/2)*???1+???????????????
										/*BigDecimal dividendRadix = icoAccount.getStorageMoney().divide(totalSales, 8, BigDecimal.ROUND_HALF_UP)
												.multiply(value.divide(new BigDecimal("2"), 8, BigDecimal.ROUND_HALF_UP))
												.multiply(new BigDecimal("100").add(additionRatio).divide(new BigDecimal("100"), 8, BigDecimal.ROUND_HALF_UP));
										dividendRadix = dividendRadix.setScale(8, BigDecimal.ROUND_HALF_UP);*/
										//?????????????????????????????????????????????  		??????????????? =?????????*??????????????????*(100+????????????)???/?????????????????????*2*100???
										BigDecimal multiply = icoAccount.getStorageMoney().multiply(value).multiply(new BigDecimal("100").add(additionRatio));
										BigDecimal multiply1 = totalSales.multiply(new BigDecimal("200"));
										BigDecimal dividendRadix=new BigDecimal("0");
										//?????????????????????
										int size = multiply1.compareTo(new BigDecimal("0"));
										if(size>0){
											dividendRadix = multiply.divide(multiply1, 8, BigDecimal.ROUND_HALF_UP);
										}
										//?????????????????????3?????????)
										BigDecimal divide = icoAccount.getStorageMoney().multiply(new BigDecimal("100")).divide(totalSales, 3, BigDecimal.ROUND_HALF_UP);

										String rebate_ratio=divide.toString()+"%";
										//???????????????????????????
										int size3 = dividendRadix.compareTo(new BigDecimal("0"));
										if(size3>0){
											//???????????????
											RedisUtil<UserRedis> redisUtil = new RedisUtil<UserRedis>(UserRedis.class);
											UserRedis userRedis = redisUtil.get(icoAccount.getCustomerId().toString());
											ExDigitalmoneyAccountRedis exAccount = UserRedisUtils.getAccount(userRedis.getDmAccountId(key).toString(),key);
											Long accountId = userRedis.getDmAccountId(exAccount.getCoinCode());
											Accountadd accountadd= ToAccountUtil.ncomeAssets(accountId,dividendRadix);//??????
											listLock.add(accountadd);

											//???????????????????????????

											//????????????????????????
											String orderNumber=String.valueOf(System.currentTimeMillis());//?????????
											// ?????????????????????????????????????????????
											if(null != userRedis){
												//????????????
												IcoTransactionRecord transactionRecord=new IcoTransactionRecord();
												transactionRecord.setCustomerId(icoAccount.getCustomerId());
												transactionRecord.setProjectNumber(orderNumber);//?????????
												transactionRecord.setCoinCode(exAccount.getCoinCode());
												transactionRecord.setColdMoney(exAccount.getColdMoney());
												transactionRecord.setHotMoney(exAccount.getHotMoney());
												transactionRecord.setType(31);// 11.??????12.??????21??????31??????32???????????? 41.??????42.??????51.??????52.??????
												transactionRecord.setState(202);//201.??????202.??????
												transactionRecord.setTransactionCount(dividendRadix);
												transactionRecord.setRemark("????????????");
												transactionRecord.setIsShow(1);

												//??????????????????
												IcoDividendRecord icoDividendRecord = new IcoDividendRecord();
												icoDividendRecord.setCustomer_id(icoAccount.getCustomerId());
												icoDividendRecord.setCustomer_level(customer_level);
												icoDividendRecord.setHedgeQuantity(icoAccount.getStorageMoney());//?????????
												icoDividendRecord.setGrossCommission(value);//???????????????
												icoDividendRecord.setCoid_id(exProduct.getId());//???id
												icoDividendRecord.setCoinCode(exProduct.getCoinCode());//?????????
												icoDividendRecord.setDividend_radix(dividendRadix);//????????????
												icoDividendRecord.setDividend_num(orderNumber);//?????????
												icoDividendRecord.setStatus("2");
												icoDividendRecord.setRebate_ratio(rebate_ratio);//????????????
												icoDividendRecord.setAdditionRatio(additionRatioStr);//????????????
												icoDividendRecordService.save(icoDividendRecord);
												transactionRecord.setForeignKey(icoDividendRecord.getId());
												icoTransactionRecordService.save(transactionRecord);
											}
										}
									}
								}
								//????????????
								messageProducer.toAccount(JSON.toJSONString(listLock));
								System.out.println("????????????");


							}



						}


					}
				}
			}

		}



	}

	public static void main(String[] args) {
		//?????? POST ??????
		//String url="https://www.bitngel.com/api/user/findSumTransactionFee";
		/*String url="http://172.16.20.62:6001/api/user/findSumTransactionFee";
		String accesskey="1db7982f19f5b404ea9c95ea3df029ec";
		String  string=DigestUtils.md5DigestAsHex(accesskey.toString().getBytes());
		String param="accesskey="+string;
		System.out.println(param);
		String sr= SendHttpUtil.sendPost(url, param);
		System.out.println(sr);*/

		String md5Key=DigestUtils.md5DigestAsHex(accesskey.toString().getBytes());
		String param="accesskey="+md5Key;
		String sr= SendHttpUtil.sendPost(url, param);
		System.out.println(sr);
	}
}
