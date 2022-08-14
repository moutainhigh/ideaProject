/**
 * Copyright:
 *
 * @author: tianpengyu
 * @version: V1.0
 * @Date: 2018-06-25 19:49:22
 */
package hry.admin.commend.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import hry.admin.commend.dao.AppCommendMoneyDao;
import hry.admin.commend.model.AppCommendMoney;
import hry.admin.commend.model.AppCommendRebat;
import hry.admin.commend.service.AppCommendMoneyService;
import hry.admin.commend.service.AppCommendRebatService;
import hry.admin.mq.producer.service.MessageProducer;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.front.redis.model.UserRedis;
import hry.listener.ServerManagement;
import hry.redis.common.dao.RedisUtil;
import hry.redis.common.utils.RedisService;
import hry.trade.redis.model.Accountadd;
import hry.util.BaseConfUtil;
import hry.util.PageFactory;
import hry.util.QueryFilter;
import hry.util.idgenerate.IdGenerate;
import hry.util.sys.ContextUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * <p> AppCommendMoneyService </p>
 *
 * @author: tianpengyu
 * @Date :          2018-06-25 19:49:22
 */
@Service("appCommendMoneyService")
public class AppCommendMoneyServiceImpl extends BaseServiceImpl<AppCommendMoney, Long> implements AppCommendMoneyService {
    private static Logger logger = Logger.getLogger(AppCommendMoneyServiceImpl.class);
    @Resource(name = "appCommendMoneyDao")
    @Override
    public void setDao(BaseDao<AppCommendMoney, Long> dao) {
        super.dao = dao;
    }

    @Resource
    private AppCommendRebatService appCommendRebatService;

    public static final String APP_COMMOD_REBAT = "21";

    @Resource
    private MessageProducer messageProducer;


    @Override
    public PageResult findPageBySql(QueryFilter filter) {

        // ----------------------分页查询头部外壳------------------------------
        Page<AppCommendMoney> page = PageFactory.getPage(filter);
        // ----------------------分页查询头部外壳------------------------------

        Map<String, Object> map = new HashMap<String, Object>();
        // ----------------------查询开始------------------------------
        String email = filter.getRequest().getParameter("email");
        String mobilePhone = filter.getRequest().getParameter("mobilePhone");
        String coinCode = filter.getRequest().getParameter("coinCode");

        if (!StringUtils.isEmpty(email)) {
            map.put("email", "%" + email + "%");
        }
        if (!StringUtils.isEmpty(coinCode)) {
            map.put("coinCode",  coinCode );
        }
        if (!StringUtils.isEmpty(mobilePhone)) {
            map.put("mobilePhone", "%" + mobilePhone + "%");
        }
        List<AppCommendMoney> pageBySql = ((AppCommendMoneyDao) dao).findPageBySql(map);
        RedisService redisService = (RedisService) ContextUtil
                .getBean("redisService");

        for (AppCommendMoney am : pageBySql) {
            String retentionNumber = redisService.get("retentionNumber:"
                    + am.getCoinCode());
            am.setRetentionNumber(retentionNumber);
        }
        // ----------------------查询结束------------------------------

        return new PageResult(page, filter.getPage(), filter.getPageSize());

    }

    public JsonResult postMoneyById(Long id, BigDecimal money) {
        JsonResult result = new JsonResult();
        result.setSuccess(false);
        QueryFilter queryFilter = new QueryFilter(AppCommendMoney.class);
        queryFilter.addFilter("id=", id);
        AppCommendMoney appCommendMoney = this.get(queryFilter);

        if (null != appCommendMoney) {
            appCommendMoney.setPaidMoney(appCommendMoney.getPaidMoney().add(money));
            appCommendMoney.setLastPaidTime(new Date());

            RedisUtil<UserRedis> redisUtil = new RedisUtil<UserRedis>(UserRedis.class);
            UserRedis userRedis = redisUtil.get(appCommendMoney.getCustromerId().toString());
            if (null == userRedis) {
                result.setMsg("此用户的缓存账号为空，登陆会生成缓存账号");
                result.setSuccess(false);
                return result;
            }
            this.update(appCommendMoney);

            // 存明细
            AppCommendRebat appCommendRebat = new AppCommendRebat();
            try {
                appCommendRebat.setCoinCode(appCommendMoney.getCoinCode());
                appCommendRebat.setRebatMoney(money);
                // appCommendRebat.setTrueName(appCommendMoney.getCustromerName());
                appCommendRebat.setCustomerId(appCommendMoney.getCustromerId());
                appCommendRebat.setTransactionNum(IdGenerate.transactionNum(APP_COMMOD_REBAT));
                appCommendRebat.setUserCode(appCommendMoney.getUserCode());

                String coin = BaseConfUtil.getConfigSingle("configCache:financeConfig", "language_code");


                if (appCommendMoney.getCoinCode().equals(coin)) {
                    // ----发送mq消息----start
                    // 热账户增加
                    Accountadd accountadd = new Accountadd();
                    Long accountId = userRedis.getAccountId();
                    accountadd.setAccountId(accountId);
                    accountadd.setMoney(money);
                    accountadd.setMonteyType(1);
                    accountadd.setRemarks(32);
                    accountadd.setAcccountType(0);
                    accountadd.setTransactionNum(appCommendRebat
                            .getTransactionNum());

                    List<Accountadd> list2 = new ArrayList<Accountadd>();
                    list2.add(accountadd);
                    messageProducer.toAccount(JSON.toJSONString(list2));
                } else {
                    // ----发送mq消息----start
                    // 热账户增加

                    Accountadd accountadd = new Accountadd();
                    Long accountId = userRedis.getDmAccountId(appCommendMoney.getCoinCode());
                    accountadd.setAccountId(accountId);
                    accountadd.setMoney(money);
                    accountadd.setMonteyType(1);
                    accountadd.setRemarks(32);
                    accountadd.setAcccountType(1);
                    accountadd.setTransactionNum(appCommendRebat.getTransactionNum());

                    List<Accountadd> list3 = new ArrayList<Accountadd>();
                    list3.add(accountadd);
                    messageProducer.toAccount(JSON.toJSONString(list3));
                }
                appCommendRebat.setStatus(1);
                result.setMsg("派送成功");
                result.setSuccess(true);
            } catch (Exception e) {
                appCommendRebat.setStatus(0);
                result.setMsg("派送失败");
                logger.error("消息发送失败：{}" + appCommendMoney.getCustromerName());
            }
            appCommendRebatService.save(appCommendRebat);
        }
        return result;
    }

}
