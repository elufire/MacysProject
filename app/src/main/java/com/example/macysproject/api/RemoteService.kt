package com.example.macysproject.api

import com.example.macysproject.models.SearchResult
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

interface RemoteService{

    @GET("/3/search/movie?api_key=48e2fedf63175a2d6e271fcfbb13ca65&language=en-US&page=1&include_adult=false")
        fun getSearchResults(@Query("page") pageNumber: Int,
                             @Query("query") search: String?):
            Observable<SearchResult>

}

