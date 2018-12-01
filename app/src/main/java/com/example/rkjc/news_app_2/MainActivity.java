package com.example.rkjc.news_app_2;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.arch.lifecycle.Observer;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    private NewsAdapter mNewsAdapter;

    private NewsItemViewModel mNewsItemViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNewsItemViewModel = ViewModelProviders.of(this).get(NewsItemViewModel.class);

        mRecyclerView = (RecyclerView) findViewById(R.id.news_recyclerview);

        LinearLayoutManager layout = new LinearLayoutManager(
                this, LinearLayoutManager.VERTICAL,false
        );

        mRecyclerView.setLayoutManager(layout);
        mRecyclerView.setHasFixedSize(true);

        mNewsAdapter = new NewsAdapter(this);
        mRecyclerView.setAdapter(mNewsAdapter);

        mNewsItemViewModel.getmAllNewsItems().observe(this, new Observer<List<NewsItem>>() {
            @Override
            public void onChanged(@Nullable final List<NewsItem> news) {
                // Update the cached copy of the words in the adapter.
                mNewsAdapter.setNews(new ArrayList<NewsItem>(news));
            }
        });

        ScheduleUtils.scheduleRefresh(this);
    }

//    private void makeNewsSearchQuery() {
//        URL newsSearchUrl = NetworkUtils.buildUrl();
//        new NewsQueryTask().execute(newsSearchUrl);
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemThatWasClickedId = item.getItemId();
        if (itemThatWasClickedId == R.id.action_search) {
            mNewsItemViewModel.sync();
            mNewsItemViewModel.updateCurrentItems();
//            mNewsItemViewModel.getmAllNewsItems();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

//    public class NewsQueryTask extends AsyncTask<URL, Void, String> {
//
//        @Override
//        protected String doInBackground(URL... params) {
//            URL searchUrl = params[0];
//            String newsSearchResults = null;
//            try {
//                newsSearchResults = NetworkUtils.getResponseFromHttpUrl(searchUrl);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return newsSearchResults;
//        }
//
//        @Override
//        protected void onPostExecute(String newsSearchResults) {
//            if (newsSearchResults != null && !newsSearchResults.equals("")) {
//                mNewsAdapter.setNews(JsonUtils.parseNews(newsSearchResults));
//            }
//        }
//    }
}
