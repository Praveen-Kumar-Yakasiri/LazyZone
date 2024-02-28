package com.eZonWork.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import com.eZonWork.Model.UserInfo;

public interface UserDetailsRepo  extends JpaRepository<UserInfo, Integer>{

	Optional<UserInfo> findByUserName(String username);

	

}
