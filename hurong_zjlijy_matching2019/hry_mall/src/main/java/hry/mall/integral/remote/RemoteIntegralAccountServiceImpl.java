package hry.mall.integral.remote;

import hry.bean.JsonResult;
import hry.common.util.DateUtils;
import hry.mall.integral.model.CustomerIntegral;
import hry.mall.integral.model.IntegralAccount;
import hry.mall.integral.model.IntegralConfig;
import hry.mall.integral.model.IntegralLevel;
import hry.mall.integral.service.*;
import hry.util.QueryFilter;
import hry.util.SNUtil;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RemoteIntegralAccountServiceImpl implements RemoteIntegralAccountService {


    @Resource
    private IntegralAccountService integralAccountService;

    @Resource
    private CustomerIntegralService customerIntegralService;

    @Resource
    private IntegralConfigService integralConfigService;

    @Resource
    IntegralDetailService integralDetailService;

    @Resource
    private ShopTeamLevelService shopTeamLevelService;
    @Resource
    private LevelRecordService levelRecordService;
    @Resource
    private  ShopMemberPerformanceService shopMemberPerformanceService;
    @Resource
    private IntegralLevelService integralLevelService;
    @Override
    public JsonResult dataCenter() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        //流通总量
        BigDecimal integralAll =  new BigDecimal(0);
        //积分余量
        BigDecimal integralReamining =  new BigDecimal(0);

        //查询所有积分账户
        List<IntegralAccount> integralAccounts = integralAccountService.findAll();
        for (int i = 0; i < integralAccounts.size(); i++) {
            IntegralAccount integralAccount = integralAccounts.get(i);
            integralAll =integralAll.add(integralAccount.getConsume_total());
            integralReamining = integralReamining.add(integralAccount.getReamining_total());
        }
        Map<String,Object> hashMap =  new HashMap<>();
        String format = df.format(new Date());
        String beforeDays = DateUtils.getBeforeDays(format, 1, "yyyy-MM-dd", "yyyy-MM-dd");
        hashMap.put("startTime", beforeDays+" 00:00:00");
        hashMap.put("endTime", beforeDays+" 23:59:59");
        Map<String, Object> mapList = integralDetailService.findByRewardType(hashMap);
        //购物产出
        String integralShop = "0";
        if(null!=mapList  && !mapList.isEmpty() && !mapList.get("integralShop").toString().equals("0E-8")){
            integralShop = mapList.get("integralShop").toString();
        }

        //任务产出
        String integralTask = "0";
        if(null!=mapList  && !mapList.isEmpty() && !mapList.get("integralTask").toString().equals("0E-8")){
            integralTask = mapList.get("integralTask").toString();
        }

        //分销产出
        String integralDistribution = "0";
        if(null!=mapList  && !mapList.isEmpty() && !mapList.get("integralDistribution").toString().equals("0E-8")){
            integralDistribution = mapList.get("integralDistribution").toString();
        }
        //总产出
        Double integralFindAll = Double.parseDouble(integralShop)+Double.parseDouble(integralDistribution)+Double.parseDouble(integralTask);
        IntegralConfig integralConfig = integralConfigService.get((long)1);
        String integralCode = integralConfig.getIntegralCode();
        //收益排名
        List<Map<String, Object>> list = customerIntegralService.findByTotal();
        for (int i = 0; i <list.size() ; i++) {
            Map<String, Object> map = list.get(i);
            map.put("integralCode",integralCode);
        }
        Map<String,Object> map = new HashMap<>();
        map.put("integralAll",integralAll);
        map.put("integralReamining",integralReamining);
        map.put("integralShop",integralShop);
        map.put("integralFindAll",integralFindAll);
        map.put("integralTask",integralTask);
        map.put("integralDistribution",integralDistribution);
        map.put("list",list);
        return new JsonResult().setSuccess(true).setObj(map);
    }



    @Override
    public JsonResult initializationCurrencyAccount (Map<String, Object> params){
        System.out.println("w维护用户的积分账户++++++++++++++++");
        if (!StringUtils.isEmpty(params.get("memberId"))) {
            //用户Id
            Long memberId = Long.parseLong(params.get("memberId").toString());
            if (!StringUtils.isEmpty(params.get("availableIntegral"))) {
                QueryFilter queryFilter = new QueryFilter(CustomerIntegral.class);
                queryFilter.addFilter("memberId=",memberId);
                List<CustomerIntegral> customerIntegrals = customerIntegralService.find(queryFilter);
                if(customerIntegrals !=null &&customerIntegrals.size()>0){
                    CustomerIntegral customerIntegral = customerIntegrals.get(0);
                    //可用积分值
                    BigDecimal availableIntegral = new BigDecimal(params.get("availableIntegral").toString());
                    QueryFilter queryFilter1 = new QueryFilter(IntegralLevel.class);
                    queryFilter1.addFilter("number=",1);
                    IntegralLevel integralLevel = integralLevelService.get(queryFilter1);
                    customerIntegral.setLevelId(integralLevel.getId());
                    customerIntegral.setLevelNumber(integralLevel.getNumber());
                    customerIntegralService.update(customerIntegral);

                    System.out.println(memberId+"的积分有:"+availableIntegral);
                    //判断积分是否大于零
                    int i = availableIntegral.compareTo(new BigDecimal("0"));
                    if (i==1){
                        System.out.println("为："+customerIntegral.getId()+"充值积分："+availableIntegral);

                        String requestNo = SNUtil.create15();

                        //4.电子劵扣款
                        Map<String, String> map1 = new HashMap<>();
                        map1.put("id", customerIntegral.getId().toString());
                        map1.put("type", CustomerIntegral.TYPE_ADD);
                        map1.put("integralCount", availableIntegral.toString());
                        map1.put("tradingDetail", "初始化可用积分");
                        map1.put("businessType", "99");//转出
                        map1.put("requestNo", requestNo);//流水号
                        boolean flag1 = customerIntegralService.updateInteger(map1);
                        if (!flag1) {
                            return new JsonResult().setSuccess(false).setMsg("kaitongshibai"); //开通失败;
                        }
                    }
                }


            }

        }



        return new JsonResult().setSuccess(true);
    }


    @Override
    public   JsonResult updateTeamLevel(){
        JsonResult jsonResult = new JsonResult();
        try {
            QueryFilter queryFilter = new QueryFilter(CustomerIntegral.class);
            queryFilter.setOrderby("id desc");
            List<CustomerIntegral> all = customerIntegralService.find(queryFilter);
            System.out.println("一共："+all.size()+"个用户");
            Map<String, Object> map = new HashMap<>();
            map.put("all",all.size());
            int oki=0;
            int noi1=0;
            int noi2=0;
            for (CustomerIntegral customerIntegral:all) {
                JsonResult jsonResult1 = shopMemberPerformanceService.updateMemberPerformance(customerIntegral);
                if (null==jsonResult1 || !jsonResult1.getSuccess()){
                    System.out.println("用户id："+customerIntegral.getMemberId()+"维护业绩失败");
                    noi1++;
                }else {
                    //维护用户的团队等级
                    JsonResult jsonResult3 = shopTeamLevelService.updateUserTeamLevel(customerIntegral.getMemberId());
                    if(jsonResult3.getSuccess()){
                        oki++;
                        System.out.println("用户id："+customerIntegral.getMemberId()+"维护业绩和团队等级成功");
                    }else{
                        noi2++;
                        System.out.println("用户id："+customerIntegral.getMemberId()+"维护业绩和团队等级失败");
                    }
                }
            }
            System.out.println("维护用户业绩结束："+ new Date());
            map.put("oki",oki);
            map.put("noi1",noi1);
            map.put("noi2",noi2);
            jsonResult.setSuccess(true).setObj(map);
        }catch (Exception e){
            e.printStackTrace();
            jsonResult.setSuccess(false);
            jsonResult.setMsg(e.toString());
        }
        return jsonResult;
    }

}
