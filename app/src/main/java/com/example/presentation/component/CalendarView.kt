package com.example.presentation.component

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import java.util.Calendar

@Composable
fun CalendarView(modifier: Modifier = Modifier) {

    val today = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)

    var date by remember {
        mutableStateOf(today)
    }

    Log.d("Today", today.toString())

}

@Preview(showBackground = true)
@Composable
fun CalendarViewPreview() {
    CalendarView()
}