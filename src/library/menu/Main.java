package library.menu;

import library.entities.Book;
import library.entities.BookLoan;
import library.entities.Review;
import library.entities.User;
import library.repository.BookLoanRepository;
import library.repository.BookRepository;
import library.repository.ReviewRepository;
import library.repository.UserRepository;
import library.service.BookService;
import library.service.ReviewService;
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
        ReviewRepository reviewRepository = new ReviewRepository();
        ReviewService reviewService = new ReviewService(reviewRepository, bookRepository, userRepository);

        bookService.addBook("Hobbit", "J.R.R. Tolkien", 1937, "Fantasy", "Klasyka fantasy");
        bookService.addBook("Diuna", "Frank Herbert", 1965, "Sci-Fi");
        User user = userService.register("ala@gmail.com", "kocurki", "Ala", "Tarnawska");
        LocalDate dueDate = LocalDate.now().plusDays(14);
        BookLoan loan = bookService.borrowBook(1, user.getId(), dueDate);
        bookService.returnBookLoan(loan.getId());
        Review review = reviewService.addReview(1, user.getId(), 4, "Nudna książka");
        try {
            Review review2 = reviewService.addReview(1, user.getId(), 5, "Nudy");
        } catch (IllegalStateException e) {
            System.out.println("Błąd: " + e.getMessage());
        }

        List<Book> books = bookService.getAllBooks();
        for (Book book : books) {
            System.out.println(book);
        }
        System.out.println(review);
    }
}