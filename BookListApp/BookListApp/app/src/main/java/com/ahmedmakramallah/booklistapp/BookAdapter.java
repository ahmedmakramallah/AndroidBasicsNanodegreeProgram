package com.ahmedmakramallah.booklistapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ahmed on 8/10/2017.
 */

public class BookAdapter extends ArrayAdapter<Book> {

    public BookAdapter(Activity context, ArrayList<Book> books) {
        super(context, 0, books);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        Book currentBook = getItem(position);
        TextView title_txt = (TextView) listItemView.findViewById(R.id.blank_book_title);
        title_txt.setText(currentBook.getmTitle().toString());

        TextView author_txt = (TextView) listItemView.findViewById(R.id.blank_authors_book);
        author_txt.setText(currentBook.getmAuthor().toString());
        return listItemView;

    }
}
