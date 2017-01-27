package com.github.menf.smprojekt;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

/**
 * Created by menf on 2017-01-19.
 */

public  class SettingsFragment extends Fragment implements View.OnClickListener {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    private ToggleButton button1;
    private ToggleButton button2;
    private SharedPreferences preferences;
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
        button1 = (ToggleButton) rootView.findViewById(R.id.toggleButton);
        button1.setOnClickListener(this);
        return rootView;
    }


@Override
    public void onClick(View arg0) {
        switch (arg0.getId()) {

            case R.id.toggleButton:
                preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
                SharedPreferences.Editor editor = preferences.edit();
                if (button1.isChecked()) {
                    editor.putString("Shake","Enable");
                    editor.apply();
                } else {
                    editor.putString("Shake","Disable");
                    editor.apply();
                }
                Toast.makeText(getActivity(), "1!",
                        Toast.LENGTH_LONG).show();
                break;

        }
    }


}