package com.example.rkjc.news_app_2;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.rkjc.news_app_2.NewsItem;

import java.util.List;

@Dao
public interface NewsItemDao {

    @Insert
    void insert(List<NewsItem> items);

    @Query("DELETE FROM news_item")
    void clearAll();

    @Query("SELECT * FROM news_item ORDER BY publishedAt ASC")
    LiveData<List<NewsItem>> loadAllNewsItems();
}

