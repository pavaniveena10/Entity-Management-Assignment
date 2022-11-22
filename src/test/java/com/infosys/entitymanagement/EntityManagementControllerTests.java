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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.infosys.entitymanagement.controller.EntityCaseManagementController;
import com.infosys.entitymanagement.entity.Entities;
import com.infosys.entitymanagement.service.EntitiesService;

@SpringBootTest
class EntityManagementControllerTests {

	@Autowired
	private EntityCaseManagementController entityCaseManagementController;

	@MockBean
	private EntitiesService entitiesService;

	Entities entity = new Entities();

	@BeforeEach
	void setUp() {
		entity.setName("name1");
		entity.setEmail("addr1@infi.com");
		entity.setAddress("Bangalore");
		entity.setCompanyName("Infi");

	}

	@Test
	public void saveEntitiesTest() {
		Mockito.when(entitiesService.saveEntities(entity)).thenReturn(entity);
		ResponseEntity<Entities> dbresponseEntity = entityCaseManagementController.saveEntities(entity);
		assertNotNull(dbresponseEntity);
		assertEquals(200, dbresponseEntity.getStatusCode().value());
	}

	@Test
	public void fetchEntitiesTest() {
		String caseNumber = "ZA-2022-2357-ZAD-ZAA-SPP-HCA";
		List<Entities> entityList = new ArrayList<Entities>();
		entityList.add(entity);
		Mockito.when(entitiesService.fetchEntitiesByCase(caseNumber)).thenReturn(entityList);
		ResponseEntity<List<Entities>> responseEntityList = entityCaseManagementController.fetchEntities(caseNumber);
		assertNotNull(responseEntityList);
		assertEquals(200, responseEntityList.getStatusCode().value());
		assertEquals(responseEntityList.getBody().size(), entityList.size());
	}

	@Test
	public void modifyCaseEntitiesRelationTest() {
		String caseNumber = "ZA-2022-2357-ZAD-ZAA-SPP-HCA";
		entity.setId(2);
		Mockito.when(entitiesService.updatedEntityCaseRelation(caseNumber, entity.getId())).thenReturn(1);
		ResponseEntity<Integer> responseEntity = entityCaseManagementController.modifyCaseEntitiesRelation(caseNumber, entity);
		assertNotNull(responseEntity);
		assertEquals(200, responseEntity.getStatusCode().value());
		assertEquals(1, responseEntity.getBody());
	}

	// TODO More Negative test cases for code coverage

}
