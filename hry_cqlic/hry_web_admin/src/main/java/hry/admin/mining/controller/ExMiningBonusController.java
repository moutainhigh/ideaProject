package hry.admin.mining.controller;

import hry.bean.JsonResult;
import hry.calculate.remote.mining.RemoteExMiningRewardTimerService;
import hry.core.annotation.base.MethodName;
import hry.reward.model.page.FrontPage;
import hry.reward.model.page.RemoteResult;
import hry.util.DataUtil;
import hry.util.sys.ContextUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * Copyright:   北京互融时代软件有限公司
 *
 * @author: jidn
 * @version: V1.0
 * @Date: 2018-06-27 19:52:24
 */
@Controller
@RequestMapping("/mining/exminingbonus")
public class ExMiningBonusController {
    /**
     * 进入分红列表页面
     *
     * @param request
     * @return
     */
    @RequestMapping("/toExminingbonusList")
    public ModelAndView toEminingList(HttpServletRequest request) {
        String status = request.getParameter("status");
        if (null == status) {
            status = "0";
        }
        ModelAndView mav = new ModelAndView("/admin/mining/exminingbonuslist");
        mav.addObject("status", status);

        return mav;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @MethodName(name = "列表ExMiningBonus")
    @RequestMapping("/list")
    @ResponseBody
    public FrontPage list(HttpServletRequest request) {
        HashMap requestMap = DataUtil.getParameterMap(request);
        requestMap.put("start", requestMap.get("offset"));
        requestMap.put("length", requestMap.get("limit"));
        /*if (requestMap.containsKey("queryDate")) {
            String queryDate = requestMap.get("queryDate").toString();
            if (!"".equals(queryDate)) {
                String[] split = queryDate.split("-");
                requestMap.put("year", split[0]);
                requestMap.put("month", split[1]);
                requestMap.put("day", split[2]);
            }
        }*/

        RemoteExMiningRewardTimerService remoteExMiningRewardTimerService = (RemoteExMiningRewardTimerService) ContextUtil.getBean("remoteExMiningRewardTimerService");
        return remoteExMiningRewardTimerService.findBonusPageBySql(requestMap);
    }

    @MethodName(name = "手动分红")
    @RequestMapping("/manual")
    @ResponseBody
    public JsonResult manual(HttpServletRequest request) {
        RemoteExMiningRewardTimerService remoteExMiningRewardTimerService = (RemoteExMiningRewardTimerService) ContextUtil.getBean("remoteExMiningRewardTimerService");
        JsonResult jsonResult = new JsonResult();
        try {
            String idss = request.getParameter("ids");
            RemoteResult remoteResult=remoteExMiningRewardTimerService.manualReturnBonus(idss);
            jsonResult.setSuccess(remoteResult.getSuccess());
            jsonResult.setMsg(remoteResult.getMsg());
        } catch (Exception e) {
            jsonResult.setSuccess(false);
            e.printStackTrace();
        }
        return jsonResult;
    }


}
