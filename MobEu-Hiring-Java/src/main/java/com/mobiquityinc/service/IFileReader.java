package com.mobiquityinc.service;

import java.util.List;

import com.mobiquityinc.data.Bag;
import com.mobiquityinc.exception.APIException;

/**
 * Interface with the services relationed to read the files.
 * 
 * @author Andres Rios
 * */
public interface IFileReader {
	
	/**
	 * Method to read the files and get the bags with their articles
	 * 
	 * @author Andres Rios
	 * 
	 * @param filePath, absolute path of the file that should be processed.
	 * 
	 * @return list with the bags of the file.
	 * */
	public List<Bag> read(String filePath) throws APIException;
	
}
