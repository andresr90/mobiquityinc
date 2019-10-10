package com.mobiquityinc.packer;

import java.util.List;

import com.mobiquityinc.constant.Constants;
import com.mobiquityinc.data.Bag;
import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.reader.ThingsFileReader;
import com.mobiquityinc.service.IFileReader;

/**
 * Class to generate the packages for sending to the friends
 * 
 * @author Andres Rios
 * */
public class Packer {
  
  private Packer() {
  }
  
  /**
   * Method to generate the packages for sending to the users.
   * 
   * @author Andres Rios
   * 
   * @param filePath, 
   * */
  public static String pack(String filePath) throws APIException {
     IFileReader fileReader = new ThingsFileReader();
     List<Bag> bags = fileReader.read(filePath);
     StringBuilder result = new StringBuilder();
     for(Bag bag: bags) {
    	 result.append(PackerCalculator.getInstance().chooseArticlesPackage(bag));
    	 result.append(Constants.BREAK_LINE);
     }
	 return result.toString();
  }  
  
}
