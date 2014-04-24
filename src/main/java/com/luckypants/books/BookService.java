package com.luckypants.books;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.luckypants.command.ListAllBooksCommand;
import com.mongodb.DBObject;

@Path("/books")
public class BookService {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBook() {
		ListAllBooksCommand listBooks = new ListAllBooksCommand();
		ArrayList<DBObject> list = listBooks.execute();
		return Response.status(200).entity(list).build();
	}

	@GET
	@Path("/{isbn}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getName(@PathParam("isbn") String isbn) {
		return Response.status(200).entity("").build();
	}

}
