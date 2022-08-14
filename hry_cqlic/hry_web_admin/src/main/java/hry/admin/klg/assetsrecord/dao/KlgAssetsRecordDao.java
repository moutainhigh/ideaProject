/**
 * Copyright:    
 * @author:      yaozhuo
 * @version:     V1.0 
 * @Date:        2019-04-29 15:54:03 
 */
package hry.admin.klg.assetsrecord.dao;

import java.util.List;
import java.util.Map;

import hry.admin.klg.assetsrecord.model.KlgAssetsRecord;
import hry.admin.klg.assetsrecord.model.vo.KlgAssetsRecordVo;
import hry.core.mvc.dao.base.BaseDao;


/**
 * <p> KlgAssetsRecordDao </p>
 * @author:         yaozhuo
 * @Date :          2019-04-29 15:54:03  
 */
public interface KlgAssetsRecordDao extends  BaseDao<KlgAssetsRecord, Long> {
	
	/**
     * 新sql分页查询
     * @param map
     * @return
     */
    List<KlgAssetsRecordVo> findPageBySql(Map<String,Object> map);

}
