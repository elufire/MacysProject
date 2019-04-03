package com.example.macysproject.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.LinearLayout
import com.example.macysproject.MacysApplication
import com.example.macysproject.R
import com.example.macysproject.adapters.RecyclerViewAdapter
import com.example.macysproject.databinding.ActivityHomePageBinding
import com.example.macysproject.models.Movie
import com.example.macysproject.viewmodels.HomePageViewModel
import com.example.macysproject.viewmodels.UniversalViewModelFactory
import kotlinx.android.synthetic.main.activity_home_page.*
import java.util.ArrayList
import javax.inject.Inject

class HomePageActivity : AppCompatActivity() {
//    val viewModel : HomePageViewModel by lazy{
//        ViewModelProviders.of(this, UniversalViewModelFactory{HomePageViewModel()}).get(HomePageViewModel::class.java)
//    }

    private var lastVisibleItemPosition: Int = 0

    private var pageNumber: Int = 1

    var moviesList: ArrayList<Movie>? = ArrayList()

    private lateinit var scrollListener: RecyclerView.OnScrollListener

    private lateinit var linearLayoutManager: LinearLayoutManager

    @Inject
    lateinit var viewModelFactory : ViewModelProvider.Factory

    val homeViewModel : HomePageViewModel by lazy{
        ViewModelProviders.of(this, viewModelFactory).get(HomePageViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as MacysApplication).macysComponent.inject(this)
        DataBindingUtil.setContentView<ActivityHomePageBinding>(this, R.layout.activity_home_page)
            .apply { viewModel =  homeViewModel; setLifecycleOwner(this@HomePageActivity)}

        linearLayoutManager = LinearLayoutManager(this)
        rvMovies.layoutManager = linearLayoutManager
        rvMovies.adapter = RecyclerViewAdapter(moviesList)

        homeViewModel.getMoviesList().observe(this, Observer<ArrayList<Movie>>{movies ->
            moviesList = movies
            (rvMovies.adapter as RecyclerViewAdapter).setItems(moviesList)
            (rvMovies.adapter as RecyclerViewAdapter).notifyDataSetChanged()
            //Log.d("TAG", "LASTVISIBLEITEMPOSITION: ${moviesList?.get(0)?.title}")
            //setRecyclerViewScrollListener()
        })

        homeViewModel.getSearchCounter().observe(this, Observer<Int> { searchCounter ->
            Log.d("SearchCounter", "$searchCounter")
            moviesList?.clear()
            pageNumber = 1
            (rvMovies.adapter as RecyclerViewAdapter).setItems(moviesList)
            (rvMovies.adapter as RecyclerViewAdapter).notifyDataSetChanged()
            setRecyclerViewScrollListener()
        })

        Log.d("TAG", "OnCreate")

    }

    private fun setRecyclerViewScrollListener() {

        Log.d("TAG", "setRecyclerViewScrollListener: $pageNumber")
        scrollListener = object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                lastVisibleItemPosition = (recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                val totalItemCount = recyclerView.layoutManager?.itemCount
                Log.d("TAG", "ItemCount: $totalItemCount LastVisibleItem: $lastVisibleItemPosition")
                if (totalItemCount == lastVisibleItemPosition +1) {
                    Log.d("PageTAG", "Load new list $pageNumber")
                    //rvMovies.removeOnScrollListener(scrollListener)
                    pageNumber++
                    homeViewModel.makeApiCall(pageNumber)

                }
            }
        }
        rvMovies.addOnScrollListener(scrollListener)
    }
}
