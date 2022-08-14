package hry.ico.service;

import hry.ico.model.IcoLockRecord;

//延时处理释放订单
public interface IDelayedOrderService {

    /**
     * 处理锁仓释放订单
     *
     * @param lockRecord
     */
    public void orderDelay(IcoLockRecord lockRecord);


    /**
     * 每次初始化需要当天释放订单到内存
     */
    void initDelayOrder();

}
