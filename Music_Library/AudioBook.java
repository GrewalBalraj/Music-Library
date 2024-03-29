import java.util.ArrayList;


/*
 * An AudioBook is a type of AudioContent.
 * It is a recording made available on the internet of a book being read aloud by a narrator
 * 
 */

public class AudioBook extends AudioContent
{
	public static final String TYPENAME =	"AUDIOBOOK";
	
	private String author; 
	private String narrator;
	private ArrayList<String> chapterTitles;
	private ArrayList<String> chapters;
	private int currentChapter = 0;

	
	public AudioBook(String title, int year, String id, String type, String audioFile, int length,
									String author, String narrator, ArrayList<String> chapterTitles, ArrayList<String> chapters)
	{
		// Make use of the constructor in the super class AudioContent. 
		// Initialize additional AudioBook instance variables. 

		// Calls constructor from the superclass AudioContent
		super(title, year, id, type, audioFile, length);

		// Intializes the rest of the audiobook instance variables 
		this.author = author;
		this.narrator = narrator;
		this.chapterTitles = chapterTitles;
		this.chapters = chapters;

	}
	
	public String getType()
	{
		return TYPENAME;
	}

  // Print information about the audiobook. First print the basic information of the AudioContent 
	// by making use of the printInfo() method in superclass AudioContent and then print author and narrator
	// see the video
	public void printInfo()
	{
		// Calls printInfo() of the superclass AudioContent to print information on the audiobook
		super.printInfo();

		// Prints information on the author and narrator of the audiobook
		System.out.println("Author: " + author + " Narrated by: " + narrator);
		
	}
	
  // Play the audiobook by setting the audioFile to the current chapter title (from chapterTitles array list) 
	// followed by the current chapter (from chapters array list)
	// Then make use of the the play() method of the superclass
	public void play()
	{
		// Sets Audio File to the title of current chapter, followed by the current chapter
		setAudioFile(chapterTitles.get(currentChapter) + ". \n" + chapters.get(currentChapter));

		// Calls the play method from the superclass AudioContent 
		super.play();
	}
	
	// Print the table of contents of the book - i.e. the list of chapter titles
	// See the video
	public void printTOC()
	{
		// For loop that iterates through the indexes of the chapterTitles arraylist
		for (int i = 0; i < chapterTitles.size(); i++){

			// Prints out the chapter title and leaves a blank line space
			System.out.println("Chapter " + (i+1) + ". " + chapterTitles.get(i));
			System.out.println();
		}
	}

	// Select a specific chapter to play - nothing to do here
	public void selectChapter(int chapter)
	{
		if (chapter >= 1 && chapter <= chapters.size())
		{
			currentChapter = chapter - 1;
		}
	}
	
	//Two AudioBooks are equal if their AudioContent information is equal and both the author and narrators are equal
	public boolean equals(Object other)
	{
		// Creates an AudioBook object called otherbook that is the object other typecasted to Audiobook
		AudioBook otherBook = (AudioBook)(other);

		// Returns true or false based on whether the audio content, author, and narrators of the two Audiobooks are the same
		return super.equals(otherBook) && author.equals(otherBook.author) && narrator.equals(otherBook.narrator);
	}
	
	public int getNumberOfChapters()
	{
		return chapters.size();
	}

	public String getAuthor()
	{
		return author;
	}

	public void setAuthor(String author)
	{
		this.author = author;
	}

	public String getNarrator()
	{
		return narrator;
	}

	public void setNarrator(String narrator)
	{
		this.narrator = narrator;
	}

	public ArrayList<String> getChapterTitles()
	{
		return chapterTitles;
	}

	public void setChapterTitles(ArrayList<String> chapterTitles)
	{
		this.chapterTitles = chapterTitles;
	}

	public ArrayList<String> getChapters()
	{
		return chapters;
	}

	public void setChapters(ArrayList<String> chapters)
	{
		this.chapters = chapters;
	}

}
