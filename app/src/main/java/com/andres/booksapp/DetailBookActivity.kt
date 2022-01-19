package com.andres.booksapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.andres.booksapp.apiService.RetrofitEngine
import com.andres.booksapp.apiService.apiService
import com.andres.booksapp.databinding.ActivityDetailBookBinding
import com.andres.booksapp.databinding.ItemBookBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailBookActivity : AppCompatActivity() {
    lateinit var  binding: ActivityDetailBookBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBookBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extras = intent.extras
        if (extras != null) {
            val isbn13 = extras.getString("isbn13")
            if (isbn13 != null) {
                 getDetailBook(isbn13)
            }
        }
    }

    private fun getDetailBook (isbn13:String ){
        binding.modalLoading.visibility = View.VISIBLE

        CoroutineScope(Dispatchers.IO).launch {
           val apiService = RetrofitEngine.retrofit()
               .create(apiService::class.java)

           val callApi = apiService.getDetailBook("books/$isbn13")

           val body = callApi.body()

           runOnUiThread {
               if (callApi.isSuccessful){
                  if (body != null){
                      Picasso.get().load(body.image).into(binding.imageView2)
                      binding.textViewTitle.text = body.title
                      binding.textViewPublisher.text = "Publisher: ${body.publisher}"
                      binding.textViewYear.text = "Year: ${body.year}"
                      binding.textViewDesc.text = body.desc
                      binding.textAutors.text = body.authors
                  }
                   binding.modalLoading.visibility = View.GONE
               }else{

               }
           }
       }
    }
}