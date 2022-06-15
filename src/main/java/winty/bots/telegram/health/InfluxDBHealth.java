package winty.bots.telegram.health;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

import winty.bots.telegram.services.InfluxServices;

@Liveness
@ApplicationScoped  
public class InfluxDBHealth implements HealthCheck  {

    @Inject InfluxServices influx;
    
    @Override
    public HealthCheckResponse call() {
        
        try {
            influx.isAvaliable();
            return HealthCheckResponse.up("Influx v2.0.3");
        } catch (Exception e) {
            //e.printStackTrace();
            return HealthCheckResponse.down("Influx Error");
        }
        
    }
    
}
