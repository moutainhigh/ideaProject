
package hry.manage.remote;

import hry.bean.JsonResult;
import hry.manage.remote.model.*;
import hry.manage.remote.model.base.FrontPage;
import hry.manage.remote.model.c2c.C2cOrder;
import hry.manage.remote.model.commend.CommendMoney;
import hry.manage.remote.model.commend.CommendUser;
import hry.trade.redis.model.EntrustTrade;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface RemoteManageService {

	/**
	 * 查询交易区
	 * @return
	 */
	List<String> getTredeArea ();

	/**
	 * 获取可用币的数量
	 */
	BigDecimal getHotMoney(String customerId, String coinCode);

	/**
	 * 更换邮箱 验证邮箱和密码
	 * @param id
	 * @param email
	 * @param loginPwd
	 * @return
	 */
	RemoteResult swapEmail(Long id, String email, String loginPwd);

	/**
	 * 通过用户校验密码是否正确
	 * @param username
	 * @param oldPassWord
	 * @return
	 */
	RemoteResult setVailPassWord(String username, String oldPassWord);

	/**
	 * 查询手机号是否存在
	 * @param telphone
	 * @return
	 */
	RemoteResult selectPhone(String telphone);

	/**
	 * 更新用户人脸待征码
	 * @param customerId
	 * @param feature
	 * @return
	 */
	RemoteResult updateFeature(String customerId,String feature);

	/**
	 * 查询登录用户的人民币账户id和币账户id
	 * @param id
	 * @return
	 */
	Map<String, Long> findAllAccountId(String id);

	/**
	 * 用户的委托单记录
	 * @param customerId
	 * @return
	 */
	List<Map<String, List<EntrustTrade>>> findExEntrustBycust(Long customerId);

	/**
	 * 下单
	 * @param exEntrust
	 * @return
	 */
	String[] addEntrust(EntrustTrade exEntrust);

	/**
	 * 通过手机号获取User对象
	 * @param tel
	 * @return
	 */
	User selectByTel(String tel);

	/**
	 * 撤销委托
	 * @param entrustTrade
	 * @return
	 */
	String[] cancelExEntrust(EntrustTrade entrustTrade);

	/**
	 * 查看用户是否平账
	 * @param customerId
	 * @return
	 */
	String[] checkPing(Long customerId);

	/**
	 * 查询c2c订单
	 * @param params
	 * @return
	 */
	FrontPage c2clist(Map<String, String> params);

	/**
	 * 获得c2c最新10条记录
	 * @param coinCode
	 */
	List<C2cOrder> c2c10Order(Long customerId, String coinCode);

	/**
	 * 获得c2c订单汇款信息
	 * @param transactionNum
	 * @return
	 */
	String getC2cOrderDetail(String transactionNum);

	/**
	 * 查找开启提币的币种
	 * @return
	 */
	List<String> findOpenTibi();







































	// ==================================分割线===========================================================================================================

	/**
	 * 登录接口
	 * @param username 用户名
	 * @param password 密码
	 * @return RemoteResult.obj 返回user
	 */
	RemoteResult login(String username, String password, String uuid);

	/**
	 * 验证白名单中是否存在该用户，根据用户id和登录ip判断
	 * @param userId
	 * @param ip
	 * @return
	 */
	boolean isFirstLoginByComplexPwd(Long userId, String ip);

	/**
	 * 添加用户到白名单
	 * @param id
	 * @param ip
	 */
	void addWhiteList(Long id, String ip);

	/**
	 * 保存用户第一次登录的ip
	 * @param mobile
	 * @param messIp
	 * @return
	 */
	RemoteResult savaIp(String mobile, String messIp);

	/**
	 * 查询代理商账户
	 * @return
	 */
	RemoteResult selectAgent(String referralCode);

	/**
	 * 邮箱注册接口
	 * @param email
	 * @param password
	 * @return
	 */
	RemoteResult regist(String email, String password, String referralCode, String language);

	/**
	 * 获取邮件注册码
	 * @param email
	 * @return
	 */
	String getEmailCode(String email);

	/**
	 * 激活注册账号
	 * @param code
	 * @return
	 */
	RemoteResult activation(String code);

	/**
	 * 手机注册
	 * @param mobile 手机号
	 * @param password 密码
	 * @param referralCode 邀请码
	 * @param country 国家
	 * @return
	 */
	RemoteResult registMobile(String mobile, String password, String referralCode, String country, String string);

	/**
	 * 净资产，负债资产，总资产，比例
	 * @param customerId
	 * @return
	 */
	BigDecimal[] getSum(Long customerId);

	/**
	 * 查询币账户
	 * @param customerId
	 * @return
	 */
	List<CoinAccount> findCoinAccount(Long customerId);

	/**
	 * 查询该币种c2c交易价格
	 * @param coinCode
	 * @param priceType
	 * @return
	 */
	BigDecimal getC2cCoinPrice(String coinCode, String priceType);

	/**
	 * 是否借款
	 * @param customerId
	 * @return
	 */
	String[] checkLend(Long customerId);

	/**
	 * 查询当天卖出量
	 * @return
	 */
	long getTransactionNumOnTodayOfSell ();

	/**
	 * 生成c2c订单
	 * @param c2cOrder
	 * @return
	 */
	RemoteResult createC2cOrder(C2cOrder c2cOrder);

	/**
	 * 查询新的c2c买单列表
	 * @return
	 */
	List<C2cOrder> c2cNewBuyOrder();

	/**
	 * 查询新的c2c卖单列表
	 * @return
	 */
	List<C2cOrder> c2cNewSellOrder();

	/**
	 * 是否可以充币
	 * @param coinCode
	 * @return
	 */
	Boolean openChongbi(String coinCode);

	/**
	 * 获得钱包地址
	 * @param exdmaccountId
	 * @return
	 */
	RemoteResult getPublicKey(Long exdmaccountId);

	/**
	 * 生成钱包地址
	 * @param exdmaccountId
	 * @return
	 */
	RemoteResult createPublicKey(Long exdmaccountId);

	/**
	 * 查询委托记录
	 * @param params
	 * @return
	 */
	FrontPage findEntrust(Map<String, String> params);

	/**
	 * 查询邮箱是否存在
	 * @param email
	 * @return
	 */
	boolean hasEmail(String email);

	/**
	 * 邮箱是否激活
	 * @param email
	 * @return
	 */
	boolean isActive(String email);

	/**
	 * 设置邮件激活码
	 * @param email
	 * @param emailCode
	 * @return
	 */
	RemoteResult forget(String email, String emailCode);

	/**
	 * 手机重置密码
	 * @param country
	 * @param mobile
	 * @param newPassWord
	 * @return
	 */
	RemoteResult updatepwdMobile(String country, String mobile, String newPassWord);

	/**
	 * 验证邮箱验证码
	 * @param email
	 * @param code
	 * @return
	 */
	RemoteResult emailvail(String email, String code);

	/**
	 * 根据邮箱修改密码
	 * @param passwd
	 * @param username
	 * @return
	 */
	RemoteResult updatepwdemail(String passwd, String username);

	/**
	 * 查询当前用户今天取了多少钱
	 * @param customerId
	 * @param created
	 * @return
	 */
	BigDecimal getOldMoney(Long customerId, String created);

	/**
	 * 查询虚拟账户
	 * @param customerId
	 * @return
	 */
	AppAccountManage getAppAccountManage(Long customerId);

	/**
	 * 实名认证
	 * @param userCode
	 * @param trueName
	 * @param sex
	 * @param surname
	 * @param country
	 * @param cardType
	 * @param cardId
	 * @param pathImg
	 * @param type
	 * @param language
	 * @return
	 */
	public RemoteResult identityVerification(String userCode, String trueName, String sex, String surname, String country, String cardType, String cardId, String[] pathImg, String type, String language);

	/**
	 * 根据用户id查询用户信息
	 * @param customerId
	 * @return
	 */
	User selectByCustomerId(Long customerId);

	/**
	 * 查询personinfo信息
	 * @param userCode
	 * @return
	 */
	RemoteResult getPersonInfo(String userCode);

	/**
	 * 手机/邮箱登录接口
	 * @param username 用户名
	 * @param password 密码
	 * @return RemoteResult.obj 返回user
	 */
	RemoteResult login(String username, String password, String country, String uuid);

	/**
	 * 根据手机号查询用户是否存在
	 * @param mobile
	 * @return
	 */
	RemoteResult regphone(String mobile);

	/**
	 * 设置安全策略
	 * @param type
	 * @param valueOf
	 * @param customerId
	 * @return
	 */
	RemoteResult setSecurity(String type, Integer valueOf, Long customerId);

	/**
	 * 绑定邮箱
	 * @param email
	 * @param customerId
	 * @param loginPwd
	 * @return
	 */
	RemoteResult setEmail(String email, Long customerId, String loginPwd);

	/**
	 * 查询手机号是否存在
	 * @param country
	 * @param mobile
	 * @return 存在返回true
	 */
	boolean hasMobile(String country, String mobile, String ip);

	/**
	 * 保存手机认证
	 * @param mobile
	 * @param username
	 * @return
	 */
	RemoteResult setPhone(String mobile, String username);

	/**
	 * 重置登录密码
	 * @param username 用户名
	 * @param oldPassWord 旧密码
	 * @param newPassWord 新密码
	 * @return
	 */
	RemoteResult setpw(String username, String oldPassWord, String newPassWord);

	/**
	 * 设置谷歌认证
	 * @param userCode
	 * @param savedSecret
	 * @return
	 */
	RemoteResult sendgoogle(String userCode, String savedSecret);

	/**
	 * 通过userCode查询User对象
	 * @param userCode
	 * @return
	 */
	User getUserByUserCode(String userCode);

	/**
	 * 解除谷歌认证
	 * @param mobile
	 * @param savedSecret
	 * @return
	 */
	RemoteResult jcgoogle(String mobile, String savedSecret);

	/**
	 * 设置交易密码
	 * @param customerId 用户名id
	 * @param accountPassWord 交易密码
	 * @return
	 */
	RemoteResult setapw(Long customerId, String accountPassWord);

	/**
	 * 根据当前登录客户id修改改客户的是否启动平台币作为交易手续费开关
	 * @param customerId
	 * @param isOpen
	 * @return
	 */
	JsonResult updateIsOpenTransFeeState (Long customerId, String isOpen);

	/**
	 * 设置默认语种
	 * @param customerId
	 * @param isOpen
	 * @return
	 */
	JsonResult setCommonLanage(Long customerId, String isOpen);

	/**
	 * 查询邀请方式
	 * @param username
	 * @param property
	 * @return
	 */
	RemoteResult selectCommend(String username, String property);

	/**
	 * 计算一级推荐个数，二级推荐个数，三级推荐个数，四级推荐个数
	 * @param map
	 * @return
	 */
	List<CommendUser> culCommendCount(Map<String, String> map);

	/**
	 * 分页查看一级推荐个数，二级推荐个数，三级推荐个数，四级推荐个数
	 * @param params
	 * @return
	 */
	FrontPage findConmmendForntPageBySql(Map<String, String> params)throws Exception;

	/**
	 * 查询推荐返佣列表
	 * @param customerId
	 * @return
	 */
	List<CommendMoney> selectCommendfind(Long customerId);

	/**
	 * 获取普通资金账户信息
	 * @param coinCode
	 * @param customerId
	 * @return
	 */
	RemoteResult getAccountInfo(String coinCode, Long customerId);

	/**
	 * 查询账户信息
	 * @param id
	 * @return
	 */
	RemoteResult getById(String id);

	/**
	 * 银行卡分页查询
	 * @param params
	 * @return
	 */
	FrontPage getPersonalAssetById(Map<String, String> params);



	/**
	 * 给用户添加或减少经验值
	 * @param customerId  用户id
	 * @param account_type  交易类型（0101 锁仓奖励，0102 注册赠送，0201 锁仓扣除，0202 释放扣除 0203月末扣除10%）
	 * @param experience  经验值（无正负）
	 * @param money 持币数（交易类型属于 0102时 传null）
	 * @param upgradeNote  备注
	 * @return
	 */
	RemoteResult clearingExperience(Long customerId ,String account_type ,Long experience, BigDecimal money,String upgradeNote);

	/**
	 * 根据经验 计算等级
	 * @return experience
	 */
	RemoteResult countLevel(Long experience );
	/**
	 * 注册送经验

	 * @return
	 */
	RemoteResult registGetExperience(Long customerId);





















































	//=======================================分割线===========================================================================================================




	/**
	 * 发送邮件时保存到数据库
	 * @param mail
	 * @return
	 */
	public boolean insertMail(Mail mail);



	/**
	 *
	 */
	public boolean setc2cTransactionStatus(String transactionNum, int status2, String remark);



	/**
	 * 查询账户ID和虚拟币账户ID
	 *
	 * @param username
	 * @param coinCode
	 * @return
	 */
	public Map<String, Object> selectRechargeCoin(String username, String coinCode);



	/**
	 * 查所有交易记录
	 *
	 * @param params
	 * @return
	 */
	public FrontPage findAllEntrust(Map<String, String> params);





	public RemoteResult appgetAccountInfo(String cionCode, String fixCode);



	/**
	 * 获得币对应的保留小数
	 *
	 * @param exdmaccountId
	 * @return
	 */
	public Integer getKeepDecimalForCoin(String CoinCode);



	public RemoteResult getPublicKey(Long exdmaccountId, String cusId);

	public void cancelAllExEntrust(EntrustTrade entrustTrade);







	/**
	 * 退出
	 *
	 * @param user
	 * @return
	 */
	public RemoteResult logout(User user);


	/**
	 * 查询交易记录
	 *
	 * @param params
	 */
	public FrontPage findTrades(Map<String, String> params);



	/**
	 * 实名认证
	 *
	 * @param userCode
	 *            用户标识
	 * @param trueName
	 *            姓名
	 * @param country
	 *            国家
	 * @param cardType
	 *            证件类型
	 * @param cardId
	 *            证件号
	 * @return
	 */
	public RemoteResult realname(String userCode, String trueName, String surName, String country, String cardType, String cardId);







	/**
	 * 重置交易密码
	 *
	 * @param customerId
	 *            用户id
	 * @param passWord
	 *            登录密码
	 * @param accountPassWord
	 *            交易密码
	 * @return
	 */
	public RemoteResult resetapw(Long customerId, String passWord, String accountPassWord);



	/**
	 * 获取虚拟账户
	 *
	 * @param customerId
	 * @param currencyType
	 * @param website
	 * @return
	 */
	public AppAccountManage getByCustomerIdAndType(Long customerId, String currencyType, String website);

	/**
	 * 判断申请的该地区的代理是否已存在
	 *
	 */
	public boolean isAgentExist(String agentLevel, String provinceId, String cityId, String countyId);

	/**
	 * 获取一个我方账户对象
	 *
	 * @param website
	 * @param currencyType
	 * @param accountType
	 * @return
	 */
	public AppOurAccountManage getOurAccount(String website, String currencyType, String accountType);

	// start交易


	/**
	 * 下单检查
	 *
	 * @param exEntrust
	 * @return
	 */
	public String[] addEntrustCheck(EntrustTrade exEntrust);


	// end交易

	/**
	 * 查询发行的币种
	 *
	 * @return
	 */
	public RemoteResult finaCoins();

	/**
	 * 获取资金账户
	 *
	 * @param mobile
	 */
	public MyAccountTO myAccount(Long customerId);





	/**
	 * 修改密码
	 *
	 * @param pwd
	 * @param tel
	 * @return
	 */
	public RemoteResult updatepwd(String pwd, String tel);



	/**
	 * app端修改交易密码
	 *
	 * @param username
	 * @param accountPassWord
	 * @return
	 */
	public RemoteResult appresetapw(String username, String accountPassWord);

	/**
	 * 获取人民币虚拟账户
	 *
	 * @param id
	 * @return
	 */
	public CoinAccount getAppaccount(Long id);

	/**
	 * test
	 *
	 * @param id
	 * @return
	 */
	public RemoteResult testAppCustomer(String username);











	/**
	 * 设置手机认证
	 *
	 * @param country
	 *            国家
	 * @param mobile
	 *            手机
	 * @param customerId
	 *            客户id
	 * @return
	 */
	RemoteResult setPhone(String country, String mobile, Long customerId);






	RemoteResult offPhone(String mobile, String username);



	public void cancelCustAllExEntrust(EntrustTrade entrustTrade);







	public BigDecimal myBtcCount(Long customerId, String code);









	public List<commendCode> selectCommendRanking();





	/**
	 * 设置c2c订单 status2字段 的状态
	 *
	 * @param transactionNum
	 * @param status2
	 */
	public boolean setc2cTransactionStatus2(String transactionNum, int status2, String remark);





	/**
	 * 判断用户的币账户和资金账户是否有负数
	 *
	 * @param customerId
	 * @return
	 */
	public RemoteResult canTakeMoney(String customerId);





	User getUserInfoByUsername (String username);





	/**
	 * 新增登录安全
	 * @param id
	 * @param username
	 * @param ip
	 * @param browserName
	 * @return
	 */
	public RemoteResult insertLoginSafe(Long id, String ip, String browserName, String userName);

	/**
	 * 获取setGoogleState、setPhoneState
	 * @param id
	 * @return
	 */
	public User getCustomerById(Long id);














		 /**
		  * 根据手机号或者邮箱获取用户user
		  * @param
		  * @return
		  */
	  public User selectMobilePhoneOrEmail(String value);





	/**
	 * 增加登录次数
	 * @param id
	 */
	public void updateWriteList(Long id);





	/**
	 * 根据手机号修改密码
	 *
	 * @param password
	 * @param phone
	 * @return
	 */
	public RemoteResult updatepwdphone(String password, String phone);

	/**
	 * 手动注册用户
	 * @param s
	 * @return
	 */
	public RemoteResult registApi(String s);







	/**
	 * 资金密码是否正确
	 *
	 * @return
	 */
	public Boolean isAccountPassword(Long userId, String accountPassword);





	String[] addLendEntrust(EntrustTrade exEntrust);


	
	/**
	 * 统计用户佣金
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> countCommendMoney(Map<String,Object> map);
}
