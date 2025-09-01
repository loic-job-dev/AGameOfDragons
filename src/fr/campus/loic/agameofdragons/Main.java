package fr.campus.loic.agameofdragons;

import fr.campus.loic.agameofdragons.db.DatabaseConnector;

public class Main {
    public static void main(String[] args) {

        //Tests for the database connection
        //DatabaseConnector db = new DatabaseConnector();

        //Display of all the heroes
        //db.getHeroes();

        Game game = new Game();
        game.launchGame();
    }
}