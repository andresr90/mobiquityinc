package com.mobiquityinc.packer;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import com.mobiquityinc.data.Article;
import com.mobiquityinc.data.Bag;

import junit.framework.Assert;

/**
 * Test to check the behaviours and operations relationed to calculator.
 * 
 * @author Andres Rios
 * */
@RunWith(MockitoJUnitRunner.class)
public class PackerCalculatorTest {
	
	@InjectMocks
	private PackerCalculator calculator;
	
	@Test
	public void chooseTheArticlesWithIndexThree() {
		Bag bag = new Bag();
		bag.setCapacity(53);
		List<Article> articles = new ArrayList<Article>();
		Article article = new Article();
		article.setIndex(1);
		article.setPrize(34.0);
		article.setWeight(20.9);
		articles.add(article);
		Article article2 = new Article();
		article2.setIndex(2);
		article2.setPrize(56.0);
		article2.setWeight(76.9);
		articles.add(article2);
		Article article3 = new Article();
		article3.setIndex(3);
		article3.setPrize(98.0);
		article3.setWeight(40.9);
		articles.add(article3);
		bag.setArticles(articles);		
		String result = calculator.chooseArticlesPackage(bag);
		Assert.assertTrue(result.contains("3,"));
	}
	
	@Test
	public void itTakeTheArticlesWithIndexTwoAndThree() {
		Bag bag = new Bag();
		bag.setCapacity(98);
		List<Article> articles = new ArrayList<Article>();
		Article article = new Article();
		article.setIndex(1);
		article.setPrize(34.0);
		article.setWeight(20.9);
		articles.add(article);
		Article article2 = new Article();
		article2.setIndex(2);
		article2.setPrize(56.0);
		article2.setWeight(35.9);
		articles.add(article2);
		Article article3 = new Article();
		article3.setIndex(3);
		article3.setPrize(98.0);
		article3.setWeight(45.9);
		articles.add(article3);
		bag.setArticles(articles);		
		String result = calculator.chooseArticlesPackage(bag);
		Assert.assertTrue(result.contains("3,2,"));		
	}
	
	@Test
	public void itDoesNotTakeAnyValue() {
		Bag bag = new Bag();
		bag.setCapacity(31);
		List<Article> articles = new ArrayList<Article>();
		Article article = new Article();
		article.setIndex(1);
		article.setPrize(120.0);
		article.setWeight(46.9);
		articles.add(article);
		Article article2 = new Article();
		article2.setIndex(2);
		article2.setPrize(156.0);
		article2.setWeight(35.9);
		articles.add(article2);
		Article article3 = new Article();
		article3.setIndex(3);
		article3.setPrize(98.0);
		article3.setWeight(45.9);
		articles.add(article3);
		bag.setArticles(articles);		
		String result = calculator.chooseArticlesPackage(bag);
		Assert.assertTrue(result.contains("-"));
	}
	
	@Test
	public void theBagHasNotArticles() {
		Bag bag = new Bag();
		bag.setCapacity(45);	
		String result = calculator.chooseArticlesPackage(bag);
		Assert.assertTrue(result.contains("-"));
	}
	
	@Test
	public void theBagSupportCapacityMoreThenMaximunAllowedForEachBag() {
		Bag bag = new Bag();
		bag.setCapacity(156);
		List<Article> articles = new ArrayList<Article>();
		Article article = new Article();
		article.setIndex(1);
		article.setPrize(120.0);
		article.setWeight(46.9);
		articles.add(article);
		Article article2 = new Article();
		article2.setIndex(2);
		article2.setPrize(156.0);
		article2.setWeight(35.9);
		articles.add(article2);
		Article article3 = new Article();
		article3.setIndex(3);
		article3.setPrize(98.0);
		article3.setWeight(45.9);
		articles.add(article3);
		bag.setArticles(articles);		
		String result = calculator.chooseArticlesPackage(bag);
		Assert.assertTrue(result.contains("1,3"));
	}
	
	@Test
	public void theBagCanSupportMoreThanFifteenSuppearTheMaximun() {
		Bag bag = new Bag();
		bag.setCapacity(100);
		List<Article> articles = new ArrayList<Article>();
		for(int i = 0; i < 20; i++) {
			Article article = new Article();
			article.setIndex(i + 1);
			article.setPrize(20.0 * (i + 1));
			article.setWeight(5.0);
			articles.add(article);
		}		
		bag.setArticles(articles);		
		String result = calculator.chooseArticlesPackage(bag);
		Assert.assertTrue(result.contains("20,19,18,17,16,15,14,13,12,11,10,9,8,7,6,5"));
	}
	
}
