package library.menu;

import library.entities.Book;
import library.entities.BookLoan;
import library.entities.User;
import library.repository.BookLoanRepository;
import library.repository.BookRepository;
import library.repository.UserRepository;
import library.service.BookService;
import library.service.UserService;

import java.util.List;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        BookRepository bookRepository = new BookRepository();
        UserRepository userRepository = new UserRepository();
        BookLoanRepository bookLoanRepository = new BookLoanRepository();
        BookService bookService = new BookService(bookRepository, userRepository, bookLoanRepository);
        UserService userService = new UserService(userRepository);

        bookService.addBook("Hobbit", "J.R.R. Tolkien", 1937, "Fantasy", "Klasyka fantasy");
        bookService.addBook("Diuna", "Frank Herbert", 1965, "Sci-Fi");
        User user = userService.register("ala@gmail.com", "kocurki", "Ala", "Tarnawska");
        LocalDate dueDate = LocalDate.now().plusDays(14);
        BookLoan loan = bookService.borrowBook(1, user.getId(), dueDate);
        bookService.returnBookLoan(loan.getId());


        List<Book> books = bookService.getAllBooks();
        for (Book book : books) {
            System.out.println(book);
        }
    }
}