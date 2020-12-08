package com.example.film_2.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.film_2.R
import com.example.film_2.adapter.FilmAdapter
import com.example.film_2.model.Code
import com.example.film_2.model.Result
import com.example.film_2.service.Instance
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(),FilmAdapter.OnItemClickedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getFilm()
    }
    var result: ArrayList<Result>?=null
    val context= this
    val KEY = "9d2611cc48aa4f2659a7af09c2f35509"

    private fun getFilm(){
        val filmService=Instance.apiService
        val call: Call<Code> =
        filmService.getResults(KEY)

        call.enqueue(object : Callback<Code>{
            override fun onFailure(call: Call<Code>, t: Throwable) {
                Log.e("Error", t.toString())
            }

            override fun onResponse(call: Call<Code>, response: Response<Code>) {
               val filmInfo =
                   response.body()
                if(filmInfo!=null){
                    result = filmInfo.results as ArrayList<Result>?
                    recycleView.layoutManager = LinearLayoutManager(context)

                    val adapter = FilmAdapter(result!!,context, context)

                    search.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                        override fun onQueryTextSubmit(query: String?): Boolean {
                            return false
                        }

                        override fun onQueryTextChange(newText: String?): Boolean {
                            adapter.filter.filter(newText)
                            return false
                        }

                    })


                    recycleView.adapter=adapter
                }
            }

        });


    }

    override fun onItemClicked(item: Result, position: Int) {
        intent = Intent(this,MainActivity2::class.java)
        intent.putExtra("title",item.title)
        intent.putExtra("poster",item.poster_path)
        intent.putExtra("popularity",item.popularity)
        intent.putExtra("overview",item.overview)
        intent.putExtra("data",item.release_date)
        startActivity(intent)
    }
}