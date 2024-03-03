package com.eZonWork.Interceptors;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebAuthIncepConfig implements WebMvcConfigurer {

	  @Override
	    public void addInterceptors(InterceptorRegistry registry) { 
	        registry.addInterceptor(new AuthInterceptor()); 
	    }
}
