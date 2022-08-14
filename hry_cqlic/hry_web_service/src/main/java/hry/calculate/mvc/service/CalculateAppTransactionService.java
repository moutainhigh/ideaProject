/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年7月5日 上午10:40:21
 */
package hry.calculate.mvc.service;

import hry.calculate.mvc.po.AppStatisticalToIndexVo;
import hry.calculate.mvc.po.CalculatePo;
import hry.calculate.mvc.po.CustromerRegisterPo;
import hry.calculate.mvc.po.PendingOrders;
import hry.calculate.settlement.model.AppOrderDistributionVo;

import java.math.BigDecimal;
import java.util.List;

import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * <p> TODO</p>
 * @author:         Wu Shuiming
 * @Date :          2016年7月5日 上午10:40:21 
 */
public interface CalculateAppTransactionService {
	
	/**
	 * 
	 * 查询充值提现订单未处理的金额
	 * 
	 * <p> TODO</p>
	 * @author:         Wu Shuiming
	 * @param:    @param status
	 * @param:    @param style
	 * @param:    @param transactionType1
	 * @param:    @param transactionType2
	 * @param:    @return
	 * @return: BigDecimal 
	 * @Date :          2016年7月5日 下午5:19:14   
	 * @throws:
	 */
	public PendingOrders selectMoneyByFactor(String type);

	/**
	 * 
	 * 查询当天所有产品的交易量  参数为 int型  表示几天之前 。
	 * 
	 * <p> TODO</p>
	 * @author:         Wu Shuiming
	 * @param:    @param i
	 * @param:    @return
	 * @return: calculatePo 
	 * @Date :          2016年7月5日 下午5:22:38   
	 * @throws:
	 */
	public List<CalculatePo> selectMoneyByOrder(Integer i);
	
	/**
	 * 
	 * <p> TODO</p>
	 * @author:         Wu Shuiming
	 * @param:    @param dayAgo  前几天到现在
	 * @param:    @param isDelete  是否删除
	 * @param:    @return
	 * @return: CustromerRegisterPo 
	 * @Date :          2016年7月5日 下午7:14:58   
	 * @throws:
	 */
	public CustromerRegisterPo selectCustromerByTime(Integer dayAgo, Integer isDelete);
	
	
	/**
	 * 查询首页用户的分布图数据
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年9月12日 下午3:14:48
	 */
	public AppStatisticalToIndexVo findUserDistribution();
	
	
	/**
	 * 查询成交单的分布图
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年9月13日 上午10:54:29
	 */
	public List<AppOrderDistributionVo> findOrderDistribution();

	/**
	 * 
	 * 查询历史的成交总额  
	 * 
	 * @return
	 */
	public Integer findTotalMoney(String website);
	

}
