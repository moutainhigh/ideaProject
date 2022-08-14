/**
 * Copyright:
 *
 * @author: javalx
 * @version: V1.0
 * @Date: 2019-06-12 16:51:43
 */
package hry.social.manage.remote.transfer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import hry.core.shiro.PasswordHelper;
import hry.front.redis.model.UserRedis;
import hry.manage.remote.model.Coin;
import hry.manage.remote.model.RemoteResult;
import hry.manage.remote.model.User;
import hry.manage.remote.model.base.FrontPage;
import hry.redis.common.dao.RedisUtil;
import hry.redis.common.utils.RedisService;
import hry.social.manage.remote.api.transfer.RemoteTransferService;
import hry.social.manage.remote.model.traderecord.SocialCoinTradeRecord;
import hry.social.manage.remote.model.transfer.SocialTransferRecord;
import hry.social.mq.producer.service.MessageProducer;
import hry.social.mq.producer.service.NoticeMessage;
import hry.social.traderecord.service.SocialCoinTradeRecordService;
import hry.social.transaction.model.ExDmTransaction;
import hry.social.transaction.service.ExDmTransactionService;
import hry.social.transfer.service.SocialTransferRecordService;
import hry.trade.redis.model.Accountadd;
import hry.trade.redis.model.ExDigitalmoneyAccountRedis;
import hry.util.*;
import hry.util.date.DateUtil;
import hry.trade.model.AccountRemarkEnum;
import hry.util.idgenerate.IdGenerate;
import hry.util.idgenerate.NumConstant;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * <p> SocialTransferRecordService </p>
 *
 * @author: javalx
 * @Date :          2019-06-12 16:51:43
 */
public class RemoteTransferServiceImpl implements RemoteTransferService {

    private Logger logger = Logger.getLogger(MessageProducer.class);

    @Resource
    RedisService redisService;
    @Resource
    private MessageProducer messageProducer;
    @Resource
    SocialTransferRecordService socialTransferRecordService;
    @Resource
    SocialCoinTradeRecordService socialCoinTradeRecordService;
    @Resource
    ExDmTransactionService exDmTransactionService;

