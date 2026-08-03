package library.entities;

import java.time.LocalDate;
import java.util.Objects;

public class Review {
    private final int id;
    private final int bookId;
    private final int userId;
    private int rating;
    private String comment;
    private final LocalDate createdAt;

    public Review(int id, int bookId, int userId, int rating, String comment) {
        validateRating(rating);
        validateComment(comment);

        this.id = id;
        this.bookId = bookId;
        this.userId = userId;
        this.rating = rating;
        this.comment = comment;
        this.createdAt = LocalDate.now();
    }
    public Review(int id, int bookId, int userId, int rating) {
       this(id, bookId, userId, rating, null);
    }

    //ID
    public int getId() {
        return id;
    }
    public int getBookId() {
        return bookId;
    }
    public int getUserId() {
        return userId;
    }

    //RATING
    public int getRating() {
        return rating;
    }
    public void changeRating(int rating){
        validateRating(rating);
        this.rating = rating;
    }

    //COMMENT
    public String getComment() {
        return comment;
    }
    public void changeComment(String comment){
        validateComment(comment);
        this.comment = comment;
    }

    //CREATEDAT
    public LocalDate getCreatedAt() {
        return createdAt;
    }

    //PRIVATE METHODS
    private void validateRating(int rating){
        if (rating > 5 || rating < 1){
            throw new IllegalArgumentException("Ocena musi być w zakresie od 1 do 5");
        }
    }
    private  void validateComment(String comment){
        if(comment != null && comment.length() > 100){
            throw new IllegalArgumentException("Komentarz nie może przekraczać 100 znaków");
        }
    }

    @Override
    public String toString() {
        return "Review{" +
                "id =" + id +
                ", bookId =" + bookId +
                ", userId =" + userId +
                ", rating =" + rating +
                ", comment ='" + comment + "'" +
                ", createdAt =" + createdAt +
                "}";
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return id == review.id;
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
