package com.bazooka.jobhunter.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bazooka.jobhunter.entity.User;
import com.bazooka.jobhunter.exceptions.ResourceNotFoundException;
import com.bazooka.jobhunter.exceptions.UsernameAlreadyExistsException;
import com.bazooka.jobhunter.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public User save(User user) {
		
		try {
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			return userRepository.save(user);
		} catch (Exception e) {
			throw new UsernameAlreadyExistsException("An account with this email already exists");
		}

	}

	@Override
	public User update(User user, String username) {
		User dbUser = findUser(username);
		user.setPassword(dbUser.getPassword());
		return userRepository.save(user);
	}
	
	@Override
	public void delete(String username) {
		User user = findUser(username);
		userRepository.delete(user);
	}
	
	private User findUser(String username) {
		Optional<User> result = userRepository.findByUsername(username);
		
		if (result.isEmpty()) {
			throw new ResourceNotFoundException("User with username " + username + " does not exist");
		}
		
		return result.get();
	}

}