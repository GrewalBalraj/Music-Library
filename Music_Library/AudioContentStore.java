import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

// Simulation of audio content in an online store
// The songs, podcasts, audiobooks listed here can be "downloaded" to your library

public class AudioContentStore
{
		private ArrayList<AudioContent> contents; 
		
		private Map<String, Integer> titles;
		private Map<String, ArrayList<Integer>> artists; 
		private Map<String, ArrayList<Integer>> genres; 
		
		public AudioContentStore()
		{
			contents = new ArrayList<AudioContent>();
			titles = new HashMap<String, Integer>();
			artists = new HashMap<String, ArrayList<Integer>>();
			genres = new HashMap<String, ArrayList<Integer>>();
			
			// try block
			try {

				// Sets contents to the arraylist returned by createContents
				contents = createContents();

			// Catches any IOExceptions 
			} catch (IOException e){

				// Prints the error message and exits
				System.out.println(e.getMessage());
				System.exit(1);
			}

			// MAP 1 (title)

			// For loop that iterates through all indexes in contents
			for (int i = 0; i < contents.size(); i++){

				// Adds the title of a song in contents as a key, and puts i as value, into titles Map
				titles.put(contents.get(i).getTitle(), i);
			}

			// MAP 2 (artists)

			// For loop that iterates through all the indexes of contents
			for (int i = 0; i < contents.size(); i++){

				// Creates an array list to hold the indexes of songs/books by the same artists or authors
				ArrayList<Integer> artistOrAuthorList = new ArrayList<Integer>();

				// Checks if the content is a song
				if (contents.get(i).getType().equals(Song.TYPENAME)){

					// For loop that iterates through all indexes of contents
					for (int j = 0; j < contents.size(); j++){

						// Checks if the content at index j in contents is a song
						if (contents.get(j).getType().equals(Song.TYPENAME)){

							// Checks if content at i in contents arraylist and content at j in contents arraylist have same author
							if (((Song)contents.get(i)).getArtist().equals(((Song)contents.get(j)).getArtist())){

								// Adds j to artistOrAuthorList 
								artistOrAuthorList.add(j);
							}
						}
					}

					// Puts the artist and the arraylist with the indexes of their songs in the map artists
					artists.put(((Song)contents.get(i)).getArtist(), artistOrAuthorList);
				
				// Else Statement (runs when content is an AudioBook)
				} else {

					// For loop that iterates through all indexes of contents
					for (int j = 0; j < contents.size(); j++){

						// Checks if content at index j in contents is an audiobook
						if (contents.get(j).getType().equals(AudioBook.TYPENAME)){

							// Checks if content at i in contents arraylist and content at j in contents arraylist have same author
							if (((AudioBook)contents.get(i)).getAuthor().equals(((AudioBook)contents.get(j)).getAuthor())){
								artistOrAuthorList.add(j);
							}
						}
					}

					// Puts the author and the arraylist with the indexes of their books in the map artists
					artists.put(((AudioBook)contents.get(i)).getAuthor(), artistOrAuthorList);
				}
			}

			// MAP 3 (genres)

			// Creates an array list to hold the indexes of pop songs
			ArrayList<Integer> popList = new ArrayList<Integer>();

			// Creates an array list to hold the indexes of rap songs
			ArrayList<Integer> rapList = new ArrayList<Integer>();

			// Creates an array list to hold the indexes of rock songs
			ArrayList<Integer> rockList = new ArrayList<Integer>();

			// Creates an array list to hold the indexes of jazz songs
			ArrayList<Integer> jazzList = new ArrayList<Integer>();

			// Creates an array list to hold the indexes of hiphop songs
			ArrayList<Integer> hipHopList = new ArrayList<Integer>();

			// Creates an array list to hold the indexes of classical songs
			ArrayList<Integer> classicalList = new ArrayList<Integer>();

			// For loop that goes through all the indexes of  contents
			for (int i = 0; i < contents.size(); i++){

				// Checks if content is a song
				if (contents.get(i).getType().equals(Song.TYPENAME)){

					// If content is a pop song, add i to popList
					if (((Song)contents.get(i)).getGenre().equals(Song.Genre.POP)){
						popList.add(i);
					
					// If content is a rock song, add i to rockList
					} else if (((Song)contents.get(i)).getGenre().equals(Song.Genre.ROCK)){
						rockList.add(i);
					
					// If content is a rap song, add i to rapList
					} else if (((Song)contents.get(i)).getGenre().equals(Song.Genre.RAP)){
						rapList.add(i);
					
					// If content is a hiphop song, add i to hipHopList
					} else if (((Song)contents.get(i)).getGenre().equals(Song.Genre.HIPHOP)){
						hipHopList.add(i);
					
					// If content is a jazz song, add i to jazzList
					} else if (((Song)contents.get(i)).getGenre().equals(Song.Genre.JAZZ)){
						jazzList.add(i);
					
					// If content is a classical song, add i to classicalList
					} else if (((Song)contents.get(i)).getGenre().equals(Song.Genre.CLASSICAL)){
						classicalList.add(i);
					}
				}
			}

			// Maps all the genres (key) to the correct list of indexes (value) in the genres map
			genres.put("POP", popList);
			genres.put("ROCK", rockList);
			genres.put("RAP", rapList);
			genres.put("HIPHOP", hipHopList);
			genres.put("JAZZ", jazzList);
			genres.put("CLASSICAL", classicalList);

		}

