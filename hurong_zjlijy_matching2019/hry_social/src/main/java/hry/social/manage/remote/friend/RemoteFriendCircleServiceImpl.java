/**
 * Copyright:
 *
 * @author: lixin
 * @version: V1.0
 * @Date: 2018-12-10 15:35:01
 */
package hry.social.manage.remote.friend;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.Page;
import hry.core.shiro.PasswordHelper;
import hry.front.redis.model.UserRedis;
import hry.manage.remote.RemoteManageService;
import hry.manage.remote.model.Coin;
import hry.manage.remote.model.RemoteResult;
import hry.manage.remote.model.User;
import hry.manage.remote.model.base.FrontPage;
import hry.redis.common.dao.RedisUtil;
import hry.redis.common.utils.RedisService;
import hry.social.friend.dao.SocialFriendCircleDao;
import hry.social.friend.dao.SocialFriendCommentDao;
import hry.social.friend.dao.SocialFriendPictureDao;
import hry.social.friend.dao.SocialFriendRewardDao;
import hry.social.manage.remote.api.friend.RemoteFriendCircleService;
import hry.social.manage.remote.model.friend.SocialFriendCircle;
import hry.social.manage.remote.model.friend.SocialFriendComment;
import hry.social.manage.remote.model.friend.SocialFriendReward;
import hry.social.manage.remote.model.traderecord.SocialCoinTradeRecord;
import hry.social.mq.producer.service.MessageProducer;
import hry.social.mq.producer.service.NoticeMessage;
import hry.social.traderecord.dao.SocialCoinTradeRecordDao;
import hry.social.transaction.model.ExDmTransaction;
import hry.social.transaction.service.ExDmTransactionService;
import hry.trade.model.AccountRemarkEnum;
import hry.trade.redis.model.Accountadd;
import hry.trade.redis.model.ExDigitalmoneyAccountRedis;
import hry.util.*;
import hry.util.date.DateUtil;
import hry.util.idgenerate.IdGenerate;
import hry.util.idgenerate.NumConstant;
import hry.util.oss.GetYunUtil;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;


/**
 * <p> RemoteFriendCircleServiceImpl </p>
 *
 * @author: lixin
 * @Date :          2018-12-10 15:35:01
 */
public class RemoteFriendCircleServiceImpl implements RemoteFriendCircleService {

    @Resource
    SocialFriendCircleDao socialFriendCircleDao;
    @Resource
    SocialFriendPictureDao socialFriendPictureDao;
    @Resource
    SocialFriendCommentDao socialFriendCommentDao;
    @Resource
    SocialFriendRewardDao socialFriendRewardDao;
    @Resource
    private MessageProducer messageProducer;
    @Resource
    private RedisService redisService;
    @Resource
    private RemoteManageService remoteManageService;
    @Resource
    private SocialCoinTradeRecordDao socialCoinTradeRecordDao;

    @Resource
    private ExDmTransactionService exDmTransactionService;

    /**
     * 分页查询朋友圈动态
     *
     * @param params
     * @return
     */
    @Override
    public FrontPage findPageList(Map<String,String> params) {
        Page page = PageFactory.getPage(params);
        String customerId = params.get("customerId");
        String palId = params.get("palId");
        // 查询方法
        List<SocialFriendCircle> list = socialFriendCircleDao.findPageList(params);
        for (SocialFriendCircle sfc : list) {
            Long sfcId = sfc.getId();
            //时间处理
            Date issuedTime = sfc.getIssuedTime();
            String appDate = APPDateUtil.appDate(issuedTime);
            //查询图片
            List<String> images = socialFriendPictureDao.findUrls(sfcId);
            for (String image : images) {
                image = GetYunUtil.getUrl(image, false);
            }
            //查询评论
            List<SocialFriendComment> comments = socialFriendCommentDao.getComments(Long.valueOf(customerId), sfcId);
            //查询点赞
            List<SocialFriendReward> rewards = socialFriendRewardDao.getRewards(Long.valueOf(customerId), sfcId);//查询点赞
            BigDecimal totalRewards = BigDecimal.ZERO;
            if (rewards != null && rewards.size() > 0) {
                totalRewards = socialFriendRewardDao.totalRewards(sfcId);
            }
            sfc.setImageUrls(images);
            sfc.setComments(comments);
            sfc.setRewards(rewards);
            sfc.setTotalRewards(totalRewards);
            sfc.setTime(appDate);
        }
        return new FrontPage(list, page.getTotal(), page.getPages(), page.getPageSize());
    }

