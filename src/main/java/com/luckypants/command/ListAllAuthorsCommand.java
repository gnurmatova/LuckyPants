package com.luckypants.command;

import java.util.ArrayList;

import com.luckypants.model.Author;
import com.luckypants.mongo.ConnectionProvider;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

public class ListAllAuthorsCommand {

	public ArrayList<Author> execute() {
		ConnectionProvider authorsConn = new ConnectionProvider();
		DBCollection collection = authorsConn.getCollection("authors");

		DBCursor cursor = collection.find();

		ArrayList<Author> authors = new ArrayList<Author>();
		GetAuthorCommand getAuthor = new GetAuthorCommand();
		try {
			while (cursor.hasNext()) {
				Author a = getAuthor.execute("_id",
						cursor.next().get("_id").toString());
				authors.add(a);
			}
		} finally {
			cursor.close();
		}
		return authors;

	}

	public static void main(String[] args) {
		
	}
}
