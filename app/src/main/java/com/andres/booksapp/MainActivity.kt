package com.andres.booksapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.andres.booksapp.adapter.Adapter
import com.andres.booksapp.apiService.RetrofitEngine
import com.andres.booksapp.apiService.apiService
import com.andres.booksapp.databinding.ActivityMainBinding
import com.andres.booksapp.models.BookItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {
    private var books = mutableListOf<BookItem>()
    private lateinit var adapter:Adapter
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
        getListBook()
        binding.searchView.setOnQueryTextListener(this)

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getListBook(query: String = "mongo") {
        CoroutineScope(Dispatchers.IO).launch {
            val apiService = RetrofitEngine.retrofit()
                .create(apiService::class.java)

            val callApi = apiService.getBooks("search/$query")

            val body = callApi.body()
            runOnUiThread {
                if (callApi.isSuccessful){
                   var results = (body?.books ?: emptyList()) as MutableList<BookItem>
                    books.clear()
                    books.addAll(results)
                    adapter.notifyDataSetChanged()
                }else{
                    Log.e("Error en peticion", "Error")
                }
            }

        }
    }

    private fun initRecyclerView() {
         adapter = Adapter(books)
        binding.recycleview.layoutManager = LinearLayoutManager(this)
        binding.recycleview.adapter = adapter
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if(!query.isNullOrEmpty()){
            getListBook(query.toLowerCase())
        }

        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {

        return true
    }
}