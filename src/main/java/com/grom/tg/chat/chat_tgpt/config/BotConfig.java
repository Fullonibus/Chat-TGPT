package com.grom.tg.chat.chat_tgpt.config;

import com.grom.tg.chat.chat_tgpt.bot.MyTelegramBot;
import com.grom.tg.chat.chat_tgpt.handler.BotHandler;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@EnableCaching
@Configuration
public class BotConfig {
    @Bean
    public MyTelegramBot myTelegramBot(@Value("${token.value}") String token) {
        return new MyTelegramBot(token, new BotHandler());
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }



}
