package com.luckypants.command;

import org.codehaus.jackson.map.ObjectMapper;

import com.luckypants.model.image;
import com.luckypants.mongo.ConnectionProvider;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

public class CreateImageCommand {
	
	public String execute(image img) {
		ConnectionProvider conn = new ConnectionProvider();
		DBCollection collection = conn.getCollection("images");
		System.out.println(collection);

		ObjectMapper mapper = new ObjectMapper();
		try {
			DBObject dbObject = (DBObject) JSON.parse(mapper
					.writeValueAsString(img));
			collection.insert(dbObject);
			return dbObject.get("_id").toString();
			
		} catch (Exception e) {
			System.out.println("ERROR during mapping author to Mongo Object");
			return null;
		}
	}

	public static void main(String[] args) {
		CreateImageCommand create = new CreateImageCommand();
		image im= new image();
		im.setImagename("12345.jpeg");
		Object id = create.execute(im);
		if ( id!=null) {
			System.out.println("SUCCESS:Image Created:"+id);
		} else {
			System.out.println("ERROR:Failed to create author");
		}

	}

}
