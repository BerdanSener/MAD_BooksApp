package com.example.mad_booksapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.mad_booksapp.viewModel.BooksViewModel
import com.example.mad_booksapp.widgets.BookRow
import com.example.mad_booksapp.widgets.SimpleTopAppBar
import androidx.compose.ui.Modifier

@Composable
fun EditScreen(
    bookISBN: String?,
    navController: NavController,
    booksViewModel: BooksViewModel
) {

    bookISBN?.let {
        val book = booksViewModel.books.filter { book -> book.isbn == bookISBN }[0]


        Scaffold (
            topBar = {
                SimpleTopAppBar(title = "movie.title") {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Go back"
                        )
                    }
                }
            }
        ){ innerPadding ->
            Column {
                BookRow(
                    modifier = Modifier.padding(innerPadding),
                    book = book,
                    onFavoriteClick = { isbn -> booksViewModel.toggleFavoriteBook(isbn = isbn) }
                )
            }
        }
    }
}