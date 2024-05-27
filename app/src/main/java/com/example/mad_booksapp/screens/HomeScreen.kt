package com.example.mad_booksapp.screens

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.mad_booksapp.widgets.SimpleBottomAppBar
import com.example.mad_booksapp.widgets.SimpleTopAppBar

@Composable
fun HomeScreen(
    navController: NavController
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
        Text("HomeScreen" + innerPadding)
        /**val moviesState by viewModel.movies.collectAsState()

        MovieList(
            modifier = Modifier.padding(innerPadding),
            movies = moviesState,
            navController = navController,
            viewModel = viewModel
        )*/
    }
}
