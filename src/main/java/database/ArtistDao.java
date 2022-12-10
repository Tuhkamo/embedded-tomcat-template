package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import model.Artist;

public class ArtistDao {

	private Database db = new Database();

	public boolean addArtist(Artist newArtist) {
		Connection connection = null;
		PreparedStatement query = null;

		try {
			connection = db.connect();
			query = connection.prepareStatement("INSERT INTO Artist (name) VALUES (?)");
			query.setString(1, newArtist.getName());
			query.executeUpdate();

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Database.closeAll(connection, query, null);
		}
		return false;
	}

	public List<Artist> getAllArtists() {
		String getAll = "SELECT ArtistId, Name FROM Artist ORDER BY Name ASC;";

		Connection connection = null;
		PreparedStatement query = null;
		ResultSet results = null;
		List<Artist> allArtists = new ArrayList<>();

		try {
			connection = db.connect();
			query = connection.prepareStatement(getAll);
			results = query.executeQuery();
			while (results.next()) {
				long id = results.getLong("ArtistId");
				String name = results.getString("Name");

				Artist a = new Artist(id, name);
				allArtists.add(a);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Database.closeAll(connection, query, results);
		}

		return allArtists;
	}

}
