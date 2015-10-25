package viewui;

import java.util.Scanner;

import model.Music;
import model.IMusicDAO; // working through the interface!
import model.datastore.mysql.MusicDAO; // choice between mySQL or file

/**
 * Simple text-based application for MusicDB. Accesses the methods from MusicDAO.java
 * to access the database.
 * 
 * @author Brett Burley-Inners
 * @version 20151015
 * 
 */
public class MusicApp {

	IMusicDAO musList = new MusicDAO();
	Scanner sc = new Scanner(System.in);

	/**
	 * I can't remember the proper name for this. This is created to call another method.
	 * 
	 * 
	 * 
	 */
	public MusicApp() {
		menuLoop();
	}

	/**
	 * This method is the menu. It loops until the option to exit is chosen. It uses a
	 * switch statement to manage the user's selection.
	 * 
	 * 
	 */
	private void menuLoop() {
		int released;
		String artist, album, label;
		@SuppressWarnings("unused")
		String art = "";
		String choice = "1";
		while (!choice.equals("0")) {
			System.out.println("\nYour Music");
			System.out.println("0 = Quit");
			System.out.println("1 = List All");
			System.out.println("2 = Add new"); //C
			System.out.println("3 = Search by Label"); //R
			System.out.println("4 = Search for Artist"); //R
			System.out.println("5 = Get Albums by Artist"); //R
			System.out.println("6 = Update"); //U
			System.out.println("7 = Delete"); //D
			choice = Validator.getLine(sc, "Number of choice: ", "^[0-7]$");

			switch (choice) {
			case "1":
				System.out.println(musList.toString());
				break;
			case "2":
				artist = Validator.getLine(sc, "Artist name: ");
				album = Validator.getLine(sc, "Album name: ");
				label = Validator.getLine(sc, "Label name: ");
				released = Validator.getInt(sc, "Year released: ");
				musList.createRecord(new Music(artist, album, label, released));
				break;
			case "3":
				label = Validator.getLine(sc, "Label: ");
				System.out.println(musList.artistByLabel(label));
				break;
			case "4":
				artist = Validator.getLine(sc, "Artist name: ");
				System.out.println(musList.searchArtist(artist));
				break;
			case "5":
				artist = Validator.getLine(sc, "Select artist: ");
				System.out.println(musList.albumsByArtist(artist).size() + " album(s) found: " +  musList.albumsByArtist(artist));
				break;
			case "6":
				art = Validator.getLine(sc, "Artist to Update: ");
				artist = Validator.getLine(sc, "Artist name: ");
				album = Validator.getLine(sc, "Album name: ");
				label = Validator.getLine(sc, "Label name: ");
				released = Validator.getInt(sc, "Year released: ");
				musList.updateRecord(new Music(artist, album, label, released));
				break;
			case "7":
				artist = Validator.getLine(sc, "Artist to delete: ");
				String ok = Validator.getLine(sc, "Delete this record? (y/n) ", "^[yYnN]$");
				if (ok.equalsIgnoreCase("Y")) {
					musList.deleteRecord(artist);
				}
				break;
			
			}
		}
	}

	/**
	 * Main method. Calls the MusicApp() method to start the program.
	 * 
	 */
	public static void main(String[] args) {
		new MusicApp();
	}
}
