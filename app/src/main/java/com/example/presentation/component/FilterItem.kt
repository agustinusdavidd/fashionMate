package com.example.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.presentation.data.Data.DataEvent
import com.example.presentation.data.Data.DataViewModel
import com.example.presentation.data.Main.MainViewModel
import com.example.presentation.ui.theme.Icon
import com.example.presentation.ui.theme.Primary
import com.example.presentation.ui.theme.White

@Composable
fun FilterItem(
    text: String,
    isSelected: Boolean,
    onFilterSelected: () -> Unit
) {
    Button(
        onClick = { onFilterSelected() },
        colors = ButtonDefaults.buttonColors( if (isSelected) Icon else White),
        modifier = Modifier.padding(end = 4.dp),
        border = if(!isSelected) BorderStroke(1.dp, color= Primary) else BorderStroke(0.dp, color= Icon)
    ) {
        Text(text = text, color = if (isSelected) White else Primary)
    }
}

@Composable
fun HorizontalFilterButton(viewModel : DataViewModel) {

    val filterItems = listOf("Untuk anda", "Pria", "Wanita", "Semua")

    var selectedFilter by remember{
        mutableStateOf(filterItems.first())
    }

    LazyRow(
        contentPadding = PaddingValues(end = 4.dp, top = 16.dp, bottom = 16.dp)
    ) {
        items(filterItems) { filter ->
            FilterItem(
                text = filter,
                isSelected = filter == selectedFilter,
                onFilterSelected = {
                    selectedFilter = filter
                    when(selectedFilter) {
                        "Untuk anda" -> {
                            DataEvent.filterForWomen(false)
                            DataEvent.filterForMen(false)
                            DataEvent.filterAll(false)
                            viewModel.onEvent(DataEvent.filterForYou(true))
                        }
                        "Pria" -> {
                            DataEvent.filterForWomen(false)
                            DataEvent.filterForYou(false)
                            DataEvent.filterAll(false)
                            viewModel.onEvent(DataEvent.filterForMen(true))
                        }

                        "Wanita" -> {
                            DataEvent.filterForYou(false)
                            DataEvent.filterForMen(false)
                            DataEvent.filterAll(false)
                            viewModel.onEvent(DataEvent.filterForWomen(true))
                        }

                        "Semua" -> {
                            DataEvent.filterForWomen(false)
                            DataEvent.filterForMen(false)
                            DataEvent.filterForYou(false)
                            viewModel.onEvent(DataEvent.filterAll(true))
                        }
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FilterItemPreview() {
    FilterItem(
        text = "Untuk anda",
        isSelected = false,
        onFilterSelected = {

        }
    )
}