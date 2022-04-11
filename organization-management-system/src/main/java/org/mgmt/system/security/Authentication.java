package org.mgmt.system.security;


import org.mgmt.system.exceptionhandler.InvalidCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class Authentication {
	
	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private UserDetailsService userDetailService;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	public String doAuthentication(AuthenticationRequest request) throws Exception
	{
		try
		{
	     	manager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		}
		catch(BadCredentialsException e)
		{
			throw new InvalidCredentialsException("Username and/or Password are incorrect");
		}
		
		final UserDetails userDetails = userDetailService.loadUserByUsername(request.getUsername());
		final String jwt = jwtUtils.generateToken(userDetails);
		return jwt;
	}
	

}
