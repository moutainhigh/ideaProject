package hry.manage.remote;

import hry.bean.JsonResult;
import hry.manage.remote.model.AppWorkOrder;
import hry.manage.remote.model.AppWorkOrderCategory;
import hry.manage.remote.model.base.FrontPage;

import java.util.List;
import java.util.Map;

/**
 * 我的工单接口
 *
 * @author syj
 * <p>
 * 2017年7月19日
 */
public interface RemoteWorkOrderService {

    /**
     * 获取当前登录用户所有工单
     *
     * @param params
     * @return 2018年5月4日
     * syj
     */
    FrontPage findWorkOrder (Map<String, String> params);


    /**
     * 根据id删除工单
     *
     * @param ids
     * @return 2018年5月4日
     * syj
     */
    JsonResult deleteBatch (String ids);

    /**
     * 获取所有工单类别
     *
     * @param params
     * @return 2018年5月4日
     * syj
     */
    List<AppWorkOrderCategory> findWorkOrderCategoryList (Map<String, String> params);

    /**
     * 添加工单
     *
     * @param appWorkOrder
     * @return 2018年5月4日
     * syj
     */
    JsonResult add (AppWorkOrder appWorkOrder);

    /**
     * 根据id获取工单详情
     *
     * @param sid
     * @return 2018年5月4日
     * syj
     */
    AppWorkOrder get (Long sid);

}
