package com.example.rkjc.news_app_2;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

public class NewsJob extends JobService {
    static AsyncTask mBackgroundTask;

    private NewsItemRepository mRepository;

    @Override
    public boolean onStartJob(final JobParameters job) {
        NotificationUtils.remindUserNewsReload(NewsJob.this);

        mBackgroundTask = new AsyncTask() {
            @Override
            protected void onPreExecute() {
                Toast.makeText(NewsJob.this, "News refreshed", Toast.LENGTH_SHORT).show();
                super.onPreExecute();
            }

            @Override
            protected Object doInBackground(Object[] params) {
                mRepository = new NewsItemRepository(NewsJob.this.getApplication());
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                jobFinished(job, false);
                super.onPostExecute(o);

            }
        };


        mBackgroundTask.execute();

        return true;
    }

    @Override
    public boolean onStopJob(JobParameters job) {

        if (mBackgroundTask != null) mBackgroundTask.cancel(false);

        return true;
    }
}
