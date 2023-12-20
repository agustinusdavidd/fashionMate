package com.example.presentation.screen.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomStart
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.fashion_mate.R
import com.example.presentation.component.ImageCard
import com.example.presentation.component.searchBar
import com.example.presentation.ui.theme.HeaderBlack

@Composable
fun SearchScreen() {

    val mainResultText = "Menampilkan hasil pencarian untuk "
    var queryResultText = "semua"

    val resultText = buildAnnotatedString {
        append(mainResultText)
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
            append(queryResultText)
        }
    }

    Column {

        Box(modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .background(HeaderBlack)
        ){
            Image(
                modifier = Modifier.align(BottomStart),
                painter = painterResource(id = R.drawable.search_header),
                contentDescription = "Screen Image"
            )
            Column (modifier = Modifier
                .height(100.dp)
                .align(BottomStart)
                .padding(horizontal = 8.dp)
            ) {
                Row {
                    searchBar(
                        modifier = Modifier
                            .fillMaxSize(0.9f)
                            .align(CenterVertically)
                            .padding(end = 8.dp)
                            .padding(start = 8.dp),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Filled.Search,
                                contentDescription = "Search"
                            )
                        }
                    )
                    Icon(
                        modifier = Modifier
                            .align(CenterVertically)
                            .size(30.dp),
                        imageVector = Icons.Filled.FilterAlt,
                        contentDescription = "Filter Icon",
                        tint = Color.White
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = resultText, modifier = Modifier.padding(horizontal = 24.dp))

        Spacer(modifier = Modifier.height(8.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            content = {
                items(6) {
                    Box (
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ){
                        ImageCard(
                            modifier = Modifier
                                .fillMaxWidth(0.5f),
                            painter = painterResource(id = R.drawable.dummy_cloth),
                            contentDescription = "Baju Dummy"
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        ImageCard(
                            modifier = Modifier
                                .fillMaxWidth(0.5f),
                            painter = painterResource(id = R.drawable.dummy_cloth),
                            contentDescription = "Baju Dummy"
                        )
                    }
                }
            }
        )
    }
}