/**
 * Copyright:
 *
 * @author: lixin
 * @version: V1.0
 * @Date: 2018-12-18 15:46:48
 */
package hry.social.manage.remote.wishwall;

import com.alibaba.fastjson.JSON;
import hry.core.shiro.PasswordHelper;
import hry.front.redis.model.UserRedis;
import hry.manage.remote.RemoteManageService;
import hry.manage.remote.model.RemoteResult;
import hry.manage.remote.model.User;
import hry.manage.remote.model.base.FrontPage;
import hry.redis.common.dao.RedisUtil;
import hry.redis.common.utils.RedisService;
import hry.social.manage.remote.api.wishwall.RemoteWishWallService;
import hry.social.manage.remote.model.traderecord.SocialCoinTradeRecord;
import hry.social.manage.remote.model.wishwall.SocialWishWall;
import hry.social.mq.producer.service.MessageProducer;
import hry.social.mq.producer.service.NoticeMessage;
import hry.social.traderecord.service.SocialCoinTradeRecordService;
import hry.social.transaction.model.ExDmTransaction;
import hry.social.transaction.service.ExDmTransactionService;
import hry.social.wishwall.service.SocialWishWallService;
import hry.trade.redis.model.Accountadd;
import hry.trade.redis.model.ExDigitalmoneyAccountRedis;
import hry.util.*;
import hry.util.date.DateUtil;
import hry.trade.model.AccountRemarkEnum;
import hry.util.idgenerate.IdGenerate;
import hry.util.idgenerate.NumConstant;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * <p> RemoteWishWallServiceImpl </p>
 *
 * @author: lixin
 * @Date :          2018-12-18 15:46:48
 */
public class RemoteWishWallServiceImpl implements RemoteWishWallService {

    @Resource
    private MessageProducer messageProducer;
    @Resource
    SocialWishWallService socialWishWallService;
    @Resource
    private RemoteManageService remoteManageService;
    @Resource
    private SocialCoinTradeRecordService socialCoinTradeRecordService;
    @Resource
    private ExDmTransactionService exDmTransactionService;
    @Resource
    private RedisService redisService;

    /**
     * 分页查询
     *
     * @param params
     * @return
     */
    @Override
    public FrontPage findPageList(Map<String,String> params) {
        // 查询方法
        return socialWishWallService.findPageList(params);
    }

