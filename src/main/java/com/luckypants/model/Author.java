package com.luckypants.model;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Author {

	private String fname;
	private String lname;
	private MongoId _id;
	private String id;

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	@JsonIgnore
	public MongoId get_id() {
		return _id;
	}

	public void set_id(MongoId _id) {
		this._id = _id;
		setId(_id.get$oid());
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	
}
