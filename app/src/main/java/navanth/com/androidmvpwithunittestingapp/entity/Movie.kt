package navanth.com.androidmvpwithunittestingapp.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Movie {

    @SerializedName("rank")
    @Expose
    var rank: Int? = null
    @SerializedName("title")
    @Expose
    var title: String? = null

}