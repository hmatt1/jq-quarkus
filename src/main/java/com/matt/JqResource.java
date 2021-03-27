package com.matt;

import com.jq.JQ;
import com.matt.client.ScryfallClient;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.lang.management.ManagementFactory;
import java.util.Map;

@Path("/")
public class JqResource {

    @Inject
    @RestClient
    ScryfallClient scryfallClient;

    @GET
    @Path("/jvm")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return ManagementFactory.getRuntimeMXBean().getVmVersion();
    }

    @POST
    @Path("jq")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Object jq(Map input) {
        return JQ.jq(input, ".foo.bar");
    }

    @POST
    @Path("cards/{name}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Object jqstr(String program, @PathParam("name") String name) {
        Object input = scryfallClient.searchByName(name);
        return JQ.jq(input, program);
    }
}