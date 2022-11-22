package com.infosys.entitymanagement.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.infosys.entitymanagement.entity.Entities;
import com.infosys.entitymanagement.entity.EntitiesToCases;

@Repository
public interface EntitiesRepository extends JpaRepository<Entities, Integer>{
	
	@Query(value = "select e from Entities e where id in (select entityId from EntitiesToCases where caseNumber = :caseNumber)")
	public List<Entities> findEntitiesByCase(String caseNumber);
	
	@Transactional
	@Modifying
	@Query(value = "update EntitiesToCases set entityId = :entityId where caseNumber = :caseNumber")
	public Integer updateEntityCaseRelation(Integer entityId, String caseNumber);
	
	@Query(value = "select e from Entities e where name = :name and email = :email")
	public Entities findEntityExists(String name, String email);
	
	@Query(value = "select e from EntitiesToCases e where caseNumber = :caseNumber")
	public List<EntitiesToCases> findCaseExists(String caseNumber);
	
}
