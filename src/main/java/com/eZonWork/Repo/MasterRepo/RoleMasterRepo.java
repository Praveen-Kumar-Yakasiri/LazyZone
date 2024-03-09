package com.eZonWork.Repo.MasterRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eZonWork.Model.MasterEntity.RoleMasterBo;

public interface RoleMasterRepo extends JpaRepository<RoleMasterBo, Integer>{

	List<RoleMasterBo> findByStatus(String isActive);

}
