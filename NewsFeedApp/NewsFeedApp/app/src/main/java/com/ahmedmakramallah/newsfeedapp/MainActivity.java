package com.ahmedmakramallah.newsfeedapp;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<NewsInfo>> {

    ArrayList<NewsInfo> newsArrayList = null;
    NewsAdapter adapter;
    TextView mEmptyTextView = null;
    private static final int LOADER_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView newsList = (ListView) findViewById(R.id.list_view);
        adapter = new NewsAdapter(this, new ArrayList<NewsInfo>());
        newsList.setAdapter(adapter);

        mEmptyTextView = (TextView) findViewById(R.id.empty_txt_view);

        newsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NewsInfo currentNews = adapter.getItem(position);
                Uri uri = Uri.parse(currentNews.getmWebUrl());
                Intent myWebsite = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(myWebsite);

            }
        });

        if (isConnectivity()) {
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(LOADER_ID, null, this);
        } else {
            mEmptyTextView.setText(getString(R.string.no_internet_connection));
        }
    }

    @Override
    public Loader<List<NewsInfo>> onCreateLoader(int id, Bundle args) {
        final String USGS_REQUEST_URL =
                "https://content.guardianapis.com/search?api-key=4ebba4b9-d8f7-41cc-90af-372bb3305364";
        return new com.ahmedmakramallah.newsfeedapp.NewsLoader(this, USGS_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<NewsInfo>> loader, List<NewsInfo> data) {
        adapter.clear();
        if (data != null && !data.isEmpty()) {
            adapter.addAll(data);
        } else {
            mEmptyTextView.setText(R.string.no_news);
        }

    }

    @Override
    public void onLoaderReset(Loader<List<NewsInfo>> loader) {

    }

    public boolean isConnectivity() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }
}
