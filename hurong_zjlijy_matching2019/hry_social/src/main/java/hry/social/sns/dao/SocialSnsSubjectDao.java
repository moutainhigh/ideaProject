/**
 * Copyright:    
 * @author:      lixin
 * @version:     V1.0 
 * @Date:        2019-05-13 10:21:57 
 */
package hry.social.sns.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.social.manage.remote.model.sns.SocialSnsSubject;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * <p> SocialSnsSubjectDao </p>
 * @author:         lixin
 * @Date :          2019-05-13 10:21:57  
 */
public interface SocialSnsSubjectDao extends  BaseDao<SocialSnsSubject, Long> {

    List<SocialSnsSubject> subjectInfo(@Param("language") String language);
}
