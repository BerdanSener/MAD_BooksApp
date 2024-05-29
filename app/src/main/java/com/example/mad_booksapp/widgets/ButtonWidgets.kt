package com.example.mad_booksapp.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.mad_booksapp.R

@Composable
fun AscButton(
    onSort: () -> Unit = {}
){
    Button(
        onClick = { onSort() },
        modifier = Modifier
            .wrapContentSize(),
        elevation = null
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(1.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.aufsteigend),
                contentDescription = "Button Image",
                modifier = Modifier
                    .size(20.dp)
                    .padding(end = 1.dp)
            )
            Text(text = "Jahr aufsteigend")
        }
    }
}

@Composable
fun DescButton(
    onSort: () -> Unit = {}
){
    Button(
        onClick = { onSort() },
        modifier = Modifier
            .wrapContentSize(),
        elevation = null
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(1.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.absteigend),
                contentDescription = "Button Image",
                modifier = Modifier
                    .size(20.dp)
                    .padding(end = 1.dp)
            )
            Text(text = "Jahr absteigend")
        }
    }
}

@Composable
fun SearchRow(
    onSearch: (String) -> Unit = {},
    searching: (Boolean) -> Unit = {}
){
    var query by remember { mutableStateOf(TextFieldValue("")) }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(1.dp)
    ) {
        SearchBar(query = query) { newValue ->
            query = newValue
        }
        Icon(
            modifier = Modifier
                .clickable {
                    onSearch(query.text)
                    searching(true)
                },
            imageVector = Icons.Filled.Search,
            contentDescription = "Search Button"
        )
        Icon(
            modifier = Modifier
                .clickable {
                    query = TextFieldValue("")
                    searching(false)
                },
            imageVector = Icons.Filled.Clear,
            contentDescription = "Clear Button"
        )
    }
}

@Composable
fun SearchBar(query: TextFieldValue, onQueryChange: (TextFieldValue) -> Unit) {
    BasicTextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = Modifier
            .width(300.dp)
            .padding(8.dp)
            .background(Color.LightGray, shape = MaterialTheme.shapes.small)
            .padding(16.dp),
        textStyle = TextStyle(color = Color.Black)
    )
}