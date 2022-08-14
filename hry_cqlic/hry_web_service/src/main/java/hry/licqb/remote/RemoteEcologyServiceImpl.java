package hry.licqb.remote;

import com.github.pagehelper.Page;
import hry.bean.FrontPage;
import hry.bean.JsonResult;
import hry.customer.person.model.AppPersonInfo;
import hry.customer.person.service.AppPersonInfoService;
import hry.licqb.award.RulesConfig;
import hry.licqb.ecology.model.Ecofund;
import hry.licqb.ecology.model.EcologEnter;
import hry.licqb.ecology.model.EcologPay;
import hry.licqb.ecology.model.EcologPlate;
import hry.licqb.ecology.service.EcofundService;
import hry.licqb.ecology.service.EcologEnterService;
import hry.licqb.ecology.service.EcologPayService;
import hry.licqb.ecology.service.EcologPlateService;
import hry.licqb.level.model.CustomerLevel;
import hry.licqb.level.service.CustomerLevelService;
import hry.licqb.util.RedisStaticUtil;
import hry.util.PageFactory;
import hry.util.QueryFilter;
import hry.util.StringUtil;
import hry.util.date.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author zhouming
 * @date 2020/6/4 18:50
 * @Version 1.0
 */
public class RemoteEcologyServiceImpl implements RemoteEcologyService{

    @Autowired
    private EcofundService ecofundService;
    @Autowired
    private CustomerLevelService customerLevelService;
    @Autowired
    private EcologEnterService ecologEnterService;
    @Autowired
    private EcologPlateService ecologPlateService;
    @Autowired
    private EcologPayService ecologPayService;
    @Autowired
    private AppPersonInfoService appPersonInfoService;


    @Override
    public FrontPage findEcofundPageList(Map<String, String> map) {
        Page<Ecofund> page = PageFactory.getPage(map);
        QueryFilter filter = new QueryFilter(Ecofund.class);
        filter.addFilter("customerId=", map.get("customerId"));
        filter.addFilter("activityStatus != ", 8);
        filter.setOrderby("id desc");
        List<Ecofund> ecofundList = ecofundService.find(filter);
        return new FrontPage(ecofundList, page.getTotal(), page.getPages(), page.getPageSize());
    }

    @Override
    public JsonResult getEcofund(Map<String, String> map) {
        QueryFilter filter = new QueryFilter(Ecofund.class);
        filter.addFilter("customerId=", map.get("customerId"));
        filter.addFilter("id=", map.get("id"));
        Ecofund ecofund = ecofundService.get(filter);
        // 获取用户基础信息
        QueryFilter userFilter = new QueryFilter(AppPersonInfo.class);
        userFilter.addFilter("customerId=", map.get("customerId"));
        AppPersonInfo info = appPersonInfoService.get(userFilter);
        ecofund.setEmail(info.getEmail());
        ecofund.setMobilePhone(info.getMobilePhone());
        return new JsonResult(true).setObj(ecofund);
    }

    @Override
    public JsonResult applyEcofund(Map<String, String> map) {
        String id = map.get("id");
        if (StringUtil.isNull(id)) {
            QueryFilter filter = new QueryFilter(Ecofund.class);
            filter.addFilter("id=", id);
            filter.addFilter("customerId=", map.get("customerId"));
            Ecofund ecofund = ecofundService.get(filter);
            if (ecofund == null) {
                return new JsonResult(false).setCode("0001");
            }
            ecofund.setAgainActivityDate(DateUtil.stringToDate(map.get("againActivityDate")));
            ecofund.setAgainActivityAddress(map.get("againActivityAddress"));
            ecofund.setAgainActivityIntro(map.get("againActivityIntro"));
            ecofund.setAgainActivityImage(map.get("againActivityImage"));
            ecofund.setAgainActivityVideo(map.get("againActivityVideo"));
            ecofund.setAgainPeopleCount(Integer.parseInt(map.get("againPeopleCount")));
            ecofund.setAgainCreated(new Date());
            ecofund.setActivityStatus(6); // 待核实
            ecofundService.update(ecofund);
        } else {
            QueryFilter levelQFilter = new QueryFilter(CustomerLevel.class);
            levelQFilter.addFilter("customerId=", map.get("customerId"));
            CustomerLevel level = customerLevelService.get(levelQFilter);
            if (level.getLevelId() == null || level.getLevelId() < 1) {
                return new JsonResult(false).setMsg("gerendengjibugou");
            }
            // 校验用户是否有未完成的申请订单
            JsonResult check = this.getUnEcofund(Long.parseLong(map.get("customerId")));
            if ("1000".equals(check.getCode())) {
                return new JsonResult(false).setMsg("shengtaijijingweiwancheng");
            }
            Ecofund ecofund = new Ecofund();
            ecofund.setOrderNum(this.createNumber("BBGO"));
            ecofund.setCustomerId(Long.parseLong(map.get("customerId")));
            ecofund.setActivityName(map.get("activityName"));
            ecofund.setActivityDate(DateUtil.stringToDate(map.get("activityDate")));
            ecofund.setActivityAddress(map.get("activityAddress"));
            ecofund.setActivityIntro(map.get("activityIntro"));
            ecofund.setActivityImage(map.get("activityImage"));
            ecofund.setPeopleCount(Integer.parseInt(map.get("peopleCount")));
            ecofund.setActivityStatus(1); // 申请中
            ecofundService.save(ecofund);
        }
        return new JsonResult(true);
    }

