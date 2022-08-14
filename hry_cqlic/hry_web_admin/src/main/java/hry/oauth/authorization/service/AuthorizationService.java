package hry.oauth.authorization.service;



import java.util.List;
import java.util.Set;

import hry.oauth.authorization.model.Authorization;
import hry.oauth.user.model.AppUser;


/**
 * client 端访问调用权限查询service
 * <p> TODO</p>
 * @author:         Liu Shilei 
 * @Date :          2015年11月4日 上午10:11:35
 */
public interface AuthorizationService {


    /**
     * 根据AppKey和用户名查找其角色
     * @param username
     * @return
     */
    public Set<String> findRoles(String saasId,String appKey, AppUser user);

    /**
     * 根据AppKey和用户名查找权限字符串
     * @param eamil
     * @return
     */
    public Set<String> findPermissions(String  username);


}
