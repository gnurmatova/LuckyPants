package com.luckypants.command;

import com.luckypants.mongo.BooksConnectionProvider;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;

public class CreateBookCommand {

	/**
	 * TODO:modify this command such that book details are sent to this method
	 * as parameters
	 * 
	 * @return
	 */
	public boolean execute() {
		BooksConnectionProvider booksConn = new BooksConnectionProvider();
		DBCollection booksCollection = booksConn.getCollection();

		BasicDBObject document = new BasicDBObject();
		document.put("title", "Lucky Pants");
		document.put("author", "John Doe");
		document.put("ISBN", "1234");
		booksCollection.insert(document);

		return true;
	}

	public static void main(String[] args) {
		CreateBookCommand create = new CreateBookCommand();
		if (create.execute()) {
			System.out.println("SUCCESS:Book Created");
		} else {
			System.out.println("ERROR:Failed to create book");
		}

	}
}
