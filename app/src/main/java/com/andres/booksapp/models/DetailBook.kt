package com.andres.booksapp.models

data class DetailBook (
    val error: String,
    val title: String,
    val subtitle: String,
    val authors: String,
    val publisher: String,
    val isbn10: String,
    val isbn13: String,
    val pages: String,
    val year: String,
    val rating: String,
    val desc: String,
    val price: String,
    val image: String,
    val url: String,
    val pdf: PDF
)

data class PDF (
    val chapter2: String,
    val chapter5: String
)