package com.example.laborator4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView tvRezultat;
    private Button btnDeschide;

    private ArrayList<Floare> listaFlori = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Button btnVeziLista = findViewById(R.id.btnVeziLista);

        btnVeziLista.setOnClickListener(v->{
            Intent intent = new Intent(MainActivity.this, ActivitateLista2.class);
            intent.putParcelableArrayListExtra("lista_key", listaFlori);
            startActivity(intent);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvRezultat = findViewById(R.id.tvRezultat);
        btnDeschide = findViewById(R.id.btnDeschideAdaugare);

        ActivityResultLauncher<Intent> launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Floare floarePrimita = result.getData().getParcelableExtra("floare_key");

                        if (floarePrimita != null) {
                            tvRezultat.setText(floarePrimita.toString());

                            //adaug in lista
                            listaFlori.add(floarePrimita);

                            Toast.makeText(this, "Obiect preluat cu succes!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        btnDeschide.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AdaugareActivitate.class);
            launcher.launch(intent);
        });
    }
}

