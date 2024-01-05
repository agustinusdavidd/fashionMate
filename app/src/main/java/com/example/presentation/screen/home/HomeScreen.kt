package home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
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
@Composable fun HomeScreen() {

    val viewModel = DataViewModel()
    val profileViewModel = ProfileViewModel()

    var name by rememberSaveable {
        mutableStateOf(profileViewModel.profileState.value.nama)
    }

    var firstTime by rememberSaveable {
        mutableStateOf(true)
    }

    LaunchedEffect(firstTime) {
        if (firstTime) {
            // Execute this block when the composable is first launched or recomposed
            profileViewModel.onEvent(ProfileEvent.profileOpened)

            delay(6000)

            name = profileViewModel.profileState.value.nama.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.getDefault()
                ) else it.toString()
            }

            firstTime = false
        }
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

            if(firstTime){
                Box{
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        CircularProgressIndicator()
                    }
                }
            }

            Row{
                Column {
                    TextHeader(
                        headerText = "Hi, $name",
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

    val profileViewModel = ProfileViewModel()

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
            ListPakaian(pakaian = result.data, viewModel = profileViewModel)
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
                Text(text = "Please Refresh This Page")
                viewModel.progress.value = false
            }
        }
    }
}