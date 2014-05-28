package com.luckypants.books;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

@Path("/twitter")
public class TwitterService {

	@GET
	@Path("/tweets")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTweets() {
		Twitter twitter = new TwitterFactory().getInstance();
		ResponseList<Status> timeline = null;
		try {
			timeline = twitter.getUserTimeline();
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		return Response.status(200).entity(timeline).build();
	}

}
