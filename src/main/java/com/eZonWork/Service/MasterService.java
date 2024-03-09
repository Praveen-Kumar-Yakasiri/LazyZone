package com.eZonWork.Service;

import java.util.List;

import com.eZonWork.Model.MasterEntity.RoleMasterBo;

public interface MasterService {

	List<RoleMasterBo> getAllRoles();

	RoleMasterBo saveOrUpdateRole(RoleMasterBo roleMasterBo);

	RoleMasterBo getByRoleId(Integer roleId);

	RoleMasterBo deleteRoles(RoleMasterBo roleMasterBo);

}
