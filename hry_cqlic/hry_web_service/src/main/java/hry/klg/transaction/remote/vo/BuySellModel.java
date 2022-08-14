package hry.klg.transaction.remote.vo;

import java.util.ArrayList;
import java.util.List;

import hry.klg.buysellaccount.model.KlgBuySellAccount;
import hry.trade.redis.model.Accountadd;

public class BuySellModel {
	/**发送币账户消息*/
	private List<Accountadd> list = new ArrayList<Accountadd>();
	/**买单支付剩余账户*/
	private KlgBuySellAccount klgBuyAccount;
	/**卖单卖出剩余*/
	private KlgBuySellAccount klgSellAccount;
	public List<Accountadd> getList() {
		return list;
	}
	public void setList(List<Accountadd> list) {
		this.list = list;
	}
	public KlgBuySellAccount getKlgBuyAccount() {
		return klgBuyAccount;
	}
	public void setKlgBuyAccount(KlgBuySellAccount klgBuyAccount) {
		this.klgBuyAccount = klgBuyAccount;
	}
	public KlgBuySellAccount getKlgSellAccount() {
		return klgSellAccount;
	}
	public void setKlgSellAccount(KlgBuySellAccount klgSellAccount) {
		this.klgSellAccount = klgSellAccount;
	}
	
	
}
