package navanth.com.androidmvpwithunittestingapp

import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.widget.SwipeRefreshLayout
import android.view.View
import android.widget.AbsListView
import android.widget.ListView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import navanth.com.androidmvpwithunittestingapp.base.BaseActivity
import navanth.com.androidmvpwithunittestingapp.engine.MoviesDataContract
import navanth.com.androidmvpwithunittestingapp.engine.MoviesDataPresenter
import navanth.com.androidmvpwithunittestingapp.entity.Movie
import navanth.com.androidmvpwithunittestingapp.entity.adapter.SwipeListAdapter


class MainActivity : BaseActivity(), MoviesDataContract.View, SwipeRefreshLayout.OnRefreshListener {

    private var adapter: SwipeListAdapter? = null
    private var moviesDataPresenter: MoviesDataPresenter? = null
    private var lvMovies: ListView? = null
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        adapter = SwipeListAdapter(this)
        lvMovies = view_flipper.findViewById<ListView>(R.id.lvMovies);
        lvMovies?.adapter = adapter
        moviesDataPresenter = MoviesDataPresenter(MoviesDataPresenter.getInstance())
        moviesDataPresenter!!.setView(this)
        fetchMoviesData()
        swipeRefreshLayout.setOnRefreshListener(this)
        setScrollListenerForListView()
    }

    private fun setScrollListenerForListView() {
        lvMovies?.setOnScrollListener(object : AbsListView.OnScrollListener {
            override fun onScrollStateChanged(view: AbsListView, scrollState: Int) {
            }

            override fun onScroll(view: AbsListView, firstVisibleItem: Int, visibleItemCount: Int, totalItemCount: Int) {
                val topRowVerticalPosition = if (lvMovies == null || lvMovies?.getChildCount() === 0)
                    0
                else
                    lvMovies?.getChildAt(0)!!.getTop()
                swipeRefreshLayout.setEnabled(firstVisibleItem == 0 && topRowVerticalPosition >= 0)
            }
        })
    }

    private fun fetchMoviesData() {
        swipeRefreshLayout.post {
            swipeRefreshLayout.isRefreshing = true
            moviesDataPresenter!!.fetchMoviesData()
        }
    }

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun setMoviesData(moviesList: List<Movie>?) {
        view_flipper.displayedChild = 0
        adapter!!.setMovieList(moviesList)
    }

    override fun setNoData(noMovieData: String?) {
        txtError.text = noMovieData
        view_flipper.displayedChild = 1
    }

    override fun setError(error: String?) {
        view_flipper.findViewById<TextView>(R.id.txtError).text = error
        view_flipper.displayedChild = 1
    }

    override fun onRefresh() {
        moviesDataPresenter!!.fetchMoviesData()
    }

    override fun onSwipeRefresh(isRefreshing: Boolean?) {
        swipeRefreshLayout.setRefreshing(isRefreshing!!)
    }


}
