package com.example.foxably.AI;

import com.example.foxably.Controller.MainController;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;
import org.alicebot.ab.MagicBooleans;

import java.io.File;

public class ChatController extends MainController {
    @FXML
    private ListView chatArea;
    @FXML
    private TextArea inputField;
    @FXML
    private Button sendButton;

    private Bot b;
    private Chat chatSession;
    private static final boolean TRACE_MODE = false;

    @FXML
    private void initialize() {
        String resourcePath = getPath();
        MagicBooleans.trace_mode = TRACE_MODE;
        b = new Bot("super", resourcePath);
        chatSession = new Chat(b);
    }

    @FXML
    private void sendMessage() {
        String userInput = inputField.getText();
        if (!userInput.isEmpty()) {
            String response = chatSession.multisentenceRespond(userInput);
            chatArea.getItems().add("YOU: " + userInput);
            chatArea.getItems().add("BOT: " + response);
            inputField.clear();
        }
    }

    private static String getPath() {
        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        String resourcePath = path + File.separator + "src" + File.separator + "main" + File.separator + "resources";
        return resourcePath;
    }
}
