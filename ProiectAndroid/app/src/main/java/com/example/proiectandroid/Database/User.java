package com.example.proiectandroid.Database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name="first_name")
    public String firstName;
    @ColumnInfo(name="last_name")
    public String lastName;
    @ColumnInfo(name="city")
    public String city;
    @ColumnInfo(name="telephone")
    public String telephone;

    public User(String firstName, String lastName, String city, String telephone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.telephone = telephone;
    }

    public int getUid() {
        return uid;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCity() {
        return city;
    }

    public String getTelephone() {
        return telephone;
    }
}
