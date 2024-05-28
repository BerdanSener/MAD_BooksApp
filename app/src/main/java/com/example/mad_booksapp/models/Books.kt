package com.example.mad_booksapp.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

data class Book(
    var isbn: String,
    var title: String,
    var author: String,
    var year: Int,
    var initialIsFavorite: Boolean = false,
    var initialIsNotRead: Boolean = false
) {
    var isFavorite by mutableStateOf(initialIsFavorite)
    var bookRead by mutableStateOf(initialIsNotRead)
}

fun getBooks(): List<Book> {
    return listOf(
        Book(isbn = "isbn1",
            title = "Buch 1",
            author = "Autor 1",
            year = 2001,
        ),
        Book(isbn = "isbn2",
            title = "Buch 2",
            author = "Autor 2",
            year = 2002,
        ),
        Book(isbn = "isbn3",
            title = "Buch 3",
            author = "Autor 3",
            year = 2003,
        )
        )
}