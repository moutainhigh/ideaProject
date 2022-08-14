package hry.ico.service.impl;

import hry.core.mvc.service.base.BaseService;
import hry.exchange.transaction.service.ExDmTransactionService;
import hry.ico.service.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("icoBillDetailsService")
public class IcoBillDetailsServiceImpl implements IcoBillDetailsService {

    @Resource
    private IcoBuyOrderService icoBuyOrderService;
    @Resource
    private IcoLockRecordService icoLockRecordService;
    @Resource
    private IcoTransferAccountsService icoTransferAccountsService;
    @Resource
    private IcoAwardRecordService icoAwardRecordService;
    @Resource
    private IcoDividendRecordService icoDividendRecordService;
    @Resource
    private ExDmTransactionService exDmTransactionService;
    @Resource
    private IcoDividendManualRecordService dividendManualRecordService;
    /**
     *
     * @param type
     * 类型( 11.锁仓12.释放 13锁仓扣币21转账入22转账出31分红32推荐奖励33后台分红 41.充币42.提币51.买入52.卖出)
     * @param keyId
     * @return
     */
    public Object getBillDetails(Integer type,Long keyId){
        Object obj=null;
        if(type==11||type==12){//锁仓释放
            obj=icoLockRecordService.get(keyId);
        }else if(type==51){//购买平台
            obj=icoBuyOrderService.get(keyId);
        }else if(type==21||type==22){//转账
           // obj=icoTransferAccountsService.get(keyId);
            obj=icoTransferAccountsService.getTransferAccountsInfo(keyId);
        }else if(type==31){//分红
            obj=icoDividendRecordService.get(keyId);
        }else if(type==33){//后台分红
            obj=dividendManualRecordService.get(keyId);
        }else if(type==32){//推荐奖励
            obj=icoAwardRecordService.get(keyId);
        }else if(type==41||type==42){//41.充币42.提币
            obj=exDmTransactionService.get(keyId);
        }
        return obj;
    }

}
