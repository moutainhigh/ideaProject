/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      denghf
 * @version:     V1.0
 * @Date:        2018-06-25 18:06:52
 */
package hry.app.otc.releaseAdvertisement.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.util.StringUtil;
import hry.app.otc.releaseAdvertisement.dao.OtcAppTransactionDao;
import hry.app.otc.releaseAdvertisement.service.AppAppealService;
import hry.app.otc.releaseAdvertisement.service.OtcAppTransactionService;
import hry.bean.FrontPage;
import hry.bean.ObjectUtil;
import hry.bean.PageResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.otc.manage.remote.model.releaseAdvertisement.AppAppeal;
import hry.otc.manage.remote.model.releaseAdvertisement.OtcAppTransaction;
import hry.otc.remote.model.OtcAppTransactionRemote;
import hry.util.PageFactory;
import hry.util.QueryFilter;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p> OtcAppTransactionService </p>
 * @author:         denghf
 * @Date :          2018-06-25 18:06:52
 */
@Service("otcAppTransactionService")
public class OtcAppTransactionServiceImpl extends BaseServiceImpl<OtcAppTransaction, Long> implements OtcAppTransactionService{

	@Resource(name="otcAppTransactionDao")
	@Override
	public void setDao(BaseDao<OtcAppTransaction, Long> dao) {
		super.dao = dao;
	}
    @Resource
    private AppAppealService appAppealService;

	@Override
	public BigDecimal adUserBy30(Long id,String coinCode){
		return ((OtcAppTransactionDao)dao).adUserBy30(id,coinCode);
	}

	@Override
	public BigDecimal adUserAll(Long id,String coinCode){
		return ((OtcAppTransactionDao)dao).adUserAll(id, coinCode);
	}

	@Override
	public Integer allTradeCount(Map<String, Object> map) {
		return ((OtcAppTransactionDao)dao).allTradeCount(map);
	}

	@Override
	public PageResult findPageBySql(QueryFilter filter, Integer type) {


		//----------------------分页查询头部外壳------------------------------
		//创建PageResult对象
		PageResult pageResult = new PageResult();
		Page<OtcAppTransaction> page = null;
		if(filter.getPageSize().compareTo(Integer.valueOf(-1))==0){
			//pageSize = -1 时
			//pageHelper传pageSize参数传0查询全部
			page= PageHelper.startPage(filter.getPage(), 0);
		}else{
			page = PageHelper.startPage(filter.getPage(), filter.getPageSize());
		}
		//----------------------分页查询头部外壳------------------------------

		//----------------------查询开始------------------------------


		Map<String,Object> map = new HashMap<String,Object>();
		Integer[] status= null;
		if( type == 2){
			//进行中交易查询
			status=new Integer[]{1,2,3};
		}else if( type==3 ){
			//已完成交易查询
			status=new Integer[]{14};
		}else if( type==4 ){
			//已取消交易查询
			status=new Integer[]{5};
		}else if( type == 5){
			//申诉中交易查询
			status=new Integer[]{4,6,9,10,15,16};
		}
		map.put("status",status);
		String buyEmail = filter.getRequest().getParameter("buyEmail");
		String buyMobilePhone = filter.getRequest().getParameter("buyMobilePhone");
		if(! StringUtils.isEmpty( buyEmail ) ){
			map.put("buyEmail",buyEmail);
		}
		if(! StringUtils.isEmpty( buyMobilePhone ) ){
			map.put("buyMobilePhone",buyMobilePhone);
		}
		String sellEmail = filter.getRequest().getParameter("sellEmail");
		String sellMobilePhone = filter.getRequest().getParameter("sellMobilePhone");
		if(! StringUtils.isEmpty( sellEmail ) ){
			map.put("sellEmail",sellEmail);
		}
		if(! StringUtils.isEmpty( sellMobilePhone ) ){
			map.put("sellMobilePhone",sellMobilePhone);
		}

		((OtcAppTransactionDao)dao).findPageBySql(map);
		//----------------------查询结束------------------------------

		//----------------------分页查询底部外壳------------------------------
		//设置分页数据
		pageResult.setRows(page.getResult());
		//设置总记录数
		pageResult.setRecordsTotal(page.getTotal());
		pageResult.setDraw(filter.getDraw());
		pageResult.setPage(filter.getPage());
		pageResult.setPageSize(filter.getPageSize());
		//----------------------分页查询底部外壳------------------------------

		return pageResult;

	}

