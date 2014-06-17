package com.luckypants.command;

import java.util.ArrayList;

import javax.ws.rs.core.Response;

import com.luckypants.model.Author;
import com.luckypants.model.Book;
import com.luckypants.mongo.ConnectionProvider;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

public class ListAllAuthorsCommand {
	String authorString = null;
	
	public ArrayList<Author> execute() {
	
		ConnectionProvider conn = new ConnectionProvider();
		DBCollection authorcollection = conn.getCollection("authors");
	
		DBCursor cursor = authorcollection.find();
		ArrayList<Author> authors = new ArrayList<Author>();
		GetAuthorCommand getauthor = new GetAuthorCommand();
		try {
			while (cursor.hasNext()) {
				Author b = getauthor.execute("_id",
						cursor.next().get("_id").toString());
				authors.add(b);
			
		}
		
	}catch (Exception e) {
		e.printStackTrace();
	} finally {
		cursor.close();
	}
	return authors;
	}
}
