package com.eZonWork.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eZonWork.Model.MasterEntity.RoleMasterBo;
import com.eZonWork.Repo.MasterRepo.RoleMasterRepo;
import com.eZonWork.Service.MasterService;
import com.eZonWork.Utility.CommonConstant;

@Service
public class MasterServiceImpl implements MasterService {
	
	@Autowired
	private RoleMasterRepo masterRepo;

	@Override
	public List<RoleMasterBo> getAllRoles() {
		// TODO Auto-generated method stub
		return masterRepo.findByStatus(CommonConstant.IS_ACTIVE);
	}

	@Override
	public RoleMasterBo saveOrUpdateRole(RoleMasterBo roleMasterBo) {
		// TODO Auto-generated method stub
		return masterRepo.save(roleMasterBo);
	}

	@Override
	public RoleMasterBo getByRoleId(Integer roleId) {
		// TODO Auto-generated method stub
		return masterRepo.findById(roleId).orElseThrow();
	}

	@Override
	public RoleMasterBo deleteRoles(RoleMasterBo roleMasterBo) {
		// TODO Auto-generated method stub
		roleMasterBo=masterRepo.findById(roleMasterBo.getId()).orElseThrow();
		return masterRepo.save(roleMasterBo);
	}

}
