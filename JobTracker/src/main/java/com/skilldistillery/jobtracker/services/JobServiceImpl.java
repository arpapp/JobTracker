package com.skilldistillery.jobtracker.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.jobtracker.entities.Job;
import com.skilldistillery.jobtracker.repositories.JobRepository;

@Service
public class JobServiceImpl implements JobService {
	
	@Autowired
	private JobRepository jobRepo;

	@Override
	public Job findById(int id) {
		Job job = null;
		Optional<Job> jobOpt = jobRepo.findById(id);
		if(jobOpt.isPresent()) {
			job = jobOpt.get();
		}
		return job;
	}

	@Override
	public List<Job> findAllJobs() {
		return jobRepo.findAll();
	}

	@Override
	public List<Job> findAllJobsForUser(int userId) {
		return jobRepo.findByUser_Id(userId);
	}

}
