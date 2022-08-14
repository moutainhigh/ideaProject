/**
 * Copyright:   
 * @author:      liuchenghui
 * @version:     V1.0 
 * @Date:        2018-06-29 16:05:59 
 */
package hry.admin.lock.service;

import hry.admin.lock.model.ExDmLockRecord;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;


/**
 * <p> ExDmLockRecordService </p>
 * @author:         liuchenghui
 * @Date :          2018-06-29 16:05:59 
 */
public interface ExDmLockRecordService  extends BaseService<ExDmLockRecord, Long>{

    PageResult findPageBySql (QueryFilter filter);

    JsonResult unlockByManual (Long id, BigDecimal unlocknum);

    JsonResult importExcel(MultipartFile file);

    List<ExDmLockRecord> getRecordBycurdate ();
}
