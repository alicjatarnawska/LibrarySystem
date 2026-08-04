package library.repository;

import library.entities.Review;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReviewRepository {
    private final Map<Integer, Review> reviews;
    private int nextId;

    public ReviewRepository(){
        this.reviews = new HashMap<>();
        this.nextId = 1;
    }

    public Review add(int bookId, int userId, int rating, String comment){
        Review review = new Review(nextId, bookId, userId, rating, comment);
        reviews.put(nextId, review);
        nextId++;
        return review;
    }
    public Review add(int bookId, int userId, int rating){
        return add(bookId, userId, rating, null);
    }

    public Review findById(int id){
        return reviews.get(id);
    }
    public List<Review> findByBookId(int bookId){
        List<Review> result = new ArrayList<>();
        for(Review review : reviews.values()){
            if(review.getBookId() == bookId){
                result.add(review);
            }
        }
        return result;
    }
    public List<Review> findByUserId(int userId){
        List<Review> result = new ArrayList<>();
        for(Review review : reviews.values()){
            if(review.getUserId() == userId){
                result.add(review);
            }
        }
        return result;
    }

    public List<Review> findAll(){
        return new ArrayList<>(reviews.values());
    }

    public boolean remove(int id){
        if(!reviews.containsKey(id)){
            return false;
        }
        reviews.remove(id);
        return true;
    }
}
