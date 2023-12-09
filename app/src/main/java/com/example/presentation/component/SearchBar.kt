@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.presentation.component

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun searchBar(
    modifier: Modifier = Modifier,
    query: String = "",
    active: Boolean = false,
    onQueryChange: (String) -> Unit,
    leadingIcon:  (@Composable () -> Unit)? = null,
    onSearch: (String) -> Unit
) {

}