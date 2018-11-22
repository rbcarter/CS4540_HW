package com.example.rkjc.news_app_2;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Ignore;
import android.support.annotation.NonNull;

@Entity(tableName ="news_item")
public class NewsItem {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    private int id;

    @NonNull
    @ColumnInfo(name="author")
    private String mAuthor;

    @NonNull
    @ColumnInfo(name="title")
    private String mTitle;

    @NonNull
    @ColumnInfo(name="description")
    private String mDescription;

    @NonNull
    @ColumnInfo(name="url")
    private String mUrl;

    @NonNull
    @ColumnInfo(name="urlToImage")
    private String mUrlToImage;

    @NonNull
    @ColumnInfo(name="publishedAt")
    private String mPublishedAt;

    public NewsItem(int id, String author, String title, String description, String url,
                    String urlToImage, String publishedAt) {
        this.id = id;
        this.mAuthor = author;
        this.mTitle = title;
        this.mDescription = description;
        this.mUrl = url;
        this.mUrlToImage = urlToImage;
        this.mPublishedAt = publishedAt;
    }

    @Ignore
    public NewsItem(String author, String title, String description, String url,
                    String urlToImage, String publishedAt) {
        this.mAuthor = author;
        this.mTitle = title;
        this.mDescription = description;
        this.mUrl = url;
        this.mUrlToImage = urlToImage;
        this.mPublishedAt = publishedAt;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public void setAuthor(String mAuthor) {
        this.mAuthor = mAuthor;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public String getUrlToImage() {
        return mUrlToImage;
    }

    public void setUrlToImage(String mUrlToImage) {
        this.mUrlToImage = mUrlToImage;
    }

    public String getPublishedAt() {
        return mPublishedAt;
    }

    public void setPublishedAt(String mPublishedAt) {
        this.mPublishedAt = mPublishedAt;
    }


}
