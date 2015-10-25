package model;

import java.util.List;

/**
 * @author Brett Burley-Inners
 * @version 20151009
 *
 */
public interface IMusicDAO {

	void createRecord(Music employee);

	List<Music> retrieveAllRecords();

	void updateRecord(Music updatedEmployee);

	void deleteRecord(String artist);

	void deleteRecord(Music employee);

	String toString();

	List<Music> searchArtist(String artist);

	List<String> artistByLabel(String label);

	List<String> albumsByArtist(String artist);

}