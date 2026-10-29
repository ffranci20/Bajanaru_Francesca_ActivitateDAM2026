package com.example.laborator4;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ActivitateLista2 extends AppCompatActivity {

    private ArrayList<Floare> listaFlori;
    private ArrayAdapter<Floare> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activitate_lista2);

        ListView lvFlori = findViewById(R.id.lvFlori);


        listaFlori = getIntent().getParcelableArrayListExtra("lista_key");

        if (listaFlori == null) {
            listaFlori = new ArrayList<>();
        }

        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                listaFlori
        );
        lvFlori.setAdapter(adapter);

        lvFlori.setOnItemClickListener((parent, view, position, id) -> {
            Floare floareSelectata = listaFlori.get(position);
            Toast.makeText(ActivitateLista2.this, floareSelectata.toString(), Toast.LENGTH_LONG).show();
        });

        lvFlori.setOnItemLongClickListener((parent, view, position, id) -> {
            listaFlori.remove(position);
            adapter.notifyDataSetChanged();

            Toast.makeText(ActivitateLista2.this, "Floare ștearsă din listă!", Toast.LENGTH_SHORT).show();

            return true;
        });
    }
}