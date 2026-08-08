package library.entities;
import java.time.LocalDate;
import java.util.Objects;

public class BookLoan {
    private final int id;
    private final int bookId;
    private final int userId;
    private final LocalDate loanDate;
    private LocalDate dueDate;
    private LocalDate returnDate;

    public BookLoan(int id, int bookId, int userId, LocalDate dueDate) {
        this.loanDate = LocalDate.now();
        validateDueDate(dueDate);

        this.id = id;
        this.bookId = bookId;
        this.userId = userId;
        this.dueDate = dueDate;
        this.returnDate = null;
    }

    //ID
    public int getId() {
        return id;
    }

    //BOOKID
    public int getBookId() {
        return bookId;
    }

    //USERID
    public int getUserId() {
        return userId;
    }

    //LOANDATE
    public LocalDate getLoanDate() {
        return loanDate;
    }

    //DUEDATE
    public LocalDate getDueDate() {
        return dueDate;
    }
    public void extendDueDate(LocalDate dueDate) {
        if(dueDate.isBefore(this.dueDate) || this.dueDate.isEqual(dueDate)) {
            throw new IllegalArgumentException("Nowy termin zwrotu nie może być przed pierwotnym");
        }
        this.dueDate = dueDate;
    }


    //RETURNDATE
    public LocalDate getReturnDate() {
        return returnDate;
    }
    public void returnBook() {
        if(returnDate != null){
            throw new IllegalStateException("Książka została już zwrócona");
        }
        this.returnDate = LocalDate.now();
    }

    //PRIVATE METHODS
    private void validateDueDate(LocalDate dueDate) {
        if(dueDate.isBefore(loanDate) || dueDate.isEqual(loanDate)) {
            throw new IllegalArgumentException("Termin zwrotu nie może być w dniu wypożyczenia lub przed nim");
        }
    }

    @Override
    public String toString() {
        return "BookLoan{" +
                "id =" + id +
                ", bookId =" + bookId +
                ", userId =" + userId +
                ", loanDate =" + loanDate +
                ", dueDate =" +  dueDate +
                ", returnDate =" + returnDate +
                "}";
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookLoan bookLoan = (BookLoan) o;
        return id == bookLoan.id;
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
