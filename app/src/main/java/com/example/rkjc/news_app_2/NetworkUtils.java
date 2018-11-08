package com.example.rkjc.news_app_2;

import android.net.Uri;
import android.os.AsyncTask;
import android.view.Menu;
import android.view.MenuItem;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {
    // base url + query strings:
    // https://newsapi.org/v1/articles?source=the-next-web&sortBy={sortBy}&apiKey={apiKey}

    final static String NEWS_API_BASE_URL =
            "https://newsapi.org/v1/articles";

    final static String PARAM_SOURCE = "source";
    final static String source = "the-next-web";

    final static String PARAM_SORT = "sortBy";
    final static String sortBy = "latest";

    // the apiKey is hardcoded by assignment requirements
    // note to self: avoid doing this for future assignments
    final static String PARAM_API_KEY = "apiKey";
    final static String apiKey = "f30a2a272ad443d3bc2a0bfd6f66915d";

    public static URL buildUrl() {
        Uri builtUri = Uri.parse(NEWS_API_BASE_URL).buildUpon()
                .appendQueryParameter(PARAM_SOURCE, source)
                .appendQueryParameter(PARAM_SORT, sortBy)
                .appendQueryParameter(PARAM_API_KEY, apiKey)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }



}
