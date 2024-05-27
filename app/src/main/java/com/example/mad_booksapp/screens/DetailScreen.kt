package com.example.mad_booksapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.mad_booksapp.widgets.SimpleTopAppBar

@Composable
fun DetailScreen(
    bookId: String?,
    navController: NavController,
    //moviesViewModel: MoviesViewModel
) {

    bookId?.let {
        //val movie = moviesViewModel.movies.filter { movie -> movie.id == movieId }[0]


        Scaffold (
            topBar = {
                SimpleTopAppBar(title = "movie.title") {
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
                Text("inner Padding" + innerPadding)
                /**MovieRow(
                    modifier = androidx.compose.ui.Modifier.padding(innerPadding),
                    movie = movie,
                    onFavoriteClick = { id -> moviesViewModel.toggleFavoriteMovie(id) }
                )

                Divider(modifier = Modifier.padding(4.dp))

                Column {
                    Text("Movie Trailer")
                    VideoPlayer(trailerURL = movie.trailer)
                }

                Divider(modifier = Modifier.padding(4.dp))

                HorizontalScrollableImageView(movie = movie)*/
            }
        }
    }
}