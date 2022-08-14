/**
 * Copyright:   
 * @author:      jidn
 * @version:     V1.0 
 * @Date:        2019-04-03 11:06:06 
 */
package hry.financail.financing.service.impl;

import com.github.pagehelper.Page;
import hry.bean.FrontPage;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.financail.customer.model.AppPersonInfo;
import hry.financail.customer.service.AppPersonInfoService;
import hry.financail.financing.dao.AppFinancialGiftRecordDao;
import hry.financail.financing.dao.AppFinancialRedAcountDao;
import hry.financail.financing.model.AppFinancialGiftRecord;
import hry.financail.financing.model.AppFinancialRedAcount;
import hry.financail.financing.service.AppFinancialGiftRecordService;
import hry.remote.model.RemoteResult;
import hry.util.PageFactory;
import hry.util.QueryFilter;
import hry.util.date.DateUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p> AppFinancialGiftRecordService </p>
 * @author:         jidn
 * @Date :          2019-04-03 11:06:06  
 */
@Service("appFinancialGiftRecordService")
public class AppFinancialGiftRecordServiceImpl extends BaseServiceImpl<AppFinancialGiftRecord, String> implements AppFinancialGiftRecordService{
	
	@Resource(name="appFinancialGiftRecordDao")
	@Override
	public void setDao(BaseDao<AppFinancialGiftRecord, String> dao) {
		super.dao = dao;
	}

	@Resource
	private AppFinancialGiftRecordDao appFinancialGiftRecordDao;
    @Resource
	private AppFinancialRedAcountDao appFinancialRedAcountDao;

    @Resource
    private AppPersonInfoService appPersonInfoService;

	@Override
	public FrontPage findUserRedAccount(Map<String, String> paramMap) {
		Page page = PageFactory.getPage(paramMap);

		Map<String,Object> map = new HashMap<String,Object>(2);
		String customerId = paramMap.get("customerId");

		if(!StringUtils.isEmpty(customerId)){
			map.put("customerId", customerId);
		}

        List<hry.remote.model.AppFinancialGiftRecord> userRedAccount = appFinancialGiftRecordDao.findUserRedAccount(map);

        return new FrontPage(userRedAccount, userRedAccount.size(), page.getPages(), page.getPageSize());
	}

	@Override
	public RemoteResult receiveAllRedAccount(String customerId, String coinCode) {
        RemoteResult remoteResult = new RemoteResult();
       try {
           //查询所有未领取的没过期的红包
           QueryFilter filter = new QueryFilter(AppFinancialGiftRecord.class);
           filter.addFilter("customerId_EQ",customerId);
           filter.addFilter("coinCode_EQ",coinCode);
           filter.addFilter("state_EQ","0");
           filter.addFilter("overTime>", DateUtil.getNewDate());
           List<AppFinancialGiftRecord> list = appFinancialGiftRecordDao.selectByExample(filter);

           //查询红包账户
           QueryFilter filter1 = new QueryFilter(AppFinancialRedAcount.class);
           filter1.addFilter("customerId_EQ",customerId);
           filter1.addFilter("coinCode_EQ",coinCode);
           AppFinancialRedAcount appFinancialRedAcount = appFinancialRedAcountDao.selectOneByExample(filter1);
           list.forEach( afgr -> {
               appFinancialRedAcount.setMoney(appFinancialRedAcount.getMoney().add(afgr.getGiftMoney()));
               afgr.setState(1);
               appFinancialGiftRecordDao.updateByPrimaryKeySelective(afgr);
           });

           appFinancialRedAcountDao.updateByPrimaryKeySelective(appFinancialRedAcount);
           remoteResult.setSuccess(true);
       } catch (Exception e){
            e.printStackTrace();
       }
        return remoteResult;
	}

    /**
     * 领取所有红包
     * @param customerId
     * @param coinCode
     * @return
     */
    @Override
	public RemoteResult receiveAllGiftRecord(String customerId, String coinCode){
        RemoteResult remoteResult = new RemoteResult();
        try{
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("customerId", customerId);
            map.put("coinCode", coinCode);
            map.put("state", "0");
            //查询所有未领取的没过期的红包
            List<AppFinancialGiftRecord> list = appFinancialGiftRecordDao.selectAllUnreceived(map);

            if(list != null && list.size() > 0){

                QueryFilter perFilter = new QueryFilter(AppPersonInfo.class);
                perFilter.addFilter("customerId_EQ",customerId);
                AppPersonInfo appPersonInfo = appPersonInfoService.get(perFilter);


                BigDecimal money = BigDecimal.ZERO;
                for(AppFinancialGiftRecord afgr : list){
                    money = money.add(afgr.getGiftMoney());

                    afgr.setState(1);
                    afgr.setReceiveTime(new Date());
                    appFinancialGiftRecordDao.updateByPrimaryKeySelective(afgr);
                }

                //查询是否存在红包账户
                Map<String, String> pMap = new HashMap<String, String>();
                pMap.put("customerId", customerId);
                pMap.put("coinCode", coinCode);
                AppFinancialRedAcount appFinancialRedAcount = appFinancialRedAcountDao.findUserRedAccount(pMap);
                if(appFinancialRedAcount == null){
                    appFinancialRedAcount = new AppFinancialRedAcount();
                    appFinancialRedAcount.setMoney(money);
                    appFinancialRedAcount.setMobile(appPersonInfo.getMobilePhone());
                    appFinancialRedAcount.setUserName(appPersonInfo.getSurname()+appPersonInfo.getTrueName());
                    appFinancialRedAcount.setCustomerId(Long.valueOf(customerId));
                    appFinancialRedAcount.setCoinCode(coinCode);
                    appFinancialRedAcount.setCreated(new Date());
                    appFinancialRedAcount.setModified(new Date());
                    appFinancialRedAcountDao.insertSelective(appFinancialRedAcount);
                } else {
                    appFinancialRedAcount.setMoney(appFinancialRedAcount.getMoney().add(money));
                    appFinancialRedAcountDao.updateByPrimaryKeySelective(appFinancialRedAcount);
                }
            }
            remoteResult.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
        }
        return remoteResult;
    }
}
