package navanth.com.androidmvpwithunittestingapp.source.network_interfaces

import navanth.com.androidmvpwithunittestingapp.entity.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


open interface NetworkAPIServices {

    @GET("/json/imdb_top_250.php")
    fun getMovieList(@Query("offset") apiKey: String): Call<List<Movie>>

}