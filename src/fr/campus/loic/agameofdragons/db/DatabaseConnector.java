package fr.campus.loic.agameofdragons.db;

import com.google.gson.Gson;
import fr.campus.loic.agameofdragons.Menu;
import fr.campus.loic.agameofdragons.characters.Character;
import fr.campus.loic.agameofdragons.material.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class that contains all the methods to interact with the database
 */
public class DatabaseConnector {

    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    Menu menu = new Menu();

    /**
     * Displays on the console all the characters saved in the database
     */
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

    /**
     * Saves in the databse the character newly created. It also sets an unique ID from the database Id to avoid problems if multiple characters share the same name
     *
     * @param character is the character created
     */
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

    /**
     * Saves the attributes of the character (like his life points, his position, his equipment) in the database. The old values are erased.
     *
     * @param character is the character played with
     */
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

    /**
     * Saved the value of the attribute "life". The old value is erased.
     *
     * @param character is the character played with
     */
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

    /**
     * Saves the board in the database. All the tiles are saved one by one, in the same order as they are listed.
     *
     * @param board is the board generated at the beginning of the game
     */
    public void createBoard(Board board) {

        String sql = "INSERT INTO Board " + "(numTiles) VALUES (?)";
        int boardId = 0;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/AGameOfDragons","root", "F@keP@ssw0rd1602");

            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setInt(1, board.getNumTiles());

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int generatedId = generatedKeys.getInt(1);
                        board.setId(generatedId);
                        boardId = generatedId;
                    }
                }
            }

        }catch (SQLException ex){
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        // language=SQL
        sql = "INSERT INTO Cell (board_id, `index`, isAlreadyVisited, type, defensiveEquipment, offensiveEquipment, enemy) VALUES (?, ?, ?, ?, ?, ?, ?)";

        Gson gson = new Gson();

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/AGameOfDragons","root", "F@keP@ssw0rd1602");

            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            List<Cell> cells = new ArrayList<>();
            cells = board.getTiles();
            int index = 0;

            for (Cell cell : cells) {
                pstmt.setInt(1, boardId);
                pstmt.setInt(2, index);
                pstmt.setBoolean(3, cell.getIsAlreadyVisited());
                pstmt.setString(4, cell.getClass().getSimpleName());
                if (cell instanceof DefensiveBonusCell) {
                    String jsonDefensive = gson.toJson(cell.getContent());
                    pstmt.setString(5, jsonDefensive);
                } else {
                    pstmt.setNull(5, java.sql.Types.VARCHAR);
                }
                if (cell instanceof OffensiveBonusCell) {
                    String jsonOffensive = gson.toJson(cell.getContent());
                    pstmt.setString(6, jsonOffensive);
                } else {
                    pstmt.setNull(6, java.sql.Types.VARCHAR);
                }
                if (cell instanceof EnemyCell) {
                    String jsonEnemy = gson.toJson(cell.getContent());
                    pstmt.setString(7, jsonEnemy);
                } else {
                    pstmt.setNull(7, java.sql.Types.VARCHAR);
                }
                pstmt.executeUpdate();
                index++;
            }

        }catch (SQLException ex){
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}
