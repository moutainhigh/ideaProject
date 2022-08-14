/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      menwei
 * @version:     V1.0 
 * @Date:        2017-11-28 15:30:37 
 */
package hry.mall.member.commend.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.bean.JsonResult;
import hry.mall.member.commend.model.AppCommendUser;
import hry.mall.integral.remote.model.AppCommendUserManage;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;


/**
 * <p> AppCommendUserDao </p>
 * @author:         menwei
 * @Date :          2017-11-28 15:30:37  
 */
public interface AppCommendUserDao extends  BaseDao<AppCommendUser, Long> {


	int findLen(@Param("id") String id);

	int findIsRealLen(@Param("id") String id);

	JsonResult forzen(@Param("ids") String ids);

	JsonResult noforzen(@Param("ids") String ids);

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
     * @param
     * @return
     */
	List<AppCommendUser> findConmmendPageBySql(@Param("sid") String sid, @Param("start") Integer start,
                                               @Param("lengthpage") Integer lengthpage);

	Long findConmmendCountBySid(@Param("sid") String sid);
	/**
     * 推荐人详情分页
     * @param
     * @return
     */
	List<AppCommendUserManage> findConmmendFrontPageBySql(@Param("sid") String sid, @Param("start") Integer start,
                                                          @Param("lengthpage") Integer lengthpage);

	AppCommendUser findPageBySqlIntegral(long memberId);

	/**
	 * 根据时间查询当前用户推荐人数
	 * */
	int findDateUserCount(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("memberId") Long memberId);
	
	/**
	 * 根据当前用户推荐的会员总人数
	 * */
	public int findUserCount(@Param("memberId") Long memberId);


	List<Long> getSubordinateIdBySeries(String sid);
}
