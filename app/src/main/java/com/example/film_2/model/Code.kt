package com.example.film_2.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Code {

    @SerializedName("results")
    @Expose
    var results: List<Result>?=null

    @SerializedName("page")
    @Expose
    var page: String?=null

    @SerializedName("total_results")
    @Expose
    var total_results: String?=null

    @SerializedName("total_pages")
    @Expose
    var total_pages: String?=null
}