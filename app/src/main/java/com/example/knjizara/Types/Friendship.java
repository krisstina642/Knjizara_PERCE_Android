package com.example.knjizara.Types;

import com.example.knjizara.Database;

public class Friendship {
    int sender_Id;
    int receiver_id;
    int status; // 0 = poslat zahtev 1 = prijatelji

    public Friendship(int sender_Id, int receiver_id){
        this.sender_Id=sender_Id;
        this.receiver_id=receiver_id;
        this.status=0;
        Database.addFriendship(this);
    }
    public Friendship(int sender_Id, int receiver_id, int status){
        this.sender_Id=sender_Id;
        this.receiver_id=receiver_id;
        this.status=status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getReceiver_id() {
        return receiver_id;
    }

    public int getSender_Id() {
        return sender_Id;
    }

    public int getStatus() {
        return status;
    }
}
