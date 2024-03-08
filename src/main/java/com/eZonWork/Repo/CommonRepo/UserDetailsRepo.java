package com.eZonWork.Repo.CommonRepo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eZonWork.Model.CommonEntity.UserInfo;

public interface UserDetailsRepo  extends JpaRepository<UserInfo, Integer>{

	Optional<UserInfo> findByUserName(String username);

	List<UserInfo> findByStatus(String isActive);

	

}
