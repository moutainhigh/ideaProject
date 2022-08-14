package hry.app.lend.aspect;

import hry.app.jwt.TokenUtil;
import hry.app.lend.model.LendUser;
import hry.bean.JsonResult;
import hry.manage.remote.model.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.BeanUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author <a href="mailto:HelloHeSir@gmail.com">Mr_He</a>
 * @Copyright (c)</                                                               b> HeC<br/>
 * @createTime 2018/7/26 11:24
 * @Description:登陆校验aop
 */
@Aspect
@Component
@Order(100)
public class LoginValidAop {

    @Pointcut("@annotation(hry.app.lend.aspect.LoginValid)")
    private void anyMethod() {
    }

    /**
     * 必传参数 HttpServletRequest request  / LendUser user
     */
    @Around("anyMethod()&&@annotation(loginValid)")
    public Object checkLogin(ProceedingJoinPoint point, LoginValid loginValid) {
        try {
            Object[] args = point.getArgs();
            HttpServletRequest request = null;
            LendUser lendUser = new LendUser();
            int idx = -1;
            for (int i = 0; i < args.length; i++) {
                if (args[i] instanceof HttpServletRequest) {
                    request = (HttpServletRequest) args[i];
                }
                if (args[i] instanceof LendUser) {
                    idx = i;
                }
            }
            if (null != request) {
                if (idx == -1) {
                    throw new RuntimeException("customerId参数不正确");
                }
                User user = TokenUtil.getLendUser(request);
                if (null != user) {
                    BeanUtils.copyProperties(user, lendUser);
//                    lendUser.setCustomerId(481127L);
                    if (null != lendUser.getCustomerId()) {
                        args[idx] = lendUser;
                        return point.proceed(args);
                    }
                } else if (loginValid.isInMethod()) {
                    return point.proceed(args);
                } else {
                    return new JsonResult("请先登录");
                }
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return new JsonResult("操作失败");
    }

}