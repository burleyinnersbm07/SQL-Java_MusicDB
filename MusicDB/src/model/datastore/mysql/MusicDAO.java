package model.datastore.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Music;
import model.IMusicDAO;

/**
 * This class controls access to the database. The class implements
 * IMusicDAO as an interface.
 * 
 * @author Brett Burley-Inners
 * @version 20151015
 *
 */
public class MusicDAO implements IMusicDAO {
	
	protected final static boolean DEBUG = true; // shows queries and commands

	/**
	 * This method creates a new record. It uses SQL notation, stored in a variable, to create
	 * the record.
	 * 
	 * @param music a Music object. The object music is created in MusicApp.java when the user adds a new artist.
	 * @throws SQLException ex
	 * 
	 */
	@Override
	public void createRecord(Music music) {
		final String QUERY = "insert into music (artist, album, label, released) VALUES (?, ?, ?, ?)";
		// closes the connections when they're done being used
		try (Connection con = DBConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(QUERY);) {
			stmt.setString(1, music.getArtist());
			stmt.setString(2, music.getAlbum());
			stmt.setString(3, music.getLabel());
			stmt.setInt(4, music.getRecordDate());
			if(DEBUG) System.out.println(stmt.toString());
			stmt.executeUpdate();

		} catch (SQLException ex) {
			System.out.println("createRecord SQLException: " + ex.getMessage());
		}
	}
	
	/**
	 * This method retrieves all entries, or rows, in the database. It prints them in ascending
	 * order by the artist name. It uses the toString() method from Music.java to format the output.
	 * 
	 * @return returns an ArrayList of Music objects
	 * @throws SQLException ex
	 * 
	 */
	@Override
	public List<Music> retrieveAllRecords() {
		final List<Music> myList = new ArrayList<>();
		final String QUERY = "select artist, album, label, released from music order by artist";

		try (Connection con = DBConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(QUERY)) {
			if(DEBUG) System.out.println(stmt.toString());
			ResultSet rs = stmt.executeQuery(QUERY);
			
			while (rs.next()) {
				myList.add(new Music(rs.getString("artist"), rs.getString("album"),
						rs.getString("label"), rs.getInt("released")));
			}
		} catch (SQLException ex) {
			System.out.println("retrieveAllRecords SQLException: " + ex.getMessage());
		}

		return myList;
	}

	/**
	 * This method updates an existing record based on the values passed from MusicApp.java as a Music object.
	 * It uses SQL notation to update the record.
	 * 
	 * @param updatedMusic a Music object. The object music is created in MusicApp.java when the user updates a record.
	 * @throws SQLException ex
	 * 
	 */
	@Override
	public void updateRecord(Music updatedMusic) {
		final String QUERY = "update music set artist=?, album=?, label=?, released=? where artist=?";

		try (Connection con = DBConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(QUERY)) {
			stmt.setString(1, updatedMusic.getArtist());
			stmt.setString(2, updatedMusic.getAlbum());
			stmt.setString(3, updatedMusic.getLabel());
			stmt.setDouble(4, updatedMusic.getRecordDate());
			stmt.setString(5, updatedMusic.getArtist());
			if(DEBUG) System.out.println(stmt.toString());
			stmt.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("updateRecord SQLException: " + ex.getMessage());
		}
	}

	/**
	 * This method deletes a record. It uses SQL notation, stored in a variable, to delete the record.
	 * Requies the artist name to delete the record. This method will delete all entries for a specified
	 * artist.
	 * 
	 * @param artist a String. The String is passed from MusicApp.java when the user deletes a record.
	 * The method uses this String to search the databases for the record(s) to remove.
	 * @throws SQLException ex
	 * 
	 */
	@Override
	public void deleteRecord(String artist) {
		final String QUERY = "delete from music where artist = ?";

		try (Connection con = DBConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(QUERY)) {
			stmt.setString(1, artist);
			if(DEBUG) System.out.println(stmt.toString());
			stmt.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("deleteRecord SQLException: " + ex.getMessage());
		}
	}

