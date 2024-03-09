package com.eZonWork.Service;

import java.net.UnknownHostException;
import java.util.List;

import com.eZonWork.Model.MasterEntity.RoleMasterBo;

import jakarta.servlet.http.HttpServletRequest;

public interface MasterService {

	List<RoleMasterBo> getAllRoles();

	RoleMasterBo saveOrUpdateRole(RoleMasterBo roleMasterBo,HttpServletRequest request)throws UnknownHostException;

	RoleMasterBo getByRoleId(Integer roleId);

	RoleMasterBo deleteRoles(Integer roleId,HttpServletRequest request)throws UnknownHostException;

}
