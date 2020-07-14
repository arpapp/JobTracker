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
		if (jobOpt.isPresent()) {
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

	@Override
	public Job updateJob(int jobId, Job job) {
		Job managedJob = null;
		Optional<Job> jobOpt = jobRepo.findById(jobId);
		if (jobOpt.isPresent()) {
			managedJob = jobOpt.get();
			managedJob.setCompany(job.getCompany());
			managedJob.setContactPerson(job.getContactPerson());
			managedJob.setCoverLetterSubmitted(job.isCoverLetterSubmitted());
			managedJob.setDateApplied(job.getDateApplied());
			managedJob.setLocation(job.getLocation());
			managedJob.setNotes(job.getNotes());
			managedJob.setStatus(job.getStatus());
			managedJob.setTitle(job.getTitle());
			jobRepo.saveAndFlush(managedJob);
		}
		return managedJob;
	}

	@Override
	public boolean deleteJob(int jobId) {
		Optional<Job> jobOpt = jobRepo.findById(jobId);
		if (jobOpt.isPresent()) {
			Job managedJob = jobOpt.get();
			jobRepo.delete(managedJob);
			if(managedJob == null) {
				return true;
			}
		}
		return false;
	}

}
