@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.presentation.component

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fashion_mate.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun searchBar(
    modifier: Modifier = Modifier,
    keyword: String = "",
    leadingIcon:  (@Composable () -> Unit)? = null
) {

    var keyword by remember {
        mutableStateOf(keyword)
    }

    Column(
        modifier = modifier
    ) {
        LeadingTextField(
            value = keyword,
            placeHolder = stringResource(R.string.search),
            onValueChange = {
                keyword = it
            },
            leadingIcon = leadingIcon,
            errorStatus = true
        )
    }
}

@Preview(showBackground = true)
@Composable
fun searchBarPreview() {
    searchBar(
        modifier = Modifier.fillMaxWidth(),
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "Search"
            )
        }
    )
}