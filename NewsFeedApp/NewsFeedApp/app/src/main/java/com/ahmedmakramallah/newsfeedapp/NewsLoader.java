package com.ahmedmakramallah.newsfeedapp;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by ahmed on 8/11/2017.
 */

public class NewsLoader extends AsyncTaskLoader<List<NewsInfo>> {

    private String mUrl;

    public NewsLoader(Context context, String url) {
        super(context);
        mUrl =url;
    }

    @Override
    public List<NewsInfo> loadInBackground() {
        if (mUrl == null){
            return null;
        }
        List<NewsInfo> newsInfoList =Utils.GetData(mUrl);
        return newsInfoList;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
}