    /**
     * 删除动态
     *
     * @param id
     * @return
     */
    @Override
    public RemoteResult delet(Long id) {
        RemoteResult remoteResult = new RemoteResult();
        SocialFriendCircle socialFriendCircle = new SocialFriendCircle();
        socialFriendCircle.setId(id);
        socialFriendCircle.setIssued(2);
        socialFriendCircleDao.updateByPrimaryKeySelective(socialFriendCircle);
        return remoteResult.setSuccess(true);
    }

    /**
     * 发朋友圈
     *
     * @return
     */
    @Override
    public RemoteResult issueCircle() {
        return null;
    }

    /**
     * 查询评论（更多）
     *
     * @param sfcId      帖子ID
     * @param customerId 当前登录人
     * @return
     */
    @Override
    public RemoteResult findComments(Long sfcId, Long customerId) {
        return null;
    }

    /**
     * 评论帖子
     *
     * @param sfcId      帖子ID
     * @param customerId 评论人
     * @param content    评论内容
     * @return
     */
    @Override
    public RemoteResult comments(Long sfcId, Long customerId, String content, Long contentId, Long friendId) {
        RemoteResult remoteResult = new RemoteResult();
        SocialFriendComment socialFriendComment = new SocialFriendComment();
        socialFriendComment.setCircleId(sfcId);
        socialFriendComment.setCustomerId(customerId);
        socialFriendComment.setComments(content);
        if (contentId != null) {
            socialFriendComment.setParentId(contentId);
        }
        if (friendId != null) {
            socialFriendComment.setFriendId(friendId);
        }
        socialFriendCommentDao.insertSelective(socialFriendComment);
        return remoteResult.setSuccess(true);
    }

