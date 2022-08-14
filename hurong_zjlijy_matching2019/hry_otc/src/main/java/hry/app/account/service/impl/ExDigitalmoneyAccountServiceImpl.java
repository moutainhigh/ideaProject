
package hry.app.account.service.impl;

import hry.app.account.dao.ExDigitalmoneyAccountDao;
import hry.app.account.service.ExDigitalmoneyAccountService;
import hry.core.constant.CodeConstant;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.otc.manage.remote.model.account.ExDigitalmoneyAccount;
import hry.util.DbcontextHolder;
import hry.util.QueryFilter;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.util.StringUtil;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      jidn
 * @version:      V1.0 
 * @Date:        2018年7月28日 下午6:12:49
 */
@Service("exDigitalmoneyAccountService")
public class ExDigitalmoneyAccountServiceImpl extends
		BaseServiceImpl<ExDigitalmoneyAccount, Long> implements
		ExDigitalmoneyAccountService {
	private final Logger log = Logger.getLogger(ExDigitalmoneyAccountServiceImpl.class);
	

	@Resource(name = "exDigitalmoneyAccountDao")
	@Override
	public void setDao(BaseDao<ExDigitalmoneyAccount, Long> dao) {
		super.dao = dao;
	}
	
	@Override
	public String[] updateAccount(ExDigitalmoneyAccount account){
		String[] relt=new String[2];  
		//try{
		long start1 = System.currentTimeMillis();
		  this.update(account);
			long end1 = System.currentTimeMillis();
			log.info("更新账户e：" + (end1 - start1) + "毫秒");
		  relt[0]=CodeConstant.CODE_SUCCESS;
			relt[1]="成功";
		/*}catch(Exception e){
			
			relt[0]=CodeConstant.CODE_FAILED;
			relt[1]="失败";
		}*/
		
		return relt;
		
		
		
	}
	@Override
	public ExDigitalmoneyAccount getByCustomerIdAndType(Long customerId,String coinCode,String currencyType,String website) {
		QueryFilter filter = new QueryFilter(ExDigitalmoneyAccount.class);
		filter.addFilter("customerId=", customerId);
		if(!StringUtil.isEmpty(currencyType)){
			filter.addFilter("currencyType=", currencyType);
		}
		if(!StringUtil.isEmpty(website)){
			filter.addFilter("website=", website);
		}
		filter.addFilter("coinCode=", coinCode);
		return this.get(filter);
	}
	
	@Override
	public List<ExDigitalmoneyAccount> findBonusUserList(Map<String, Object> map) {
		String coinCode =(String) map.get("coinCode");
		String pageSize =String.valueOf(map.get("pageSize"));
		String currentPage =String.valueOf(map.get("currentPage"));
		StringBuilder sql = new StringBuilder("SELECT eda.hotMoney, eda.customerId, eda.coinCode,api.cardId FROM ex_digitalmoney_account eda");
		sql.append(" JOIN app_person_info api ON api.customerId = eda.customerId");
		if(!StringUtils.isEmpty(coinCode)){
			sql.append(" and eda.coinCode='"+coinCode+"'");
		}
		if(!StringUtils.isEmpty(pageSize) && !"null".equals(pageSize)){
			sql.append(" LIMIT "+currentPage+","+pageSize+"");
		}
		try {
			return DbcontextHolder.jdbcQueryAccountList(sql.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<ExDigitalmoneyAccount> findMiningOrderList(
			Map<String, Object> PageMap) {
		return ((ExDigitalmoneyAccountDao)dao).findMiningOrderList(PageMap);
	}

	@Override
	public Integer findMiningOrderListcount(){
		StringBuilder sql = new StringBuilder("SELECT count(*) FROM ex_digitalmoney_account eda");
		try {
			return DbcontextHolder.jdbcQueryInteger(sql.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public int findBonusUserListCount(Map<String, Object> map) {
		String coinCode = (String) map.get("coinCode");
		StringBuilder sql = new StringBuilder("SELECT count(*) FROM ex_digitalmoney_account eda where ");
		if(!StringUtils.isEmpty(coinCode)){
			sql.append(" eda.coinCode = '"+coinCode+"'");
		}
		try {
			return DbcontextHolder.jdbcQueryInteger(sql.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	@Override
	public List<ExDigitalmoneyAccount> findCustomerCoin(Map<String, Object> PageMap) {
		String coinCode =(String) PageMap.get("coinCode");
		String pageSize =String.valueOf(PageMap.get("pageSize"));
		String currentPage =String.valueOf(PageMap.get("currentPage"));
		StringBuilder sql = new StringBuilder("SELECT eda.id,eda.customerId, eda.coinCode FROM ex_digitalmoney_account eda WHERE eda.customerId IN (");
		sql.append(" SELECT eda.customerId FROM ex_digitalmoney_account eda");
		if(!StringUtils.isEmpty(coinCode)){
			sql.append(" where eda.coinCode ='"+coinCode+"' AND eda.hotMoney > 0");
		}
		sql.append(") GROUP BY  eda.customerId, eda.coinCode");
		
		if(!StringUtils.isEmpty(pageSize) && !"null".equals(pageSize)){
			sql.append(" LIMIT "+currentPage+","+pageSize+"");
		}
		try {
			return DbcontextHolder.jdbcQueryAccountList(sql.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Integer findCustomerCoinCount(Map<String, Object> PageMap) {
		String coinCode = (String) PageMap.get("coinCode");
		StringBuilder sql = new StringBuilder("SELECT count(*) FROM ( SELECT eda.customerId, eda.coinCode FROM ex_digitalmoney_account eda WHERE eda.customerId in ( SELECT eda.customerId FROM ex_digitalmoney_account eda ");
		if(!StringUtils.isEmpty(coinCode)){
			sql.append(" where eda.coinCode = '"+coinCode+"'");
		}
		sql.append(" AND eda.hotMoney > 0 )  GROUP BY  eda.customerId, eda.coinCode ) emb");
		try {
			return DbcontextHolder.jdbcQueryInteger(sql.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
