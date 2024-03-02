package com.eZonWork.Service;


import java.net.UnknownHostException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.eZonWork.Model.UserInfo;
import com.eZonWork.Repo.UserDetailsRepo;
import com.eZonWork.Utility.CommonConstant;
import com.eZonWork.Utility.CommonResponse;
import com.eZonWork.Utility.CommonUtility;

@Service
public class CommonServiceImpl implements CommonService {
  @Autowired
  private UserDetailsRepo detailsRepo;
  
  @Autowired
  private PasswordEncoder  encoder;
  

@Override
public CommonResponse<UserInfo> saveOrUpdateUser(UserInfo userDetails) throws UnknownHostException {
	
	try {
		if(userDetails.getCrudFlag().equals(CommonConstant.Save)) {
			userDetails.setCrtDate(new Date());
			userDetails.setCrtIp(CommonUtility.getIpAddress());
			userDetails.setCrtUser(userDetails.getUserName());
			userDetails.setPassWord(encoder.encode(userDetails.getPassWord()));
		
		}else {
			userDetails.setCrtDate(new Date());
			userDetails.setCrtIp(CommonUtility.getIpAddress());
			userDetails.setCrtUser(userDetails.getUserName());
			userDetails.setLstDate(new Date());
			userDetails.setLstIp(CommonUtility.getIpAddress());
			userDetails.setLstUser(userDetails.getUserName());
			userDetails.setPassWord(encoder.encode(userDetails.getPassWord()));
			
		}
		userDetails= detailsRepo.save(userDetails);
		return CommonResponse.of(CommonConstant.SuccessCode,CommonConstant.Success,"Data Saved Successfully",userDetails);
	}catch(Exception ex) {
		
		return CommonResponse.of(CommonConstant.ErrorCode,CommonConstant.Error,"Data Saved UnSuccessful",ex.getMessage());
	}	
	
}


@Override
public CommonResponse<UserInfo> getUserById(Integer userId) {
	try {
		UserInfo userInfo=detailsRepo.findById(userId).get();
		
			return CommonResponse.of(CommonConstant.SuccessCode,CommonConstant.Success,"Data Fetched Successfully",userInfo);
	
        }catch(Exception ex) {
	        return CommonResponse.of(CommonConstant.ErrorCode,CommonConstant.Error,"Data Fetched UnSuccessful",ex.getMessage());
	   }
	
	
    }


@Override
public CommonResponse<UserInfo> deleteUserById(Integer userId) {
	
		   CommonResponse<UserInfo> response=getUserById(userId);
		   if(response.getStatusCode().equals("200")) {
			   detailsRepo.deleteById(userId);
				
				return CommonResponse.of(CommonConstant.SuccessCode,CommonConstant.Success,"Data Deleted Successfully",null);
		   }else {
			   return CommonResponse.of(CommonConstant.ErrorCode,CommonConstant.Error,"Data Deleted UnSuccessful");
		   }
		    
	        
	   
}
}
