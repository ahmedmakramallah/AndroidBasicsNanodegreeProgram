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
public class CinemaFragment extends Fragment {


    public CinemaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.location_list, container, false);

        final ArrayList<CustomLocation> cinemaLocation = new ArrayList<CustomLocation>();
        cinemaLocation.add(new CustomLocation(getString(R.string.golden_stars_cinema), getString(R.string.golden_stars_cinema_addr)));
        cinemaLocation.add(new CustomLocation(getString(R.string.stars_cinema), getString(R.string.stars_cinema_addr)));
        cinemaLocation.add(new CustomLocation(getString(R.string.ramses_hilton_cinema), getString(R.string.ramses_hilton_cinema_addr)));
        cinemaLocation.add(new CustomLocation(getString(R.string.city_center_cinema), getString(R.string.city_center_cinema_addr)));
        cinemaLocation.add(new CustomLocation(getString(R.string.renaissance_nile_city_cinema), getString(R.string.renaissance_nile_city_cinema_addr)));
        cinemaLocation.add(new CustomLocation(getString(R.string.genena_cinema), getString(R.string.genena_cinema_addr)));
        cinemaLocation.add(new CustomLocation(getString(R.string.golf_city_cinema), getString(R.string.golf_city_cinema_addr)));

        CustomLocationAdapter customLocationAdapter = new CustomLocationAdapter(getActivity(), cinemaLocation, R.color.category_cinema);
        ListView restaurantListView = (ListView) rootView.findViewById(R.id.list);
        restaurantListView.setAdapter(customLocationAdapter);

        return rootView;
    }

}
