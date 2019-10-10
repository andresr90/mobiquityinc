package com.mobiquityinc.packer;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.mobiquityinc.exception.APIException;

import junit.framework.Assert;

@RunWith(MockitoJUnitRunner.class)
public class PackerTest {
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@Test
	public void packerGetResultsNotNull() throws APIException {		
		String result = Packer.pack("/home/andres.rios/file.txt");
		Assert.assertNotNull(result);
	}
	
	@Test
	public void packerGetResultsNull() throws APIException {
	    expectedException.expect(APIException.class);
		Packer.pack("/home/andres.rios/test.txt");
	}
	
	@Test
	public void packerGetResultsWithARecordWithIndexFour() throws APIException {
		String result = Packer.pack("/home/andres.rios/file.txt");
		Assert.assertTrue(result.contains("4,"));
	}
	
	@Test
	public void packerGetResultsWithAnRecordsWithIndexesEightAndNine() throws APIException {
		String result = Packer.pack("/home/andres.rios/file.txt");
		Assert.assertTrue(result.contains("8,9,"));
	}
	
	@Test
	public void packerGetResultWithARecordWithIndexOneLineThisMeanBaghasnotArticles() throws APIException {
		String result = Packer.pack("/home/andres.rios/file.txt");
		Assert.assertTrue(result.contains("-"));
	}
	
}
