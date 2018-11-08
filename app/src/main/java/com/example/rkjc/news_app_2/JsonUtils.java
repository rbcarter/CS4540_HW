package com.example.rkjc.news_app_2;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;

public class JsonUtils {
    public static ArrayList<NewsItem> parseNews(String response) {
        try {
            ArrayList<NewsItem> news = new ArrayList<NewsItem>();
            JSONObject resp = new JSONObject(response);

            if(resp.getString("status").equals("error")) {
                return news; //this will be empty
            }

            JSONArray articles = resp.getJSONArray("articles");
            JSONObject currentArticle = null;
            for(int index = 0; index < articles.length(); index++) {
                currentArticle = articles.getJSONObject(index);
                news.add(new NewsItem(
                        currentArticle.getString("author"),
                        currentArticle.getString("title"),
                        currentArticle.getString("description"),
                        currentArticle.getString("url"),
                        currentArticle.getString("urlToImage"),
                        currentArticle.getString("publishedAt")
                ));
            }

            return news;
        }
        catch(Exception ex) {
            Log.e("Exception", ex.toString());
        }

        // return empty list if something weird broke
        return new ArrayList<NewsItem>();
    }
}


