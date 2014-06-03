package com.luckypants.books;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;

import com.luckypants.command.ListAllAuthorsCommand;
import com.luckypants.model.Author;

@Path("/authors")
public class AuthorService {
	ObjectMapper mapper = new ObjectMapper();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listAuthors() {
		ListAllAuthorsCommand listAuthors = new ListAllAuthorsCommand();
		ArrayList<Author> list = listAuthors.execute();
		String AuthorsString = null;
		try {
			AuthorsString = mapper.writeValueAsString(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.status(200).entity(AuthorsString).build();
	}


}
