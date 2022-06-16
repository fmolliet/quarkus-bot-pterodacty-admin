package winty.bots.telegram.model;

import java.time.Instant;
import lombok.Data;

@Data
public class InfluxRequest {

   private String name;
   private String event;
   
    public InfluxRequest(String name, String event) {
        this.name = name;
        this.event = event;
    }

    @Override
    public String toString() {
        return "events,host="+this.getName()+" "+this.getEvent()+"=1 "+ Instant.now().getEpochSecond()*1000000000;
    }
}
