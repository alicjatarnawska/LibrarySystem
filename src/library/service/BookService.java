package library.service;

import library.entities.Book;
import library.entities.BookLoan;
import library.entities.User;
import library.repository.BookLoanRepository;
import library.repository.BookRepository;
import library.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;

public class BookService {
    private BookRepository bookRepository;
    private UserRepository userRepository;
    private BookLoanRepository bookLoanRepository;
    public BookService(BookRepository bookRepository, UserRepository userRepository, BookLoanRepository bookLoanRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.bookLoanRepository = bookLoanRepository;
    }

    //DOODAWANIE KSIĄŻKI
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

    //USÓWANIE KSIĄŻKI
    public boolean deleteBook(int id){
        return bookRepository.removeBook(id);
    }
    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    //WYPOŻYCZANIE KSIĄŻKI
    public BookLoan borrowBook(int bookId, int userId, LocalDate dueDate) {
        Book book = bookRepository.findById(bookId);
        User user = userRepository.findById(userId);
        if (book == null) {
            throw new IllegalArgumentException("Książka o podanym id nie istnieje");
        }
        if (user == null) {
            throw new IllegalArgumentException("Użytkownik o podanym id nie istnieje");
        }
        if(!book.isAvailable()){
            throw new IllegalStateException("Książka o podanym id nie jest dostępna");
        }
        book.markAsUnavailable();
        return bookLoanRepository.add(bookId, userId, dueDate);
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
