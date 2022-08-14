/**
 * Copyright:  北京互融时代软件有限公司
 *
 * @author: Wu Shuiming
 * @version: V1.0
 * @Date: 2016年5月30日 下午5:45:20
 */
package hry.base.message.service.impl;

import hry.base.message.service.AppMessageCategoryService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;

import javax.annotation.Resource;

import hry.model.base.message.AppMessageCategory;
import org.springframework.stereotype.Service;

/**
 * <p> TODO</p>
 *
 * @author: Wu Shuiming
 * @Date :          2016年5月30日 下午5:45:20
 */
@Service("appMessageCategoryService")
public class AppMessageCategoryServiceImpl extends BaseServiceImpl<AppMessageCategory,Long> implements AppMessageCategoryService {

    @Resource(name = "appMessageCategoryDao")
    @Override
    public void setDao(BaseDao<AppMessageCategory,Long> dao) {
        super.dao = dao;
    }

}