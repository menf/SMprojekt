package com.github.menf.smprojekt;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import com.github.menf.smprojekt.util.AdapterAkwaria;
import com.github.menf.smprojekt.util.Akwaria;
import com.github.menf.smprojekt.util.AkwariaImp;
import com.github.menf.smprojekt.util.Bazadanych;


/**
 * Created by menf on 2017-01-19.
 */

public  class MainFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    private AkwariaImp akw;
    private Akwaria tmp;
    private ListView lista;
    private AdapterAkwaria adapter;
    private Button button;
    private Button button2;
    private int selecteditem=-666;
    public MainFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
      //  args.putInt(ARG_SECTION_NUMBER);
      //  fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onPause() {
        akw.close();
        Log.d("onPause","onPause");
        super.onPause();
    }

    @Override
    public void onResume() {
        akw.open();
        Log.d("onResume","onResume");
        lista.setAdapter(null);
        adapter = new AdapterAkwaria(getActivity(),akw.PobierzWszystkieAkwaria());
        lista.setAdapter(adapter);
        super.onResume();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        Bazadanych baza = Bazadanych.PobierzBazeDanych(getActivity(), "bazadanych.db", null, 1);
        lista= (ListView) rootView.findViewById(R.id.ListViewAkwaria);
        button= (Button) rootView.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View arg0) {
                                          Intent intent = new Intent(getActivity(), NoweAkwariumActivity.class);
                                          startActivity(intent);
                                      }
                                  });
                akw = new AkwariaImp(getActivity(),baza);
        button2= (Button) rootView.findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if(selecteditem>-1) {
                    String s = Integer.toString(selecteditem);
                    Intent intent = new Intent(getActivity(), AkwariumActivity.class);
                    intent.putExtra("id", s);
                    startActivity(intent);
                }
            }
        });
        akw.open();
        adapter = new AdapterAkwaria(getActivity(),akw.PobierzWszystkieAkwaria());
        lista.setAdapter(adapter);
lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position,
                                    long id) {
                 tmp =(Akwaria) lista.getItemAtPosition(position);
 selecteditem=tmp.getId();

            }
        });
        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int arg2, long arg3) {
                tmp =(Akwaria) lista.getItemAtPosition(arg2);

                new AlertDialog.Builder(getActivity())
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle(R.string.potwierdzenie_tytul)
                        .setMessage(R.string.potwierdzenie)
                        .setPositiveButton(R.string.tak, new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                akw.UsunAkwarium(tmp.getId());
                                adapter.remove(tmp);
                                adapter.notifyDataSetChanged();
                            }

                        })
                        .setNegativeButton(R.string.nie, null)
                        .show();
                return false;
            }
        });
        return rootView;
    }
}