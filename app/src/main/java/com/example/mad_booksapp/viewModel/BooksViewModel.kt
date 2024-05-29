package com.example.mad_booksapp.viewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.mad_booksapp.models.Book

class BooksViewModel: ViewModel() {
    private val _books = mutableStateListOf<Book>()
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

    fun editBook(isbn: String, newAuthor: String, newTitle: String, newYear: Int) {
        _books.find { it.isbn == isbn }?.let { book ->
            book.author = newAuthor
            book.title = newTitle
            book.year = newYear
        }
    }

    fun addBook(isbn: String, author: String, title: String, year: Int) {
        val newBook = Book(isbn = isbn, author = author, title =  title, year =  year)
        _books.add(newBook)
    }

    val bookExists: (String) -> Boolean = { isbn ->
        _books.any { it.isbn == isbn }
    }

    fun sortByYearAscending() {
        _books.sortBy { it.year }
    }

    fun sortByYearDescending() {
        _books.sortByDescending { it.year }
    }

    fun filterBooks(query: String): List<Book> {
        val lowerCaseQuery = query.lowercase()
        return favoriteBooks.filter { book ->
            book.author.lowercase().contains(lowerCaseQuery) || book.title.lowercase().contains(lowerCaseQuery)
        }
    }
}