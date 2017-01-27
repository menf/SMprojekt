package com.github.menf.smprojekt;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.github.menf.smprojekt.util.AdapterAkwaria;
import com.github.menf.smprojekt.util.AdapterRybki;
import com.github.menf.smprojekt.util.Bazadanych;
import com.github.menf.smprojekt.util.Rybki;
import com.github.menf.smprojekt.util.RybkiImp;

import java.util.List;

/**
 * Created by menf on 2017-01-26.
 */

public class AkwariumFragment extends Fragment {
    private OnFragmentInteractionListener mListener;
    private RybkiImp ryb;
    private AdapterRybki adapter;
    private ListView lista;
    private String id;
    public AkwariumFragment(){

    }

    public static AkwariumFragment newInstance() {
        AkwariumFragment fragment = new AkwariumFragment();

      //  args.putString(ARG_PARAM2, param2);
      //  fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_akwarium, container, false);
        Bazadanych baza = Bazadanych.PobierzBazeDanych(getActivity(), "bazadanych.db", null, 1);
        lista= (ListView) rootView.findViewById(R.id.ListViewRybki);
        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int arg2, long arg3) {
 Rybki rybka =(Rybki) lista.getItemAtPosition(arg2);
                String idr = Integer.toString(rybka.getId());
                String ida = Integer.toString(rybka.getId_akwaria());
                String naz = rybka.getNazwa();
                String il = rybka.getIlosc();
                String wie = rybka.getWiek();
                Intent i = new Intent(getActivity(), EdytujRybkeActivity.class);
                i.putExtra("idr", idr);
                i.putExtra("ida", ida);
                i.putExtra("naz", naz);
                i.putExtra("il", il);
                i.putExtra("wie", wie);
                startActivity(i);
                return false;
            }
        });
        ryb = new RybkiImp(getActivity(),baza);
        ryb.open();
        id = getActivity().getIntent().getExtras().getString("id");
        adapter = new AdapterRybki(getActivity(),ryb.PobierzWszystkieRybki(Integer.parseInt(id)));
  lista.setAdapter(adapter);

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
        lista.setAdapter(null);
        adapter = new AdapterRybki(getActivity(),ryb.PobierzWszystkieRybki(Integer.parseInt(id)));
        lista.setAdapter(adapter);
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
