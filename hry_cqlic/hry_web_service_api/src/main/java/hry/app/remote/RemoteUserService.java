package hry.app.remote;

import hry.manage.remote.model.User;

public interface RemoteUserService {

    /**
     * 获取用户信息
     * @param username
     * @return
     */
    User getUser(String username);

    /**
     * 定时执行 增加一级推荐个数，二级推荐个数，三级推荐个数，四级以上推荐个数
     */
    void addNumber();


}
