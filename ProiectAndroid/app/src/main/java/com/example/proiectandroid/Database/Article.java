package com.example.proiectandroid.Database;

import android.graphics.Bitmap;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity
public class Article {

    @PrimaryKey(autoGenerate = true)
    public int aid;
    @ForeignKey(entity = User.class,parentColumns = "uid",childColumns = "userid")
    public int userid;
    @ColumnInfo(name="title")
    public String title;
    @ColumnInfo(name="description")
    public String description;
    @ColumnInfo(name="price")
    public float price;
    @ColumnInfo(name="image")
    public byte[] image; //Bitmap

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public float getPrice() {
        return price;
    }

    public byte[] getImage() {
        return image;
    }

    public int getUserid() {
        return userid;
    }

    public int getAid() {
        return aid;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Article(int userid, String title, String description, float price, byte[] image) {
        this.userid = userid;
        this.title = title;
        this.description = description;
        this.price = price;
        this.image = image;
    }


}
