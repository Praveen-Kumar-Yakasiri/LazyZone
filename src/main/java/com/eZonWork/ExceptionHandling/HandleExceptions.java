package com.eZonWork.ExceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.eZonWork.Utility.CommonConstant;
import com.eZonWork.Utility.CommonResponse;

import io.jsonwebtoken.ExpiredJwtException;




@RestControllerAdvice
public class HandleExceptions  {
   
	    @ExceptionHandler(ExpiredJwtException.class)
	    public ResponseEntity<CommonResponse<Object>> handleExpiredTokenException(ExpiredJwtException ex) {
	    	System.err.println("handled exception");
	    	
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(CommonResponse.of(CommonConstant.UnauthorizedCode, CommonConstant.Error, CommonConstant.JWTEXPIRATION));
	    }
	    
	    
	    @ExceptionHandler(AccessDeniedException.class)
	    public ResponseEntity<CommonResponse<Object>> handleAccesDenied(AccessDeniedException ex) {
	    	System.err.println("handled exception");
	        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(CommonResponse.of(String.valueOf(HttpStatus.FORBIDDEN.value()), HttpStatus.FORBIDDEN.toString().substring(4), ex.getMessage(), null));
	    }
	    
	    @ExceptionHandler(Exception.class)
	    public ResponseEntity<String> handleException(Exception ex) {
	        // Handle other exceptions
	    	System.err.println("handled exception");
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
	    }
}
