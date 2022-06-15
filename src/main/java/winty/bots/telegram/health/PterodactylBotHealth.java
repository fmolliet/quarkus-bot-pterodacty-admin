package winty.bots.telegram.health;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import winty.bots.telegram.PterodactylBot;

@Liveness
@ApplicationScoped  
public class PterodactylBotHealth implements HealthCheck {
    
    @Inject PterodactylBot bot;
    
    private static final String NAME = "Pterodactyl - BOT";
    
    @Override
    public HealthCheckResponse call() {
        try {
            bot.getMe();
            return HealthCheckResponse.up(NAME);
        } catch (TelegramApiException e) {
            e.printStackTrace();
            return HealthCheckResponse.down(NAME);
        }
        
    }
}
