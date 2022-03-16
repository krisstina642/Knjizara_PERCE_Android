package com.example.knjizara.Types;

import com.example.knjizara.Database;

public class Comment {
    int book_id, user_id;
    String comment;
    float rating;

    public Comment(int book_id, int user_id, String comment){
        this.book_id=book_id;
        this.user_id=user_id;
        this.comment=comment;
        this.rating=-1;
        Database.addComment(this);
    }

    public Comment(int book_id, int user_id, String comment, float rating){
        this(book_id,user_id,comment);
        this.rating=rating;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getName(){
       return Database.getUsersName(user_id);
    }

    public int getBook_id() {
        return book_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getComment() {
        return comment;
    }
}
