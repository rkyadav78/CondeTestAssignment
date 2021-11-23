package com.e.condetestassignment.network

import com.e.condetestassignment.model.Todo
import retrofit2.Response
import retrofit2.http.GET

interface Api {
    @GET("/todos")
    suspend fun getTodo(): Response<List<Todo>>
}