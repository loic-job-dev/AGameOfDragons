package fr.campus.loic.agameofdragons;

import fr.campus.loic.agameofdragons.db.DatabaseConnector;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws IOException {

        Game game = new Game();
        game.launchGame();
    }
}