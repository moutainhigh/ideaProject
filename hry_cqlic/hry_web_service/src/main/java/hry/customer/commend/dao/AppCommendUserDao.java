/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      menwei
 * @version:     V1.0 
 * @Date:        2017-11-28 15:30:37 
 */
package hry.customer.commend.dao;

import hry.bean.JsonResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.customer.commend.model.AppCommendUser;
import hry.manage.remote.model.AppCommendUserManage;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * <p> AppCommendUserDao </p>
 * @author:         menwei
 * @Date :          2017-11-28 15:30:37  
 */
public interface AppCommendUserDao extends  BaseDao<AppCommendUser, Long> {
	//查看一级所有人数
	int countOneLevel(@Param("pid") String pid);

	int findLen(@Param("id") String id);

	JsonResult forzen(@Param("ids") String ids);

	JsonResult noforzen(@Param("ids")String ids);

    List<AppCommendUser> findLikeBySid(Map pids);

    List<AppCommendUser> findByAloneMoneyIsNotZero(Map map);
    
    /**
     * sql分页
     * @param map
     * @return
     */
	List<AppCommendUser> findPageBySql(Map<String, Object> map);

	/**
	 * 根据isCulComment获取id，sid数据
	 * @return
	 */
	List<AppCommendUser> findByIsCulCommend(@Param("isCulCommend") Integer isCulCommend);

	void updateIsCulCommendById(@Param("isCulCommend") Integer isCulCommend, @Param("id") Long id);
    
	/**
     * 推荐人详情分页
     * @param map
     * @return
     */
	List<AppCommendUser> findConmmendPageBySql(@Param("sid") String sid, @Param("start") Integer start,
                                               @Param("lengthpage") Integer lengthpage);

	Long findConmmendCountBySid(@Param("sid") String sid);
	/**
     * 推荐人详情分页
     * @param map
     * @return
     */
	List<AppCommendUserManage> findConmmendFrontPageBySql(@Param("sid") String sid, @Param("start") Integer start,
                                                          @Param("lengthpage") Integer lengthpage);

	List<AppCommendUser> findConmmendPageByArray(String[] param);

	List<String> findSid(String str);
	
	int countLen(String sid);
}
