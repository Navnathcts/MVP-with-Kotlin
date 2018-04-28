package navanth.com.androidmvpwithunittestingapp.source

import android.util.Log
import navanth.com.androidmvpwithunittestingapp.entity.Movie
import navanth.com.androidmvpwithunittestingapp.source.listeners.DataProviderInterface
import navanth.com.androidmvpwithunittestingapp.source.network_interfaces.NetworkAPIServices
import navanth.com.androidmvpwithunittestingapp.source.networkmodule.RetrofitModule
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


open class MovieDataSource constructor() : INetworkDataSource {
    override fun fetchNetworkData(providerInterface: DataProviderInterface<List<Movie>?>, offset: String?): List<Movie>? {
        var request = RetrofitModule.getInstance().create(NetworkAPIServices::class.java).getMovieList(offset!!)
        request.enqueue(object : Callback<List<Movie>> {
            override fun onResponse(call: Call<List<Movie>>, response: Response<List<Movie>>) {
                Log.d("MovieDataSource ", "onResponse ")
                providerInterface.passNetworkResponseToPresenter(response.body())
            }

            override fun onFailure(call: Call<List<Movie>>, t: Throwable) {
                Log.d("MovieDataSource ", "onFailure ")
                providerInterface.onError(t.message)
            }

        })
        return null
    }
}