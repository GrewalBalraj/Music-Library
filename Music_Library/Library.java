import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
// Name: Balraj Grewal
// Student #: 501166280

/*
 * This class manages, stores, and plays audio content such as songs, podcasts and audiobooks. 
 */
public class Library
{
	private ArrayList<Song> 			songs; 
	private ArrayList<AudioBook> 	audiobooks;
	private ArrayList<Playlist> 	playlists; 
	
  //private ArrayList<Podcast> 	podcasts;
	
	// Public methods in this class set errorMesg string 
	// Error Messages can be retrieved from main in class MyAudioUI by calling  getErrorMessage()
	// In assignment 2 we will replace this with Java Exceptions
	String errorMsg = "";
	
	public String getErrorMessage()
	{
		return errorMsg;
	}

	public Library()
	{
		songs 			= new ArrayList<Song>(); 
		audiobooks 	= new ArrayList<AudioBook>(); ;
		playlists   = new ArrayList<Playlist>();
	  //podcasts		= new ArrayList<Podcast>(); ;
	}
	/*
	 * Download audio content from the store. Since we have decided (design decision) to keep 3 separate lists in our library
	 * to store our songs, podcasts and audiobooks (we could have used one list) then we need to look at the type of
	 * audio content (hint: use the getType() method and compare to Song.TYPENAME or AudioBook.TYPENAME etc)
	 * to determine which list it belongs to above
	 * 
	 * Make sure you do not add song/podcast/audiobook to a list if it is already there. Hint: use the equals() method
	 * If it is already in a list, set the errorMsg string and return false. Otherwise add it to the list and return true
	 * See the video
	 */
	public void download(AudioContent content) throws AudioContentAlreadyDownloadedException
	{

		// Compares the type of the content to Song.TYPENAME to check if content is a song
		if (content.getType().equals(Song.TYPENAME)){

			// For loop that iterates through all the indexes of the songs arraylist
			for (int i = 0; i < songs.size(); i++) {

				// Checks if content is the same as song at index i in the songs arraylist
				if (content.equals(songs.get(i))){

					// Throws exception 
					throw new AudioContentAlreadyDownloadedException("Song " + songs.get(i).getTitle() + " Already Downloaded");
				}
			}

			// Adds content (typecasted to Song) to the songs arraylist 
			songs.add((Song)(content));
		}

		// Else if statement that compares the tyoe of content to AudioBook.TYPENAME to check if content is an audiobook
		else if (content.getType().equals(AudioBook.TYPENAME)) {

			// For loop that iterates through all the indexes of the audiobooks arraylist
			for (int i = 0; i < audiobooks.size(); i++){

				// Checks if content is the same as audiobook at index i in the audiobooks arraylist
				if (content.equals(audiobooks.get(i))){

					// Throw exception
					throw new AudioContentAlreadyDownloadedException("AudioBook " + audiobooks.get(i).getTitle() + " Already Downloaded");
				}
			}

			// Adds content (typecasted to AudioBook) to the audiobooks array list
			audiobooks.add((AudioBook)(content));
		}


	}
	
	// Print Information (printInfo()) about all songs in the array list
	public void listAllSongs()
	{
		for (int i = 0; i < songs.size(); i++)
		{
			int index = i + 1;
			System.out.print("" + index + ". ");
			songs.get(i).printInfo();
			System.out.println();	
		}
	}
	
	// Print Information (printInfo()) about all audiobooks in the array list
	public void listAllAudioBooks()
	{
		// For loop that iterates through all the indexes of the audiobooks arraylist
		for (int i = 0; i < audiobooks.size(); i++)
		{
			// Creates index variable which is intialized to iteration variable i plus 1
			int index = i + 1;

			// Lists and prints out information for audiobook  
			System.out.print("" + index + ". ");
			audiobooks.get(i).printInfo();
			System.out.println();	
		}
	}
	
  // Print Information (printInfo()) about all podcasts in the array list
	public void listAllPodcasts()
	{
		
	}
	
  // Print the name of all playlists in the playlists array list
	// First print the index number as in listAllSongs() above
	public void listAllPlaylists()
	{
		// For loop that iterates through all the indexes of the playlists arraylist
		for (int i = 0; i < playlists.size(); i++)
		{
			// Creates index variable which is intialized to iteration variable i plus 1
			int index = i + 1;

			// Lists and prints the title of a playlist
			System.out.println("" + index + ". " + playlists.get(i).getTitle());	
		}
	}
	
