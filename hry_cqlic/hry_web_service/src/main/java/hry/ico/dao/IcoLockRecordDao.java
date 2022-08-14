/**
 * Copyright:    
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-01-15 14:34:14 
 */
package hry.ico.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.ico.model.IcoLockRecord;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;


/**
 * <p> IcoLockRecordDao </p>
 * @author:         lzy
 * @Date :          2019-01-15 14:34:14  
 */
public interface IcoLockRecordDao extends  BaseDao<IcoLockRecord, Long> {
    //释放
    int updateByState(@Param("id") Long id,
                      @Param("releaseDeduct") BigDecimal releaseDeduct,
                      @Param("releaseType") Integer releaseType,
                      @Param("actualReleaseNum") BigDecimal actualReleaseNum,
                      @Param("releaseDeductNum") BigDecimal releaseDeductNum);
    //追加天数
    int appendLockDays(@Param("id") Long id,@Param("addDay") Integer addDay);

    List<IcoLockRecord> getWaitReleaseList();
}
