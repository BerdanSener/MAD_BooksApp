package com.example.mad_booksapp.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
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
                    .size(30.dp)
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
                    .size(30.dp)
                    .padding(end = 1.dp)
            )
            Text(text = "Jahr absteigend")
        }
    }
}