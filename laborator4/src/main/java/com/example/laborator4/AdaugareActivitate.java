package com.example.laborator4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AdaugareActivitate extends AppCompatActivity {

    private EditText et;
    private CheckBox cb;
    private RadioGroup rg;
    private Spinner sp;
    private RatingBar rb;
    private Switch sw;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adaugare_activitate);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //leg variabilele de interfata grafica
        et = findViewById(R.id.etDenumire);
        cb = findViewById(R.id.cbSpini);
        rg = findViewById(R.id.rgMediu);
        sp = findViewById(R.id.spinnerCuloare);
        rb = findViewById(R.id.ratingBar);
        sw = findViewById(R.id.swPerena);
        btn = findViewById(R.id.btnSalvare);

        //construiesc lista derulanta a spinner ului
        String[] culori = {"Roșu", "Alb", "Galben", "Roz", "Violet"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, culori);
        sp.setAdapter(adapter);


        btn.setOnClickListener(v -> {

            String denumire = et.getText().toString().trim();
            if (denumire.isEmpty()) {
                et.setError("Câmp obligatoriu!");
                return;
            }

            boolean areSpini = cb.isChecked();
            boolean estePerena = sw.isChecked();
            float rating = rb.getRating();

            String culoare = sp.getSelectedItem().toString();

            int idSelectat = rg.getCheckedRadioButtonId();
            RadioButton rbSelectat = findViewById(idSelectat);
            String mediu = rbSelectat.getText().toString();

            Floare floareNoua = new Floare(denumire, areSpini, mediu, culoare, rating, estePerena);

            Intent intent = new Intent();
            intent.putExtra("floare_key", floareNoua);
            setResult(RESULT_OK, intent);
            finish();
        });
    }
}


