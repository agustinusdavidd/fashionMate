package com.example.presentation.component

import android.annotation.SuppressLint
import android.util.Log
import android.widget.CalendarView
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import java.util.Calendar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarView(modifier: Modifier = Modifier) {

    val today = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)

    var date by remember {
        mutableStateOf(today.toString())
    }

    Scaffold(
        modifier = Modifier.fillMaxWidth(),
        content = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AndroidView(
                    modifier = Modifier
                        .align(CenterVertically)
                        .fillMaxWidth(),
                    factory = { CalendarView(it) },
                    update = {
                    it.setOnDateChangeListener { CalendarView, year, month, day ->
                        date = "$day - ${month + 1} - $year"
                    }
                })
            }
        }
    )

    Log.d("Today", today.toString())

}

@Preview(showBackground = true)
@Composable
fun CalendarViewPreview() {
    CalendarView()
}