package hry.manage.remote;

import hry.customer.user.service.AppCustomerService;
import hry.manage.remote.model.RemoteResult;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 系统配置远程调用接口实现类
 */
public class RemoteSysConfigServiceImpl implements RemoteSysConfigService {

    @Resource
    private AppCustomerService appCustomerService;

    /**
     * 获得审核密码是否为空
     * @return
     */
    @Override
    public RemoteResult getAuditpassword () {
       return appCustomerService.getAuditpassword();
    }

    /**
     * 保存审核密码
     * @param map
     * @return
     */
    @Override
    public RemoteResult setAuditpassword (Map<String, String> map) {
        return appCustomerService.setAuditpassword(map);
    }
}
