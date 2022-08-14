/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Gao Mimi
 * @version:      V1.0 
 * @Date:        2016年8月25日 下午5:18:49
 */
package hry.admin.exchange.service;

import hry.admin.exchange.model.Coin;
import hry.admin.exchange.model.ProductCommon;

/**
 * <p> TODO</p>
 * @author:         Gao Mimi 
 * @Date :          2016年8月25日 下午5:18:49 
 */
public interface ProductCommonService {
 // public ProductCommon getProductCommon(String coinCode,String fixPriceCoinCode);
  public ProductCommon getProductCommon(String coinCode);
  public Coin getProductCommon(String coinCode, String fixPriceCoinCode);
  public Coin getCoinFromreds(String coinCode, String fixPriceCoinCode);
}