    private String createNumber(String flag) {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
        String newDate = sdf.format(new Date());
        Random r = new Random();
        newDate += r.nextInt(1000);
        return newDate = flag+newDate;
    }

    @Override
    public JsonResult updateEcofund(Map<String, String> map) {
        String id = map.get("id");
        QueryFilter filter = new QueryFilter(Ecofund.class);
        filter.addFilter("id=", id);
        filter.addFilter("customerId=", map.get("customerId"));
        Ecofund ecofund = ecofundService.get(filter);
        if (ecofund == null) {
            // 0001 不是该用户的订单
            return new JsonResult(false).setCode("0001");
        }
        if (ecofund.getActivityStatus() != 3) {
            // 0002 订单不是后台审核通过状态
            return new JsonResult(false).setCode("0002");
        }
        String activityStatus = map.get("activityStatus");
        if (!"4".equals(activityStatus) && !"5".equals(activityStatus)) {
            // 前台必须传 4 或者 5
            return new JsonResult(false).setCode("0003");
        }
        if ("5".equals(activityStatus)) {
            ecofund.setItAgain(0);
        }
        ecofund.setActivityStatus(Integer.parseInt(activityStatus));
        ecofundService.update(ecofund);
        return new JsonResult(true);
    }

    @Override
    public JsonResult getUnEcofund(Long customerId) {
        JsonResult result = new JsonResult(false).setCode("0000");
        QueryFilter filter = new QueryFilter(Ecofund.class);
        filter.addFilter("customerId=", customerId);
        filter.addFilter("activityStatus_in", "1,3,5,6");
        List<Ecofund> ecofundList = ecofundService.find(filter);
        if (ecofundList != null && ecofundList.size() > 0) {
            // 1000 用户存在申请订单
            result.setSuccess(true).setCode("1000").setObj(ecofundList.get(0));
        } else {
            // 1001 用户不存在申请订单
            result.setSuccess(true).setCode("1001");
        }
        return result;
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~生态入驻接口~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public FrontPage findEcologEnterPageList(Map<String, String> map) {
        Page<EcologEnter> page = PageFactory.getPage(map);
        QueryFilter filter = new QueryFilter(Ecofund.class);
        filter.addFilter("customerId=", map.get("customerId"));
        filter.setOrderby("id desc");
        List<EcologEnter> list = ecologEnterService.find(filter);
        list.stream().forEach((enter) -> {
            EcologPlate plate = ecologPlateService.get(enter.getPlateId());
            enter.setPlateName(plate.getPlateName());
        });
        return new FrontPage(list, page.getTotal(), page.getPages(), page.getPageSize());
    }

    @Override
    public JsonResult getEcologEnter(Map<String, String> map) {
        QueryFilter filter = new QueryFilter(EcologEnter.class);
        filter.addFilter("customerId=", map.get("customerId"));
        filter.addFilter("id=", map.get("id"));
        EcologEnter ecologEnter = ecologEnterService.get(filter);
        // 获取版块名称
        EcologPlate plate = ecologPlateService.get(ecologEnter.getPlateId());
        ecologEnter.setPlateName(plate.getPlateName());
        if (ecologEnter.getEnterStatus() == 3) {
            // 收款地址
            String acceptAddress = RedisStaticUtil.getAppConfigValue(RulesConfig.EcologenterKey, "acceptAddress");
            ecologEnter.setAcceptAddress(acceptAddress);
        } else if (ecologEnter.getEnterStatus() != 1 && ecologEnter.getEnterStatus() != 2) {
            // 查询该用户最新缴费记录
            QueryFilter filter1 = new QueryFilter(EcologPay.class);
            filter1.addFilter("customerId= ", ecologEnter.getCustomerId());
            filter1.addFilter("enterId= ", ecologEnter.getId());
            filter1.setOrderby("id DESC");
            EcologPay ecologPay = ecologPayService.get(filter1);
            if (ecologPay != null) {
                ecologEnter.setAcceptAddress(ecologPay.getAcceptAddress());
                ecologEnter.setPaymentAddress(ecologPay.getPaymentAddress());
            }
        }
        return new JsonResult(true).setObj(ecologEnter);
    }

    @Override
    public FrontPage findEcologPlatePageList(Map<String, String> map) {
        Page<EcologPlate> page = PageFactory.getPage(map);
        QueryFilter filter = new QueryFilter(EcologPlate.class);
        filter.addFilter("isOpen=", 1);
        List<EcologPlate> list = ecologPlateService.find(filter);
        return new FrontPage(list, page.getTotal(), page.getPages(), page.getPageSize());
    }

    @Override
    public FrontPage findHasEnterList(Map<String, String> map) {
        Page<EcologEnter> page = PageFactory.getPage(map);
        QueryFilter filter = new QueryFilter(Ecofund.class);
        filter.addFilter("plateId=", map.get("plateId"));
        filter.addFilter("enterStatus=", 6);
        filter.setOrderby("enterLevel asc, id asc");
        List<EcologEnter> list = ecologEnterService.find(filter);
        return new FrontPage(list, page.getTotal(), page.getPages(), page.getPageSize());
    }

    @Override
    public JsonResult applyEcologEnter(Map<String, String> map) {
        // 校验该版块入驻等级A是否已满
        if ("A".equals(map.get("enterLevel"))) {
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("plateId", map.get("plateId"));
            paramMap.put("enterLevel", "A");
            paramMap.put("enterStatus", "enterStatusA");
            int count = ecologEnterService.countEnter(paramMap);
            if (count >= 3 ) {
                // ruzhudengjiyiman = 该版块入驻等级A已满
                return new JsonResult(false).setMsg("ruzhudengjiyiman");
            }
        }
        EcologEnter enter = new EcologEnter();
        enter.setOrderNum(this.createNumber("EN"));
        enter.setCustomerId(Long.parseLong(map.get("customerId")));
        enter.setPlateId(Long.parseLong(map.get("plateId")));
        enter.setEnterLevel(map.get("enterLevel"));
        enter.setEnterName(map.get("enterName"));
        enter.setEnterLogo(map.get("enterLogo"));
        enter.setDownloadLink(map.get("downloadLink"));
        enter.setEnterApplyIntro(map.get("enterApplyIntro"));
        enter.setEnterStatus(1);
        ecologEnterService.save(enter);
        return new JsonResult(true);
    }

    @Override
    public JsonResult updateEcologEnter(Map<String, String> map) {
        String id = map.get("id");
        QueryFilter filter = new QueryFilter(EcologEnter.class);
        filter.addFilter("id=", id);
        filter.addFilter("customerId=", map.get("customerId"));
        EcologEnter ecologEnter = ecologEnterService.get(filter);
        if (ecologEnter == null) {
            // 0001 不是该用户的订单
            return new JsonResult(false).setCode("0001");
        }
        if (ecologEnter.getEnterStatus() != 3) {
            // 0002 订单不是后台审核通过待付款状态
            return new JsonResult(false).setCode("0002");
        }
        String enterStatus = map.get("enterStatus");
        if (!"4".equals(enterStatus) && !"5".equals(enterStatus)) {
            // 前台必须传 4 或者 5
            return new JsonResult(false).setCode("0003");
        }
        ecologEnter.setEnterStatus(Integer.parseInt(enterStatus));
        // 生成缴费记录
        if ("5".equals(enterStatus)) {
            if (!StringUtil.isNull(map.get("acceptAddress"))) {
                // 确认支付时 收款地址不能为空
                return new JsonResult(false).setCode("0004");
            }
            if (!StringUtil.isNull(map.get("paymentAddress"))) {
                // 确认支付时 付款地址不能为空
                return new JsonResult(false).setCode("0005");
            }
            ecologEnter.setAcceptAddress(map.get("acceptAddress"));
            ecologEnter.setPaymentAddress(map.get("paymentAddress"));
            addEcologPay(ecologEnter);
        }
        ecologEnterService.update(ecologEnter);
        return new JsonResult(true);
    }

    @Override
    public JsonResult checkRenew(Map<String, String> map) {
        String enterId = map.get("id");
        String customerId = map.get("customerId");
        QueryFilter filter = new QueryFilter(EcologEnter.class);
        filter.addFilter("customerId=", customerId);
        filter.addFilter("id=", enterId);
        EcologEnter ecologEnter = ecologEnterService.get(filter);
        if (ecologEnter != null && ecologEnter.getEnterStatus() == 6) {
            // 查询是否有待核实订单
           if (ecologEnter.getRenewStatus() == 1) {
               // 有正在核实记录
               return new JsonResult(false).setMsg("youzhengzaiheshijilu");
           } else {
               // 最新收款地址
               String acceptAddress = RedisStaticUtil.getAppConfigValue(RulesConfig.EcologenterKey, "acceptAddress");
               return new JsonResult(true).setObj(acceptAddress);
           }
        } else {
            // 0006 非法请求
            return new JsonResult("false").setCode("0006");
        }
    }

    @Override
    public JsonResult affirmRenew(Map<String, String> map) {
        String enterId = map.get("id");
        String customerId = map.get("customerId");
        String acceptAddress = map.get("acceptAddress");
        String paymentAddress = map.get("paymentAddress");
        if (!StringUtil.isNull(acceptAddress)) {
            // 确认支付时 收款地址不能为空
            return new JsonResult(false).setCode("0004");
        }
        if (!StringUtil.isNull(paymentAddress)) {
            // 确认支付时 付款地址不能为空
            return new JsonResult(false).setCode("0005");
        }
        QueryFilter filter = new QueryFilter(EcologEnter.class);
        filter.addFilter("customerId=", customerId);
        filter.addFilter("id=", enterId);
        EcologEnter ecologEnter = ecologEnterService.get(filter);
        if (ecologEnter != null && ecologEnter.getEnterStatus() == 6) {
            ecologEnter.setRenewStatus(1);
            ecologEnterService.update(ecologEnter);
            ecologEnter.setAcceptAddress(map.get("acceptAddress"));
            ecologEnter.setPaymentAddress(map.get("paymentAddress"));
            addEcologPay(ecologEnter);
            return new JsonResult(true);
        } else {
            // 0006 非法请求
            return new JsonResult("false").setCode("0006");
        }
    }

    /**
     * 生成缴费记录
     * */
    private void addEcologPay(EcologEnter ecologEnter) {
        EcologPay pay = new EcologPay();
        pay.setCustomerId(ecologEnter.getCustomerId());
        pay.setEnterId(ecologEnter.getId());
        pay.setOrderNum(ecologEnter.getOrderNum());
        pay.setPayDate(new Date());
        pay.setAcceptAddress(ecologEnter.getAcceptAddress());
        pay.setPaymentAddress(ecologEnter.getPaymentAddress());
        pay.setStatus(1);
        pay.setRemark("待核实");
        ecologPayService.save(pay);
    }




    /**
     * 定时器执行方法
     * */
    @Override
    public void updateEcologEnter() {
        QueryFilter filter = new QueryFilter(EcologEnter.class);
        filter.addFilter("enterStatus=", 6);
        filter.addFilter("expireDate <= ", new Date());
        List<EcologEnter> list = ecologEnterService.find(filter);
        list.stream().forEach((enter) -> {
            // 设置过期状态
            enter.setEnterStatus(8);
            ecologEnterService.update(enter);
        });
    }
}
