/**
 * Copyright:   
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-07-11 14:27:21 
 */
package hry.admin.web.service;

import hry.admin.web.model.WhiteList;
import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;

import java.util.Map;


/**
 * <p> WhiteListService </p>
 * @author:         liushilei
 * @Date :          2018-07-11 14:27:21 
 */
public interface WhiteListService  extends BaseService<WhiteList, Long>{


    PageResult findCustomListByPage (Map<String, String> paraMap);

    PageResult findWhiteListBySql (Map<String, String> paraMap);
}
