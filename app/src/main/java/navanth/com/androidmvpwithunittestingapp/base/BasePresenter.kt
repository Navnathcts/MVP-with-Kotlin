package navanth.com.androidmvpwithunittestingapp.base

import android.app.ProgressDialog

open interface BasePresenter<P> {
    fun setView(p: P?)
    fun destroyView()
}