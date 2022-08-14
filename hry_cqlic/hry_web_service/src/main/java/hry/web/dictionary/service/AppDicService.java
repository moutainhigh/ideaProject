/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      liuchenghui
 * @version:     V1.0 
 * @Date:        2018-06-28 15:48:48 
 */
package hry.web.dictionary.service;

import hry.core.mvc.service.base.BaseService;
import hry.web.dictionary.model.AppDic;

import java.util.List;


/**
 * <p> AppDicService </p>
 * @author:         liuchenghui
 * @Date :          2018-06-28 15:48:48  
 */
public interface AppDicService  extends BaseService<AppDic, Long>{


    List<AppDic> findByPkey(String pkey);

    List<String> findValByPkey(String pkey);
}
