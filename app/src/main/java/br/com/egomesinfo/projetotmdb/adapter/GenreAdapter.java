package br.com.egomesinfo.projetotmdb.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import br.com.egomesinfo.projetotmdb.GenreActivity;
import br.com.egomesinfo.projetotmdb.R;
import br.com.egomesinfo.projetotmdb.model.Genre;

import static br.com.egomesinfo.projetotmdb.BuildConfig.THE_MOVIE_DB_API_TOKEN;

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.MyViewHolder> {

    private Context mContext;
    private List<Genre> genreList;

    public GenreAdapter(Context mContext, List<Genre> genreList){
        this.mContext = mContext;
        this.genreList = genreList;

    }

    @Override
    public GenreAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.genre_card, viewGroup, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final GenreAdapter.MyViewHolder viewHolder, int i){
        viewHolder.title.setText(genreList.get(i).getName());

       /* String gener = "https://image.tmdb.org/t/p/w500" + movieList.get(i).getPosterPath();

        https://api.themoviedb.org/3/genre/movie/list?api_key=baebb72c7791db8b4fb7ef34b3c721dd&language=en-US  */

        String namegenre = "https://api.themoviedb.org/3/genre/movie/list" + genreList.get(i).getId();

          }

    @Override
    public int getItemCount(){

        return genreList.size();

    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView title;
        public TextView idgenre;

        public MyViewHolder(View view){
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            idgenre = (TextView) view.findViewById(R.id.idgenre);

            view.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION){
                    Genre clickedDataItem = genreList.get(pos);
                    Integer movie_id = genreList.get(pos).getId();
                        Intent intent = new Intent(mContext,GenreActivity.class);;
                    intent.putExtra("movie_id", movie_id);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(intent);
                    Toast.makeText(v.getContext(), "You clicked " + clickedDataItem.getId(), Toast.LENGTH_SHORT).show();
                }
                }
            });

        }
    }
}
