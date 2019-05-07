package com.example.proiectandroid.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {User.class,Article.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract ArticleDao articleDao();
}

