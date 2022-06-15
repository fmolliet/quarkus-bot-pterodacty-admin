package winty.bots.telegram.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class InfluxResponse {
    private String code;
    private String message;
}
