package library.service;

import library.entities.Book;
import library.repository.BookRepository;

import java.time.LocalDate;
import java.util.List;

public class BookService {
    private BookRepository bookRepository;
    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }
    public Book addBook(String title, String author, int year, String category, String description){
        validateNotBlank(title, "title");
        validateNotBlank(author, "author");
        validateNotBlank(category, "category");
        validateYear(year);
        return bookRepository.add(title, author, year, category, description);
    }
    public Book addBook(String title, String author, int year, String category){
        return addBook(title, author, year, category, null);
    }
    public boolean deleteBook(int id){
        return bookRepository.removeBook(id);
    }
    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }
    //WALIDACJA
    private void validateNotBlank(String value, String fieldName){
        if(value == null || value.isBlank()){
            throw new IllegalArgumentException(fieldName + " nie może być pusty");
        }
    }
    private void validateYear(int year){
        if(year <= 0 || year > LocalDate.now().getYear()){
            throw new IllegalArgumentException("Niepoprawny rok");
        }
    }
}
