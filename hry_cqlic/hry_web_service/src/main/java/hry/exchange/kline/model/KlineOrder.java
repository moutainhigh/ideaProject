/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0 
 * @Date:        2016年5月19日 下午1:43:09
 */
package hry.exchange.kline.model;

import java.util.ArrayList;
import java.util.List;


/**
 * <p> TODO</p>
 * @author:         Liu Shilei 
 * @Date :          2016年5月19日 下午1:43:09 
 */
public class KlineOrder {
	
	private String des = "";
	
	private boolean isSuc = true;
	
	private List<KlineOrderData>  datas;

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getDes() {
		return des;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setDes(String des) {
		this.des = des;
	}

	

	/**
	 * <p> TODO</p>
	 * @return:     boolean
	 */
	public boolean getisSuc() {
		return isSuc;
	}

	/** 
	 * <p> TODO</p>
	 * @return: boolean
	 */
	public void setSuc(boolean isSuc) {
		this.isSuc = isSuc;
	}

	/**
	 * <p> TODO</p>
	 * @return:     List<KlineOrderData>
	 */
	public List<KlineOrderData> getDatas() {
		return datas;
	}

	/** 
	 * <p> TODO</p>
	 * @return: List<KlineOrderData>
	 */
	public void setDatas(List<KlineOrderData> datas) {
		this.datas = datas;
	}

	
	
	
}
