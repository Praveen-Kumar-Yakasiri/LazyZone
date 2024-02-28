package com.eZonWork.Utility;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponse<T> {

	private String statusCode;
	
	private String statusMessage;
	
	private String message;
	
	private String errorMessage;
	
	private T data;
	
	private String timeStamp;
	
	
	@SuppressWarnings("unchecked")
	public static <T> CommonResponse<T> of(String successCode,String statusMessage, String message, String token) {
	        CommonResponse<T> response = new CommonResponse<>();
	        response.setStatusCode(successCode);
	        response.setStatusMessage(statusMessage);
	        response.setMessage(message);
	        response.setData((T) token);
	        response.setTimeStamp(CommonUtility.timeStamp());
	        return response;
	    }
	
	
	public static <T> CommonResponse<T> of(String statusCode, String statusMessage, String message,String errorMessage,T data) {
	        CommonResponse<T> response = new CommonResponse<>();
	        response.setStatusCode(statusCode); 
	        response.setStatusMessage(statusMessage);
	        response.setMessage(message);
	        response.setErrorMessage(errorMessage);
	        response.setData(data);
	        if(!statusCode.equals("200")) {
	        	response.setTimeStamp(CommonUtility.timeStamp());
	        }
	        
	        return response;
	    }
}
