/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年7月4日 下午9:16:20
 */
package hry.calculate.mvc.dao;

import hry.calculate.mvc.po.AppStatisticalToIndexVo;
import hry.calculate.mvc.po.CalculatePo;
import hry.calculate.mvc.po.CustromerRegisterPo;
import hry.calculate.mvc.po.PendingOrders;
import hry.calculate.settlement.model.AppOrderDistributionVo;
import hry.core.mvc.dao.base.BaseDao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * <p> TODO</p>
 * @author:         Wu Shuiming
 * @Date :          2016年7月4日 下午9:16:20 
 */
public interface CalculateAppTransactionDao extends BaseDao<CalculatePo,Long> {
	
	/**
	 * 
	 * <p> TODO</p>
	 * @author:         Wu Shuiming
	 * @param:    @param status    表示审核状态( 1 表示待审核  ,2表示已审核 , 3表示已否决)
	 * @param:    @param style     表示充值 方式 (0 表示银行卡    1表示支付宝)
	 * @param:    @param transactionType1  这两参数用户控制查询充值提现等操作
	 * @param:    @param transactionType2
	 * @param:    @return
	 * @return: BigDecimal 
	 * @Date :          2016年7月5日 下午7:02:51   
	 * @throws:
	 */
	public PendingOrders selectAppTransactionOfRmb(); 			
	
	
	/**
	 * 提现订单统计
	 * @param status
	 * @param style
	 * @param transactionType1
	 * @param transactionType2
	 * @param type
	 * @return
	 */
	public PendingOrders selectAppTransactionOfRmbGet(); 			
	
	
	/**
	 * 
	 * 
	 * <p> TODO</p>
	 * @author:         Wu Shuiming
	 * @param:    @param dayAgo     表示 查询几天之前的数据
	 * @param:    @return
	 * @return: CalculatePo 
	 * @Date :          2016年7月5日 下午7:03:00   
	 * @throws:
	 */
	public List<CalculatePo> selectTransactionDayAgo(@Param(value = "dayAgo") Integer dayAgo);

	// 查询当日注册的用户  以及所有用户的数量  
	/**
	 * 查询前几天 共注册的用户数量  以及总共注册的人数
	 * <p> TODO</p>
	 * @author:         Wu Shuiming
	 * @param:    @param dayAgo
	 * @param:    @param isDelete
	 * @param:    @return
	 * @return: CustromerRegisterPo 
	 * @Date :          2016年7月5日 下午7:07:20   
	 * @throws:
	 */
	public CustromerRegisterPo selectCustromerNum(@Param(value = "dayAgo") Integer dayAgo, @Param(value = "isDelete") Integer isDelete);

	
	/**
	 * 
	 * 查询后台首页用户分布的数据图
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年9月12日 下午3:13:08
	 */
	public AppStatisticalToIndexVo findUserDistribution();
	
	
	/**
	 * 查询成交数据的分布图
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年9月13日 上午10:50:14
	 */
	public List<AppOrderDistributionVo> findOrderDistribution();

	/**
	 * 成交总额 
	 * 
	 * @return
	 */
	public Integer findTotalMoney(@Param(value = "website") String website);
	
	
}
