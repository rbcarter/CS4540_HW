package com.example.rkjc.news_app_2;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;

import java.net.URL;
import java.util.List;

public class NewsItemRepository {
    private NewsItemDao mNewsItemDao;
    private LiveData<List<NewsItem>> mAllNewsItems;

    public NewsItemRepository(Application application){
        NewsRoomDatabase db = NewsRoomDatabase.getDatabase(application.getApplicationContext());
        mNewsItemDao = db.newsItemDao();
        mAllNewsItems = mNewsItemDao.loadAllNewsItems();
    }

    LiveData<List<NewsItem>> getAllNewsItems() {
        return mAllNewsItems;
    }

    public void setAll (LiveData<List<NewsItem>> items) {
        this.mAllNewsItems = items;
    }

    public void updateList (List<NewsItem> items) {
        new updateListAsyncTask(mNewsItemDao).execute(items);
    }

    public void sync(List<NewsItem> items){
        new syncDBAsyncTask(mNewsItemDao).execute(items);
    }

    private class updateListAsyncTask extends AsyncTask<List<NewsItem>, Void, Void> {
        private NewsItemDao mAsyncTaskDao;
        updateListAsyncTask(NewsItemDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final List<NewsItem>... params) {
            NewsItemRepository.this.setAll(mAsyncTaskDao.loadAllNewsItems());
            return null;
        }
    }

    private class syncDBAsyncTask extends AsyncTask<List<NewsItem>, Void, Void> {
        private NewsItemDao mAsyncTaskDao;
        syncDBAsyncTask(NewsItemDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final List<NewsItem>... params) {
            try {
                URL newsSearchUrl = NetworkUtils.buildUrl();
                String response = NetworkUtils.getResponseFromHttpUrl(newsSearchUrl);
                mAsyncTaskDao.clearAll();
                mAsyncTaskDao.insert(JsonUtils.parseNews(response));
            }
            catch(Exception e) {
                Log.e("exception during sync", e.toString());
            }


            return null;
        }
    }
}
