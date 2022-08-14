package hry.account.remote;

import javax.annotation.Resource;

import hry.account.fund.model.AppUserCoin;
import hry.account.fund.service.AppUserCoinService;
import hry.util.QueryFilter;
import hry.util.serialize.ObjectUtil;
import hry.manage.remote.RemoteCoinSecurityService;
import hry.manage.remote.model.AppUserCoinRemote;
import hry.manage.remote.model.RemoteResult;

/**
 * RemoteCoinSecurityServiceImpl.java
 * @author denghf
 * 2018年5月10日 下午7:49:46
 */
public class RemoteCoinSecurityServiceImpl implements RemoteCoinSecurityService{

	@Resource
	private AppUserCoinService appUserCoinService;
	
	@Override
	public RemoteResult loginCoinSecurity(String userName, String passWord){
		RemoteResult r = new RemoteResult();
		
		AppUserCoin appUserCoin = appUserCoinService.get(new QueryFilter(AppUserCoin.class).addFilter("userName=", userName).addFilter("passWord=", passWord));
		if(appUserCoin!=null){
			AppUserCoinRemote appUserCoinRemote = ObjectUtil.bean2bean(appUserCoin, AppUserCoinRemote.class);
			
			r.setSuccess(true);
			r.setObj(appUserCoinRemote);
			return r;
		}
		r.setSuccess(false);
		r.setMsg("用户名或密码错误");
		return r;
	}
}
