/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年3月24日 下午1:45:20
 */
package hry.exchange.product.service;

import hry.bean.JsonResult;
import hry.core.mvc.service.base.BaseService;
import hry.exchange.product.model.ExProduct;

import java.util.List;

/**
 * 
 * <p>
 * TODO
 * </p>
 * 
 * @author: Wu Shuiming
 * @Date : 2016年3月24日 下午1:47:26
 */
public interface ExProductService extends BaseService<ExProduct, Long> {

	public boolean findByCoinCode(String c);
	
	public ExProduct findByCoinCode(String c, String sassId);
	
	public List<ExProduct> findByIssueState(Integer i, String saasId);
	// 发布产品
	public JsonResult publishProduct(Long[] ids, String language);
	// 退市产品
	public JsonResult delishProduct(Long[] ids);
	// 通过用户id 查询用户跟用户有关的所有 产品信息 
	public List<ExProduct> findProductByCustomerId(Long id);

	/**
	 * <p>初始化产品Code 到redis中</p>
	 * @author:         Liu Shilei
	 * @param:    
	 * @return: void 
	 * @Date :          2016年8月30日 下午6:28:14   
	 * @throws:
	 */
	public void initRedisCode();
	
	
	/**
	 * 
	 * <p> 包括查询所有在表里面的</p>
	 * @author:         Gao Mimi
	 * @param:    @param CoinCode
	 * @param:    @return
	 * @return: ExProduct 
	 * @Date :          2016年9月3日 下午12:54:51   
	 * @throws:
	 */
	public ExProduct findByallCoinCode(String CoinCode);

	/**
	 * 查询产品从缓存中获取 
	 * 
	 * @param coinCode
	 * @return
	 */
	public ExProduct findProductByRedis(String coinCode);

	public ExProduct updateProductToRedis(String coinCode);

	/**
	 * 检查币账户没有publicKey的 ，重新生成
	 * <p> TODO</p>
	 * @author:         Liu Shilei
	 * @param:    
	 * @return: void 
	 * @Date :          2016年10月18日 下午5:39:20   
	 * @throws:
	 */
	public String detection();

	/**
	 * 关闭某个币的交易
	 * i   1 表示闭盘   2 表示开盘
	 * @param id
	 * @return
	 */
	public JsonResult endProduct(Long id, Integer i);
	
	public Integer getkeepDecimalForRmb();

	

	
	public JsonResult setCoinStatus(Long id, Integer i);
	
	
}
