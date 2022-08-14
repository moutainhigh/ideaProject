/**
 * Copyright:   
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-01-15 14:34:14 
 */
package hry.ico.service.impl;

import hry.ico.dao.IcoLockRecordDao;
import hry.ico.model.IcoLockRecord;
import hry.ico.service.IcoAccountService;
import hry.ico.service.IcoLockRecordService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p> IcoLockRecordService </p>
 * @author:         lzy
 * @Date :          2019-01-15 14:34:14  
 */
@Service("icoLockRecordService")
public class IcoLockRecordServiceImpl extends BaseServiceImpl<IcoLockRecord, Long> implements IcoLockRecordService{
	
	@Resource(name="icoLockRecordDao")
	@Override
	public void setDao(BaseDao<IcoLockRecord, Long> dao) {
		super.dao = dao;
	}

	/**
	 * 锁仓释放
	 * @return
	 */
	@Override
	@Transactional
	public boolean releaseLockRecord(IcoLockRecord icoLockRecord) {
		Long lockRecordId=icoLockRecord.getId();
		Integer releaseType=icoLockRecord.getReleaseType();//1为提前释放0正常
		BigDecimal releaseDeduct=icoLockRecord.getReleaseDeduct();
		BigDecimal actualReleaseNum=icoLockRecord.getActualReleaseNum();//实际释放数量
		BigDecimal releaseDeductNum=icoLockRecord.getReleaseDeductNum();//释放扣币数量
		//IcoLockRecord lockRecord=this.get(lockRecordId);
		int v=((IcoLockRecordDao)dao).updateByState(lockRecordId,releaseDeduct,releaseType,actualReleaseNum,releaseDeductNum);
		if(v>0){
			return true;
		}
		return false;
	}

	/**
	 * 追加天数
	 * @param icoLockRecord
	 * @return
	 */
	@Override
	@Transactional
	public boolean appendLockRecord(IcoLockRecord icoLockRecord,Integer addDay) {
		Long lockId=icoLockRecord.getId();
		if(addDay.intValue()<1){
			return  false;
		}
		int v=((IcoLockRecordDao)dao).appendLockDays(lockId,addDay);
		if(v>0){
			return true;
		}
		return false;
	}

	@Override
	public List<IcoLockRecord> getWaitReleaseList() {
		return ((IcoLockRecordDao)dao).getWaitReleaseList();
	}
}
