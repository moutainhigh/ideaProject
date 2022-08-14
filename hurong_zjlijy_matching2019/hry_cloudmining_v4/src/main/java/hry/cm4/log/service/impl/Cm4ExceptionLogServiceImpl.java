/**
 * Copyright:   
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-11-21 10:01:39 
 */
package hry.cm4.log.service.impl;

import hry.cm4.log.model.Cm4ExceptionLog;
import hry.cm4.log.service.Cm4ExceptionLogService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * <p> Cm4ExceptionLogService </p>
 * @author:         yaozh
 * @Date :          2019-11-21 10:01:39  
 */
@Service("cm4ExceptionLogService")
public class Cm4ExceptionLogServiceImpl extends BaseServiceImpl<Cm4ExceptionLog, Long> implements Cm4ExceptionLogService{
	
	@Resource(name="cm4ExceptionLogDao")
	@Override
	public void setDao(BaseDao<Cm4ExceptionLog, Long> dao) {
		super.dao = dao;
	}

	@Override
	public void insertlog(Exception e, String functionName, String remark) {
		// TODO Auto-generated method stub
		try {
			// 插入异常日志
			Cm4ExceptionLog cmExceptionLog = new Cm4ExceptionLog();
			cmExceptionLog.setFunctionName(functionName);
			String strE = getStackTraceInfo(e);
			if (strE.length() <= 250) {
				cmExceptionLog.setExceptionText(strE);
			} else {
				cmExceptionLog.setExceptionText(strE.substring(0, 250));
			}
			if (remark.length() <= 250) {
				cmExceptionLog.setRemark(remark);
			} else {
				cmExceptionLog.setRemark(remark.substring(0, 250));
			}
			super.save(cmExceptionLog);
		} catch (Exception e2) {
			// TODO: handle exception
			e.printStackTrace();
			Cm4ExceptionLog cmExceptionLog = new Cm4ExceptionLog();
			cmExceptionLog.setFunctionName(functionName);
			cmExceptionLog.setSaasId("log");
			if (remark.length() <= 250) {
				cmExceptionLog.setRemark(remark);
			} else {
				cmExceptionLog.setRemark(remark.substring(0, 250));
			}
			super.save(cmExceptionLog);
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
