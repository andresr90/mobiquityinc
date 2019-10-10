package com.mobiquityinc.validator;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import com.mobiquityinc.data.Article;
import com.mobiquityinc.exception.APIException;

/**
 * Tests for checking each data related to each record of a file
 * @author Andres Rios
 * */
@RunWith(MockitoJUnitRunner.class)
public class ThingValidatorTest {
    
	@InjectMocks
	ThingValidator validator;
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();	
	
	@Test
	public void hasInformationTheRecord() throws APIException {
		Article thing = new Article();
		thing.setIndex(1);
		thing.setWeight(43.56);
		thing.setPrize(5.8);
		validator.setThing(thing);
		boolean result = validator.validate();
		Assert.assertTrue(result);
	} 	
	
	@Test
	public void doesntSendTheThingForTheValidator() throws APIException {
	    expectedException.expect(APIException.class);
	    expectedException.expectMessage("Doesnt exist information of the thing");
		validator.validate();	
	}
	
	@Test
	public void theItemHasNotIndex() throws APIException {
	    expectedException.expect(APIException.class);
	    expectedException.expectMessage("The thing hasnt index");
		Article thing = new Article();
		validator.setThing(thing);
		validator.validate();
	}
	
	@Test
	public void theItemHasNotWeight() throws APIException {
	    expectedException.expect(APIException.class);
	    expectedException.expectMessage("The thing hasnt weight");
		Article thing = new Article();
		thing.setIndex(1);
		validator.setThing(thing);
		validator.validate();
	}
	
	@Test
	public void theItemHasNotPrize() throws APIException {
	    expectedException.expect(APIException.class);
	    expectedException.expectMessage("The thing hasnt prize");
		Article thing = new Article();
		thing.setIndex(1);
		thing.setWeight(34.76);;
		validator.setThing(thing);
		validator.validate();
	}
	
	@Test
	public void theItemHasntAnyFields() throws APIException {
	    expectedException.expect(APIException.class);
	    expectedException.expectMessage("The thing hasnt index");
		Article thing = new Article();
		validator.setThing(thing);
		validator.validate();
	}
	
	
	@Test
	public void theArticleHasAWeightMoreThanMaximunAllowed() throws APIException {
		Article thing = new Article();
		thing.setIndex(1);
		thing.setWeight(110.0);
		thing.setPrize(5.8);
		validator.setThing(thing);
		boolean result = validator.validate();
		Assert.assertFalse(result);
	}
	
	@Test
	public void theArticleHasAPrizeMoreThanMaximunAllowed() throws APIException {
		Article thing = new Article();
		thing.setIndex(1);
		thing.setWeight(50.0);
		thing.setPrize(125.8);
		validator.setThing(thing);
		boolean result = validator.validate();
		Assert.assertFalse(result);		
	}
	
}
