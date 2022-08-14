/**
 * Copyright:    
 * @author:      sunyujie
 * @version:     V1.0 
 * @Date:        2018-09-28 21:20:08 
 */
package hry.admin.web.dao;

import hry.admin.web.model.AppFileUpload;
import hry.core.mvc.dao.base.BaseDao;


/**
 * <p> appFileUploadDao </p>
 * @author:         sunyujie
 * @Date :          2018-09-28 21:20:08  
 */
public interface AppFileUploadDao extends  BaseDao<AppFileUpload, Long> {

    void uploadOther(AppFileUpload appFileUpload);
}
