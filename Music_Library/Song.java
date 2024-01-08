/*
 * A Song is a type of AudioContent. A Song has extra fields such as Artist (person(s) singing the song) and composer 
 */



public class Song extends AudioContent implements Comparable<Song> // implement the Comparable interface
{
	public static final String TYPENAME =	"SONG";
	
	public static enum Genre {POP, ROCK, JAZZ, HIPHOP, RAP, CLASSICAL}; 
	private String artist; 		// Can be multiple names separated by commas
	private String composer; 	// Can be multiple names separated by commas
	private Genre  genre; 
	private String lyrics;
	
	
	
	public Song(String title, int year, String id, String type, String audioFile, int length, String artist,
			String composer, Song.Genre genre, String lyrics)
	{
		// Make use of the constructor in the super class AudioContent. 
		// Initialize additional Song instance variables. 

		// Calls the constructor in the super class AudioContent with the appropriate parameters
		super(title, year, id, type, audioFile, length);

		// Intializes the additional instance variables 
		this.artist = artist;
		this.composer = composer;
		this.genre = genre;
		this.lyrics = lyrics;
	}
	
	public String getType()
	{
		return TYPENAME;
	}
	
	// Print information about the song. First print the basic information of the AudioContent 
	// by making use of the printInfo() method in superclass AudioContent and then print artist, composer, genre 
	public void printInfo()
	{

		// Calls the printInfo of the super class AudioContent to print information on the song
		super.printInfo();

		// Prints the artist, composer, and genre, of the song 
		System.out.println("Artist: " + artist + " Composer: " + composer + " Genre: " + genre);

	}
	
	// Play the song by setting the audioFile to the lyrics string and then calling the play() method of the superclass
	public void play()
	{
		// Sets the audioFile to the lyrics of the song 
		setAudioFile(lyrics);

		// Plays the audioFile
		super.play();
	}
	
	public String getComposer()
	{
		return composer;
	}
	public void setComposer(String composer)
	{
		this.composer = composer;
	}
	
	public String getArtist()
	{
		return artist;
	}
	public void setArtist(String artist)
	{
		this.artist = artist;
	}
	
	public String getLyrics()
	{
		return lyrics;
	}
	public void setLyrics(String lyrics)
	{
		this.lyrics = lyrics;
	}

	public Genre getGenre()
	{
		return genre;
	}

	public void setGenre(Genre genre)
	{
		this.genre = genre;
	}	
	
	// Two songs are equal if their AudioContent information is equal and both the composer and artists are the same
	// Make use of the superclass equals() method
	public boolean equals(Object other)
	{

		// Creates a song variable called other song, which is intialized to other typecasted to Song
		Song otherSong = (Song)(other);

		// Returns boolean value depending on if the AudioContent info, composer, and artist of the two songs are the same
		return super.equals(otherSong) && composer.equals(otherSong.composer) && artist.equals(otherSong.artist);

	}
	
	// Implement the Comparable interface 
	// Compare two songs based on their title
	// This method will allow songs to be sorted alphabetically
	public int compareTo(Song other)
	{
		// Returns the integer resulting from comparing the titles of the two songs
		return getTitle().compareToIgnoreCase(other.getTitle());

	}
}
