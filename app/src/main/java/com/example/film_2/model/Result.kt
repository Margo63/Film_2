package com.example.film_2.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Result {
    @SerializedName("title")
    @Expose
    var title: String?=null

    @SerializedName("poster_path")
    @Expose
    var poster_path: String?=null

    @SerializedName("popularity")
    @Expose
    var popularity: String?=null

    @SerializedName("release_date")
    @Expose
    var release_date: String?=null

    @SerializedName("overview")
    @Expose
    var overview: String?=null
}