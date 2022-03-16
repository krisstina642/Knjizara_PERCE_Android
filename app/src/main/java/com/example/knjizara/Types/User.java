package com.example.knjizara.Types;

import com.example.knjizara.Database;

public class User {
    private String name, surnme, ussername, password, town, phone, street, street_number;
    int id;

    public User(int id, String name, String surnme, String ussername, String password, String town, String phone, String street, String street_number) {
        this.name = name;
        this.surnme = surnme;
        this.ussername = ussername;
        this.password = password;
        this.town = town;
        this.phone = phone;
        this.street = street;
        this.street_number = street_number;
        this.id=id;
    }

    public User(String name, String surnme, String ussername, String password, String town, String phone, String street, String street_number) {
        this(1, name, surnme, ussername, password, town, phone, street, street_number);
        this.id = Database.getArrayOfUsers().size();
        Database.getArrayOfUsers().add(this);
    }

    public void setAll( String ime, String prezime, String korisnicko_ime, String lozinka, String grad, String telefon, String ulica, String broj) {
        this.name =ime;
        this.surnme =prezime;
        this.ussername =korisnicko_ime;
        this.password =lozinka;
        this.town =grad;
        this.phone =telefon;
        this.street =ulica;
        this.street_number =broj;

    }

    public int getId() {
        return id;
    }

    public void setStreet_number(String street_number) {
        this.street_number = street_number;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurnme(String surnme) {
        this.surnme = surnme;
    }

    public void setUssername(String ussername) {
        this.ussername = ussername;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStreet_number() {
        return street_number;
    }

    public String getStreet() {
        return street;
    }

    public String getPhone() {
        return phone;
    }

    public String getTown() {
        return town;
    }

    public String getName() {
        return name;
    }

    public String getUssername() {
        return ussername;
    }

    public String getPassword() {
        return password;
    }

    public String getSurnme() {
        return surnme;
    }
}

