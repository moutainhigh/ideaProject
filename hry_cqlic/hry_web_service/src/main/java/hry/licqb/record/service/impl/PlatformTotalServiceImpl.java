/**
 * Copyright:
 * @author:      zhouming
 * @version:     V1.0
 * @Date:        2020-04-02 11:12:05
 */
package hry.licqb.record.service.impl;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.licqb.record.model.PlatformTotal;
import hry.licqb.record.service.PlatformTotalService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p> platformTotalService </p>
 * @author:         zhouming
 * @Date :          2020-04-02 11:12:05
 */
@Service("platformTotalService")
public class PlatformTotalServiceImpl extends BaseServiceImpl<PlatformTotal, Long> implements PlatformTotalService {

    @Resource(name="platformTotalDao")
    @Override
    public void setDao(BaseDao<PlatformTotal, Long> dao) {
        super.dao = dao;
    }


}
