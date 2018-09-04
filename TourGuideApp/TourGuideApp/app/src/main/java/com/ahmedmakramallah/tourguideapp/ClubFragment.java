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
public class ClubFragment extends Fragment {


    public ClubFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.location_list, container, false);

        final ArrayList<CustomLocation> clubLocation = new ArrayList<CustomLocation>();
        clubLocation.add(new CustomLocation(getString(R.string.al_ahly_club), getString(R.string.al_ahly_club_addr), R.drawable.al_ahly_sc));
        clubLocation.add(new CustomLocation(getString(R.string.misr_lel_makkasa_club), getString(R.string.misr_lel_makkasa_club_addr), R.drawable.misr_lel_makkasa_sc));
        clubLocation.add(new CustomLocation(getString(R.string.zamalek_club), getString(R.string.al_ahly_club_addr), R.drawable.zamalek_sc));
        clubLocation.add(new CustomLocation(getString(R.string.al_masry_club), getString(R.string.al_masry_club_addr), R.drawable.al_masry_sc));
        clubLocation.add(new CustomLocation(getString(R.string.smouha_club), getString(R.string.smouha_club_addr), R.drawable.smouha_sc));
        clubLocation.add(new CustomLocation(getString(R.string.ismaily_club), getString(R.string.ismaily_club_addr), R.drawable.ismaily_sc));
        clubLocation.add(new CustomLocation(getString(R.string.talaea_el_gaish_club), getString(R.string.talaea_el_gaish_club_addr), R.drawable.el_gaish_sc));
        clubLocation.add(new CustomLocation(getString(R.string.al_ittihad_alexandria_club), getString(R.string.al_ittihad_alexandria_club_addr), R.drawable.alittihad_alexandria_sc));
        clubLocation.add(new CustomLocation(getString(R.string.el_mokawloon_club), getString(R.string.el_mokawloon_club_addr), R.drawable.el_mokawloon_sc));
        clubLocation.add(new CustomLocation(getString(R.string.enppi_club), getString(R.string.enppi_club_addr), R.drawable.enppi_sc));

        CustomLocationAdapter customLocationAdapter = new CustomLocationAdapter(getActivity(), clubLocation, R.color.category_club);
        ListView clubListView = (ListView) rootView.findViewById(R.id.list);
        clubListView.setAdapter(customLocationAdapter);

        return rootView;
    }

}
