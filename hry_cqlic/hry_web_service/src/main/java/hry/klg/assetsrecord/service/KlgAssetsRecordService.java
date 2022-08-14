/**
 * Copyright:   
 * @author:      yaozhuo
 * @version:     V1.0 
 * @Date:        2019-04-29 16:16:58 
 */
package hry.klg.assetsrecord.service;

import hry.core.mvc.service.base.BaseService;
import hry.klg.assetsrecord.model.KlgAssetsRecord;



/**
 * <p> KlgAssetsRecordService </p>
 * @author:         yaozhuo
 * @Date :          2019-04-29 16:16:58 
 */
public interface KlgAssetsRecordService  extends BaseService<KlgAssetsRecord, Long>{


    //抓取提币审核记录级流水
    void  saveTiBiRecord();

}
