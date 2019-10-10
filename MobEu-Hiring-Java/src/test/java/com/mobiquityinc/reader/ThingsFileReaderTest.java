package com.mobiquityinc.reader;

import java.util.List;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import com.mobiquityinc.data.Bag;
import com.mobiquityinc.exception.APIException;

/**
 * Tests for checking each behaviour related to the file reader associted to txt files
 * @author Andres Rios
 * */
@RunWith(MockitoJUnitRunner.class)
public class ThingsFileReaderTest {

	@InjectMocks
	ThingsFileReader fileReader;
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@Test
	public void readFileSuccessfulAndGetListPackagesAndArticles() throws APIException {
		String filePath = "./src/main/resources/file.txt";
		List<Bag> packages = fileReader.read(filePath);
		Assert.assertNotNull(packages);
		Assert.assertNotNull(packages.get(0).getArticles());
	}
	
	@Test
	public void readFilehasnotRecords() throws APIException {
		String filePath = "./src/main/resources/norecords.txt";
		List<Bag> packages = fileReader.read(filePath);
		Assert.assertTrue(packages.isEmpty());
	}
	
	@Test
	public void readFilehasnotNothingInformationRelationedToTheProcess() throws APIException {
	    expectedException.expect(APIException.class);
	    expectedException.expectMessage("Error or issue reading the file");
		String filePath = "./src/main/resources/test.txt";
		fileReader.read(filePath);
	}
	
	@Test
	public void readFileWithoutStructureAccordingToManageTheInformation() throws APIException {
	    expectedException.expect(APIException.class);
	    expectedException.expectMessage("Error or issue reading the file");
		String filePath = "./src/main/resources/withoutStructure.txt";
		fileReader.read(filePath);
	}
	
	@Test
	public void readFileWithOnlyOneBagWithArticlesWithoutPrizes() throws APIException {
	    expectedException.expect(APIException.class);
	    expectedException.expectMessage("Error or issue reading the file");
		String filePath = "./src/main/resources/onlyOneBag.txt";
		fileReader.read(filePath);
	}
	
	@Test
	public void readFileWithOnlyOneBagWithArticlesWithoutIndexes() throws APIException {
	    expectedException.expect(APIException.class);
	    expectedException.expectMessage("Error or issue reading the file");
		String filePath = "./src/main/resources/onlyOneBagWithoutIndexes.txt";
		fileReader.read(filePath);
	}
	
	@Test
	public void readFileWithOnlyOneBagWithArticlesWithoutWeight() throws APIException {
	    expectedException.expect(APIException.class);
	    expectedException.expectMessage("Error or issue reading the file");
		String filePath = "./src/main/resources/onlyOneBagWithoutWeight.txt";
		fileReader.read(filePath);
	}
	
}
