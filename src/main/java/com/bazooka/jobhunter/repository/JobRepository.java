package com.bazooka.jobhunter.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bazooka.jobhunter.entity.Job;
import com.bazooka.jobhunter.entity.User;

@Repository
public interface JobRepository extends CrudRepository<Job, Long> {

	Iterable<Job> findAllByUser(User user);
	
	Optional<Job> findByIdAndUser(long id, User user);

}