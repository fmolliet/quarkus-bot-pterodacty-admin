package winty.bots.telegram.commands;

import javax.inject.Inject;

import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Hello extends BotCommand {
    
    @Inject 

    public Hello(String commandIdentifier, String description) {
        super(commandIdentifier, description);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] arguments) {

        try {
            absSender.execute(
                SendMessage.builder()
                    .text("ola")
                    .chatId(
                        chat.getId()
                            .toString()
                        )
                    .build()
                );
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    
    
}
