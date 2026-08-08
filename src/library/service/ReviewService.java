package library.service;

import library.entities.Book;
import library.entities.Review;
import library.entities.User;
import library.repository.BookRepository;
import library.repository.ReviewRepository;
import library.repository.UserRepository;

import java.util.List;

public class ReviewService {
    private ReviewRepository reviewRepository;
    private BookRepository bookRepository;
    private UserRepository userRepository;

    public ReviewService(ReviewRepository reviewRepository, BookRepository bookRepository, UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    //DODAWANIE RECENZJI
    public Review addReview(int bookId, int userId, int rating, String comment){
        Book book = bookRepository.findById(bookId);
        User user = userRepository.findById(userId);
        if(book == null){
            throw new IllegalArgumentException("Książka o tym id nie istnieje");
        }
        if(user == null){
            throw new IllegalArgumentException("Użytkownik o tym  id nie istnieje");
        }
        List<Review> bookReviews = reviewRepository.findByBookId(bookId);
        for(Review review : bookReviews){
            if(review.getUserId() == userId){
                throw new IllegalStateException("Użytkownik może dodać tylko 1 recenzje do książki");
            }
        }
        return  reviewRepository.add(bookId, userId, rating, comment);
    }
    public Review addReview(int bookId, int userId, int rating){
        return addReview(bookId, userId, rating, null);
    }

    //EDYCJA RECENZJI
    public Review editReview(int reviewId, int userId, int rating, String comment){
        Review review = reviewRepository.findById(reviewId);
        if(review == null){
            throw new IllegalArgumentException("Recenzja o tym id nie istnieje");
        }
        if(review.getUserId() !=  userId){
            throw new SecurityException("Można edytować tylko własne recenzje");
        }
        review.changeRating(rating);
        review.changeComment(comment);
        return review;
    }
    public Review editReview(int reviewId, int userId, int rating){
        return editReview(reviewId, userId, rating, null);
    }

    //USUWANIE RECENZJI
    public boolean deleteReview(int reviewId, int userId){
        Review review = reviewRepository.findById(reviewId);
        if(review == null){
            throw new IllegalArgumentException("Recenzja o tym id nie istnieje");
        }
        if(review.getUserId() != userId){
            throw new SecurityException("Można usunąć tylko swoją recenzję");
        }
        return reviewRepository.remove(reviewId);
    }

}
