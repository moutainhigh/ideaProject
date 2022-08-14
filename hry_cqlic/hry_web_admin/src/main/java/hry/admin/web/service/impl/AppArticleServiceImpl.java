/**
 * Copyright:   
 * @author:      liuchenghui
 * @version:     V1.0 
 * @Date:        2018-06-26 14:55:28 
 */
package hry.admin.web.service.impl;

import hry.admin.web.model.AppArticle;
import hry.admin.web.service.AppArticleService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;

import hry.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p> AppArticleService </p>
 * @author:         liuchenghui
 * @Date :          2018-06-26 14:55:28  
 */
@Service("appArticleService")
public class AppArticleServiceImpl extends BaseServiceImpl<AppArticle, Long> implements AppArticleService{
	
	@Resource(name="appArticleDao")
	@Override
	public void setDao(BaseDao<AppArticle, Long> dao) {
		super.dao = dao;
	}

	@Override
	public String removeAll (String ids) {
		try {
			if (!StringUtils.isEmpty(ids)) {
                String[] idArr = ids.split(",");
                for (int i = 0; i < idArr.length; i++) {
                    delete(new Long(idArr[i]));
                }
                return "OK";
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "fail";
	}

}
