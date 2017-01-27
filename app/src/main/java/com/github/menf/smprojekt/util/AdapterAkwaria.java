package com.github.menf.smprojekt.util;

import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.LinearLayout;
        import android.widget.TextView;

import com.github.menf.smprojekt.R;

import java.util.List;

/**
 * Created by menf on 2017-01-26.
 */

public class AdapterAkwaria extends ArrayAdapter<Akwaria> {

    List<Akwaria> mylist;
    private final Context context;

    public AdapterAkwaria(Context _context, List<Akwaria> _mylist) {
        super(_context, R.layout.adapter_akwaria, _mylist);

        this.mylist = _mylist;
        this.context = _context;
    }




    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.adapter_akwaria, parent, false);
            holder.txtTitle = (TextView) convertView.findViewById(R.id.nazwa);
            holder.txtDesc = (TextView) convertView.findViewById(R.id.opis);
            convertView.setTag(holder); // view lookup cache stored in tag
        }

        else {
            holder = (ViewHolder) convertView.getTag();
        }
        // Product object
        Akwaria product = getItem(position);
        //
        holder.txtTitle.setText(product.getPojemnosc() + " l");

        holder.txtDesc.setText("Dlugosc: " + Integer.toString(product.getDlugosc()) +  " Wysokosc: " + Integer.toString(product.getWysokosc()) + " Szerokosc: " + Integer.toString(product.getSzerokosc()));

        // show image
        //ImageView img = (ImageView)convertView.findViewById(R.id.image);

        // download image
        //img.setImageResource(product.img_url);

        return convertView;
    }

   private static class ViewHolder {
        private TextView txtTitle;
        private TextView txtDesc;

    }
}
