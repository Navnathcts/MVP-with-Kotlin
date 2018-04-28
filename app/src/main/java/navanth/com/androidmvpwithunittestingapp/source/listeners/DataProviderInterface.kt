package navanth.com.androidmvpwithunittestingapp.source.listeners


open interface DataProviderInterface<T> {
    fun passNetworkResponseToPresenter(t: T?)
    fun onError(error: String?)
}