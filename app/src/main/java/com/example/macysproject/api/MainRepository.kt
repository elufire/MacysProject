package com.example.macysproject.api

import com.example.macysproject.Constants
import com.example.macysproject.models.SearchResult
import io.reactivex.Observable
import javax.inject.Inject

class MainRepository @Inject constructor(private val retrofitHelper: RetrofitHelper) {


    fun requestMoviesApi(pageNumber: Int, passedString: String?): Observable<SearchResult>{
        return retrofitHelper.search(pageNumber, passedString)
    }
}