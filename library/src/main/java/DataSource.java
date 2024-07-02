import java.sql.Connection;
    import java.sql.DriverManager;
    import java.sql.PreparedStatement;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    
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
        connect();
    }
}

