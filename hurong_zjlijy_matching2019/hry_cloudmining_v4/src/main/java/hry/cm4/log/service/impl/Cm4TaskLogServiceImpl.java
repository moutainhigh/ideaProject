/**
 * Copyright:   
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-11-21 10:04:52 
 */
package hry.cm4.log.service.impl;

import hry.cm4.log.model.Cm4TaskLog;
import hry.cm4.log.service.Cm4TaskLogService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Date;
import java.util.Enumeration;

/**
 * <p> Cm4TaskLogService </p>
 * @author:         yaozh
 * @Date :          2019-11-21 10:04:52  
 */
@Service("cm4TaskLogService")
public class Cm4TaskLogServiceImpl extends BaseServiceImpl<Cm4TaskLog, Long> implements Cm4TaskLogService{
	
	@Resource(name="cm4TaskLogDao")
	@Override
	public void setDao(BaseDao<Cm4TaskLog, Long> dao) {
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
		Cm4TaskLog log = new Cm4TaskLog();
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