    /**
     * 点赞（打赏）
     *
     * @param sfcId        帖子ID
     * @param rewardNumStr 打赏金额
     * @param customerId   打赏人
     * @param acpwd        资金密码
     * @return
     */
    @Override
    public RemoteResult reward(Long sfcId, String rewardNumStr, Long customerId, String acpwd) {
        RemoteResult remoteResult = new RemoteResult();
        User user = remoteManageService.getUserByCustomerId(customerId);
        SocialFriendCircle socialFriendCircle = socialFriendCircleDao.selectByPrimaryKey(sfcId);
        Integer issued = socialFriendCircle != null ? socialFriendCircle.getIssued() : 2;
        if (issued.intValue() == 2) {
            return remoteResult.setMsg("post_has_delete_error");
        }
        int hasReward = socialFriendRewardDao.hasReward(sfcId, customerId);
        if (hasReward == 1) {
            return remoteResult.setMsg("repeat_reward_error");
        }
        // 获取 平台币
        String platCoin = BaseConfUtil.getConfigSingle("platCoin", "configCache:basefinanceConfig");
        if (StringUtils.isEmpty(platCoin)) {
            System.out.println("平台币配置错误");
            remoteResult.setMsg("xnzh_no_exist");
            return remoteResult;
        }
        Map<String,String> productMap = redisService.getMap(SocialUtil.PRODUCT_KEY);
        if (StringUtils.isEmpty(productMap)) {
            System.out.println("平台币配置错误");
            remoteResult.setMsg("xnzh_no_exist");
            return remoteResult;
        }
        String coinStr = productMap.get(platCoin);
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
        // 获取免密打赏数量
        String noVerifyNumStr = BaseConfUtil.getConfigSingle("rewardNum", "configCache:friendCircleConfig");
        // 获取 打赏平台费率（%）
        String rewardRateStr = BaseConfUtil.getConfigSingle("rewardRate", "configCache:friendCircleConfig");
        BigDecimal noVerifyNum = BigDecimal.ZERO;
        BigDecimal feeRatio = BigDecimal.ZERO;
        try {
            noVerifyNum = StringUtils.isEmpty(noVerifyNumStr) ? noVerifyNum : new BigDecimal(noVerifyNumStr);
        } catch (Exception e) {
            feeRatio = StringUtils.isEmpty(rewardRateStr) ? feeRatio : new BigDecimal(rewardRateStr);
        }
        BigDecimal rewardNum = null;
        try {
            rewardNum = new BigDecimal(rewardNumStr);
        } catch (Exception e) {
            remoteResult.setMsg("reward_num_error");
            return remoteResult;
        }
        if (rewardNum.compareTo(BigDecimal.ZERO) <= 0) {
            remoteResult.setMsg("reward_num_error");
            return remoteResult;
        }
        if (rewardNum.compareTo(noVerifyNum) > 0) {
            PasswordHelper passwordHelper = new PasswordHelper();
            String apw = passwordHelper.encryString(acpwd, user.getSalt());
            if (!apw.equals(user.getAccountPassWord())) {
                return new RemoteResult().setMsg("acpwd_error");
            }
        }
        BigDecimal fee = rewardNum.multiply(feeRatio).divide(new BigDecimal("100"));
        BigDecimal coinNum = rewardNum.subtract(fee);
        Long issuseId = socialFriendCircle.getCustomerId();
        User issuseUser = remoteManageService.getUserByCustomerId(issuseId);
        // 判断该用户虚拟账户
        String rewardId = customerId.toString();
        String lockKey = "LOCK_ACCOUNT:" + rewardId + ":" + platCoin;
        boolean lock = redisService.lock(lockKey);
        if (lock) {
            RedisUtil<UserRedis> redisUtil = new RedisUtil<UserRedis>(UserRedis.class);
            UserRedis fUserRedis = redisUtil.get(rewardId);
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
            if (rewardDigAccount.getHotMoney().compareTo(rewardNum) < 0) {
                //释放锁
                redisService.unLock(lockKey);
                remoteResult.setMsg("xnzh_insufficient");
                return remoteResult;
            }

            UserRedis toUserRedis = redisUtil.get(issuseId.toString());
            ExDigitalmoneyAccountRedis toDigAccount = UserRedisUtils.getAccount(toUserRedis.getDmAccountId(platCoin).toString(), platCoin);
            if (toDigAccount == null) {
                // 转出虚拟账户不存在
                //释放锁
                redisService.unLock(lockKey);
                remoteResult.setMsg("xnzh_no_exist");
                return remoteResult;
            }
            //生成打赏记录
            SocialFriendReward socialFriendReward = new SocialFriendReward();
            socialFriendReward.setCircleId(sfcId);
            socialFriendReward.setCustomerId(issuseId);
            socialFriendReward.setRewardId(customerId);
            socialFriendReward.setCoinCode(platCoin);
            socialFriendReward.setRewardNum(rewardNum);
            socialFriendReward.setActualReward(coinNum);
            socialFriendReward.setFee(fee);
            socialFriendRewardDao.insertSelective(socialFriendReward);
            //生成打赏人结晶流水记录
            SocialCoinTradeRecord fsctr = new SocialCoinTradeRecord();
            //收支类型（0：收入 ， 1：支出）
            fsctr.setCategory(1);
            //类型（0：奖励，1：充币，2：提币，3：支付）
            //类型（0：奖励(收)，1：充币(收)，2：提币(支)，3：购买平台币(收、支)，4转账(收、支)， 5打赏(收、支)，6上链(支)，7捐赠(支)）
            fsctr.setType(5);
            //币种
            fsctr.setCoinCode(platCoin);
            fsctr.setStates(2);
            fsctr.setSource("friend_reward");
            fsctr.setNumber(rewardNum);
            fsctr.setCustomerId(customerId);
            //溯源标识(根据type和sourceNum可以找到具体的业务流水)
            fsctr.setSourceNum(socialFriendReward.getId().toString());
            socialCoinTradeRecordDao.insertSelective(fsctr);
            // 交易所流水
            ExDmTransaction fexDmTransaction = new ExDmTransaction();
            fexDmTransaction.setCustomerId(customerId);
            String transactionNum = NumConstant.Ex_Dm_Transaction;
            fexDmTransaction.setTransactionNum(IdGenerate.transactionNum(transactionNum));
            fexDmTransaction.setAccountId(rewardDigAccount.getId());
            fexDmTransaction.setTransactionType(2);  // 交易类型(1币收入 ，2币支出)
            fexDmTransaction.setTransactionMoney(rewardNum);
            fexDmTransaction.setCustomerName(user.getNickName());
            fexDmTransaction.setStatus(2);   // 状态 1待审核 --2已完成 -- 3以否决
            fexDmTransaction.setCoinCode(platCoin);
            fexDmTransaction.setCurrencyType(user.getCommonLanguage());
            fexDmTransaction.setFee(fee);
            fexDmTransaction.setOrderNo(fsctr.getId().toString());
            fexDmTransaction.setOptType(AccountRemarkEnum.TYPE505.getIndex());//TYPE500("数币奖励", 500), TYPE503("购买平台币", 503), TYPE505("朋友圈打赏", 505), TYPE506("许愿上链", 506), TYPE507("区块链公益捐赠", 507), TYPE508("购买矿机", 508), TYPE509("购买会员", 509);
            // 保存订单
            exDmTransactionService.save(fexDmTransaction);
            //生成被打赏人结晶流水记录
            SocialCoinTradeRecord tsctr = new SocialCoinTradeRecord();
            //种类（0：收入 ， 1：支出）
            tsctr.setCategory(0);
            //类型（0：奖励(收)，1：充币(收)，2：提币(支)，3：购买平台币(收、支)，4转账(收、支)， 5打赏(收、支)，6上链(支)，7捐赠(支)）
            tsctr.setType(5);
            tsctr.setCoinCode(platCoin);
            tsctr.setStates(2);
            tsctr.setSource("friend_reward");
            tsctr.setNumber(coinNum);
            tsctr.setCustomerId(issuseId);
            tsctr.setSourceNum(socialFriendReward.getId().toString());
            socialCoinTradeRecordDao.insertSelective(tsctr);
            // 交易所流水
            ExDmTransaction exDmTransaction = new ExDmTransaction();
            exDmTransaction.setCustomerId(issuseUser.getCustomerId());
            String ttransactionNum = NumConstant.Ex_Dm_Transaction;
            exDmTransaction.setTransactionNum(IdGenerate.transactionNum(ttransactionNum));
            exDmTransaction.setAccountId(toDigAccount.getId());
            exDmTransaction.setTransactionType(1);  // 交易类型(1币收入 ，2币支出)
            exDmTransaction.setTransactionMoney(coinNum);
            exDmTransaction.setCustomerName(issuseUser.getNickName());
            exDmTransaction.setStatus(2);   // 状态 1待审核 --2已完成 -- 3以否决
            exDmTransaction.setCoinCode(platCoin);
            exDmTransaction.setCurrencyType(issuseUser.getCommonLanguage());
            exDmTransaction.setFee(BigDecimal.ZERO);
            exDmTransaction.setOrderNo(tsctr.getId().toString());
            exDmTransaction.setOptType(AccountRemarkEnum.TYPE505.getIndex());//TYPE500("数币奖励", 500), TYPE503("购买平台币", 503), TYPE505("朋友圈打赏", 505), TYPE506("许愿上链", 506), TYPE507("区块链公益捐赠", 507), TYPE508("购买矿机", 508), TYPE509("购买会员", 509);
            // 保存订单
            exDmTransactionService.save(exDmTransaction);
            // 发送提币mq通知缓存
            Accountadd accountFrom = new Accountadd();
            accountFrom.setAccountId(rewardDigAccount.getId());
            accountFrom.setMoney(rewardNum.multiply(new BigDecimal(-1)));
            accountFrom.setMonteyType(1);
            accountFrom.setAcccountType(1);
            accountFrom.setRemarks(26);
            accountFrom.setTransactionNum(fexDmTransaction.getTransactionNum());
            List<Accountadd> list = new ArrayList<Accountadd>();
            list.add(accountFrom);
            // 发送充币mq通知缓存
            Accountadd accountTo = new Accountadd();
            accountTo.setAccountId(toDigAccount.getId());
            accountTo.setMoney(coinNum);
            accountTo.setMonteyType(1);
            accountTo.setAcccountType(1);
            accountTo.setRemarks(27);
            accountTo.setTransactionNum(exDmTransaction.getTransactionNum());
            list.add(accountTo);
            messageProducer.toAccount(JSON.toJSONString(list));
            //发站内消息
            Map<String,String> paramers = new HashMap<>();
            paramers.put(NoticeTemplateUtil.HRY_TIME, DateUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
            paramers.put(NoticeTemplateUtil.HRY_USER, issuseUser.getNickName());
            paramers.put(NoticeTemplateUtil.HRY_COIN_OUT, rewardNumStr + platCoin);
            NoticeMessage noticeMessage = new NoticeMessage(issuseUser,NoticeTemplateUtil.FRIEND_REWARD_SUCCESS, paramers);
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
     * 发帖
     *
     * @param customerId
     * @param content
     * @param link
     * @param linkTitle
     * @param linkImage
     * @param device
     * @param site
     * @param pathImg
     * @return
     */
    @Override
    public void friendCircle(Long customerId, String content, String link, String linkTitle, String linkImage, String device, String site, String[] pathImg) {
        String picture = "";
        if (pathImg != null && pathImg.length > 0) {
            picture = pathImg[0];
            for (int i = 1; i < pathImg.length; i++) {
                picture = picture + "|" + pathImg[i];
            }
        }

        SocialFriendCircle socialFriendCircle = new SocialFriendCircle();
        socialFriendCircle.setCustomerId(customerId);
        socialFriendCircle.setContent(content);
        socialFriendCircle.setLink(link);
        socialFriendCircle.setLinkTitle(linkTitle);
        socialFriendCircle.setLinkImage(linkImage);
        socialFriendCircle.setDevice(device);
        socialFriendCircle.setSite(site);
        socialFriendCircle.setPicture(picture);
        socialFriendCircle.setCreated(new Date());
        socialFriendCircle.setIssued(1);
        socialFriendCircle.setIssuedTime(new Date());
        socialFriendCircleDao.insertSelective(socialFriendCircle);
        Long sfcId = socialFriendCircle.getId();
        if (pathImg != null && pathImg.length > 0) {
            for (int i = 0; i < pathImg.length; i++) {
                String s = pathImg[i];
                socialFriendCircleDao.saveImg(sfcId, s.replace("hryfile/", ""), s);
            }
        }
    }

    /**
     * 设置朋友圈个人主题
     *
     * @param customerId
     * @param themeImg
     * @return
     */
    @Override
    public RemoteResult theme(Long customerId, String[] themeImg) {
        RemoteResult remoteResult = new RemoteResult();
        socialFriendCircleDao.updateTheme(customerId, themeImg[0]);
        remoteResult.setSuccess(true).setObj(themeImg[0]);
        return remoteResult;
    }

    @Override
    public FrontPage findRewardPage(Map<String,String> params) {
        Page page = PageFactory.getPage(params);
        // 查询方法
        List<SocialFriendReward> list = socialFriendRewardDao.findPageList(params);
        return new FrontPage(list, page.getTotal(), page.getPages(), page.getPageSize());
    }

    /**
     * 周榜
     *
     * @param customerId
     * @return
     */
    @Override
    public List<SocialFriendCircle> weeklyRank(Long customerId) {
        // 查询方法
        List<SocialFriendCircle> list = socialFriendCircleDao.weeklyRank();
        for (SocialFriendCircle sfc : list) {
            Long sfcId = sfc.getId();
            //时间处理
            Date issuedTime = sfc.getIssuedTime();
            String appDate = APPDateUtil.appDate(issuedTime);
            //查询图片
            List<String> images = socialFriendPictureDao.findUrls(sfcId);
            for (String image : images) {
                image = GetYunUtil.getUrl(image, false);
            }
            //查询评论
            List<SocialFriendComment> comments = socialFriendCommentDao.getComments(customerId, sfcId);
            //查询点赞
            List<SocialFriendReward> rewards = socialFriendRewardDao.getRewards(customerId, sfcId);//查询点赞
            BigDecimal totalRewards = BigDecimal.ZERO;
            if (rewards != null && rewards.size() > 0) {
                totalRewards = socialFriendRewardDao.totalRewards(sfcId);
            }
            sfc.setImageUrls(images);
            sfc.setComments(comments);
            sfc.setRewards(rewards);
            sfc.setTotalRewards(totalRewards);
            sfc.setTime(appDate);
        }
        return list;
    }

    /**
     * 月榜
     *
     * @param customerId
     * @return
     */
    @Override
    public List<SocialFriendCircle> monthRank(Long customerId) {
        // 查询方法
        List<SocialFriendCircle> list = socialFriendCircleDao.monthRank();
        for (SocialFriendCircle sfc : list) {
            Long sfcId = sfc.getId();
            //时间处理
            Date issuedTime = sfc.getIssuedTime();
            String appDate = APPDateUtil.appDate(issuedTime);
            //查询图片
            List<String> images = socialFriendPictureDao.findUrls(sfcId);
            for (String image : images) {
                image = GetYunUtil.getUrl(image, false);
            }
            //查询评论
            List<SocialFriendComment> comments = socialFriendCommentDao.getComments(customerId, sfcId);
            //查询点赞
            List<SocialFriendReward> rewards = socialFriendRewardDao.getRewards(customerId, sfcId);//查询点赞
            BigDecimal totalRewards = BigDecimal.ZERO;
            if (rewards != null && rewards.size() > 0) {
                totalRewards = socialFriendRewardDao.totalRewards(sfcId);
            }
            sfc.setImageUrls(images);
            sfc.setComments(comments);
            sfc.setRewards(rewards);
            sfc.setTotalRewards(totalRewards);
            sfc.setTime(appDate);
        }
        return list;
    }

    /**
     * 查询总打赏值
     *
     * @param customerId
     * @return
     */
    @Override
    public BigDecimal friendReward(Long customerId) {
        return socialFriendRewardDao.friendReward(customerId);
    }
}
