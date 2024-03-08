package com.eZonWork.Interceptors;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor implements HandlerInterceptor {

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		boolean flag=true;

		try {

			String path = request.getRequestURI();
			if ( path.equals("/common/getAllRegister")|| path.equals("/common/updateUser")|| path.equals("/common/deleteUserById/") || path.startsWith("/common/getUserById/")) {
				if (!userHasAuthority(request, "ADMIN")) {
					flag=false;
						if (!userHasAuthority(request, "USER")) {
							flag=false;
						}else {
							flag=true;
						}				
					

				}
			} 
			else if (path.equals("/common/updateUser") || path.startsWith("/common/getUserById/")) {
			if (!userHasAuthority(request, "USER")) {
					flag=false;

			}
		}
		} catch (Exception e) {
			flag=false;

		}
		return flag;
	}

	private boolean userHasAuthority(HttpServletRequest request, String authority) {
		String authorities = request.getAttribute("authrole").toString();
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i <= authorities.length() - 1; i++) {
			if (i == 0 || i == authorities.length() - 1) {
				continue;
			} else {

				stringBuilder.append(authorities.charAt(i));
			}
		}
		System.err.println(stringBuilder);
		authorities = stringBuilder.toString();
		if (authorities != null) {
			if (authorities.equals(authority)) {
				return true;

			}

		}
		return false;
	}

}
