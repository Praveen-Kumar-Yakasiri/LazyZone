package com.eZonWork.Controller;

import java.net.UnknownHostException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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


	@GetMapping("/getAllRegister")
	public CommonResponse<List<UserInfo>> getAllRegister() {
		return commonService.getAllRegister();
	}

	@PostMapping("/saveUser")
	public CommonResponse<UserInfo> saveOrUpdateUser(@RequestBody UserInfo userDetails) throws UnknownHostException {

		return commonService.saveOrUpdateUser(userDetails);

	}

	@PostMapping("/updateUser")
	public CommonResponse<UserInfo> updateUser(@RequestBody UserInfo userDetail) throws UnknownHostException {
		return commonService.updateUser(userDetail);
	}

	@GetMapping("/getUserById/{userId}")
	public CommonResponse<UserInfo> getUserById(@PathVariable("userId") Integer userId) {
		return commonService.getUserById(userId);
	}

	@DeleteMapping("/deleteUserById/{userId}")
	public CommonResponse<UserInfo> deleteUserById(@PathVariable("userId") Integer userId) {
		return commonService.deleteUserById(userId);
	}

}
