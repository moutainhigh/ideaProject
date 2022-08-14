/**
 * Copyright:   
 * @author:      yaozhuo
 * @version:     V1.0 
 * @Date:        2019-04-29 16:16:58 
 */
package hry.klg.assetsrecord.service.impl;

import com.github.pagehelper.Page;
import hry.exchange.transaction.model.ExDmTransaction;
import hry.exchange.transaction.service.ExDmTransactionService;
import hry.klg.assetsrecord.model.KlgAssetsRecord;
import hry.klg.assetsrecord.service.KlgAssetsRecordService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;

import hry.klg.enums.TriggerPointEnum;
import hry.redis.common.utils.RedisService;
import hry.util.QueryFilter;
import hry.util.idgenerate.IdGenerate;
import hry.util.idgenerate.NumConstant;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p> KlgAssetsRecordService </p>
 * @author:         yaozhuo
 * @Date :          2019-04-29 16:16:58  
 */
@Service("klgAssetsRecordService")
public class KlgAssetsRecordServiceImpl extends BaseServiceImpl<KlgAssetsRecord, Long> implements KlgAssetsRecordService{

	private  final static String TIBiRedisRecordKey="TIBiRedisRecordKey";
	private  final static int pageSize=5000;
	private static Object rlock = new Object();
	@Resource
	private RedisService redisService;
	@Resource
	private ExDmTransactionService dmTransactionService;

	@Resource(name="klgAssetsRecordDao")
	@Override
	public void setDao(BaseDao<KlgAssetsRecord, Long> dao) {
		super.dao = dao;
	}


	@Override
	public void saveTiBiRecord() {
		synchronized (rlock) {
			String str=redisService.get(TIBiRedisRecordKey);
			if(str==null){
				str="0";
			}
			QueryFilter filter=new QueryFilter(ExDmTransaction.class);
			filter.addFilter("status=",2);//'1待审核 2已完成 3以否决',
			filter.addFilter("id>",str);
			filter.addFilter("transactionType=",2);//(1充币 ，2提币)
			filter.setPage(1);
			filter.setPageSize(2000);
			filter.setOrderby("id asc");
			Page<ExDmTransaction> pages=dmTransactionService.findPage(filter);
			List <ExDmTransaction> ls= pages.getResult();
			if(ls!=null&&ls.size()>0){
				try {
					for (ExDmTransaction ext:ls) {
						str=ext.getId().toString();
						saveRecord(ext);
					}
				}catch (Exception e){
					e.printStackTrace();
				}finally {
					redisService.save(TIBiRedisRecordKey,str);
				}
			}
		}
	}

	private  boolean saveRecord(ExDmTransaction exDmTransaction){
		//transactionType(1充币 ，2提币)
		String serialNumber= IdGenerate.transactionNum(NumConstant.Ex_Dm_Transaction);
		KlgAssetsRecord klgAssetsRecord2=new KlgAssetsRecord();//交易流水
		klgAssetsRecord2.setCustomerId(exDmTransaction.getUserId());//用户Id
		klgAssetsRecord2.setAccountId(exDmTransaction.getAccountId());//币账户ID
		klgAssetsRecord2.setSerialNumber(serialNumber);//流水号
		klgAssetsRecord2.setAccountType(2);  //账户类型 1.热账户 2.冷账户
		klgAssetsRecord2.setCoinCode(exDmTransaction.getCoinCode());//币种Code
		klgAssetsRecord2.setChangeCount(exDmTransaction.getTransactionMoney()); //交易量
		klgAssetsRecord2.setChangeType(2);////变动类型 1加 2减
		klgAssetsRecord2.setTriggerPoint(TriggerPointEnum.TransferToExchange.getKey());//触发点
		klgAssetsRecord2.setTriggerSerialNumber(exDmTransaction.getOrderNo()); //触发点流水号
		klgAssetsRecord2.setRemark("转出到交易所支出");
		super.save(klgAssetsRecord2);
	/*	klgAssetsRecord2.setId(null);
		klgAssetsRecord2.setChangeType(1);////变动类型 1加 2减
		klgAssetsRecord2.setAccountType(2);  //账户类型 1.热账户 2.冷账户
		super.save(klgAssetsRecord2);*/
		return  true;
	}
}
