package com.grom.tg.chat.chat_tgpt.handler;

import com.grom.tg.chat.chat_tgpt.bot.MyTelegramBot;
import com.grom.tg.chat.chat_tgpt.command.BotCommand;
import com.grom.tg.chat.chat_tgpt.command.StartCommand;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.HashMap;
import java.util.Map;


@Getter
public class BotHandler {
    private final Map<String, BotCommand> commandMap;


    private  MyTelegramBot myTelegramBot;

    public BotHandler() {
        this.commandMap = new HashMap<>();
    }

    public void setBot(MyTelegramBot bot) {
        this.myTelegramBot = bot;
        initializeCommands();
    }

    private void initializeCommands() {
        commandMap.put("/start", new StartCommand(myTelegramBot));

    }


}
