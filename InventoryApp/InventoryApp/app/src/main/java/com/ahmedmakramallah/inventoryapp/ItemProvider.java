package com.ahmedmakramallah.inventoryapp;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by ahmed on 8/18/2017.
 */

public class ItemProvider extends ContentProvider {

    private static final int ITEMS = 100;
    private static final int ITEM_ID = 101;
    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    private DBHelper dbHelper;

    static {
        uriMatcher.addURI(Item.CONTENT_AUTHORITY, Item.PATH, ITEMS);
        uriMatcher.addURI(Item.CONTENT_AUTHORITY, Item.PATH + "/#", ITEM_ID);
    }

    @Override
    public boolean onCreate() {
        dbHelper = new DBHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        // Using Get readable database method to read database
        SQLiteDatabase database = dbHelper.getReadableDatabase();

        // This cursor will hold the result of the query
        Cursor cursor;

        // Figure out if the URI matcher can match the URI to a specific code
        int match = uriMatcher.match(uri);
        switch (match) {
            case ITEMS:
                cursor = database.query(Item.ItemEntites.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case ITEM_ID:
                selection = Item.ItemEntites._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};

                // This will perform a query on the pets table where the _id equals 3 to return a
                // Cursor containing that row of the table.
                cursor = database.query(Item.ItemEntites.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("unknown URI " + uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final int match = uriMatcher.match(uri);
        switch (match) {
            case ITEMS:
                return insertItem(uri, values);
            default:
                throw new IllegalArgumentException("Insertion is not supported for " + uri);
        }
    }

    private Uri insertItem(Uri uri, ContentValues values) {

        SQLiteDatabase database = dbHelper.getWritableDatabase();
        long id = database.insert(Item.ItemEntites.TABLE_NAME, null, values);
        if (id == -1) {
            Log.e("ItemProvider", "Failed to insert row for " + uri);
            return null;
        }
        getContext().getContentResolver().notifyChange(uri, null);

        // Once we know the ID of the new row in the table,
        // return the new URI with the ID appended to the end of it
        return ContentUris.withAppendedId(uri, id);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Using Get writeable database
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        final int match = uriMatcher.match(uri);
        switch (match) {
            case ITEMS:
                // Delete all rows that match the selection and selection args
                return database.delete(Item.ItemEntites.TABLE_NAME, selection, selectionArgs);
            case ITEM_ID:
                // Delete a single row given by the ID in the URI
                selection = Item.ItemEntites._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                int row_deleted = database.delete(Item.ItemEntites.TABLE_NAME, selection, selectionArgs);
                if (row_deleted != 0)
                    getContext().getContentResolver().notifyChange(uri, null);

                return row_deleted;
            default:
                throw new IllegalArgumentException("Deletion is not supported for " + uri);
        }
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        final int match = uriMatcher.match(uri);
        switch (match) {
            case ITEMS:
                return updateItem(uri, values, selection, selectionArgs);
            case ITEM_ID:
                selection = Item.ItemEntites._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return updateItem(uri, values, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("Update is not applied for " + uri);
        }
    }

    private int updateItem(Uri uri, ContentValues values, String selection, String[] selectionArgs) {


        // check that the name value is not null.
        if (values.containsKey(Item.ItemEntites.COLUMN_NAME)) {
            String name = values.getAsString(Item.ItemEntites.COLUMN_NAME);
            if (name == null) {
                throw new IllegalArgumentException("Item is required a name");
            }
        }
        // check that the quantity value is not null.
        if (values.containsKey(Item.ItemEntites.COLUMN_QUANTITY)) {
            Integer quantity = values.getAsInteger(Item.ItemEntites.COLUMN_QUANTITY);
            if (quantity == null) {
                throw new IllegalArgumentException("Item is required quantity");
            }
        }
        // check that the price value is not null.
        if (values.containsKey(Item.ItemEntites.COLUMN_PRICE)) {

            Integer price = values.getAsInteger(Item.ItemEntites.COLUMN_PRICE);
            if (price == null) {
                throw new IllegalArgumentException("Item is required price");
            }
        }
        // If there are no values to update, then don't try to update the database
        if (values.size() == 0) {
            return 0;
        }
        // Otherwise, get writeable database to update the data
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        int row_updated = database.update(Item.ItemEntites.TABLE_NAME, values, selection, selectionArgs);
        if (row_updated != 0)
            getContext().getContentResolver().notifyChange(uri, null);
        // Returns the number of database rows affected by the update statement
        return row_updated;
    }

}
