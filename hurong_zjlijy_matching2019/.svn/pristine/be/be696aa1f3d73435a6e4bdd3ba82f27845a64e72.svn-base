/**
 * Copyright:
 *
 * @author: javalx
 * @version: V1.0
 * @Date: 2019-06-11 18:29:25
 */
package hry.social.manage.remote.mill;

import com.alibaba.fastjson.JSON;
import hry.core.shiro.PasswordHelper;
import hry.front.redis.model.UserRedis;
import hry.manage.remote.RemoteManageService;
import hry.manage.remote.model.RemoteResult;
import hry.manage.remote.model.User;
import hry.redis.common.dao.RedisUtil;
import hry.redis.common.utils.RedisService;
import hry.social.SocialPayConf.service.SocialPayConfService;
import hry.social.force.dao.SocialTerminableForceDao;
import hry.social.manage.remote.api.mill.RemoteMillService;
import hry.social.manage.remote.api.task.RemoteTaskService;
import hry.social.manage.remote.model.SocialPayConf.SocialPayConf;
import hry.social.manage.remote.model.force.SocialTerminableForce;
import hry.social.manage.remote.model.mill.SocialMillInfo;
import hry.social.manage.remote.model.mill.SocialMillTradeRecord;
import hry.social.manage.remote.model.traderecord.SocialCoinTradeRecord;
import hry.social.mill.dao.SocialMillTradeRecordDao;
import hry.social.mill.service.SocialMillInfoService;
import hry.social.mq.producer.service.MessageProducer;
import hry.social.mq.producer.service.NoticeMessage;
import hry.social.traderecord.service.SocialCoinTradeRecordService;
import hry.social.transaction.model.ExDmTransaction;
import hry.social.transaction.service.ExDmTransactionService;
import hry.trade.redis.model.Accountadd;
import hry.trade.redis.model.ExDigitalmoneyAccountRedis;
import hry.util.NoticeTemplateUtil;
import hry.util.QueryFilter;
import hry.util.UserRedisUtils;
import hry.util.date.DateUtil;
import hry.trade.model.AccountRemarkEnum;
import hry.util.idgenerate.IdGenerate;
import hry.util.idgenerate.NumConstant;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;

import javax.annotation.Resource;
import javax.persistence.Column;
import java.math.BigDecimal;
import java.util.*;

/**
 * <p> RemoteMillServiceImpl </p>
 *
 * @author: javalx
 * @Date :          2019-06-11 18:29:25
 */
public class RemoteMillServiceImpl implements RemoteMillService {

    @Resource
    SocialMillInfoService socialMillInfoService;
    @Resource
    RemoteManageService remoteManageService;
    @Resource
    RemoteTaskService remoteTaskService;
    @Resource
    private MessageProducer messageProducer;
    @Resource
    SocialCoinTradeRecordService socialCoinTradeRecordService;
    @Resource
    SocialPayConfService socialPayConfService;
    @Resource
    SocialMillTradeRecordDao socialMillTradeRecordDao;
    @Resource
    SocialTerminableForceDao socialTerminableForceDao;
    @Resource
    private ExDmTransactionService exDmTransactionService;
    @Resource
    private RedisService redisService;

