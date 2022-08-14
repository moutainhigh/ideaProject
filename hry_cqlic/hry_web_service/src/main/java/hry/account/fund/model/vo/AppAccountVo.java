package hry.account.fund.model.vo;

import java.io.Serializable;
import java.util.List;

import hry.account.fund.model.AppAccount;
import hry.exchange.account.model.ExDigitalmoneyAccount;

public class AppAccountVo implements Serializable {
	
	private AppAccount appAccount;
	
	private List<ExDigitalmoneyAccount> list;

	public AppAccount getAppAccount() {
		return appAccount;
	}

	public void setAppAccount(AppAccount appAccount) {
		this.appAccount = appAccount;
	}

	public List<ExDigitalmoneyAccount> getList() {
		return list;
	}

	public void setList(List<ExDigitalmoneyAccount> list) {
		this.list = list;
	}
	

}
