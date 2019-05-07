package com.example.proiectandroid.Database;


import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface ArticleDao {

    @Query("SELECT * FROM article")
    List<Article> getAll();

    @Query("SELECT * FROM article WHERE title LIKE :title ")
    List<Article> findByTitle(String title);

    @Query("SELECT * FROM article WHERE userid = :userId")
    List<Article> findByAuthor(int userId);

    @Query("SELECT * FROM article WHERE aid = :articleId LIMIT 1")
    Article findByArticleId(int articleId);

    @Query("UPDATE article SET title = :title, description= :desc, price =:price  WHERE aid = :articleId")
    void updateArticle(int articleId, String title, String desc, Float price);

    @Insert
    void insert(Article... articles);

    @Delete
    void delete(Article article);
}
