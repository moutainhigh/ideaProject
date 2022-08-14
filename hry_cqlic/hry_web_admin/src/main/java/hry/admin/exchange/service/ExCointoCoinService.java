/**
 * Copyright:   
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-12 15:52:02 
 */
package hry.admin.exchange.service;

import hry.core.mvc.service.base.BaseService;
import hry.admin.exchange.model.ExCointoCoin;

import java.util.List;


/**
 * <p> ExCointoCoinService </p>
 * @author:         liushilei
 * @Date :          2018-06-12 15:52:02 
 */
public interface ExCointoCoinService  extends BaseService<ExCointoCoin, Long>{
    public void initRedisCode();

    public List<ExCointoCoin> getBylist(String toProductcoinCode, String fromProductcoinCode, Integer issueState);

    public List<ExCointoCoin> findByList();


    public void newinitRedisCode();

    public void openNewCointoCoin(ExCointoCoin exCointoCoin);

    public void closeCointoCoin(ExCointoCoin exCointoCoin);

    List<ExCointoCoin> findCoinCodes();

}
