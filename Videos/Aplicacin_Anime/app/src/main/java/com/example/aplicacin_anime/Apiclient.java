package com.example.aplicacin_anime;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Apiclient {
    private static final String BASE_URL = "https://api.jikan.moe/v4/";  // Asegúrate de que esta URL sea correcta

    private static Retrofit retrofit = null;

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)  // Establecer la URL base de la API
                    .addConverterFactory(GsonConverterFactory.create())  // Usar Gson para convertir JSON
                    .build();
        }
        return retrofit;
    }

    public static AnimeApiService getAnimeService() {
        return getRetrofitInstance().create(AnimeApiService.class);  // Crear el servicio de la API
    }
}
