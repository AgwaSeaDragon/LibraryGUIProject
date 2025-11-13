package aiBadgeLibraryGUIpkg;

import java.util.ArrayList;
import java.util.List;

public class Library
{
    private final List<Book> books = new ArrayList<>();

    public void addBook(Book book)
    {
    	books.add(book);
    }
    public void removeBook(int index)
    {
        if (index >= 0 && index < books.size()) books.remove(index);
    }
    public List<Book> getBooks()
    {
    	return books;
    }

    public List<Book> search(String keyword)
    {
        keyword = keyword.toLowerCase();
        List<Book> results = new ArrayList<>();
        for (Book b : books)
        {
            if (b.getTitle().toLowerCase().contains(keyword) ||
                b.getAuthor().toLowerCase().contains(keyword) ||
                String.valueOf(b.getYear()).contains(keyword))
            {
                results.add(b);
            }
        }
        return results;
    }
}
