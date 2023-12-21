package com.example.presentation.component

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.material3.Surface
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.presentation.ui.theme.BackgroundWhite
import com.example.presentation.ui.theme.Black
import com.example.presentation.ui.theme.White
import java.util.Calendar

@Composable
fun MonthRow(modifier: Modifier = Modifier) {

    val currentMonth = Calendar.getInstance().get(Calendar.MONTH)

    val months = listOf("Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember")

    var selectedMonth by remember {
        mutableStateOf(months[currentMonth])
    }

//    Log.d("Selected month", selectedMonth)

    LazyRow(modifier = Modifier
        .background(BackgroundWhite)
    ){
        items(months) { month ->
            MonthItem(
                month = month,
                isSelected = month == selectedMonth,
                onMonthSelected = {
                    selectedMonth = month
//                    Log.d("month", month)
                }
            )
        }
    }
}

@Composable
fun MonthItem(month: String, isSelected: Boolean, onMonthSelected: (String) -> Unit) {

    val backgroundColor = if(isSelected) {
        Color.LightGray
    } else {
        BackgroundWhite
    }

    Surface(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(12.dp))
            .clickable {
                onMonthSelected(month)
            },
        color = backgroundColor
    ) {
        Text (
            text = month,
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .padding(top = 16.dp, bottom = 16.dp),
            style = MaterialTheme.typography.titleLarge,
            color = Black
        )
    }
}

@Preview
@Composable
fun MonthRowPreview() {
    MonthRow()
}