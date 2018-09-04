package com.ahmedmakramallah.inventoryapp;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by ahmed on 8/18/2017.
 */

public class ItemCursorAdapter extends CursorAdapter {

    Cursor cursor;

    public ItemCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView name_text_view = (TextView) view.findViewById(R.id.product_name);
        final TextView quantity_text_view = (TextView) view.findViewById(R.id.product_quantity);
        TextView price_text_view = (TextView) view.findViewById(R.id.product_price);
        Button sellButton = (Button) view.findViewById(R.id.sell_btn);


        int nameColumnIndex = cursor.getColumnIndex(Item.ItemEntites.COLUMN_NAME);
        final int quantityColumnIndex = cursor.getColumnIndex(Item.ItemEntites.COLUMN_QUANTITY);
        int priceColumnIndex = cursor.getColumnIndex(Item.ItemEntites.COLUMN_PRICE);
        int imageColumnIndex = cursor.getColumnIndex(Item.ItemEntites.COLUMN_IMAGE_PATH);

        final String itemName = cursor.getString(nameColumnIndex);
        final String itemImage = cursor.getString(imageColumnIndex);
        final int itemQuantity = cursor.getInt(quantityColumnIndex);
        final int itemPrice = cursor.getInt(priceColumnIndex);


        name_text_view.setText(itemName);
        quantity_text_view.setText(Integer.toString(itemQuantity));
        price_text_view.setText(Integer.toString(itemPrice));
        final Long id = cursor.getLong(cursor.getColumnIndex(Item.ItemEntites._ID));


        final Uri mUri = ContentUris.withAppendedId(Item.ItemEntites.CONTENT_URI,
                id);


        sellButton.setTag(R.string.quantity_id, cursor.getInt(quantityColumnIndex));

        sellButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity_valid = (int) v.getTag(R.string.quantity_id);


                if (quantity_valid > 0) {
                    quantity_valid--;

                    ContentValues values = new ContentValues();
                    values.put(Item.ItemEntites.COLUMN_NAME, itemName);
                    values.put(Item.ItemEntites.COLUMN_QUANTITY, quantity_valid);
                    values.put(Item.ItemEntites.COLUMN_PRICE, itemPrice);
                    values.put(Item.ItemEntites.COLUMN_IMAGE_PATH, itemImage);

                    int rowsUpdated = v.getContext().getContentResolver().update(mUri, values, null, null);
                    if (rowsUpdated == 0) {
                        Toast.makeText(v.getContext(), "Error",
                                Toast.LENGTH_LONG).show();
                    } else {
                        quantity_text_view.setText(String.valueOf(quantity_valid));
                    }


                }


            }


        });
    }
}
