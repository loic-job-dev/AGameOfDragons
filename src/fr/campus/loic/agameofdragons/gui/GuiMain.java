package fr.campus.loic.agameofdragons.gui;

import fr.campus.loic.agameofdragons.Game;
import fr.campus.loic.agameofdragons.tools.GameLogger;
import fr.campus.loic.agameofdragons.tools.TextAreaHandler;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * Ths class is the entry point for the graphic version
 */
public class GuiMain extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        // TextArea to display the messages
        TextArea consoleArea = new TextArea();
        consoleArea.setEditable(false);

        // TextField for the user's text
        TextField inputField = new TextField();

        // Layout
        BorderPane root = new BorderPane();
        root.setCenter(consoleArea);
        root.setBottom(inputField);

        Scene scene = new Scene(root, 1080, 610, Color.BLACK);
        primaryStage.setScene(scene);
        primaryStage.setTitle("A Game of Dragons");
        primaryStage.show();

        // Logger to TextArea
        GameLogger.LOGGER.addHandler(new TextAreaHandler(consoleArea));

        // System.in redirection
        PipedOutputStream pipedOut = new PipedOutputStream();
        PipedInputStream pipedIn = new PipedInputStream(pipedOut);
        System.setIn(pipedIn);

        // Link between TextField and System.in
        inputField.setOnAction(e -> {
            String input = inputField.getText() + "\n";
            inputField.clear();
            try {
                pipedOut.write(input.getBytes());
                pipedOut.flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        // Launch the game in a thread
        new Thread(() -> {
            Game game = new Game();
            game.launchGame();
        }).start();
    }

    public static void main(String[] args) {
        launch();
    }

    // Util method to execute the thread JavaFX
    public static void runOnUIThread(Runnable runnable) {
        Platform.runLater(runnable);
    }
}
