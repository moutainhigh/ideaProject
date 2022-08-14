/**
 * Copyright:   
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-07-31 19:33:49 
 */
package hry.cm.log.service.impl;

import hry.cm.log.model.CmTaskLog;
import hry.cm.log.service.CmTaskLogService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Date;
import java.util.Enumeration;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> CmTaskLogService </p>
 * @author:         yaozh
 * @Date :          2019-07-31 19:33:49  
 */
@Service("cmTaskLogService")
public class CmTaskLogServiceImpl extends BaseServiceImpl<CmTaskLog, Long> implements CmTaskLogService{
	
	@Resource(name="cmTaskLogDao")
	@Override
	public void setDao(BaseDao<CmTaskLog, Long> dao) {
		super.dao = dao;
	}
	
	@Override
	public void saveLog(String functionName, Integer isException, Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		InetAddress localHostLANAddress = null;
		try {
			localHostLANAddress = getLocalHostLANAddress();
		} catch (Exception e) {
			e.printStackTrace();
		}
		CmTaskLog log = new CmTaskLog();
		log.setFunctionName(functionName);
		log.setIsException(isException);
		log.setIpaddress(localHostLANAddress.getHostAddress().toString());
		long seconds = startDate.getTime() - endDate.getTime();
		long minutes = seconds / (1000 * 60);
		log.setLasttime(minutes + "");
		log.setStartDate(startDate);
		log.setEndDate(endDate);
		dao.insert(log);
	}

	public InetAddress getLocalHostLANAddress() throws Exception {
		try {
			InetAddress candidateAddress = null;
			// 遍历所有的网络接口
			for (Enumeration ifaces = NetworkInterface.getNetworkInterfaces(); ifaces.hasMoreElements();) {
				NetworkInterface iface = (NetworkInterface) ifaces.nextElement();
				// 在所有的接口下再遍历IP
				for (Enumeration inetAddrs = iface.getInetAddresses(); inetAddrs.hasMoreElements();) {
					InetAddress inetAddr = (InetAddress) inetAddrs.nextElement();
					if (!inetAddr.isLoopbackAddress()) {// 排除loopback类型地址
						if (inetAddr.isSiteLocalAddress()) {
							// 如果是site-local地址，就是它了
							return inetAddr;
						} else if (candidateAddress == null) {
							// site-local类型的地址未被发现，先记录候选地址
							candidateAddress = inetAddr;
						}
					}
				}
			}
			if (candidateAddress != null) {
				return candidateAddress;
			}
			// 如果没有发现 non-loopback地址.只能用最次选的方案
			InetAddress jdkSuppliedAddress = InetAddress.getLocalHost();
			return jdkSuppliedAddress;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	

}
