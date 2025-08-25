package fr.campus.loic.agameofdragons.gui;

import fr.campus.loic.agameofdragons.Game;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class GuiMain extends Application {

    @Override
    public void start(Stage primaryStage) {

        Color black = Color.BLACK;
        Scene scene = new Scene(new Group(), 800, 600, black);
        primaryStage.setScene(scene);
        primaryStage.setTitle("A Game of Dragons");
        primaryStage.show();

        // Lancer le jeu dans un thread séparé
        new Thread(() -> {
            Game game = new Game();
            game.launchGame();
        }).start();
    }

    public static void main(String[] args) {
        launch();
    }
}
