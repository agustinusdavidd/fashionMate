package com.example.presentation.screen.setting.option

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.presentation.component.GenderFilter
import com.example.presentation.component.SettingHeader
import com.example.presentation.component.SkinChooser
import com.example.presentation.component.SmallTextHeader
import com.example.presentation.component.TrailingSmallTextHeader
import com.example.presentation.data.Profile.ProfileEvent
import com.example.presentation.data.Profile.ProfileViewModel
import com.example.presentation.ui.theme.Primary
import com.example.presentation.ui.theme.White
import kotlinx.coroutines.delay
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(
    navController: NavHostController,
    viewModel: ProfileViewModel
) {

    var nama by rememberSaveable {
        mutableStateOf(viewModel.profileState.value.nama)
    }

    var email by rememberSaveable {
        mutableStateOf(viewModel.profileState.value.email)
    }

    var gender by rememberSaveable {
        mutableStateOf(viewModel.profileState.value.gender)
    }

    var tinggi by rememberSaveable {
        mutableStateOf(viewModel.profileState.value.tinggi_badan)
    }

    var berat by rememberSaveable {
        mutableStateOf(viewModel.profileState.value.berat_badan)
    }

    var warna_kulit by rememberSaveable {
        mutableStateOf(viewModel.profileState.value.warna_kulit)
    }

    var firstTime by rememberSaveable {
        mutableStateOf(true)
    }

    val openDialog = remember {
        mutableStateOf(false)
    }

    LaunchedEffect(firstTime) {
        if (firstTime) {
            // Execute this block when the composable is first launched or recomposed
            viewModel.onEvent(ProfileEvent.profileOpened)

            delay(5000)

            nama = viewModel.profileState.value.nama.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.getDefault()
                ) else it.toString()
            }

            email = viewModel.profileState.value.email
            tinggi = viewModel.profileState.value.tinggi_badan
            berat = viewModel.profileState.value.berat_badan
            gender = viewModel.profileState.value.gender
            warna_kulit = viewModel.profileState.value.warna_kulit

            firstTime = false
        }
    }

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 24.dp)
    ) {

        if(firstTime){
            Box{
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = CenterHorizontally
                ){
                    CircularProgressIndicator()
                }
            }
        }

        SettingHeader(title = "Edit Profile", navController = navController)

        SmallTextHeader(
            headerText = "Nama Lengkap",
            supportText = nama
        )

        Spacer(modifier = Modifier.height(16.dp))

        SmallTextHeader(
            headerText = "Email",
            supportText = email
        )

        Spacer(modifier = Modifier.height(16.dp))

        GenderFilter(viewModel = viewModel, selected = gender)
        Spacer(modifier = Modifier.height(16.dp))
        
        SkinChooser(viewModel = viewModel, warna_kulit = warna_kulit)
        Spacer(modifier = Modifier.height(16.dp))

        TrailingSmallTextHeader(
            headerText1 = "Tinggi",
            supportText1 = tinggi.toString() + " cm",
            value1 = tinggi.toString(),
            onValueChange1 = {
                if(it == null){
                    tinggi = 0
                } else {
                    tinggi = it.toInt()
                }
                viewModel.onEvent(ProfileEvent.heightChange(it))
            },
            headerText2 = "Berat",
            supportText2 = berat.toString() + " kg",
            value2 = berat.toString(),
            onValueChange2 = {
                if(it == null){
                    berat = 0
                } else {
                    berat = it.toInt()
                }
                viewModel.onEvent(ProfileEvent.weightChange(it))
            }
        )

        Spacer(modifier = Modifier.weight(1f))
        
        Button(
            onClick = {
                openDialog.value = true
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .align(CenterHorizontally),
            shape = RoundedCornerShape(15.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Primary,
                contentColor = White
            )
        ) {
            Text(text = "Simpan")
        }

        if (openDialog.value){
            AlertDialog(
                onDismissRequest = {
                    openDialog.value = true
                },
                title = {
                    Text(text = "Konfirmasi perubahan")
                },
                text = {
                    Text(text = "Anda yakin ingin mengubah data anda?")
                },
                confirmButton = {
                    Button(
                        onClick = {
                            viewModel.onEvent(ProfileEvent.savedButtonClicked)
                            openDialog.value = false
                        }
                    ) {
                        Text(text = "Konfirmasi")
                    }
                },
                dismissButton = {
                    Button(
                        onClick = {
                            openDialog.value = false
                        }
                    ) {
                        Text(text = "Batal")
                    }
                }
            )
        }

    }
}