  // Print the name of all artists. 
	public void listAllArtists()
	{
		// First create a new (empty) array list of string 
		// Go through the songs array list and add the artist name to the new arraylist only if it is
		// not already there. Once the artist arraylist is complete, print the artists names

		// Creates a string arraylist variable artistsList
		ArrayList<String> artistsList = new ArrayList<String>();

		// For loop that iterates through all of the indexes of the songs arraylist
		for (int i = 0; i < songs.size(); i++){

			// If the artistsList does not contain an artist, the artist is added to the artistList
			if (!artistsList.contains(songs.get(i).getArtist())){
				artistsList.add(songs.get(i).getArtist());
			}
		}

		// For loop that goes through all of the indexes in the artistList arraylist
		for (int i = 0; i < artistsList.size(); i++){

			// Lists and prints the name of the artist
			System.out.println((i + 1) + ". " + artistsList.get(i));
		}
		
	}

	// Delete a song from the library (i.e. the songs list) - 
	// also go through all playlists and remove it from any playlist as well if it is part of the playlist
	public void deleteSong(int index) throws AudioContentNotFoundException
	{ 	
		// Checks if index is not in correct range
		if ((index > songs.size() || index < 1)){

			// throw exception 
			throw new AudioContentNotFoundException("Song Not Found");
		} 

		// For loop that iterates through all of the indexes of the playlists arraylist
		for (int i = 0; i < playlists.size(); i++){

			// For loop that iterates through all the indexes of the contents arraylist of the playlist at index i 
			for (int j = 0; j < playlists.get(i).getContent().size(); j++){

				// Checks if content is a song
				if (playlists.get(i).getContent().get(j).getType().equals(Song.TYPENAME)){

					/* If the content at index j in the playlist is equal to the song 
					* at index - 1 in the songs arraylist, remove that song from the content of the playlist and break */
					if (playlists.get(i).getContent().get(j).equals(songs.get(index - 1))){
						playlists.get(i).getContent().remove(j);
						break;
					}
				}
			}
		}

		// Removes the song at index - 1
		songs.remove(index - 1);
		
	}
	
  //Sort songs in library by year
	public void sortSongsByYear()
	{
		// Use Collections.sort() 
		Collections.sort(songs, new SongYearComparator());
	
	}
  // Write a class SongYearComparator that implements
	// the Comparator interface and compare two songs based on year
	private class SongYearComparator implements Comparator<Song>
	{
		// Compares two songs based on year
		public int compare(Song song1, Song song2)
		{
			// Returns 1 if the year of song1 is larger than year of song2
			if (song1.getYear() > song2.getYear()){
				return 1;
			
		    // Returns -1 if the year of song2 is larger than year of song1
			} else if (song2.getYear() > song1.getYear()){
				return -1;
			
			// Returns 0 if the years of the two songs are the same 
			} else {
				return 0;
			}
		}
		
	}

	// Sort songs by length
	public void sortSongsByLength()
	{

		// Use Collections.sort() 
		Collections.sort(songs, new SongLengthComparator());

	}
  // Write a class SongLengthComparator that implements
	// the Comparator interface and compare two songs based on length
	private class SongLengthComparator implements Comparator<Song>
	{
		// Compares two songs based on their length
		public int compare(Song song1, Song song2){

			// Returns 1 if the length of song1 is larger than length of song2
			if (song1.getLength() > song2.getLength()){
				return 1;
			
			// Returns -1 if the length of song2 is larger than length of song1
			} else if (song2.getLength() > song1.getLength()){
				return -1;
			
			// Returns 0 if the length of the two songs are the same
			} else {
				return 0;
			}
		}
		
	}

	// Sort songs by title 
	public void sortSongsByName()
	{
	  // Use Collections.sort()
		// class Song should implement the Comparable interface
		// see class Song code
		Collections.sort(songs);
	}

	
	
	/*
	 * Play Content
	 */
	
	// Play song from songs list
	public void playSong(int index) throws AudioContentAlreadyDownloadedException
	{
		if (index < 1 || index > songs.size())
		{
			// throw exception 
			throw new AudioContentNotFoundException("Song Not Found");
		}
		songs.get(index-1).play();
	}
	
	// Play podcast from list (specify season and episode)
	// Bonus
	public boolean playPodcast(int index, int season, int episode)
	{
		return false;
	}
	