	@Override
	public FrontPage findFrontPageBySql(Map<String, String> map){
		try {
			Page<OtcAppTransaction> page = PageFactory.getPage(map);

			Map<String,Object> paramMap = new HashMap<String,Object>();

			if ("3".equals(map.get("transactionMode"))) {
				paramMap.put("transactionMode", "3");
			}

			if (StringUtil.isNotEmpty(map.get("buyUserId"))) {
				paramMap.put("buyUserId", map.get("buyUserId"));
			}
			if (StringUtil.isNotEmpty(map.get("sellUserId"))) {
				paramMap.put("sellUserId", map.get("sellUserId"));
			}
			if (StringUtil.isNotEmpty(map.get("transactionNum"))) {
				paramMap.put("transactionNum", "%"+map.get("transactionNum")+"%");
			}
			if (StringUtil.isNotEmpty(map.get("status"))) {
				if ("3".equals(map.get("status"))) {
					paramMap.put("status", "3,6,7");
				} else if ("4".equals(map.get("status"))) {
					paramMap.put("status", "4,9,10,11,12,15,16");
				} else {
					paramMap.put("status", map.get("status"));
				}
			}
			//买卖方是否删除
			if(StringUtil.isNotEmpty(map.get("buyIsDeleted"))){
				paramMap.put("buyIsDeleted", 0);
			}
			if(StringUtil.isNotEmpty(map.get("sellIsDeleted"))){
				paramMap.put("sellIsDeleted", 0);
			}

			if(StringUtil.isNotEmpty(map.get("coinCode"))){
				paramMap.put("coinCode", map.get("coinCode"));
			}

            List<OtcAppTransaction> pageBySql = ((OtcAppTransactionDao) dao).findFrontPageBySql(paramMap);

            List<OtcAppTransactionRemote> beanList = ObjectUtil.beanList(pageBySql, OtcAppTransactionRemote.class);
            if(beanList != null && beanList.size() > 0){
                for(OtcAppTransactionRemote att : beanList){
                    if(att != null && att.getTransactionNum() != null){
                        //获得申诉人id
                        QueryFilter filter = new QueryFilter(AppAppeal.class);
                        filter.addFilter("transactionNum=", att.getTransactionNum());
                        AppAppeal appeal = appAppealService.get(filter);
                        if(appeal != null && appeal.getUserId() != null){
                            att.setAppealId(appeal.getUserId()); //设置交易单申诉人id
                        }
                    }
                }
            }

            return  new FrontPage(beanList, page.getTotal(), page.getPages(), page.getPageSize());
		} catch (Exception e){

		}
		return null;
	}

