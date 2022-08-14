package hry.oauth.shiro;

import hry.bean.BaseManageUser;
import hry.listener.ServerManagement;
import hry.oauth.authorization.service.AuthorizationService;
import hry.oauth.user.dao.AppUserDao;
import hry.oauth.user.service.AppResourceService;
import hry.oauth.user.service.AppRoleService;
import hry.oauth.user.service.AppUserService;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;
import java.util.Set;

/**
 * 认证授权域
 * <p>
 * TODO
 * </p>
 * 
 * @author: Liu Shilei
 * @Date : 2015年9月25日 上午10:38:46
 */
public class MyRealm extends AuthorizingRealm {
	private static Logger logger = Logger.getLogger(MyRealm.class);
	@Resource
	private AppUserService appUserService;
	@Resource
	private AppRoleService appRoleService;
	@Resource
	private AppResourceService appResourceService;
	@Resource
	private AuthorizationService authorizationService;
	@Resource
	private AppUserDao appUserDao;

	private static final String OR_OPERATOR = " or ";
	private static final String AND_OPERATOR = " and ";
	private static final String NOT_OPERATOR = " not ";

	/**
	 * 授权--查询登录用户所拥有的权限资源
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

		String saasId = (String) SecurityUtils.getSubject().getSession().getAttribute("hrysaasid");
		String userid = (String) SecurityUtils.getSubject().getSession().getAttribute("userid");
		// 登录后查询权限
		logger.info("查询权限.......................................................");
		// authorizationInfo.setRoles(authorizationService.findRoles(saasId, appKey,
		// user));
		Set<String> findPermissions = authorizationService.findPermissions(userid);
		authorizationInfo.setStringPermissions(findPermissions);

		return authorizationInfo;
	}

	/**
	 * 认证--登录认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;

		BaseManageUser _user = appUserService.checkUsername(token.getUsername());
		logger.error(_user.getPassword());
		logger.error(_user.getSalt());
		if (token.getUsername().equals(_user.getUsername())) {
			return new SimpleAuthenticationInfo(_user.getUsername(), // 用户名
					_user.getPassword(), // 密码
					ByteSource.Util.bytes(_user.getSalt()), // salt
					getName());

		} else {
			logger.error("认证失败");
			throw new AuthenticationException();
		}
	}

	/**
	 * 支持or and not 关键词 不支持and or混用
	 * 
	 * @param principals
	 * @param permission
	 * @return
	 */
	public boolean isPermitted(PrincipalCollection principals, String permission) {
		if (permission.contains(OR_OPERATOR)) {
			String[] permissions = permission.split(OR_OPERATOR);
			for (String orPermission : permissions) {
				if (isPermittedWithNotOperator(principals, orPermission)) {
					return true;
				}
			}
			return false;
		} else if (permission.contains(AND_OPERATOR)) {
			String[] permissions = permission.split(AND_OPERATOR);
			for (String orPermission : permissions) {
				if (!isPermittedWithNotOperator(principals, orPermission)) {
					return false;
				}
			}
			return true;
		} else {
			return isPermittedWithNotOperator(principals, permission);
		}
	}

	private boolean isPermittedWithNotOperator(PrincipalCollection principals, String permission) {
		if (permission.startsWith(NOT_OPERATOR)) {
			return !super.isPermitted(principals, permission.substring(NOT_OPERATOR.length()));
		} else {
			return super.isPermitted(principals, permission);
		}
	}

}