package com.bazooka.jobhunter.service;

import com.bazooka.jobhunter.entity.User;

public interface UserService {
	
	public User save(User user);
	
	public User update(User user, String username);
	
	void delete(String username);

}
