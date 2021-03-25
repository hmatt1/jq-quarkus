package com.matt;

import com.jq.JQ;
import org.jboss.resteasy.annotations.Body;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.lang.management.ManagementFactory;
import java.util.Map;

@Path("/")
public class GreetingResource {

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
    @Path("jqstr")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String jqstr(Map input) {
        return JQ.jq(input, ".foo.bar", String.class);
    }
}