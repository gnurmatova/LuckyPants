package test;

/*
 * this code uses example from 
 * http://www.mkyong.com/mongodb/java-mongodb-hello-world-example/
 */
import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;

/**
 * this is a test class with some sample code for you to try your Mongo HQ
 * connection Do not use this same connection, create your own account/DB and
 * user
 * 
 */
public class MongoExplore {
	public static void main(String[] args) {

		try {

			/**
			 * Connect to Mongo DB
			 */
			MongoClient mongo = new MongoClient("oceanic.mongohq.com", 10099);

			/**
			 * Connect to DB
			 */
			DB db = mongo.getDB("luckypants");
			if (db == null) {
				System.out.println("Could not connect to Database");
			}

			/**
			 * authenticate to Mongo HQ
			 */
			boolean auth = db.authenticate("unh", "unh".toCharArray());
			if (auth == false) {
				System.out.println("Could not authenticate");
			}

			/**
			 * get a handler to books collection
			 */
			DBCollection booksColl = db.getCollection("books");

			/**
			 * find a book by title
			 */
			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put("title", java.util.regex.Pattern.compile(".*Poem.*.(?!Pants)"));

			DBCursor cursor = booksColl.find(searchQuery);

			while (cursor.hasNext()) {
				System.out.println(cursor.next());
			}

			System.out.println("Done");

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (MongoException e) {
			e.printStackTrace();
		}

	}
}