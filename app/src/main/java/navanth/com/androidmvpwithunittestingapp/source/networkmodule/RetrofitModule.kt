package navanth.com.androidmvpwithunittestingapp.source.networkmodule

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


open class RetrofitModule {

    companion object {
        var BASE_URL = "https://api.androidhive.info"
         var retrofitInstance: Retrofit?=null
        fun getInstance(): Retrofit {
            if (retrofitInstance == null) {
                retrofitInstance = Retrofit
                        .Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()

            }
            return retrofitInstance!!
        }
    }
}