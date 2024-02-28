package com.eZonWork.JwtFilter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.eZonWork.Configurations.UserDetailsServiceInfo;
import com.eZonWork.JwtService.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private UserDetailsServiceInfo info;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token=null;
		String userName=null;
		String auth=request.getHeader("Authorization");
		
		if(auth!=null && auth.startsWith("Bearer ")) {
			token=auth.substring(7);
			
			userName=jwtService.getUserFromToken(token);
			if(userName!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
				UserDetails details=info.loadUserByUsername(userName);
				
				if(jwtService.isTokenValid(token, details)) {
					
					UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(userName, null,details.getAuthorities());
					authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authenticationToken);
				}
			}
		}
		filterChain.doFilter(request, response);
	}

}
