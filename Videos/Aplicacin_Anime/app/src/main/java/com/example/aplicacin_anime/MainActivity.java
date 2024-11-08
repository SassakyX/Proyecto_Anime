package com.example.aplicacin_anime;
import android.os.Bundle;
import android.util.Log;
import android.widget.SearchView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AnimeAdapter animeAdapter;
    private List<Animes> animeList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        animeAdapter = new AnimeAdapter(animeList, this);
        recyclerView.setAdapter(animeAdapter);

        // Obtener animes desde la API
        getAnimes();
    }

    private void getAnimes() {
        Apiclient.getAnimeService().getAnimes().enqueue(new Callback<AnimeResponse>() {
            @Override
            public void onResponse(Call<AnimeResponse> call, Response<AnimeResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    animeList.clear();  // Limpiar la lista de animes
                    animeList.addAll(response.body().getData());  // Agregar los animes obtenidos
                    animeAdapter.notifyDataSetChanged();  // Notificar al adaptador que los datos cambiaron
                } else {
                    Log.e("MainActivity", "Error en la respuesta: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<AnimeResponse> call, Throwable t) {
                Log.e("MainActivity", "Error en la conexión: " + t.getMessage());
            }
        });
    }
}
