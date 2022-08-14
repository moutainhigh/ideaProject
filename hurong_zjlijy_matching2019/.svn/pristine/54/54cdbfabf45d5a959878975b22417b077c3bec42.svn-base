/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年3月24日 下午2:04:29
 */
package hry.mq.message.service.impl;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.model.message.AppException;
import hry.mq.message.service.AppExceptionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * TODO
 * </p>
 * 
 * @author: syj
 * @Date : 2018年8月9日 下午5:00:18
 */
@Service("appExceptionService")
public class AppExceptionServiceImpl extends BaseServiceImpl<AppException, Long> implements AppExceptionService {

    @Resource(name = "appExceptionDao")
    @Override
    public void setDao(BaseDao<AppException, Long> dao) {
            super.dao = dao;
    }
}
