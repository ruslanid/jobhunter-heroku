package com.bazooka.jobhunter.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bazooka.jobhunter.entity.Job;
import com.bazooka.jobhunter.service.JobService;
import com.bazooka.jobhunter.service.EntityValidationService;

@RestController
@RequestMapping("/api")
public class JobController {
	
	@Autowired
	private JobService jobService;
	
	@Autowired
	private EntityValidationService entityValidationService;
	
	@GetMapping("/jobs")
	public Iterable<Job> getAll(Principal principal) {
		return jobService.findAll(principal.getName());
	}
	
	@GetMapping("/jobs/{jobId}")
	public ResponseEntity<Job> getJob(@PathVariable long jobId, Principal principal) {
		Job job = jobService.findJob(jobId, principal.getName());
		return ResponseEntity.ok().body(job);
	}
	
	@PostMapping("/jobs")
	public ResponseEntity<?> addJob(
			@Valid @RequestBody Job job, BindingResult result, Principal principal) {

		if (result.hasErrors()) {
			return entityValidationService.validateFields(result);
		} else {
			job.setId(0);
			Job newJob = jobService.save(job, principal.getName());
			return ResponseEntity.ok().body(newJob);
		}
	}

	@PutMapping("/jobs")
	public ResponseEntity<?> updateJob(
			@Valid @RequestBody Job job, BindingResult result, Principal principal) {

		if (result.hasErrors()) {
			return entityValidationService.validateFields(result);
		} else {
			Job updatedJob = jobService.save(job, principal.getName());
			return ResponseEntity.ok().body(updatedJob);
		}
	}
	
	@DeleteMapping("/jobs/{jobId}")
	public ResponseEntity<String> deleteJob(@PathVariable long jobId, Principal principal) {
		jobService.delete(jobId, principal.getName());
		return ResponseEntity.ok().body("Job has been deleted.");
	}
	
	@DeleteMapping("/jobs/all")
	public ResponseEntity<String> deleteAllJobs(Principal principal) {
		jobService.deleteAll(principal.getName());
		return ResponseEntity.ok().body("All jobs have been deleted.");
		
	}
}
