package com.mobiquityinc.validator;

import java.io.File;

import com.mobiquityinc.constant.MessageConstants;
import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.service.IValidator;

/**
 * Validator to check the information related to the file path
 * if it's correct, will allow to continue working in the logic process
 * @author Andres Rios
 * */
public class FileValidator implements IValidator {
	
	private String filePath;
	
	@Override
	public boolean validate() throws APIException {
		if(filePath == null || filePath.isEmpty()) {
			throw new APIException(MessageConstants.MESSAGE_FILEPATH_IS_EMPTY);
		}
		File file = new File(filePath);
		if(file == null || !file.isFile()) {
			throw new APIException(MessageConstants.MESSAGE_FILE_NO_EXIST);
		}
		return true;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}	
	
}
