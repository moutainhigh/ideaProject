package hry.licqb.remote;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import hry.bean.FrontPage;
import hry.bean.JsonResult;
import hry.front.redis.model.UserRedis;
import hry.licqb.exchange.model.ExchangeConfig;
import hry.licqb.exchange.model.ExchangeImage;
import hry.licqb.exchange.model.ExchangeItem;
import hry.licqb.exchange.model.ExchangeRecord;
import hry.licqb.exchange.service.*;
import hry.licqb.record.service.TaskLogService;
import hry.licqb.util.AccountUtil;
import hry.mq.producer.service.MessageProducer;
import hry.redis.common.dao.RedisUtil;
import hry.redis.common.utils.RedisService;
import hry.trade.redis.model.Accountadd;
import hry.trade.redis.model.ExDigitalmoneyAccountRedis;
import hry.util.PageFactory;
import hry.util.QueryFilter;
import hry.util.date.DateUtil;
import hry.util.idgenerate.IdGenerate;
import util.UserRedisUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author zhouming
 * @date 2020/12/22 14:30
 * @Version 1.0
 */
public class RemoteExchangeServiceImpl implements RemoteExchangeService {

    @Resource
    private ExchangeConfigService exchangeConfigService;
    @Resource
    private ExchangeRecordService exchangeRecordService;
    @Resource
    private ExchangeItemService exchangeItemService;
    @Resource
    private RedisService redisService;
    @Resource
    private MessageProducer messageProducer;
    @Resource
    private ExchangeTotalService exchangeTotalService;
    @Resource
    private TaskLogService taskLogService;
    @Resource
    private ExchangeImageService exchangeImageService;

    @Override
    public FrontPage findExchangeConfigList(Map<String, String> map) {
        Page<ExchangeConfig> page = PageFactory.getPage(map);
        List<ExchangeConfig> configList = exchangeConfigService.findExchangeConfig(map);
        // 获取指定首图
        if (configList != null && configList.size() > 0) {
            // 查询对应的图片
            QueryFilter filter = new QueryFilter(ExchangeImage.class);
            filter.addFilter("configId=", configList.get(0).getId());
            filter.addFilter("languagetype=", map.get("locale"));
            ExchangeImage image = exchangeImageService.get(filter);
            if (image != null) {
                configList.get(0).setImage(image.getImage());
                configList.get(0).setImageTwo(image.getImageTwo());
                configList.get(0).setImageThree(image.getImageThree());
            }
        }
        return new FrontPage(configList, page.getTotal(), page.getPages(), page.getPageSize());
    }

    @Override
    public JsonResult getExchangeConfig(String configId, String locale) {
        Map<String, String> map = new HashMap<>();
        map.put("configId", configId);
        map.put("locale", locale);
        List<ExchangeConfig> configList = exchangeConfigService.findExchangeConfig(map);
        if (configList != null && configList.size() > 0) {
            ExchangeConfig config = configList.get(0);
            // 查询对应的图片
            QueryFilter filter = new QueryFilter(ExchangeImage.class);
            filter.addFilter("configId=", configId);
            filter.addFilter("languagetype=", locale);
            ExchangeImage image = exchangeImageService.get(filter);
            if (image != null) {
                config.setImage(image.getImage());
                config.setImageTwo(image.getImageTwo());
                config.setImageThree(image.getImageThree());
            }
            return new JsonResult(true).setObj(config);
        } else {
            return new JsonResult(false);
        }
    }

