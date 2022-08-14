package hry.remote.service;

import hry.bean.FrontPage;
import hry.remote.model.*;

import java.util.List;
import java.util.Map;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2019/4/2 19:17
 * @Description: 理财
 */
public interface RemoteFinancailService {


    /**
     * 前台查询用户红包
     * @param paramMap
     * @return
     */
    public FrontPage findFrontPageUserRedAccountList(Map<String,String> paramMap);

    /**
     * 前台查询投资的理财
     * @param paramMap
     * @return
     */
    public FrontPage findFrontPageUserFinancalList(Map<String,String> paramMap);


    /**
     * 查询所有理财产品
     * @param paramMap
     * @return
     */
    public List findFronTPageFinancalList(Map<String,String> paramMap);

    /**
     * 理财大厅查询理财产品
     * @param paramMap
     * @return
     */
    public List<AppFinancialProducts> findFinaningProductList(Map<String,String> paramMap);

    /**
     * 根据条件查询理财产品
     * @param paramMap
     * @return
     */
    public AppFinancialProducts findAppFinancialProductByMap(Map<String,String> paramMap);

    /**
     * 创建理财订单
     * @param paramMap
     * @return
     */
    public RemoteResult createFinancialOrder(Map<String,String> paramMap);

    /**
     * 用户一键领取 红包
     * @param customerId
     * @param coinCodes
     * @return
     */
    public RemoteResult receiveAllRedAccount(String customerId,String[] coinCodes);

    /**
     * 赎回理财产品
     * @param productId
     * @param customerId
     * @return
     */
    public RemoteResult cancelFinancialOrder(String productId,String customerId);

    /**
     * 获取用户红包
     * @param customerId
     * @param coinCode
     * @return
     */
    public RemoteResult getUserRedAccount(String customerId,String coinCode);

    /**
     * 获得已投资人数
     * @param pMap
     * @return
     */
    public Integer findInvestmentedPersonCount(Map<String, Object> pMap);

    /**
     * 查询理财推荐人列表
     * @param pMap
     * @return
     */
    public FrontPage findFinancialRecommendList(Map<String, String> pMap);

    /**
     * 查询理财推荐人列表
     * @param pMap
     * @return
     */
    public List<AppFinancialRecommendTransaction> findFinancialRecommendTransactionList(Map<String, String> pMap);

    /**
     * 根据用户
     * @param customerId
     * @return
     */
    public AppFinancialRecommend findOne(Long customerId);

    /**
     * 根据customerId查找团队总业绩
     * @param customerId
     * @return
     */
    public List<AppFinancialRecommendVo> findTeamFinancialList(Long customerId);

    /**
     * 超越 查询理财产品
     * @param paramMap
     * @return
     */
    public AppFinancialProducts findAppFinancialProductByMap_CY(Map<String,String> paramMap);

    /**
     * todo 超越 理财增加推荐关系
     * @param customerId
     * @param code
     * @return
     */
    public RemoteResult addFinancialRecommendShip(Long customerId,String code);

    /**
     *
     * @param customerId
     * @return
     */
    public int findShipCount(Long customerId);

}
