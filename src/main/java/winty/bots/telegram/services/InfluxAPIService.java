package winty.bots.telegram.services;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;


@Path("/api/v2")
@RegisterRestClient(configKey = "influx-api")
@ClientHeaderParam(name = "Content-Type", value = "text/plain")
@Singleton
public interface InfluxAPIService {
    
    @POST
    @Path("/write")
    public void write(	
                @HeaderParam("Authorization") String token,
                @QueryParam("bucket") String bucket,
                @QueryParam("org") String org,
                @RequestBody String payload );

    @GET
    @Path("/")        
    public String getEndpoints(
        @HeaderParam("Authorization") String token
    );
      
}
