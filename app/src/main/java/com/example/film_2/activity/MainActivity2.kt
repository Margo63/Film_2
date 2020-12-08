package com.example.film_2.activity

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.film_2.R
import com.example.film_2.adapter.FilmAdapter
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.card_film.*

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val film = intent.extras
        if(film!=null){
            textView3.text=film.get("title").toString()



           val image ="https://image.tmdb.org/t/p/w500"+film.get("poster").toString()


            Glide.with(this).load(image)
                .placeholder(R.drawable.ic_launcher_background)
                .into(imageView2)



            textView4.text=film.get("popularity").toString()
            textView5.text=film.get("data").toString()
            textView6.text=film.get("overview").toString()
        }
    }
}