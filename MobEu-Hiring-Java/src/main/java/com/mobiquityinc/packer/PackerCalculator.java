package com.mobiquityinc.packer;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.mobiquityinc.constant.Constants;
import com.mobiquityinc.data.Bag;
import com.mobiquityinc.data.Article;

/**
 * Calculator to realize the operations associated for the packer like
 * choose the articles in each bag.
 * @author Andres Rios
 * */
public class PackerCalculator {

	private static PackerCalculator instance;
	
	public static PackerCalculator getInstance() {
		if(instance ==  null) {
			instance = new PackerCalculator();
		}
		return instance;
	}
	
	/**
	 * Method to choose the articles should be saving in the bag
	 * according the business rules.
	 *  
	 * @author Andres Rios
	 * 
	 * @param bag, package contain all articles possible to choose
	 * 
	 * @return articles have a great value that bag should be saving.
	 * */
	public String chooseArticlesPackage(Bag bag) {
		  StringBuilder result = new StringBuilder();
		  double countWeightBag=0;
		  if(bag.getArticles() != null) {
			  bag.setArticles(orderArticles(bag.getArticles()));		    
		      int position=0;
		      int countArticles = 0;
		      while(countWeightBag<bag.getCapacity() 
		    		  && countWeightBag < Constants.MAXIMUN_WEIGHT_BAG
		    		  && position < bag.getArticles().size()) {
		          Article thing = bag.getArticles().get(position);
		          if(countWeightBag + thing.getWeight() <= bag.getCapacity() 
		        		  && countArticles <= Constants.AMOUNT_MAXIMUN_ARTICLES_PER_BAG) {
		              result.append(thing.getIndex()).append(Constants.COMMA);
		              countWeightBag+=thing.getWeight();
		              countArticles++;		              
		          }
		          position++;
		      }		     
		  }
		  if(countWeightBag == 0) {
	    	  result.append(Constants.LINE);
	      }
	      return result.toString();
	  }
	  
	/**
	 * Method to order the articles for analizing each one, so take the decisition
	 * of the articles bring in bag.
	 * 
	 * @author Andres Rios 
	 * 
	 * @param things, List containg all articles should be ordered according the prize of each one
	 * 
	 * @return List organized and ordered.
	 * */
	  private List<Article> orderArticles(List<Article> things){
		  Comparator<Article> comparatorPrizes = new Comparator<Article>() {
	          public int compare(Article x, Article y) {
	              return (int) (x.getPrize() - y.getPrize());
	          }
	      };
	      Collections.sort(things,comparatorPrizes);  
	      Collections.reverse(things);
	      return things;
	  }
	
}
