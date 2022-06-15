package winty.bots.telegram.resources;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import winty.bots.telegram.model.InfluxRequest;
import winty.bots.telegram.services.InfluxAPIService;
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
