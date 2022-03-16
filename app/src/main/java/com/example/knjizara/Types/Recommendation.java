package com.example.knjizara.Types;

import com.example.knjizara.Database;

public class Recommendation {
    int sender_Id;
    int receiver_id;
    Book book;

    public Recommendation(int sender_Id, int receiver_id, Book book){
        this.sender_Id=sender_Id;
        this.receiver_id=receiver_id;
        this.book=book;
        Database.addRecommendation(this);
    }

    public int getSender_Id() {
        return sender_Id;
    }

    public int getReceiver_id() {
        return receiver_id;
    }

    public Book getBook() {
        return book;
    }
}
