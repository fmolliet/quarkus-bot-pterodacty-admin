package winty.bots.telegram;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.jboss.logging.Logger;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;

@ApplicationScoped
public class AppLifecycleBean {
    
    @Inject PterodactylBot bot;
    
    private static final Logger LOGGER = Logger.getLogger("ListenerBean");
    
    void onStart(@Observes StartupEvent ev) {
        LOGGER.info("Bot is starting...");
        this.bot.setup();
        this.bot.start();
        
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(bot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    void onStop(@Observes ShutdownEvent ev) {
        LOGGER.info("Bot is stopping...");
    }
}
