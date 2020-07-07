package com.bazooka.jobhunter.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bazooka.jobhunter.entity.Job;
import com.bazooka.jobhunter.entity.User;
import com.bazooka.jobhunter.exceptions.ResourceNotFoundException;
import com.bazooka.jobhunter.repository.JobRepository;
import com.bazooka.jobhunter.repository.UserRepository;

@Service
public class JobServiceImpl implements JobService {
	
	@Autowired
	private JobRepository jobRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public Iterable<Job> findAll(String username) {
		User user = findUser(username);
		return jobRepository.findAllByUser(user);
	}

	@Override
	public Job findJob(long id, String username) {
		User user = findUser(username);
		return findJobByIdAndUser(id, user);
	}

	@Override
	public Job save(Job job, String username) {
		User user = findUser(username);
		job.setUser(user);
		return jobRepository.save(job);
	}

	@Override
	public void delete(long id, String username) {
		User user = findUser(username);
		Job job = findJobByIdAndUser(id, user);
		jobRepository.delete(job);
	}
	
	@Override
	public void deleteAll(String username) {
		User user = findUser(username);
		List<Job> jobs = user.getJobs();

		for (Job job : jobs) {
			jobRepository.delete(job);
		}
	}
	
	private Job findJobByIdAndUser(long id, User user) {
		Optional<Job> result = jobRepository.findByIdAndUser(id, user);
		
		if (result.isEmpty()) {
			throw new ResourceNotFoundException("Job with id " + id + " does not exist in your account");
		}
		
		return result.get();
	}
	
	private User findUser(String username) {
		Optional<User> result = userRepository.findByUsername(username);
		
		if (result.isEmpty()) {
			throw new ResourceNotFoundException("User with username " + username + " does not exist");
		}
		
		return result.get();
	}

}
