package aiBadgeLibraryGUIpkg;

public class Book
{
    private String title;
    private String author;
    private int year;
    private boolean checkedOut;

    public Book(String title, String author, int year)
    {
        this.title = title;
        this.author = author;
        this.year = year;
        this.checkedOut = false;
    }

    // Getters and setters
    public String getTitle()
    {
    	return title;
    }
    public void setTitle(String title)
    {
    	this.title = title;
    }

    public String getAuthor()
    {
    	return author;
    }
    public void setAuthor(String author)
    {
    	this.author = author;
    }
    public int getYear()
    {
    	return year;
    }
    public void setYear(int year)
    {
    	this.year = year;
    }
    public boolean isCheckedOut()
    {
    	return checkedOut;
    }
    public void setCheckedOut(boolean checkedOut)
    {
    	this.checkedOut = checkedOut;
    }

    @Override
    public String toString()
    {
        return title + " by " + author + " (" + year + ")" + (checkedOut ? " - Checked Out" : "");
    }
}
