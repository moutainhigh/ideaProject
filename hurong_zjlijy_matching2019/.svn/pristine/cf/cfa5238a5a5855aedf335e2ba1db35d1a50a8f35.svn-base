package hry.cm5.remote;

import com.github.pagehelper.Page;
import hry.bean.FrontPage;
import hry.bean.JsonResult;
import hry.bean.ObjectUtil;
import hry.cm5.miner.model.Cm5MinerGoods;
import hry.cm5.miner.model.Cm5MinerOrder;
import hry.cm5.miner.service.Cm5MinerGoodsService;
import hry.cm5.miner.service.Cm5MinerOrderService;
import hry.cm5.remote.model.Cm5MinerGoodsRemote;
import hry.cm5.remote.model.Cm5MinerOrderRemote;
import hry.redis.common.utils.RedisService;
import hry.util.PageFactory;
import hry.util.QueryFilter;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author zhouming
 * @date 2020/1/9 10:14
 * @Version 1.0
 */
public class RemoteCmCustomerMinerServiceImpl implements RemoteCmCustomerMinerService{

    @Resource
    private Cm5MinerGoodsService cm5MinerGoodsService;
    @Resource
    private Cm5MinerOrderService cm5MinerOrderService;
    @Resource
    private RedisService redisService;

    @Override
    public FrontPage findCustomerMinerList(Map<String, String> params) {
        //用户id
        Long customerId = Long.valueOf(params.get("customerId"));
        Page page = PageFactory.getPage(params);
        QueryFilter filter = new QueryFilter(Cm5MinerGoods.class);
        filter.addFilter("states=", 0);
        List<Cm5MinerGoods> minerGoodsList = cm5MinerGoodsService.find(filter);
        QueryFilter filter1 = new QueryFilter(Cm5MinerOrder.class);
        filter1.addFilter("customerId=", customerId);
        List<Cm5MinerOrder> minerOrderList = cm5MinerOrderService.find(filter1);
        /*~~~业务需求---一个用户只能购买一个矿机，则此处一个用户一笔订单代表一个矿机*/
        for (Cm5MinerGoods minerGoods : minerGoodsList) {
            Integer minerStates = 1; // 矿机激活状态 1 待激活 2 已升级 3 运行中
            for (Cm5MinerOrder minerOrder : minerOrderList) {
                if (minerGoods.getId().equals(minerOrder.getMinerId())) {
                    if (minerOrder.getStatus() == 1) {
                        minerStates = 3;
                    } else if (minerOrder.getStatus() == 2) {
                        minerStates = 2;
                    }
                }
            }
            minerGoods.setMinerStates(minerStates);
            // 注：如矿机正在运行中，计算出矿机的累计收益
            if (minerStates == 3) {
                // 累计收益 --- 待计算
                minerGoods.setTotalAward(new BigDecimal("119"));
            }
        }
        List<Cm5MinerGoodsRemote> beanList = ObjectUtil.beanList(minerGoodsList, Cm5MinerGoodsRemote.class);
        return new FrontPage(beanList, page.getTotal(), page.getPages(), page.getPageSize());
    }

    @Override
    public JsonResult buyMinerGoods(Map<String, String> params) {
        // 用户id
        Long customerId = Long.valueOf(params.get("customerId"));
        // 要购买的矿机Id
        Long minerId = Long.valueOf(params.get("minerId"));
        Boolean flag = redisService.lock("CM5:BUYMINER:"+customerId);
        if (!flag) {
            //系统繁忙
            return new JsonResult(false).setMsg("cm_xitongfanmang");
        }
        Cm5MinerGoods willBuyMiner = cm5MinerGoodsService.get(minerId);
        // 查询已经购买的矿机
        QueryFilter filter = new QueryFilter(Cm5MinerOrder.class);
        filter.addFilter("customerId=", customerId);
        filter.setOrderby("id desc");
        Cm5MinerOrder minerOrder = cm5MinerOrderService.get(filter);
        // status 矿机状态 1：运行中 2：已升级(低版本升高版本--需继承上一版本) 3:已结束(已出局---未发收益给下笔订单（矿机）)
        if (minerOrder != null && minerOrder.getStatus() != 3) {
            if (minerOrder.getStatus() == 1) {
                // 校验：欲购买的矿机比已购买正在运行的矿机价格低，则判断为低等级矿机则不能购买
                if (willBuyMiner.getMinerPrice().compareTo(minerOrder.getMinerPrice()) < 1) {
                    redisService.unLock("CM5:BUYMINER:"+customerId);
                    return new JsonResult(false).setMsg("不能购买比当前低的矿机");
                }
                // 矿机升级---保存订单
                JsonResult result = cm5MinerOrderService.saveMinerOrder(customerId, minerId, minerOrder);
                redisService.unLock("CM5:BUYMINER:"+customerId);
                return result;
            } else {
                redisService.unLock("CM5:BUYMINER:"+customerId);
                return new JsonResult(false).setMsg("用户矿机信息有误");
            }
        } else {
            // 矿机购买---保存订单
            JsonResult result = cm5MinerOrderService.saveMinerOrder(customerId,minerId, null);
            redisService.unLock("CM5:BUYMINER:"+customerId);
            return result;
        }
    }

    @Override
    public FrontPage findMinerOrderList(Map<String, String> params) {
        //用户id
        Long customerId = Long.valueOf(params.get("customerId"));
        Page page = PageFactory.getPage(params);
        QueryFilter filter = new QueryFilter(Cm5MinerOrder.class);
        filter.addFilter("customerId=", customerId);
        List<Cm5MinerOrder> minerOrderList = cm5MinerOrderService.find(filter);
        List<Cm5MinerOrderRemote> beanList = ObjectUtil.beanList(minerOrderList, Cm5MinerOrderRemote.class);
        return new FrontPage(beanList, page.getTotal(), page.getPages(), page.getPageSize());
    }

    @Override
    public JsonResult getMinerOrderDetails(String minerOrderId) {
        Cm5MinerOrder cm5MinerOrder = cm5MinerOrderService.get(Long.parseLong(minerOrderId));
        Cm5MinerOrderRemote cm5MinerOrderRemote = ObjectUtil.bean2bean(cm5MinerOrder, Cm5MinerOrderRemote.class);
        return new JsonResult(true).setObj(cm5MinerOrderRemote);
    }
}
