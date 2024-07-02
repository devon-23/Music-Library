import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;

import okhttp3.*;
import okhttp3.Request.Builder;

import java.io.IOException;
import com.google.gson.*;
import java.util.*;

    
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
        LastFM apiClient = new LastFM();
        String[][] topTracks = apiClient.getTopTracks(); 
        addSong(topTracks);
    }

    public static void makeTables() {
        String createTable = "CREATE TABLE IF NOT EXISTS MusicLibrary (id INTEGER NOT NULL, songTitle TEXT NOT NULL, artistName TEXT NOT NULL, albumArtwork TEXT NOT NULL);";
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

    public static void addSong(String[][] topTracks) {
        String sql = "DELETE FROM MusicLibrary; ";

        for (int i = 0; i < topTracks[0].length; i++) {
            String insert = "\"%s\", \"%s\", \"%s\", \"%s\"";
            String query = String.format(insert, (i+1), topTracks[0][i], topTracks[1][i], topTracks[2][i]);
            sql += "INSERT INTO MusicLibrary (id, songTitle, artistName, albumArtwork) VALUES (" + query + "); ";
        }

        try (Connection connection = connect();
            Statement statement = connection.createStatement()) {
            String[] queries = sql.split(";");
            for (String query : queries) {
                if (query.trim().isEmpty()) {
                    continue;
                } else {
                    statement.execute(query.trim());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Song getSong(String songTitle) {
        String sql = "select * from library where songTitle = ?";
        Song song = null;

        return song;
    }
}

