package hry.app.remote;

import hry.config.redis.Config4RedisUtil;
import hry.customer.commend.service.AppCommendUserService;
import hry.customer.person.model.AppPersonInfo;
import hry.customer.person.service.AppPersonInfoService;
import hry.customer.user.model.AppCustomer;
import hry.customer.user.service.AppCustomerService;
import hry.manage.remote.model.User;
import hry.util.QueryFilter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

public class RemoteUserServiceImpl implements RemoteUserService {

    @Autowired
    private AppPersonInfoService appPersonInfoService;

    @Autowired
    private AppCustomerService appCustomerService;

    @Resource
    private AppCommendUserService appCommendUserService;

    @Override
    public User getUser(String username) {

        AppCustomer appCustomer = null;
        //登录方式,默认为邮箱
        String loginType = "email";
        if (username.contains("@")) {// 邮箱登录查找用户信息
            QueryFilter f = new QueryFilter(AppPersonInfo.class);
            f.addFilter("email=", username);
            AppPersonInfo appPersonInfo = appPersonInfoService.get(f);
            if (appPersonInfo != null) {
                appCustomer = appCustomerService.get(appPersonInfo.getCustomerId());
            }
        } else {// 手机登录查找用户信息
            //手机登录
            loginType = "mobile";
            QueryFilter f = new QueryFilter(AppPersonInfo.class);
            f.addFilter("mobilePhone=", username);
            AppPersonInfo appPersonInfo = appPersonInfoService.get(f);
            if (appPersonInfo != null) {
                appCustomer = appCustomerService.get(appPersonInfo.getCustomerId());
            }

        }

        // 判断用户是否存在
        if (appCustomer != null) {
            // 查询appPersonInfo
            QueryFilter filter = new QueryFilter(AppPersonInfo.class);
            filter.addFilter("customerId=", appCustomer.getId());
            AppPersonInfo apppersonInfo = appPersonInfoService.get(filter);

            User user = new User();
            user.setLoginType(loginType);
            //设置登录后显示的名称,登录用什么登录就显示什么(邮箱或者手机号)
            user.setNickName(username);

            user.setUsername(appCustomer.getUserName());
            user.setUserCode(appCustomer.getUserCode());
            user.setAccountPassWord(appCustomer.getAccountPassWord());
            user.setIsReal(appCustomer.getIsReal() == null ? 0 : appCustomer.getIsReal().intValue());
            user.setIsDelete(appCustomer.getIsDelete());
            user.setIsChange(appCustomer.getIsChange());
            user.setCustomerId(appCustomer.getId());
            // 国家
            user.setCountry(apppersonInfo.getCountry());
            // 手机号
            user.setMobile(apppersonInfo.getMobilePhone());
            // 是否开启平台币当手续费用开关
            user.setIsOpenCoinFee(apppersonInfo.getIsOpenCoinFee());
            user.setSurname(apppersonInfo.getSurname());
            user.setTruename(apppersonInfo.getTrueName());
            user.setIsLock(appCustomer.getIsLock());
            user.setCustomerType(apppersonInfo.getCustomerType());
            user.setSalt(appCustomer.getSalt());
            user.setGoogleKey(appCustomer.getGoogleKey());
            user.setGoogleState(appCustomer.getGoogleState());
            user.setMessIp(appCustomer.getMessIp());
            user.setPassDate(appCustomer.getPassDate());
            user.setPhoneState(appCustomer.getPhoneState());
            user.setStates(appCustomer.getStates());
            user.setPassword(appCustomer.getPassWord());
            //设置邮箱
            user.setEmail(apppersonInfo.getEmail());
            // 邮箱是否激活
            user.setHasEmail(appCustomer.getHasEmail());

            user.setSafeLoginType(appCustomer.getSafeLoginType());
            user.setSafeTixianType(appCustomer.getSafeTixianType());
            user.setSafeTradeType(appCustomer.getSafeTradeType());
            user.setCommonLanguage(appCustomer.getCommonLanguage());  //常用语言

            user.setIsBlacklist(appCustomer.getIsBlacklist());
            user.setFeature(appCustomer.getFeature());
            // 是否展示推荐返佣
            String commend = Config4RedisUtil.getCnfigValue("commend_switch");
            if (commend != null && !"".equals(commend)) {
                user.setCommend(Integer.valueOf(commend));
            }
            appCustomerService.update(appCustomer);

            String cardCurrency = Config4RedisUtil.getCnfigValue("card_Currency");
            // 未实名每日提币量
            String uncardCurrency = Config4RedisUtil.getCnfigValue("uncard_Currency");
            if (cardCurrency != null && uncardCurrency != null) {
                user.setCardCurrency(cardCurrency);
                user.setUncardCurrency(uncardCurrency);
            }
            user.setNickNameOtc(appCustomer.getNickNameOtc());
            return user;
        }
        return null;

    }

    @Override
    public void addNumber() {
        try {
            appCommendUserService.addNumber();
        } catch (Exception e) {
            e.getLocalizedMessage();
        }
    }
}
