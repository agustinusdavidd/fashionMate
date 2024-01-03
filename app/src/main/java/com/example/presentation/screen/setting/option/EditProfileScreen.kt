package com.example.presentation.screen.setting.option

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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

@Composable
fun EditProfileScreen(
    navController: NavHostController
) {

    val viewModel = ProfileViewModel()

    var nama = viewModel.profileState.value.nama
    var email = viewModel.profileState.value.email
    
    var gender by remember {
        mutableStateOf("")
    }

    var warna_kulit by remember {
        mutableStateOf("")
    }

    var tinggi by remember {
        mutableStateOf(0)
    }

    var berat by remember {
        mutableStateOf(0)
    }

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 24.dp)
    ) {

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

        GenderFilter()
        Spacer(modifier = Modifier.height(16.dp))
        
        SkinChooser()
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
            }
        )

        Spacer(modifier = Modifier.weight(1f))
        
        Button(
            onClick = { /*TODO*/ },
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

    }
}

@Preview(showBackground = true)
@Composable
fun epsp() {
    EditProfileScreen(navController = rememberNavController())
}