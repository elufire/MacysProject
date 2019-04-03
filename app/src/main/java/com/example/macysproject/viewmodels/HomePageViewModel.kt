package com.example.macysproject.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Log
import com.example.macysproject.MacysApplication
import com.example.macysproject.api.MainRepository
import com.example.macysproject.models.Movie
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.ArrayList
import javax.inject.Inject

class HomePageViewModel : AndroidViewModel(Application()){
    val name = MutableLiveData<String>()
    val newSearch = MutableLiveData<Int>()
    var searchCounter = 0
    var movies = ArrayList<Movie>()
    val moviesLiveData = MutableLiveData<ArrayList<Movie>>()
    var query : String ?= "fun"
    var finalQuery : String ?= "fun"
    var disposable: Disposable? = null
    var movie : Movie ?= null

    @Inject
    lateinit var mainRepository: MainRepository

    init {
        MacysApplication().macysComponent.inject(this)
        name.value = "Ricky"
        Log.d("TAG", "In init ViewModel" + name.value)
    }

    fun onTextChanged(charSequence: CharSequence){
        query = charSequence.toString()
        Log.d("TAG", "onTextChanged: $query")
    }

    fun handleClick(){
        finalQuery = query
        searchCounter++
        newSearch.value = searchCounter
        makeApiCall(1)

    }

    fun setViewModelMovie(movie: Movie){
        this.movie = movie
    }

    fun getSearchCounter(): MutableLiveData<Int>{
        return newSearch
    }

    fun getMoviesList(): MutableLiveData<ArrayList<Movie>>{
        return moviesLiveData
    }

    fun makeApiCall(pageNumber: Int){
        disposable =
                mainRepository.requestMoviesApi(pageNumber, finalQuery)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                    { result -> movies.addAll(ArrayList(result.results))
                        moviesLiveData.value = movies
                        Log.d("TAG", result.results.get(0).title)},
                        { error -> Log.d("TAG", "ERROR IN API CALL ${error.message}")}
                    )
    }
}