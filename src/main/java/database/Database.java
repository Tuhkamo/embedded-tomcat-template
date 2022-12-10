package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// import org.junit.jupiter.api.BeforeEach;

public class Database {

    // read the database connection String from an environment variable:
    private static final String JDBC_URL = System.getenv("JDBC_DATABASE_URL");
    
    Connection connect() throws SQLException {
        return DriverManager.getConnection(JDBC_URL);
    }
    
    public static void closeAll(Connection connection, PreparedStatement query, ResultSet results) {
		if (connection != null) {
	         try {
	            connection.close();
	         } catch (SQLException e) {
	            e.printStackTrace();
	         }
	   }

	   if (query != null) {
	         try {
	            query.close();
	         } catch (SQLException e) {
	            e.printStackTrace();
	         }
	   }

	   if (results != null) {
	         try {
	            results.close();
	         } catch (SQLException e) {
	            e.printStackTrace();
	         }
	   }
	}
}