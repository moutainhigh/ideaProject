package hry.financail.financing.service.impl;

import hry.financail.financing.FinancialUtil;
import hry.financail.financing.dao.AppFinancialRecommendDao;
import hry.financail.financing.dao.AppFinancialRecommendTransactionConfigDao;
import hry.financail.financing.dao.AppFinancialRecommendTransactionDao;
import hry.financail.financing.dao.AppFinancialUserDao;
import hry.financail.financing.model.AppFinancialRecommendTransaction;
import hry.financail.financing.model.AppFinancialRecommendTransactionConfig;
import hry.financail.financing.model.AppFinancialUser;
import hry.financail.financing.service.AppFinancialRecommendRefactorService;
import hry.remote.model.AppFinancialRecommend;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2019/7/8 11:47
 * @Description:
 */
@Service("appFinancialRecommendRefactorService")
public class AppFinancialRecommendRefactorServiceImpl implements AppFinancialRecommendRefactorService {

    @Resource
    private AppFinancialUserDao appFinancialUserDao;
    @Resource
    private AppFinancialRecommendDao appFinancialRecommendDao;
    @Resource
    private AppFinancialRecommendTransactionDao appFinancialRecommendTransactionDao;
    @Resource
    private AppFinancialRecommendTransactionConfigDao appFinancialRecommendTransactionConfigDao;



    @Override
    public void issueFinancialRefactoring() {
        try {
            System.out.println("====================重构进入统计购买理财================================");
            //查询是否有新产品
            Map<String,Object> paramMap = new HashMap<>(3);
            int list_count = appFinancialUserDao.findAppFinancialUserRefactorCount();
            int page_size = FinancialUtil.PAGE_SIZE;
            if (list_count >= FinancialUtil.MAX_SIZE) {
                int export_times = FinancialUtil.calcPage(list_count, page_size);
                for (int m = 0; m < export_times; m++) {
                    paramMap.put("currentPage", (m*page_size));
                    paramMap.put("pageSize",  page_size);
                    List<AppFinancialUser> userRefactorList = appFinancialUserDao.findAppFinancialUserRefactorList(paramMap);
                    dealWithPartnerAccount(userRefactorList);
                }
            } else {
                List<AppFinancialUser> userRefactorList = appFinancialUserDao.findAppFinancialUserRefactorList(paramMap);
                dealWithPartnerAccount(userRefactorList);
            }
            System.out.println("====================处理完理财统计数据条数为:"+list_count+"================================");
        } catch (Exception e){
            e.printStackTrace();
        }

    }


