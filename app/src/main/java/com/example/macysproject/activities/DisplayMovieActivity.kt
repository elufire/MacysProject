package com.example.macysproject.activities

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide


import com.example.macysproject.MacysApplication
import com.example.macysproject.R
import com.example.macysproject.databinding.ActivityDisplayMovieBinding
import com.example.macysproject.databinding.ActivityHomePageBinding
import com.example.macysproject.models.Movie
import com.example.macysproject.viewmodels.HomePageViewModel
import kotlinx.android.synthetic.main.activity_display_movie.*
import javax.inject.Inject

class DisplayMovieActivity : AppCompatActivity() {


    @Inject
    lateinit var viewModelFactory : ViewModelProvider.Factory

    val displayMovieViewModel : HomePageViewModel by lazy{
        ViewModelProviders.of(this, viewModelFactory).get(HomePageViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as MacysApplication).macysComponent.inject(this)
        DataBindingUtil.setContentView<ActivityDisplayMovieBinding>(this, R.layout.activity_display_movie)
            .apply { movieViewModel =  displayMovieViewModel; setLifecycleOwner(this@DisplayMovieActivity)}
        val movieReceived = intent.getParcelableExtra<Movie>("movie_object")
        Log.d("TAG", "onCreate DisplayMovieActivity: $movieReceived")
        displayMovieViewModel.setViewModelMovie(movieReceived)
        Glide.with(this).load("http://image.tmdb.org/t/p/w342${movieReceived.poster_path}").into(ivDisplayMovie)
    }
}
