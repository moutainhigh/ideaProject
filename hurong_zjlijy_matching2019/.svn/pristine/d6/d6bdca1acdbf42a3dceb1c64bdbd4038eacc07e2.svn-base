package hry.cm2.remote;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import hry.bean.FrontPage;
import hry.bean.JsonResult;
import hry.bean.ObjectUtil;
import hry.cm2.account.model.Cm2AccountRecord;
import hry.cm2.customer.model.Cm2Customer;
import hry.cm2.customerminer.model.Cm2CustomerMiner;
import hry.cm2.remote.model.CmCustomerRemote;
import hry.cm2.remote.model.CmTeamlevelRemote;
import hry.cm2.team.model.Cm2Teamlevel;
import hry.cm2.account.service.Cm2AccountRecordService;
import hry.cm2.customer.model.Cm2Customer;
import hry.cm2.customer.service.Cm2CustomerService;
import hry.cm2.customerminer.service.Cm2CustomerMinerProfitService;
import hry.cm2.customerminer.service.Cm2CustomerMinerService;
import hry.cm2.profitone.service.Cm2ProfitOneService;
import hry.cm2.profittwo.service.Cm2ProfitTwoService;
import hry.cm2.team.service.Cm2TeamlevelService;
import hry.mq.producer.service.MessageProducer;
import hry.redis.common.utils.RedisService;
import hry.util.PageFactory;
import hry.util.QueryFilter;
import hry.utils.BaseConfUtil;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RemoteCmCustomerServiceImpl implements RemoteCmCustomerService {

    @Resource
    private RedisService redisService;
    @Resource
    private Cm2CustomerService cmCustomerService;
    @Resource
    private MessageProducer messageProducer;
    @Resource
    private Cm2TeamlevelService cmTeamlevelService;
    @Resource
    private Cm2AccountRecordService cmAccountRecordService;
    @Resource
    private Cm2CustomerMinerProfitService cmCustomerMinerProfitService;
    @Resource
    private Cm2ProfitOneService cmProfitOneService;
    @Resource
    private Cm2CustomerMinerService cmCustomerMinerService;
    @Resource
    private Cm2ProfitTwoService cmProfitTwoService;

    @Override
    public JsonResult getCustomerGrade(Map<String, String> params) {
        // TODO Auto-generated method stub
        Long customerId = Long.valueOf(params.get("customerId"));
        /***
         * 1.redis中查询用户等级信息
         * 2.redis没有，则取数据库中查询，同时发送消息更新等级信息
         */

        String str = redisService.get("CM2:CUSTOMERGRADE_CUSTOMERID:" + customerId);
        Cm2Customer cmCustomer = null;
        if (str != null && !str.equals("")) {
            cmCustomer = JSON.parseObject(str, Cm2Customer.class);
        } else {
            QueryFilter filterCmCustomer = new QueryFilter(Cm2Customer.class);
            filterCmCustomer.addFilter("customerId=", customerId);
            cmCustomer = cmCustomerService.get(filterCmCustomer);
            if (cmCustomer == null) {
                cmCustomer = new Cm2Customer();
                cmCustomer.setCustomerId(customerId);
                //cmCustomerService.save(cmCustomer);
            }
            //发送更新等级消息
            messageProducer.toCmUpdateGrade(customerId.toString());
        }
        CmCustomerRemote bean = ObjectUtil.bean2bean(cmCustomer, CmCustomerRemote.class);
        return new JsonResult().setSuccess(true).setObj(bean);
    }

    @Override
    public FrontPage findCustomerTeamlist(Map<String, String> params) {
        // TODO Auto-generated method stub
        Long customerId = Long.valueOf(params.get("customerId"));//用户id
        Page page = PageFactory.getPage(params);
        List<Cm2Teamlevel> list = cmTeamlevelService.findTeamByCustomerId(customerId, 1);//查询直推
        List<CmTeamlevelRemote> beanList = ObjectUtil.beanList(list, CmTeamlevelRemote.class);
        return new FrontPage(beanList, page.getTotal(), page.getPages(), page.getPageSize());
    }

    @Override
    public JsonResult getProfitStatistics(Map<String, String> params) {
        // TODO Auto-generated method stub
        Map<String, Object> paramResult = new HashMap<>();
        Long customerId = Long.valueOf(params.get("customerId"));//用户id
        /**查询redis*/
        String str = redisService.get("CM2:CUSTOMERGRADE_CUSTOMERID_PROFIT:" + customerId);
        if (str != null && !str.equals("")) {
            paramResult = JSON.parseObject(str, HashMap.class);
            return new JsonResult().setSuccess(true).setObj(paramResult);
        }

        /** 查询平台币Code */
        String platCoin = BaseConfUtil.getConfigSingle("platCoin", "configCache:basefinanceConfig");
        /**获取用户收益统计信息 */
        List<Cm2AccountRecord> list = cmAccountRecordService.getProfitStatistics(customerId, platCoin);

        BigDecimal profitTotal = new BigDecimal(0);//总收益
        BigDecimal profit1 = new BigDecimal(0);//分红收益
        BigDecimal profit2 = new BigDecimal(0);//矿机收益
        BigDecimal profit3 = new BigDecimal(0);//矿工动态收益
        BigDecimal profit4 = new BigDecimal(0);//矿场动态收益
        if (list != null && list.size() > 0) {
            for (Cm2AccountRecord cmAccountRecord : list) {
                //profitTotal = profitTotal.add(cmAccountRecord.getTransactionMoney());
                if (cmAccountRecord.getRemarkkey() == 6) {
                    profit1 = cmAccountRecord.getTransactionMoney();//分红收益
                }
				/*矿机收益取所有矿机总产出数量，此处注释掉
				 * if(cmAccountRecord.getRemarkkey()==7){
					profit2 = cmAccountRecord.getTransactionMoney();//矿机收益
				}*/
                if (cmAccountRecord.getRemarkkey() == 8) {
                    profit3 = profit3.add(cmAccountRecord.getTransactionMoney());//矿工动态收益1
                }
                if (cmAccountRecord.getRemarkkey() == 9) {
                    profit4 = cmAccountRecord.getTransactionMoney();//矿场动态收益
                }
                if (cmAccountRecord.getRemarkkey() == 10) {
                    profit3 = profit3.add(cmAccountRecord.getTransactionMoney());//矿工动态收益2
                }
            }
        }
        //查询矿机收益
        profit2 = cmCustomerMinerService.getMinigProFitByCustomerId(customerId);
        if (profit2 == null) {
            profit2 = new BigDecimal(0);
        }
        //计算总收益
        profitTotal = profit1.add(profit2).add(profit3).add(profit4);

        //分红收益
        paramResult.put("profit1", profit1);
        //矿机收益
        paramResult.put("profit2", profit2);
        //矿工动态收益
        paramResult.put("profit3", profit3);
        //矿场动态收益
        paramResult.put("profit4", profit4);
        paramResult.put("profitTotal", profitTotal.toString());//总收益

        /**查询当前用户封顶奖励*/
        //获取封顶倍数
        Cm2Customer cmCustomer = cmCustomerService.getCustomerByCustomerId(customerId);
        BigDecimal cappingMultiple = cmCustomer.getCappingMultiple()==null? BigDecimal.ZERO:cmCustomer.getCappingMultiple();//封顶倍数
        //查询用户今天产币信息
        BigDecimal profitSum = cmCustomerMinerProfitService.getProfitSumByCustomerId(customerId);
        if (profitSum == null) {
            profitSum = new BigDecimal(0);
        }
        BigDecimal fengding = profitSum.multiply(cappingMultiple).setScale(4, BigDecimal.ROUND_HALF_UP);
        if (fengding == null) {
            fengding = new BigDecimal(0);
        }
        paramResult.put("fengding", fengding);//封顶收益

        /**查询今天烧伤收益总和*/
        BigDecimal shaoshang = cmProfitOneService.getProfit3SumByCustomerId(customerId);
        if (shaoshang == null) {
            shaoshang = new BigDecimal(0);
        }
        paramResult.put("shaoshang", shaoshang);//烧伤

        //保存信息到redis中，保存1小时
        //redisService.save("CM2:CUSTOMERGRADE_CUSTOMERID_PROFIT:"+customerId, JSON.toJSONString(paramResult),60*60*1);


        return new JsonResult().setSuccess(true).setObj(paramResult);
    }

    @Override
    public JsonResult getPlatCoin() {
        // TODO Auto-generated method stub
        /** 查询平台币Code */
        String platCoin = BaseConfUtil.getConfigSingle("platCoin", "configCache:basefinanceConfig");
        return new JsonResult().setSuccess(true).setObj(platCoin);
    }

    @Override
    public JsonResult getMyMiner(Map<String, String> params) {
        // TODO Auto-generated method stub
        Map<String, Object> paramResult = new HashMap<>();
        Long customerId = Long.valueOf(params.get("customerId"));//用户id

        /**查询redis*/
        String str = redisService.get("CM2:CUSTOMERGRADE_CUSTOMERID_MYMINER:" + customerId);
        if (str != null && !str.equals("")) {
            paramResult = JSON.parseObject(str, HashMap.class);
            return new JsonResult().setSuccess(true).setObj(paramResult);
        }

        /**查询矿机信息*/
        List<Cm2CustomerMiner> cmCustomerMinerList = cmCustomerMinerService.getMyMinerCustomerId(customerId);
        BigDecimal dayProfit = new BigDecimal(0);//日收益
        BigDecimal totalPrice = new BigDecimal(0);//总投入
        BigDecimal profit3 = new BigDecimal(0);//累计收益
        Integer minerNum1 = 0;//待生效矿机数量
        Integer minerNum2 = 0;//运行中矿机数量
        Integer minerNum3 = 0;//已结束矿机数量

        if (cmCustomerMinerList != null && cmCustomerMinerList.size() > 0) {
            for (Cm2CustomerMiner cmCustomerMiner : cmCustomerMinerList) {
                profit3 = profit3.add(cmCustomerMiner.getProfit3());
                switch (cmCustomerMiner.getStatus()) {
                    case 1:
                        //待生效
                        minerNum1 = cmCustomerMiner.getMinerNum();
                        break;
                    case 2:
                        //运行中
                        minerNum2 = cmCustomerMiner.getMinerNum();
                        dayProfit = cmCustomerMiner.getDayProfit();
                        totalPrice = totalPrice.add(cmCustomerMiner.getMinerPrice());
                        break;
                    case 3:
                        //已结束
                        minerNum3 = cmCustomerMiner.getMinerNum();

                        break;

                    default:
                        break;
                }
            }
        }

        /**查询矿工收益*/
        BigDecimal kuanggongProfit = cmProfitOneService.getProfitAllByCustomerId(customerId);
        if (kuanggongProfit == null) {
            kuanggongProfit = new BigDecimal(0);
        }


        paramResult.put("dayProfit", dayProfit);//日收益
        paramResult.put("totalPrice", totalPrice);//总投入
        paramResult.put("profit3", profit3);//累计收益
        paramResult.put("minerNum1", minerNum1);//待生效矿机数量
        paramResult.put("minerNum2", minerNum2);//运行中矿机数量
        paramResult.put("minerNum3", minerNum3);//已结束矿机数量
        paramResult.put("minerNum", minerNum1 + minerNum2 + minerNum3);//矿机总数量
        paramResult.put("kuanggongProfit", kuanggongProfit);//矿工收益

        //保存信息到redis中，保存10分钟
        //redisService.save("CM2:CUSTOMERGRADE_CUSTOMERID_MYMINER:"+customerId, JSON.toJSONString(paramResult),60*10);


        return new JsonResult().setSuccess(true).setObj(paramResult);
    }

    @Override
    public JsonResult getMyMinercamps(Map<String, String> params) {
        // TODO Auto-generated method stub

        Map<String, Object> paramResult = new HashMap<>();
        Long customerId = Long.valueOf(params.get("customerId"));//用户id

        /**查询redis*/
        String str = redisService.get("CM2:CUSTOMERGRADE_CUSTOMERID_MYMINERCAMPS:" + customerId);
        if (str != null && !str.equals("")) {
            paramResult = JSON.parseObject(str, HashMap.class);
            return new JsonResult().setSuccess(true).setObj(paramResult);
        }

        //1.查询用户投入
        BigDecimal myTouru = cmCustomerMinerService.getMinerPriceSumByCustomerId(customerId);
        if (myTouru == null) {
            myTouru = new BigDecimal(0);
        }
        //2.查询用户伞下投入
        BigDecimal myteamTouru = cmCustomerMinerService.getTeamMinerPriceSumByCustomerId(customerId);
        if (myteamTouru == null) {
            myteamTouru = new BigDecimal(0);
        }
        //3.查询用户今天矿场收益
        BigDecimal myprofit = cmProfitTwoService.getTodayProfitSum(customerId);
        if (myprofit == null) {
            myprofit = new BigDecimal(0);
        }
        //4.查询用户矿场总收益
        BigDecimal totalprofit = cmProfitTwoService.getTotalProfitSum(customerId);
        if (totalprofit == null) {
            totalprofit = new BigDecimal(0);
        }

        paramResult.put("myprofit", myprofit);//昨日收益
        paramResult.put("totalTouru", myteamTouru.add(myTouru));//总投入
        paramResult.put("myTouru", myTouru);//自投入
        paramResult.put("myteamTouru", myteamTouru);//伞下投入
        paramResult.put("totalprofit", totalprofit);//矿场总收益
        paramResult.put("fenhongPrifit", new BigDecimal(0));//分红收益

        //保存信息到redis中，保存1小时
        //redisService.save("CM2:CUSTOMERGRADE_CUSTOMERID_MYMINERCAMPS:"+customerId, JSON.toJSONString(paramResult),60*60*1);

        return new JsonResult().setSuccess(true).setObj(paramResult);
    }

}
