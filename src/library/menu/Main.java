package library.menu;

import library.entities.Book;
import library.repository.BookRepository;
import library.service.BookService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        BookRepository bookRepository = new BookRepository();
        BookService bookService = new BookService(bookRepository);

        bookService.addBook("Hobbit", "J.R.R. Tolkien", 1937, "Fantasy", "Klasyka fantasy");
        bookService.addBook("Diuna", "Frank Herbert", 1965, "Sci-Fi");

        List<Book> books = bookService.getAllBooks();
        for (Book book : books) {
            System.out.println(book);
        }
    }
}