	/**
	 * This method deletes a record when passed a music object from the MusicApp.java class.
	 * 
	 * @param music a Music object. Passed from MusicApp.java when the user specifies the artist
	 * to delete.
	 * @throws SQLException ex
	 * 
	 */
	@Override
	public void deleteRecord(Music music) {
		final String QUERY = "delete from music where artist = ?";

		try (Connection con = DBConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(QUERY)) {
			stmt.setString(1, music.getArtist());
			if(DEBUG) System.out.println(stmt.toString());
			stmt.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("deleteRecord SQLException: " + ex.getMessage());
		}
	}
	
	/**
	 * This method searches the database to find all data for a given artist. Because identification
	 * numbers aren't used in this database, all queries are done by artist. This method uses the artist
	 * name, passed from MusicApp.java to search the database with a simple SQL query
	 * 
	 * @return This method returns an ArrayList of Music objects. The toString() method from Music.java is
	 * used for formatting the output.
	 * @param artist a String. The String is passed from MusicApp.java when the user searches the database.
	 * The method uses this String to search the databases for the record(s) to display.
	 * @throws SQLException ex
	 * 
	 */
	@Override
	public List<Music> searchArtist(String artist) {
		final List<Music> mu = new ArrayList<>();
		final String QUERY = "select * from music where artist = '" + artist + "'";
		// final String QUERY = "select id, artist, album, label,
		// released from music where artist = ?";
		
		try (Connection con = DBConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(QUERY)) {
			if(DEBUG) System.out.println(stmt.toString());
			ResultSet rs = stmt.executeQuery(QUERY);
			
			while (rs.next()) {
				mu.add(new Music(rs.getString("artist"), rs.getString("album"),
						rs.getString("label"), rs.getInt("released")));
			}
		} catch (SQLException ex) {
			System.out.println("searchArtist SQLException: " + ex.getMessage());
		}

		return mu;
	}
	
	/**
	 * This method returns all of the artists associated with a specific label. It returns the artists
	 * as an ArrayList of Strings. The label is entered by the user in the MusicApp.java class. Queries to
	 * the database are made with SQL syntax.
	 * 
	 * @param label	A String. This String is used to query the database. It is passed from the
	 * MusicApp.java class.
	 * @return This method returns an ArrayList of String objects.
	 */
	@Override
	public List<String> artistByLabel(String label) {
		final List<String> artists = new ArrayList<>();
		final String QUERY = "select artist from music where label = '" + label + "'";
		
		try (Connection con = DBConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(QUERY)) {
			if(DEBUG) System.out.println(stmt.toString());
			ResultSet rs = stmt.executeQuery(QUERY);
			
			while (rs.next()) {
				artists.add(rs.getString("artist"));
			}
		} catch (SQLException ex) {
			System.out.println("artistByLabel SQLException: " + ex.getMessage());
		}
		
		return artists;
	}
	
	/**
	 * This method returns only the albums names for a specified artist.
	 * 
	 * @param artist A String. This String is used to query the database. It is passed from the
	 * MusicApp.java class.
	 * @return This method returns an ArrayList of String objects.
	 */
	@Override
	public List<String> albumsByArtist(String artist) {
		final List<String> albums = new ArrayList<>();
		final String QUERY = "SELECT album FROM music WHERE artist='" + artist + "'";
		
		try (Connection con = DBConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(QUERY)) {
			if(DEBUG) System.out.println(stmt.toString());
			ResultSet rs = stmt.executeQuery(QUERY);
			
			while (rs.next()) {
				albums.add(rs.getString("album"));
			}
		} catch (SQLException ex) {
			System.out.println("albumsByArtist SQLException: " + ex.getMessage());
		}
		
		return albums;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		for (Music music : retrieveAllRecords()) {
			sb.append(music.toString() + "\n");
		}

		return sb.toString();
	}
}
