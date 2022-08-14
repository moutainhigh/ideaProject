package hry.core.shiro;

import com.auth0.jwt.exceptions.TokenExpiredException;
import hry.app.jwt.JWTToken;
import hry.app.jwt.JWTUtil;
import hry.bean.BaseCustomerUser;
import hry.manage.remote.RemoteManageService;
import hry.manage.remote.model.User;
import hry.util.common.SpringUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.jsoup.helper.StringUtil;

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


	/**
	 * 大坑，必须重写此方法，不然Shiro会报错
	 */
	@Override
	public boolean supports(AuthenticationToken token) {
		return token instanceof JWTToken;
	}

	/**
	 * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		String account = JWTUtil.getClaim(principals.toString(), "account");
		return simpleAuthorizationInfo;
	}

	/**
	 * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
		String token = (String) auth.getCredentials();

		// 检查token是否有效
		if (token == null) {
			throw new AuthenticationException("token不存在(The token does not exist.)");
		}

		// username，用于和数据库进行对比
		String username = JWTUtil.getUsername(token);

		// 获取token中的来源
		String source = JWTUtil.getClaim(token, "source");

		// 帐号为空
		if (StringUtil.isBlank(username)) {
			throw new AuthenticationException("Token中帐号为空(The account in Token is empty.)");
		}

		// 查询用户是否存在
		RemoteManageService remoteManageService = SpringUtil.getBean("remoteManageService");
		User user = remoteManageService.getUserInfoByUsername(username);
		if (user == null) {
			throw new AuthenticationException("该帐号不存在(The account does not exist.)");
		}

		// 开始认证，要AccessToken认证通过
		if (!JWTUtil.verify(token, username, source, user.getPassword())) {
			throw new TokenExpiredException("Token已过期(Token expired or incorrect.)");
		}

		return new SimpleAuthenticationInfo(token, token, getName());
	}

}
