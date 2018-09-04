package com.ahmedmakramallah.inventoryapp;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewProductActivity extends AppCompatActivity {

    private static final int SELECTED_PICTURE = 1;
    String selectedImagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product);


        final EditText name_edit_text = (EditText) findViewById(R.id.name_edit_txt);
        final EditText quantity_edit_text = (EditText) findViewById(R.id.quantity_edit_txt);
        final EditText price_edit_text = (EditText) findViewById(R.id.price_edit_txt);
        final EditText number_edit_txt = (EditText) findViewById(R.id.number_edit_txt);
        Button select_photo_button = (Button) findViewById(R.id.select_photo);
        Button done_button = (Button) findViewById(R.id.add_new_product_btn);

        select_photo_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,
                        "Select Picture"), SELECTED_PICTURE);
            }
        });

        done_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = name_edit_text.getText().toString().trim();
                String quantity = quantity_edit_text.getText().toString().trim();
                String price = price_edit_text.getText().toString().trim();
                String number = number_edit_txt.getText().toString().trim();
                //TODO: check empty fields such as "selectedImagePath and price"
                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(quantity) || TextUtils.isEmpty(price)|| TextUtils.isEmpty(number) || TextUtils.isEmpty(selectedImagePath))
                    Toast.makeText(getApplicationContext(), "Data not found", Toast.LENGTH_SHORT).show();
                else {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(Item.ItemEntites.COLUMN_NAME, name);
                    contentValues.put(Item.ItemEntites.COLUMN_PRICE, Long.parseLong(price));
                    contentValues.put(Item.ItemEntites.COLUMN_QUANTITY, Long.parseLong(quantity));
                    contentValues.put(Item.ItemEntites.COLUMN_NUMBER, number);
                    contentValues.put(Item.ItemEntites.COLUMN_IMAGE_PATH, selectedImagePath);
                    Uri newUri = getContentResolver().insert(Item.ItemEntites.CONTENT_URI, contentValues);
                    if (newUri == null)
                        Toast.makeText(getApplicationContext(), "insertion is not applied", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getApplicationContext(), "the insertion is applied", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(NewProductActivity.this, MainActivity.class);
                    startActivity(intent);


                }

            }
        });

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECTED_PICTURE) {
                Uri selectedImageUri = data.getData();
                selectedImagePath = selectedImageUri.toString();

            }
        }
    }

    /**
     * getPath() method to retrieve the path of an image URI
     */
    public String getPath(Uri uri) {
        // just some safety built in
        if (uri == null) {
            Context context = getApplicationContext();
            CharSequence text = "Enter a photo";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return null;
        }
        // try to retrieve the image from the media store first
        // this will only work for images selected from gallery
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        if (cursor != null) {
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            String path = cursor.getString(column_index);
            cursor.close();
            return path;
        }
        // this is our fallback here
        return uri.getPath();
    }
}
