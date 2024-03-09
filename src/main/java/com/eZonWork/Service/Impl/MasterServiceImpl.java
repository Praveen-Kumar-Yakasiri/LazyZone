package com.eZonWork.Service.Impl;

import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eZonWork.Model.MasterEntity.RoleMasterBo;
import com.eZonWork.Repo.MasterRepo.RoleMasterRepo;
import com.eZonWork.Service.MasterService;
import com.eZonWork.Utility.CommonConstant;
import com.eZonWork.Utility.CommonUtility;

import jakarta.servlet.http.HttpServletRequest;

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
	public RoleMasterBo saveOrUpdateRole(RoleMasterBo roleMasterBo,HttpServletRequest request) throws UnknownHostException {
		// TODO Auto-generated method stub
		if(roleMasterBo.getCrudFlag().equals(CommonConstant.Save))
		{
			roleMasterBo.setCrtDate(new Date());
			roleMasterBo.setCrtIp(CommonUtility.getIpAddress());
			roleMasterBo.setCrtUser(CommonUtility.getUser(request));
			roleMasterBo.setStatus(CommonConstant.IS_ACTIVE);
		}else {
			roleMasterBo=masterRepo.findById(roleMasterBo.getId()).orElseThrow();
			roleMasterBo.setCrtDate(roleMasterBo.getCrtDate());
			roleMasterBo.setCrtIp(roleMasterBo.getCrtIp());
			roleMasterBo.setCrtUser(roleMasterBo.getCrtUser());
			roleMasterBo.setLstDate(new Date());
			roleMasterBo.setLstUpdIp(CommonUtility.getIpAddress());
			roleMasterBo.setLstUpdUser(CommonUtility.getUser(request));
			roleMasterBo.setStatus(CommonConstant.IS_ACTIVE);
		}
		return masterRepo.save(roleMasterBo);
	}

	@Override
	public RoleMasterBo getByRoleId(Integer roleId) {
		// TODO Auto-generated method stub
		return masterRepo.findById(roleId).orElseThrow();
	}

	@Override
	public RoleMasterBo deleteRoles(Integer roleId,HttpServletRequest request) throws UnknownHostException {
		// TODO Auto-generated method stub
		RoleMasterBo roleMasterBo=masterRepo.findById(roleId).orElseThrow();
		roleMasterBo.setCrtDate(roleMasterBo.getCrtDate());
		roleMasterBo.setCrtIp(roleMasterBo.getCrtIp());
		roleMasterBo.setCrtUser(roleMasterBo.getCrtUser());
		roleMasterBo.setLstDate(new Date());
		roleMasterBo.setLstUpdIp(CommonUtility.getIpAddress());
		roleMasterBo.setLstUpdUser(CommonUtility.getUser(request));
		roleMasterBo.setStatus(CommonConstant.IS_DEAVTIVE);
		return masterRepo.save(roleMasterBo);
	}

}
