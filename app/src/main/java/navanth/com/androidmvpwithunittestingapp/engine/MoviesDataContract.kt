package navanth.com.androidmvpwithunittestingapp.engine

import navanth.com.androidmvpwithunittestingapp.base.BasePresenter
import navanth.com.androidmvpwithunittestingapp.base.BaseView
import navanth.com.androidmvpwithunittestingapp.entity.Movie


class MoviesDataContract {

    open interface Presenter : BasePresenter<View> {
        fun fetchMoviesData()
    }

    open interface View : BaseView<Presenter> {

        fun setMoviesData(moviesList: List<Movie>?)

        fun setNoData(noData: String?)

        fun setError(error: String?)
        fun onSwipeRefresh(isRefreshing: Boolean?)
    }
}