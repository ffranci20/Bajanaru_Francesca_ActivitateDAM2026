package com.example.laborator4;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class LVPozeActivitate extends AppCompatActivity {

    private List<Bitmap> imagini = new ArrayList<>();
    private ArrayList<Poze> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lvpoze_activitate);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // link-urile DIRECTE catre imagini (trebuie sa fie fisiere .jpg/.png, nu pagini)
        List<String> linkuriImagini = new ArrayList<>();
        linkuriImagini.add("https://img.a1.ro/0/2019/2/13/842167/15500492268f9ca40e.jpg");
        linkuriImagini.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSYNuYR2QIVl1GwcUZiFwfMJg6YlLArp10zEyVPJbZzgQ&s=10");
        linkuriImagini.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTA7-IqTVpdl-f7hMVJFzbViHOH9taX_HpPckDiAiQCbw&s=10");
        linkuriImagini.add("https://www.mecarul.ro/wp-content/uploads/2022/04/Cultura-de-floarea-soarelui2-1200x800.jpg");
        linkuriImagini.add("https://www.glissandogardencenter.ro/wp-content/uploads/2018/12/Crizantema-1-600x600.jpg");

        // pornim un fir de executie secundar pentru descarcare
        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.myLooper());

        executor.execute(new Runnable() {
            @Override
            public void run() {
                for (String link : linkuriImagini) {
                    HttpURLConnection con = null;
                    try {
                        URL url = new URL(link);
                        con = (HttpURLConnection) url.openConnection();
                        InputStream is = con.getInputStream();
                        imagini.add(BitmapFactory.decodeStream(is));
                    } catch (Exception e) {
                        e.printStackTrace();
                        imagini.add(null);
                    }
                }

                // dupa ce s-au descarcat imaginile ne intoarcem pe firul principal
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        lista = new ArrayList<>();
                        lista.add(new Poze("Margarete", imagini.get(0), "https://a1.ro/galerie/imagini-cu-flori-de-primavara-poze-care-iti-dau-energie-id842167-play1382317.html"));
                        lista.add(new Poze("Lisianthus", imagini.get(1), "https://en.wikipedia.org/wiki/Eustoma"));
                        lista.add(new Poze("Bujori", imagini.get(2), "https://ro.wikipedia.org/wiki/Bujor"));
                        lista.add(new Poze("Floarea soarelui", imagini.get(3), "https://www.mecarul.ro/cultura-de-floarea-soarelui-etape-de-ingrijire/"));
                        lista.add(new Poze("Crizantema", imagini.get(4), "https://www.glissandogardencenter.ro/fitofarmacie/plante/plante-decorative/plante-ornamentale-flori/chrysanthemum-2/"));

                        ListView lv = findViewById(R.id.lvPoze);
                        ImagineAdapter adapter = new ImagineAdapter(getApplicationContext(), lista, R.layout.item_imagine);
                        lv.setAdapter(adapter);
                    }
                });
            }
        });

        ListView lv = findViewById(R.id.lvPoze);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(getApplicationContext(), WebViewActivity.class);
                it.putExtra("link", lista.get(i).getLink());
                startActivity(it);
            }
        });
    }
}
