package com.example.presentation.screen.profile

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Dehaze
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.presentation.component.CustomTabs
import com.example.presentation.component.ListPakaian
import com.example.presentation.component.ListUploadPakaian
import com.example.presentation.data.Profile.ProfileEvent
import com.example.presentation.data.Profile.ProfileViewModel
import com.example.presentation.navigation.BottomBarScreen
import com.example.presentation.navigation.Graph
import com.example.presentation.ui.theme.Black
import com.example.presentation.ui.theme.Icon
import kotlinx.coroutines.delay

@Composable
fun ProfileScreen(navController: NavHostController) {

    val viewModel = ProfileViewModel()
    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }
    val context = LocalContext.current
    val bitmap = remember {
        mutableStateOf<Bitmap?>(null)
    }
    var showContent by remember {
        mutableStateOf(false)
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) {
        imageUri = it
    }

    var isUploading by remember {
        mutableStateOf(false)
    }

    var firstTime by rememberSaveable {
        mutableStateOf(true)
    }

    LaunchedEffect(firstTime){
        if(firstTime){
            delay(1000)
        }

        firstTime = false

    }

    Column(){
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

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .padding(top = 24.dp)
        ){
            IconButton(onClick = {
                launcher.launch("image/*")
                showContent = true
            },
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(vertical = 4.dp)
                    .weight(0.25f)
            ) {
                Icon(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .size(30.dp),
                    imageVector = Icons.Default.Add,
                    contentDescription = "Upload Icon",
                    tint = Black,
                )
            }

            CustomTabs(
                modifier = Modifier
                    .weight(0.4f)
                    .padding(start = 10.dp, end = 10.dp),
                viewModel = viewModel
            )

            IconButton(onClick = {
                navController.navigate(Graph.SETTING)
            },
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(vertical = 4.dp)
                    .weight(0.25f)
            ) {
                Icon(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .size(30.dp),
                    imageVector = Icons.Filled.Dehaze,
                    contentDescription = "Setting Icon",
                    tint = Black,
                )
            }
        }

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            imageUri?.let {

                val uri = it

                if (Build.VERSION.SDK_INT < 28) {
                    bitmap.value = MediaStore.Images.Media.getBitmap(context.contentResolver, it)
                } else {
                    val source = ImageDecoder.createSource(context.contentResolver, it)
                    bitmap.value = ImageDecoder.decodeBitmap(source)
                }

                bitmap.value?.let {
                    if (showContent) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 24.dp)
                                .padding(top = 10.dp)
                        ) {
                            Column(
                                modifier = Modifier.border(width = 2.dp, color = Icon, shape = RoundedCornerShape(12.dp)),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Image(
                                    bitmap = it.asImageBitmap(),
                                    contentDescription = "Photo",
                                    modifier = Modifier
                                        .size(400.dp)
                                        .padding(10.dp)
                                )

                                Button(
                                    modifier = Modifier
                                        .padding(top = 10.dp)
                                        .height(48.dp),
                                    onClick = {
                                        viewModel.onEvent(ProfileEvent.uploadClicked(uri, context))
                                        isUploading = true
                                        showContent = false
                                        navController.navigate(BottomBarScreen.Profile.route)
                                    }
                                ) {
                                    Text(text = "Upload")
                                }

                                Button(
                                    modifier = Modifier
                                        .padding(top = 10.dp, bottom = 10.dp)
                                        .height(48.dp),
                                    onClick = {
                                        Log.d("Profile Screen", "Exit")
                                        showContent = false
                                        navController.navigate(BottomBarScreen.Profile.route)
                                    }
                                ) {
                                    Text(text = "Cancel")
                                }
                            }
                        }
                    } else {
                        ProfileEvent.filterSaved(false)
                        viewModel.onEvent(ProfileEvent.filterUpload(true))
                    }
                }
            }
        }

        Column(
            modifier = Modifier
                .padding(top = 24.dp)
                .padding(horizontal = 24.dp)
        ) {
            ShowData(viewModel = viewModel)
        }
    }
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