package com.example.mad_booksapp.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.mad_booksapp.viewModel.BooksViewModel
import com.example.mad_booksapp.widgets.EditBookScreen
import com.example.mad_booksapp.widgets.SimpleTopAppBar

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun EditScreen(
    bookISBN: String?,
    navController: NavController,
    viewModel: BooksViewModel
) {

    bookISBN?.let {
        val book = viewModel.books.filter { book -> book.isbn == bookISBN }[0]


        Scaffold (
            topBar = {
                SimpleTopAppBar("Edit Book") {
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
                Text(
                    modifier = Modifier.padding(innerPadding),
                    text = book.title
                )
                EditBookScreen(
                    modifier = Modifier.padding(innerPadding),
                    viewModel = viewModel,
                    title = book.title,
                    author = book.author,
                    year = book.year,
                    isbn = book.isbn,
                    onFinish = { navController.popBackStack() }
                )
            }
        }
    }
}