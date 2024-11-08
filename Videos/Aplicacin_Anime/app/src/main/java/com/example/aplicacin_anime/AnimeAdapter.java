package com.example.aplicacin_anime;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;

import java.util.List;



public class AnimeAdapter extends RecyclerView.Adapter<AnimeAdapter.AnimeViewHolder> {

    private List<Animes> animeList;
    private Context context;

    public AnimeAdapter(List<Animes> animeList, Context context) {
        this.animeList = animeList;
        this.context = context;
    }

    @NonNull
    @Override
    public AnimeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_anime, parent, false);
        return new AnimeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimeViewHolder holder, int position) {
        Animes anime = animeList.get(position);

        holder.textViewTitle.setText(anime.getTitle());
        holder.textViewEpisodes.setText("Episodes: " + anime.getEpisodes());

        // Cargar imagen usando Glide si la URL no es nula ni vacía
        String imageUrl = anime.getImageUrl();
        if (imageUrl != null && !imageUrl.isEmpty()) {
            Glide.with(context)
                    .load(imageUrl)
                    .into(holder.imageViewAnime);
        } else {
            Log.w("AnimeAdapter", "Imagen no disponible para: " + anime.getTitle());
        }
    }

    @Override
    public int getItemCount() {
        return animeList.size();
    }

    public static class AnimeViewHolder extends RecyclerView.ViewHolder {

        ImageView imageViewAnime;
        TextView textViewTitle;
        TextView textViewEpisodes;

        public AnimeViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewAnime = itemView.findViewById(R.id.imageView);
            textViewTitle = itemView.findViewById(R.id.titleTextView);
            textViewEpisodes = itemView.findViewById(R.id.episodesTextView);
        }
    }
}
