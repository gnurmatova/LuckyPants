package com.luckypants.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class image {

	
	private String imagename;

	/**
	 * @return the imagename
	 */
	public String getImagename() {
		return imagename;
	}

	/**
	 * @param imagename the imagename to set
	 */
	public void setImagename(String imagename) {
		
		this.imagename = imagename;
		
	}
	
}
