package com.eZonWork.Utility;

import java.text.SimpleDateFormat;


public class CommonConstant {

	//AUTHOR PRAVEEN KUMAR
	
	public final static String Success="SUCCESS";
	
	public final static String Error="ERROR";
	
	public final static String Save="Save";
	
	public final static String Update="Update";
	
	public final static String Delete="Delete";
	
	public final static String SuccessCode="200";
	
	public final static String ErrorCode="500";
	
	public final static String UnauthorizedCode="401";
	
	public final static String FORBIDDEN="403";
	
	public final static String JWTEXPIRATION="JWT Token Is Expired. Please Login Agian.....!";
	
	
	//Added by PRAVEEN KUMAR 03-03-2024
	//DESC: Status for Active AND DeActive
	public final static String IS_ACTIVE="1";
	public final static String IS_DEAVTIVE="0";
	
	
	// DATE FORMATER ADDED By PRAVEEN KUMAR 03-03-2024
	
	public final static SimpleDateFormat DATE_FORMATER_WITH_TIME=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	
	public final static SimpleDateFormat DATE_FORMATER_WITHOUT_TIME=new SimpleDateFormat("dd-MM-yyyy");
	
	
	public final static String FETCHED="Details Fetched Successfully..!";
	public final static String SAVED="Details Saved Successfully..!";
	public final static String DELETED="Details Deleted Successfully..!";

	
	
}
