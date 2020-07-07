package com.bazooka.jobhunter.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bazooka.jobhunter.entity.User;
import com.bazooka.jobhunter.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> result = userRepository.findByUsername(username);
		if (result.isEmpty()) {
			throw new UsernameNotFoundException(username);
		}
		return result.get();
	}

}
