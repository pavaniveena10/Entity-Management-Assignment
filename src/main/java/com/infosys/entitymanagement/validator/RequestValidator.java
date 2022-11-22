package com.infosys.entitymanagement.validator;

import org.springframework.stereotype.Component;

import com.infosys.entitymanagement.entity.Entities;

@Component
public class RequestValidator {

	public boolean isValidEntityRequest(Entities entities) {
		if (null == entities) {
			return false;
		}
		if (entities.getName() == null || entities.getAddress() == null || entities.getEmail() == null
				|| entities.getCompanyName() == null) {
			return false;
		}

		return true;
	}

}
