package com.andres.booksapp.adapter

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andres.booksapp.DetailBookActivity
import com.andres.booksapp.MainActivity
import com.andres.booksapp.R
import com.andres.booksapp.adapter.Adapter.*
import com.andres.booksapp.databinding.ItemBookBinding
import com.andres.booksapp.models.BookItem

import com.andres.booksapp.models.BookList
import com.squareup.picasso.Picasso

class Adapter(var list: List<BookItem>): RecyclerView.Adapter<BookHolder>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return  BookHolder(layoutInflater.inflate(R.layout.item_book, parent,false))
    }

    override fun onBindViewHolder(holder: BookHolder, position: Int) {
        val item = list[position]

        holder.render(item)
        holder.itemView.setOnClickListener {
            var bundle = Bundle()

            var intent = Intent(holder.itemView.context, DetailBookActivity::class.java)

            intent.putExtra("isbn13",item.isbn13)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = list.size

    class BookHolder(view: View):RecyclerView.ViewHolder(view) {
        private val binding = ItemBookBinding.bind(view)

       fun render(item: BookItem) {
            Picasso.get().load(item.image).into(binding.imageView)
            binding.textTitle.text = item.title
           binding.textSubtitle.text = item.subtitle
           binding.textPrice.text = item.price

       }
    }
}


