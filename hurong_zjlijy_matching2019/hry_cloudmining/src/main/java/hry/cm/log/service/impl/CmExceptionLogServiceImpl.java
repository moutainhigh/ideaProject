/**
 * Copyright:   
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-07-30 16:40:55 
 */
package hry.cm.log.service.impl;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import hry.cm.log.model.CmExceptionLog;
import hry.cm.log.service.CmExceptionLogService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;

/**
 * <p> CmExceptionLogService </p>
 * @author:         yaozh
 * @Date :          2019-07-30 16:40:55  
 */
@Service("cmExceptionLogService")
public class CmExceptionLogServiceImpl extends BaseServiceImpl<CmExceptionLog, Long> implements CmExceptionLogService{
	
	@Resource(name="cmExceptionLogDao")
	@Override
	public void setDao(BaseDao<CmExceptionLog, Long> dao) {
		super.dao = dao;
	}

	@Override
	public void insertlog(Exception e, String functionName, String remark) {
		// TODO Auto-generated method stub
		try {
			// 插入异常日志
			CmExceptionLog cmExceptionLog = new CmExceptionLog();
			/*String strE = getStackTraceInfo(e);
			if (strE.length() <= 250) {
				cmExceptionLog.setExceptionText(strE);
			} else {
				cmExceptionLog.setExceptionText(strE.substring(0, 250));
			}*/
			cmExceptionLog.setFunctionName(functionName);
			if (remark.length() <= 250) {
				cmExceptionLog.setRemark(remark);
			} else {
				cmExceptionLog.setRemark(remark.substring(0, 250));
			}
			super.save(cmExceptionLog);
		} catch (Exception e2) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 获取异常信息
	 */
	public static String getStackTraceInfo(Exception e) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		try {
			e.printStackTrace(pw);
			pw.flush();
			sw.flush();
			return sw.toString();
		} finally {
			try {
				pw.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			try {
				sw.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

}
