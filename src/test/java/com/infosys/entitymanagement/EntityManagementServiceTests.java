package com.infosys.entitymanagement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.infosys.entitymanagement.entity.Entities;
import com.infosys.entitymanagement.entity.EntitiesToCases;
import com.infosys.entitymanagement.exception.InvalidRequestException;
import com.infosys.entitymanagement.repository.EntitiesRepository;
import com.infosys.entitymanagement.service.EntitiesService;

@SpringBootTest
class EntityManagementServiceTests {

	@Autowired
	private EntitiesService entitiesService;

	@MockBean
	private EntitiesRepository entitiesRepository;

	Entities entity = new Entities();

	@BeforeEach
	void setUp() {
		entity.setName("name1");
		entity.setEmail("addr1@infi.com");
		entity.setAddress("Bangalore");
		entity.setCompanyName("Infi");

		Mockito.when(entitiesRepository.save(entity)).thenReturn(entity);
	}

	@Test
	public void saveEntitiesTest() {
		Mockito.when(entitiesRepository.findEntityExists(entity.getName(), entity.getEmail())).thenReturn(null);
		Entities dbEntity = entitiesService.saveEntities(entity);
		assertNotNull(dbEntity);
		assertEquals(dbEntity.getName(), entity.getName());
	}

	@Test
	public void saveEntitiesInvalidRequestTest() {
		Mockito.when(entitiesRepository.findEntityExists(entity.getName(), entity.getEmail())).thenReturn(entity);
		try {
			entitiesService.saveEntities(entity);
		} catch (InvalidRequestException ex) {
			assertEquals("Invalid_Request", ex.getErrorCode());
		}
	}
	
	@Test
	public void fetchEntitiesByCaseTest() {
		String caseNumber = "ZA-2022-2357-ZAD-ZAA-SPP-HCA";
		List<EntitiesToCases> entityToCaseList = new ArrayList<EntitiesToCases>();
		EntitiesToCases entitiesToCases = new EntitiesToCases();
		entitiesToCases.setEntityId(1);
		entitiesToCases.setCaseNumber(caseNumber);
		entityToCaseList.add(entitiesToCases);
		Mockito.when(entitiesRepository.findCaseExists(caseNumber)).thenReturn(entityToCaseList);
		List<Entities> entityList = new ArrayList<Entities>();
		entityList.add(entity);
		Mockito.when(entitiesRepository.findEntitiesByCase(caseNumber)).thenReturn(entityList);
		List<Entities> dbEntityList = entitiesService.fetchEntitiesByCase(caseNumber);
		assertNotNull(dbEntityList);
		assertEquals(dbEntityList.size(), entityList.size());
	}
	
	@Test
	public void updatedEntityCaseRelationTest() {
		String caseNumber = "ZA-2022-2357-ZAD-ZAA-SPP-HCA";
		List<EntitiesToCases> entityToCaseList = new ArrayList<EntitiesToCases>();
		EntitiesToCases entitiesToCases = new EntitiesToCases();
		entitiesToCases.setEntityId(1);
		entitiesToCases.setCaseNumber(caseNumber);
		entityToCaseList.add(entitiesToCases);
		Mockito.when(entitiesRepository.findCaseExists(caseNumber)).thenReturn(entityToCaseList);
		Mockito.when(entitiesRepository.updateEntityCaseRelation(2, caseNumber)).thenReturn(1);
		Integer count = entitiesService.updatedEntityCaseRelation(caseNumber, 2);
		assertNotNull(count);
		assertEquals(1, count);
	}

	//TODO More Negative test cases for code coverage
	
}
