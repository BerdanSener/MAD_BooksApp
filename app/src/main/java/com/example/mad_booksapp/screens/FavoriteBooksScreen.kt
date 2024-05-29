package com.example.mad_booksapp.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.mad_booksapp.viewModel.BooksViewModel
import com.example.mad_booksapp.widgets.AscButton
import com.example.mad_booksapp.widgets.BookList
import com.example.mad_booksapp.widgets.DescButton
import com.example.mad_booksapp.widgets.SearchRow
import com.example.mad_booksapp.widgets.SimpleBottomAppBar
import com.example.mad_booksapp.widgets.SimpleTopAppBar

@SuppressLint("UnrememberedMutableState")
@Composable
fun FavoriteBooksScreen(
    navController: NavController,
    viewModel: BooksViewModel
){

    val _searching = mutableStateOf(false)
    var searching by _searching
    var searchQuery = ""

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
            SearchRow(
                onSearch = { query ->
                    searchQuery = query
                },
                searching = { isSearching ->
                    searching = isSearching
                }
            )
            if(searching) {
                BookList(
                    modifier = Modifier.padding(innerPadding),
                    navController = navController,
                    viewModel = viewModel,
                    books = viewModel.filterBooks(query = searchQuery)
                )
            }else{
                BookList(
                    modifier = Modifier.padding(innerPadding),
                    navController = navController,
                    viewModel = viewModel,
                    books = viewModel.favoriteBooks
                )
            }
        }
    }
}