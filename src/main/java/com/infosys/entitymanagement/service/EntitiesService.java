package com.infosys.entitymanagement.service;

import java.util.List;

import com.infosys.entitymanagement.entity.Entities;

public interface EntitiesService {
	
	public Entities saveEntities(Entities entities);
	
	public List<Entities> fetchEntitiesByCase(String caseValue);
	
	public Integer updatedEntityCaseRelation(String caseValue, Integer entityId);

}
