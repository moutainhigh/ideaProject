/**
 * Copyright:   
 * @author:      denghf
 * @version:     V1.0 
 * @Date:        2019-01-14 13:38:06 
 */
package hry.ico.service.impl;

import com.alibaba.fastjson.JSON;
import hry.bean.JsonResult;
import hry.ico.dao.IcoAccountDao;
import hry.ico.model.IcoAccount;
import hry.ico.model.util.IcoAccountAtioPo;
import hry.ico.model.util.IcoAwardPo;
import hry.ico.service.IcoAccountService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;

import hry.mq.producer.service.MessageProducer;
import hry.util.QueryFilter;
import hry.util.date.DateUtil;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * <p> IcoAccountService </p>
 * @author:         denghf
 * @Date :          2019-01-14 13:38:06  
 */
@Service("icoAccountService")
public class IcoAccountServiceImpl extends BaseServiceImpl<IcoAccount, Long> implements IcoAccountService{

	@Resource(name="icoAccountDao")
	@Override
	public void setDao(BaseDao<IcoAccount, Long> dao) {
		super.dao = dao;
	}

	@Resource
	private MessageProducer messageProducer;

	@Override
	public boolean updateUserAccount(Long customerId, BigDecimal number){
		IcoAccount icoAccount=this.get(new QueryFilter(IcoAccount.class).addFilter("customerId=",customerId));
		int nu=((IcoAccountDao)dao).updateByVersion(icoAccount.getId(),icoAccount.getVersion(),number);
		if(nu>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean updateByAccountId(Long accountId,Integer version,BigDecimal number) {
		int nu=((IcoAccountDao)dao).updateByVersion(accountId,version,number);
		if(nu>0){
			return true;
		}
		return false;
	}

	@Override
	public String recommendedLockSum(Long customerId) {
		return ((IcoAccountDao)dao).recommendedLockSum(customerId);
	}

	/**
	 * 定时发经验奖励
	 * @return
	 */
	@Override
	public boolean timingToAward() {
		//TODO 测试使用
	/*	long time =System.currentTimeMillis();
		List<IcoAccount> list=super.find(new QueryFilter(IcoAccount.class).addFilter("storageMoney>","0"));
		System.out.println("定时发经验定时器进来了"+list.size());
		if(list!=null&&list.size()>0){
			//long time =DateUtil.getLongNowDate();
			for (IcoAccount icoAccount:list) {
				IcoAwardPo icoAwardPo = new IcoAwardPo();//释放存储变更结算以前奖励
				icoAwardPo.setCustomerId(icoAccount.getCustomerId());
				icoAwardPo.setCurrentLockSum(icoAccount.getStorageMoney());//释放前的存储量
				icoAwardPo.setTime(time);
				messageProducer.toAward(JSON.toJSONString(icoAwardPo));
			}
		}*/
		return true;
	}
	//所有用户总锁仓数量
	@Override
	public String allMemberLockSum(){
		return  ((IcoAccountDao)dao).allMemberLockSum();
	}
	//获取用户锁仓比率 （自身锁仓数/所有用户的总锁仓数）
	@Override
	public String getMemberLockAtio( Long customerId){
		return  ((IcoAccountDao)dao).getMemberLockAtio(customerId);
	}
	//获取用户的锁仓占比
	public List<IcoAccountAtioPo> getAccountAtioBylevelSort(Integer levelSort){
		return  ((IcoAccountDao)dao).getAccountAtioBylevelSort(levelSort);
	}

}
