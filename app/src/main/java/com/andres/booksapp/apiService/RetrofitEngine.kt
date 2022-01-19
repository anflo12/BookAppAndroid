package com.andres.booksapp.apiService


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitEngine {


    companion object{
        fun retrofit():Retrofit{

            return Retrofit.Builder()
                .baseUrl("https://api.itbook.store/1.0/").
                addConverterFactory(GsonConverterFactory.create()).
                build()

        }
    }
}