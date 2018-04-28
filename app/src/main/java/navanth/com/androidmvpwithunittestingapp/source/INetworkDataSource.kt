package navanth.com.androidmvpwithunittestingapp.source

import navanth.com.androidmvpwithunittestingapp.entity.Movie
import navanth.com.androidmvpwithunittestingapp.source.listeners.DataProviderInterface

interface INetworkDataSource {

    fun fetchNetworkData(providerInterface: DataProviderInterface<List<Movie>?>,offset: String?): List<Movie>?

    interface IRepositoryDataSource {
        fun fetchNetworkData(providerInterface: DataProviderInterface<List<Movie>?>, offset: String?): List<Movie>?
    }
}