package hry.mall.manage.remote;

import hry.bean.JsonResult;
import hry.mall.integral.model.CustomerIntegral;
import hry.mall.integral.model.IntegralLevel;
import hry.mall.integral.model.ShopMemberPerformance;
import hry.mall.integral.remote.RemoteIntegralDetailService;
import hry.mall.integral.service.*;
import hry.mall.third.remote.service.TimerPayService;
import hry.util.QueryFilter;
import hry.util.SpringUtil;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @auther zhouming
 * @date 2019/7/5 10:34
 * @Version 1.0
 */
public class RemoteMallManageServiceImpl implements RemoteMallManageService {

    @Resource
    private IntegralAccountService integralAccountService;

    @Resource
    private CustomerIntegralService customerIntegralService;
    @Resource
    private IntegralLevelService integralLevelService;

    @Resource
    private IntegralDetailService integralDetailService;
    @Resource
    private ShopMemberPerformanceService shopMemberPerformanceService;
    @Resource
    private TimerPayService  timerPayService;
    
    @Override
    public void executeListener() {
        // 导入数据时注释这方法的实现
        // 获取未生成积分账户的用户
        List<Long> customerIdList = integralAccountService.findNoIntegralAccountUser();
         for (Long id : customerIdList) {
            System.out.println("为用户id为："+id+"的账户添加积分账户");
            CustomerIntegral customerIntegral = new CustomerIntegral();
            customerIntegral.setMemberId(id);
            customerIntegral.setIntegralType("System"); //积分类型
            customerIntegral.setIntegralName("HRY"); // 积分名称
            customerIntegralService.save(customerIntegral);
            System.out.println("~~~~~~~~~~~~~~~~积分发放（注册积分，父级分销积分）~~~~~~~~~~~~~~~~~~~~~~~~~~");
            integralDetailService.addIntegralDetail1(Long.valueOf("0"), id, "taskAccount", "register", 2);//改为新方法

        }
        // 获取账户的父级推荐人未获取注册积分的账户集合
        // 注释掉--功能不全 后期更改
        /*List<Long> customerIds = integralAccountService.findNoIntegralDetailUser();
        for (Long id : customerIds) {
            integralDetailService.addIntegralDetail(0, id, "taskAccount", "register", 2);
        }*/
        //生成用户业绩账户
        this.addShopMemberPerformance();
        //添加默认等级
        this.addDefaultLevel();
    }
    /**
     * 生成用户业绩账户
     * */
    public void addShopMemberPerformance(){
        // 获取未生成积分账户的用户
        List<Long> customerIdList = shopMemberPerformanceService.findNoMemberPerformanceUser();
        for (Long id : customerIdList) {
            //添加用户业绩账户
            QueryFilter queryFilter = new QueryFilter(ShopMemberPerformance.class);
            queryFilter.addFilter("memberId=",id);
            List<ShopMemberPerformance> shopMemberPerformances = shopMemberPerformanceService.find(queryFilter);
            if(null!=shopMemberPerformances && shopMemberPerformances.size()>0){
                System.out.println("已有业绩账户表。用户id为："+id);
            }else{
                ShopMemberPerformance shopMemberPerformance = new ShopMemberPerformance();
                shopMemberPerformance.setMemberId(id);
                shopMemberPerformance.setAllPerformance(new BigDecimal("0"));
                shopMemberPerformance.setNewPerformance(new BigDecimal("0"));
                shopMemberPerformance.setTeamAllPerformance(new BigDecimal("0"));
                shopMemberPerformance.setTeamNewPerformance(new BigDecimal("0"));
                shopMemberPerformance.setTeamIncome(new BigDecimal("0"));
                shopMemberPerformance.setIncome(new BigDecimal("0"));
                shopMemberPerformanceService.save(shopMemberPerformance);
            }
//            //给上级发放注册奖励(不需要了)
//            System.out.println("发放推荐奖励的参数："+id);
//            RemoteIntegralDetailService remoteIntegralDetailService = SpringUtil.getBean("remoteIntegralDetailService");
//            JsonResult jsonResult1 = remoteIntegralDetailService.performingTaskRecommendKey(id, "recommendKey");
//            System.out.println("发放推荐注册奖励状态："+jsonResult1.getSuccess());

        }
    }



    /**
     * 为用户添加默认等级
     * */
    @Override
    public void addDefaultLevel(){
        /*----为账户添加积分等级及相关额度---*///胡一茗二期--会员等级只能购买，不初始化   //又改为初始化了
        QueryFilter filter = new QueryFilter(IntegralLevel.class);
        filter.setOrderby("number asc");
        List<IntegralLevel> integralLevelList = integralLevelService.find(filter);
        IntegralLevel integralLevel=null;
        if (integralLevelList != null && integralLevelList.size() > 0){
            integralLevel = integralLevelList.get(0); //取最小等级为默认等级
            //查询出没有默认等级的会员
            List<CustomerIntegral> customerIntegrals = customerIntegralService.findNoDefaultLevel();
            for (CustomerIntegral customerIntegral : customerIntegrals) {
                //添加默认等级
                if(customerIntegral != null && customerIntegral.getLevelId() == null &&  integralLevel !=null){
                    customerIntegral.setLevelId(integralLevel.getId());
                    customerIntegral.setLevelNumber(integralLevel.getNumber());
                    customerIntegral.setTotalQuota(integralLevel.getBaseQuota());
                    customerIntegral.setBaseQuota(integralLevel.getBaseQuota());
                    customerIntegral.setRecommendQuota(new BigDecimal("0"));
                    customerIntegral.setMaxQuota(integralLevel.getMaxQuota());
                    customerIntegral.setStartDate(new Date());
                    Calendar calendar = Calendar.getInstance();
                    calendar.add(Calendar.YEAR, integralLevel.getValidityPeriod());
                    Date time = calendar.getTime();
                    customerIntegral.setEndDate(time);
                    customerIntegralService.update(customerIntegral);
                }
            }

        }


    }
	@Override
	public void payWeiOrderQuery() {
		// TODO Auto-generated method stub
		timerPayService.payOrderQuery();
	}

}
