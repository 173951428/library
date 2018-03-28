package com.library.system.handlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.library.system.entity.MyUser;

/*
 * 登录拦截器  
 * @author zhaoxinping
 *
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {

	/**
	 * 请求被处理之前调用这个方法
	 */
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		boolean islogin = false;
		String url = request.getRequestURL().toString();
		if (url.contains("user/login.ajax")
				|| url.contains("user/checkUser.ajax")
				|| url.contains("user/checkCode.ajax")
				|| url.contains("user/changePassword.ajax")
				|| url.contains("user/getEmail.ajax") 
				|| url.contains(".shtml")
				|| url.contains("checkMessage.ajax")
				|| url.contains("user/saveOrUpdate.ajax")) {
			islogin = true;

		} else {
			System.out.println(url);
			HttpSession session = request.getSession();
			String userId = (String) session.getAttribute("userId");
			if (null == userId || userId.equals("")) {
				response.sendRedirect(request.getContextPath()
						+ "/redirect.html");
				islogin = false;
			} else {
				islogin = true;
			}
		}
		return islogin;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