    /**
     * 查询用户矿机支付信息
     *
     * @param customerId
     * @return
     */
    @Override
    public RemoteResult getMillPay(Long customerId) {
        RemoteResult remoteResult = new RemoteResult();
        QueryFilter qf = new QueryFilter(SocialPayConf.class);
        qf.addFilter("type=", 1);
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
     * 查看用户矿机购买信息
     *
     * @param customerId
     * @return
     */
    @Override
    public RemoteResult getUserMillInfo(Long customerId) {
        return socialMillInfoService.getMillInfo(customerId);
    }

    /**
     * 查看用户矿机购买记录
     *
     * @param customerId
     * @return
     */
    @Override
    public RemoteResult getUerMillList(Long customerId) {
        RemoteResult remoteResult = new RemoteResult();
        //奖励方式 1：奖励算力，2：奖励数币
        List<SocialMillTradeRecord> forceMillList = socialMillTradeRecordDao.getUerMillList(customerId, 1);
        List<SocialMillTradeRecord> coinMillList = socialMillTradeRecordDao.getUerMillList(customerId, 2);
        Map<String,List<SocialMillTradeRecord>> map = new HashMap<>();
        forceMillList = forceMillList == null ? new ArrayList<>() : forceMillList;
        coinMillList = coinMillList == null ? new ArrayList<>() : coinMillList;
        map.put("forceMillList", forceMillList);
        map.put("coinMillList", coinMillList);
        remoteResult.setSuccess(true);
        remoteResult.setObj(map);
        return remoteResult;
    }

    /**
     * 查看用户矿机购买信息
     *
     * @param rewardType
     * @return
     */
    @Override
    public RemoteResult getMillInfo(Integer rewardType) {
        RemoteResult remoteResult = new RemoteResult();
        QueryFilter qf = new QueryFilter(SocialMillInfo.class);
        qf.addFilter("rewardType=", rewardType);
        qf.addFilter("states=", 1);
        List<SocialMillInfo> socialMillInfos = socialMillInfoService.find(qf);
        remoteResult.setSuccess(true);
        remoteResult.setObj(socialMillInfos);
        return remoteResult;
    }

    /**
     * 查看用户购买数币矿机总奖励量
     *
     * @param customerId
     * @return
     */
    @Override
    public BigDecimal findMillCoin(Long customerId) {
        return socialMillInfoService.findMillCoin(customerId);
    }

    /**
     * 矿机购买
     *
     * @param millId     矿机ID
     * @param num        购买数量
     * @param cnyAmount  购买总价（CNY）
     * @param coinCode   支付币种
     * @param payNum     支付数
     * @param coinMarket 支付币市值
     * @param payAmount  支付币总金额（CNY）
     * @param accountPwd 资金密码
     * @param customerId 用户ID
     * @return
     */
    @Override
    public RemoteResult millSave(Long millId, BigDecimal num, BigDecimal cnyAmount, String coinCode, BigDecimal payNum, BigDecimal coinMarket, BigDecimal payAmount, String accountPwd, Long customerId) {
        RemoteResult remoteResult = new RemoteResult();
        SocialMillInfo socialMillInfo = socialMillInfoService.get(millId);
        BigDecimal price = socialMillInfo.getPrice();

        if (num.compareTo(BigDecimal.ZERO) <= 0) {
            remoteResult.setMsg("trade_num_lt0_error");
            remoteResult.setSuccess(false);
            return remoteResult;
        }
        if (payNum.compareTo(BigDecimal.ZERO) <= 0) {
            remoteResult.setMsg("pay_num_lt0_error");
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

        // 判断用户是否已经有虚拟账户
        RedisUtil<UserRedis> redisUtil = new RedisUtil<UserRedis>(UserRedis.class);
        UserRedis userRedis = redisUtil.get(customerId.toString());
        if (userRedis == null) {
            // 虚拟账户不存在
            remoteResult.setMsg("xnzh_no_exist");
            return remoteResult;
        }
        String personId = customerId.toString();
        String lockKey = "LOCK_ACCOUNT:" + personId + ":" + coinCode;
        boolean lock = redisService.lock(lockKey);
        if (lock) {
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
            //创建矿机购买记录
            SocialMillTradeRecord smtr = new SocialMillTradeRecord();
            Integer timeNum = socialMillInfo.getValidNum();
            Integer timeUnit = socialMillInfo.getTimeUnit();
            Integer rewardType = socialMillInfo.getRewardType();
            BigDecimal rewardNum = socialMillInfo.getRewardNum();
            String serialNum = socialMillInfo.getSerialNum();
            Date date = timeDeal(timeNum, timeUnit);
            smtr.setMillId(millId);
            smtr.setCustomerId(customerId);
            smtr.setCnyPrice(price);
            smtr.setNum(num);
            smtr.setCnyAmount(cnyAmount);
            smtr.setCoinCode(socialMillInfo.getCoinCode());
            smtr.setPayCoin(coinCode);
            smtr.setPayNum(payNum);
            smtr.setCoinMarket(coinMarket.toPlainString());
            smtr.setPayAmount(payAmount);
            smtr.setRewardNum(rewardNum);
            smtr.setRewardType(rewardType);
            smtr.setSerialNum(serialNum);
            smtr.setEndTime(date);
            smtr.setOrderNum(IdGenerate.transactionNum("AR"));
            socialMillTradeRecordDao.insertSelective(smtr);
            // 如果是算力矿机则添加相应的矿机算力值
            if (rewardType == 1) { //奖励方式 1：奖励算力，2：奖励数币
                SocialTerminableForce stf = new SocialTerminableForce();
                stf.setEndTime(date);
                stf.setCustomerId(customerId);
                stf.setType(2);
                stf.setTerminable(num.multiply(rewardNum));
                stf.setRemark(serialNum + "矿机" + num + "台");
                socialTerminableForceDao.insertSelective(stf);
                //统计算力
                remoteTaskService.calculateForce(customerId);
            }
            //生成转账结晶流水记录
            SocialCoinTradeRecord fsctr = new SocialCoinTradeRecord();
            //收支类型（0：收入 ， 1：支出）
            fsctr.setCategory(1);
            //类型（0：奖励，1：充币，2：提币，3：支付）
            //类型（0：奖励(收)，1：充币(收)，2：提币(支)，3：购买平台币(收、支)，4转账(收、支)， 5打赏(收、支)，6上链(支)，7捐赠(支)，8购买矿机(支)，9购买会员(支)）
            fsctr.setType(8);
            //币种
            fsctr.setCoinCode(coinCode);
            fsctr.setStates(2);
            fsctr.setSource("mill");
            fsctr.setNumber(payNum);
            fsctr.setCustomerId(customerId);
            //溯源标识(根据type和sourceNum可以找到具体的业务流水)
            fsctr.setSourceNum(smtr.getId().toString());
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
            exDmTransaction.setOptType(AccountRemarkEnum.TYPE508.getIndex());//TYPE500("数币奖励", 500), TYPE503("购买平台币", 503), TYPE505("朋友圈打赏", 505), TYPE506("许愿上链", 506), TYPE507("区块链公益捐赠", 507), TYPE508("购买矿机", 508), TYPE509("购买会员", 509);
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
            fromParamers.put(NoticeTemplateUtil.HRY_USER, user.getNickName());
            fromParamers.put(NoticeTemplateUtil.HRY_COIN_OUT, payNum + coinCode);
            NoticeMessage noticeMessage = new NoticeMessage(user,NoticeTemplateUtil.MILL_TRADE_SUCCESS, fromParamers);
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
     * 计算到期时间
     *
     * @param aging 时效数
     * @param unit  时效单位（0：年，1：月，2：周，3：日） 时效单位 1：天，2：月，3年
     * @return
     */
    Date timeDeal(int aging, int unit) {
        int calendarUN = 0;
        switch (unit) {
            case 3:
                calendarUN = Calendar.YEAR;
                break;
            case 2:
                calendarUN = Calendar.MONTH;
                break;
            case 4:
                calendarUN = Calendar.DATE;
                aging = aging * 7;
                break;
            case 1:
                calendarUN = Calendar.DATE;
                break;
            default:
                break;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());    //设置起时间
        cal.add(calendarUN, aging);
        Date time = cal.getTime();
        System.out.println("输出::" + time);
        return time;
    }

}
