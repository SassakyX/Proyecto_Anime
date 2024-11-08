package com.example.aplicacin_anime;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import java.util.List;
public interface AnimeApiService {
    @GET("anime")
    Call<AnimeResponse> getAnimes();
}

