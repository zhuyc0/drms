package com.why.drms.handle;

import com.why.drms.entity.Result;
import com.why.drms.util.ResponseUtil;
import com.why.drms.util.ResultUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * spring security处理器
 *
 * @author zhuyc
 *
 *         2019年03月25日
 */
@Configuration
public class SecurityHandler {

	/**
	 * 登陆成功
	 * @return
	 */
	@Bean
	public AuthenticationSuccessHandler loginSuccessHandler() {

		return new AuthenticationSuccessHandler() {
			@Override
			public void onAuthenticationSuccess(HttpServletRequest request,
												HttpServletResponse response,
                                                Authentication authentication) throws IOException, ServletException {
				ResponseUtil.responseJson(response, HttpStatus.OK.value(), ResultUtil.successMsg("登陆成功！"));
			}
		};
	}

	/**
	 * 登陆失败
	 *
	 * @return
	 */
	@Bean
	public AuthenticationFailureHandler loginFailureHandler() {
		return new AuthenticationFailureHandler() {

			@Override
			public void onAuthenticationFailure(HttpServletRequest request,
												HttpServletResponse response,
                                                AuthenticationException exception) {

				String message;
				if (exception instanceof UsernameNotFoundException) {
					message = "用户不存在！";
				} else if (exception instanceof BadCredentialsException) {
					message = "用户名或密码错误！";
				} else if (exception instanceof LockedException) {
					message = "用户被锁定!";
				} else if (exception instanceof DisabledException) {
					message = "用户不可用！请查看审核信息";
				} else if (exception instanceof AccountExpiredException) {
					message = "账户已过期！";
				} else if (exception instanceof CredentialsExpiredException) {
					message = "用户密码已过期！";
				} else {
					message = "认证失败，请联系网站管理员！";
				}
				ResponseUtil.responseJson(response, HttpStatus.OK.value(), ResultUtil.error(-1,message));
			}
		};

	}

	/**
	 * 未登录，返回401
	 * @return
	 */
	@Bean
	public AuthenticationEntryPoint authenticationEntryPoint() {
		return new AuthenticationEntryPoint() {

			@Override
			public void commence(HttpServletRequest request,
								 HttpServletResponse response,
                                 AuthenticationException authException) throws IOException {
				if (ExceptionHandle.isAjax(request)){
					Result result = new Result(HttpStatus.UNAUTHORIZED.value(),"请登录后在操作");
					ResponseUtil.responseJson(response, HttpStatus.UNAUTHORIZED.value(), result);
				}else {
					response.sendRedirect("/login.html");
				}
			}
		};
	}

	/**
	 * 退出处理
	 * @return
	 */
	@Bean
	public LogoutSuccessHandler logoutSussHandler() {
		return new LogoutSuccessHandler() {
			@Override
			public void onLogoutSuccess(HttpServletRequest request,
										HttpServletResponse response,
                                        Authentication authentication){
				Result result = new Result(HttpStatus.OK.value(),"退出成功");
				ResponseUtil.responseJson(response, HttpStatus.OK.value(), result);
			}
		};

	}

}
