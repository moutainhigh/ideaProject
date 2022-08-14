/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      zhangcb
 * @version:     V1.0 
 * @Date:        2016-11-22 18:25:52 
 */
package hry.customer.person.service.impl;

import hry.core.mvc.dao.base.BaseDao;
import hry.bean.PageResult;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.util.QueryFilter;
import hry.util.sys.ContextUtil;
import hry.customer.person.dao.AppPersonInfoDao;
import hry.customer.person.model.AppPersonInfo;
import hry.customer.person.service.AppPersonInfoService;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.Transient;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * <p> AppPersonInfoService </p>
 * @author:         zhangcb
 * @Date :          2016-11-22 18:25:52  
 */
@Service("appPersonInfoService")
public class AppPersonInfoServiceImpl extends BaseServiceImpl<AppPersonInfo, Long> implements AppPersonInfoService{
	
	@Resource(name="appPersonInfoDao")
	@Override
	public void setDao(BaseDao<AppPersonInfo, Long> dao) {
		super.dao = dao;
	}
	/**
	 * 
	 */
	@Override
	public PageResult findPageBySql(QueryFilter filter) {
		
		//----------------------分页查询头部外壳------------------------------
		//创建PageResult对象
		PageResult pageResult = new PageResult();
		Page<AppPersonInfo> page = null;
		if(filter.getPageSize().compareTo(Integer.valueOf(-1))==0){
			//pageSize = -1 时  
			//pageHelper传pageSize参数传0查询全部
			page= PageHelper.startPage(filter.getPage(), 0);
		}else{
			page = PageHelper.startPage(filter.getPage(), filter.getPageSize());
		}
		//----------------------分页查询头部外壳------------------------------
		
		//----------------------查询开始------------------------------
		/*filter.addFilter("country!=", "0086");
		filter.addFilter("country!=", "");
		filter.addFilter("trueName!=", "");*/

		String userName = filter.getRequest().getParameter("userName_like");
		String trueName = filter.getRequest().getParameter("trueName_like");
		String cardId = filter.getRequest().getParameter("cardId_like");
		
		
		
		Map<String,Object> map = new HashMap<String,Object>();
		if(!StringUtils.isEmpty(userName)){
			map.put("userName", "%"+userName+"%");
		}
		if(!StringUtils.isEmpty(trueName)){
			map.put("trueName", "%"+trueName+"%");
		}
		if(!StringUtils.isEmpty(cardId)){
			map.put("cardId", "%"+cardId+"%");
		}
		
		
		
		((AppPersonInfoDao)dao).findPageBySql(map);
	
		
		//----------------------查询结束------------------------------
		
		//----------------------分页查询底部外壳------------------------------
		//设置分页数据
		pageResult.setRows(page.getResult());
		//设置总记录数
		pageResult.setRecordsTotal(page.getTotal());
		pageResult.setDraw(filter.getDraw());
		pageResult.setPage(filter.getPage());
		pageResult.setPageSize(filter.getPageSize());
		//----------------------分页查询底部外壳------------------------------
		
		return pageResult;
	}
	
	/**
	 * 查询方法
	 */
	@Override
	public List<AppPersonInfo> getById(Long id) {

		Map<String, Object> map=new HashMap<String, Object>();
		
		if(null!=id&&!"".equals(id)){
			map.put("id", id);
			List<AppPersonInfo> list=((AppPersonInfoDao)dao).getById(map);
			return list;
		}
		
		return null;
	
	}
	

