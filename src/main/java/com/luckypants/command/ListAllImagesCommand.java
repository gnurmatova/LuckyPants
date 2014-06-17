package com.luckypants.command;

import java.util.ArrayList;

import com.luckypants.model.Book;
import com.luckypants.model.image;
import com.luckypants.mongo.ConnectionProvider;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

public class ListAllImagesCommand {
//String authorString = null;
	
	public ArrayList<image> execute() {
	
		ConnectionProvider conn = new ConnectionProvider();
		DBCollection imagecollection = conn.getCollection("images");
	
		DBCursor cursor = imagecollection.find();
		ArrayList<image> images = new ArrayList<image>();
		GetImageCommand getimages = new GetImageCommand();
		try {
			while (cursor.hasNext()) {
				image b = getimages.execute("_id",
						cursor.next().get("_id").toString());
				images.add(b);
		}
		
	}catch (Exception e) {
		e.printStackTrace();
	} finally {
		cursor.close();
	}
	return images;
	}
	
	public static void main(String[] args) {
		ListAllImagesCommand listBooks = new ListAllImagesCommand();
		ArrayList<image> list = listBooks.execute();
		for(image i: list){
			System.out.println(i.getImagename());
		}

	}

}
