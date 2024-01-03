package com.example.presentation.screen.calendar

import android.annotation.SuppressLint
import android.widget.CalendarView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Bottom
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import com.example.presentation.component.CalendarView
import kotlinx.coroutines.launch
import java.util.Calendar

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
                Text(text = Calendar.getInstance().get(Calendar.YEAR).toString(),
                    style = MaterialTheme.typography.headlineLarge)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        CalendarView(
            modifier = Modifier
                .fillMaxWidth()
                .align(CenterHorizontally)
                .padding(16.dp)
        )

    }
}

@Preview(showBackground=true)
@Composable
fun CalendarScreenPreview() {
    CalendarScreen()
}