	/**
	 * 注册用户审核
	 */
	@Override
	public boolean updateExamine(AppPersonInfo appPersonInfo) {
		try {
			AppPersonInfo personInfo=super.get(Long.valueOf(appPersonInfo.getId()));
			if(appPersonInfo!=null&&appPersonInfo.getIsExamine()==1){//审核通过
				personInfo.setIsExamine(Integer.valueOf(1));
			}else if(appPersonInfo!=null&&appPersonInfo.getIsExamine()==2){//审核不通过
				/*personInfo.setIsExamine(Integer.valueOf(2));
				personInfo.setEamineRefusalReason(appPersonInfo.getEamineRefusalReason());*/
				//浙江龙银--审核拒绝前台重新进入审核
				//把name和卡号置空
				personInfo.setTrueName("");
				personInfo.setCardIdUsd("");
				personInfo.setCardId("");
				//审核状态初始为0
				personInfo.setIsExamine(Integer.valueOf(0));
				personInfo.setEamineRefusalReason(appPersonInfo.getEamineRefusalReason());
			}else{
				return false;
			}
			super.update(personInfo);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * 根据customerId查询personinfo对象
	 * <p> TODO</p>
	 * @author:         Zhang Lei
	 * @param:    @param customerId
	 * @param:    @return
	 * @return: AppPersonInfo 
	 * @Date :          2017年3月8日 下午2:58:52   
	 * @throws:
	 */
	@Override
	public AppPersonInfo getByCustomerId(Long customerId){
		if(null!=customerId){
			QueryFilter filter = new QueryFilter(AppPersonInfo.class);
			filter.addFilter("customerId=", customerId);
			return get(filter);
		}
		return null;
	}
	
	
	/**
	 * 代理商申请列表
	 */
	@Override
	public PageResult findAgentApplyList(QueryFilter filter) {
		//----------------------分页查询头部外壳------------------------------
		//创建PageResult对象
		PageResult pageResult = new PageResult();
		Page<AppPersonInfo> page = null;
		if(filter.getPageSize().compareTo(Integer.valueOf(-1))==0){
			page= PageHelper.startPage(filter.getPage(), 0);
		}else{
			page = PageHelper.startPage(filter.getPage(), filter.getPageSize());
		}
		//----------------------分页查询头部外壳------------------------------
		
		//----------------------查询开始------------------------------
		String userName = filter.getRequest().getParameter("userName_like");
		String trueName = filter.getRequest().getParameter("trueName_like");
		
		Map<String,Object> map = new HashMap<String,Object>();
		if(!StringUtils.isEmpty(userName)){
			map.put("userName", "%"+userName+"%");
		}
		if(!StringUtils.isEmpty(trueName)){
			map.put("trueName", "%"+trueName+"%");
		}
		
		((AppPersonInfoDao)dao).findAgentApplyList(map);
	
		//----------------------查询结束------------------------------
		//----------------------分页查询底部外壳------------------------------
		//设置分页数据
		pageResult.setRows(page.getResult());
		//设置总记录数
		pageResult.setRecordsTotal(page.getTotal());
		pageResult.setDraw(filter.getDraw());
		pageResult.setPage(filter.getPage());
		pageResult.setPageSize(filter.getPageSize());
		//----------------------分页查询底部外壳------------------------------
		
		return pageResult;
	}
	
	/**
	 * 判断申请的该地区的代理是否已存在
	 * <p> TODO</p>
	 * @author:         Zhang Lei
	 * @param:    @param agentLevel
	 * @param:    @param provinceId
	 * @param:    @param cityId
	 * @param:    @param countyId
	 * @param:    @return
	 * @return: boolean 
	 * @Date :          2017年3月22日 下午2:19:54   
	 * @throws:
	 */
	@Override
	public boolean isAgentExist(String agentLevel, String provinceId,String cityId, String countyId) {
		AppPersonInfo person=null;
		boolean res=false;
		//0 会员   1 区代（县代）  2 市代   3省代
		if("3".equals(agentLevel)){
			 person=((AppPersonInfoDao)dao).findAgentByTypeAndAreacode(agentLevel,provinceId);
		}else if("2".equals(agentLevel)){
			 person=((AppPersonInfoDao)dao).findAgentByTypeAndAreacode(agentLevel,cityId);
		}else if("1".equals(agentLevel)){
			 person=((AppPersonInfoDao)dao).findAgentByTypeAndAreacode(agentLevel,countyId);
		}
		
		if(person!=null){
			res=true;
		}
		return res;
	}
	
	/**
	 * 获取推荐人的personinfo对象
	 * <p> TODO</p>
	 * @author:         Zhang Lei
	 * @param:    @return
	 * @return: AppPersonInfo 
	 * @Date :          2017年3月30日 下午4:03:18   
	 * @throws:
	 */
	@Override
	public AppPersonInfo getAgentPerson(Long customerid) {
		return ((AppPersonInfoDao)dao).getAgentPerson(customerid);
	}
	
	
	
	/**
	 * 金科新加用户资金报表
	 * <p> TODO</p>
	 * @author:         Zhang Lei
	 * @param:    @param request
	 * @param:    @return
	 * @return: PageResult 
	 * @Date :          2017年4月12日 上午11:21:19   
	 * @throws:
	 */
	@Override
	public PageResult findPageBySql1(QueryFilter filter) {
		//----------------------分页查询头部外壳------------------------------
		//创建PageResult对象
		PageResult pageResult = new PageResult();
		Page<AppPersonInfo> page = null;
		if(filter.getPageSize().compareTo(Integer.valueOf(-1))==0){
			page= PageHelper.startPage(filter.getPage(), 0);
		}else{
			page = PageHelper.startPage(filter.getPage(), filter.getPageSize());
		}
		//----------------------分页查询头部外壳------------------------------
		
		//----------------------查询开始------------------------------
		String userName = filter.getRequest().getParameter("userName_like");
		String trueName = filter.getRequest().getParameter("trueName_like");
		
		Map<String,Object> map = new HashMap<String,Object>();
		if(!StringUtils.isEmpty(userName)){
			map.put("userName", "%"+userName+"%");
		}
		if(!StringUtils.isEmpty(trueName)){
			map.put("trueName", "%"+trueName+"%");
		}
		
		((AppPersonInfoDao)dao).findPageBySql1(map);
		//----------------------查询结束------------------------------
		
		//----------------------分页查询底部外壳------------------------------
		
		//设置分页数据
		pageResult.setRows(page.getResult());
		//设置总记录数
		pageResult.setRecordsTotal(page.getTotal());
		pageResult.setDraw(filter.getDraw());
		pageResult.setPage(filter.getPage());
		pageResult.setPageSize(filter.getPageSize());
		//----------------------分页查询底部外壳------------------------------
		return pageResult;
	}
	
	
	public List<AppPersonInfo> getAppPersonInfoByEmailPhone(List<String> listId) {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("listId", listId);
		return ((AppPersonInfoDao)dao).getAppPersonInfoByEmailPhone(paramMap);
	}
	
}