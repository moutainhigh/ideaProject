/**
 * Copyright:
 *
 * @author: javalx
 * @version: V1.0
 * @Date: 2019-06-12 11:24:44
 */
package hry.social.manage.remote.vip;

import com.alibaba.fastjson.JSON;
import hry.core.shiro.PasswordHelper;
import hry.front.redis.model.UserRedis;
import hry.manage.remote.RemoteManageService;
import hry.manage.remote.model.RemoteResult;
import hry.manage.remote.model.User;
import hry.redis.common.dao.RedisUtil;
import hry.redis.common.utils.RedisService;
import hry.social.SocialPayConf.service.SocialPayConfService;
import hry.social.force.service.SocialForceCoinAdditionService;
import hry.social.manage.remote.api.task.RemoteTaskService;
import hry.social.manage.remote.api.vip.RemoteVipService;
import hry.social.manage.remote.model.SocialPayConf.SocialPayConf;
import hry.social.manage.remote.model.force.SocialForceCoinAddition;
import hry.social.manage.remote.model.traderecord.SocialCoinTradeRecord;
import hry.social.manage.remote.model.vip.SocialCustomerVip;
import hry.social.manage.remote.model.vip.SocialVipInfo;
import hry.social.manage.remote.model.vip.SocialVipTradeRecord;
import hry.social.mq.producer.service.MessageProducer;
import hry.social.mq.producer.service.NoticeMessage;
import hry.social.traderecord.service.SocialCoinTradeRecordService;
import hry.social.transaction.model.ExDmTransaction;
import hry.social.transaction.service.ExDmTransactionService;
import hry.social.vip.dao.SocialCustomerVipDao;
import hry.social.vip.service.SocialCustomerVipService;
import hry.social.vip.service.SocialVipInfoService;
import hry.social.vip.service.SocialVipTradeRecordService;
import hry.trade.redis.model.Accountadd;
import hry.trade.redis.model.ExDigitalmoneyAccountRedis;
import hry.util.NoticeTemplateUtil;
import hry.util.QueryFilter;
import hry.util.UserRedisUtils;
import hry.util.date.DateUtil;
import hry.trade.model.AccountRemarkEnum;
import hry.util.idgenerate.IdGenerate;
import hry.util.idgenerate.NumConstant;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.persistence.Column;
import java.math.BigDecimal;
import java.util.*;

/**
 * <p> RemoteVipServiceImpl </p>
 *
 * @author: javalx
 * @Date :          2019-06-12 11:24:44
 */
public class RemoteVipServiceImpl implements RemoteVipService {

    @Resource
    private SocialCustomerVipService socialCustomerVipService;

    @Resource
    private SocialVipInfoService socialVipInfoService;
    @Resource
    private RemoteManageService remoteManageService;
    @Resource
    private MessageProducer messageProducer;
    @Resource
    private SocialForceCoinAdditionService socialForceCoinAdditionService;
    @Resource
    private SocialVipTradeRecordService socialVipTradeRecordService;
    @Resource
    private SocialCoinTradeRecordService socialCoinTradeRecordService;
    @Resource
    private SocialPayConfService socialPayConfService;
    @Resource
    private SocialCustomerVipDao socialCustomerVipDao;
    @Resource
    RemoteTaskService remoteTaskService;
    @Resource
    private ExDmTransactionService exDmTransactionService;
    @Resource
    private RedisService redisService;

    /**
     * 查询用户会员支付信息
     *
     * @param customerId
     * @return
     */
    @Override
    public RemoteResult getVipPay(Long customerId) {
        RemoteResult remoteResult = new RemoteResult();
        QueryFilter qf = new QueryFilter(SocialPayConf.class);
        qf.addFilter("type=", 2);
        List<SocialPayConf> socialPayConfs = socialPayConfService.find(qf);
        for (SocialPayConf spc : socialPayConfs) {
            String coinCode = spc.getCoinCode();
            // 判断用户是否已经有虚拟账户
            RedisUtil<UserRedis> redisUtil = new RedisUtil<UserRedis>(UserRedis.class);
            UserRedis userRedis = redisUtil.get(customerId.toString());
            ExDigitalmoneyAccountRedis digAccount = UserRedisUtils.getAccount(userRedis.getDmAccountId(coinCode).toString(), coinCode);
            spc.setHotMoney(digAccount.getHotMoney());
        }
        remoteResult.setSuccess(true);
        remoteResult.setObj(socialPayConfs);
        return remoteResult;
    }

