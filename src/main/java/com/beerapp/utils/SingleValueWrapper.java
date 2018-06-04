package com.beerapp.utils;

public class SingleValueWrapper {

	private String value;
	
	public SingleValueWrapper() {
		
	}
	
	public SingleValueWrapper(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
}
