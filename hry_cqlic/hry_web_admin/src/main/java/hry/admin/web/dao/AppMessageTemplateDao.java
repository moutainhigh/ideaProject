/**
 * Copyright:    
 * @author:      liuchenghui
 * @version:     V1.0 
 * @Date:        2018-07-05 10:23:25 
 */
package hry.admin.web.dao;

import hry.admin.web.model.AppMessageCateTemp;
import hry.core.mvc.dao.base.BaseDao;
import hry.admin.web.model.AppMessageTemplate;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * <p> AppMessageTemplateDao </p>
 * @author:         liuchenghui
 * @Date :          2018-07-05 10:23:25  
 */
public interface AppMessageTemplateDao extends  BaseDao<AppMessageTemplate, Long> {

    List<AppMessageCateTemp> findKey ();

    AppMessageTemplate selectId (@Param("templateId") Long templateId);

    void insertTemplate(@Param("templateId")Long templateId, @Param("title")String title, @Param("content")String content);

    void updateId(@Param("templateId")Long templateId, @Param("title")String title, @Param("content")String content);
}
