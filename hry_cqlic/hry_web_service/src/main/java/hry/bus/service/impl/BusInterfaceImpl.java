package hry.bus.service.impl;

import com.alibaba.fastjson.JSON;
import hry.account.remote.RemoteAppAccountService;
import hry.bus.model.BusCustomerTO;
import hry.bus.service.BusInterface;
import hry.customer.commend.service.AppCommendUserService;
import hry.customer.person.model.AppPersonInfo;
import hry.customer.person.service.AppPersonInfoService;
import hry.customer.user.model.AppCustomer;
import hry.customer.user.service.AppCustomerService;
import hry.exchange.remote.account.RemoteExProductService;
import hry.klg.level.service.KlgCustomerLevelService;
import hry.klg.level.service.KlgTeamlevelService;
import hry.manage.remote.MsgTypeEnum;
import hry.manage.remote.RemoteManageService;
import hry.manage.remote.model.User;
import hry.util.UUIDUtil;
import hry.util.sys.ContextUtil;
import hry.web.message.service.AppMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.MathUtil;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service("busInterface")
public class BusInterfaceImpl implements BusInterface {

    @Autowired
    private AppPersonInfoService appPersonInfoService;

    @Autowired
    private AppCustomerService appCustomerService;

    @Autowired
    private AppCommendUserService appCommendUserService;

    @Autowired
    private AppMessageService appMessageService;
    @Resource
    private KlgCustomerLevelService klgCustomerLevelService;
    @Resource
    private KlgTeamlevelService klgTeamlevelService;
    @Override
    public User login(String loginusername, BusCustomerTO busCustomerTO) {

        String password =busCustomerTO.getPassword();
        String email = busCustomerTO.getEmail();
        String mobile = busCustomerTO.getMobile();
        String salt = busCustomerTO.getSalt();
        String number = busCustomerTO.getNumber();
        String country = busCustomerTO.getCountry();
        String referralCode = busCustomerTO.getReferralCode()==null?"":busCustomerTO.getReferralCode();//本人的推荐码
        String inviteCode = busCustomerTO.getInviteCode()==null?"":busCustomerTO.getInviteCode();//邀请码---别人的


        //获取盐值
        AppCustomer customer = new AppCustomer();
        //设置总线编号
        customer.setBusNumber(number);
        String uuid = UUIDUtil.getUUID();
        // 设置用户名唯一ID
        customer.setUserName(uuid);
        // 设置用户唯一ID
        customer.setUserCode(uuid);
        // 设置密码
        customer.setPassWord(password);
        // 设置谷歌认证初始化为0
        customer.setGoogleState(0);
        // 设置手机认证初始化为0
        customer.setPhoneState(0);
        // 设置实名状态
        customer.setStates(0);
        // 设置实名
        customer.setIsReal(0);
        // 设置推荐码
        if(referralCode==null||referralCode.equals("")){
        	referralCode = MathUtil.generateShortUuid();
        }
        customer.setReferralCode(referralCode);
        //设置邀请码-别人的
        customer.setCommendCode(inviteCode);
        // 设置盐值
        customer.setSalt(salt);
        // 密码加密
        customer.setPassWord(password);
        // 设置密码激活
        customer.setHasEmail(1);
        // 保存appCustomer
        customer.setCommonLanguage("zh_CN");
        appCustomerService.save(customer);

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("customerLocalNumber",customer.getId());
        //BusCustomerUtil.updateCustomer(busCustomerNumber,map);

        // 初始化数据AppPersonInfo
        AppPersonInfo appPersonInfo = new AppPersonInfo();
        // 设置默认国家
        appPersonInfo.setCountry(country);
        // 设置客户来源
        appPersonInfo.setCustomerSource(0);
        // 设置customerId
        appPersonInfo.setCustomerId(customer.getId());
        // 设置客户类型
        appPersonInfo.setCustomerType(1);
        // 设置邮箱激活码
        appPersonInfo.setEmailCode(UUIDUtil.getUUID());
        // 设置邮箱
        appPersonInfo.setEmail(email);
        // 设置手机号
        appPersonInfo.setMobilePhone(mobile);
        appPersonInfoService.save(appPersonInfo);

        // 开通人民币账户
        RemoteAppAccountService remoteAppAccountService = (RemoteAppAccountService) ContextUtil.getBean("remoteAppAccountService");
        remoteAppAccountService.openAccount(customer, appPersonInfo, "zh_CN", ContextUtil.getWebsite());

        // 开通虚拟币账户
        RemoteExProductService remoteExProductService = (RemoteExProductService) ContextUtil.getBean("remoteExProductService");
        remoteExProductService.openDmAccount(customer, appPersonInfo, null, ContextUtil.getWebsite(), "zh_CN");

        // 推荐人
        appCommendUserService.saveObj(customer);

        //发送站内内信
        appMessageService.sysSendMsg(customer, MsgTypeEnum.REGIST);

        try {
            addUserLevel(customer);
        }catch (Exception e){
            System.out.println("添加等级关系和 用户当前等级  异常"+ JSON.toJSONString(customer));
        }

        User user = new User();
        //设置登录后显示的名称,登录用什么登录就显示什么(邮箱或者手机号)
        user.setNickName(loginusername);

        user.setUsername(customer.getUserName());
        user.setUserCode(customer.getUserCode());
        user.setAccountPassWord(customer.getAccountPassWord());
        user.setIsReal(customer.getIsReal() == null ? 0 : customer.getIsReal().intValue());
        user.setIsDelete(customer.getIsDelete() == null ? 0 : customer.getIsDelete().intValue());
        user.setIsChange(customer.getIsChange() == null ? 0 : customer.getIsChange().intValue());
        user.setCustomerId(customer.getId());
        // 国家
        user.setCountry(appPersonInfo.getCountry());
        // 手机号
        user.setMobile(appPersonInfo.getMobilePhone());
        // 是否开启平台币当手续费用开关
        user.setIsOpenCoinFee(appPersonInfo.getIsOpenCoinFee()== null ? 0 : appPersonInfo.getIsOpenCoinFee().intValue());
        user.setSurname(appPersonInfo.getSurname());
        user.setTruename(appPersonInfo.getTrueName());
        user.setIsLock(customer.getIsLock()==null?0 : customer.getIsLock().intValue());
        user.setCustomerType(appPersonInfo.getCustomerType());
        user.setSalt(customer.getSalt());
        user.setGoogleKey(customer.getGoogleKey());
        user.setGoogleState(customer.getGoogleState());
        user.setMessIp(customer.getMessIp());
        user.setPassDate(customer.getPassDate());
        user.setPhoneState(customer.getPhoneState());
        user.setStates(customer.getStates());
        user.setPassword(customer.getPassWord());
        user.setUuid(uuid);
        //设置邮箱
        user.setEmail(appPersonInfo.getEmail());
        // 邮箱是否激活
        user.setHasEmail(customer.getHasEmail());

        user.setSafeLoginType(customer.getSafeLoginType());
        user.setSafeTixianType(customer.getSafeTixianType());
        user.setSafeTradeType(customer.getSafeTradeType());

        user.setIsBlacklist(customer.getIsBlacklist());

        return user;
    }


    /**
     *
     *添加等级关系和 用户当前等级
     */
    private  void addUserLevel(AppCustomer customer){
        klgCustomerLevelService.addUserLevel(customer);
        klgTeamlevelService.addUser(customer);//维护新推荐关系
    }

}