		// private method to intialize the contents list for the AudioContentStore
		private ArrayList<AudioContent> createContents() throws IOException
		{

			// Creates an arraylist called contents to hold AudioContent
			ArrayList<AudioContent> contents = new ArrayList<AudioContent>();

			// Creates and intializes the file, and scanner for the file
			File file = new File("store.txt");
			Scanner scanner = new Scanner(file);

			// While scanner has another line
			while (scanner.hasNextLine()){

				// Creates a string type and intializes it to a string returned by scanner
				String type = scanner.nextLine();

				// If the type is a song
				if (type.equals(Song.TYPENAME)){

					// Creates and sets all the song variables with the correct information from the file using the scanner
					String id = scanner.nextLine();
					String title = scanner.nextLine();
					int year = scanner.nextInt();
					scanner.nextLine();
					int length = scanner.nextInt();
					scanner.nextLine();
					String artist = scanner.nextLine();
					String composer = scanner.nextLine();
					String genre = scanner.nextLine();
					int lines = scanner.nextInt();
					scanner.nextLine(); 

					// Creates a String for the lyrics of the song
					String lyrics = "";

					// For loop that runs as many iterations as there are lines of lyrics
					for (int i = 0; i < lines; i++){

						// Adds the next line (next lyric) to lyrics 
						lyrics += scanner.nextLine() + "\n";
					}

					// If song was a pop song
					if (genre.equals("POP")) {

						// Adds a new song to the contents list (pop song)
						contents.add(new Song(title, year, id, type, lyrics, length, artist, composer, Song.Genre.POP, lyrics));

					// Else-if song was a rock song
					} else if (genre.equals("ROCK")) {

						// Adds a new song to the contents list (rock song)
						contents.add(new Song(title, year, id, type, lyrics, length, artist, composer, Song.Genre.ROCK, lyrics));

					// Else-if song was a rap song
					} else if (genre.equals("RAP")) {

						// Adds a new song to the contents list (rap song)
						contents.add(new Song(title, year, id, type, lyrics, length, artist, composer, Song.Genre.RAP, lyrics));
		
					// Else-if song was a jazz song
					} else if (genre.equals("JAZZ")) {

						// Adds a new song to the contents list (jazz song)
						contents.add(new Song(title, year, id, type, lyrics, length, artist, composer, Song.Genre.JAZZ, lyrics));

					// Else-if song was a hiphop song
					} else if (genre.equals("HIPHOP")) {

						// Adds a new song to the contents list (hiphop song)
						contents.add(new Song(title, year, id, type, lyrics, length, artist, composer, Song.Genre.HIPHOP, lyrics));
					
					} else {

						// Adds a new song to the contents list (classical song)
						contents.add(new Song(title, year, id, type, lyrics, length, artist, composer, Song.Genre.CLASSICAL, lyrics));
					}
				
				// Else statement (runs only when type is an AudioBook)
				} else {

					// Creates and sets all the audiobook variables with the correct information from the file using the scanner
					String id = scanner.nextLine();
					String title = scanner.nextLine();
					int year = scanner.nextInt();
					scanner.nextLine();
					int length = scanner.nextInt();
					scanner.nextLine();
					String author = scanner.nextLine();
					String narrator = scanner.nextLine();
					int numOfChapters = scanner.nextInt();
					scanner.nextLine();

					// Creates an arrayList chapterTitles for the chapter titles
					ArrayList<String> chapterTitles = new ArrayList<String>();

					// For loop that iterates as many times as there are chapters
					for (int i = 0; i < numOfChapters; i++){

						// Adds the chapter title to the chapterTitles arrayList
						chapterTitles.add(scanner.nextLine());
					}

					// Creates an arrayList chapterTitles for the chapter titles
					ArrayList<String> chaptersLines = new ArrayList<String>();

					// For loop that iterates as many times as there are chapters 
					for (int i = 0; i < numOfChapters; i++){

						// Gets number of chapter lines 
						int numOfChapterLines = scanner.nextInt();
						scanner.nextLine();

						// Creates a String linesOfChapter to hold the chapter lines
						String linesOfChapter = "";

						// For loop that iterates as many times as there are chapterLines
						for (int j = 0; j < numOfChapterLines; j++){

							// Adds the next line of the chapter onto linesOfChapter
							linesOfChapter += scanner.nextLine() + "\n";
						}

						// Adds chapterLines to chaptersLnes 
						chaptersLines.add(linesOfChapter);
					}

					// Adds the audiobook to contents 
					contents.add(new AudioBook(title, year, id, type,  "", length, author, narrator, chapterTitles, chaptersLines));

				}
			}

			// Closes scanner
			scanner.close();

			// returns contents
			return contents;
		}

