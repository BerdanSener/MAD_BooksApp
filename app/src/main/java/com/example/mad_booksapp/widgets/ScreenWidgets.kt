package com.example.mad_booksapp.widgets

import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.mad_booksapp.viewModel.BooksViewModel

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
        AddBookDialog(onDismiss = { showDialog = false }, onSave = { title, author, isbn, year ->
            viewModel.addBook(isbn, author, title, year)
            showDialog = false
        })
    }
}

@Composable
fun AddBookDialog(onDismiss: () -> Unit, onSave: (String, String, String, Int) -> Unit) {
    var title by remember { mutableStateOf("") }
    var author by remember { mutableStateOf("") }
    var isbn by remember { mutableStateOf("") }
    var year by remember { mutableStateOf("") }

    var titleError by remember { mutableStateOf<String?>(null) }
    var authorError by remember { mutableStateOf<String?>(null) }
    var isbnError by remember { mutableStateOf<String?>(null) }
    var yearError by remember { mutableStateOf<String?>(null) }

    val isSaveEnabled = title.isNotBlank() && author.isNotBlank() && isbn.isNotBlank() && year.toIntOrNull() != null

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
                        isbnError = if (!isValidISBN13(isbn)) "Invalid ISBN" else null
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
                        yearError = if (year.toIntOrNull() == null) "Year must be a number" else null
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