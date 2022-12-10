// tiedosto JDBCShoppingListItemDao.java

package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// import org.junit.jupiter.api.BeforeEach;

import model.ShoppingListItem;

public class JDBCShoppingListItemDao implements ShoppingListItemDao {

    // read the database connection String from an environment variable:
    private static final String JDBC_URL = System.getenv("JDBC_DATABASE_URL");
    
    Connection connect() throws SQLException {
        return DriverManager.getConnection(JDBC_URL);
    }
    
    @Override
    public List<ShoppingListItem> getAllItems() {
    	Connection connection = null;
		PreparedStatement query = null;
		ResultSet results = null;
		
		List<ShoppingListItem> items = new ArrayList<>();
		
		try {
			connection = DriverManager.getConnection(JDBC_URL);
			query = connection.prepareStatement("SELECT * FROM ShoppingListItem");
	        results = query.executeQuery();
	        
	        while (results.next()) {
	            long id = results.getLong("id");
	            String title = results.getString("title");
	            ShoppingListItem newItem = new ShoppingListItem(id, title);

	            items.add(newItem);
	        }
	        
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(connection, query, results);
		}
		return items;
    }

    @Override
    public ShoppingListItem getItem(long id) {
    	Connection connection = null;
		PreparedStatement query = null;
		ResultSet results = null;
		
		
		try {
			connection = DriverManager.getConnection(JDBC_URL);
			query = connection.prepareStatement("SELECT * FROM ShoppingListItem WHERE id = ?");
			query.setLong(1, id);
			results = query.executeQuery();
		
			String title = results.getString("title");
			ShoppingListItem item = new ShoppingListItem(id, title);
			return item;
	        
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(connection, query, results);
		}
		return null;
    }

    @Override
    public boolean addItem(ShoppingListItem newItem) {
    	Connection connection = null;
		PreparedStatement query = null;
		
		try {
			connection = DriverManager.getConnection(JDBC_URL);
			query = connection.prepareStatement("INSERT INTO ShoppingListItem (title) VALUES (?)");
			query.setString(1, newItem.getTitle());
			query.executeUpdate();
			
			return true;
	        
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(connection, query, null);
		}
        return false;
    }

    @Override
    public boolean removeItem(ShoppingListItem item) {
    	Connection connection = null;
		PreparedStatement query = null;
		
		try {
			connection = DriverManager.getConnection(JDBC_URL);
			query = connection.prepareStatement("DELETE FROM ShoppingListItem WHERE id = ?");
			query.setLong(1, item.getId());
			int deleted = query.executeUpdate();
			
			if (deleted == 0) {
				return false;
			} else {
				query.executeUpdate();
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(connection, query, null);
		}
		return false;
    }
    
    private static void closeAll(Connection connection, PreparedStatement query, ResultSet results) {
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