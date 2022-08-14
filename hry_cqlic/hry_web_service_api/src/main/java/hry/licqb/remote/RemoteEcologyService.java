package hry.licqb.remote;

import hry.bean.FrontPage;
import hry.bean.JsonResult;

import java.util.Map;

/**
 * @author zhouming
 * @date 2020/6/4 18:49
 * @Version 1.0
 */
public interface RemoteEcologyService {

    /**
     * 申请列表
     * */
    public FrontPage findEcofundPageList(Map<String, String> map);

    /**
     * 获取生态基金
     * */
    public JsonResult getEcofund(Map<String, String> map);

    /**
     * 申请生态基金
     * */
    public JsonResult applyEcofund(Map<String, String> map);

    /**
     * 用户修改订单状态
     * */
    public JsonResult updateEcofund(Map<String, String> map);

    /**
     * 获取用户未完成订单
     * */
    public JsonResult getUnEcofund(Long customerId);

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~生态入驻接口~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    /**
     * 生态入驻---申请列表
     * */
    public FrontPage findEcologEnterPageList(Map<String, String> map);

    /**
     * 获取生态入驻详情
     * */
    public JsonResult getEcologEnter(Map<String, String> map);

    /**
     * 生态入驻---版块列表
     * */
    public FrontPage findEcologPlatePageList(Map<String, String> map);

    /**
     * 生态入驻---已入驻列表
     * */
    public FrontPage findHasEnterList(Map<String, String> map);
    /**
     * 生态入驻 --- 申请
     * */
    public JsonResult applyEcologEnter(Map<String, String> map);

    /**
     * 用户修改入驻订单状态
     * */
    public JsonResult updateEcologEnter(Map<String, String> map);

    /**
     * 校验是否可续费
     * */
    public JsonResult checkRenew(Map<String, String> map);

    /**
     * 续费确认
     * */
    public JsonResult affirmRenew(Map<String, String> map);

    /**
     * 定时器---修改已核实订单已到期订单
     * */
    public void updateEcologEnter();

}
