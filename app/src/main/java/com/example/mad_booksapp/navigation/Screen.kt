package com.example.mad_booksapp.navigation

const val DETAIL_ARGUMENT_KEY = "book_id"
sealed class Screen(val route: String) {
    object HomeScreen : Screen("home")
   object DetailScreen : Screen("detail/{$DETAIL_ARGUMENT_KEY}") {
        fun withId(id: String): String {
            return this.route.replace(oldValue = "{$DETAIL_ARGUMENT_KEY}", newValue = id)
        }
    }
    object FavoriteBooks : Screen("favorite")
}