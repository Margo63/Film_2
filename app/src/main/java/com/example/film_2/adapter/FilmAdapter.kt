package com.example.film_2.adapter

import android.app.AppComponentFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.film_2.R
import com.example.film_2.model.Result
import java.util.*
import kotlin.collections.ArrayList

class FilmAdapter(var items: List<Result>, var context: AppCompatActivity, var clickListener:OnItemClickedListener):RecyclerView.Adapter<FilmAdapter.ViewHolder>(),
    Filterable{

    var filmFilmList =  ArrayList<Result>()as List<Result>
            init{
        filmFilmList=items
    }

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        private val title = itemView.findViewById<TextView>(R.id.textView)
        private val data = itemView.findViewById<TextView>(R.id.textView2)
        private val poster = itemView.findViewById<ImageView>(R.id.imageView)


        fun bind(item:Result,action:OnItemClickedListener){
            title.text = item.title
            data.text = item.release_date

            val image ="https://image.tmdb.org/t/p/w500"+item.poster_path
            Glide.with(context).load(image)
                .placeholder(R.drawable.ic_launcher_background)
                .into(poster)

            itemView.setOnClickListener{
                action.onItemClicked(item,adapterPosition)

            }

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder=
        ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.card_film,parent,false))

    override fun getItemCount(): Int = filmFilmList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(filmFilmList[position],clickListener)
    }

    interface OnItemClickedListener{
        fun  onItemClicked(item: Result,position: Int)
    }



    override fun getFilter(): Filter {
       return object: Filter(){
           override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch=constraint.toString()
               if(charSearch.isEmpty()){
                   filmFilmList=items
               }
               else{
                   val resultList=ArrayList<Result>()
                   for (row in items){
                       if(row.title?.toLowerCase(Locale.ROOT)?.contains(charSearch.toLowerCase(Locale.ROOT))!!){
                           resultList.add(row)
                       }
                   }
                   filmFilmList = resultList
               }
               val filterResult = FilterResults()
               filterResult.values = filmFilmList
               return filterResult
           }

           override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filmFilmList = results?.values as ArrayList<Result>
               notifyDataSetChanged()
           }

       }
    }
}