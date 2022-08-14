package hry.manage.remote;

import hry.manage.remote.model.RemoteResult;

import java.util.Map;

/**
 * 系统配置远程调用接口
 */
public interface RemoteSysConfigService {

    /**
     * 获得审核密码是否为空
     * @return
     */
    RemoteResult getAuditpassword();

    RemoteResult setAuditpassword(Map<String, String> map);
}
