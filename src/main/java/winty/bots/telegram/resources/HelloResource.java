package winty.bots.telegram.resources;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import winty.bots.telegram.services.InfluxServices;

@Path("/hello")
public class HelloResource {

    @Inject InfluxServices influx;
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello(){
        influx.writeEvent("api:hello");
        return "hello api";
    }

}
