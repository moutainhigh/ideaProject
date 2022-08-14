package hry.mall.manage.remote;

/**
 * @auther zhouming
 * @date 2019/7/5 10:30
 * @Version 1.0
 */
public interface RemoteMallManageService {

    /**
    * 用户注册后执行监听
    * 例如：生成积分账户、 注册发放积分...
    * */
    public void executeListener();

    /**
     * 生成用户业绩账户
     * */
    public void addShopMemberPerformance();

    /**
     * 为用户添加默认等级
     * */
    public void addDefaultLevel();
    
    /**
     * 查询微星订单
     */
    public void payWeiOrderQuery();
    
    

}
