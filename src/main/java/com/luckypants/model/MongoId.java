package com.luckypants.model;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class MongoId {
	private String $oid;

	public MongoId() {
	}

	public String get$oid() {
		return $oid;
	}

	public void set$oid(String $oid) {
		this.$oid = $oid;
	}

	@JsonCreator
	public static String fromJSON(String val) throws JsonParseException,
			JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		MongoId a = mapper.readValue(val, MongoId.class);
		return a.get$oid();
	}
}
