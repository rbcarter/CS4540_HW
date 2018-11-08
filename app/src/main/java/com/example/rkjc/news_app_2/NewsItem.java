package com.example.rkjc.news_app_2;

public class NewsItem {
    public String mAuthor;
    public String mTitle;
    public String mDescription;
    public String mUrl;
    public String mUrlToImage;
    public String mPublishedAt;

    public NewsItem(String author, String title, String description, String url,
                    String urlToImage, String publishedAt) {
        this.mAuthor = author;
        this.mTitle = title;
        this.mDescription = description;
        this.mUrl = url;
        this.mUrlToImage = urlToImage;
        this.mPublishedAt = publishedAt;
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
