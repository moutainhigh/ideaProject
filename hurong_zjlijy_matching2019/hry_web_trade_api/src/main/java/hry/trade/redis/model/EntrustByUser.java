package hry.trade.redis.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntrustByUser implements Serializable{

	private Long customerId;
	private Map<String, List<EntrustTrade>> entrustedmap = new HashMap<String, List<EntrustTrade>>();
	private Map<String, List<EntrustTrade>> entrustingmap = new HashMap<String, List<EntrustTrade>>();
	public static final Integer ingMAXsize = 100; // 最大条数
	public static final Integer edMAXsize = 10; // 最大条数
	public void addEntrust(EntrustTrade entrust) {
		if(null!=entrust.getIsType()&&entrust.getIsType()==1){ //如果是杠杠的单子直接忽略由杠杆那么做缓存
			return;
		}
		//没匹配，匹配一半，匹配完成，撤销
		String key = entrust.getCoinCode() + "_" + entrust.getFixPriceCoinCode();
		List<EntrustTrade> entrustinglist = entrustingmap.get(key);
		if(null!=entrustinglist&&entrustinglist.size()>0){
			for(EntrustTrade entrustTrade:entrustinglist){
				if(entrustTrade.getEntrustNum().equals(entrust.getEntrustNum())){
					entrustinglist.remove(entrustTrade);
					break;
				}
			}
		}
		if(entrust.getStatus().compareTo(1)==1) {//已经完成或撤销
			List<EntrustTrade> entrustedlist = entrustedmap.get(key);
			if (null == entrustedlist) {
				entrustedlist = new ArrayList<EntrustTrade>();
			}
			if (entrustedlist.size() == edMAXsize) {
				entrustedlist.remove(0);
			}
			entrustedlist.add(entrust);
			entrustedmap.put(key, entrustedlist);
			
		} else  {
			if (null == entrustinglist) {
				entrustinglist = new ArrayList<EntrustTrade>();
			}
			if (entrustinglist.size() == ingMAXsize) {
				entrustinglist.remove(0);
			}
			entrustinglist.add(entrust);
			entrustingmap.put(key, entrustinglist);
		}

	}

	public void addEntrust1(EntrustTrade entrust) { // 没匹配，匹配一半，匹配完成，撤销
		String key = entrust.getCoinCode() + "_" + entrust.getFixPriceCoinCode();
		if (entrust.getStatus().equals(2)) {
			List<EntrustTrade> entrustedlist = entrustedmap.get(key);
			if (null == entrustedlist) {
				entrustedlist = new ArrayList<EntrustTrade>();
			}
			if (entrustedlist.size() == 5) {
				entrustedlist.remove(0);
			}
			entrustedlist.add(entrust);
			entrustedmap.put(key, entrustedlist);

		} else if (entrust.getStatus().equals(0) || entrust.getStatus().equals(1)) {
			List<EntrustTrade> entrustinglist = entrustingmap.get(key);
			if (null == entrustinglist) {
				entrustinglist = new ArrayList<EntrustTrade>();
			}
			if (entrustinglist.size() == 5) {
				entrustinglist.remove(0);
			}
			entrustinglist.add(entrust);
			entrustedmap.put(key, entrustinglist);
		} else { // 撤销

			List<EntrustTrade> entrustinglist = entrustingmap.get(key);
			if (null == entrustinglist) {
				entrustinglist = new ArrayList<EntrustTrade>();
			} else {
				entrustinglist.remove(entrust);
				entrustedmap.put(key, entrustinglist);
			}

		}

	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Map<String, List<EntrustTrade>> getEntrustedmap() {
		return entrustedmap;
	}

	public void setEntrustedmap(Map<String, List<EntrustTrade>> entrustedmap) {
		this.entrustedmap = entrustedmap;
	}

	public Map<String, List<EntrustTrade>> getEntrustingmap() {
		return entrustingmap;
	}

	public void setEntrustingmap(Map<String, List<EntrustTrade>> entrustingmap) {
		this.entrustingmap = entrustingmap;
	}

}
