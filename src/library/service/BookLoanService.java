package library.service;

import library.entities.BookLoan;
import library.entities.Role;
import library.entities.User;
import library.repository.BookLoanRepository;
import library.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;

public class BookLoanService {
    private BookLoanRepository bookLoanRepository;
    private UserRepository userRepository;

    public BookLoanService(BookLoanRepository bookLoanRepository, UserRepository userRepository) {
        this.bookLoanRepository = bookLoanRepository;
        this.userRepository = userRepository;
    }

    //PRZEDŁUŻENIE TERMINU ZWROTU
    public BookLoan extendDueDate(int bookLoanId, int userId, LocalDate newDueDate) {
        BookLoan bookLoan = bookLoanRepository.findById(bookLoanId);
        User user = userRepository.findById(userId);
        if (bookLoan == null) {
            throw new IllegalArgumentException("Wypożyczenie o tym id nie istnieje");
        }
        if (user == null) {
            throw new IllegalArgumentException("Użytkownik o tym id nie istnieje");
        }
        if(user.getRole() != Role.ADMIN && user.getRole() != Role.EMPLOYEE){
            throw new SecurityException("Tylko pracownik lub admin może przedłużyć termin zwrotu");
        }
        bookLoan.extendDueDate(newDueDate);
        return bookLoan;
    }

    //LISTY
    public List<BookLoan> getLoansByUserId(int requesterId, int userId) {
        if (!canAccessUserData(requesterId, userId)) {
            throw new SecurityException("Brak uprawnień do przeglądania tych wypożyczeń");
        }
        return bookLoanRepository.findByUserId(userId);
    }
    public List<BookLoan> getActiveLoansByUserId(int requesterId, int userId) {
        if (!canAccessUserData(requesterId, userId)) {
            throw new SecurityException("Brak uprawnień do przeglądania tych wypożyczeń");
        }
        return bookLoanRepository.detainedBooks(userId);
    }
    public List<BookLoan> getLoansByBookId(int requesterId, int bookId) {
        if (!isEmployeeOrAdmin(requesterId)) {
            throw new SecurityException("Brak uprawnień do przeglądania tych wypożyczeń");
        }
        return bookLoanRepository.findByBookId(bookId);
    }

    //WALIDACJA
    private boolean canAccessUserData(int requesterId, int userId){
        return requesterId == userId || isEmployeeOrAdmin(requesterId);
    }
    private boolean isEmployeeOrAdmin(int requesterId) {
        User requester = userRepository.findById(requesterId);
        if (requester == null) {
            throw new IllegalArgumentException("Użytkownik o podanym id nie istnieje");
        }
        return requester.getRole() == Role.EMPLOYEE || requester.getRole() == Role.ADMIN;
    }

}
