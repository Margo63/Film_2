package com.example.film_2.service

import com.example.film_2.model.Code
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import javax.crypto.Cipher

interface Service {
    @GET("/3/movie/popular")
    fun getResults(@Query("api_key") apiKey:String): Call<Code>
}