    /**
     * 转账
     *
     * @param fromUser
     * @param toUser
     * @param coinCode
     * @param transferNum
     * @param accountPwd
     * @param remark
     * @return
     */
    @Override
    @Transactional
    public RemoteResult transferCoin(User fromUser, User toUser, String coinCode, BigDecimal transferNum, String accountPwd, String remark) {
        RemoteResult remoteResult = new RemoteResult();
        Long fromCustomerId = fromUser.getCustomerId();
        Long toCustomerId = toUser.getCustomerId();
        // 获取 平台币
        String platCoin = BaseConfUtil.getConfigSingle("platCoin", "configCache:basefinanceConfig");
        if(!coinCode.equals(platCoin)){
            return remoteResult.setMsg("un_platCoin_transfer_error");
        }

        // 获取 币币交易实名认证
        String isTrade = BaseConfUtil.getConfigSingle("isTrade", "configCache:juheConfig");
        // 获取 币种语言
        String languageCode = BaseConfUtil.getConfigSingle("language_code", "configCache:financeConfig");
        Integer states = fromUser.getStates();
        int isReal = fromUser.getIsReal();
        if (isTrade != null && "0".equals(isTrade)) {
            if (isReal != 1 || states.intValue() != 2) {
                return remoteResult.setMsg("un_realname_error");
            }
        }
        //验证密码
        String accountPW = fromUser.getAccountPassWord();
        PasswordHelper passwordHelper = new PasswordHelper();
        String apw = passwordHelper.encryString(accountPwd, fromUser.getSalt());
        if (!apw.equals(accountPW)) {
            return new RemoteResult().setMsg("acpwd_error");
        }
        // 判断用户是否已经有虚拟账户
        RedisUtil<UserRedis> redisUtil = new RedisUtil<UserRedis>(UserRedis.class);
        String fromId = fromUser.getCustomerId().toString();
        UserRedis fuserRedis = redisUtil.get(fromId);
        if (fuserRedis == null) {
            // 转出虚拟账户不存在
            remoteResult.setMsg("xnzh_no_exist");
            return remoteResult;
        }
        UserRedis tuserRedis = redisUtil.get(toUser.getCustomerId().toString());
        if (tuserRedis == null) {
            // 转入虚拟账户不存在
            remoteResult.setMsg("xnzh_no_exist");
            return remoteResult;
        }
        Map<String,String> productMap = redisService.getMap(SocialUtil.PRODUCT_KEY);
        if (StringUtils.isEmpty(productMap)) {
            System.out.println("平台币配置错误");
            remoteResult.setMsg("xnzh_no_exist");
            return remoteResult;
        }
        String coinStr = productMap.get(coinCode);
        if (StringUtils.isEmpty(coinStr)) {
            System.out.println("平台币配置错误");
            remoteResult.setMsg("xnzh_no_exist");
            return remoteResult;
        }
        Integer keepNum = 8;
        Coin coin = JSONArray.parseObject(coinStr, Coin.class);
        if (coin != null && coin.getKeepDecimalForCoin() != null) {
            keepNum = coin.getKeepDecimalForCoin();
        }
        //提币数量判断
        transferNum = transferNum.setScale(keepNum, BigDecimal.ROUND_DOWN);
        if (transferNum.compareTo(BigDecimal.ZERO) <= 0) {
            remoteResult.setMsg("transfer_num_error");
            remoteResult.setSuccess(false);
            return remoteResult;
        }

        // 手续费
        BigDecimal feeRate = BigDecimal.ZERO;
        // 获取 是否收取手续费
        String is_need_fee = BaseConfUtil.getConfigSingle("is_need_fee", "configCache:transferRange");
        // 获取 手续费率
        String fee = BaseConfUtil.getConfigSingle("fee", "configCache:transferRange");
        if ("1".equals(is_need_fee)) {
            feeRate = new BigDecimal(fee);
        }
        String lockKey = "LOCK_ACCOUNT:" + fromId + ":" + coinCode;
        boolean lock = redisService.lock(lockKey);
        if (lock) {
            // 手续费
            BigDecimal serviceFee = transferNum.multiply(feeRate).divide(new BigDecimal(100), 8, BigDecimal.ROUND_HALF_DOWN);
            // 手续费加算
            transferNum = transferNum.add(serviceFee);

            ExDigitalmoneyAccountRedis formDigAccount = UserRedisUtils.getAccount(fuserRedis.getDmAccountId(coinCode).toString(), coinCode);
            if (formDigAccount == null) {
                // 转出虚拟账户不存在
                //释放锁
                redisService.unLock(lockKey);
                remoteResult.setMsg("xnzh_no_exist");
                return remoteResult;
            }
            ExDigitalmoneyAccountRedis toDigAccount = UserRedisUtils.getAccount(tuserRedis.getDmAccountId(coinCode).toString(), coinCode);
            if (toDigAccount == null) {
                // 转入虚拟账户不存在
                //释放锁
                redisService.unLock(lockKey);
                remoteResult.setMsg("xnzh_no_exist");
                return remoteResult;
            }
            if (formDigAccount.getHotMoney().compareTo(BigDecimal.ZERO) <= 0) {
                //释放锁
                redisService.unLock(lockKey);
                remoteResult.setMsg("xnzh_insufficient");
                return remoteResult;
            }
            if (formDigAccount.getColdMoney().compareTo(BigDecimal.ZERO) < 0) {
                //释放锁
                redisService.unLock(lockKey);
                remoteResult.setMsg("xnzh_abnormal_error");
                return remoteResult;
            }
            if (formDigAccount.getHotMoney().compareTo(transferNum) < 0) {
                //释放锁
                redisService.unLock(lockKey);
                remoteResult.setMsg("xnzh_insufficient");
                return remoteResult;
            }


            //创建转账记录
            SocialTransferRecord sfr = new SocialTransferRecord();
            sfr.setOrderNum(IdGenerate.transactionNum("TF"));//交易ID
            sfr.setFormId(fromCustomerId);//付款方ID
            sfr.setCoinCode(coinCode);//转账币种
            sfr.setNum(transferNum);
            sfr.setToId(toCustomerId);
            sfr.setServiceFee(serviceFee); // 手续费
            sfr.setRemark(remark);//备注
            socialTransferRecordService.save(sfr);
            //生成转账结晶流水记录
            SocialCoinTradeRecord fsctr = new SocialCoinTradeRecord();
            //收支类型（0：收入 ， 1：支出）
            fsctr.setCategory(1);
            //类型（0：奖励，1：充币，2：提币，3：支付）
            //类型（0：奖励(收)，1：充币(收)，2：提币(支)，3：购买平台币(收、支)，4转账(收、支)， 5打赏(收、支)，6上链(支)，7捐赠(支)）
            fsctr.setType(4);
            //币种
            fsctr.setCoinCode(coinCode);
            fsctr.setStates(2);
            fsctr.setSource("friend_transfer");
            fsctr.setNumber(transferNum);
            fsctr.setServiceFee(serviceFee); //手续费
            fsctr.setCustomerId(fromCustomerId);
            //溯源标识(根据type和sourceNum可以找到具体的业务流水)
            fsctr.setSourceNum(sfr.getId().toString());
            socialCoinTradeRecordService.save(fsctr);
            // 交易所流水
            ExDmTransaction fexDmTransaction = new ExDmTransaction();
            fexDmTransaction.setCustomerId(fromCustomerId);
            String ftransactionNum = NumConstant.Ex_Dm_Transaction;
            fexDmTransaction.setTransactionNum(IdGenerate.transactionNum(ftransactionNum));
            fexDmTransaction.setAccountId(formDigAccount.getId());
            fexDmTransaction.setTransactionType(2);  // 交易类型(1币收入 ，2币支出)
            fexDmTransaction.setTransactionMoney(transferNum);
            fexDmTransaction.setCustomerName(fromUser.getNickName());
            fexDmTransaction.setStatus(2);   // 状态 1待审核 --2已完成 -- 3以否决
            fexDmTransaction.setCoinCode(coinCode);
            fexDmTransaction.setCurrencyType(fromUser.getCommonLanguage());
            fexDmTransaction.setFee(serviceFee);
            fexDmTransaction.setOrderNo(fsctr.getId().toString());
            fexDmTransaction.setRemark(remark);
            fexDmTransaction.setOptType(AccountRemarkEnum.TYPE504.getIndex());//TYPE500("数币奖励", 500), TYPE503("购买平台币", 503), TYPE505("朋友圈打赏", 505), TYPE506("许愿上链", 506), TYPE507("区块链公益捐赠", 507), TYPE508("购买矿机", 508), TYPE509("购买会员", 509);
            // 保存订单
            exDmTransactionService.save(fexDmTransaction);
            //生成被转账人结晶流水记录
            SocialCoinTradeRecord tsctr = new SocialCoinTradeRecord();
            //收支类型（0：收入 ， 1：支出）
            tsctr.setCategory(0);
            //类型（0：奖励，1：充币，2：提币，3：支付）
            //类型（0：奖励(收)，1：充币(收)，2：提币(支)，3：购买平台币(收、支)，4转账(收、支)， 5打赏(收、支)，6上链(支)，7捐赠(支)）
            tsctr.setType(4);
            //币种
            tsctr.setCoinCode(coinCode);
            tsctr.setStates(2);
            tsctr.setSource("friend_transfer");
            tsctr.setNumber(transferNum.subtract(serviceFee));
            tsctr.setServiceFee(BigDecimal.ZERO); //手续费
            tsctr.setCustomerId(toCustomerId);
            //溯源标识(根据type和sourceNum可以找到具体的业务流水)
            tsctr.setSourceNum(sfr.getId().toString());
            socialCoinTradeRecordService.save(tsctr);
            // 交易所流水
            ExDmTransaction texDmTransaction = new ExDmTransaction();
            texDmTransaction.setCustomerId(toCustomerId);
            String ttransactionNum = NumConstant.Ex_Dm_Transaction;
            texDmTransaction.setTransactionNum(IdGenerate.transactionNum(ttransactionNum));
            texDmTransaction.setAccountId(toDigAccount.getId());
            texDmTransaction.setTransactionType(1);  // 交易类型(1币收入 ，2币支出)
            texDmTransaction.setTransactionMoney(transferNum.subtract(serviceFee));
            texDmTransaction.setCustomerName(toUser.getNickName());
            texDmTransaction.setStatus(2);   // 状态 1待审核 --2已完成 -- 3以否决
            texDmTransaction.setCoinCode(coinCode);
            texDmTransaction.setCurrencyType(toUser.getCommonLanguage());
            texDmTransaction.setFee(BigDecimal.ZERO);
            texDmTransaction.setOrderNo(tsctr.getId().toString());
            texDmTransaction.setRemark(remark);
            texDmTransaction.setOptType(AccountRemarkEnum.TYPE504.getIndex());//TYPE500("数币奖励", 500), TYPE503("购买平台币", 503), TYPE505("朋友圈打赏", 505), TYPE506("许愿上链", 506), TYPE507("区块链公益捐赠", 507), TYPE508("购买矿机", 508), TYPE509("购买会员", 509);
            // 保存订单
            exDmTransactionService.save(texDmTransaction);
            // 发送提币mq通知缓存
            Accountadd accountFrom = new Accountadd();
            accountFrom.setAccountId(formDigAccount.getId());
            accountFrom.setMoney(transferNum.multiply(new BigDecimal(-1)));
            accountFrom.setMonteyType(1);
            accountFrom.setAcccountType(1);
            accountFrom.setRemarks(26);
            accountFrom.setTransactionNum(fexDmTransaction.getTransactionNum());
            List<Accountadd> list = new ArrayList<Accountadd>();
            list.add(accountFrom);
            // 发送充币mq通知缓存
            Accountadd accountTo = new Accountadd();
            accountTo.setAccountId(toDigAccount.getId());
            accountTo.setMoney(transferNum.subtract(serviceFee));
            accountTo.setMonteyType(1);
            accountTo.setAcccountType(1);
            accountTo.setRemarks(27);
            accountTo.setTransactionNum(texDmTransaction.getTransactionNum());
            list.add(accountTo);
            messageProducer.toAccount(JSON.toJSONString(list));
            remoteResult.setSuccess(true);
            //发站内消息
            Map<String,String> fromParamers = new HashMap<>();
            fromParamers.put(NoticeTemplateUtil.HRY_TIME, DateUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
            fromParamers.put(NoticeTemplateUtil.HRY_USER, toUser.getNickName());
            fromParamers.put(NoticeTemplateUtil.HRY_COIN_OUT, transferNum + coinCode);
            NoticeMessage noticeMessage = new NoticeMessage(fromUser, NoticeTemplateUtil.COIN_TO_SUCCESS, fromParamers);
            messageProducer.toSendMsg(JSON.toJSONString(noticeMessage));
            //释放锁
            redisService.unLock(lockKey);
            remoteResult.setSuccess(true);
            return remoteResult;
        } else {
            remoteResult.setMsg("sys_busy_error");
            return remoteResult;
        }
    }

    /**
     * 分页查询
     *
     * @param params
     * @return
     */
    @Override
    public FrontPage findTransferPage(Map<String,String> params) {
        return socialTransferRecordService.findPageList(params);
    }
}