	// Print the episode titles of a specified season
	// Bonus 
	public boolean printPodcastEpisodes(int index, int season)
	{
		return false;
	}
	
	// Play a chapter of an audio book from list of audiobooks
	public void playAudioBook(int index, int chapter) throws AudioContentNotFoundException, ChapterNotFoundException
	{
		// Checks if the index is not in correct range 
		if (index < 1 || index > audiobooks.size())
		{
			// throw exception 
			throw new AudioContentNotFoundException("AudioBook Not Found");
		
		// Checks if chapter is not in correct range
		} else if (chapter < 1 || chapter > audiobooks.get(index - 1).getNumberOfChapters()){

			// throw exception 
			throw new ChapterNotFoundException("Chapter Not Found");
		}

		// Changes the chapter of the audiobook
		audiobooks.get(index - 1).selectChapter(chapter);

		// Plays the audiobook 
		audiobooks.get(index-1).play();
	}
	
	// Print the chapter titles (Table Of Contents) of an audiobook
	// see class AudioBook
	public void printAudioBookTOC(int index) throws AudioContentNotFoundException
	{
		// Checks if index is not in correct range
		if (index < 1 || index > audiobooks.size())
		{
			// throw exception 
			throw new AudioContentNotFoundException("AudioBook Not Found");
		}

		// Prints the table of contents of the audiobook
		audiobooks.get(index-1).printTOC();
	}
	
  /*
   * Playlist Related Methods
   */
	
	// Make a new playlist and add to playlists array list
	// Make sure a playlist with the same title doesn't already exist
	public void makePlaylist(String title) throws PlaylistAlreadyExistsException
	{

		// Creates a Playlist variable called newPlaylist
		Playlist newPlaylist = new Playlist(title);

		// For loop that iterates through all the indexes of the playlists arraylist
		for (int i = 0; i < playlists.size(); i++){

			// Checks if newPlaylist is equal to playlist at index i
			if (newPlaylist.equals(playlists.get(i))){

				// throw exception 
				throw new PlaylistAlreadyExistsException("Playlist " + title + " Already Exists");
			}
		}

		// Adds newPlaylist to playlists arraylist 
		playlists.add(newPlaylist);
	}
	
	// Print list of content information (songs, audiobooks etc) in playlist named title from list of playlists
	public void printPlaylist(String title) throws PlaylistNotFoundException
	{
		// For loop that iterates through all the indexes of the playlists arraylist
		for (int i = 0; i < playlists.size(); i++){

			// Checks if playlist at index i has the same title as the String variable title
			if (playlists.get(i).getTitle().equals(title)){

				// Prints the contents of playlist at index i and returns
				playlists.get(i).printContents();
				return;
			}
		}

		// throw exception 
		throw new PlaylistNotFoundException("Playlist " + title + " Not Found");
	}
	
	// Play all content in a playlist
	public void playPlaylist(String playlistTitle) throws PlaylistNotFoundException
	{
		// For loop that iterates through all the indexes of the playlist arraylist
		for (int i = 0; i < playlists.size(); i++){

			// Checks if the title of the playlist at index i is equal to playlistTitle
			if (playlists.get(i).getTitle().equals(playlistTitle)){

				// Play all the content of the playlist at index i and returns
				playlists.get(i).playAll();
				return;
			}
		}

		// throw exception 
		throw new PlaylistNotFoundException("Playlist " + playlistTitle + " Not Found");
	}
	
	// Play a specific song/audiobook in a playlist
	public void playPlaylist(String playlistTitle, int indexInPL) throws AudioContentNotFoundException, PlaylistNotFoundException
	{
		// For loop that iterates through all the indexes of the playlists arraylist
		for (int i = 0; i < playlists.size(); i++){

			// Checks if the title of the playlist at index i is equal to playlistTitle
			if (playlists.get(i).getTitle().equals(playlistTitle)){

				// Checks if the playlist at index i contains indexInPL
				if (playlists.get(i).contains(indexInPL)){

					// Plays the AudioContent at indexInPL in the playlist and returns
					playlists.get(i).play(indexInPL);
					return;

				} else {

					// throw exception 
					throw new AudioContentNotFoundException("AudioContent Not Found");
				}
				
			}
		}

		// throw exception 
		throw new PlaylistNotFoundException("Playlist " + playlistTitle + " Not Found");
	}
	
