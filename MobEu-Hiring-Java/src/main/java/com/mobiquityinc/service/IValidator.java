package com.mobiquityinc.service;

import com.mobiquityinc.exception.APIException;

/**
 * Service interface with the service to validate information of a object
 * or business object
 * */
public interface IValidator {
	
	/**
	 * Service to validate the information 
	 * 
	 * @return true - is valid, False - is not valid
	 * */
	public boolean validate() throws APIException; 
	
}
