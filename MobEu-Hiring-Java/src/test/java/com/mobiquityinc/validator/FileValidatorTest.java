package com.mobiquityinc.validator;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import com.mobiquityinc.exception.APIException;

/**
 * Tests for checking each behaviour related to File Validator
 * @author Andres Rios
 * */
@RunWith(MockitoJUnitRunner.class)
public class FileValidatorTest {

	@InjectMocks
	FileValidator validator;
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@Test
	public void isNotEmptyFilePath() throws APIException {
		String filePath = "/home/andres.rios/newFile.txt";
		validator.setFilePath(filePath);
		boolean result = validator.validate();
		Assert.assertTrue(result);
	}
	
	@Test
	public void isEmptyThePath() throws APIException {
	    expectedException.expect(APIException.class);
	    expectedException.expectMessage("File path is empty");
	    
		String filePath = "";
		validator.setFilePath(filePath);
		validator.validate();		
	}
	
	@Test
	public void isNullThePath() throws APIException {
	    expectedException.expect(APIException.class);
	    expectedException.expectMessage("File path is empty");
	    
		String filePath = null;
		validator.setFilePath(filePath);
		validator.validate();		
	}
	
	@Test
	public void existFile() throws APIException {
		String filePath = "/home/andres.rios/test.txt";
		validator.setFilePath(filePath);
		validator.validate();
	}
	
	@Test
	public void notExistFile() throws APIException {		
	    expectedException.expect(APIException.class);
	    expectedException.expectMessage("File doesnt exist");
		
		String filePath = "/home/webapp/aaabbbccc.txt";
		validator.setFilePath(filePath);
		validator.validate();
	}
	
	@Test
	public void isNotAFile() throws APIException {
	    expectedException.expect(APIException.class);
	    expectedException.expectMessage("File doesnt exist");
		String filePath = "/home/webapp/";
		validator.setFilePath(filePath);
		validator.validate();
	}
	
}