		// Method to get the store index for a song/audiobook of a given title (using titles map)
		public int getTitlesIndex(String title){
			return titles.get(title);
		}

		// Method to get the store indexes of all the content by an artist or author (using artists map)
		public ArrayList<Integer> getArtistsIndices(String artist) {
			return artists.get(artist);
		}

		// Method to get store indexes of all the songs in a genre in the store (using genres map)
		public ArrayList<Integer> getGenresIndices(String genre) {
			return genres.get(genre);
		}

		
		public AudioContent getContent(int index)
		{
			if (index < 1 || index > contents.size())
			{
				return null;
			}
			return contents.get(index-1);
		}
		
		public void listAll()
		{
			for (int i = 0; i < contents.size(); i++)
			{
				int index = i + 1;
				System.out.print("" + index + ". ");
				contents.get(i).printInfo();
				System.out.println();
			}
		}
		
		private ArrayList<String> makeHPChapterTitles()
		{
			ArrayList<String> titles = new ArrayList<String>();
			titles.add("The Riddle House");
			titles.add("The Scar");
			titles.add("The Invitation");
			titles.add("Back to The Burrow");
			return titles;
		}
		
		private ArrayList<String> makeHPChapters()
		{
			ArrayList<String> chapters = new ArrayList<String>();
			chapters.add("In which we learn of the mysterious murders\r\n"
					+ " in the Riddle House fifty years ago, \r\n"
					+ "how Frank Bryce was accused but released for lack of evidence, \r\n"
					+ "and how the Riddle House fell into disrepair. ");
			chapters.add("In which Harry awakens from a bad dream, \r\n"
					+ "his scar burning, we recap Harry's previous adventures, \r\n"
					+ "and he writes a letter to his godfather.");
			chapters.add("In which Dudley and the rest of the Dursleys are on a diet,\r\n"
					+ " and the Dursleys get letter from Mrs. Weasley inviting Harry to stay\r\n"
					+ " with her family and attend the World Quidditch Cup finals.");
			chapters.add("In which Harry awaits the arrival of the Weasleys, \r\n"
					+ "who come by Floo Powder and get trapped in the blocked-off fireplace\r\n"
					+ ", blast it open, send Fred and George after Harry's trunk,\r\n"
					+ " then Floo back to the Burrow. Just as Harry is about to leave, \r\n"
					+ "Dudley eats a magical toffee dropped by Fred and grows a huge purple tongue. ");
			return chapters;
		}
		
