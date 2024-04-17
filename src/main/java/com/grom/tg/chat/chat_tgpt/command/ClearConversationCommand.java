package com.grom.tg.chat.chat_tgpt.command;

import com.grom.tg.chat.chat_tgpt.bot.MyTelegramBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public class ClearConversationCommand implements BotCommand{

    private final MyTelegramBot myTelegramBot;

    public ClearConversationCommand(MyTelegramBot myTelegramBot) {
        this.myTelegramBot = myTelegramBot;
    }

    @Override
    public void execute(Update update) {


    }
}
