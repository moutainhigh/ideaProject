package hry.admin.klg.transaction.service;

import java.util.List;
import java.util.Map;

import hry.admin.klg.transaction.model.KlgBuyTransaction;
import hry.admin.klg.transaction.model.vo.KlgBuyTransactionVo;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;

public interface KlgBuySellMatchingService extends BaseService<KlgBuyTransaction, Long>{
	
	/**
     * 新sql分页查询
     * @param map
     * @return
     */
	PageResult pipeiSelectPage(QueryFilter filter);
	
	/**
	 * 查询买单买单上方信息
	 * @return
	 */
	JsonResult selectBuySellData();
	
	Map<String ,Object> selectPipeiData(QueryFilter filter,String ids);
	
	/**
	 * 买单匹配弹框确认
	 * @return
	 */
	JsonResult peipeiSubmit(String ids,QueryFilter filter);
	/**
	 * 买单匹配弹框确认
	 * @return
	 */
	JsonResult buyAlonePipeiSubmit(String ids,QueryFilter filter);
	/**
	 * 卖单匹配弹框确认
	 * @return
	 */
	JsonResult sellAlonePipeiSubmit(String ids,QueryFilter filter);
	/**
	 * 查询and计算调控数量
	 * @return
	 */
	Map<String ,Object>  selectTiaokongData();
	
	/**
	 * 调控弹框确认
	 * @return
	 */
	JsonResult tiaokongSubmit(String tiaokongSme,String tiaokongUsdt,QueryFilter filter);
	
	/**
	 * 吃单确认
	 * @return
	 */
	JsonResult chidanSubmit(String ids);
	
	/**
	 * 卖单
	 * @return
	 */
	Map<String ,Object> selectPipeiSellData(QueryFilter filter,String ids);
	
	/**
     * 卖单匹配新sql分页查询
     * @param map
     * @return
     */
	PageResult pipeiSellSelectPage(QueryFilter filter);
	
	/**
	 * 卖单单匹配弹框确认
	 * @return
	 */
	JsonResult peipeiSellSubmit(String ids,QueryFilter filter);
	
	/**
	 * 吃卖单确认
	 * @return
	 */
	JsonResult chidanSellSubmit(String ids);
	
	/**
	 * 剩余转出弹框确认
	 * @return
	 */
	JsonResult outSurplusSubmit(String outSme,String outUsdt,QueryFilter filter);
	
}
