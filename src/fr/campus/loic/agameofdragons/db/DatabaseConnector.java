package fr.campus.loic.agameofdragons.db;

import com.google.gson.Gson;
import com.mysql.cj.jdbc.JdbcStatement;
import fr.campus.loic.agameofdragons.Menu;
import fr.campus.loic.agameofdragons.characters.Character;
import fr.campus.loic.agameofdragons.material.Board;

import java.sql.*;

public class DatabaseConnector {

    Connection conn = null;
    Statement stmt = null;

    ResultSet rs = null;
    Menu menu = new Menu();

    public void getHeroes() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/AGameOfDragons","root", "F@keP@ssw0rd1602");

            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM `Characters`");

            //loop to see all the names one by one
            //while(rs.next()) {
            //System.out.println("Nom des personnages sur la base de données : " + rs.getString("Name"));
            //}

            while (rs.next()) {  // return true if a row exists
                //sysout for the first row = Id
                menu.displayMessage("Nom : " + rs.getString("name"));
                //sysout for the third row = Name
                menu.displayMessage("Type : " + rs.getString("type"));
                //sysout for life points
                menu.displayMessage("Points de vie : " +rs.getString("life"));
                //sysout for Strength
                menu.displayMessage("Position : " + rs.getString("position"));
                //sysout for the row "OffensiveEquipment"
                menu.displayMessage("Equipement offensif : " + rs.getString("offensiveEquipment"));
                //sysout for the row "DefensiveEquipment"
                menu.displayMessage("Equipement defensif : " + rs.getString("defensiveEquipment"));
                menu.displayMessage("------------------------");
            }
        } catch (SQLException ex){
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } finally {
            // it is a good idea to release
            // resources in a finally{} block
            // in reverse-order of their creation
            // if they are no-longer needed

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) { } // ignore
                rs = null;
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) { } // ignore

                stmt = null;
            }
        }
    }

    public void createHero(Character character) {

        String sql = "INSERT INTO `Characters` " + "(name, attack, life, position, offensiveEquipment, DefensiveEquipment, type, offensiveEquipmentType, defensiveEquipmentType) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        Gson gson = new Gson();
        String jsonOffensive = gson.toJson(character.getOffensiveEquipment());
        String jsonDefensive = gson.toJson(character.getDefensiveEquipment());

        //To create the object :
        //OffensiveEquipment offensiveEquipment = gson.fromJson(jsonOffensive, OffensiveEquipment.class);

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/AGameOfDragons","root", "F@keP@ssw0rd1602");

            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, character.getName());
            pstmt.setInt(2, character.getAttack());
            pstmt.setInt(3, character.getLife());
            pstmt.setInt(4, character.getPosition());
            pstmt.setString(5, jsonOffensive);
            pstmt.setString(6, jsonDefensive);
            pstmt.setString(7, character.getType());
            pstmt.setString(8, character.getOffensiveEquipmentType());
            pstmt.setString(9, character.getDefensiveEquipmentType());

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int generatedId = generatedKeys.getInt(1);
                        character.setId(generatedId);
                        System.out.println("Héro sauvé en base de donnée avec l'Id : " + generatedId);
                    }
                }
            }

        }catch (SQLException ex){
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    public void editHero (Character character) {

        String sql = "UPDATE `Characters` " +
                "SET name = ?, attack = ?, life = ?, position = ?, offensiveEquipment = ?, DefensiveEquipment = ?, type = ?, offensiveEquipmentType = ?, defensiveEquipmentType = ? " +
                "WHERE id = ?";

        Gson gson = new Gson();
        String jsonOffensive = gson.toJson(character.getOffensiveEquipment());
        String jsonDefensive = gson.toJson(character.getDefensiveEquipment());

        //To create the object :
        //OffensiveEquipment offensiveEquipment = gson.fromJson(jsonOffensive, OffensiveEquipment.class);

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/AGameOfDragons","root", "F@keP@ssw0rd1602");

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, character.getName());
            pstmt.setInt(2, character.getAttack());
            pstmt.setInt(3, character.getLife());
            pstmt.setInt(4, character.getPosition());
            pstmt.setString(5, jsonOffensive);
            pstmt.setString(6, jsonDefensive);
            pstmt.setString(7, character.getType());
            pstmt.setString(8, character.getOffensiveEquipmentType());
            pstmt.setString(9, character.getDefensiveEquipmentType());
            pstmt.setInt(10, character.getId());


            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Sauvegarde mise à jour!");
            } else {
                System.out.println("Aucun héros trouvé sous ce nom");
            }

        }catch (SQLException ex){
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    public void changeLifePoints(Character character) {
        String sql = "UPDATE `Characters` " +
                "SET life = ?" +
                "WHERE id = ?";

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/AGameOfDragons","root", "F@keP@ssw0rd1602");

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, character.getLife());
            pstmt.setInt(2, character.getId());

            pstmt.executeUpdate();

        }catch (SQLException ex){
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    public void saveBoard(Board board) {

    }
}
