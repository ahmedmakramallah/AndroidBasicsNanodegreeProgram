package com.ahmedmakramallah.inventoryapp;

import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    ItemCursorAdapter itemCursorAdapter;
    ListView listView;
    private static final int ITEM_LOADER = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.list_view);
        Button AddButton = (Button) findViewById(R.id.add_btn);

        View emptyView = findViewById(R.id.empty_view);
        listView.setEmptyView(emptyView);

        itemCursorAdapter = new ItemCursorAdapter(this, null);
        listView.setAdapter(itemCursorAdapter);
        getLoaderManager().initLoader(ITEM_LOADER, null, this);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                Uri currentUri = ContentUris.withAppendedId(Item.ItemEntites.CONTENT_URI, id);
                intent.setData(currentUri);
                startActivity(intent);
            }
        });


        AddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewProductActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] project = {
                Item.ItemEntites._ID,
                Item.ItemEntites.COLUMN_NAME,
                Item.ItemEntites.COLUMN_QUANTITY,
                Item.ItemEntites.COLUMN_PRICE,
                Item.ItemEntites.COLUMN_IMAGE_PATH

        };
        return new CursorLoader(this, Item.ItemEntites.CONTENT_URI, project, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        itemCursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        itemCursorAdapter.swapCursor(null);
    }
}
