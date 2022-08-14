/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年3月24日 下午2:04:29
 */
package hry.exchange.lend.service.impl;

import hry.core.mvc.dao.base.BaseDao;
import hry.util.PageFactory;
import hry.bean.PageResult;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.util.QueryFilter;
import hry.util.RemoteQueryFilter;
import hry.util.idgenerate.IdGenerate;
import hry.util.idgenerate.NumConstant;
import hry.util.sys.ContextUtil;
import hry.customer.person.model.AppPersonInfo;
import hry.customer.remote.RemoteAppPersonInfoService;
import hry.customer.user.model.AppCustomer;
import hry.exchange.entrust.dao.ExExEntrustDao;
import hry.exchange.entrust.service.ExExEntrustService;
import hry.exchange.lend.dao.ExDmLendIntentDao;
import hry.exchange.lend.model.ExDmLend;
import hry.exchange.lend.model.ExDmLendIntent;
import hry.exchange.lend.model.vo.ExDmLendIntentAndCustomer;
import hry.exchange.lend.service.ExDmLendIntentService;
import hry.exchange.lend.service.ExDmLendService;
import hry.exchange.websocketclient.soleClient;
import hry.exchange.websocketclient.clienthandler.MyWebSocketClient;
import hry.manage.remote.model.Entrust;
import hry.manage.remote.model.Lend;
import hry.manage.remote.model.LendIntent;
import hry.manage.remote.model.base.FrontPage;
import hry.trade.entrust.dao.ExEntrustDao;
import hry.trade.entrust.model.ExEntrust;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * <p> TODO</p>
 * @author:         Gao Mimi 
 * @Date :          2016年4月12日 下午4:45:50 
 */
@Service("exDmLendIntentService")
public class ExDmLendIntentServiceImpl extends BaseServiceImpl<ExDmLendIntent, Long>
		implements ExDmLendIntentService {

	@Resource(name = "exDmLendIntentDao")
	@Override
	public void setDao(BaseDao<ExDmLendIntent, Long> dao) {
		super.dao = dao;
	}

	@Override
	public ExDmLendIntent create(ExDmLend exDmLend,BigDecimal repayCount,String intentType) {
		ExDmLendIntent exDmLendIntent=new ExDmLendIntent();
		exDmLendIntent.setAccountId(exDmLend.getAccountId());
		exDmLendIntent.setCustomerId(exDmLend.getCustomerId());
		exDmLendIntent.setLendCoinType(exDmLend.getLendCoinType());
		exDmLendIntent.setLendCoin(exDmLend.getLendCoin());
		exDmLendIntent.setIntentNum(IdGenerate.transactionNum(NumConstant.Ex_Dm_Lend_Intent));
		exDmLendIntent.setLendId(exDmLend.getId());
		exDmLendIntent.setIntentTime(new Date());
		exDmLendIntent.setRepayCount(repayCount);
		exDmLendIntent.setIntentType(intentType);
		exDmLendIntent.setSaasId(exDmLend.getSaasId());
		exDmLendIntent.setWebsite(exDmLend.getWebsite());
		exDmLendIntent.setCurrencyType(exDmLend.getCurrencyType());
		RemoteAppPersonInfoService remoteAppPersonInfoService = (RemoteAppPersonInfoService)ContextUtil.getBean("remoteAppPersonInfoService");
		AppPersonInfo appPersonInfo = remoteAppPersonInfoService.getByCustomerId(exDmLend.getCustomerId());
		exDmLendIntent.setTrueName(appPersonInfo.getTrueName());
		exDmLendIntent.setUserName(exDmLend.getUserName());
		return exDmLendIntent;
	}

	@Override
	public FrontPage listIntentPage(Map<String, String> params) {
		
		Page page = PageFactory.getPage(params);
		// 查询方法
		List<LendIntent> list = ((ExDmLendIntentDao) dao).findLendIntentList(params);
		return new FrontPage(list, page.getTotal(), page.getPages(), page.getPageSize());
	}

	@Override
	public PageResult findPageBySql(QueryFilter filter) {
		
		//----------------------分页查询头部外壳------------------------------
				//创建PageResult对象
				PageResult pageResult = new PageResult();
				Page<AppCustomer> page = null;
				if(filter.getPageSize().compareTo(Integer.valueOf(-1))==0){
					//pageSize = -1 时  
					//pageHelper传pageSize参数传0查询全部
					page= PageHelper.startPage(filter.getPage(), 0);
				}else{
					page = PageHelper.startPage(filter.getPage(), filter.getPageSize());
				}
				//----------------------分页查询头部外壳------------------------------
				
				//----------------------查询开始------------------------------
				
				String userName = filter.getRequest().getParameter("userName_like");
				String trueName = filter.getRequest().getParameter("trueName_like");
				String lendCoinType = filter.getRequest().getParameter("lendCoinType");
				String beginTime = filter.getRequest().getParameter("beginTime");
				String endTime = filter.getRequest().getParameter("endTime");
				
				Map<String,String> map = new HashMap<String,String>();
				if(!StringUtils.isEmpty(userName)){
					map.put("userName", "%"+userName+"%");
				}if(!StringUtils.isEmpty(trueName)){
					map.put("trueName", "%"+trueName+"%");
				}
				if(!StringUtils.isEmpty(lendCoinType)){
					map.put("lendCoinType", lendCoinType);
				}
				if(!StringUtils.isEmpty(beginTime)){
					map.put("beginTime", beginTime);
				}
				if(!StringUtils.isEmpty(endTime)){
					map.put("endTime", endTime);
				}
				
				ExDmLendIntentDao lendIntenDao = (ExDmLendIntentDao) dao;
				List<ExDmLendIntentAndCustomer> list = lendIntenDao.findPageBySql(map);
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
	
	
}
