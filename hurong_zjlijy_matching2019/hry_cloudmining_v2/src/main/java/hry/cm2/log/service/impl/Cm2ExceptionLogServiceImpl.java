/**
 * Copyright:   
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-10-14 16:05:22 
 */
package hry.cm2.log.service.impl;

import hry.cm2.log.model.Cm2ExceptionLog;
import hry.cm2.log.service.Cm2ExceptionLogService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * <p> Cm2ExceptionLogService </p>
 * @author:         yaozh
 * @Date :          2019-10-14 16:05:22  
 */
@Service("cm2ExceptionLogService")
public class Cm2ExceptionLogServiceImpl extends BaseServiceImpl<Cm2ExceptionLog, Long> implements Cm2ExceptionLogService{
	
	@Resource(name="cm2ExceptionLogDao")
	@Override
	public void setDao(BaseDao<Cm2ExceptionLog, Long> dao) {
		super.dao = dao;
	}

	@Override
	public void insertlog(Exception e, String functionName, String remark) {
		// TODO Auto-generated method stub
		try {
			// 插入异常日志
			Cm2ExceptionLog cmExceptionLog = new Cm2ExceptionLog();
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
