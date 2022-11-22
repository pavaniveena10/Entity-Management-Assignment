package com.infosys.entitymanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infosys.entitymanagement.entity.Entities;
import com.infosys.entitymanagement.entity.EntitiesToCases;
import com.infosys.entitymanagement.exception.InvalidRequestException;
import com.infosys.entitymanagement.repository.EntitiesRepository;
import com.infosys.entitymanagement.validator.RequestValidator;

@Service
public class EntitiesServiceImpl implements EntitiesService {
	
	@Autowired
	private EntitiesRepository entitiesRepository;
	
	@Autowired
	private RequestValidator requestValidator;

	@Override
	public Entities saveEntities(Entities entities) {
		if (!requestValidator.isValidEntityRequest(entities)) {
			throw new InvalidRequestException("Invalid_Request", "Mandatory elements are not provided");
		}
		Entities entity = entitiesRepository.findEntityExists(entities.getName(), entities.getEmail());
		if (null != entity) {
			throw new InvalidRequestException("Invalid_Request", "Entity already exists in system");
		}
		return entitiesRepository.save(entities);
	}

	@Override
	public List<Entities> fetchEntitiesByCase(String caseNumber) {
		
		List<EntitiesToCases> entitiesToCases = entitiesRepository.findCaseExists(caseNumber);
		if (null == entitiesToCases || entitiesToCases.isEmpty()) {
			throw new InvalidRequestException("Invalid_Request", "Case doesnot exists");
		}
		return entitiesRepository.findEntitiesByCase(caseNumber);
	}

	@Override
	public Integer updatedEntityCaseRelation(String caseNumber, Integer entityId) {
		List<EntitiesToCases> entitiesToCases = entitiesRepository.findCaseExists(caseNumber);
		if (null == entitiesToCases || entitiesToCases.isEmpty()) {
			throw new InvalidRequestException("Invalid_Request", "Case doesnot exists");
		}
		return entitiesRepository.updateEntityCaseRelation(entityId, caseNumber);
	}

}
