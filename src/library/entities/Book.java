package library.entities;

import java.util.Objects;

public class Book {
    private final int id;
    private String title;
    private String author;
    private int year;
    private String category;
    private String description;
    private boolean available;

    public Book(int id, String title, String author, int year, String category, String description) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.category = category;
        this.description = description;
        this.available = true;
    }
    public Book(int id, String title, String author, int year, String category) {
        this(id, title, author, year, category, null);
    }

    //ID
    public int getId() {
        return id;
    }

    //TITLE
    public String getTitle() {
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }

    //AUTHOR
    public String getAuthor(){
        return author;
    }
    public void setAuthor(String author){
        this.author = author;
    }

    //YEAR
    public int getYear() {
        return year;
    }

    //CATEGORY
    public String getCategory(){
        return category;
    }
    public void setCategory(String category){
        this.category = category;
    }

    //DESCRIPTION
    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }

    //AVAILABLE
    public boolean isAvailable() {
        return available;
    }
    public void markAsAvailable(){
        this.available = true;
    }
    public void markAsUnavailable(){
        this.available = false;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + "'" +
                ", author='" + author + "'" +
                ", year=" + year +
                ", category='" + category + "'" +
                ", description='" + description + "'" +
                ", available=" + available +
                "}";
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id;
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
