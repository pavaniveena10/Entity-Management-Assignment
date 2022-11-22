package com.infosys.entitymanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.entitymanagement.entity.Entities;
import com.infosys.entitymanagement.exception.ApplicationRuntimeException;
import com.infosys.entitymanagement.exception.InvalidRequestException;
import com.infosys.entitymanagement.service.EntitiesService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/entitycases")
@Slf4j
public class EntityCaseManagementController {
	
	@Autowired
	private EntitiesService entitiesService;
	
	@PostMapping("/entities")
	public ResponseEntity<Entities> saveEntities(@RequestBody Entities entities) {
		log.info("Receved request to add Entity");
		try {
			return new ResponseEntity<Entities>(entitiesService.saveEntities(entities), HttpStatus.OK);
		} catch(InvalidRequestException ex) {
			throw ex;
		} catch(Exception ex) {
			throw new ApplicationRuntimeException("Internal_Server_Error", "Server is tempararly unavalable");
		}
	}
	
	@GetMapping("/cases/{caseNumber}/entities")
	public ResponseEntity<List<Entities>> fetchEntities(@PathVariable String caseNumber) {
		log.info("Receved request to fetchEntitiesByCase");
		try {
			return new ResponseEntity<List<Entities>>(entitiesService.fetchEntitiesByCase(caseNumber), HttpStatus.OK);
		} catch(InvalidRequestException ex) {
			throw ex;
		} catch(Exception ex) {
			throw new ApplicationRuntimeException("Internal_Server_Error", "Server is tempararly unavalable");
		}

	}
	
	@PostMapping("/cases/{caseNumber}/entities")
	public ResponseEntity<Integer> modifyCaseEntitiesRelation(@PathVariable String caseNumber, @RequestBody Entities entities) {
		log.info("Receved request to modify the relation between a case and entities");
		return new ResponseEntity<Integer>(entitiesService.updatedEntityCaseRelation(caseNumber, entities.getId()), HttpStatus.OK);
	}

}
