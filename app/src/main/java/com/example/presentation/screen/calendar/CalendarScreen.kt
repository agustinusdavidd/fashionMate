package com.example.presentation.screen.calendar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.presentation.component.MonthRow

@Composable
fun CalendarScreen() {

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
                Text(text = "2023",
                    style = MaterialTheme.typography.headlineLarge)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        MonthRow(modifier = Modifier.fillMaxWidth(1f))
    }
}

@Preview(showBackground=true)
@Composable
fun CalendarScreenPreview() {
    CalendarScreen()
}