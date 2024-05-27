package com.example.mad_booksapp.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.mad_booksapp.models.Book
import com.example.mad_booksapp.models.getBooks
import com.example.mad_booksapp.viewModel.BooksViewModel
import com.example.mad_booksapp.widgets.SimpleBottomAppBar
import com.example.mad_booksapp.widgets.SimpleTopAppBar

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: BooksViewModel
) {

    Scaffold (
        topBar = {
            SimpleTopAppBar(title = "Edit Books")
        },
        bottomBar = {
            SimpleBottomAppBar(
                navController = navController
            )
        }
    ){ innerPadding ->
       Text("text = viewModel.books" + innerPadding)

        var bookList = getBooks()
        System.out.println(bookList.get(0))
    }
}
