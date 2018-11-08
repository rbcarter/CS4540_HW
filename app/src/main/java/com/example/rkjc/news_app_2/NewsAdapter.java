package com.example.rkjc.news_app_2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class NewsAdapter  extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private ArrayList<NewsItem> mNews;
    private Context context;

    public NewsAdapter(Context context) {
        this.context = context;
    }

    @Override
    public void onBindViewHolder(NewsViewHolder newsViewHolder, int position) {
        newsViewHolder.bind(position);
    }

    @Override
    public int getItemCount() {
        if(null == mNews) {
            return 0;
        }

        return mNews.size();
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int listItemId = R.layout.news_item;
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(listItemId, parent, false);
        return new NewsViewHolder(view, context);
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView mTitle;
        public final TextView mDescription;
        public final TextView mDate;
        public String mUrl;
        private Context context;

        public NewsViewHolder(View view, Context context) {
            super(view);
            mTitle = (TextView) view.findViewById(R.id.title);
            mDescription = (TextView) view.findViewById(R.id.description);
            mDate = (TextView) view.findViewById(R.id.date);
            this.context = context;
        }

        public void onClick(View view) {
            Uri uri = Uri.parse(mUrl);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            context.startActivity(intent);
        }

        void bind(final int listIndex) {
            mTitle.setText("Title: " + mNews.get(listIndex).getTitle());
            mDescription.setText("Description: " + mNews.get(listIndex).getDescription());
            mDate.setText("Date: " + mNews.get(listIndex).getPublishedAt());
            mUrl = mNews.get(listIndex).getUrl();
//            url.setText(mRepos.get(listIndex).getUrl());
            itemView.setOnClickListener(this);
        }
    }

    public void setNews(ArrayList<NewsItem> news) {
        mNews = news;
        notifyDataSetChanged();
    }

}

