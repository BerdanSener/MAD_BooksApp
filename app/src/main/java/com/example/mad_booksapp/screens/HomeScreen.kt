package com.example.mad_booksapp.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.mad_booksapp.viewModel.BooksViewModel
import com.example.mad_booksapp.widgets.AddBookButton
import com.example.mad_booksapp.widgets.BookList
import com.example.mad_booksapp.widgets.SimpleBottomAppBar
import com.example.mad_booksapp.widgets.SimpleTopAppBar

@RequiresApi(Build.VERSION_CODES.O)
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
            AddBookButton(modifier = Modifier.padding(innerPadding), viewModel = viewModel)
            if(viewModel.books.isNotEmpty()){
                BookList(
                    modifier = Modifier.padding(innerPadding),
                    navController = navController,
                    viewModel = viewModel,
                    books = viewModel.books
                )
            }else{
                Text(modifier = Modifier.padding(innerPadding), text = "Es wurden noch keine Bücher angelegt.")
            }
        }
    }
}
