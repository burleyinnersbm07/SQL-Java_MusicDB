package model;

/**
 * The Music class represents a single music object which includes the artist
 * name, album name, label name, and album release year..
 * 
 * @author Brett Burley-Inners
 * @version 20151015
 *
 */
public class Music {

	private String artist;
	private String album;
	private String label;
	private int date;

	public Music() {
		artist = "";
		album = "";
		label = "";
		date = 0000;
	}

	public Music(String artist, String album, String label, int date) {
		this.artist = artist;
		this.album = album;
		this.label = label;
		this.date = date;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getRecordDate() {
		return date;
	}

	public void setRecordDate(int date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return String.format("%s: %s, %s, %4d", this.getArtist(),
				this.getAlbum(), this.getLabel(), this.getRecordDate());
	}
}