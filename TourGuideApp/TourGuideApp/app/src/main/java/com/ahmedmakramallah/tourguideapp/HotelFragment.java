package com.ahmedmakramallah.tourguideapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HotelFragment extends Fragment {


    public HotelFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.location_list, container, false);

        final ArrayList<CustomLocation> hotelLocation = new ArrayList<CustomLocation>();
        hotelLocation.add(new CustomLocation(getString(R.string.ramses_hotel), getString(R.string.ramses_hotel_addr)));
        hotelLocation.add(new CustomLocation(getString(R.string.the_nile_ritz_hotel), getString(R.string.the_nile_ritz_hotel_addr)));
        hotelLocation.add(new CustomLocation(getString(R.string.sofitel_hotel), getString(R.string.sofitel_hotel_addr)));
        hotelLocation.add(new CustomLocation(getString(R.string.interContinental_hotel), getString(R.string.interContinental_hotel_addr)));
        hotelLocation.add(new CustomLocation(getString(R.string.novotel_hotel), getString(R.string.novotel_hotel_addr)));
        hotelLocation.add(new CustomLocation(getString(R.string.four_seasons_hotel), getString(R.string.four_seasons_hotel_addr)));
        hotelLocation.add(new CustomLocation(getString(R.string.grand_royal_hotel), getString(R.string.grand_royal_hotel_addr)));

        CustomLocationAdapter customLocationAdapter = new CustomLocationAdapter(getActivity(), hotelLocation, R.color.category_hotel);
        ListView hotelListView = (ListView) rootView.findViewById(R.id.list);
        hotelListView.setAdapter(customLocationAdapter);

        return rootView;
    }

}
