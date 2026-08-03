package library.repository;

import library.entities.Book;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookRepository {
    private final Map<Integer, Book> books;
    private int nextId;

    public BookRepository() {
        this.books = new HashMap<>();
        this.nextId = 1;
    }

    public Book add(String title, String author, int year, String category, String description) {
        Book book = new Book(nextId, title, author, year, category, description);
        books.put(nextId, book);
        nextId++;
        return book;
    }
    public Book add(String title, String author, int year, String category) {
        return add(title, author, year, category, null);
    }

    public Book findById(int id) {
        return books.get(id);
    }

    public List<Book> findAll(){
        return new ArrayList<>(books.values());
    }

    public boolean removeBook(int id) {
        if(!books.containsKey(id)){
            return false;
        }
        books.remove(id);
        return true;
    }
}
