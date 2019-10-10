package com.mobiquityinc.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.mobiquityinc.constant.Constants;
import com.mobiquityinc.constant.MessageConstants;
import com.mobiquityinc.data.Bag;
import com.mobiquityinc.data.Article;
import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.service.IFileReader;
import com.mobiquityinc.validator.FileValidator;
import com.mobiquityinc.validator.ThingValidator;

/**
 * Reader to check the files will be procesed and get the bags with their articles.
 * 
 * @author Andres Rios
 * */
public class ThingsFileReader implements IFileReader{

	private FileValidator fileValidator;
	private ThingValidator thingValidator;
	
	@Override
	public List<Bag> read(String filePath) throws APIException {
		fileValidator = new FileValidator();
		fileValidator.setFilePath(filePath);
		fileValidator.validate();
		List<Bag> bags = new ArrayList<Bag>();
		BufferedReader bufferReader =  null;
		try {
			File file = new File(filePath);
			bufferReader = new BufferedReader(new FileReader(file));
			String text = bufferReader.readLine();
			while (text  != null) {			
				bags.add(createPackage(text));
				text = bufferReader.readLine();
			}
		} catch (IOException e) {
			throw new APIException(MessageConstants.MESSAGE_ERROR_INPUT_OUTPUT,e);
		} catch(Exception e) {
			throw new APIException(MessageConstants.MESSAGE_ERROR_READING_FILE,e);
		} finally {
			if(bufferReader != null) {
				try {
					bufferReader.close();
				} catch (IOException e) {
					throw new APIException(MessageConstants.MESSAGE_ERROR_CLOSING_BUFFER,e);
				}
			}
		}
		return bags;
	}
	
	private Bag createPackage(String record) throws APIException {
		Bag bag = new Bag();
		String[] records  = record.split(Constants.COLON);
		if(records != null && record.length() > 1) {
			Double capacity = Double.valueOf(records[0].trim());
			bag.setCapacity(capacity);
			bag.setArticles(createThings(records[1]));
		}
		return bag;
	}
	
	private List<Article> createThings(String things) throws APIException {
		List<Article> listThings = new ArrayList<Article>();
		String[] items = things.split(Constants.SPACE);
		for(String item:items) {
			if(item != null && item.length() > 0) {
				Article thing = createThing(item);
				thingValidator = new ThingValidator();
				thingValidator.setThing(thing);
				if(thingValidator.validate()) {
					listThings.add(thing);
				}
			}
		}
		return listThings;
	}
	
	private Article createThing(String thing) {
		Article newThing = new Article();
		thing = thing.replace(Constants.OPEN_PARENTHESIS, Constants.NO_SPACE);
		thing = thing.replace(Constants.CLOSE_PARENTHESIS, Constants.NO_SPACE);
		String[] itemValues = thing.trim().split(Constants.COMMA);
		if(itemValues != null && itemValues.length > 2) {
			newThing.setIndex(Integer.valueOf(itemValues[0]));
			newThing.setWeight(Double.valueOf(itemValues[1]));
			String prize = itemValues[2].replace(Constants.EURO_SIGN_MONEY, Constants.NO_SPACE);
			newThing.setPrize(Double.valueOf(prize));
		}
		return newThing;
	}	
	
}
