package com.skilldistillery.jobtracker.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JobTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Job job;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("JobTrackerPU");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		job = em.find(Job.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		job = null;
		em.close();
	}


	@Test
	void test() {
		assertNotNull(job);
		assertEquals("Software Developer", job.getTitle());
		assertEquals("Woodridge Software", job.getCompany());
		assertEquals("Golden, CO", job.getLocation());
		assertTrue(job.isCoverLetterSubmitted());
		assertEquals("Kaj Gronholm", job.getContactPerson());
		assertEquals(null, job.getNotes());
		assertEquals(Status.PENDING, job.getStatus());
	}
	
	@Test
	void test_relationship_mapping_User() {
		assertNotNull(job);
		assertEquals("Toni", job.getUser().getFirstName());
	}

}
