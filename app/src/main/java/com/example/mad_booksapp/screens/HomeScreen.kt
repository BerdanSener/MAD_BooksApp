package com.example.mad_booksapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.mad_booksapp.models.Book
import com.example.mad_booksapp.models.getBooks
import com.example.mad_booksapp.viewModel.BooksViewModel
import com.example.mad_booksapp.widgets.BookList
import com.example.mad_booksapp.widgets.SimpleBottomAppBar
import com.example.mad_booksapp.widgets.SimpleTopAppBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mad_booksapp.widgets.AddBookButton

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: BooksViewModel
) {

    Scaffold (
        topBar = {
            SimpleTopAppBar(title = "Library")
        },
        bottomBar = {
            SimpleBottomAppBar(
                navController = navController
            )
        }
    ) { innerPadding ->
        Column {
            AddBookButton(modifier = Modifier.padding(innerPadding))
            BookList(
                modifier = Modifier.padding(innerPadding),
                navController = navController,
                viewModel = viewModel
            )
        }
    }
}
