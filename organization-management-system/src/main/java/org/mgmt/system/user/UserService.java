package org.mgmt.system.user;

import org.mgmt.system.exceptionhandler.RecordAlreadyPresentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	
	public UserService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	public User save(UserRegistrationDto registrationDto) {
		try
		{
		userRepository.findByemail(registrationDto.getEmail());
		User user = new User(registrationDto.getFirstName(), 
				registrationDto.getLastName(), registrationDto.getEmail(),
				passwordEncoder.encode(registrationDto.getPassword()), registrationDto.getRoles());
		
		return userRepository.save(user);
		}
		catch(DataIntegrityViolationException e)
		{
			throw new RecordAlreadyPresentException(
					"User with given emailId: " +registrationDto.getEmail() + " is already present");
		}
	}

}
