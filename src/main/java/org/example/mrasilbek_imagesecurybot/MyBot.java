package org.example.mrasilbek_imagesecurybot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.ChatLocation;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyBot extends TelegramLongPollingBot {

    MyBotSecury myBotSecury = new MyBotSecury();

    private Map<Long, String> logins = new HashMap<>();
    private Map<Long, String> passwords = new HashMap<>();
    private Map<Long, String> userNames = new HashMap<>();
    private Map<Long, String> userSurnames = new HashMap<>();
    private Map<Long, String> userGenders = new HashMap<>();
    private Map<Long, String> userPhoneNumbers = new HashMap<>();
    private Map<Long, double[]> userLocations = new HashMap<>();

    private Map<Long, String> loginsAndPasswords = new HashMap<>();
    private Map<Long, String> stateMap = new HashMap<>();

    String loginBack = "loginBack";

    String signUp = "signUp";
    String signUp1 = "signUp1";
    String signUp2 = "signUp2";
    String signUp3 = "signUp3";
    String signUp4 = "signUp4";
    String signUp5 = "signUp5";

    String default1 = "default1";
    String default2 = "default2";
    String default3 = "default3";
    String default4 = "default4";
    String default5 = "default5";
    String default6 = "default6";
    String default7 = "default7";
    String default8 = "default8";
    String default9 = "default9";
    String default10 = "default10";

    String signIn = "signIn";
    String signIn1 = "signIn1";
    String signIn2 = "signIn2";
    String signIn3 = "signIn3";
    String signIn4 = "signIn4";
    String signIn5 = "signIn5";

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            String firstName = update.getMessage().getChat().getFirstName();
            String lastName = update.getMessage().getChat().getLastName();
            Long chatId = update.getMessage().getChatId();
            String text = update.getMessage().getText();
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(chatId);

            if (true){
                Introduction(chatId, signUp1, text, sendMessage, signUp2, firstName, signUp3, lastName, signUp4, signUp5, default2, signIn1, signIn2, signIn3, default1);
            }
        } else if (update.hasCallbackQuery()) {
            AllCallbackQuery(update, signUp, signUp1, signIn, signIn1);
        } else if (update.hasMessage() && update.getMessage().hasContact()) {
            SendContact(update);
        } else if (update.hasMessage() && update.getMessage().hasLocation()) {
            SendLocation(update);
        }
    }

    private void AllCallbackQuery(Update update, String signUp, String signUp1, String signIn, String signIn1) {
        SendMessage sendMessage = new SendMessage();
        Long chatId2 = update.getCallbackQuery().getMessage().getChatId();
        Integer messageId = update.getCallbackQuery().getMessage().getMessageId();
        String data = update.getCallbackQuery().getData();
        // birinchi sign up qiladi harqanday odam shuning uchun biz ham sign up dan boshlaymiz yani huddi foydalanuvchi kabi harakatlanamiz!
        if (data.equals(signUp)) {
            sendMessage.setChatId(chatId2);
            sendMessage.setText("Please enter username!");
            InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
            List<List<InlineKeyboardButton>> rowsListInlie = new ArrayList<>();
            List<InlineKeyboardButton> row = new ArrayList<>();
            InlineKeyboardButton button = new InlineKeyboardButton("⬅\uFE0F Orqaga");
            button.setCallbackData(loginBack);
            row.add(button);
            rowsListInlie.add(row);
            inlineKeyboardMarkup.setKeyboard(rowsListInlie);
            sendMessage.setReplyMarkup(inlineKeyboardMarkup);
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
            stateMap.put(chatId2, signUp1);
        } else if (data.equals(loginBack)) {
            try {
                execute(myBotSecury.login(chatId2));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        } else if (data.equals(signIn)) {
            sendMessage.setChatId(chatId2);
            sendMessage.setText("Please enter username!");
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
            stateMap.put(chatId2, signIn1);
            SendMessage sendMessage1 = myBotSecury.edit(chatId2);
            EditMessageText editMessageText = new EditMessageText();
            editMessageText.setChatId(chatId2);
            editMessageText.setMessageId(messageId);
            editMessageText.setText(sendMessage1.getText());
            editMessageText.setReplyMarkup((InlineKeyboardMarkup) sendMessage1.getReplyMarkup());
            try {
                execute(editMessageText);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        } else if (data.equals("editProfil")) {
        } else if (data.equals("backHome")) {
            SendMessage sendMessage1 = myBotSecury.MainMenu(chatId2);
            EditMessageText editMessageText = new EditMessageText();
            editMessageText.setChatId(chatId2);
            editMessageText.setMessageId(messageId);
            editMessageText.setText(sendMessage1.getText());
            editMessageText.setReplyMarkup((InlineKeyboardMarkup) sendMessage1.getReplyMarkup());
            try {
                execute(editMessageText);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    private void SendLocation(Update update) {
        SendMessage sendMessage = new SendMessage();
        Long chatId = update.getMessage().getChatId();
        double latitude = update.getMessage().getLocation().getLatitude();
        double longitude = update.getMessage().getLocation().getLongitude();

        userLocations.put(chatId, new double[]{latitude, longitude});
        sendMessage.setChatId(chatId);
        sendMessage.setText("Information received. Please press login and enter again!");
        try {
            execute(sendMessage);
            execute(myBotSecury.login(chatId));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void SendContact(Update update) {
        SendMessage sendMessage = new SendMessage();
        Long chatId = update.getMessage().getChatId();
        String phoneNumber = update.getMessage().getContact().getPhoneNumber();
        userPhoneNumbers.put(chatId, phoneNumber);
        sendMessage.setChatId(chatId);
        sendMessage.setText("Please sent your Location!");
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> rowList = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        KeyboardButton button = new KeyboardButton("Share my location!");
        button.setRequestLocation(true);
        row.add(button);
        rowList.add(row);
        replyKeyboardMarkup.setKeyboard(rowList);
        replyKeyboardMarkup.setResizeKeyboard(true);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void Introduction(Long chatId, String signUp1, String text, SendMessage sendMessage, String signUp2, String firstName, String signUp3, String lastName, String signUp4, String signUp5, String default2, String signIn1, String signIn2, String signIn3, String default1) {
        stateMap.putIfAbsent(chatId, "start");
        if (stateMap.getOrDefault(chatId, "").equals("start")) {
            try {
                execute(myBotSecury.login(chatId));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        } else if (stateMap.getOrDefault(chatId, "").equals(signUp1)) {
            String username = text;
            logins.put(chatId, username);
            sendMessage.setChatId(chatId);
            sendMessage.setText("Please enter password!");
            InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
            List<List<InlineKeyboardButton>> rowsListInlie = new ArrayList<>();
            List<InlineKeyboardButton> row = new ArrayList<>();
            InlineKeyboardButton button = new InlineKeyboardButton("⬅\uFE0F Orqaga");
            button.setCallbackData(loginBack);
            row.add(button);
            rowsListInlie.add(row);
            inlineKeyboardMarkup.setKeyboard(rowsListInlie);
            sendMessage.setReplyMarkup(inlineKeyboardMarkup);
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
            stateMap.put(chatId, signUp2);
        } else if (firstName != null && stateMap.getOrDefault(chatId, "").equals(signUp2)) {
            String password = text;
            passwords.put(chatId, password);
            sendMessage.setChatId(chatId);
            sendMessage.setText("Please enter your Name!");
            ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
            List<KeyboardRow> rowList = new ArrayList<>();
            KeyboardRow row = new KeyboardRow();
            KeyboardButton button = new KeyboardButton(firstName);
            row.add(button);
            rowList.add(row);
            replyKeyboardMarkup.setKeyboard(rowList);
            replyKeyboardMarkup.setResizeKeyboard(true);
            sendMessage.setReplyMarkup(replyKeyboardMarkup);
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
            stateMap.put(chatId, signUp3);
        } else if (firstName == null && stateMap.getOrDefault(chatId, "").equals(signUp2)) {
            sendMessage.setChatId(chatId);
            sendMessage.setText("Please enter your Name!");
            ReplyKeyboardRemove replyKeyboardRemove = new ReplyKeyboardRemove();
            replyKeyboardRemove.setSelective(true);
            sendMessage.setReplyMarkup(replyKeyboardRemove);
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
            stateMap.put(chatId, signUp3);
        } else if (lastName != null && stateMap.getOrDefault(chatId, "").equals(signUp3)) {
            String firtsName = text;
            userNames.put(chatId, firtsName);
            sendMessage.setChatId(chatId);
            sendMessage.setText("Please enter your Last Name!");
            ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
            List<KeyboardRow> rowList = new ArrayList<>();
            KeyboardRow row = new KeyboardRow();
            KeyboardButton button = new KeyboardButton(lastName);
            row.add(button);
            rowList.add(row);
            replyKeyboardMarkup.setKeyboard(rowList);
            replyKeyboardMarkup.setResizeKeyboard(true);
            sendMessage.setReplyMarkup(replyKeyboardMarkup);
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
            stateMap.put(chatId, signUp4);
        } else if (lastName == null && stateMap.getOrDefault(chatId, "").equals(signUp3)) {
            sendMessage.setChatId(chatId);
            sendMessage.setText("Please enter your Last Name!");
            ReplyKeyboardRemove replyKeyboardRemove = new ReplyKeyboardRemove();
            replyKeyboardRemove.setRemoveKeyboard(true);
            sendMessage.setReplyMarkup(replyKeyboardRemove);
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
            stateMap.put(chatId, signUp4);
        } else if (stateMap.getOrDefault(chatId, "").equals(signUp4)) {
            String lastNamee = text;
            userSurnames.put(chatId, lastNamee);
            sendMessage.setChatId(chatId);
            sendMessage.setText("Please enter your Gender!");
            ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
            List<KeyboardRow> rowList = new ArrayList<>();
            KeyboardRow row1 = new KeyboardRow();
            row1.add(new KeyboardButton("Male"));
            rowList.add(row1);
            KeyboardRow row2 = new KeyboardRow();
            row2.add(new KeyboardButton("Female"));
            rowList.add(row2);
            replyKeyboardMarkup.setKeyboard(rowList);
            replyKeyboardMarkup.setResizeKeyboard(true);
            sendMessage.setReplyMarkup(replyKeyboardMarkup);
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
            stateMap.put(chatId, signUp5);
        } else if (text.equals("Male") || text.equals("Female") && stateMap.getOrDefault(chatId, "").equals(signUp5)) {
            String Gender = text;
            userGenders.put(chatId, Gender);
            sendMessage.setChatId(chatId);
            sendMessage.setText("Please enter your phone number!");
            ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
            List<KeyboardRow> rowList = new ArrayList<>();
            KeyboardRow row = new KeyboardRow();
            KeyboardButton button = new KeyboardButton("Share my phone number!");
            button.setRequestContact(true);
            row.add(button);
            rowList.add(row);
            replyKeyboardMarkup.setKeyboard(rowList);
            replyKeyboardMarkup.setResizeKeyboard(true);
            sendMessage.setReplyMarkup(replyKeyboardMarkup);
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        } else if (stateMap.getOrDefault(chatId, "").equals(default2)) {
            sendMessage.setChatId(chatId);
            sendMessage.setText("Please enter your Gender!");
            ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
            List<KeyboardRow> rowList = new ArrayList<>();
            KeyboardRow row1 = new KeyboardRow();
            row1.add(new KeyboardButton("Male"));
            rowList.add(row1);
            KeyboardRow row2 = new KeyboardRow();
            row2.add(new KeyboardButton("Female"));
            rowList.add(row2);
            replyKeyboardMarkup.setKeyboard(rowList);
            replyKeyboardMarkup.setResizeKeyboard(true);
            sendMessage.setReplyMarkup(replyKeyboardMarkup);
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
            stateMap.put(chatId, signUp5);
        } else if (stateMap.getOrDefault(chatId, "").equals(signUp5)) {
            sendMessage(chatId, "Please select from the given button!");
            stateMap.put(chatId, default2);
        } else if (stateMap.getOrDefault(chatId, "").equals(signIn1)) {
            String sendsLogin = text;
            if (logins.containsValue(sendsLogin)) {
                sendMessage(chatId, "Enter the password on login now!");
                stateMap.put(chatId, signIn2);
                loginsAndPasswords.put(chatId, sendsLogin);
            } else {
                sendMessage(chatId, "Login try again error!");
                try {
                    execute(myBotSecury.login(chatId));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        } else if (stateMap.getOrDefault(chatId, "").equals(signIn2)) {
            String sendsPassword = text;

            String savedLogin = loginsAndPasswords.get(chatId);


            if (logins.containsValue(savedLogin) && passwords.containsValue(sendsPassword)){
                sendMessage(chatId, "Good, everything is set up correctly. Restart the bot with /start and use it as much as you want!");
                stateMap.put(chatId, signIn3);
            } else {
                sendMessage(chatId, "Password try again error!");
                try {
                    execute(myBotSecury.login(chatId));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        } else if (stateMap.getOrDefault(chatId, "").equals(default1)) {
            try {
                execute(myBotSecury.login(chatId));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        } else if (stateMap.getOrDefault(chatId, "").equals(signIn3)) {
            try {
                execute(myBotSecury.MainMenu(chatId));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    public SendMessage sendMessage(Long chatId, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        return sendMessage;
    }

    @Override
    public String getBotUsername() {
        return "MrAsilbek_TEST_bot";
    }

    @Override
    public String getBotToken() {
        return "7761830612:AAHEPMboOtP9SKyr1PmoK8BjYXYHbmVvMMI";
    }
}
