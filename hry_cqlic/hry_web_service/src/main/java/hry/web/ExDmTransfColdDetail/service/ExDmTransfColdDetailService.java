/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      shangxl
 * @version:     V1.0 
 * @Date:        2017-11-13 19:17:02 
 */
package hry.web.ExDmTransfColdDetail.service;

import java.util.Map;

import hry.core.mvc.service.base.BaseService;
import hry.manage.remote.model.ExDmTransfColdDetailManage;
import hry.manage.remote.model.RemoteResult;
import hry.manage.remote.model.base.FrontPage;
import hry.web.ExDmTransfColdDetail.model.ExDmTransfColdDetail;



/**
 * <p> ExDmTransfColdDetailService </p>
 * @author:         shangxl
 * @Date :          2017-11-13 19:17:02  
 */
public interface ExDmTransfColdDetailService  extends BaseService<ExDmTransfColdDetail, Long>{

	/**
	 * 查询代币资产
	 * @param params
	 * @return
	 */
	public FrontPage listTokenAssets(Map<String, String> params);

	/**
	 * 充值旷工费
	 * @param params
	 * @return
	 */
	RemoteResult rechargeTxFee2TokenAddress(Map<String, String> params);

	/**
	 * 代币归集
	 * @param params
	 * @return
	 */
	RemoteResult tokenCollect(Map<String, String> params);

	/**
	 * 转入线下
	 * @param params
	 * @return
	 */
	RemoteResult toColdAccount(Map<String, String> params);

	/**
	 * 转出冷钱包记录列表查询
	 * @param params
	 * @return
	 */
	public FrontPage transfColdRecordList(Map<String, String> params);

	/**
	 * 根据id查询转出冷钱包记录
	 * @param id
	 * @return
	 */
	public ExDmTransfColdDetailManage transfColdDetail(Long id);

	/**
	 * 查询钱包余额列表
	 * @param params
	 * @return
	 */
	public FrontPage listWalletBalance(Map<String, String> params); 

}
