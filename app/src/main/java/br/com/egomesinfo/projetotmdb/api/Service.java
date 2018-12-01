package br.com.egomesinfo.projetotmdb.api;

import br.com.egomesinfo.projetotmdb.model.GenresResponse;
import br.com.egomesinfo.projetotmdb.model.MoviesResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Service {
    @GET("movie/popular")
    Call<MoviesResponse> getPopularMovies(@Query("api_key") String apiKey);

    @GET("movie/top_rated")
    Call<MoviesResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("genre/movie/list")
    Call<GenresResponse> getIdGenre(@Path("movie_id") int id, @Query("api_key") String apiKey);

}
