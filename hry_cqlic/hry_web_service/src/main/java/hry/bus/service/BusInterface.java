package hry.bus.service;

import hry.bus.model.BusCustomerTO;
import hry.manage.remote.model.User;

public interface BusInterface {

    /**
     * 总线同步登录
     * @param loginusername   登录时用户名
     * @param busCustomerStr  同步用户数据
     * @return
     */
    User login(String loginusername,BusCustomerTO busCustomerTO);

}
