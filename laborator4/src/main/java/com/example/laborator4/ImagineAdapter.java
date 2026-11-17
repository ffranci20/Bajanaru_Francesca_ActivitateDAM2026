package com.example.laborator4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.ResourceBundle;

public class ImagineAdapter extends BaseAdapter {
    private Context ctx;
    private ArrayList<Poze> listaPoze;
    private int resourceLayout;

    public ImagineAdapter(Context ctx, ArrayList<Poze> listaPoze, int resourceLayout) {
        this.ctx = ctx;
        this.listaPoze = listaPoze;
        this.resourceLayout = resourceLayout;
    }


    @Override
    public int getCount() {
        return listaPoze.size();
    }

    @Override
    public Object getItem(int position) {
        return listaPoze.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View v = inflater.inflate(R.layout.item_imagine, parent, false);

        TextView tvText = v.findViewById(R.id.tvTextImagine);
        ImageView ivImagine = v.findViewById(R.id.ivImagine);

        Poze p = (Poze) getItem(position);

        Poze p1 = new Poze(p.getTextAfisat(), p.getImagine(), p.getLink());

        tvText.setText(p.getTextAfisat().toString().trim());
        ivImagine.setImageBitmap(p.getImagine());

        return v;
    }
}
