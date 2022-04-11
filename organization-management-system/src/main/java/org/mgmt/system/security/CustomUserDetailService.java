package org.mgmt.system.security;

import java.util.Arrays;
import java.util.List;

import org.mgmt.system.user.User;
import org.mgmt.system.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		List<SimpleGrantedAuthority> roles = null;
		
		User user = userRepository.findByemail(username);
		if(user != null)
		{
			roles = Arrays.asList(new SimpleGrantedAuthority(user.getRoles()));
			return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), roles);
		}
		
		throw new UsernameNotFoundException("Given user name is not found");
		
	}


}
