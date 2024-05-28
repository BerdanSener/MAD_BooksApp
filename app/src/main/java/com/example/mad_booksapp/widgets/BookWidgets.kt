package com.example.mad_booksapp.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mad_booksapp.R
import com.example.mad_booksapp.models.Book
import com.example.mad_booksapp.models.getBooks
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
            /*BookRow(book = book,
                onItemClick = { bookISBN ->
                    navController.navigate(route = Screen.DetailScreen.withId(bookISBN))
                },
                onFavoriteClick = {bookISBN ->
                    viewModel.toggleFavoriteBook(bookISBN)
                }
            )*/
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
                }
            )
        }
    }
}

@Composable
fun BookRow(
    modifier: Modifier = Modifier,
    book: Book,
    onItemClick: (String) -> Unit = {},
    onFavoriteClick: (String) -> Unit = {}
){
    Card(modifier = modifier
        .fillMaxWidth()
        .padding(5.dp)
        .clickable {
            onItemClick(book.isbn)
        },
        shape = ShapeDefaults.Large,
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Column {

            //BookCardHeader(isFavorite = book.isFavorite, onFavoriteClick = { onFavoriteClick(book.isbn) })
            BookItem(book = book)
            BookDetails(modifier = modifier.padding(12.dp), book = book)

        }
    }
}

@Composable
fun BookCardHeader(
    isFavorite: Boolean = false,
    onFavoriteClick: () -> Unit = {}
) {
    Box(
        contentAlignment = Alignment.Center
    ) {
        FavoriteIcon(isFavorite = isFavorite, onFavoriteClick)
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
    onItemClick: (String) -> Unit = {},
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
        Text(text = book.title)
        /*Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_book),
            contentDescription = "Book Icon",
            modifier = Modifier
                .size(24.dp)
                //.clickable { onItemClick(book.isbn) }
        )*/
        Spacer(modifier = Modifier.width(16.dp))
        EditBookItem(modifier = modifier, onClick = onEdit)
        Spacer(modifier = Modifier.width(16.dp))
        DeleteBookItem(modifier = modifier, onClick = onDelete)
        Spacer(modifier = Modifier.width(16.dp))
        BookReadItem(modifier = modifier, onClick = onBookRead, bookRead = book.bookRead)
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
    modifier: Modifier,
    onClick: (String) -> Unit = {}
){
    Icon(
        contentDescription = "EditIcon",
        modifier = Modifier
        .clickable {
            println("Edit")
        },
        imageVector = Icons.Filled.Edit
    )
}

@Composable
fun DeleteBookItem(
    modifier: Modifier,
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
    modifier: Modifier,
    onClick: () -> Unit,
    bookRead: Boolean
){
    Icon(
        contentDescription = "CheckIcon",
        modifier = Modifier
            .clickable {
                onClick()
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