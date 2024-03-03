package com.eZonWork.Controller;

import java.net.UnknownHostException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eZonWork.Model.UserInfo;

import com.eZonWork.Service.CommonService;
import com.eZonWork.Utility.CommonResponse;

@RestController
@RequestMapping("/common")
public class CommonController {

	@Autowired
	private CommonService commonService;
	
	
	
	@GetMapping("/loginAsUser")
	@PreAuthorize("hasAuthority('USER')")
	public String loginAsUser() {
		
		return "Welcome to the User...!";
	}
	
	
	@GetMapping("/loginAsAdmin")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String loginAsAdmin() {
		
		return "Welcome to the Admin...!";
	}
	
	@PostMapping("/saveOrUpdateUser")
	public CommonResponse<UserInfo> saveOrUpdateUser(@RequestBody UserInfo userDetails) throws UnknownHostException {
		
		return commonService.saveOrUpdateUser(userDetails);
		
	}
	
	@GetMapping("/getUserById/{userId}")
	@PreAuthorize("hasAuthority('USER')")
	public CommonResponse<UserInfo> getUserById(@PathVariable("userId") Integer userId){
		return commonService.getUserById(userId);
	}
	
	@DeleteMapping("/deleteUserById/{userId}")
	@PreAuthorize("hasAuthority('USER')")
	public CommonResponse<UserInfo> deleteUserById(@PathVariable("userId") Integer userId){
		return commonService.deleteUserById(userId);
	}
	
	
	
	
	
	
	
}
