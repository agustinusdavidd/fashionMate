@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.presentation.component

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.fashion_mate.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun searchBar(
    modifier: Modifier = Modifier,
    query: String = "",
    leadingIcon:  (@Composable () -> Unit)? = null
) {
    Column(
        modifier = modifier
    ) {
        LeadingTextField(
            value = query,
            modifier = Modifier.fillMaxWidth(),
            placeHolder = stringResource(R.string.search),
            onValueChange = {

            },
            leadingIcon = leadingIcon
        )
    }
}