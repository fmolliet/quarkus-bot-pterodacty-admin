package winty.bots.telegram;

import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.IBotCommand;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import winty.bots.telegram.commands.Hello;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;

import javax.inject.Singleton;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

@Singleton
public class PterodactylBot extends TelegramLongPollingCommandBot {

    @ConfigProperty(name = "telegram.bot.username") String username;
    
    @ConfigProperty(name = "telegram.bot.token") String token;
    
    private static final Logger logger = Logger.getLogger(PterodactylBot.class);
    
    private ArrayList<IBotCommand> commands = new ArrayList<IBotCommand>();
    
    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
        
    }

    
    public PterodactylBot() {
        super(new DefaultBotOptions());
    }
    
    public void start() {
    }

    public void setup() {
        this.loadCommands();
        this.registerCommand();
        logger.info("Comandos carregados com sucesso!");
    }
    
    private void registerCommand() {
        commands.forEach( (command) -> {
            super.register(command);
        });
    }

    private void loadCommands() {
        commands.add(new Hello("hello", "Da oi"));
    }

    private SendMessage responder(Update update) {
        var textoMensagem = update.getMessage().getText().toLowerCase();
        var chatId = update.getMessage().getChatId().toString();

        var resposta = "";

        if ("data".equals(textoMensagem)) {
            resposta = "a";
        } else if (textoMensagem.startsWith("hora")) {
            resposta = "Hora";
        } else if (textoMensagem.startsWith("ola") || textoMensagem.startsWith("olá") || textoMensagem.startsWith("oi")) {
            resposta = "\uD83E\uDD16 Olá, vejo que você entende muito sobre BOTS!";
        } else if (textoMensagem.startsWith("quem é você") || textoMensagem.startsWith("quem e voce")) {
            resposta = "\uD83E\uDD16 Eu sou um bot";
        } else {
            resposta = "Não entendi!\nDigite /help para ver os comandos disponíveis.";
        }

        return SendMessage.builder()
                .text(resposta)
                .chatId(chatId)
                .build();
    }

    @Override
    public void processNonCommandUpdate(Update update) {
        
        if (update.hasMessage() && update.getMessage().hasText()) {
            var mensagem = responder(update);
            try {
                execute(mensagem);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
    
    
    
}
