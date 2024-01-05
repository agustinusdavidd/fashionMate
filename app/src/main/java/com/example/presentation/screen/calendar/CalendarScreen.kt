package com.example.presentation.screen.calendar

import android.icu.text.SimpleDateFormat
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.presentation.component.CustomTabs
import com.example.presentation.component.ListUploadPakaian
import com.example.presentation.data.Profile.ProfileEvent
import com.example.presentation.data.Profile.ProfileViewModel
import com.example.presentation.utils.Models.CalendarInput
import java.util.Calendar
import java.util.Locale

@Composable
fun CalendarScreen() {

    val viewModel = ProfileViewModel()

    val clickedCalendarItem by remember {
        mutableStateOf<CalendarInput?>(null)
    }

    val calendarInputList by remember {
        mutableStateOf(createCalendarList())
    }

    val currentDate = Calendar.getInstance().time
    val currentMonth = SimpleDateFormat("MMMM", Locale.getDefault()).format(currentDate)
    val currentYear = SimpleDateFormat("yyyy", Locale.getDefault()).format(currentDate).toString()
    val currentDay = SimpleDateFormat("dd", Locale.getDefault()).format(currentDate).toString()

    Column {
        Row (modifier = Modifier
            .fillMaxWidth(1f)
            .padding(top = 24.dp)
            .padding(horizontal = 24.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .align(CenterVertically),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(text = "Calendar",
                    style = MaterialTheme.typography.headlineLarge)
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(CenterVertically),
                contentAlignment = Alignment.CenterEnd
            ) {
                Text(text = "2024",
                    style = MaterialTheme.typography.headlineLarge)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = when(currentMonth) {
                "January" -> {
                    "Januari"
                }
                "February" -> {
                    "Februari"
                }
                "March" -> {
                    "Maret"
                }
                else -> {
                    "Another"
                }
            },
            modifier = Modifier
                .align(CenterHorizontally)
                .padding(bottom = 16.dp),
            fontSize = 24.sp
        )

        Log.d("Day", currentDay.toString())
        Column(
            modifier = Modifier
                .padding(horizontal = 24.dp)
        ){
            com.example.presentation.component.Calendar(
                onDayClick = {
                    viewModel.onEvent(ProfileEvent.dayClicked(day = it, month = currentMonth, year = currentYear))
                },
                month = currentMonth,
                modifier = Modifier
                    .padding(top = 10.dp)
                    .fillMaxWidth()
                    .aspectRatio(1f),
                calendarInput = calendarInputList,
                day = currentDay
            )
            
            Spacer(modifier = Modifier.padding(top = 10.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = CenterHorizontally
            ) {
                CustomTabs(
                    viewModel = viewModel
                )
            }

            Spacer(modifier = Modifier.padding(top = 10.dp))

            viewModel.onEvent(ProfileEvent.filterUpload(true))

            ShowData(viewModel)
        }
    }
}

fun createCalendarList() : List<CalendarInput> {
    val calendarInputs = mutableListOf<CalendarInput>()
    for (i in 1..31) {
        calendarInputs.add(
            CalendarInput(
                i,
                savedClothPlanned = emptyList()
            )
        )
    }
    return calendarInputs
}

@Composable
fun ShowData(viewModel: ProfileViewModel) {

    when (val result = viewModel.response.value) {

        is ProfileEvent.Loading -> {
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
        is ProfileEvent.Success -> {
            ListUploadPakaian(pakaian = result.data, viewModel = viewModel)
            viewModel.progress.value = false
        }

        is ProfileEvent.Failure -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                androidx.compose.material3.Text(text = result.msg)
                viewModel.progress.value = false
            }
        }

        else -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                androidx.compose.material3.Text(text = "Please Refresh This Page")
                viewModel.progress.value = false
            }
        }
    }
}