package com.mobiquityinc.validator;

import com.mobiquityinc.constant.Constants;
import com.mobiquityinc.constant.MessageConstants;
import com.mobiquityinc.data.Article;
import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.service.IValidator;

public class ThingValidator implements IValidator {

	private Article thing;
	
	@Override
	public boolean validate() throws APIException {
		boolean result = true;
		if(thing == null) {
			throw new APIException(MessageConstants.MESSAGE_NO_EXIST_INFORMATION_ARTICLE);	
		}
		if(thing.getIndex() == null){
			throw new APIException(MessageConstants.MESSAGE_ARTICLE_HASNOT_INDEX);	
		}
		if(thing.getWeight() == null) {
			throw new APIException(MessageConstants.MESSAGE_ARTICLE_HASNOT_WEIGHT);
		}
		if(thing.getPrize() == null) {
			throw new APIException(MessageConstants.MESSAGE_ARTICLE_HASNOT_PRIZE);
		}		
		if(thing.getWeight() > Constants.MAXIMUN_VALUES_PRIZE_WEIGHT 
				|| thing.getPrize() > Constants.MAXIMUN_VALUES_PRIZE_WEIGHT) {
			result = false;
		}
		return result;
	}

	public Article getThing() {
		return thing;
	}

	public void setThing(Article thing) {
		this.thing = thing;
	}	

}
