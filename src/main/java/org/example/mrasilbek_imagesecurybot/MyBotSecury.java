package org.example.mrasilbek_imagesecurybot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class MyBotSecury {
    public SendMessage MainMenu(Long chatId){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("Select");
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsListInlie = new ArrayList<>();
        List<InlineKeyboardButton> row1 = new ArrayList<>();
        InlineKeyboardButton button1 = new InlineKeyboardButton("Profil Edit \uD83D\uDCDD");
        button1.setCallbackData("editProfil");
        row1.add(button1);
        rowsListInlie.add(row1);
        List<InlineKeyboardButton> row2 = new ArrayList<>();
        InlineKeyboardButton button2 = new InlineKeyboardButton("My Project \uD83D\uDD27");
        button2.setCallbackData("myProject");
        row2.add(button2);
        InlineKeyboardButton button3 = new InlineKeyboardButton("Chat access \uD83D\uDCAC");
        button3.setCallbackData("chatAccess");
        row2.add(button3);
        rowsListInlie.add(row2);
        List<InlineKeyboardButton> row3 = new ArrayList<>();
        InlineKeyboardButton button4 = new InlineKeyboardButton("Get In Job \uD83D\uDCBC");
        button4.setCallbackData("getInJob");
        row3.add(button4);
        InlineKeyboardButton button5 = new InlineKeyboardButton("My Wallet Money \uD83D\uDCB3\uD83D\uDCB8");
        button5.setCallbackData("myWalletMoney");
        row3.add(button5);
        rowsListInlie.add(row3);
        List<InlineKeyboardButton> row4 = new ArrayList<>();
        InlineKeyboardButton button6 = new InlineKeyboardButton("Help On Work \uD83C\uDD98\uD83D\uDCBC");
        button6.setCallbackData("helpOnWork");
        row4.add(button6);
        InlineKeyboardButton button7 = new InlineKeyboardButton("Help \uD83C\uDD98");
        button7.setCallbackData("help");
        row4.add(button7);
        rowsListInlie.add(row4);
        inlineKeyboardMarkup.setKeyboard(rowsListInlie);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        return sendMessage;
    }
    public SendMessage login(Long chatId){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("Hi please select");
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsListInlie = new ArrayList<>();
        List<InlineKeyboardButton> row = new ArrayList<>();
        InlineKeyboardButton button1 = new InlineKeyboardButton("Sign-In \uD83D\uDCDD");
        button1.setCallbackData("signIn");
        row.add(button1);
        InlineKeyboardButton button2 = new InlineKeyboardButton("Sign-Up \uD83C\uDF10");
        button2.setCallbackData("signUp");
        row.add(button2);
        rowsListInlie.add(row);
        inlineKeyboardMarkup.setKeyboard(rowsListInlie);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        return sendMessage;
    }
}
