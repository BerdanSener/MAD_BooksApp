package com.example.mad_booksapp.viewModel

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.mad_booksapp.models.Book
import com.example.mad_booksapp.models.getBooks

class BooksViewModel: ViewModel() {
    private val _books = getBooks().toMutableStateList()
    val books: List<Book>
        get() = _books

    val favoriteBooks: List<Book>
        get() = _books.filter { book -> book.isFavorite }

    fun toggleFavoriteBook(isbn: String) = _books.find { it.isbn == isbn }?.let { book ->
        book.isFavorite = !book.isFavorite
    }

    fun toggleBookRead(isbn: String) = _books.find { it.isbn == isbn }?.let { book ->
        book.bookRead = !book.bookRead
    }

    fun deleteBook(isbn: String) {
        _books.removeIf { it.isbn == isbn }
    }

    fun editBook(isbn: String, newAuthor: String, newTitle: String) {
        _books.find { it.isbn == isbn }?.let { book ->
            book.author = newAuthor
            book.title = newTitle
        }
    }

    fun addBook(isbn: String, author: String, title: String, year: Int) {
        val newBook = Book(isbn, author, title, year)
        _books.add(newBook)
    }
}