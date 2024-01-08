import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;


// Simulation of a Simple Text-based Music App (like Apple Music)

public class MyAudioUI
{
	public static void main(String[] args)
	{
		// Simulation of audio content in an online store
		// The songs, podcasts, audiobooks in the store can be downloaded to your mylibrary
		AudioContentStore store = new AudioContentStore();
		
		// Create my music mylibrary
		Library mylibrary = new Library();

		Scanner scanner = new Scanner(System.in);
		System.out.print(">");

		// Process keyboard actions
		while (scanner.hasNextLine())
		{

			// Try Block
			try {

			
				String action = scanner.nextLine();

				if (action == null || action.equals("")) 
				{
					System.out.print("\n>");
					continue;
				}
				else if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT"))
					return;
				
				else if (action.equalsIgnoreCase("STORE"))	// List all songs
				{
					store.listAll(); 
				}
				else if (action.equalsIgnoreCase("SONGS"))	// List all songs
				{
					mylibrary.listAllSongs(); 
				}
				else if (action.equalsIgnoreCase("BOOKS"))	// List all songs
				{
					mylibrary.listAllAudioBooks(); 
				}
				else if (action.equalsIgnoreCase("PODCASTS"))	// List all songs
				{
					mylibrary.listAllPodcasts(); 
				}
				else if (action.equalsIgnoreCase("ARTISTS"))	// List all songs
				{
					mylibrary.listAllArtists(); 
				}
				else if (action.equalsIgnoreCase("PLAYLISTS"))	// List all play lists
				{
					mylibrary.listAllPlaylists(); 
				}
				// Download audiocontent (song/audiobook/podcast) from the store 
				// Specify the index of the content
				else if (action.equalsIgnoreCase("DOWNLOAD")) 
				{

					// Creates starting and ending index variables 
					int index_start = 0;
					int index_end = 0;

					// Asks user to input a starting index for the store content
					System.out.print("From Store Content #: ");
					if (scanner.hasNextInt())
					{
						// Sets index_start to the int entered by the user
						index_start = scanner.nextInt();
						scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())

						// Asks user to input a ending index for the store content
						System.out.print("To Store Content #: ");
						if(scanner.hasNextInt()){

							// Sets index_end to the int entered by the user
							index_end = scanner.nextInt();
							scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
						}
					}

					// For loop that goes through all the contents between the two indexes entered by user
					for (int i = index_start; i <= index_end; i++){

						// Sets the content to a content of index i
						AudioContent content = store.getContent(i);

						// If content is null, print content is not in store
						if (content == null){
							System.out.println("Content Not Found in Store");

						} else {

							// try block 
							try{

								// Calls download method and prints a message for the content being added to the library
								mylibrary.download(content);
								System.out.println(content.getType() + " " + content.getTitle() + " Added to Library");

							// Catches any AudioContentAlreadyDownloadedExceptions
							} catch (AudioContentAlreadyDownloadedException e){

								// Prints the exception message
								System.out.println(e.getMessage());
							}
						}
					}
										
				} 
				// Action to download all the content of an artist or author
				else if (action.equalsIgnoreCase("DOWNLOADA"))
				{

					// Creates String artist variable 
					String artist = "";

					// Print statement asks user for artist  
					System.out.print("Artist: ");

					// Executes the following code if a string is input
					if (scanner.hasNextLine()){

						// Sets artist to the input given by the user
						artist = scanner.nextLine();
					}

					// try block
					try{ 

						// For loop that iterates through the integer arraylist (value) of an artist (key) in artists map
						for (int index : store.getArtistsIndices(artist)){

							// try block
							try {

								// Calls download method and prints a statement about content being downloaded
								mylibrary.download(store.getContent(index + 1));
								System.out.println(store.getContent(index + 1).getType() + " " + store.getContent(index + 1).getTitle() + " Added to Library");

							// Catches any AudioContentAlreadyDownloadedExceptions
							} catch (AudioContentAlreadyDownloadedException e){

								// Prints the exception message
								System.out.println(e.getMessage());
							}
							
						}
					
					// Catches any NullPointerExceptions
					} catch (NullPointerException e){

						// Prints that there was no matches for the artist inputted
						System.out.println("No matches for " + artist);
					}
					
				} 
				// Action to download all songs in a genre
				else if (action.equalsIgnoreCase("DOWNLOADG"))
				{

					// Creates String genre variable 
					String genre = "";

					// Print statement asks user for genre  
					System.out.print("Genre [POP, ROCK, JAZZ, HIPHOP, RAP, CLASSICAL]: ");

					// Executes the following code if a string is input
					if (scanner.hasNextLine()){

						// Sets genre to the input given by the user
						genre = scanner.nextLine();
					}

					// try block
					try {

						// For loop that iterates through the integer arraylist (value) of a genre (key) in genres map
						for (int index : store.getGenresIndices(genre.toUpperCase())){

							// try block
							try {
							
								// Calls download method and prints a statement about content being downloaded
								mylibrary.download(store.getContent(index + 1));
								System.out.println(store.getContent(index + 1).getType() + " " + store.getContent(index + 1).getTitle() + " Added to Library");

							// Catches any AudioContentAlreadyDownloadedExceptions
							} catch (AudioContentAlreadyDownloadedException e){

								// Prints the exception message
								System.out.println(e.getMessage());
							}
						}
					
					// Catches any NullPointerExceptions
					} catch (NullPointerException e){

						// Prints that there was no matches for the genre inputted
						System.out.println("No matches for " + genre);
					}
				}
				// Get the *library* index (index of a song based on the songs list)
				// of a song from the keyboard and play the song 
				else if (action.equalsIgnoreCase("PLAYSONG")) 
				{
					// Print error message if the song doesn't exist in the library

					// Creates int index variable
					int index = 0;

					// Print statement asks user for song number
					System.out.print("Song Number: ");

					// Executes the following code if a integer is input
					if (scanner.hasNextInt())
					{
						// Sets index to the value inputted by the user
						index = scanner.nextInt();
						scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
					}

					// Calls the playSong method
					mylibrary.playSong(index);
					
				}
				// Print the table of contents (TOC) of an audiobook that
				// has been downloaded to the library. Get the desired book index
				// from the keyboard - the index is based on the list of books in the library
				else if (action.equalsIgnoreCase("BOOKTOC")) 
				{
				// Print error message if the book doesn't exist in the library

					// Creates int index variable
					int index = 0;

					// Print statement asks user for audio book number
					System.out.print("Audio Book Number: ");

					// Executes the following code if an integer is input
					if (scanner.hasNextInt())
					{
						// Sets index to the value inputted by the user
						index = scanner.nextInt();
						scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
					}

					// Calls the printAudioBookTOC method
					mylibrary.printAudioBookTOC(index);
							
				}
				// Similar to playsong above except for audio book
				// In addition to the book index, read the chapter 
				// number from the keyboard - see class Library
				else if (action.equalsIgnoreCase("PLAYBOOK")) 
				{
					// Creates int index and int chapter variables
					int index = 0;
					int chapter = 0;

					// Print statement that asks user for audio book number
					System.out.print("Audio Book Number: ");

					// Executes the following code if an integer is input
					if (scanner.hasNextInt())
					{
						// Sets index to value entered by the user
						index = scanner.nextInt();
						scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())

						// Print statement that asks user for chapter number
						System.out.print("Chapter Number: ");

						// Executes the following code if an integer is input
						if(scanner.hasNextInt()){
							
							// Sets chapter to value entered by the user
							chapter = scanner.nextInt();
							scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
						}
					}

					// Calls playAudioBook method 
					mylibrary.playAudioBook(index, chapter);
					
				}
				// Print the episode titles for the given season of the given podcast
				// In addition to the podcast index from the list of podcasts, 
				// read the season number from the keyboard
				// see class Library for the method to call
				else if (action.equalsIgnoreCase("PODTOC")) 
				{
					
				}
				// Similar to playsong above except for podcast
				// In addition to the podcast index from the list of podcasts, 
				// read the season number and the episode number from the keyboard
				// see class Library for the method to call
				else if (action.equalsIgnoreCase("PLAYPOD")) 
				{
					
				}
				// Specify a playlist title (string) 
				// Play all the audio content (songs, audiobooks, podcasts) of the playlist 
				// see class Library for the method to call
				else if (action.equalsIgnoreCase("PLAYALLPL")) 
				{
					// Creates string variable playlistTitle
					String playlistTitle = "";

					// Print statement asks user for the playlist title 
					System.out.print("Playlist Title: ");

					// Executes the following code if string is input
					if (scanner.hasNextLine()){

						// playlistTitle is set to the input given by the user
						playlistTitle = scanner.nextLine();
						
					}

					// Calls playPlaylist method using playlistTitle as parameter
					mylibrary.playPlaylist(playlistTitle);
						
				}
				// Specify a playlist title (string) 
				// Read the index of a song/audiobook/podcast in the playist from the keyboard 
				// Play all the audio content 
				// see class Library for the method to call
				else if (action.equalsIgnoreCase("PLAYPL")) 
				{
					// Creates String playlistTitle and int index variables
					String playlistTitle = "";
					int index = 0;

					// Print statement asks user for a playlist title
					System.out.print("Playlist Title: ");

					// Executes the following code if a string is input
					if (scanner.hasNextLine()){

						// Sets playlistTitle to the input given by the user
						playlistTitle = scanner.nextLine();

						// Print statement asks user for content number
						System.out.print("Content Number: ");

						// Executes the following code if an integer is input
						if(scanner.hasNextInt()){

							// Sets index to the value inputted by the user
							index = scanner.nextInt();
							scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
						}
					}

					// Calls playPlaylist method using playlistTitle and index as parameters
					mylibrary.playPlaylist(playlistTitle, index);
					
				}
				// Delete a song from the list of songs in mylibrary and any play lists it belongs to
				// Read a song index from the keyboard
				// see class Library for the method to call
				else if (action.equalsIgnoreCase("DELSONG")) 
				{
					// Creates int index variable
					int index = 0;

					// Print statement asks user for library song number 
					System.out.print("Library Song #: ");

					// Executes the following code if an integer is input
					if (scanner.hasNextInt()){

						// Sets index to the value entered by the user
						index = scanner.nextInt();
						scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
					}

					// Calls deleteSong method
					mylibrary.deleteSong(index);

				}
				// Read a title string from the keyboard and make a playlist
				// see class Library for the method to call
				else if (action.equalsIgnoreCase("MAKEPL")) 
				{
					// Creates String playlistTitle variable 
					String playlistTitle = "";

					// Print statement asks user for playlist title
					System.out.print("Playlist Title: ");

					// Executes the following code if a string is input
					if (scanner.hasNextLine()){

						// Sets playlistTitle to the input given by the user
						playlistTitle = scanner.nextLine();
					}

					// Calls makePlaylist method 
					mylibrary.makePlaylist(playlistTitle);
						
				}
				// Print the content information (songs, audiobooks, podcasts) in the playlist
				// Read a playlist title string from the keyboard
			// see class Library for the method to call
				else if (action.equalsIgnoreCase("PRINTPL"))	// print playlist content
				{
					// Creates String playlistTitle variable 
					String playlistTitle = "";

					// Print statement asks user for a playlist title 
					System.out.print("Playlist Title: ");

					// Executes the following code if a string is input
					if (scanner.hasNextLine()){

						// Sets playlistTitle to the input given by the user
						playlistTitle = scanner.nextLine();
					}

					// Calls printPlaylist method 
					mylibrary.printPlaylist(playlistTitle);
					
				}
				// Add content (song, audiobook, podcast) from mylibrary (via index) to a playlist
				// Read the playlist title, the type of content ("song" "audiobook" "podcast")
				// and the index of the content (based on song list, audiobook list etc) from the keyboard
			// see class Library for the method to call
				else if (action.equalsIgnoreCase("ADDTOPL")) 
				{
					// Creates String playlistTitle, String type, and int index variables
					String playlistTitle = "";
					String type = "";
					int index = 0;

					// Print statement asks user for playlist title
					System.out.print("Playlist Title: ");

					// Executes the following code if a string is input
					if (scanner.hasNextLine()){

						// Sets playlistTitle to the input given by the user
						playlistTitle = scanner.nextLine();

						// Print statement asks user for the content type
						System.out.print("Content Type [SONG, AUDIOBOOK]: ");

						// Executes the following code if a string is input
						if (scanner.hasNextLine()){

							// Sets type to the input given by the user
							type = scanner.nextLine();

							// Print statement asks the user for the library content number
							System.out.print("Library Content #: ");

							// Executes the following code if an integer is input
							if (scanner.hasNextInt()){

								// Sets index to the value inputted by the user
								index = scanner.nextInt();
								scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
							}
						}
					}

					// Calls addContentToPlaylist method 
					mylibrary.addContentToPlaylist(type, index, playlistTitle);
						
				}
				// Delete content from play list based on index from the playlist
				// Read the playlist title string and the playlist index
			// see class Library for the method to call
				else if (action.equalsIgnoreCase("DELFROMPL")) 
				{
					// Creates String variable playlistTitle and int variable index
					String playlistTitle = "";
					int index = 0;

					// Print statement that asks the user to input the playlist title
					System.out.print("Playlist Title: ");

					// Executes the following code if a string is input
					if (scanner.hasNextLine()){

						// Sets playlistTitle to the input given by the user
						playlistTitle = scanner.nextLine();

						// Print statement that asks user to input the content number
						System.out.print("Playlist Content #: ");

						// Executes the following code if an integer is input
						if (scanner.hasNextInt()){

							// Sets index to the integer inputted by the user
							index = scanner.nextInt();
							scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
						}
					}

					// Calls delContentFromPlaylist method
					mylibrary.delContentFromPlaylist(index, playlistTitle);
				}
				
				else if (action.equalsIgnoreCase("SORTBYYEAR")) // sort songs by year
				{
					mylibrary.sortSongsByYear();
				}
				else if (action.equalsIgnoreCase("SORTBYNAME")) // sort songs by name (alphabetic)
				{
					mylibrary.sortSongsByName();
				}
				else if (action.equalsIgnoreCase("SORTBYLENGTH")) // sort songs by length
				{
					mylibrary.sortSongsByLength();
				} 

				// Searches store for an audiocontent given the title of the content
				else if (action.equalsIgnoreCase("SEARCH")){

					// Creates String title variable 
					String title = "";

					// Print statement asks user for content title 
					System.out.print("Title: ");

					// Executes the following code if a string is input
					if (scanner.hasNextLine()){

						// Sets title to the input given by the user
						title = scanner.nextLine();
					}

					// try block
					try {

						// Prints the index of the content in the store and the info of the AudioContent with the given title
						System.out.print((store.getTitlesIndex(title) + 1) + ". ");
						store.getContent(store.getTitlesIndex(title) + 1).printInfo();
				
					// Catch block to catch any NullPointerExceptions
					} catch (NullPointerException e) {
		
						// Prints an error message
						System.out.println("No matches for " + title);
					}
		
				}
				// Searches store for all audiocontent from an artist/author given their name
				else if (action.equalsIgnoreCase("SEARCHA")){

					// Creates String artist variable 
					String artist = "";

					// Print statement asks user for artist name 
					System.out.print("Artist: ");

					// Executes the following code if a string is input
					if (scanner.hasNextLine()){

						// Sets artist to the input given by the user
						artist = scanner.nextLine();
					}

					// try block
					try{

						// For loop that iterates through the integer arraylist (value) of an artist (key) in artists map
						for (int index : store.getArtistsIndices(artist)){


							// Prints the index of the content in the store and the info of the AudioContent from that artist/author
							System.out.print((index + 1) + ". ");
							store.getContent(index + 1).printInfo();
							System.out.println();
						}
		
					// Catch block to catch any NullPointerExceptions
					} catch (NullPointerException e){

						// Prints an error message
						System.out.println("No matches for " + artist);
					}

				} 
				// Searches store for all audiocontent from an artist/author given their name
				else if (action.equalsIgnoreCase("SEARCHG")){

					// Creates String genre variable 
					String genre = "";

					// Print statement asks user for genre  
					System.out.print("Genre [POP, ROCK, JAZZ, HIPHOP, RAP, CLASSICAL]: ");

					// Executes the following code if a string is input
					if (scanner.hasNextLine()){

						// Sets genre to the input given by the user
						genre = scanner.nextLine();
					}

					// try block
					try{

						// For loop that iterates through the integer arraylist (value) of a genre (key) in genres map
						for (int index : store.getGenresIndices(genre.toUpperCase())){
		
		
							// Prints the index of the song in the store and info of the song from that genre
							System.out.print((index + 1) + ". ");
							store.getContent(index + 1).printInfo();
							System.out.println();
						}
				
					// Catch block to catch any NullPointerExceptions
					} catch (NullPointerException e){
		
						// Prints an error message
						System.out.println("No matches for " + genre);
					}
				} 
			
			// Catches any AudioContentNotFoundExceptions
			} catch (AudioContentNotFoundException e){

				// Prints the exception message
				System.out.println(e.getMessage());

			// Catches any ChapterNotFoundExceptions
			} catch (ChapterNotFoundException e){

				// Prints the exception message
				System.out.println(e.getMessage());

			// Catches any PlaylistNotFoundExceptions
			} catch (PlaylistNotFoundException e){

				// Prints the exception message
				System.out.println(e.getMessage());

			// Catches any PlaylistAlreadyExistsExceptions
			} catch (PlaylistAlreadyExistsException e){

				// Prints the exception message
				System.out.println(e.getMessage());

			// Catches any ContentTypeNotFoundExceptions
			} catch (ContentTypeNotFoundException e){

				// Prints the exception message
				System.out.println(e.getMessage());
			}

			System.out.print("\n>");
		}
	}
}
