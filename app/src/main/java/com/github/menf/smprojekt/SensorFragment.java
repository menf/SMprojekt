package com.github.menf.smprojekt;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.hardware.SensorEventListener;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SensorFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SensorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SensorFragment extends Fragment {
    private OnFragmentInteractionListener mListener;
    private TextView textLIGHT_available, textLIGHT_reading;
    private  Sensor LightSensor;
    SensorEventListener LightSensorListener;
    public SensorFragment(){

    }

    public static SensorFragment newInstance() {
        SensorFragment fragment = new SensorFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_sensor, container, false);
        textLIGHT_available
                = (TextView) rootView.findViewById(R.id.LIGHT_available);
        textLIGHT_reading
                = (TextView)rootView.findViewById(R.id.LIGHT_reading);

        SensorManager mySensorManager = (SensorManager)getActivity().getSystemService(Context.SENSOR_SERVICE);

         LightSensor = mySensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        LightSensorListener
                = new SensorEventListener(){

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onSensorChanged(SensorEvent event) {
                if(event.sensor.getType() == Sensor.TYPE_LIGHT){
                    textLIGHT_reading.setText("Natężenie światła: " + event.values[0] + " lx");
                }
            }

        };
        if(LightSensor != null){
            textLIGHT_available.setText("Sensor światła dostępny");
            mySensorManager.registerListener(
                    LightSensorListener,
                    LightSensor,
                    SensorManager.SENSOR_DELAY_NORMAL);

        }else{
            textLIGHT_available.setText("Sensor światła nie dostępny");
        }




        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
    @Override
    public void onPause() {
        Log.d("onPause","onPause");
        super.onPause();
    }

    @Override
    public void onResume() {
        Log.d("onResume","onResume");

        super.onResume();
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }





}