package org.mgmt.system.user;

import org.mgmt.system.security.Authentication;
import org.mgmt.system.security.AuthenticationRequest;
import org.mgmt.system.security.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private Authentication authentication;

	
	@PostMapping(value="/registerUser", produces = "application/xml")
	public ResponseEntity<User> registerUser(@RequestBody UserRegistrationDto userRegistrationDto)
	{
		return new ResponseEntity<User>(userService.save(userRegistrationDto), HttpStatus.CREATED);
	}
	
	
	@PostMapping("/authenticateUser")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest request) throws Exception
	{
		String jwt = authentication.doAuthentication(request);
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
		
	}

}
