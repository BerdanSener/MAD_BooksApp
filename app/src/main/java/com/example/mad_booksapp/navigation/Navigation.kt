package com.example.mad_booksapp.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mad_booksapp.screens.HomeScreen
import com.example.mad_booksapp.screens.EditScreen
import com.example.mad_booksapp.screens.FavoriteBooksScreen
import com.example.mad_booksapp.viewModel.BooksViewModel


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation() {
    val navController = rememberNavController() // create a NavController instance

   val viewModel = BooksViewModel()  // create a MoviesViewModel instance to use in HomeScreen and WatchlistScreen


    NavHost(navController = navController, // pass the NavController to NavHost
        startDestination = Screen.HomeScreen.route) {  // pass a start destination
        composable(route = Screen.HomeScreen.route){   // route with name "homescreen" navigates to HomeScreen composable
            HomeScreen(navController = navController, viewModel = viewModel)
        }

        composable(
            route = Screen.DetailScreen.route,
            arguments = listOf(navArgument(name = DETAIL_ARGUMENT_KEY) {type = NavType.StringType})
        ) {/* backStackEntry ->
            DetailScreen(
                //navController = navController,
                //movieId = backStackEntry.arguments?.getString(DETAIL_ARGUMENT_KEY),
                //moviesViewModel = moviesViewModel
            )
        */}

        composable(route = Screen.FavoriteBooks.route){
            FavoriteBooksScreen(viewModel = viewModel, navController = navController) //, moviesViewModel = moviesViewModel
        }
    }
}