    public void dealWithPartnerAccount(List<AppFinancialUser> userRefactorList){
        try {
            if(userRefactorList != null && userRefactorList.size() > 0){
                for(AppFinancialUser fu : userRefactorList){
                    if(fu.getRealIncome().compareTo(BigDecimal.ZERO) == 1){
                        AppFinancialRecommend myself = appFinancialRecommendDao.findOne(fu.getCustomerId());
                        List<AppFinancialRecommend> parents = findParents(fu);

                        for(AppFinancialRecommend afr : parents){
                            if(afr.getVipLevel() == 0 && (myself.getLevel() - afr.getLevel()) == 1){
                                AppFinancialRecommendTransaction transaction = appFinancialRecommendTransactionDao.
                                        findAppFinancialTransactionByCustomerId(afr.getUid());
                                BigDecimal money = (fu.getRealIncome().subtract(fu.getCoinMoney()))
                                        .multiply(getRewardRatio(afr,myself.getLevel(),"WRB")).divide(FinancialUtil.HUNDRED,6,BigDecimal.ROUND_HALF_DOWN);
                                updateUserMoney(transaction,money,afr.getUid());
                            } else {

                                Integer level = getLevel(afr.getVipLevel());

                                if(afr.getVipLevel() > myself.getVipLevel() && level > myself.getLevel()){

                                    AppFinancialRecommendTransaction transaction = appFinancialRecommendTransactionDao.
                                            findAppFinancialTransactionByCustomerId(afr.getUid());
                                    BigDecimal money = (fu.getRealIncome().subtract(fu.getCoinMoney()))
                                            .multiply(getRewardRatio(afr,myself.getLevel(),"WRB")).divide(FinancialUtil.HUNDRED,6,BigDecimal.ROUND_HALF_DOWN);
                                    updateUserMoney(transaction,money,afr.getUid());
                                } else if(afr.getVipLevel() <= myself.getVipLevel() && (myself.getLevel() - afr.getLevel()) == 1){
                                    //主账号和直推人等级相同 并且大于vip2 则按照vip0拿直推人的奖励
                                    afr.setVipLevel(0);
                                    AppFinancialRecommendTransaction transaction = appFinancialRecommendTransactionDao.
                                            findAppFinancialTransactionByCustomerId(afr.getUid());
                                    BigDecimal money = (fu.getRealIncome().subtract(fu.getCoinMoney()))
                                            .multiply(getRewardRatio(afr,myself.getLevel(),"WRB")).divide(FinancialUtil.HUNDRED,6,BigDecimal.ROUND_HALF_DOWN);

                                    if(afr.getVipLevel() > 2){
                                        AppFinancialRecommendTransaction sameLevel = appFinancialRecommendTransactionDao.
                                                findAppFinancialTransactionByCustomerId(myself.getUid());
                                        BigDecimal sameLevelMoney = sameLevel.getMoney().multiply(new BigDecimal(10)).divide(FinancialUtil.HUNDRED, 6, BigDecimal.ROUND_HALF_DOWN);

                                        money = money.add(sameLevelMoney);
                                    }

                                    updateUserMoney(transaction,money,afr.getUid());
                                }
                            }
                        }
                    }
                    fu.setIfRecommend(1);
                    appFinancialUserDao.updateByPrimaryKeySelective(fu);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<AppFinancialRecommend> findParents(AppFinancialUser fu){
        List<AppFinancialRecommend> list = new ArrayList<AppFinancialRecommend>();
        AppFinancialRecommend one = appFinancialRecommendDao.findOne(fu.getCustomerId());
        findParents(one,list);
        return list;
    }

    public void findParents(AppFinancialRecommend one,List<AppFinancialRecommend> list){
        if(one.getPid() != null){
            AppFinancialRecommend one1 = appFinancialRecommendDao.findOne(one.getPid());
            list.add(one1);
            findParents(one1,list);
        }
    }

    /**
     * 根据会员等级获取查询最大层级数
     * @param vipLevel
     * @return
     */
    public Integer getLevel(Integer vipLevel){
        Integer level ;
        switch (vipLevel){
            case 0:
                level = 2;
                break;
            case 1:
                level = 2;
                break;
            case 2:
                level = 2;
                break;
            case 3:
                level = 3;
                break;
            case 4:
                level = 8;
                break;
            case 5:
                level = 15;
                break;
            default:
                level = 1;
                break;
        }
        return level;
    }

    public BigDecimal getRewardRatio(AppFinancialRecommend afr, Integer level, String coinCode){
        BigDecimal ratio = BigDecimal.ZERO;
        Integer level_s = 0;
        AppFinancialRecommendTransactionConfig oneByCoinCode = appFinancialRecommendTransactionConfigDao.findOneByCoinCode(coinCode);
        if(oneByCoinCode == null){
            System.out.println("=======没有配置币种奖励比例");
            return ratio;
        }

        if(afr.getVipLevel() > 0 && afr.getPid() != null ){
            level_s = level - afr.getLevel();
        } else if(afr.getVipLevel() == 0){
            level_s = 0;
        } else {
            level_s = level;
        }

        switch (level_s){
            case 0:
                ratio = oneByCoinCode.getVip_zero();
                break;
            case 1:
                ratio = oneByCoinCode.getVip_one();
                break;
            case 2:
                ratio = oneByCoinCode.getVip_two();
                break;
            case 3:
                ratio = oneByCoinCode.getVip_three();
                break;
            case 4:
                ratio = oneByCoinCode.getVip_four();
                break;
            case 5:
            case 6:
            case 7:
            case 8:
                ratio = oneByCoinCode.getVip_four();
                break;
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
                ratio = oneByCoinCode.getVip_five();
                break;
            default:
                ratio = oneByCoinCode.getVip_one();
                break;
        }
        System.out.println("=======币种推荐奖励比例为：【"+coinCode+"】【"+ratio+"】");
        return ratio;
    }


    public void updateUserMoney(AppFinancialRecommendTransaction transaction,BigDecimal money,Long customerId){
        try {
            if(transaction == null){
                transaction = new AppFinancialRecommendTransaction();
                transaction.setCustomerId(customerId);
                transaction.setCoinCode("WRB");
                transaction.setMoney(money);
                transaction.setIssuedMoney(BigDecimal.ZERO);
                transaction.setCreated(new Date());
                transaction.setModified(new Date());
                appFinancialRecommendTransactionDao.insertSelective(transaction);
            } else {
                transaction.setMoney(transaction.getMoney().add(money));
                transaction.setModified(new Date());
                appFinancialRecommendTransactionDao.updateByPrimaryKeySelective(transaction);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
