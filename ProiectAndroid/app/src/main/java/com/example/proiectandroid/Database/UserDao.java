package com.example.proiectandroid.Database;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserDao {


    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user WHERE uid = :userId LIMIT 1")
    User findUserById(int userId);

    @Query("SELECT city FROM user WHERE uid = :id LIMIT 1")
    String findCityById(int id);

    @Query("SELECT * FROM user WHERE telephone LIKE :tel LIMIT 1")
    User findByTelephone(String tel);

    @Insert
    void insert(User... users);

    @Delete
    void delete(User user);

}
