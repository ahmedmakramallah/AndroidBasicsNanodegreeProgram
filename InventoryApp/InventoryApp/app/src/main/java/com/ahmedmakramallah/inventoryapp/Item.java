package com.ahmedmakramallah.inventoryapp;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by ahmed on 8/18/2017.
 */

public final class Item {

    public static final String PATH = "items";
    public static final String CONTENT_AUTHORITY = "com.ahmedmakramallah.inventoryapp";
    public static final Uri BASE_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    private Item() {
    }

    public static final class ItemEntites implements BaseColumns {

        public static final String CONTENT_LIST_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH;

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_URI, PATH);


        // Table name
        public final static String TABLE_NAME = "items";
        // Column ID
        public final static String _ID = BaseColumns._ID;
        // Column name
        public final static String COLUMN_NAME = "name";
        // Column quantity
        public final static String COLUMN_QUANTITY = "quantity";
        // Column price
        public final static String COLUMN_PRICE = "price";
        // Column Image path
        public final static String COLUMN_IMAGE_PATH = "image";
        // Column number
        public final static String COLUMN_NUMBER = "number";


    }


}