	@Override
	public Map<String,BigDecimal> getTransactionSumById (Long advertisementId) {
		Map<String,BigDecimal> result = new HashMap<>(15);
		String[] str1={"2"};//待付款
		String[] str2={"3","6","7"};//已付款
		String[] str3={"14"};//已完成
		String[] str4={"5"};//已取消
		String[] str5={"4","9","10","11","12","15","16"};//申诉中

		BigDecimal tradeNum1 = BigDecimal.ZERO;
		BigDecimal tradeNum2 = BigDecimal.ZERO;
		BigDecimal tradeNum3 = BigDecimal.ZERO;
		BigDecimal tradeNum4 = BigDecimal.ZERO;
		BigDecimal tradeNum5 = BigDecimal.ZERO;
		BigDecimal sellfee1 = BigDecimal.ZERO;
		BigDecimal sellfee2 = BigDecimal.ZERO;
		BigDecimal sellfee3 = BigDecimal.ZERO;
		BigDecimal sellfee4 = BigDecimal.ZERO;
		BigDecimal sellfee5 = BigDecimal.ZERO;
		BigDecimal tradeMoney1 = BigDecimal.ZERO;
		BigDecimal tradeMoney2 = BigDecimal.ZERO;
		BigDecimal tradeMoney3 = BigDecimal.ZERO;
		BigDecimal tradeMoney4 = BigDecimal.ZERO;
		BigDecimal tradeMoney5 = BigDecimal.ZERO;
		BigDecimal count1 = BigDecimal.ZERO;
		BigDecimal count2 = BigDecimal.ZERO;
		BigDecimal count3 = BigDecimal.ZERO;
		BigDecimal count4 = BigDecimal.ZERO;
		BigDecimal count5 = BigDecimal.ZERO;
		BigDecimal buyfee1 = BigDecimal.ZERO;
		BigDecimal buyfee2 = BigDecimal.ZERO;
		BigDecimal buyfee3 = BigDecimal.ZERO;
		BigDecimal buyfee4 = BigDecimal.ZERO;
		BigDecimal buyfee5 = BigDecimal.ZERO;
		List<Map<String,Object>> list = ((OtcAppTransactionDao) dao).getTransactionSumById(advertisementId);
		if(list != null && list.size() > 0){
			for (Map<String, Object> map : list) {
				String status = map.get("status").toString();
				String tradeNum = map.get("tradeNum").toString();
				String tradeMoney = map.get("tradeMoney").toString();
				String fee = map.get("sellfee").toString();
				String count = map.get("con").toString();
				String buyfee = map.get("buyfee").toString();
				if(Arrays.asList(str1).contains(status)){
					tradeNum1 = tradeNum1.add(new BigDecimal(tradeNum));
					tradeMoney1 = tradeMoney1.add(new BigDecimal(tradeMoney));
					sellfee1 = sellfee1.add(new BigDecimal(fee));
					count1 = count1.add(new BigDecimal(count));
					buyfee1 = buyfee1.add(new BigDecimal(buyfee));
				}else if(Arrays.asList(str2).contains(status)){
					tradeNum2 = tradeNum2.add(new BigDecimal(tradeNum));
					tradeMoney2 = tradeMoney2.add(new BigDecimal(tradeMoney));
					sellfee2 = sellfee2.add(new BigDecimal(fee));
					count2 = count2.add(new BigDecimal(count));
					buyfee2 = buyfee2.add(new BigDecimal(buyfee));
				}else if(Arrays.asList(str3).contains(status)){
					tradeNum3 = tradeNum3.add(new BigDecimal(tradeNum));
					tradeMoney3 = tradeMoney3.add(new BigDecimal(tradeMoney));
					sellfee3 = sellfee3.add(new BigDecimal(fee));
					count3 = count3.add(new BigDecimal(count));
					buyfee3 = buyfee3.add(new BigDecimal(buyfee));
				}else if(Arrays.asList(str4).contains(status)){
					tradeNum4 = tradeNum4.add(new BigDecimal(tradeNum));
					tradeMoney4 = tradeMoney4.add(new BigDecimal(tradeMoney));
					sellfee4 = sellfee4.add(new BigDecimal(fee));
					count4 = count4.add(new BigDecimal(count));
					buyfee4 = buyfee4.add(new BigDecimal(buyfee));
				}else if(Arrays.asList(str5).contains(status)){
					tradeNum5 = tradeNum5.add(new BigDecimal(tradeNum));
					tradeMoney5 = tradeMoney5.add(new BigDecimal(tradeMoney));
					sellfee5 = sellfee5.add(new BigDecimal(fee));
					count5 = count5.add(new BigDecimal(count));
					buyfee5 = buyfee5.add(new BigDecimal(buyfee));
				}
			}
		}
		result.put("tradeNum1",tradeNum1);
		result.put("tradeNum2",tradeNum2);
		result.put("tradeNum3",tradeNum3);
		result.put("tradeNum4",tradeNum4);
		result.put("tradeNum5",tradeNum5);
		result.put("tradeMoney1",tradeMoney1);
		result.put("tradeMoney2",tradeMoney2);
		result.put("tradeMoney3",tradeMoney3);
		result.put("tradeMoney4",tradeMoney4);
		result.put("tradeMoney5",tradeMoney5);
		result.put("count1",count1);
		result.put("count2",count2);
		result.put("count3",count3);
		result.put("count4",count4);
		result.put("count5",count5);
		result.put("sellfee1",sellfee1);
		result.put("sellfee2",sellfee2);
		result.put("sellfee3",sellfee3);
		result.put("sellfee4",sellfee4);
		result.put("sellfee5",sellfee5);
		result.put("buyfee1",buyfee1);
		result.put("buyfee2",buyfee2);
		result.put("buyfee3",buyfee3);
		result.put("buyfee4",buyfee4);
		result.put("buyfee5",buyfee5);
		return result;
	}

	@Override
	public List<OtcAppTransaction> findOtcAppTransactionListBySql (Map<String, String> map) {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		if ("3".equals(map.get("transactionMode"))) {
			paramMap.put("transactionMode", "3");
		}

		if (StringUtil.isNotEmpty(map.get("buyUserId"))) {
			paramMap.put("buyUserId", map.get("buyUserId"));
		}
		if (StringUtil.isNotEmpty(map.get("sellUserId"))) {
			paramMap.put("sellUserId", map.get("sellUserId"));
		}
		if (StringUtil.isNotEmpty(map.get("transactionNum"))) {
			paramMap.put("transactionNum", "%"+map.get("transactionNum")+"%");
		}
		if (StringUtil.isNotEmpty(map.get("status"))) {
			paramMap.put("status", map.get("status"));
		}
		//买卖方是否删除
		if(StringUtil.isNotEmpty(map.get("buyIsDeleted"))){
			paramMap.put("buyIsDeleted", 0);
		}
		if(StringUtil.isNotEmpty(map.get("sellIsDeleted"))){
			paramMap.put("sellIsDeleted", 0);
		}

		if(StringUtil.isNotEmpty(map.get("coinCode"))){
			paramMap.put("coinCode", map.get("coinCode"));
		}
		if(StringUtil.isNotEmpty(map.get("id"))){
			paramMap.put("advertisementId=", map.get("id"));
		}
		List<OtcAppTransaction> pageBySql = ((OtcAppTransactionDao) dao).findOtcAppTransactionListBySql(paramMap);
		return pageBySql;
	}

	@Override
	public void saveOtcLog (Long id) {
		((OtcAppTransactionDao) dao).saveOtcLog(id);
	}

	@Override
	public int getOrderSumBy30(Long id,String coinCode){
		return ((OtcAppTransactionDao)dao).getOrderSumBy30(id,coinCode);
	}
}
