package com.example.laborator4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ActivitateLista2 extends AppCompatActivity {

    private ArrayList<Floare> listaFlori;
    private ArrayAdapter<Floare> adapter;

    private List<Floare> floare = null;
    private int idModificat =0;
    private FloareBaseAdapterS6 adapterCustom = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activitate_lista2);

        ListView lvFlori = findViewById(R.id.lvFlori);


        listaFlori = getIntent().getParcelableArrayListExtra("lista_key");

        if (listaFlori == null) {
            listaFlori = new ArrayList<>();
        }

//        adapter = new ArrayAdapter<>(
//                this,
//                android.R.layout.simple_list_item_1,
//                listaFlori
//        );
//        lvFlori.setAdapter(adapter);

        //custom adapter
        adapterCustom = new FloareBaseAdapterS6(getApplicationContext(), listaFlori, R.layout.floare_base_adapter_listview);
        lvFlori.setAdapter(adapterCustom);

        //asta e pt modificare (toast)
//        lvFlori.setOnItemClickListener((parent, view, position, id) -> {
//            Floare floareSelectata = listaFlori.get(position);
//            Toast.makeText(ActivitateLista2.this, floareSelectata.toString(), Toast.LENGTH_LONG).show();
//        });

        lvFlori.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intentModif = new Intent(getApplicationContext(), AdaugareActivitate.class);
                //trimitem obiectul apasat
                intentModif.putExtra("floare", listaFlori.get(i));

                //salvam pozitia randului apasat
                idModificat = i;

                //deschidem activitatea cerand un rezulat
                startActivityForResult(intentModif, 209);

                //toastsul e demonstrativ, nu trebuie scris, e optional
                //Toast.makeText(getApplicationContext(), floare.get(i).toString(), Toast.LENGTH_SHORT).show();
            }

        });

        //asta e stergerea
        lvFlori.setOnItemLongClickListener((parent, view, position, id) -> {
            listaFlori.remove(position);
            adapterCustom.notifyDataSetChanged();

            Toast.makeText(ActivitateLista2.this, "Floare ștearsă din listă!", Toast.LENGTH_SHORT).show();

            return true;
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 209 && resultCode == RESULT_OK) {
            if (data != null) {
                Floare floareModificata = data.getParcelableExtra("floare_key");
                listaFlori.set(idModificat, floareModificata);
                adapterCustom.notifyDataSetChanged();
            }
        }
    }
}