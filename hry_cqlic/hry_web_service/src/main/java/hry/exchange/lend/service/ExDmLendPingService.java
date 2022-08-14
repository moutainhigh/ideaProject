package hry.exchange.lend.service;

import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.exchange.lend.model.ExDmPing;

public interface ExDmLendPingService extends BaseService<ExDmPing,Long> {

    public PageResult see(QueryFilter filter);

    boolean stopeAlllistByapply(Long customerId, String currencyType, String website);

    boolean stopeMoneylistByapply(Long customerId, String currencyType, String website);
}
