package com.example.knjizara.Types;

public class Book {
    private String name, author, description, image;
    private int year, num_of_pages;
    private boolean sale;
    private float price, rating;
    int id;

    public Book(int id, String name, String author, String description, String image, int year,
                int num_of_pages, boolean sale, float price, float rating){
        this.name = name;
        this.author = author;
        this.description = description;
        this.image = image;
        this.year = year;
        this.num_of_pages = num_of_pages;
        this.sale = sale;
        this.price = price;
        this.rating = rating;
        this.id=id;
    }

    public int getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public float getRating() {
        return rating;
    }

    public int getNum_of_pages() {
        return num_of_pages;
    }

    public int getYear() {
        return year;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isSale() {
        return sale;
    }
}
