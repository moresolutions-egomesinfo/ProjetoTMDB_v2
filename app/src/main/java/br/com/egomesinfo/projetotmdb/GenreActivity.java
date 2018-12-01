package br.com.egomesinfo.projetotmdb;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import br.com.egomesinfo.projetotmdb.adapter.GenreAdapter;
import br.com.egomesinfo.projetotmdb.api.Client;
import br.com.egomesinfo.projetotmdb.api.Service;
import br.com.egomesinfo.projetotmdb.model.Genre;
import br.com.egomesinfo.projetotmdb.model.GenresResponse;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static br.com.egomesinfo.projetotmdb.BuildConfig.THE_MOVIE_DB_API_TOKEN;


public class GenreActivity extends AppCompatActivity {
    TextView title;
    private RecyclerView recyclerView;
    private GenreAdapter adapter;
    private List<Genre> genreList;
    private SwipeRefreshLayout swipeContainer;
    ProgressDialog pd;
    public static final String LOG_TAG = GenreAdapter.class.getName();
    Genre genre;
    int movie_id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genre);

        initViews2();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_genre);

    }

    public Activity getActivity() {
        Context context = this;
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        return null;

    }

    private void initViews2() {

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_genre);
        genreList = new ArrayList<>();
        adapter = new GenreAdapter(this, genreList);

        if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        }
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.genre_content);
        swipeContainer.setColorSchemeResources(android.R.color.holo_orange_dark);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initViews2();
                Toast.makeText(GenreActivity.this, "Genre Refreshed", Toast.LENGTH_SHORT).show();
                loadJSON1();
            }
        });

    }

    private void loadJSON1() {

        try {
            if (BuildConfig.THE_MOVIE_DB_API_TOKEN.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Please obtain API Key firstly from themoviedb.org", Toast.LENGTH_SHORT).show();
                pd.dismiss();
                return;
            }

            movie_id = genre.getId();
            Client Client = new Client();
            Service apiService =
                    Client.getClient().create(Service.class);
            Call<GenresResponse> call = apiService.getIdGenre(movie_id, BuildConfig.THE_MOVIE_DB_API_TOKEN);
            call.enqueue(new Callback<GenresResponse>() {
                @Override
                public void onResponse(Call<GenresResponse> call, Response<GenresResponse> response) {
                    List<Genre> genre = response.body().getResults();
                    recyclerView.setAdapter(new GenreAdapter(getApplicationContext(), genre));
                    recyclerView.smoothScrollToPosition(0);
                    if (swipeContainer.isRefreshing()) {
                        swipeContainer.setRefreshing(false);
                    }
                }

                @Override
                public void onFailure(Call<GenresResponse> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                    Toast.makeText(GenreActivity.this, "Error Fetching Data!", Toast.LENGTH_SHORT).show();

                }
            });
        } catch (Exception e) {
            Log.d("Error", e.getMessage());
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}

