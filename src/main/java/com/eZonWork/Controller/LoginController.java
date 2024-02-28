package com.eZonWork.Controller;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eZonWork.Dto.UserLogin;
import com.eZonWork.JwtService.JwtService;
import com.eZonWork.Utility.AuthResponse;
import com.eZonWork.Utility.CommonConstant;
import com.eZonWork.Utility.CommonResponse;

@RestController
@RequestMapping("/log")
public class LoginController {
	
	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private JwtService jwtService;
	
	
	@PostMapping("/loginUser")
	public CommonResponse<AuthResponse> login(@RequestBody UserLogin login){
		String token=null;
		Authentication authentication=manager.authenticate(new UsernamePasswordAuthenticationToken(login.getUserName(), login.getPassWord()));
		if(authentication.isAuthenticated()) {
			token=jwtService.generateToken(login.getUserName());
		}
		AuthResponse authResponse=new AuthResponse();
		authResponse.setAccessToken(token);
		return CommonResponse.of(CommonConstant.SuccessCode, CommonConstant.Success,"Token Generated Successfully", null, authResponse);
	}

}