package com.luckypants.command;

import java.io.IOException;

import org.bson.types.ObjectId;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.luckypants.model.Author;
import com.luckypants.mongo.ConnectionProvider;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

public class GetAuthorCommand {
	ObjectMapper mapper = new ObjectMapper();

	// modify signature of this method to now return the Author, not DBObject
	public Author execute(String key, String value) {
		ConnectionProvider conn = new ConnectionProvider();
		DBCollection authorsCollection = conn.getCollection("authors");

		BasicDBObject searchQuery = new BasicDBObject();
		if (key.equals("_id")) {
			searchQuery.put(key, new ObjectId(value));
		} else {
			searchQuery.put(key, value);
		}

		DBObject author = authorsCollection.findOne(searchQuery);

		try {
			return mapper.readValue(author.toString(), Author.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
