package winty.bots.telegram.services;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import winty.bots.telegram.model.InfluxRequest;

@Singleton
public class InfluxServices {
    
    @Inject @RestClient InfluxAPIService influxServices;
    
    @ConfigProperty( name = "influx.token") String token;
    
    @ConfigProperty(name = "influx.host") String host;
    
    @ConfigProperty(name = "influx.bucket") String bucket;
    
    @ConfigProperty(name = "influx.org") String org;
    
    @ConfigProperty(name = "influx.name") String name;
    
    public void writeEvent(String event ){ 
        influxServices.write( "Token " + token,
            bucket,
            org,
            new InfluxRequest(name, event).toString()
        );
    }

    public void isAvaliable() {
        influxServices.getEndpoints( "Token " + token);
    }
    
}
