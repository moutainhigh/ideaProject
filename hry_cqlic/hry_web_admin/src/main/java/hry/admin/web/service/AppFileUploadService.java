/**
 * Copyright:   
 * @author:      sunyujie
 * @version:     V1.0 
 * @Date:        2018-09-28 21:20:08 
 */
package hry.admin.web.service;

import hry.admin.web.model.AppFileUpload;
import hry.core.mvc.service.base.BaseService;



/**
 * <p> appFileUploadService </p>
 * @author:         sunyujie
 * @Date :          2018-09-28 21:20:08 
 */
public interface AppFileUploadService extends BaseService<AppFileUpload, Long>{


    void uploadOther(AppFileUpload appFileUpload);

    void initRedis();
}
