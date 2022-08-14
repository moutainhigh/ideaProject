/**
 * Copyright:   
 * @author:      kongdebiao
 * @version:     V1.0 
 * @Date:        2018-11-16 10:45:45 
 */
package hry.mall.goods.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.*;

/**
 * <p> GoodsSpec </p>
 * @author:         kongdebiao
 * @Date :          2018-11-16 10:45:45  
 */
@Table(name="shop_goods_spec")
public class GoodsSpec extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //商品规格索引id
	
	@Column(name= "goodsId")
	private Long goodsId;  //商品id
	
	@Column(name= "specName")
	private String specName;  //规格名称
	
	@Column(name= "specNameValue")
	private String specNameValue;  //规格值
	
	@Column(name= "specGoodsPrice")
	private BigDecimal specGoodsPrice;  //规格商品价格
	
	@Column(name= "specGoodsStorage")
	private Integer specGoodsStorage;  //规格商品库存
	
	@Column(name= "specGoodsSerial")
	private String specGoodsSerial;  //规格商品虚列号
	
	@Column(name= "specIsOpen")
	private Integer specIsOpen;  //是否开启规格,1:开启，0:关闭
	
	@Column(name= "specStorageWarning")
	private Integer specStorageWarning;  //库存预警值
	
	@Column(name= "integralCount")
	private BigDecimal integralCount;  //积分兑换数量
	
	@Transient
	private List<String> specNameList; //规格名list

	@Transient
	private List<String> specNameValueList;//规格值list
    
	public BigDecimal getIntegralCount() {
		return integralCount;
	}

	public void setIntegralCount(BigDecimal integralCount) {
		this.integralCount = integralCount;
	}

	public List<String> getSpecNameList() {
		return specNameList;
	}

	public void setSpecNameList(List<String> specNameList) {
		this.specNameList = specNameList;
	}

	public List<String> getSpecNameValueList() {
		return specNameValueList;
	}

	public void setSpecNameValueList(List<String> specNameValueList) {
		this.specNameValueList = specNameValueList;
	}

	/**
	 * <p>商品规格索引id</p>
	 * @author:  kongdebiao
	 * @return:  Long 
	 * @Date :   2018-11-16 10:45:45    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>商品规格索引id</p>
	 * @author:  kongdebiao
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-11-16 10:45:45   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>商品id</p>
	 * @author:  kongdebiao
	 * @return:  Long 
	 * @Date :   2018-11-16 10:45:45    
	 */
	public Long getGoodsId() {
		return goodsId;
	}
	
	/**
	 * <p>商品id</p>
	 * @author:  kongdebiao
	 * @param:   @param goodsId
	 * @return:  void 
	 * @Date :   2018-11-16 10:45:45   
	 */
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}
	
	
	/**
	 * <p>规格名称</p>
	 * @author:  kongdebiao
	 * @return:  String 
	 * @Date :   2018-11-16 10:45:45    
	 */
	public String getSpecName() {
		return specName;
	}
	
	/**
	 * <p>规格名称</p>
	 * @author:  kongdebiao
	 * @param:   @param specName
	 * @return:  void 
	 * @Date :   2018-11-16 10:45:45   
	 */
	public void setSpecName(String specName) {
		this.specName = specName;
	}
	
	
	/**
	 * <p>规格值</p>
	 * @author:  kongdebiao
	 * @return:  String 
	 * @Date :   2018-11-16 10:45:45    
	 */
	public String getSpecNameValue() {
		return specNameValue;
	}
	
	/**
	 * <p>规格值</p>
	 * @author:  kongdebiao
	 * @param:   @param specNameValue
	 * @return:  void 
	 * @Date :   2018-11-16 10:45:45   
	 */
	public void setSpecNameValue(String specNameValue) {
		this.specNameValue = specNameValue;
	}
	
	
	/**
	 * <p>规格商品价格</p>
	 * @author:  kongdebiao
	 * @return:  BigDecimal 
	 * @Date :   2018-11-16 10:45:45    
	 */
	public BigDecimal getSpecGoodsPrice() {
		return specGoodsPrice;
	}
	
	/**
	 * <p>规格商品价格</p>
	 * @author:  kongdebiao
	 * @param:   @param specGoodsPrice
	 * @return:  void 
	 * @Date :   2018-11-16 10:45:45   
	 */
	public void setSpecGoodsPrice(BigDecimal specGoodsPrice) {
		this.specGoodsPrice = specGoodsPrice;
	}
	
	
	/**
	 * <p>规格商品库存</p>
	 * @author:  kongdebiao
	 * @return:  Integer 
	 * @Date :   2018-11-16 10:45:45    
	 */
	public Integer getSpecGoodsStorage() {
		return specGoodsStorage;
	}
	
	/**
	 * <p>规格商品库存</p>
	 * @author:  kongdebiao
	 * @param:   @param specGoodsStorage
	 * @return:  void 
	 * @Date :   2018-11-16 10:45:45   
	 */
	public void setSpecGoodsStorage(Integer specGoodsStorage) {
		this.specGoodsStorage = specGoodsStorage;
	}
	
	
	/**
	 * <p>规格商品虚列号</p>
	 * @author:  kongdebiao
	 * @return:  String 
	 * @Date :   2018-11-16 10:45:45    
	 */
	public String getSpecGoodsSerial() {
		return specGoodsSerial;
	}
	
	/**
	 * <p>规格商品虚列号</p>
	 * @author:  kongdebiao
	 * @param:   @param specGoodsSerial
	 * @return:  void 
	 * @Date :   2018-11-16 10:45:45   
	 */
	public void setSpecGoodsSerial(String specGoodsSerial) {
		this.specGoodsSerial = specGoodsSerial;
	}
	
	
	/**
	 * <p>是否开启规格,1:开启，0:关闭</p>
	 * @author:  kongdebiao
	 * @return:  Integer 
	 * @Date :   2018-11-16 10:45:45    
	 */
	public Integer getSpecIsOpen() {
		return specIsOpen;
	}
	
	/**
	 * <p>是否开启规格,1:开启，0:关闭</p>
	 * @author:  kongdebiao
	 * @param:   @param specIsOpen
	 * @return:  void 
	 * @Date :   2018-11-16 10:45:45   
	 */
	public void setSpecIsOpen(Integer specIsOpen) {
		this.specIsOpen = specIsOpen;
	}
	
	
	/**
	 * <p>库存预警值</p>
	 * @author:  kongdebiao
	 * @return:  Integer 
	 * @Date :   2018-11-16 10:45:45    
	 */
	public Integer getSpecStorageWarning() {
		return specStorageWarning;
	}
	
	/**
	 * <p>库存预警值</p>
	 * @author:  kongdebiao
	 * @param:   @param specStorageWarning
	 * @return:  void 
	 * @Date :   2018-11-16 10:45:45   
	 */
	public void setSpecStorageWarning(Integer specStorageWarning) {
		this.specStorageWarning = specStorageWarning;
	}
	
	

}
