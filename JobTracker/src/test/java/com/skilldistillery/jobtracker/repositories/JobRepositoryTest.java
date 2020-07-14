package com.skilldistillery.jobtracker.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.skilldistillery.jobtracker.entities.Job;

@SpringBootTest
class JobRepositoryTest {
	
	@Autowired
	private JobRepository jobRepo;

	@Test
	void test_find_all() {
		List<Job> jobs = jobRepo.findAll();
		assertNotNull(jobs);
		assertTrue(jobs.size() > 0);
	}
	
	@Test
	void test_find_by_id() {
		Optional<Job> jobOpt = jobRepo.findById(1);
		Job job = jobOpt.get();
		assertEquals("Software Developer", job.getTitle());	
	}
	
	@Test
	void test_custom_method_for_user_jobs() {
		List<Job> jobs = jobRepo.findByUser_Id(1);
		assertNotNull(jobs);
		assertTrue(jobs.size() > 0);
		assertEquals("Software Developer", jobs.get(0).getTitle());	
	}

}
