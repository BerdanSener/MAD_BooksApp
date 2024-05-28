package com.example.mad_booksapp.widgets

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.mad_booksapp.viewModel.BooksViewModel
import java.time.LocalDateTime

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddBookButton(modifier: Modifier, viewModel: BooksViewModel) {
    var showDialog by remember { mutableStateOf(false) }

    Button(
        modifier = modifier,
        onClick = { showDialog = true }
    ) {
        Text("Add Book")
    }

    if (showDialog) {
        AddBookDialog(
            onDismiss = { showDialog = false },
            onSave = { title, author, isbn, year ->
                viewModel.addBook(isbn = isbn, author = author, title = title, year = year)
                showDialog = false
            },
            viewModel = viewModel
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddBookDialog(onDismiss: () -> Unit, onSave: (String, String, String, Int) -> Unit, viewModel: BooksViewModel) {
    var title by remember { mutableStateOf("") }
    var author by remember { mutableStateOf("") }
    var isbn by remember { mutableStateOf("") }
    var year by remember { mutableStateOf("") }

    var titleError by remember { mutableStateOf<String?>(null) }
    var authorError by remember { mutableStateOf<String?>(null) }
    var isbnError by remember { mutableStateOf<String?>(null) }
    var yearError by remember { mutableStateOf<String?>(null) }

    val currentYear = LocalDateTime.now().year

    val isSaveEnabled = title.isNotBlank() &&
            author.isNotBlank() &&
            isValidISBN13(isbn) &&
            year.toIntOrNull() != null &&
            year.toInt() <= currentYear &&
            year.toInt() > 0

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add Book") },
        text = {
            Column {
                OutlinedTextField(
                    value = title,
                    onValueChange = {
                        title = it
                        titleError = if (title.isBlank()) "Title cannot be empty" else null
                    },
                    label = { Text("Title") },
                    modifier = Modifier.fillMaxWidth().padding(8.dp),
                    isError = titleError != null
                )
                if (titleError != null) {
                    Text(titleError!!, color = Color.Red, modifier = Modifier.padding(start = 16.dp, top = 0.dp, end = 16.dp, bottom = 8.dp))
                }

                OutlinedTextField(
                    value = author,
                    onValueChange = {
                        author = it
                        authorError = if (author.isBlank()) "Author cannot be empty" else null
                    },
                    label = { Text("Author") },
                    modifier = Modifier.fillMaxWidth().padding(8.dp),
                    isError = authorError != null
                )
                if (authorError != null) {
                    Text(authorError!!, color = Color.Red, modifier = Modifier.padding(start = 16.dp, top = 0.dp, end = 16.dp, bottom = 8.dp))
                }

                OutlinedTextField(
                    value = isbn,
                    onValueChange = {
                        isbn = it
                        isbnError = when {
                            !isValidISBN13(isbn) -> "Invalid ISBN"
                            viewModel.bookExists(isbn) -> "Book with this ISBN already exists"
                            else -> null
                        }
                    },
                    label = { Text("ISBN") },
                    modifier = Modifier.fillMaxWidth().padding(8.dp),
                    isError = isbnError != null
                )
                if (isbnError != null) {
                    Text(isbnError!!, color = Color.Red, modifier = Modifier.padding(start = 16.dp, top = 0.dp, end = 16.dp, bottom = 8.dp))
                }

                OutlinedTextField(
                    value = year,
                    onValueChange = {
                        year = it
                        yearError = when {
                            year.toIntOrNull() == null -> "Year must be a number"
                            year.toInt() > currentYear -> "Year cannot be in the future"
                            year.toInt() <= 0 -> "Year must be greater than 0"
                            else -> null
                        }
                    },
                    label = { Text("Year") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth().padding(8.dp),
                    isError = yearError != null
                )
                if (yearError != null) {
                    Text(yearError!!, color = Color.Red, modifier = Modifier.padding(start = 16.dp, top = 0.dp, end = 16.dp, bottom = 8.dp))
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    val yearInt = year.toIntOrNull() ?: 0
                    onSave(title, author, isbn, yearInt)
                },
                enabled = isSaveEnabled
            ) {
                Text("Save")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun EditBookScreen(
    modifier: Modifier,
    viewModel: BooksViewModel,
    title: String,
    author: String,
    year: Int,
    isbn: String,
    onFinish: () -> Unit = {}
) {
    var editedTitle by remember { mutableStateOf(title) }
    var editedAuthor by remember { mutableStateOf(author) }
    var editedYear by remember { mutableStateOf(year.toString()) }
    var editedIsbn by remember { mutableStateOf(isbn) }

    var titleError by remember { mutableStateOf<String?>(null) }
    var authorError by remember { mutableStateOf<String?>(null) }
    var isbnError by remember { mutableStateOf<String?>(null) }
    var yearError by remember { mutableStateOf<String?>(null) }

    val currentYear = LocalDateTime.now().year

    val isSaveEnabled = editedTitle.isNotBlank() &&
            editedAuthor.isNotBlank() &&
            isValidISBN13(editedIsbn) &&
            editedYear.toIntOrNull() != null &&
            editedYear.toInt() <= currentYear &&
            editedYear.toInt() > 0 &&
            yearError == null

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Edit Book")

        OutlinedTextField(
            value = editedIsbn,
            onValueChange = {
                editedIsbn = it
                isbnError = if (!isValidISBN13(editedIsbn)) "Invalid ISBN" else null
            },
            label = { Text("ISBN") },
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            isError = isbnError != null,
            enabled = false
        )
        if (isbnError != null) {
            Text(isbnError!!, color = Color.Red, modifier = Modifier.padding(start = 16.dp, top = 0.dp, end = 16.dp, bottom = 8.dp))
        }

        OutlinedTextField(
            value = editedTitle,
            onValueChange = {
                editedTitle = it
                titleError = if (editedTitle.isBlank()) "Title cannot be empty" else null
            },
            label = { Text("Title") },
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            isError = titleError != null
        )
        if (titleError != null) {
            Text(titleError!!, color = Color.Red, modifier = Modifier.padding(start = 16.dp, top = 0.dp, end = 16.dp, bottom = 8.dp))
        }

        OutlinedTextField(
            value = editedAuthor,
            onValueChange = {
                editedAuthor = it
                authorError = if (editedAuthor.isBlank()) "Author cannot be empty" else null
            },
            label = { Text("Author") },
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            isError = authorError != null
        )
        if (authorError != null) {
            Text(authorError!!, color = Color.Red, modifier = Modifier.padding(start = 16.dp, top = 0.dp, end = 16.dp, bottom = 8.dp))
        }

        OutlinedTextField(
            value = editedYear,
            onValueChange = {
                editedYear = it
                yearError = when {
                    editedYear.toIntOrNull() == null -> "Year must be a number"
                    editedYear.toInt() > currentYear -> "Year cannot be in the future"
                    editedYear.toInt() <= 0 -> "Year must be greater than 0"
                    else -> null
                }
            },
            label = { Text("Year") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            isError = yearError != null
        )
        if (yearError != null) {
            Text(yearError!!, color = Color.Red, modifier = Modifier.padding(start = 16.dp, top = 0.dp, end = 16.dp, bottom = 8.dp))
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = { onFinish() }) {
                Text("Cancel")
            }
            Button(
                onClick = {
                    viewModel.editBook(isbn = isbn, newAuthor = editedAuthor, newTitle = editedTitle, newYear = editedYear.toInt())
                    onFinish()
                          },
                enabled = isSaveEnabled
            ) {
                Text("Done")
            }
        }
    }
}



/*
    ISBN-Checker generated with Chat-GPT
 */
fun isValidISBN13(isbn: String): Boolean {
    // Remove any dashes from the input string
    val cleanedIsbn = isbn.replace("-", "")

    // Ensure the cleaned string has exactly 13 characters
    if (cleanedIsbn.length != 13 || !cleanedIsbn.all { it.isDigit() }) {
        return false
    }

    // Convert the string to a list of integers
    val digits = cleanedIsbn.map { it.toString().toInt() }

    // Calculate the check digit
    val sum = digits.take(12).mapIndexed { index, digit ->
        if (index % 2 == 0) digit else digit * 3
    }.sum()

    val calculatedCheckDigit = (10 - (sum % 10)) % 10

    // Compare the calculated check digit with the 13th digit
    return calculatedCheckDigit == digits[12]
}