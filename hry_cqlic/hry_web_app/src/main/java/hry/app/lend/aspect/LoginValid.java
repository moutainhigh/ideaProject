package hry.app.lend.aspect;

import java.lang.annotation.*;

/**
 * 必传参数 HttpServletRequest request  /  Long customerId 没有顺序要求
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoginValid {
    boolean isLogin() default true;

    /**
     * 用户未登录时,是否进入方法
     * 进入方法时customerId会传空
     */
    boolean isInMethod() default false;

}
