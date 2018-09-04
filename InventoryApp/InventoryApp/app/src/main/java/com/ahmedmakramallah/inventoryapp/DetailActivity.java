package com.ahmedmakramallah.inventoryapp;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int ITEM_LOADER = 0;
    ImageView productImage;
    TextView productName, productQuantity, productPrice;
    Button add_one_quantity_button;
    Button remove_one_quantity_button;

    Button orderButton, deleteButton, doneButton;
    private Uri currentUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        currentUri = intent.getData();

        productImage = (ImageView) findViewById(R.id.product_image);
        productName = (TextView) findViewById(R.id.product_name);
        productQuantity = (TextView) findViewById(R.id.productQuantity);
        productPrice = (TextView) findViewById(R.id.product_price);
        add_one_quantity_button = (Button) findViewById(R.id.add_one_btn);
        remove_one_quantity_button = (Button) findViewById(R.id.remove_one_btn);
        orderButton = (Button) findViewById(R.id.order_btn);
        deleteButton = (Button) findViewById(R.id.delete_btn);
        doneButton = (Button) findViewById(R.id.done_btn);

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(DetailActivity.this, MainActivity.class);
                startActivity(intent1);
            }
        });

        getSupportLoaderManager().initLoader(ITEM_LOADER, null, this);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = AskOption(v);
                alertDialog.show();
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
                Item.ItemEntites.COLUMN_IMAGE_PATH,
                Item.ItemEntites.COLUMN_NUMBER
        };
        return new CursorLoader(this, currentUri, project, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        // check if cursor returns with null
        if (data == null || data.getCount() < 1) {
            return;
        }
        if (data.moveToFirst()) {
            int idIndex = data.getColumnIndex(Item.ItemEntites._ID);
            int nameIndex = data.getColumnIndex(Item.ItemEntites.COLUMN_NAME);
            int quantityIndex = data.getColumnIndex(Item.ItemEntites.COLUMN_QUANTITY);
            int priceIndex = data.getColumnIndex(Item.ItemEntites.COLUMN_PRICE);
            int imageIndex = data.getColumnIndex(Item.ItemEntites.COLUMN_IMAGE_PATH);
            int numberIndex = data.getColumnIndex(Item.ItemEntites.COLUMN_NUMBER);

            String name_of_the_product = data.getString(nameIndex);
            int quantity_of_the_product = data.getInt(quantityIndex);
            int price_of_the_product = data.getInt(priceIndex);
            String image = data.getString(imageIndex);
            String number = data.getString(numberIndex);

            //TODO: check if views are null
            if (findViewById(R.id.product_name) != null && findViewById(R.id.productQuantity) != null && findViewById(R.id.product_price) != null){
                productName.setText(name_of_the_product);
                productQuantity.setText(String.valueOf(quantity_of_the_product));
                productPrice.setText(String.valueOf(price_of_the_product));
            }

            productImage.setImageURI(null);
            if (image != null) {
                productImage.setImageURI(Uri.parse(image));
            }
            remove_one_quantity_button.setTag(R.string.quantity_id, quantity_of_the_product);
            remove_one_quantity_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int quantity_valid = (int) v.getTag(R.string.quantity_id);

                    if (quantity_valid > 0) {
                        quantity_valid--;

                        ContentValues values = new ContentValues();
                        values.put(Item.ItemEntites.COLUMN_QUANTITY, quantity_valid);

                        int rowsUpdated = v.getContext().getContentResolver().update(currentUri, values, null, null);
                        if (rowsUpdated == 0) {
                            Toast.makeText(v.getContext(), "Error occurred",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            productQuantity.setText(String.valueOf(quantity_valid));
                        }


                    }


                }
            });
            add_one_quantity_button.setTag(R.string.quantity_id, quantity_of_the_product);
            add_one_quantity_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int quantity_valid = (int) v.getTag(R.string.quantity_id);

                    quantity_valid++;

                    ContentValues values = new ContentValues();
                    values.put(Item.ItemEntites.COLUMN_QUANTITY, quantity_valid);

                    int rowsUpdated = v.getContext().getContentResolver().update(currentUri, values, null, null);
                    if (rowsUpdated == 0) {
                        Toast.makeText(v.getContext(), "Error", Toast.LENGTH_LONG).show();
                    } else {
                        productQuantity.setText(String.valueOf(quantity_valid));
                    }

                }
            });
            orderButton.setTag(R.string.quantity_id, number);
            orderButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String number = (String) v.getTag(R.string.quantity_id);

                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", number, null));
                    startActivity(intent);
                }
            });


        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    private AlertDialog AskOption(final View v) {
        AlertDialog quittingDialogBox = new AlertDialog.Builder(this)
                //set message, title, and icon
                .setTitle("Delete")
                .setMessage("sure! Do you want to Delete")

                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        int rowsDeleted = getContentResolver().delete(currentUri, null, null);
                        if (rowsDeleted == 0)
                            Toast.makeText(v.getContext(), "Failed to delete", Toast.LENGTH_SHORT).show();
                        else {
                            Toast.makeText(v.getContext(), "Delete is done", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(DetailActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                        dialog.dismiss();
                    }

                })


                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();

                    }
                })
                .create();
        return quittingDialogBox;

    }
}
