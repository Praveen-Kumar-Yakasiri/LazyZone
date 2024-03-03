package com.eZonWork.Utility;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

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
	 
	 
}