		private ArrayList<String> makeMDChapterTitles()
		{
			ArrayList<String> titles = new ArrayList<String>();
			titles.add("Loomings.");
			titles.add("The Carpet-Bag.");
			titles.add("The Spouter-Inn.");
			return titles;
		}
		private ArrayList<String> makeMDChapters()
		{
			ArrayList<String> chapters = new ArrayList<String>();
			chapters.add("Call me Ishmael. Some years ago never mind how long precisely having little\r\n"
					+ " or no money in my purse, and nothing particular to interest me on shore,\r\n"
					+ " I thought I would sail about a little and see the watery part of the world.");
			chapters.add("stuffed a shirt or two into my old carpet-bag, tucked it under my arm, \r\n"
					+ "and started for Cape Horn and the Pacific. Quitting the good city of old Manhatto, \r\n"
					+ "I duly arrived in New Bedford. It was a Saturday night in December.");
			chapters.add("Entering that gable-ended Spouter-Inn, you found yourself in a wide, \r\n"
					+ "low, straggling entry with old-fashioned wainscots, \r\n"
					+ "reminding one of the bulwarks of some condemned old craft.");
			return chapters;
		}
		
		private ArrayList<String> makeSHChapterTitles()
		{
			ArrayList<String> titles = new ArrayList<String>();
			titles.add("Prologue");
			titles.add("Chapter 1");
			titles.add("Chapter 2");
			titles.add("Chapter 3");
			return titles;
		}
		
		private ArrayList<String> makeSHChapters()
		{
			ArrayList<String> chapters = new ArrayList<String>();
			chapters.add("The gale tore at him and he felt its bite deep within\r\n"
					+ "and he knew that if they did not make landfall in three days they would all be dead");
			chapters.add("Blackthorne was suddenly awake. For a moment he thought he was dreaming\r\n"
					+ "because he was ashore and the room unbelieveable");
			chapters.add("The daimyo, Kasigi Yabu, Lord of Izu, wants to know who you are,\r\n"
					+ "where you come from, how ou got here, and what acts of piracy you have committed.");
			chapters.add("Yabu lay in the hot bath, more content, more confident than he had ever been in his life.");
			return chapters;
		}
		
		// Podcast Seasons
		/*
		private ArrayList<Season> makeSeasons()
		{
			ArrayList<Season> seasons = new ArrayList<Season>();
		  Season s1 = new Season();
		  s1.episodeTitles.add("Bay Blanket");
		  s1.episodeTitles.add("You Don't Want to Sleep Here");
		  s1.episodeTitles.add("The Gold Rush");
		  s1.episodeFiles.add("The Bay Blanket. These warm blankets are as iconic as Mariah Carey's \r\n"
		  		+ "lip-syncing, but some people believe they were used to spread\r\n"
		  		+ " smallpox and decimate entire Indigenous communities. \r\n"
		  		+ "We dive into the history of The Hudson's Bay Company and unpack the\r\n"
		  		+ " very complicated story of the iconic striped blanket.");
		  s1.episodeFiles.add("There is no doubt that the Klondike Gold Rush was an iconic event. \r\n"
		  		+ "But what did the mining industry cost the original people of the territory? \r\n"
		  		+ "And what was left when all the gold was gone? And what is a sour toe cocktail?");
		  s1.episodeFiles.add("here is no doubt that the Klondike Gold Rush was an iconic event. \r\n"
		  		+ "But what did the mining industry cost the original people of the territory? \r\n"
		  		+ "And what was left when all the gold was gone? And what is a sour toe cocktail?");
		  s1.episodeLengths.add(31);
		  s1.episodeLengths.add(32);
		  s1.episodeLengths.add(45);
		  seasons.add(s1);
		  Season s2 = new Season();
		  s2.episodeTitles.add("Toronto vs Everyone");
		  s2.episodeTitles.add("Water");
		  s2.episodeFiles.add("There is no doubt that the Klondike Gold Rush was an iconic event. \r\n"
		  		+ "But what did the mining industry cost the original people of the territory? \r\n"
		  		+ "And what was left when all the gold was gone? And what is a sour toe cocktail?");
		  s2.episodeFiles.add("Can the foundation of Canada be traced back to Indigenous trade routes?\r\n"
		  		+ " In this episode Falen and Leah take a trip across the Great Lakes, they talk corn\r\n"
		  		+ " and vampires, and discuss some big concerns currently facing Canada's water."); 
		  s2.episodeLengths.add(45);
		  s2.episodeLengths.add(50);
		 
		  seasons.add(s2);
		  return seasons;
		}
		*/
}
