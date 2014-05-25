package com.luckypants.command;

import java.util.ArrayList;

import com.luckypants.model.Book;
import com.luckypants.mongo.BooksConnectionProvider;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

public class ListAllBooksCommand {

	public ArrayList<Book> execute() {
		BooksConnectionProvider booksConn = new BooksConnectionProvider();
		DBCollection booksCollection = booksConn.getCollection();

		DBCursor cursor = booksCollection.find();

		ArrayList<Book> books = new ArrayList<Book>();
		GetBookCommand getBook = new GetBookCommand();
		try {
			while (cursor.hasNext()) {
				Book b = getBook.execute("_id",
						cursor.next().get("_id").toString());
				books.add(b);
			}
		} finally {
			cursor.close();
		}
		return books;

	}

	public static void main(String[] args) {
		ListAllBooksCommand listBooks = new ListAllBooksCommand();
		ArrayList<Book> list = listBooks.execute();
		System.out.println(list);

	}
}
