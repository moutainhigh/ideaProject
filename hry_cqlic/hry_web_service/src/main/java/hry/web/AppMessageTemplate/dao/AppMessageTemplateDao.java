/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      niuy
 * @version:     V1.0 
 * @Date:        2018-04-25 16:59:26 
 */
package hry.web.AppMessageTemplate.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import hry.core.mvc.dao.base.BaseDao;
import hry.bean.JsonResult;
import hry.web.AppMessageTemplate.model.AppMessageTemplate;
import hry.web.app.model.AppMessageCateTemp;
import hry.web.app.model.AppMessageCategory;


/**
 * <p> AppMessageTemplateDao </p>
 * @author:         niuy
 * @Date :          2018-04-25 16:59:26  
 */
public interface AppMessageTemplateDao extends  BaseDao<AppMessageTemplate, Long> {

	
	List<AppMessageCateTemp> findKey();

	AppMessageTemplate selectId(@Param("templateId") Long templateId);

	void insertTemplate(@Param("templateId") Long templateId, @Param("title") String title, @Param("content") String content);

	void updateId(@Param("templateId") Long templateId, @Param("title") String title, @Param("content") String content);

}
