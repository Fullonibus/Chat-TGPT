package com.grom.tg.chat.chat_tgpt.bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Component
public class BotInitializer {

    final
    MyTelegramBot myTelegramBot;

    public BotInitializer(MyTelegramBot myTelegramBot) throws TelegramApiException {
        this.myTelegramBot = myTelegramBot;
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        botsApi.registerBot(myTelegramBot);
    }


}
