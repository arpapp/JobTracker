package com.skilldistillery.jobtracker.services;

import java.util.List;

import com.skilldistillery.jobtracker.entities.Job;

public interface JobService {
	Job findById(int id);
	List<Job> findAllJobs();
	List<Job> findAllJobsForUser(int userId);
}
