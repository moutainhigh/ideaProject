package hry.oauth.aop;

import hry.bean.BaseManageUser;
import hry.bean.JsonResult;
import hry.util.httpRequest.IpUtil;
import hry.util.sys.ContextUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 登录切面
 * LoginAspect.java
 * @author denghf
 * 2018年2月1日 下午9:40:09
 */
@Aspect
@Component
public class LoginLogAop {

	@Resource
	private JdbcTemplate jdbcTemplate;
	
	@Pointcut("execution(* hry.oauth.index.controller.IndexController.checklogin(..))")
	public void log() {
		
	}

	@AfterReturning(pointcut="log()", returning="rvt")
    public void doAfterReturning(JoinPoint joinPoint, Object rvt) throws NoSuchMethodException, SecurityException {
		JsonResult jsonResult = (JsonResult)rvt;
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		final String ip = IpUtil.getIp(request);
		if(jsonResult.getSuccess()){
			BaseManageUser user = ContextUtil.getCurrentUser();
			System.out.println("===========================后台登录信息："+user.getId()+","+user.getUsername()+"======================");
			jdbcTemplate.update("insert into app_login_log(userId, userName, ip, loginTime, saasId) values ("+user.getId()+",\""+user.getUsername()+"\",\""+ip+"\",\""+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"\",\"登录成功\")");
		}else{
			jdbcTemplate.update("insert into app_login_log(userName, ip, loginTime, saasId) values (\""+jsonResult.getObj()+"\",\""+ip+"\",\""+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"\",\""+jsonResult.getMsg()+"\")");
		}
	}

}
