package com.ahmedmakramallah.newsfeedapp;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ahmed on 8/11/2017.
 */

public class NewsAdapter extends ArrayAdapter<NewsInfo> {
    public NewsAdapter(Activity context, ArrayList<NewsInfo> newslist) {
        super(context, 0, newslist);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        NewsInfo currentNews = getItem(position);
        TextView newsTitle = (TextView) listItemView.findViewById(R.id.news_title);
        newsTitle.setText(currentNews.getmTitle().toString());

        TextView sectionName = (TextView) listItemView.findViewById(R.id.sectionName_title);
        sectionName.setText(currentNews.getmSectionName().toString());

        TextView Description = (TextView) listItemView.findViewById(R.id.description);
        Description.setText(currentNews.getmSectionName());

        TextView date = (TextView) listItemView.findViewById(R.id.date);
        date.setText(currentNews.getmDate());

        return listItemView;
    }

}
