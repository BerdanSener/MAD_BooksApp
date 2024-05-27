package com.example.mad_booksapp.screens

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.mad_booksapp.widgets.SimpleBottomAppBar
import com.example.mad_booksapp.widgets.SimpleTopAppBar

@Composable
fun FavoriteBooksScreen(
    navController: NavController,
    //moviesViewModel: MoviesViewModel
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

        Text("Favorite Books" +innerPadding)
    }
}