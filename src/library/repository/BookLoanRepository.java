package library.repository;

import library.entities.BookLoan;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookLoanRepository {
    private final Map<Integer, BookLoan> bookLoans;
    private int nextId;

    public BookLoanRepository(){
        bookLoans = new HashMap<>();
        nextId = 1;
    }

    public BookLoan add(int bookId, int userId, LocalDate dueDate){
        BookLoan bookloan = new BookLoan(nextId, bookId, userId, dueDate);
        bookLoans.put(nextId, bookloan);
        nextId++;
        return bookloan;
    }
    public BookLoan findById(int id){
        return bookLoans.get(id);
    }
    public List<BookLoan> findByBookId(int bookId){
        List<BookLoan> result = new ArrayList<>();
        for(BookLoan bookLoan : bookLoans.values()){
            if(bookLoan.getBookId() == bookId){
                result.add(bookLoan);
            }
        }
        return result;
    }
    public List<BookLoan> findByUserId(int userId){
        List<BookLoan> result = new ArrayList<>();
        for(BookLoan bookLoan : bookLoans.values()){
            if(bookLoan.getUserId() == userId){
                result.add(bookLoan);
            }
        }
        return result;
    }
    public List<BookLoan> detainedBooks(int userId){
        List<BookLoan> result = new ArrayList<>();
        for(BookLoan bookLoan : bookLoans.values()){
            if(bookLoan.getUserId() == userId && bookLoan.getReturnDate() == null){
                result.add(bookLoan);
            }
        }
        return result;
    }
    public List<BookLoan> findAll(){
        return new ArrayList<>(bookLoans.values());
    }

    public boolean remove(int id){
        if(!bookLoans.containsKey(id)){
            return false;
        }
        bookLoans.remove(id);
        return true;
    }
}
