/**
 * Copyright:   
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-12 15:44:37 
 */
package hry.admin.exchange.service;

import hry.admin.customer.model.AppCustomer;
import hry.admin.customer.model.AppPersonInfo;
import hry.bean.JsonResult;
import hry.core.mvc.service.base.BaseService;
import hry.admin.exchange.model.ExProduct;

import java.util.List;


/**
 * <p> ExProductService </p>
 * @author:         liushilei
 * @Date :          2018-06-12 15:44:37 
 */
public interface ExProductService  extends BaseService<ExProduct, Long>{

    public boolean findByCoinCode(String c);

    public ExProduct findByallCoinCode(String coinCode);

    // 发布产品
    public JsonResult publishProduct(Long[] ids, String language);

    public ExProduct updateProductToRedis(String coinCode);

    public JsonResult setCoinStatus(Long id, Integer i);

    public List<ExProduct> findByIssueState(Integer i, String saasId);

    public void initRedisCode();

    public JsonResult delishProduct(Long ids);

    public JsonResult openDmAccount(AppCustomer appCustomer, AppPersonInfo appPersonInfo, String website, String currencyType);

    public List<ExProduct> findByIssueState(Integer i);

    public Integer getkeepDecimalForRmb();

    public ExProduct findByCoinCode(String c, String sassId);

    public  void syncedUser(ExProduct exProduct);

    public void pullCoinPrice();

    public void hrySaaSCoinInfo();

    public void initToRedis();


}
