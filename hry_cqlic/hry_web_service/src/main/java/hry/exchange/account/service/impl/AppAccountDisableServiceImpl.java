/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Gao mimi
 * @version:     V1.0 
 * @Date:        2016-12-12 19:39:38 
 */
package hry.exchange.account.service.impl;

import java.math.BigDecimal;

import hry.exchange.account.model.AppAccountDisable;
import hry.exchange.account.model.ExDigitalmoneyAccount;
import hry.exchange.account.service.AppAccountDisableService;
import hry.exchange.account.service.ExDigitalmoneyAccountService;
import hry.core.constant.CodeConstant;
import hry.core.mvc.dao.base.BaseDao;
import hry.bean.JsonResult;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.util.idgenerate.IdGenerate;
import hry.util.idgenerate.NumConstant;
import hry.util.sys.ContextUtil;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p> AppAccountDisableService </p>
 * @author:         Gao mimi
 * @Date :          2016-12-12 19:39:38  
 */
@Service("appAccountDisableService")
public class AppAccountDisableServiceImpl extends BaseServiceImpl<AppAccountDisable, Long> implements AppAccountDisableService{
	
	@Resource(name="appAccountDisableDao")
	@Override
	public void setDao(BaseDao<AppAccountDisable, Long> dao) {
		super.dao = dao;
	}
	@Resource
	public ExDigitalmoneyAccountService exDigitalmoneyAccountService;
	@Override
	public JsonResult coindisable(String transactionCount, String id,String remark) {
		JsonResult jr = new JsonResult();
		ExDigitalmoneyAccount account=exDigitalmoneyAccountService.get(Long.valueOf(id));
		
		if(account.getHotMoney().compareTo(new BigDecimal(transactionCount))>=0){
			AppAccountDisable  appAccountDisable=new AppAccountDisable();
			appAccountDisable.setAccountId(Long.valueOf(id));
			appAccountDisable.setCoinCode(account.getCoinCode());
			appAccountDisable.setCurrencyType(account.getCurrencyType());
			appAccountDisable.setCustomerId(account.getCustomerId());
			appAccountDisable.setRemark(remark);
			appAccountDisable.setStatus(1);
			appAccountDisable.setTransactionCount(new BigDecimal(transactionCount));
			appAccountDisable.setTransactionType(1);
			appAccountDisable.setTransactionNum(IdGenerate.transactionNum(NumConstant.Ex_Dm_Transaction));
			appAccountDisable.setUserName(account.getUserName());
			appAccountDisable.setUserId(ContextUtil.getCurrentUserId());
			appAccountDisable.setWebsite(account.getWebsite());
			this.save(appAccountDisable);
			exDigitalmoneyAccountService.freezeAccountSelf(account.getId(), appAccountDisable.getTransactionCount(), appAccountDisable.getTransactionNum(), "禁用",null,null);
			jr.setMsg("禁用成功");
			jr.setSuccess(true);
		}else{
			jr.setMsg("禁用币数量不能超过可用币数量");
			jr.setSuccess(false);
		}
	    return jr;
		
	}
	@Override
	public JsonResult encoindisable(String ids) {
		JsonResult jr = new JsonResult();
		if(!StringUtils.isEmpty(ids)){
			String[] split = org.apache.commons.lang3.StringUtils.split(ids, ",");
			for(String id  : split){
				AppAccountDisable appAccountDisable=this.get(Long.valueOf(id));
				if(appAccountDisable.getStatus().equals(2)){
					jr.setMsg("已经解禁过");
					jr.setSuccess(false);
					return jr;
				}
			
				
				String[] relt=exDigitalmoneyAccountService.unfreezeAccountSelf(appAccountDisable.getAccountId(), appAccountDisable.getTransactionCount(), appAccountDisable.getTransactionNum(), "解禁",null,null);
			
			   if(relt[0].equals(CodeConstant.CODE_FAILED)){
				   
				    jr.setMsg("账户异常");
					jr.setSuccess(false);
					return jr;
					
			   }else{
				   appAccountDisable.setStatus(2);
					this.update(appAccountDisable);
			   }
			}
			jr.setMsg("解禁成功");
			jr.setSuccess(true);
		}else{
			jr.setMsg("解禁失败");
			jr.setSuccess(false);
		}
	
		return jr;
	
	}
	

}
