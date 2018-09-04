package com.ahmedmakramallah.tourguideapp;

/**
 * Created by ahmed on 8/1/2017.
 */

public class CustomLocation {
    // at the beginning there is no image provided.
    public static final int NO_IMAGE_PROVIDED = -1 ;
    private String mLocationName;
    private String mLocationAddress;
    private int mImageResourceId =  NO_IMAGE_PROVIDED;

    // constructs a new word with initial values for locationName and locationAddress
    public CustomLocation(String locationName, String locationAddress){
        mLocationName = locationName;
        mLocationAddress = locationAddress;
    }

    // constructs a new word with initial values for locationName, locationAddress and imageResourceId
    public CustomLocation(String locationName, String locationAddress, int imageResourceId){
        mLocationName = locationName;
        mLocationAddress = locationAddress;
        mImageResourceId = imageResourceId;
    }

    public String getmLocationName(){
        return mLocationName;
    }

    public String getmLocationAddress(){
        return mLocationAddress;
    }

    public int getmImageResourceId(){
        return mImageResourceId;
    }

    /**
     *Returns whether or not there is an image for this place
     */
    public boolean hasImage(){
        return mImageResourceId != NO_IMAGE_PROVIDED ;
    }

    @Override
    public String toString() {
        return "CustomLocation{" +
                "mLocationName='" + mLocationName + '\'' +
                ", mLocationAddress='" + mLocationAddress + '\'' +
                ", mImageResourceId=" + mImageResourceId +
                '}';
    }
}
