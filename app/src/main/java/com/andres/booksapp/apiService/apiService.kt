package com.andres.booksapp.apiService

import com.andres.booksapp.models.BookList
import com.andres.booksapp.models.DetailBook
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface apiService {

    @GET
    suspend fun  getBooks(@Url url:String): Response<BookList>
    @GET
    suspend fun  getDetailBook(@Url url: String) : Response<DetailBook>
}