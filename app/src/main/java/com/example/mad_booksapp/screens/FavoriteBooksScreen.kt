package com.example.mad_booksapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.mad_booksapp.viewModel.BooksViewModel
import com.example.mad_booksapp.widgets.AscButton
import com.example.mad_booksapp.widgets.BookList
import com.example.mad_booksapp.widgets.DescButton
import com.example.mad_booksapp.widgets.SimpleBottomAppBar
import com.example.mad_booksapp.widgets.SimpleTopAppBar

@Composable
fun FavoriteBooksScreen(
    navController: NavController,
    viewModel: BooksViewModel
){
    Scaffold (
        topBar = {
            SimpleTopAppBar(title = "Your Favorite Books")
        },
        bottomBar = {
            SimpleBottomAppBar(
                navController = navController
            )
        }
    ){ innerPadding ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ){
            Row{
                AscButton(
                    onSort = {
                        viewModel.sortByYearAscending()
                    })
                DescButton(
                    onSort = {
                        viewModel.sortByYearDescending()
                    })
            }
            BookList(
                modifier = Modifier.padding(innerPadding),
                navController = navController,
                viewModel = viewModel,
                books = viewModel.favoriteBooks
            )
        }
    }
}