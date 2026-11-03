package com.example.laborator4;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class FloareBaseAdapterS6 extends BaseAdapter {

    //S6
//am nevoie de un inflater care sa imcarce ce folosisc in resursa, se foloseste pt a incarca o resursa vizuala
//voi avaea nevoie de un view in care bag imaginea si cele 2 textview
//adapterul vechi e in activitatea 3. trebuie comentat si pus cel nou
//serializare - Parcelable -> in momenrul in care este realizata contine f multe resurse, de aceea s a creat parcelable
//(sunt asemanatoare), dar ca mod de lucru este acelasi lucru
//writeToParcel - pune in acel pachet toate obj serializare
//protected Calculator (Parcel in) - deserializeaza
//CREATOR - am o colectie si e facuta ca sa deserializeze acel pachet

    private Context ctx;
    private ArrayList<Floare> listaFlori;
    private int resourceLayout;

    public FloareBaseAdapterS6(Context ctx, ArrayList<Floare> listaFlori, int resourceLayout) {
        this.ctx = ctx;
        this.listaFlori = listaFlori;
        this.resourceLayout = resourceLayout;
    }

    @Override
    public int getCount() {
        return listaFlori.size();
    }

    @Override
    public Object getItem(int i) {
        return listaFlori.get(i);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View v = inflater.inflate(R.layout.floare_base_adapter_listview, parent, false);

        TextView tvDenumire = v.findViewById(R.id.tvDenumire);
        TextView tvSpini = v.findViewById(R.id.tvSpini);
        TextView tvMediu = v.findViewById(R.id.tvMediu);
        TextView tvCuloare = v.findViewById(R.id.tvCuloare);
        TextView tvRating = v.findViewById(R.id.tvRating);
        TextView tvPerena = v.findViewById(R.id.tvPerena);

        Floare f = (Floare) getItem(position);

        tvDenumire.setText(f.getDenumire());
        tvSpini.setText(f.getAreSpini()?"Da":"Nu");
        tvMediu.setText(f.getTipMediu());
        tvCuloare.setText(f.getCuloare());
        tvRating.setText(String.valueOf(f.getRating()));
        tvPerena.setText(f.getEstePerena()?"Da":"Nu");

        return v;
    }


}