    /**
     * 查询用户会员信息
     *
     * @param customerId
     * @return
     */
    @Override
    public SocialCustomerVip getUserVipInfo(Long customerId) {
        return socialCustomerVipService.getUserVipInfo(customerId);
    }

    /**
     * 查询可购买的会员信息
     *
     * @param socialCustomerVip
     * @return
     */
    @Override
    public List<SocialVipInfo> getAvailableVip(SocialCustomerVip socialCustomerVip) {
        return socialVipInfoService.getAvailableVip(socialCustomerVip);
    }

    /**
     * 获取会员信息
     *
     * @param vipId
     * @return
     */
    @Override
    public SocialVipInfo vipOne(Long vipId) {
        QueryFilter qf = new QueryFilter(SocialVipInfo.class);
        qf.addFilter("id=", vipId);
        qf.addFilter("states=", 1);
        return socialVipInfoService.get(qf);
    }

    /**
     * 会员续购
     *
     * @param vipId
     * @param num
     * @param cnyAmount
     * @param coinCode
     * @param payNum
     * @param coinMarket
     * @param payAmount
     * @param accountPwd
     * @param customerId
     * @return
     */
    @Override
    @Transactional
    public RemoteResult vipRenewSave(Long vipId, BigDecimal num, BigDecimal cnyAmount, String coinCode, BigDecimal payNum, BigDecimal coinMarket, BigDecimal payAmount, String accountPwd, Long customerId) {
        RemoteResult remoteResult = new RemoteResult();
        SocialVipInfo socialVipInfo = vipOne(vipId);
        if (socialVipInfo == null) {
            remoteResult.setMsg("req_error");
            remoteResult.setSuccess(false);
            return remoteResult;
        }
        BigDecimal realPrice = socialVipInfo.getPrice().multiply(num);
        if (realPrice.compareTo(cnyAmount) > 0) {
            remoteResult.setMsg("req_error");
            remoteResult.setSuccess(false);
            return remoteResult;
        }
        if (cnyAmount.compareTo(BigDecimal.ZERO) <= 0) {
            remoteResult.setMsg("req_error");
            remoteResult.setSuccess(false);
            return remoteResult;
        }
        if (payNum.compareTo(BigDecimal.ZERO) <= 0) {
            remoteResult.setMsg("req_error");
            remoteResult.setSuccess(false);
            return remoteResult;
        }
        if (coinMarket.compareTo(BigDecimal.ZERO) <= 0) {
            remoteResult.setMsg("req_error");
            remoteResult.setSuccess(false);
            return remoteResult;
        }
        User user = remoteManageService.getUserByCustomerId(customerId);
        PasswordHelper passwordHelper = new PasswordHelper();
        String apw = passwordHelper.encryString(accountPwd, user.getSalt());
        if (!apw.equals(user.getAccountPassWord())) {
            return new RemoteResult().setMsg("acpwd_error");
        }
        String personId = customerId.toString();
        String lockKey = "LOCK_ACCOUNT:" + personId + ":" + coinCode;
        boolean lock = redisService.lock(lockKey);
        if (lock) {
            // 判断该用户是否已经有虚拟账户
            RedisUtil<UserRedis> redisUtil = new RedisUtil<UserRedis>(UserRedis.class);
            UserRedis userRedis = redisUtil.get(customerId.toString());
            if (userRedis == null) {
                // 虚拟账户不存在
                //释放锁
                redisService.unLock(lockKey);
                remoteResult.setMsg("xnzh_no_exist");
                return remoteResult;
            }
            ExDigitalmoneyAccountRedis digAccount = UserRedisUtils.getAccount(userRedis.getDmAccountId(coinCode).toString(), coinCode);
            if (digAccount == null) {
                // 虚拟账户不存在
                //释放锁
                redisService.unLock(lockKey);
                remoteResult.setMsg("xnzh_no_exist");
                return remoteResult;
            }
            if (digAccount.getHotMoney().compareTo(BigDecimal.ZERO) <= 0) {
                //释放锁
                redisService.unLock(lockKey);
                remoteResult.setMsg("xnzh_insufficient");
                return remoteResult;
            }
            if (digAccount.getColdMoney().compareTo(BigDecimal.ZERO) < 0) {
                //释放锁
                redisService.unLock(lockKey);
                remoteResult.setMsg("xnzh_abnormal_error");
                return remoteResult;
            }
            if (digAccount.getHotMoney().compareTo(payNum) < 0) {
                //释放锁
                redisService.unLock(lockKey);
                remoteResult.setMsg("xnzh_insufficient");
                return remoteResult;
            }
            SocialCustomerVip socialCustomerVip = getUserVipInfo(customerId);
            if (socialCustomerVip == null) {
                socialCustomerVipDao.deleteOverdue(customerId);
                //释放锁
                redisService.unLock(lockKey);
                remoteResult.setMsg("vip_expired");
                return remoteResult;
            }
            Date endTime = socialCustomerVip.getEndTime();
            Date oldEndTime = endTime;
            Long updateId = socialCustomerVip.getUpdateId();
            Calendar cal = Calendar.getInstance();
            cal.setTime(endTime);    //设置起时间
            cal.add(Calendar.YEAR, num.intValue());
            endTime = cal.getTime();
            socialCustomerVip.setEndTime(endTime);
            SocialForceCoinAddition sfca = socialForceCoinAdditionService.findVip(customerId);
            sfca.setRemark("vip_renew");
            sfca.setEndTime(endTime);
            socialForceCoinAdditionService.update(sfca);
            //保存会员续购记录
            SocialVipTradeRecord svtr = new SocialVipTradeRecord();
            svtr.setOrderNum(IdGenerate.transactionNum("RR"));//交易ID
            svtr.setCustomerId(customerId);//用户ID
            svtr.setAdditionRatio(socialCustomerVip.getAdditionRatio());//加成比例
            svtr.setOldVipId(vipId);//原会员ID
            svtr.setOldVipName(socialVipInfo.getVipName());//原会员等级
            svtr.setOldVipNum(socialVipInfo.getSerialNum()); //原会员编号
            svtr.setOldEndTime(oldEndTime);//原会员到期时间
            svtr.setNowVipId(vipId);//新会员ID
            svtr.setNowVipName(socialVipInfo.getVipName());//新会员等级
            svtr.setNowVipNum(socialVipInfo.getSerialNum());//新会员编号
            svtr.setNowEndTime(endTime);//到期时间
            svtr.setStates(2);//类型 1升级，2续购
            svtr.setPrice(cnyAmount);//总价格（CNY）
            svtr.setCoinCode(coinCode);//支付币种
            svtr.setPayNum(payNum);//支付数量（个）
            svtr.setCoinMarket(coinMarket.toPlainString());//支付时市值（CNY）
            svtr.setPayAmount(payAmount);//支付币总金额（CNY）
            socialVipTradeRecordService.save(svtr);
            socialCustomerVip.setRenewId(svtr.getId());
            socialCustomerVipDao.updateByPrimaryKeySelective(socialCustomerVip);
            //生成会员续购支付结晶流水记录
            SocialCoinTradeRecord fsctr = new SocialCoinTradeRecord();
            //收支类型（0：收入 ， 1：支出）
            fsctr.setCategory(1);
            //类型（0：奖励，1：充币，2：提币，3：支付）
            //类型（0：奖励(收)，1：充币(收)，2：提币(支)，3：购买平台币(收、支)，4转账(收、支)， 5打赏(收、支)，6上链(支)，7捐赠(支)，8购买矿机(支)，9购买会员(支)）
            fsctr.setType(9);
            //币种
            fsctr.setCoinCode(coinCode);
            fsctr.setStates(2);
            fsctr.setSource("vip_renew");
            fsctr.setNumber(payNum);
            fsctr.setCustomerId(customerId);
            //溯源标识(根据type和sourceNum可以找到具体的业务流水)
            fsctr.setSourceNum(svtr.getId().toString());
            socialCoinTradeRecordService.save(fsctr);
            // 交易所流水
            ExDmTransaction exDmTransaction = new ExDmTransaction();
            exDmTransaction.setCustomerId(customerId);
            String transactionNum = NumConstant.Ex_Dm_Transaction;
            exDmTransaction.setTransactionNum(IdGenerate.transactionNum(transactionNum));
            exDmTransaction.setAccountId(digAccount.getId());
            exDmTransaction.setTransactionType(2);  // 交易类型(1币收入 ，2币支出)
            exDmTransaction.setTransactionMoney(payNum);
            exDmTransaction.setCustomerName(user.getNickName());
            exDmTransaction.setStatus(2);   // 状态 1待审核 --2已完成 -- 3以否决
            exDmTransaction.setCoinCode(coinCode);
            exDmTransaction.setCurrencyType(user.getCommonLanguage());
            exDmTransaction.setFee(BigDecimal.ZERO);
            exDmTransaction.setOrderNo(fsctr.getId().toString());
            exDmTransaction.setOptType(AccountRemarkEnum.TYPE509.getIndex());//TYPE500("数币奖励", 500), TYPE503("购买平台币", 503), TYPE505("朋友圈打赏", 505), TYPE506("许愿上链", 506), TYPE507("区块链公益捐赠", 507), TYPE508("购买矿机", 508), TYPE509("购买会员", 509);
            // 保存订单
            exDmTransactionService.save(exDmTransaction);
            // 发送提币mq通知缓存
            Accountadd accountAdd = new Accountadd();
            accountAdd.setAccountId(digAccount.getId());
            accountAdd.setMoney(payNum.multiply(new BigDecimal(-1)));
            accountAdd.setMonteyType(1);
            accountAdd.setAcccountType(1);
            accountAdd.setRemarks(26);
            accountAdd.setTransactionNum(exDmTransaction.getTransactionNum());
            List<Accountadd> list = new ArrayList<Accountadd>();
            list.add(accountAdd);
            messageProducer.toAccount(JSON.toJSONString(list));
            //发站内消息
            Map<String,String> fromParamers = new HashMap<>();
            fromParamers.put(NoticeTemplateUtil.HRY_TIME, DateUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
            fromParamers.put(NoticeTemplateUtil.HRY_VIP_NUM, socialVipInfo.getVipName());
            fromParamers.put(NoticeTemplateUtil.HRY_VIP_TIME, DateUtil.dateToString(endTime, "yyyy-MM-dd HH:mm:ss"));
            fromParamers.put(NoticeTemplateUtil.HRY_USER, user.getNickName());
            fromParamers.put(NoticeTemplateUtil.HRY_COIN_OUT, payNum + coinCode);
            NoticeMessage noticeMessage = new NoticeMessage(user,NoticeTemplateUtil.VIP_RENEW_SUCCESS, fromParamers);
            messageProducer.toSendMsg(JSON.toJSONString(noticeMessage));
            //释放锁
            redisService.unLock(lockKey);
            remoteResult.setSuccess(true);
            return remoteResult;
        }else {
            remoteResult.setMsg("sys_busy_error");
            return remoteResult;
        }
    }

    /**
     * 会员升级
     *
     * @param oldvipId
     * @param nowvipId
     * @param updatePrice
     * @param coinCode
     * @param payNum
     * @param coinMarket
     * @param payAmount
     * @param accountPwd
     * @param customerId
     * @return
     */
    @Override
    public RemoteResult vipUpdateSave(Long oldvipId, Long nowvipId, BigDecimal updatePrice, String coinCode, BigDecimal payNum, BigDecimal coinMarket, BigDecimal payAmount, String accountPwd, Long customerId) {
        RemoteResult remoteResult = new RemoteResult();

        BigDecimal paysum = coinMarket.multiply(payNum);
        if (updatePrice.compareTo(BigDecimal.ZERO) <= 0) {
            remoteResult.setMsg("req_error");
            remoteResult.setSuccess(false);
            return remoteResult;
        }
        if (payNum.compareTo(BigDecimal.ZERO) <= 0) {
            remoteResult.setMsg("req_error");
            remoteResult.setSuccess(false);
            return remoteResult;
        }
        if (coinMarket.compareTo(BigDecimal.ZERO) <= 0) {
            remoteResult.setMsg("req_error");
            remoteResult.setSuccess(false);
            return remoteResult;
        }
        User user = remoteManageService.getUserByCustomerId(customerId);
        PasswordHelper passwordHelper = new PasswordHelper();
        String apw = passwordHelper.encryString(accountPwd, user.getSalt());
        if (!apw.equals(user.getAccountPassWord())) {
            return new RemoteResult().setMsg("acpwd_error");
        }
        SocialVipInfo nowvip = vipOne(nowvipId);
        int remaining = nowvip.getTimeNum().intValue() * 365;
        SocialCustomerVip customerVip = null;
        BigDecimal oldvipPrice = BigDecimal.ZERO;
        BigDecimal newvipPrice = nowvip.getPrice();
        if (null != oldvipId) {
            SocialVipInfo oldvip = vipOne(oldvipId);
            oldvipPrice = oldvip.getPrice();
            customerVip = getUserVipInfo(customerId);
            remaining = Integer.parseInt(customerVip.getExpire());
        }
//        BigDecimal per = new BigDecimal(remaining).divide(new BigDecimal(365), 3, BigDecimal.ROUND_HALF_UP);
//        BigDecimal subPrice = newvipPrice.subtract(oldvipPrice);
//        BigDecimal realPrice = subPrice.multiply(per).setScale(2, BigDecimal.ROUND_HALF_UP);
//        BigDecimal updatePrice = svur.getUpdatePrice();
//        BigDecimal realSub = realPrice.subtract(updatePrice);
//        if (realSub.abs().compareTo(BigDecimal.ONE) >= 0) {
//            return remoteResult.setMsg("req_error");
//        }
        // 判断该用户是否已经有虚拟账户
        RedisUtil<UserRedis> redisUtil = new RedisUtil<UserRedis>(UserRedis.class);
        UserRedis userRedis = redisUtil.get(customerId.toString());
        String personId = customerId.toString();
        String lockKey = "LOCK_ACCOUNT:" + personId + ":" + coinCode;
        boolean lock = redisService.lock(lockKey);
        if (lock) {
            if (userRedis == null) {
                // 虚拟账户不存在
                //释放锁
                redisService.unLock(lockKey);
                remoteResult.setMsg("xnzh_no_exist");
                return remoteResult;
            }
            ExDigitalmoneyAccountRedis digAccount = UserRedisUtils.getAccount(userRedis.getDmAccountId(coinCode).toString(), coinCode);
            if (digAccount == null) {
                // 虚拟账户不存在
                //释放锁
                redisService.unLock(lockKey);
                remoteResult.setMsg("xnzh_no_exist");
                return remoteResult;
            }
            if (digAccount.getHotMoney().compareTo(BigDecimal.ZERO) <= 0) {
                //释放锁
                redisService.unLock(lockKey);
                remoteResult.setMsg("xnzh_insufficient");
                return remoteResult;
            }
            if (digAccount.getColdMoney().compareTo(BigDecimal.ZERO) < 0) {
                //释放锁
                redisService.unLock(lockKey);
                remoteResult.setMsg("xnzh_abnormal_error");
                return remoteResult;
            }
            if (digAccount.getHotMoney().compareTo(payNum) < 0) {
                //释放锁
                redisService.unLock(lockKey);
                remoteResult.setMsg("xnzh_insufficient");
                return remoteResult;
            }
            Date endTime = null;
            //svtr
            SocialVipTradeRecord svtr = new SocialVipTradeRecord();
            svtr.setOrderNum(IdGenerate.transactionNum("UR"));//交易ID
            svtr.setCustomerId(customerId);//用户ID
            svtr.setAdditionRatio(nowvip.getAdditionRatio());//加成比例
            svtr.setNowVipId(nowvipId);//新会员ID
            svtr.setNowVipName(nowvip.getVipName());//新会员等级
            svtr.setNowVipNum(nowvip.getSerialNum());//新会员编号
            svtr.setStates(2);//类型 1升级，2续购
            svtr.setPrice(updatePrice);//总价格（CNY）
            svtr.setCoinCode(coinCode);//支付币种
            svtr.setPayNum(payNum);//支付数量（个）
            svtr.setCoinMarket(coinMarket.toPlainString());//支付时市值（CNY）
            svtr.setPayAmount(payAmount);//支付币总金额（CNY）
            if (customerVip != null && customerVip.getVipId() != null) {
                svtr.setOldVipId(customerVip.getVipId());//原会员ID
                svtr.setOldVipName(customerVip.getVipName());//原会员等级
                svtr.setOldVipNum(customerVip.getSerialNum()); //原会员编号
                svtr.setOldEndTime(customerVip.getEndTime());//原会员到期时间
                Long updateId = customerVip.getUpdateId();
                endTime = customerVip.getEndTime();
                svtr.setNowEndTime(endTime);//到期时间
            } else {
                Calendar cal = Calendar.getInstance();
                cal.setTime(new Date());    //设置起时间
                cal.add(Calendar.YEAR, nowvip.getTimeNum().intValue());
                endTime = cal.getTime();
                svtr.setNowEndTime(endTime);//到期时间
            }
            socialVipTradeRecordService.save(svtr);
            if (customerVip == null) {
                socialCustomerVipDao.deleteOverdue(customerId);
                customerVip = new SocialCustomerVip();
                customerVip.setCustomerId(customerId);//用户ID
                customerVip.setVipId(nowvipId);//会员ID
                customerVip.setAdditionRatio(nowvip.getAdditionRatio());//加成比例（%）
                customerVip.setStartTime(new Date()); //开始时间
                customerVip.setEndTime(endTime);//到期时间
                customerVip.setUpdateId(svtr.getId());//升级ID
                socialCustomerVipDao.insertSelective(customerVip);
                SocialForceCoinAddition sfca = new SocialForceCoinAddition();
                sfca.setCustomerId(customerId); //用户ID
                sfca.setAdditionType(2);//来源类型 1：任务，2：会员
                sfca.setAdditionType(0);//加成类型 0：算力&数币，1：算力，2：数币
                sfca.setAddition(nowvip.getAdditionRatio()); //加成值
                sfca.setEndTime(endTime);//到期时间
                sfca.setRemark("vip_update");//获取途径备注
                sfca.setSourceType(2);
                socialForceCoinAdditionService.save(sfca);
            } else {
                customerVip.setVipId(nowvipId);
                customerVip.setAdditionRatio(nowvip.getAdditionRatio());
                customerVip.setUpdateId(svtr.getId());
                socialCustomerVipDao.updateByPrimaryKeySelective(customerVip);
                SocialForceCoinAddition sfca = socialForceCoinAdditionService.findVip(customerId);
                sfca.setRemark("vip_update");
                sfca.setAddition(nowvip.getAdditionRatio());
                socialForceCoinAdditionService.update(sfca);
            }
            //统计算力
            remoteTaskService.calculateForce(customerId);
            //生成会员续购支付结晶流水记录
            SocialCoinTradeRecord fsctr = new SocialCoinTradeRecord();
            //收支类型（0：收入 ， 1：支出）
            fsctr.setCategory(1);
            //类型（0：奖励，1：充币，2：提币，3：支付）
            //类型（0：奖励(收)，1：充币(收)，2：提币(支)，3：购买平台币(收、支)，4转账(收、支)， 5打赏(收、支)，6上链(支)，7捐赠(支)，8购买矿机(支)，9购买会员(支)）
            fsctr.setType(9);
            //币种
            fsctr.setCoinCode(coinCode);
            fsctr.setStates(2);
            fsctr.setSource("vip_update");
            fsctr.setNumber(payNum);
            fsctr.setCustomerId(customerId);
            //溯源标识(根据type和sourceNum可以找到具体的业务流水)
            fsctr.setSourceNum(svtr.getId().toString());
            socialCoinTradeRecordService.save(fsctr);
            // 交易所流水
            ExDmTransaction exDmTransaction = new ExDmTransaction();
            exDmTransaction.setCustomerId(customerId);
            String transactionNum = NumConstant.Ex_Dm_Transaction;
            exDmTransaction.setTransactionNum(IdGenerate.transactionNum(transactionNum));
            exDmTransaction.setAccountId(digAccount.getId());
            exDmTransaction.setTransactionType(2);  // 交易类型(1币收入 ，2币支出)
            exDmTransaction.setTransactionMoney(payNum);
            exDmTransaction.setCustomerName(user.getNickName());
            exDmTransaction.setStatus(2);   // 状态 1待审核 --2已完成 -- 3以否决
            exDmTransaction.setCoinCode(coinCode);
            exDmTransaction.setCurrencyType(user.getCommonLanguage());
            exDmTransaction.setFee(BigDecimal.ZERO);
            exDmTransaction.setOrderNo(svtr.getOrderNum());
            exDmTransaction.setOptType(AccountRemarkEnum.TYPE509.getIndex());//TYPE500("数币奖励", 500), TYPE503("购买平台币", 503), TYPE505("朋友圈打赏", 505), TYPE506("许愿上链", 506), TYPE507("区块链公益捐赠", 507), TYPE508("购买矿机", 508), TYPE509("购买会员", 509);
            // 保存订单
            exDmTransactionService.save(exDmTransaction);
            // 发送提币mq通知缓存
            Accountadd accountAdd = new Accountadd();
            accountAdd.setAccountId(digAccount.getId());
            accountAdd.setMoney(payNum.multiply(new BigDecimal(-1)));
            accountAdd.setMonteyType(1);
            accountAdd.setAcccountType(1);
            accountAdd.setRemarks(26);
            accountAdd.setTransactionNum(exDmTransaction.getTransactionNum());
            List<Accountadd> list = new ArrayList<Accountadd>();
            list.add(accountAdd);
            messageProducer.toAccount(JSON.toJSONString(list));
            //发站内消息
            Map<String,String> fromParamers = new HashMap<>();
            fromParamers.put(NoticeTemplateUtil.HRY_TIME, DateUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
            fromParamers.put(NoticeTemplateUtil.HRY_VIP_NUM, nowvip.getVipName());
            fromParamers.put(NoticeTemplateUtil.HRY_COIN_OUT, payNum + coinCode);
            NoticeMessage noticeMessage = new NoticeMessage(user,NoticeTemplateUtil.VIP_UP_SUCCESS, fromParamers);
            messageProducer.toSendMsg(JSON.toJSONString(noticeMessage));
            //释放锁
            redisService.unLock(lockKey);
            remoteResult.setSuccess(true);
            return remoteResult;
        }else {
            remoteResult.setMsg("sys_busy_error");
            return remoteResult;
        }
    }
}
