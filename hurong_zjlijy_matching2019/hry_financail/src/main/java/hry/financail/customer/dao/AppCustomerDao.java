package hry.financail.customer.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.financail.customer.model.AppCustomer;


public interface AppCustomerDao extends  BaseDao<AppCustomer, Long> {

    /**
     * 根据推荐码查人
     * @param code
     * @return
     */
    public AppCustomer findAppCustomerByReferralCode(String referralCode);
}