    /**
     * 新增许愿
     *
     * @param content    心愿
     * @param signature  签名
     * @param acpwd      资金密码
     * @param customerId 用户ID
     * @return
     */
    @Override
    public RemoteResult save(String content, String signature, String acpwd, Long customerId) {
        RemoteResult remoteResult = new RemoteResult();
        // 获取 平台币
        String platCoin = BaseConfUtil.getConfigSingle("platCoin", "configCache:basefinanceConfig");
        if (StringUtils.isEmpty(platCoin)) {
            System.out.println("平台币配置错误");
            remoteResult.setMsg("xnzh_no_exist");
            return remoteResult;
        }
        String feeStr = BaseConfUtil.getConfigSingle("fee", "configCache:wishWallConfig");
        if (StringUtils.isEmpty(feeStr)) {
            System.out.println("许愿配置错误");
            remoteResult.setMsg("req_error");
            return remoteResult;
        }
        BigDecimal fee = new BigDecimal(feeStr);
        User user = remoteManageService.getUserByCustomerId(customerId);
        PasswordHelper passwordHelper = new PasswordHelper();
        String apw = passwordHelper.encryString(acpwd, user.getSalt());
        if (!apw.equals(user.getAccountPassWord())) {
            return new RemoteResult().setMsg("acpwd_error");
        }
        // 判断该用户虚拟账户
        RedisUtil<UserRedis> redisUtil = new RedisUtil<UserRedis>(UserRedis.class);
        UserRedis fUserRedis = redisUtil.get(customerId.toString());
        String personId = customerId.toString();
        String lockKey = "LOCK_ACCOUNT:" + personId + ":" + platCoin;
        boolean lock = redisService.lock(lockKey);
        if (lock) {
            ExDigitalmoneyAccountRedis rewardDigAccount = UserRedisUtils.getAccount(fUserRedis.getDmAccountId(platCoin).toString(), platCoin);
            if (rewardDigAccount == null) {
                // 转出虚拟账户不存在
                //释放锁
                redisService.unLock(lockKey);
                remoteResult.setMsg("xnzh_no_exist");
                return remoteResult;
            }
            if (rewardDigAccount.getHotMoney().compareTo(BigDecimal.ZERO) <= 0) {
                //释放锁
                redisService.unLock(lockKey);
                remoteResult.setMsg("xnzh_insufficient");
                return remoteResult;
            }
            if (rewardDigAccount.getColdMoney().compareTo(BigDecimal.ZERO) < 0) {
                //释放锁
                redisService.unLock(lockKey);
                remoteResult.setMsg("xnzh_abnormal_error");
                return remoteResult;
            }
            if (rewardDigAccount.getHotMoney().compareTo(fee) < 0) {
                //释放锁
                redisService.unLock(lockKey);
                remoteResult.setMsg("xnzh_insufficient");
                return remoteResult;
            }
            //生成许愿墙记录
            SocialWishWall wishWall = new SocialWishWall();
            wishWall.setCoinCode(platCoin);
            wishWall.setContent(content);
            wishWall.setWishNum(IdGenerate.transactionNum("XY"));
            wishWall.setCustomerId(customerId);
            wishWall.setSignature(signature);
            wishWall.setType(0);
            wishWall.setFee(fee);
            socialWishWallService.save(wishWall);
            //生成打赏人结晶流水记录
            SocialCoinTradeRecord fsctr = new SocialCoinTradeRecord();
            //收支类型（0：收入 ， 1：支出）
            fsctr.setCategory(1);
            //类型（0：奖励，1：充币，2：提币，3：支付）
            //类型（0：奖励(收)，1：充币(收)，2：提币(支)，3：购买平台币(收、支)，4转账(收、支)， 5打赏(收、支)，6上链(支)，7捐赠(支)）
            fsctr.setType(6);
            //币种
            fsctr.setCoinCode(platCoin);
            fsctr.setStates(2);
            fsctr.setSource("wish_wall_record");
            fsctr.setNumber(fee);
            fsctr.setCustomerId(customerId);
            //溯源标识(根据type和sourceNum可以找到具体的业务流水)
            fsctr.setSourceNum(wishWall.getId().toString());
            socialCoinTradeRecordService.save(fsctr);
            // 交易所流水
            ExDmTransaction texDmTransaction = new ExDmTransaction();
            texDmTransaction.setCustomerId(customerId);
            String ttransactionNum = NumConstant.Ex_Dm_Transaction;
            texDmTransaction.setTransactionNum(IdGenerate.transactionNum(ttransactionNum));
            texDmTransaction.setAccountId(rewardDigAccount.getId());
            texDmTransaction.setTransactionType(2);  // 交易类型(1币收入 ，2币支出)
            texDmTransaction.setTransactionMoney(fee);
            texDmTransaction.setCustomerName(user.getNickName());
            texDmTransaction.setStatus(2);   // 状态 1待审核 --2已完成 -- 3以否决
            texDmTransaction.setCoinCode(platCoin);
            texDmTransaction.setCurrencyType(user.getCommonLanguage());
            texDmTransaction.setFee(BigDecimal.ZERO);
            texDmTransaction.setOrderNo(fsctr.getId().toString());
            texDmTransaction.setOptType(AccountRemarkEnum.TYPE506.getIndex());//TYPE500("数币奖励", 500), TYPE503("购买平台币", 503), TYPE505("朋友圈打赏", 505), TYPE506("许愿上链", 506), TYPE507("区块链公益捐赠", 507), TYPE508("购买矿机", 508), TYPE509("购买会员", 509);
            // 保存订单
            exDmTransactionService.save(texDmTransaction);
            // 发送提币mq通知缓存
            Accountadd accountFrom = new Accountadd();
            accountFrom.setAccountId(rewardDigAccount.getId());
            accountFrom.setMoney(fee.multiply(new BigDecimal(-1)));
            accountFrom.setMonteyType(1);
            accountFrom.setAcccountType(1);
            accountFrom.setRemarks(26);
            accountFrom.setTransactionNum(texDmTransaction.getTransactionNum());
            List<Accountadd> list = new ArrayList<Accountadd>();
            list.add(accountFrom);
            messageProducer.toAccount(JSON.toJSONString(list));
            Map<String,String> paramers = new HashMap<>();
            paramers.put(NoticeTemplateUtil.HRY_TIME, DateUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
            paramers.put(NoticeTemplateUtil.HRY_USER, user.getNickName());
            paramers.put(NoticeTemplateUtil.HRY_COIN_OUT, fee + platCoin);
            NoticeMessage noticeMessage = new NoticeMessage(user,NoticeTemplateUtil.WISH_WALL_SUCCESS, paramers);
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
