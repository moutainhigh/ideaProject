/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      gaomimi
 * @version:     V1.0 
 * @Date:        2017-07-06 19:40:34 
 */
package hry.exchange.product.service;

import java.util.List;

import hry.core.mvc.service.base.BaseService;
import hry.exchange.product.model.ExCointoCoin;
import hry.exchange.product.model.ExProductVo;



/**
 * <p> ExCointoCoinService </p>
 * @author:         gaomimi
 * @Date :          2017-07-06 19:40:34  
 */
public interface ExCointoCoinService  extends BaseService<ExCointoCoin, Long>{

   public List<ExCointoCoin> getBylist(String toProductcoinCode, String fromProductcoinCode, Integer issueState);
   public List<ExCointoCoin> getBylist(Integer state);
   public void initRedisCode();
   public void updateCode(Long id);
   public ExProductVo selectCode(String email, String password);
   public ExProductVo selectCodePhone(String mobilePhone, String password);	
   public List<ExCointoCoin> findByList();	
}
