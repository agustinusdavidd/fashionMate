package com.example.presentation.screen.search

import android.util.Log
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomStart
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.fashion_mate.R
import com.example.presentation.component.ImageCard
import com.example.presentation.component.ListPakaian
import com.example.presentation.component.searchBar
import com.example.presentation.data.Data.DataEvent
import com.example.presentation.data.Data.DataState
import com.example.presentation.data.Data.DataViewModel
import com.example.presentation.data.Profile.ProfileViewModel
import com.example.presentation.ui.theme.HeaderBlack

@Composable
fun SearchScreen() {

    var query by remember {
        mutableStateOf("")
    }

    val mainResultText = "Menampilkan hasil pencarian untuk "
    var queryResultText by remember {
        mutableStateOf("semua")
    }

    val resultText = buildAnnotatedString {
        append(mainResultText)
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
            append(queryResultText)
        }
    }

    var viewModel = DataViewModel()

    Column {

        Box(modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .background(HeaderBlack)
        ){

            if(query == ""){
                viewModel.onEvent(DataEvent.onFirstOpenSearch())
            } else {
                viewModel.onEvent(DataEvent.onSearch(query))
            }

            Log.d("Data View Model 0", "Start")

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
                        },
                        onValueChange = {
                            query = it
                            queryResultText = query
                        },
                        placeholder = stringResource(id = R.string.search),
                        keyword = query
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

        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
        ){

            if(query == ""){
                queryResultText == "semua"
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = resultText, )

            Spacer(modifier = Modifier.height(16.dp))

            ShowData(viewModel = viewModel)

            Spacer(modifier = Modifier.height(8.dp))

        }
    }
}

@Composable
fun ShowData(viewModel: DataViewModel) {
    val profileViewModel = ProfileViewModel()

    when (val result = viewModel.response.value) {
        is DataEvent.Loading -> {
            viewModel.progress.value = true
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                if (viewModel.progress.value) {
                    CircularProgressIndicator()
                }
            }
        }
        is DataEvent.Success -> {
            ListPakaian(pakaian = result.data, viewModel = profileViewModel)
            viewModel.progress.value = false
        }

        is DataEvent.Failure -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = result.msg)
                viewModel.progress.value = false
            }
        }

        else -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Please Refresh This Page")
                viewModel.progress.value = false
            }
        }
    }
}