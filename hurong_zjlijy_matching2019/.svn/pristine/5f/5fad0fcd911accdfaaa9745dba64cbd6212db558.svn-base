/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        5050-01-08 14:15:35 
 */
package hry.cm5.log.service.impl;

import hry.cm5.log.model.Cm5TaskLog;
import hry.cm5.log.service.Cm5TaskLogService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Date;
import java.util.Enumeration;

/**
 * <p> Cm5TaskLogService </p>
 * @author:         zhouming
 * @Date :          5050-01-08 14:15:35  
 */
@Service("cm5TaskLogService")
public class Cm5TaskLogServiceImpl extends BaseServiceImpl<Cm5TaskLog, Long> implements Cm5TaskLogService{
	
	@Resource(name="cm5TaskLogDao")
	@Override
	public void setDao(BaseDao<Cm5TaskLog, Long> dao) {
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
		Cm5TaskLog log = new Cm5TaskLog();
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
