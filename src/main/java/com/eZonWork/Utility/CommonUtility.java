package com.eZonWork.Utility;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.eZonWork.JwtService.JwtService;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class CommonUtility {

	
	public static String randomIdGenerator() {
		UUID uuid=UUID.randomUUID();
		return uuid.toString();
	}

	 public static String getIpAddress() throws UnknownHostException {
		 InetAddress localhost = InetAddress.getLocalHost(); 
		 return localhost.getHostAddress();
	 }
	 
	 public static String timeStamp() {
		 SimpleDateFormat format=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		 return format.format(new Date());
	 }
	 
	 
	 public static String getUser(HttpServletRequest request) {
		 String userName=null;
		 JwtService jwtService=new JwtService();
		String token=request.getHeader("Authorization");
		if(token!=null && token.startsWith("Bearer ")) {
			token=token.substring(7);
			userName = jwtService.getUserFromToken(token);
		}
				 
		 return  userName;
	 }
	 
	 
	 
}
