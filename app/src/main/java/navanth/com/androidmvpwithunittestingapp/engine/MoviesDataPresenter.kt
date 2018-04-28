package navanth.com.androidmvpwithunittestingapp.engine

import navanth.com.androidmvpwithunittestingapp.entity.Movie
import navanth.com.androidmvpwithunittestingapp.source.MovieDataRepository
import navanth.com.androidmvpwithunittestingapp.source.listeners.DataProviderInterface

class MoviesDataPresenter(var movieDataRepository: MovieDataRepository?) : MoviesDataContract.Presenter, DataProviderInterface<List<Movie>?> {

    var mView: MoviesDataContract.View? = null
    var offset: String = "0"

    override fun setView(mView: MoviesDataContract.View?) {
        this.mView = mView!!
    }

    override fun fetchMoviesData() {
        mView?.onSwipeRefresh(true)
        movieDataRepository?.fetchNetworkData(this, offset)
    }


    override fun passNetworkResponseToPresenter(movieList: List<Movie>?) {
        mView?.onSwipeRefresh(false)
        if (movieList == null) {
            mView?.setNoData("No movie data available")
        } else {
            offset = movieList.get(movieList.size - 1).rank.toString()
            mView?.setMoviesData(movieList)
        }
    }

    override fun onError(error: String?) {
        mView?.onSwipeRefresh(false)
        mView?.setError(error)
    }


    override fun destroyView() {
        mView = null
        movieDataRepository = null
    }


    companion object {

        var movieDataRepository: MovieDataRepository? = null

        fun getInstance(): MovieDataRepository? {
            if (movieDataRepository == null) {
                movieDataRepository = MovieDataRepository(MovieDataRepository.getInstance())
            }
            return movieDataRepository
        }
    }


}