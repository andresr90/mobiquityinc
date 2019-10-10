package com.mobiquityinc.data;

import java.util.List;

/**
 * Class to manage the information related to a package with his representatives
 * elements or things that it's saving.
 * @author Andres Rios
 * */
public class Bag {
	
	/**
	 * this indicate the value of the capacity that bag or package support
	 * */
	private double capacity;
	
	/**
	 * List with the different things or articles in the package
	 * */
	private List<Article> articles;

	public double getCapacity() {
		return capacity;
	}

	public void setCapacity(double capacity) {
		this.capacity = capacity;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> things) {
		this.articles = things;
	}	
	
}
