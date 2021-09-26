import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class WoodLift extends TelegramLongPollingBot {

    ReplyKeyboardMarkup replykeyboard = new ReplyKeyboardMarkup();


    @Override
    public String getBotUsername() {
        return "Wood_Lift_bot";
    }

    @Override
    public String getBotToken() {
        return "1983418214:AAEVG7DeDmfQnhkZR1oS_Fk_UKiuYrbvNWU";
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            SendMessage message = new SendMessage();
            message.setChatId(update.getMessage().getChatId().toString());
            try {
                message.setReplyMarkup(replykeyboard);
                message.setText(answer(update.getMessage().getText()));

            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    String answer(String msg) throws IOException {
        ArrayList<KeyboardRow> keyboard = new ArrayList<>();
        replykeyboard.setResizeKeyboard(true);
        replykeyboard.setOneTimeKeyboard(false);
        replykeyboard.setSelective(true);
        System.out.println(msg);
        if ("/start".equals(msg)) {
            KeyboardRow one = new KeyboardRow();
            one.add("Начать");
            keyboard.add(one);

            replykeyboard.setKeyboard(keyboard);
            keyboard.clear();
            return trainings("src/main/resources/greeting");
        }
        switch (msg) {
            case "1":
                return trainings("src/main/resources/back");
            case "2":
                return trainings("src/main/resources/arms");
            case "3":
                return trainings("src/main/resources/shoulders");
            case "4":
                return trainings("src/main/resources/pe");

        }


        return "Я пока не умею отвечать";
    }
public static String trainings(String filename) throws IOException {

        Path path = Paths.get(filename);
    List<String> s = Files.readAllLines(path);
    String st = "";
    for(String str : s)
        st = st + str + '\n';
    return st;

}






}
