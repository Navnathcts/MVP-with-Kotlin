package navanth.com.androidmvpwithunittestingapp.source

import navanth.com.androidmvpwithunittestingapp.entity.Movie
import navanth.com.androidmvpwithunittestingapp.source.listeners.DataProviderInterface

class MovieDataRepository(val movieDataSource: MovieDataSource) : INetworkDataSource.IRepositoryDataSource {
    companion object {
        var movieDataSource: MovieDataSource? = null

        fun getInstance(): MovieDataSource {
            if (movieDataSource == null) {
                movieDataSource = MovieDataSource()
            }
            return movieDataSource!!
        }
    }

    override fun fetchNetworkData(providerInterface: DataProviderInterface<List<Movie>?>,offset: String?): List<Movie>? {
        return movieDataSource!!.fetchNetworkData(providerInterface,offset)
    }
}