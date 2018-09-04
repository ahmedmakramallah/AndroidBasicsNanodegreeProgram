package com.ahmedmakramallah.tourguideapp;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by ahmed on 8/1/2017.
 */

public class CustomLocationAdapter extends ArrayAdapter<CustomLocation> {

    // Resource Id for the background color to this list of word
    private int mColorResourceId ;

    // to know the resource of this custom adapter.
    public static final String LOG_TAG = CustomLocationAdapter.class.getSimpleName();

    public CustomLocationAdapter(Activity context, ArrayList<CustomLocation> location, int colorResourceId) {
        super(context, 0, location);
        mColorResourceId = colorResourceId;
    }

    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        View listItemView = convertView;
        // Check if the existing view is being reused, otherwise inflate the view
        if (listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        // Get the  object located at this position in the list
        CustomLocation currentLocation = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView locName = (TextView) listItemView.findViewById(R.id.locName);
        // Get the name of the location from the current CustomLocation object and
        // set this text on the name TextView
        locName.setText(currentLocation.getmLocationName());

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView locAddress = (TextView) listItemView.findViewById(R.id.locAddress);
        // Get the address of the location from the current CustomLocation object and
        // set this text on the address TextView
        locAddress.setText(currentLocation.getmLocationAddress());

        // Find the ImageView in the list_item.xml layout with the ID of image
        ImageView image = (ImageView) listItemView.findViewById(R.id.list_item_icon);
        if(currentLocation.hasImage()) {
            // Get the image from the current CustomLocation object and
            // set this image on the imageView
            image.setImageResource(currentLocation.getmImageResourceId());
            // make sure the image is visible
            image.setVisibility(View.VISIBLE);
        }else {
            /**
             *we don't left blank sapce at the the left of the list view at .......
             * so make visibiltiy is gone to delete the space
             otherwise hide the image (Visibility to GONE ) */
            image.setVisibility(View.GONE);
        }
        // set the theme color for the list item
        View textContainer = listItemView.findViewById(R.id.textContainer);
        // find the color that the resource ID maps to
        int color = ContextCompat.getColor(getContext(), mColorResourceId);
        // set the background color of the next container view
        textContainer.setBackgroundColor(color);
        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }
}
