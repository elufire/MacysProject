package com.example.macysproject.adapters

import android.content.Intent
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.macysproject.R
import com.example.macysproject.activities.DisplayMovieActivity
import com.example.macysproject.databinding.ItemBinding
import com.example.macysproject.extension.inflate
import com.example.macysproject.models.Movie
import java.util.ArrayList

class RecyclerViewAdapter(private var movies: ArrayList<Movie>?)
    : androidx.recyclerview.widget.RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapter.ViewHolder {
//        val inflatedView = parent.inflate(R.layout.item, false)
        val binding = DataBindingUtil.inflate<ItemBinding>(LayoutInflater.from(parent.context), R.layout.item, parent, false)

        return ViewHolder(binding)
    }

    override fun getItemCount() = movies?.size as Int

    override fun onBindViewHolder(holder: RecyclerViewAdapter.ViewHolder, position: Int){
        val movie = movies?.get(position)
        Log.d("TAG", "onBindViewHolder: ${movie?.poster_path}")
        Glide.with(holder.binding.root)
            .load("http://image.tmdb.org/t/p/w342${movie?.poster_path}")
            .into(holder.binding.ivMovie)
        holder.binding.movie = movie

    }

    class ViewHolder(val binding: ItemBinding) : androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root), View.OnClickListener {
//        private var view: View = v

        init {
            binding.root.setOnClickListener(this)
        }

//        fun bind(item: Movie){
//            with(binding){
//                tvMovieTitle.text =
//            }
//        }

        override fun onClick(v: View?) {
            Log.d("TAG", "Item Clicked")
            val intent = Intent(v?.context, DisplayMovieActivity::class.java)
            intent.putExtra("movie_object", binding.movie)
            v?.context?.startActivity(intent)
        }
    }

    fun setItems(movieList: ArrayList<Movie>?){
        movies = movieList
    }


}