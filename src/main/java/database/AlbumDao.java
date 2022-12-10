package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import model.Album;

public class AlbumDao {

	private Database db = new Database();

	public List<Album> getAlbums(long id) {
		String getAll = "SELECT AlbumId, Title, ArtistId FROM Album WHERE ArtistId = ?;";

		Connection connection = null;
		PreparedStatement query = null;
		ResultSet results = null;
		List<Album> allAlbums = new ArrayList<>();

		try {
			connection = db.connect();
			query = connection.prepareStatement(getAll);
			query.setLong(1, id);
			results = query.executeQuery();
			while (results.next()) {
				long albumId = results.getLong("AlbumId");
				String title = results.getString("Title");
				long artistId = results.getLong("ArtistId");

				Album a = new Album(albumId, title, artistId);
				allAlbums.add(a);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Database.closeAll(connection, query, results);
		}
		return allAlbums;
	}

}
