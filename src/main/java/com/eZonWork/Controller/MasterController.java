package com.eZonWork.Controller;

import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eZonWork.Model.MasterEntity.RoleMasterBo;
import com.eZonWork.Service.MasterService;
import com.eZonWork.Utility.CommonConstant;
import com.eZonWork.Utility.CommonResponse;
import com.eZonWork.Utility.CommonUtility;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/master")
public class MasterController {

	@Autowired
	private MasterService masterService;
	
	@GetMapping("/getAllRoles")
	public CommonResponse<Object> getAllRoles(){
		List<RoleMasterBo> masterBo=masterService.getAllRoles();
		return CommonResponse.of(CommonConstant.SuccessCode, CommonConstant.Success,CommonConstant.FETCHED, masterBo);
	}
	
	@GetMapping("/getByRoleId/{roleId}")
	public CommonResponse<Object> getByRoleId(@PathVariable Integer roleId){
		RoleMasterBo roleMasterBo=masterService.getByRoleId(roleId);
		return CommonResponse.of(CommonConstant.SuccessCode, CommonConstant.Success,CommonConstant.FETCHED, roleMasterBo);
	}
	
	@PostMapping("/saveOrUpdateRole")
	public CommonResponse<Object> saveOrUpdateRole(@RequestBody RoleMasterBo roleMasterBo,HttpServletRequest request) throws UnknownHostException{
		if(roleMasterBo.getCrudFlag().equals(CommonConstant.Save))
		{
			roleMasterBo.setCrtDate(new Date());
			roleMasterBo.setCrtIp(CommonUtility.getIpAddress());
			roleMasterBo.setCrtUser(CommonUtility.getUser(request));
			roleMasterBo.setStatus(CommonConstant.IS_ACTIVE);
		}else {
			roleMasterBo.setCrtDate(new Date());
			roleMasterBo.setCrtIp(CommonUtility.getIpAddress());
			roleMasterBo.setCrtUser(CommonUtility.getUser(request));
			roleMasterBo.setLstDate(new Date());
			roleMasterBo.setLstUpdIp(CommonUtility.getIpAddress());
			roleMasterBo.setLstUpdUser(CommonUtility.getUser(request));
			roleMasterBo.setStatus(CommonConstant.IS_ACTIVE);
		}
		roleMasterBo=masterService.saveOrUpdateRole(roleMasterBo);
		return CommonResponse.of(CommonConstant.SuccessCode, CommonConstant.Success,CommonConstant.SAVED, roleMasterBo);
	}
	
	
	@PostMapping("/deleteRoles/{roleId}")
	public CommonResponse<Object> deleteRoles(@PathVariable Integer roleId) throws UnknownHostException{
		RoleMasterBo roleMasterBo=new RoleMasterBo();
		roleMasterBo.setId(roleId);
		roleMasterBo.setLstDate(new Date());
		roleMasterBo.setLstUpdIp(CommonUtility.getIpAddress());
		roleMasterBo.setLstUpdUser(null);
		roleMasterBo.setStatus(CommonConstant.IS_DEAVTIVE);
		roleMasterBo=masterService.deleteRoles(roleMasterBo);
		return CommonResponse.of(CommonConstant.SuccessCode, CommonConstant.Success,CommonConstant.DELETED, roleMasterBo);
	}
	
	
	
	
}
