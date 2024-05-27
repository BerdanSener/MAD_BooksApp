package com.example.mad_booksapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mad_booksapp.screens.HomeScreen
import com.example.mad_booksapp.screens.DetailScreen
import com.example.mad_booksapp.screens.FavoriteBooksScreen


@Composable
fun Navigation() {
    val navController = rememberNavController() // create a NavController instance

   // val moviesViewModel: MoviesViewModel = viewModel()  // create a MoviesViewModel instance to use in HomeScreen and WatchlistScreen


    NavHost(navController = navController, // pass the NavController to NavHost
        startDestination = Screen.HomeScreen.route) {  // pass a start destination
        composable(route = Screen.HomeScreen.route){   // route with name "homescreen" navigates to HomeScreen composable
            HomeScreen(navController = navController)
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
            FavoriteBooksScreen(navController = navController) //, moviesViewModel = moviesViewModel
        }
    }
}