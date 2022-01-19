package com.andres.booksapp.models

data class BookList(
    val books: List<BookItem>,
    val page: String,
    val total: String
)

data class BookItem(
    val image: String,
    val isbn13: String,
    val price: String,
    val subtitle: String,
    val title: String,
    val url: String
)