    @Override
    public JsonResult toExchange(Map<String, String> map) {
        String configId = map.get("configId");
        String exchangeNum = map.get("exchangeNum");
        String customerId = map.get("customerId");

        // 获取兑换配置
        ExchangeConfig config = exchangeConfigService.get(Long.parseLong(configId));
        if (0 == config.getStatus()) {
            // 项目未开启
            return new JsonResult(false).setMsg("weiqidongxiangmu");
        }
        /*兑换时间校验*/
        // 兑换开始时间
        String exchangeStartTime = config.getGainStartDate();
        // 兑换结束时间
        String exchangeEndTime = config.getGainEndDate();
        // 当前时间
        String nowDate = DateUtil.dateToString(new Date()).split(" ")[1];
        Boolean flag = hourMinuteBetween(nowDate, exchangeStartTime, exchangeEndTime);
        if (!flag) {
            return new JsonResult(false).setMsg("qingzaizhidignshijianduihuan");
        }
        // 获取当前期数
        QueryFilter filter = new QueryFilter(ExchangeItem.class);
        filter.addFilter("itemConfigId= ", configId);
        filter.addFilter("status= ", 0);
        ExchangeItem item = exchangeItemService.get(filter);
        /*校验用户是否已兑换该项目*/
        if (checkUserIsBuyItem(customerId, item.getPeriodsNum())) {
            // 已购买，不能重复购买
            return new JsonResult(false).setMsg("xiangmuyigoumai");
        }
        /*校验最低购买数量*/
        if (new BigDecimal(exchangeNum).compareTo(config.getSingleMinNum()) == -1) {
            return new JsonResult(false).setMsg("duihuanshuliangyouwu");
        }
        /*校验用户购买数量是否已到达100%*/
        if (config.getHasChangeRatio().compareTo(new BigDecimal("100")) > -1) {
            // 已全部出售
            return new JsonResult(false).setMsg("yiquanbuchushou");
        }
        /*校验用户购买数量实际是否达到100%*/
        if (item.getHasBuyNum().compareTo(item.getTotalNum()) > -1) {
            // 已全部出售
            return new JsonResult(false).setMsg("yiquanbuchushou");
        }
        /*校验用户是否降要购买数量超过最大值*/
        BigDecimal hasBuyNum = this.checkUserBugItemCount(customerId, item.getPeriodsNum());
        // 将要购买数量
        BigDecimal willBuyNum = new BigDecimal(exchangeNum).add(hasBuyNum);
        if (willBuyNum.compareTo(config.getSoloMaxNum()) == 1) {
            BigDecimal surplusNum = config.getSoloMaxNum().subtract(hasBuyNum);
            // 购买以超过最大限额
            return new JsonResult(false).setMsg("goumaichaoguozuidaxiane").setObj(surplusNum);
        }
        Boolean lock = redisService.lock("exchangeItem:"+customerId);
        if (lock) {
            // 使用币
            String sellCode = config.getPayCoinCode();
            // 将要换的币
            String buyCode = config.getGainCoinCode();
            // 汇率
            BigDecimal ratio = config.getRatio();
            // 交易数量
            BigDecimal gainNum = new BigDecimal(exchangeNum);
            // 支付币数量
            BigDecimal payNum = gainNum.multiply(ratio);

            /*校验支付币种是否足够*/
            ExDigitalmoneyAccountRedis payAccount = this.selectAccount(Long.parseLong(customerId), sellCode);
            if (payAccount.getHotMoney().compareTo(payNum) == -1) {
                redisService.unLock("exchangeItem:"+customerId);
                return new JsonResult(false).setMsg("duihuanshuliangyouwu");
            }
            List<Accountadd> list = new ArrayList<Accountadd>();
            String exchangeSn = IdGenerate.transactionNum("07");
            // 支付币种---热账户减少
            list.add(AccountUtil.getAccountAdd(payAccount.getId(), new BigDecimal("-" + payNum), 1, 1, 55,
                    exchangeSn));
            // 兑换币种---热账户增加
            ExDigitalmoneyAccountRedis buyAccount = this.selectAccount(Long.parseLong(customerId), buyCode);
            list.add(AccountUtil.getAccountAdd(buyAccount.getId(), new BigDecimal("+" + gainNum), 1, 1, 55,
                    exchangeSn));
            /*添加兑换记录*/
            ExchangeRecord record = new ExchangeRecord();
            record.setCustomerId(Long.parseLong(customerId));
            record.setExchangeSn(exchangeSn);
            record.setItemId(item.getId());
            record.setItemName(item.getItemName());
            record.setRatio(config.getRatio());
            record.setPayCoinCode(sellCode);
            record.setGainCoinCode(buyCode);
            record.setPayNum(payNum);
            record.setGainNum(gainNum);
            record.setPeriodsNum(item.getPeriodsNum());
            exchangeRecordService.save(record);
            /*修改项目统计值*/
            this.updateItem(item.getId(), gainNum);
            // 币账户修改 发送mq
            messageProducer.toAccount(JSON.toJSONString(list));
            redisService.unLock("exchangeItem:"+customerId);
        }
        return new JsonResult(true).setMsg("duihuanchenggong");
    }