	// Add a song/audiobook/podcast from library lists at top to a playlist
	// Use the type parameter and compare to Song.TYPENAME etc
	// to determine which array list it comes from then use the given index
	// for that list
	public void addContentToPlaylist(String type, int index, String playlistTitle) throws AudioContentNotFoundException, ContentTypeNotFoundException, PlaylistNotFoundException
	{

		// For loop that iterates through all the indexes of the playlists arraylist
		for (int i = 0; i < playlists.size(); i++){
			
			// Checks if the title of the playlist at index i is equal to playlistTitle
			if (playlists.get(i).getTitle().equals(playlistTitle)){

				// Checks if the type is a song
				if (type.equalsIgnoreCase(Song.TYPENAME)){ 

					// Checks if index is in correct range
					if (1 <= index && index <= songs.size()){

						// Adds song at index - 1 to the playlist at index i and returns
						playlists.get(i).addContent(songs.get(index - 1));
						return;

					} else {

						// throw exception 
						throw new AudioContentNotFoundException("Song Not Found");
					}
				
				// Else if the type is an audiobook
				} else if (type.equalsIgnoreCase(AudioBook.TYPENAME)){

					// Checks if index is in the correct range
					if (1 <= index && index <= audiobooks.size()){

						// Adds audiobook at index - 1 to playlist at index i and returns
						playlists.get(i).addContent(audiobooks.get(index - 1));
						return;

					} else {

						// throw exception 
						throw new AudioContentNotFoundException("AudioBook Not Found");
					}

				// Else statement that runs when type is not equal to either song or audiobook
				} else {

					// throw exception 
					throw new ContentTypeNotFoundException("Content Type " + type + " Not Found");
				}
			}
		}

		// throw exception 
		throw new PlaylistNotFoundException("Playlist " + playlistTitle + " Not Found");
	}

  // Delete a song/audiobook/podcast from a playlist with the given title
	// Make sure the given index of the song/audiobook/podcast in the playlist is valid 
	public void delContentFromPlaylist(int index, String title) throws AudioContentNotFoundException, PlaylistNotFoundException
	{
		// For loop that iterates through all of the indexes of the playlists arraylist
		for (int i = 0; i < playlists.size(); i++){

			// Checks if the title of the playlist at index i is equal to String variable title
			if (playlists.get(i).getTitle().equals(title)){

				// Checks if the playlist at i contains the index 
				if (playlists.get(i).contains(index)){

					// Deletes the content at index from the playlist and returns
					playlists.get(i).deleteContent(index);
					return;

				} else {

					// throw exception 
					throw new AudioContentNotFoundException("AudioContent Not Found");
				}
				
			}
		}

		// throw exception 
		throw new PlaylistNotFoundException("Playlist " + title + " Not Found");
	}
	
} 

// ALL EXCEPTION CLASSES

// Creates AudioContentNotFoundException that extends RunTimeException
// Exception for when an AudioContent cannot be found
class AudioContentNotFoundException extends RuntimeException {

	public AudioContentNotFoundException(){}

	public AudioContentNotFoundException(String message){
		super(message);
	}
}

// Creates AudioContentAlreadyDownloadedException that extends RunTimeException
// Exception for when an AudioContent has already been downloaded
class AudioContentAlreadyDownloadedException extends RuntimeException {

	public AudioContentAlreadyDownloadedException(){}

	public AudioContentAlreadyDownloadedException(String message){
		super(message);
	}
}

// Creates ChapterNotFoundException that extends RunTimeException
// Exception for when a chapter cannot be found
class ChapterNotFoundException extends RuntimeException {

	public ChapterNotFoundException(){}

	public ChapterNotFoundException(String message){
		super(message);
	}
}

// Creates PlaylistAlreadyExistsException that extends RunTimeException
// Exception for when a playlist already exists
class PlaylistAlreadyExistsException extends RuntimeException {

	public PlaylistAlreadyExistsException(){}

	public PlaylistAlreadyExistsException(String message){
		super(message);
	}
}

// Creates PlaylistNotFoundException that extends RunTimeException
// Exception for when a playlist cannot be found
class PlaylistNotFoundException extends RuntimeException {

	public PlaylistNotFoundException(){}

	public PlaylistNotFoundException(String message){
		super(message);
	}
}

// Creates ContentTypeNotFoundException that extends RunTimeException
// Exception for when a content type cannot be found (invalid content type was input)
class ContentTypeNotFoundException extends RuntimeException {

	public ContentTypeNotFoundException(){}

	public ContentTypeNotFoundException(String message){
		super(message);
	}
}


