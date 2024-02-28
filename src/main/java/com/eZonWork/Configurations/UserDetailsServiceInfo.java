package com.eZonWork.Configurations;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.eZonWork.Model.UserInfo;
import com.eZonWork.Repo.UserDetailsRepo;

@Component
public class UserDetailsServiceInfo implements UserDetailsService {
	
	@Autowired
	private UserDetailsRepo repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserInfo> service=repo.findByUserName(username);
		return service.map(UserDetailsInfo::new)
				.orElseThrow(()->new UsernameNotFoundException("User Not Found...!"));
	}

}
