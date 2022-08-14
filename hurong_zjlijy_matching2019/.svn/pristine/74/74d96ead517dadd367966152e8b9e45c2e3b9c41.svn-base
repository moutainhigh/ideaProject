package hry.listener.mall;

import hry.mall.manage.remote.RemoteMallManageService;
import hry.util.SpringUtil;

/**
 * @auther zhouming
 * @date 2019/7/4 14:17
 * @Version 1.0
 */
public class MallManagement {

    /**
     * 注册相关监听
     * */
    public void registerListener () {

        // 获取未执行监听的注册用户
        RemoteMallManageService remoteMallManageService = SpringUtil.getBean("remoteMallManageService");
        remoteMallManageService.executeListener();
    }
    /**
     * 查询微星充话费订单
     */
    public void payWeiOrderListener () {

        // 获取未执行监听的注册用户
        RemoteMallManageService remoteMallManageService = SpringUtil.getBean("remoteMallManageService");
        remoteMallManageService.payWeiOrderQuery();
    }
}