    @Override
    public FrontPage findUserToExchangeRecord(Map<String, String> map) {
        Page<ExchangeRecord> page = PageFactory.getPage(map);
        QueryFilter filter = new QueryFilter(ExchangeRecord.class);
        filter.addFilter("customerId=", map.get("customerId"));
        List<ExchangeRecord> recordList = exchangeRecordService.find(filter);
        return new FrontPage(recordList, page.getTotal(), page.getPages(), page.getPageSize());
    }

    /**
     *  校验时间
     * */
    private Boolean hourMinuteBetween (String nowDate, String startDate, String endDate) {
        Boolean flag = false;
        try {
            SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
            Date now = format.parse(nowDate);
            Date start = format.parse(startDate);
            Date end = format.parse(endDate);
            long nowTime = now.getTime();
            long startTime = start.getTime();
            long endTime = end.getTime();
            flag = nowTime >= startTime && nowTime <= endTime;
        } catch (Exception e) {
            e.getLocalizedMessage();
            flag = false;
        }
        return flag;
    }

    private Boolean checkUserIsBuyItem(String customerId, Integer periodsNum) {
        boolean flag = false;
        // 校验已结束的阶段项目中是否有购买记录
        QueryFilter filter = new QueryFilter(ExchangeRecord.class);
        filter.addFilter("customerId =", customerId);
        filter.addFilter("periodsNum !=", periodsNum);
        List<ExchangeRecord> recordList = exchangeRecordService.find(filter);
        if (recordList != null && recordList.size() > 0) {
            flag = true;
        }
        return flag;
    }

    private BigDecimal checkUserBugItemCount(String customerId, Integer periodsNum){
        // 查询当期已购买数量
        BigDecimal hasBuyNum = new BigDecimal("0");
        QueryFilter filter = new QueryFilter(ExchangeRecord.class);
        filter.addFilter("customerId =", customerId);
        filter.addFilter("periodsNum =", periodsNum);
        List<ExchangeRecord> recordList = exchangeRecordService.find(filter);
        for (ExchangeRecord record : recordList) {
            hasBuyNum = hasBuyNum.add(record.getGainNum());
        }
        return hasBuyNum;
    }

    /**
     * 查询币账户信息
     *
     * @param customerId
     * @param coinCode
     * @return
     */
    private ExDigitalmoneyAccountRedis selectAccount(Long customerId, String coinCode) {
        // 查redis缓存
        RedisUtil<UserRedis> redisUtil = new RedisUtil<UserRedis>(UserRedis.class);
        UserRedis userRedis = redisUtil.get(customerId.toString());
        // 获得币账户
        ExDigitalmoneyAccountRedis exaccount = UserRedisUtils.getAccount(userRedis.getDmAccountId(coinCode).toString(),
                coinCode);
        return exaccount;
    }

    /**
     * 修改兑换项目统计值
     * */
    public synchronized void updateItem(Long itemId, BigDecimal gainNum) {
        ExchangeItem item = exchangeItemService.get(itemId);
        item.setHasBuyNum(item.getHasBuyNum().add(gainNum));
        exchangeItemService.update(item);
    }

    @Override
    public void payAndExtractExchangeTotal() {
        Date startDate = new Date();
        exchangeTotalService.payAndExtractExchangeTotal();
        Date endDate = new Date();
        /*~~~~~~~~~~~~~定时器执行日志---添加~~~~~~~~~~*/
        taskLogService.saveLog("payAndExtractExchangeTotal", 0, startDate, endDate);
    }
}
