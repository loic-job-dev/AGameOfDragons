package fr.campus.loic.agameofdragons;

import fr.campus.loic.agameofdragons.db.DatabaseConnector;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws IOException {

        //Path for dev :
        String path = System.getProperty("user.dir") + "/out/artifacts/AGameOfDragons_jar/properties.txt";
        //Path in prod :
        //String path = System.getProperty("user.dir") + "/properties.txt";

        Properties props = new Properties();

        try (FileReader fr = new FileReader(path)) {
            props.load(fr); // Charge le fichier properties

            String user = props.getProperty("USER");
            String password = props.getProperty("PASSWORD");
            String host = props.getProperty("HOST");
            String port = props.getProperty("PORT");
            String database = props.getProperty("DATABASE");

            //System.out.println("User: " + user);
            //System.out.println("Password: " + password);

            //Tests for the database connection
            DatabaseConnector db = new DatabaseConnector(user, password, host, port, database);

            //Display of all the heroes
            //db.getHeroes();

        } catch (IOException e) {
            e.printStackTrace();
        }

        Game game = new Game();
        game.launchGame();
    }
}