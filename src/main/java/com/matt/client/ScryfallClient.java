package com.matt.client;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.util.Map;

@Path("/")
@RegisterRestClient
public interface ScryfallClient {

    @GET
    @Path("/cards/search")
    Map<Object, Object> search(@QueryParam("q") String query);

    @GET
    @Path("/cards/named")
    Map<Object, Object> searchByName(@QueryParam("fuzzy") String name);
}
