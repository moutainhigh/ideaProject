/**
 * Copyright:   
 * @author:      xubb
 * @version:     V1.0 
 * @Date:        2019-07-04 10:06:17 
 */
package hry.app.account.service;

import hry.bean.FrontPage;
import hry.core.mvc.service.base.BaseService;
import hry.otc.manage.remote.model.account.OtcAccountRecord;

import java.util.Map;


/**
 * <p> OtcAccountRecordService </p>
 * @author:         xubb
 * @Date :          2019-07-04 10:06:17 
 */
public interface OtcAccountRecordService extends BaseService<OtcAccountRecord, Long>{

    FrontPage getAccountRecord (Map<String, String> params);
}
