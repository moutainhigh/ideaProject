package hry.util;

import hry.redis.common.utils.RedisService;
import hry.social.manage.remote.model.user.AppCustomer;
import hry.social.manage.remote.model.user.AppPersonInfo;
import hry.social.manage.remote.model.user.AppUserInfo;
import hry.util.sys.ContextUtil;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * @author javal
 * @title: UserInfoUtil
 * @projectName hurong_matching2019
 * @description: 用户信息处理类
 * @date 2019/5/3118:34
 */
public class UserInfoUtil {
    /**
     * 聚合AppUser
     *
     * @param appCustomer
     * @param appPersonInfo
     * @return
     */
    public static AppUserInfo combineUser(AppCustomer appCustomer, AppPersonInfo appPersonInfo) {
        AppUserInfo appUserInfo = new AppUserInfo();
        if (appCustomer != null) {
            int hasAcPwd = StringUtils.isEmpty(appCustomer.getAccountPassWord()) ? 0 : 1;
            int isReal = appCustomer.getIsReal() == null ? 0 : appCustomer.getIsReal().intValue();
            String commonLanguage = appCustomer.getCommonLanguage();
            commonLanguage = commonLanguage != null ? commonLanguage : "zh_CN";
            appUserInfo.setIsReal(isReal);
            appUserInfo.setHasAcPwd(hasAcPwd);
            appUserInfo.setCommonLanguage(commonLanguage);
            appUserInfo.setCommendCode(appCustomer.getCommendCode());
            appUserInfo.setUserName(appCustomer.getUserName());
            appUserInfo.setUserCode(appCustomer.getUserCode());
            appUserInfo.setCustomerId(appCustomer.getId());
            appUserInfo.setIsChange(appCustomer.getIsChange());
            appUserInfo.setIsDelete(appCustomer.getIsDelete());
            appUserInfo.setPassWord(appCustomer.getPassWord());
            appUserInfo.setOldUuid(appCustomer.getUuid());
            appUserInfo.setUuid(appCustomer.getUuid());
            appUserInfo.setStates(appCustomer.getStates());
            appUserInfo.setReferralCode(appCustomer.getReferralCode());
            appUserInfo.setHasEmail(appCustomer.getHasEmail());
            appUserInfo.setPhoneState(appCustomer.getPhoneState());
            appUserInfo.setShopCommendCode(appCustomer.getShopCommendCode());
            appUserInfo.setShopInvitationCode(appCustomer.getShopInvitationCode());
            appUserInfo.setSsoId(appCustomer.getSsoId());
            appUserInfo.setFansGroupId(appCustomer.getFansGroupId());
            Date created = appCustomer.getCreated();
            if (null == created) {
                created = new Date();
            }
            long time = created.getTime();
            String reg_default = String.valueOf(time);
            String userName = appUserInfo.getUserName();
            RedisService redisService = (RedisService) ContextUtil.getBean("redisService");
            String reg_modify = redisService.get("REG_MODIFY:" + userName);
            reg_modify = StringUtils.isEmpty(reg_modify) ? reg_default : reg_modify;
            appUserInfo.setRegModify(reg_modify);
        }
        if (appPersonInfo != null) {
            appUserInfo.setHeadPortrait(appPersonInfo.getHeadPortrait());
            appUserInfo.setNickName(appPersonInfo.getNickName());
            appUserInfo.setAccid(appPersonInfo.getAccid());
            appUserInfo.setToken(appPersonInfo.getToken());
            appUserInfo.setCountry(appPersonInfo.getCountry());
            appUserInfo.setMobilePhone(appPersonInfo.getMobilePhone());
            appUserInfo.setMood(appPersonInfo.getMood());
            appUserInfo.setThemeImg(appPersonInfo.getThemeImg());
            appUserInfo.setCardType(appPersonInfo.getCardType());
            appUserInfo.setCardId(appPersonInfo.getCardId());
            appUserInfo.setQuickMark(appPersonInfo.getQuickMark());
            appUserInfo.setTruename(appPersonInfo.getTrueName());
            appUserInfo.setSurname(appPersonInfo.getSurname());
            appUserInfo.setInviteCode(appPersonInfo.getInviteCode());
            appUserInfo.setShopInviteCode(appPersonInfo.getShopInviteCode());
            appUserInfo.setEmail(appPersonInfo.getEmail());
            appUserInfo.setEmailCode(appPersonInfo.getEmailCode());
            appUserInfo.setCountryArea(appPersonInfo.getCountryArea());
        }

        return appUserInfo;
    }
}
