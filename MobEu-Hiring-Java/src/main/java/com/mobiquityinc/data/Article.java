package com.mobiquityinc.data;

/**
 * Object to manage the information related to a thing or element could be
 * included in a package or bag.
 * @author Andres Rios
 * */
public class Article {
	
	/**
	 * unique indicator of the article
	 * */
	private Integer index;
	
	/**
	 * Amount weight of the article
	 * */
	private Double weight;
	
	/**
	 * The value in money of the article
	 * */
	private Double prize;

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Double getPrize() {
		return prize;
	}

	public void setPrize(Double prize) {
		this.prize = prize;
	}
	
}
