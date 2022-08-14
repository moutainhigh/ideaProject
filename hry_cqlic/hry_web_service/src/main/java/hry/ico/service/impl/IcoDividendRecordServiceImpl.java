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

	//获取用户分红记录
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
	 * 发放分红
	 */
	@Override
	public void issueDividend(){
		System.out.println("开始发放分红：  "+ new Date());


		JsonResult getPlatformRule=remoteIcoService.getPlatformRule(RulesConfig.RulesICOKey);//Ico设置
		if(getPlatformRule.getSuccess()){
			IcoRulesConfig rulesConfig= ObjectUtil.bean2bean(getPlatformRule.getObj(),IcoRulesConfig.class);
			String startTime= rulesConfig.getIcoLockStartTime();//开始时间
			String endTime= rulesConfig.getIcoLockEndTime();//结束时间
			boolean fl= DateUtil.isDateInterval(startTime,endTime);
			//非ico阶段才发放分红  todo   if(!fl)
			//if(fl){
			if(!fl){
				//发送 POST 请求
				String md5Key=DigestUtils.md5DigestAsHex(accesskey.toString().getBytes());
				String param="accesskey="+md5Key;
				String sr= SendHttpUtil.sendPost(url, param);
				System.out.println("result："+sr);
				//分红模拟
				//sr="{\"success\":true,\"msg\":\"\",\"obj\":{\"ETH\": 10000},\"code\":\"10000\"}";
				//String sr="{\"success\":true,\"msg\":\"\",\"obj\":{},\"code\":\"10000\"}";
				JsonResult result = JSON.parseObject(sr, JsonResult.class);
				String code = result.getCode();
				if(code.equals("10000")){
					Map<String,Object> map = (Map<String,Object> ) result.getObj();
					for(String key:map.keySet()){
						System.out.println("keyset：key:"+key+"   value: "+map.get(key));
						//交易所手续费总利润
						BigDecimal value= new BigDecimal(map.get(key).toString());

						//获取系统上架币种
						QueryFilter queryFilterExProduct = new QueryFilter(ExProduct.class);
						queryFilterExProduct.addFilter("issueState=",1);
						queryFilterExProduct.addFilter("coinCode=",key);
						List<ExProduct> exProducts = exProductService.find(queryFilterExProduct);
						if(null != exProducts && exProducts.size() > 0){
							//这个币在上架中
							ExProduct exProduct = exProducts.get(0);
							//查找需要分红的用户
							List<IcoAccount> all1 = icoAccountService.findAll();
							if(null != all1 && all1.size() > 0){

								RedisService redisService = (RedisService) SpringUtil.getBean("redisService");
								//平台币剩余数量
								String str = redisService.get(RulesConfig.PLATFORM_NUMBER);
								if(str==null||str.equals("")){
									str = "0";
								}
								//获得发行数量
								List<IcoRuleDetailed> all = icoRuleDetailedService.findAll();
								BigDecimal totalCirculation=new BigDecimal("0");//发行总量
								if(null != all && all.size() > 0){
									for (int i=0;i<all.size();i++){
										IcoRuleDetailed icoRuleDetailed = all.get(i);
										if(icoRuleDetailed.getState().equals("1")){   //已发行
											totalCirculation=totalCirculation.add(icoRuleDetailed.getTotalNum());
										}
									}
								}
								//代币售出总量
								BigDecimal totalSales = totalCirculation.subtract(new BigDecimal(str));
								//添加设定值
								JsonResult platformRule = remoteIcoService.getPlatformRule(RulesConfig.RulesCoinKey);
								if (platformRule.getSuccess()) {
									IcoRulesConfig icoRulesConfig = (IcoRulesConfig) platformRule.getObj();
									totalSales=totalSales.add(new BigDecimal(icoRulesConfig.getSetValue()));
								}
								//修改用户金额队列
								List<Accountadd>listLock=new ArrayList<>();


								for (int i=0;i<all1.size();i++){
									//IcoBuyOrder icoBuyOrder = all1.get(i);
									IcoAccount icoAccount = all1.get(i);

									//计算用户分红
									int size1 = totalSales.compareTo(new BigDecimal("0"));//代币售出
									int size2 = value.compareTo(new BigDecimal("0"));//交易手续费
									if(size1>0 && size2>0){
										//查看用户等级
										JsonResult jsonResult = remoteIcoService.seeCustomerLevelAccount(icoAccount.getCustomerId());
										//加成比例
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

										//利润分红数=（购买数/代币售出总量）*(交易所手续费总利润/2)*（1+等级加成）
										/*BigDecimal dividendRadix = icoAccount.getStorageMoney().divide(totalSales, 8, BigDecimal.ROUND_HALF_UP)
												.multiply(value.divide(new BigDecimal("2"), 8, BigDecimal.ROUND_HALF_UP))
												.multiply(new BigDecimal("100").add(additionRatio).divide(new BigDecimal("100"), 8, BigDecimal.ROUND_HALF_UP));
										dividendRadix = dividendRadix.setScale(8, BigDecimal.ROUND_HALF_UP);*/
										//为了避免数据不精确吧公式改为：  		利润分红数 =（购买*交易所手续费*(100+等级加成)）/（代币售出总量*2*100）
										BigDecimal multiply = icoAccount.getStorageMoney().multiply(value).multiply(new BigDecimal("100").add(additionRatio));
										BigDecimal multiply1 = totalSales.multiply(new BigDecimal("200"));
										BigDecimal dividendRadix=new BigDecimal("0");
										//被除数需大于零
										int size = multiply1.compareTo(new BigDecimal("0"));
										if(size>0){
											dividendRadix = multiply.divide(multiply1, 8, BigDecimal.ROUND_HALF_UP);
										}
										//返佣占比（保留3位小数)
										BigDecimal divide = icoAccount.getStorageMoney().multiply(new BigDecimal("100")).divide(totalSales, 3, BigDecimal.ROUND_HALF_UP);

										String rebate_ratio=divide.toString()+"%";
										//判断分红是否大于零
										int size3 = dividendRadix.compareTo(new BigDecimal("0"));
										if(size3>0){
											//添加至队列
											RedisUtil<UserRedis> redisUtil = new RedisUtil<UserRedis>(UserRedis.class);
											UserRedis userRedis = redisUtil.get(icoAccount.getCustomerId().toString());
											ExDigitalmoneyAccountRedis exAccount = UserRedisUtils.getAccount(userRedis.getDmAccountId(key).toString(),key);
											Long accountId = userRedis.getDmAccountId(exAccount.getCoinCode());
											Accountadd accountadd= ToAccountUtil.ncomeAssets(accountId,dividendRadix);//收入
											listLock.add(accountadd);

											//发送消息给用户分红

											//创建资金流水记录
											String orderNumber=String.valueOf(System.currentTimeMillis());//流水号
											// 获取资金账户，判断资金账户余额
											if(null != userRedis){
												//交易流水
												IcoTransactionRecord transactionRecord=new IcoTransactionRecord();
												transactionRecord.setCustomerId(icoAccount.getCustomerId());
												transactionRecord.setProjectNumber(orderNumber);//流水号
												transactionRecord.setCoinCode(exAccount.getCoinCode());
												transactionRecord.setColdMoney(exAccount.getColdMoney());
												transactionRecord.setHotMoney(exAccount.getHotMoney());
												transactionRecord.setType(31);// 11.锁仓12.释放21转账31分红32推荐奖励 41.充币42.提币51.买入52.卖出
												transactionRecord.setState(202);//201.支出202.收入
												transactionRecord.setTransactionCount(dividendRadix);
												transactionRecord.setRemark("平台分红");
												transactionRecord.setIsShow(1);

												//创建分红记录
												IcoDividendRecord icoDividendRecord = new IcoDividendRecord();
												icoDividendRecord.setCustomer_id(icoAccount.getCustomerId());
												icoDividendRecord.setCustomer_level(customer_level);
												icoDividendRecord.setHedgeQuantity(icoAccount.getStorageMoney());//购买量
												icoDividendRecord.setGrossCommission(value);//手续费总量
												icoDividendRecord.setCoid_id(exProduct.getId());//币id
												icoDividendRecord.setCoinCode(exProduct.getCoinCode());//币代码
												icoDividendRecord.setDividend_radix(dividendRadix);//分红数量
												icoDividendRecord.setDividend_num(orderNumber);//流水号
												icoDividendRecord.setStatus("2");
												icoDividendRecord.setRebate_ratio(rebate_ratio);//返佣占比
												icoDividendRecord.setAdditionRatio(additionRatioStr);//加成比例
												icoDividendRecordService.save(icoDividendRecord);
												transactionRecord.setForeignKey(icoDividendRecord.getId());
												icoTransactionRecordService.save(transactionRecord);
											}
										}
									}
								}
								//提交队列
								messageProducer.toAccount(JSON.toJSONString(listLock));
								System.out.println("完美结束");


							}



						}


					}
				}
			}

		}



	}

	public static void main(String[] args) {
		//发送 POST 请求
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
