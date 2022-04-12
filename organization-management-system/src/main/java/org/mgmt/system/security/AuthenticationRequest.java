package org.mgmt.system.security;

import javax.validation.constraints.NotEmpty;

public class AuthenticationRequest {
	
	@NotEmpty
	private String username;
	
	@NotEmpty
	private String password;
	
	public AuthenticationRequest() {
	}
	
	public AuthenticationRequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
