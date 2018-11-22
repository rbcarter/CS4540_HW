package com.example.rkjc.news_app_2;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class NewsItemViewModel extends AndroidViewModel {
    private NewsItemRepository mRepository;

    private LiveData<List<NewsItem>> mAllNewsItems;

    public NewsItemViewModel (Application application) {
        super(application);
        mRepository = new NewsItemRepository(application);
        mAllNewsItems = mRepository.getAllNewsItems();
    }

    public LiveData<List<NewsItem>> getmAllNewsItems() {
        return mAllNewsItems;
    }

    public void updateCurrentItems() {
        mAllNewsItems = mRepository.getAllNewsItems();
    }

    public void sync(){
        mRepository.sync(null);
    }

    public void loadAll() {
        mRepository.updateList(null);
    }
}
