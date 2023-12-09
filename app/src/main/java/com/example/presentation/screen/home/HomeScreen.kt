@file:OptIn(ExperimentalMaterial3Api::class)

package home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable fun HomeScreen(
    navHostController: NavHostController
) {

    var text by remember {
        mutableStateOf("")
    }

    var isActive by remember {
        mutableStateOf(false)
    }

    SearchBar (
        modifier = modifier,
        query = text,
        active = isActive,
        onQueryChange = {
            text = it
        },
        leadingIcon = {
            leadingIcon?.invoke()
        },
        onSearch = onSearch
    ){

    }

    Scaffold {

    }
}