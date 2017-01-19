package com.github.menf.smprojekt;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by menf on 2017-01-19.
 */

public  class SettingsFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    public SettingsFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static SettingsFragment newInstance() {
        SettingsFragment fragment = new SettingsFragment();
        //   Bundle args = new Bundle();
        // args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        // fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.settings_fragment, container, false);
        //   TextView textView = (TextView) rootView.findViewById(R.id.section_label);
        //   textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
        return rootView;
    }


    public void onClick(View arg0) {
        switch (arg0.getId()) {

            case R.id.toggleButton:
                //
            case R.id.toggleButton2:
                //
        }

    }
}