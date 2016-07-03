package com.example.webcore.listener.authentication;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.dao.entity.User;

public class AuthenticationFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	private boolean isLoginScreen(HttpServletRequest httpServletRequest) {

		String screenURI = httpServletRequest.getRequestURI();

		return ((screenURI.indexOf("index") != -1)
				|| (screenURI.indexOf("errorpage") != -1)
				|| (screenURI.indexOf("reporterLogin.xhtml") != -1)
				|| (screenURI.indexOf("redirectSession.xhtml") != -1)
				|| (screenURI.indexOf("sessionExpire.xhtml") != -1)
				|| (screenURI.equals(httpServletRequest.getContextPath() + "/")) || (screenURI
					.equals(httpServletRequest.getContextPath())));
	}

	private boolean isSessionExpired(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		boolean isSessionExpired = false;
		// Check for a valid session availability
		HttpSession currentSession = httpServletRequest.getSession(false);
		// For current requests on session unavailability
		if (null != currentSession) {
			try {
				User user = (User) currentSession.getAttribute("agxUser");
				if (null == user || null == user.getUserId()
						|| "".equals(user.getUserId())) {
					isSessionExpired = true;
					httpServletRequest.setAttribute("COMM_URL",
							currentSession.getAttribute("COMM_URL"));
				}

			} catch (Exception e) {
				isSessionExpired = true;
			}
		} else {
			isSessionExpired = true;
		}
		return isSessionExpired;
	}

}
