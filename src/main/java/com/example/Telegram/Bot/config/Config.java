package com.example.Telegram.Bot.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class Config {

    @Value("azemkin_bot")
    private String botUserName;

    @Value("1953950849:AAGLbn3LcEaqTr-Gx9MwvaZhoTAYgN8466E")
    private String token;


}
