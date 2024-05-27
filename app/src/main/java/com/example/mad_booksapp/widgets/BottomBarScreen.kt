package com.example.mad_booksapp.widgets

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.mad_booksapp.navigation.Screen

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home: BottomBarScreen(
        route = Screen.HomeScreen.route,
        title = "Home",
        icon = Icons.Filled.Home
    )

    object Watchlist: BottomBarScreen(
        route = Screen.FavoriteBooks.route,
        title = "Favorites",
        icon = Icons.Filled.Star
    )
}