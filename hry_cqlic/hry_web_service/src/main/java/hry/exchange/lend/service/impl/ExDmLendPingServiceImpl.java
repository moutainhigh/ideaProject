package hry.exchange.lend.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import hry.account.fund.model.AppTransaction;
import hry.account.fund.service.AppTransactionService;
import hry.core.mvc.dao.base.BaseDao;
import hry.bean.PageResult;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.util.QueryFilter;
import hry.exchange.lend.dao.ExDmLendDao;
import hry.exchange.lend.dao.ExDmLendPingDao;
import hry.exchange.lend.model.ExDmLend;
import hry.exchange.lend.model.ExDmPing;
import hry.exchange.lend.service.ExDmLendPingService;
import hry.exchange.transaction.model.ExDmTransaction;
import hry.exchange.transaction.service.ExDmTransactionService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("exDmLendPingService")
public class ExDmLendPingServiceImpl extends BaseServiceImpl<ExDmPing,Long> implements ExDmLendPingService {

    @Resource(name = "exDmLendPingDao")
    @Override
    public void setDao(BaseDao<ExDmPing, Long> dao) {
        super.dao=dao;
    }
    @Resource
    public AppTransactionService appTransactionService;
    @Resource
    public ExDmTransactionService exDmTransactionService;


    @Override
    public PageResult see(QueryFilter filter) {


        //----------------------分页查询头部外壳------------------------------
        //创建PageResult对象
        PageResult pageResult = new PageResult();
        Page<ExDmLend> page = null;
        if(filter.getPageSize().compareTo(Integer.valueOf(-1))==0){
            //pageSize = -1 时
            //pageHelper传pageSize参数传0查询全部
            page= PageHelper.startPage(filter.getPage(), 0);
        }else{
            page = PageHelper.startPage(filter.getPage(), filter.getPageSize());
        }
        //----------------------分页查询头部外壳------------------------------

        //----------------------查询开始------------------------------

        String userName = filter.getRequest().getParameter("userName_LIKE");

        Map<String,String> map = new HashMap<String,String>();
        if(!StringUtils.isEmpty(userName)){
            map.put("userName", "%"+userName+"%");
        }

        ((ExDmLendPingDao)dao).see(map);
        //设置分页数据
        pageResult.setRows(page.getResult());
        //设置总记录数
        pageResult.setRecordsTotal(page.getTotal());
        pageResult.setDraw(filter.getDraw());
        pageResult.setPage(filter.getPage());
        pageResult.setPageSize(filter.getPageSize());

        return pageResult;
    }

    /**
     * 驳回所有提币申请
     * @param customerId
     * @param currencyType
     * @param website
     */
    public boolean stopeAlllistByapply(Long customerId, String currencyType, String website) {
        try {
            QueryFilter filter = new QueryFilter(ExDmTransaction.class);
            filter.addFilter("status=",1);
            filter.addFilter("transactionType=",2);
            List<ExDmTransaction> exDmTransactions = exDmTransactionService.find(filter);
            if(exDmTransactions!=null&&exDmTransactions.size()>0){
                for(ExDmTransaction transaction:exDmTransactions){
                    boolean flag = appTransactionService.dmWithdrawReject(transaction);
                }
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 驳回某用户的所有提现申请
     * @param customerId
     * @param currencyType
     * @param website
     * @return
     */
    @Override
    public boolean stopeMoneylistByapply(Long customerId, String currencyType, String website) {
        try {
            QueryFilter filter = new QueryFilter(AppTransaction.class);
            filter.addFilter("status=",1);
            filter.addFilter("transactionType=",4);
            List<AppTransaction> appTransactions = appTransactionService.find(filter);
            if(appTransactions!=null&&appTransactions.size()>0){
                for(AppTransaction transaction:appTransactions){
                    boolean flag = appTransactionService.withdrawReject(transaction);
                }
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
