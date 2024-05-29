package com.example.mad_booksapp.widgets

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mad_booksapp.models.Book
import com.example.mad_booksapp.navigation.Screen
import com.example.mad_booksapp.viewModel.BooksViewModel


@Composable
fun BookList(
    modifier: Modifier,
    navController: NavController,
    viewModel: BooksViewModel,
    books: List<Book>
){
    LazyColumn(modifier = modifier) {
        items(books) { book ->
            BookItem(
                book = book,
                modifier = modifier,
                onDelete = {
                    viewModel.deleteBook(book.isbn)
                },
                onBookRead = {
                    viewModel.toggleBookRead(book.isbn)
                },
                onFavoriteClick = {
                    viewModel.toggleFavoriteBook(book.isbn)
                },
                onEdit = {
                    bookISBN -> navController.navigate(route = Screen.EditScreen.withId(bookISBN))
                }
            )
        }
    }
}


@Composable
fun BookDetails(modifier: Modifier, book: Book) {
    var showDetails by remember {
        mutableStateOf(false)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(modifier = Modifier
            .clickable {
                showDetails = !showDetails
            },
            imageVector =
            if (showDetails) Icons.Filled.KeyboardArrowUp
            else Icons.Default.KeyboardArrowDown, contentDescription = "show more")
    }


    AnimatedVisibility(
        visible = showDetails,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Column (modifier = modifier) {
            Text(text = "Titel: ${book.title}", style = MaterialTheme.typography.bodySmall)
            Text(text = "Autor: ${book.author}", style = MaterialTheme.typography.bodySmall)
            Text(text = "Release Year: ${book.year}", style = MaterialTheme.typography.bodySmall)
            Text(text = "ISBN: ${book.isbn}", style = MaterialTheme.typography.bodySmall)

            Divider(modifier = Modifier.padding(3.dp))
        }
    }
}

@Composable
fun BookItem(
    modifier: Modifier = Modifier,
    book: Book,
    onFavoriteClick: () -> Unit = {},
    onEdit: (String) -> Unit = {},
    onDelete: () -> Unit = {},
    onBookRead: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(text = book.title + " by " + book.author)
        Spacer(modifier = Modifier.width(16.dp))
        EditBookItem(onClick = onEdit, isbn = book.isbn)
        Spacer(modifier = Modifier.width(16.dp))
        DeleteBookItem(onClick = onDelete)
        Spacer(modifier = Modifier.width(16.dp))
        BookReadItem(onClick = onBookRead, bookRead = book.bookRead)
        Spacer(modifier = Modifier.width(16.dp))
        FavoriteIcon(onFavoriteClick = onFavoriteClick, isFavorite = book.isFavorite)
    }
    Box(
        contentAlignment = Alignment.Center
    ){
        BookDetails(modifier = modifier.padding(12.dp), book = book)
    }
}


@Composable
fun EditBookItem(
    onClick: (String) -> Unit = {},
    isbn: String
){
    Icon(
        contentDescription = "EditIcon",
        modifier = Modifier
        .clickable {
            onClick(isbn)
        },
        imageVector = Icons.Filled.Edit
    )
}

@Composable
fun DeleteBookItem(
    onClick: () -> Unit
){
    Icon(
        contentDescription = "DeleteIcon",
        modifier = Modifier
            .clickable {
                onClick()
            },
        imageVector = Icons.Filled.Delete
    )
}

@Composable
fun BookReadItem(
    onClick: () -> Unit,
    bookRead: Boolean
){
    val context = LocalContext.current

    Icon(
        contentDescription = "CheckIcon",
        modifier = Modifier
            .clickable {
                onClick()
                if(bookRead) Toast.makeText(context, "Als nicht gelesen markiert", Toast.LENGTH_SHORT).show() else Toast.makeText(context, "Als gelesen markiert", Toast.LENGTH_SHORT).show()
            },
        tint = if (bookRead) Color.Green else Color.Black,
        imageVector =
        if (bookRead) {
            Icons.Filled.Check
        } else {
            Icons.Default.CheckCircle
        }
    )
}

@Composable
fun FavoriteIcon(
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit = {}
) {
        Icon(
            modifier = Modifier.clickable {
                onFavoriteClick() },
            tint = Color.Red,
            imageVector =
            if (isFavorite) {
                Icons.Filled.Favorite
            } else {
                Icons.Default.FavoriteBorder
            },

            contentDescription = "Add to favorites"
        )
}