package com.example.macysproject.api

import com.example.macysproject.MacysApplication
import com.example.macysproject.models.SearchResult
import io.reactivex.Observable
import java.util.*
import javax.inject.Inject

//48e2fedf63175a2d6e271fcfbb13ca65

class RetrofitHelper @Inject constructor(private val remoteService: RemoteService){


    fun search(pageNumber: Int, query: String?): Observable<SearchResult>{
        return remoteService.getSearchResults(pageNumber, query)
    }


}