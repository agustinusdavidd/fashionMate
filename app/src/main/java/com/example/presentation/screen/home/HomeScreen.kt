@file:OptIn(ExperimentalMaterial3Api::class)

package home

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NotificationsNone
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.presentation.component.HorizontalFilterButton
import com.example.presentation.component.ListPakaian
import com.example.presentation.component.TextHeader
import com.example.presentation.data.Data.DataEvent
import com.example.presentation.data.Data.DataViewModel
import com.example.presentation.data.Profile.ProfileEvent
import com.example.presentation.data.Profile.ProfileViewModel
import kotlinx.coroutines.delay
import java.util.Locale

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable fun HomeScreen() {

    var viewModel = DataViewModel()
    var profileViewModel = ProfileViewModel()

    var name by remember {
        mutableStateOf("")
    }

    LaunchedEffect(profileViewModel.profileState.value.firstTime) {
        Log.d("Get User Data", profileViewModel.profileState.value.firstTime.toString())
        if (profileViewModel.profileState.value.firstTime) {
            // Execute this block when the composable is first launched or recomposed
            profileViewModel.onEvent(ProfileEvent.profileOpened)

            delay(5000)

            name = profileViewModel.profileState.value.nama.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.getDefault()
                ) else it.toString()
            }
            Log.d("Get User Data", name)

            profileViewModel.profileState.value.firstTime = false
        }
        Log.d("Get User Data", profileViewModel.profileState.value.firstTime.toString())
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 24.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {

            Row{
                Column {
                    TextHeader(
                        headerText = "Hi, " + name,
                        supportText = "Temukan style-mu bersama FashionMate."
                    )
                }
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.End
                ) {
                    IconButton(
                        onClick = {

                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.NotificationsNone,
                            contentDescription = null,
                            Modifier.size(30.dp)
                        )
                    }
                }
            }

            viewModel.isFirstTimeHomeOpen()

            Spacer(modifier = Modifier.fillMaxWidth())

            HorizontalFilterButton(viewModel)

            ShowData(viewModel = viewModel)

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun ShowData(viewModel: DataViewModel) {
    when (val result = viewModel.response.value) {
        is DataEvent.Loading -> {
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
        is DataEvent.Success -> {
            ListPakaian(pakaian = result.data)
            viewModel.progress.value = false
        }

        is DataEvent.Failure -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = result.msg)
                viewModel.progress.value = false
            }
        }

        else -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Error Fetching Data")
                viewModel.progress.value = false
            }
        }
    }
}