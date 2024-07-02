import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
    
    public class DataSource {
    
    public static Connection connect() {
        String db_file = "jdbc:sqlite:resources/library.db";

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(db_file);
            System.out.println("connected");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

    public static void main(String[] args) {
        // makeTables();
    }

    public static void makeTables() {
        String createTable = "CREATE TABLE IF NOT EXISTS MusicLibrary (id INTEGER NOT NULL, SongTitle TEXT NOT NULL, Artist TEXT NOT NULL, Album TEXT NOT NULL);";
        String delSQL = "DROP TABLE MusicLibrary";
        try {
            Connection connection = connect();

            PreparedStatement del = connection.prepareStatement(delSQL);
            del.executeUpdate();

            PreparedStatement statement = connection.prepareStatement(createTable);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

