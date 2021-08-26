package com.example.Telegram.Bot.bot;


import com.example.Telegram.Bot.config.Config;
import com.example.Telegram.Bot.model.Users;
import com.example.Telegram.Bot.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@Slf4j
public class Bot extends TelegramLongPollingBot {

    @Autowired
    private UsersRepository repository;

    @Autowired
    Users model;

    final Config config;

    public Bot(Config config) {
        this.config = config;
    }

    @Override
    public void onUpdateReceived(Update update) {
        update.getUpdateId();
        SendMessage.SendMessageBuilder builder = SendMessage.builder();

        String messageText;
        String chatId;



        if (update.getMessage() != null) {
            chatId = update.getMessage().getChatId().toString();
            builder.chatId(chatId);
            messageText = update.getMessage().getText();

        } else {
            chatId = update.getChannelPost().getChatId().toString();
            builder.chatId(chatId);
            messageText = update.getChannelPost().getText();
        }




        if (messageText.contains("/hello")) {
            builder.text("Hello" + " " + update.getMessage().getFrom().getUserName() + "!");

            Users request = repository.findModelByUserId(update.getMessage().getFrom().getId());
            if (request == null || request.getId() == null && request.getUserName() == null) {
                model.setUserName(update.getMessage().getFrom().getUserName());
                model.setUserId(update.getMessage().getFrom().getId());
                repository.save(model);
            }

            try {
                execute(builder.build());
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }


        if (messageText.contains("/done")) {

            Users request = repository.findModelByUserId(update.getMessage().getFrom().getId());
            builder.text("Tracker : +" + 1);
            if (request.getAmount() == null) {
                request.setAmount(1);
                repository.save(request);
            } else {
                request.setAmount(request.getAmount() + 1);
                repository.save(request);
            }

            try {
                execute(builder.build());
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

        if (messageText.contains("/count")) {
            Users request = repository.findModelByUserId(update.getMessage().getFrom().getId());
            if(request.getAmount()>10){
                builder.text("Amount : " + request.getAmount() +" , good job!");
            }else{
                builder.text("Amount : " + request.getAmount());
            }


            try {
                execute(builder.build());
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

        }

    }


    @Override
    public String getBotUsername() {
        return config.getBotUserName();
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }

    public void sendMessage(String chatId ,String text ) {
        try {
            execute(SendMessage.builder().chatId(chatId).text(text).build());
        } catch (TelegramApiException ex) {
            ex.getMessage();
        }
    }
}
