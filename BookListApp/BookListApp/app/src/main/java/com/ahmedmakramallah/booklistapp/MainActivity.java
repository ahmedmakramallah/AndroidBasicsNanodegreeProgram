package com.ahmedmakramallah.booklistapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String REQUESTED_URL_BASE =
            "https://www.googleapis.com/books/v1/volumes?q=";
    private String maxResults = "&maxResults=10";

    private TextView empty_txt_view = null;
    private ImageButton search_btn;
    private EditText search_edt_txt;
    private BookAdapter bookAdapter;
    private String url;
    private String search_Book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        empty_txt_view = (TextView) findViewById(R.id.empty_txt);
        ListView BookList = (ListView) findViewById(R.id.list_view);
        BookList.setEmptyView(empty_txt_view);
        bookAdapter = new BookAdapter(this, new ArrayList<Book>());
        BookList.setAdapter(bookAdapter);
        search_btn = (ImageButton) findViewById(R.id.search_btn);
        search_edt_txt = (EditText) findViewById(R.id.search_txt);

        // the action on search button to fetch the specified book
        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check_connection()) {
                    search_Book = search_edt_txt.getText().toString();
                    url = REQUESTED_URL_BASE + search_Book + maxResults;
                    BookAsyncTask task = new BookAsyncTask();
                    task.execute(url.toString());
                } else {
                    empty_txt_view.setText(R.string.no_internet_connection);
                    bookAdapter.clear();
                }
            }
        });

    }
    private boolean check_connection() {
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        } else {
            return false;
        }
    }

    private class BookAsyncTask extends AsyncTask<String, Void, List<Book>> {
        protected List<Book> doInBackground(String... urls) {
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }
            List<Book> result = Utils.fetchData(urls[0]);
            return result;
        }

        protected void onPostExecute(List<Book> result) {
            // Clear the adapter of previous book data
            if (result == null) {
                return;
            } else {
                empty_txt_view.setText("no valid data to search");
            }
            bookAdapter.clear();
            bookAdapter.addAll(result);
        }
    }

}
