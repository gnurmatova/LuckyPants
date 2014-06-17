package com.luckypants.command;

import org.bson.types.ObjectId;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.ObjectMapper;

import com.luckypants.model.Book;
import com.luckypants.model.image;
import com.luckypants.mongo.ConnectionProvider;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;


public class GetImageCommand {
	ObjectMapper mapper = new ObjectMapper();

	public image execute(String key, String value) {
		ConnectionProvider conn = new ConnectionProvider();
		DBCollection imageCollection = conn.getCollection("images");
		
		BasicDBObject searchQuery = new BasicDBObject();
		if (key.equals("_id")) {
			searchQuery.put(key, new ObjectId(value));
		} else {
			searchQuery.put(key, value);
		}

		DBObject image = imageCollection.findOne(searchQuery);

		image vimage=null;
		// Now find the Author
		try {
			vimage = mapper.readValue(image.toString(), image.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vimage;
	}
	public static void main(String[] args) {
		GetImageCommand command = new GetImageCommand();

		image b = command.execute("imagename", "asdf.jpg");
		ObjectMapper mapper = new ObjectMapper();
		try {
			System.out.println(mapper.writeValueAsString(b));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
