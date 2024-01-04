package com.example.presentation.component

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.presentation.data.Data.DataEvent
import com.example.presentation.data.Data.DataViewModel
import com.example.presentation.data.Profile.ProfileEvent
import com.example.presentation.data.Profile.ProfileViewModel
import com.example.presentation.ui.theme.Bright
import com.example.presentation.ui.theme.Dark
import com.example.presentation.ui.theme.Icon
import com.example.presentation.ui.theme.Normal
import com.example.presentation.ui.theme.Primary
import com.example.presentation.ui.theme.Purple40
import com.example.presentation.ui.theme.SlightlyBright
import com.example.presentation.ui.theme.SlightlyDark
import com.example.presentation.ui.theme.White

@Composable
fun FilterItem(
    text: String,
    isSelected: Boolean,
    onFilterSelected: () -> Unit
) {
    Button(
        onClick = { onFilterSelected() },
        colors = ButtonDefaults.buttonColors( if (isSelected) Icon else White),
        modifier = Modifier.padding(end = 4.dp),
        border = if(!isSelected) BorderStroke(1.dp, color= Primary) else BorderStroke(0.dp, color= Icon)
    ) {
        Text(text = text, color = if (isSelected) White else Primary)
    }
}

@Composable
fun HorizontalFilterButton(viewModel : DataViewModel) {

    val filterItems = listOf("Untuk anda", "Pria", "Wanita", "Semua")

    var selectedFilter by remember{
        mutableStateOf(filterItems.first())
    }

    LazyRow(
        contentPadding = PaddingValues(end = 4.dp, top = 16.dp, bottom = 16.dp)
    ) {
        items(filterItems) { filter ->
            FilterItem(
                text = filter,
                isSelected = filter == selectedFilter,
                onFilterSelected = {
                    selectedFilter = filter
                    when(selectedFilter) {
                        "Untuk anda" -> {
                            DataEvent.filterForWomen(false)
                            DataEvent.filterForMen(false)
                            DataEvent.filterAll(false)
                            viewModel.onEvent(DataEvent.filterForYou(true))
                        }
                        "Pria" -> {
                            DataEvent.filterForWomen(false)
                            DataEvent.filterForYou(false)
                            DataEvent.filterAll(false)
                            viewModel.onEvent(DataEvent.filterForMen(true))
                        }

                        "Wanita" -> {
                            DataEvent.filterForYou(false)
                            DataEvent.filterForMen(false)
                            DataEvent.filterAll(false)
                            viewModel.onEvent(DataEvent.filterForWomen(true))
                        }

                        "Semua" -> {
                            DataEvent.filterForWomen(false)
                            DataEvent.filterForMen(false)
                            DataEvent.filterForYou(false)
                            viewModel.onEvent(DataEvent.filterAll(true))
                        }
                    }
                }
            )
        }
    }
}

@Composable
fun GenderFilter(
    headerTextStyle: TextStyle = MaterialTheme.typography.headlineSmall,
    viewModel: ProfileViewModel,
    headerColor: Color = Primary,
    selected: String
) {
    val filterItems = listOf("pria", "wanita")

    var selectedFilter by remember {
        mutableStateOf(selected)
    }

    Text(text = "Gender", style = headerTextStyle, color = headerColor)

    LazyRow(
        contentPadding = PaddingValues(end = 4.dp, top = 10.dp)
    ) {
        items(filterItems) { filter ->
            FilterItem(
                text = filter,
                isSelected = filter == selectedFilter,
                onFilterSelected = {
                    selectedFilter = filter
                    when (selectedFilter) {
                        "pria" -> {
                            viewModel.onEvent(ProfileEvent.genderChanged("pria"))
                        }

                        "wanita" -> {
                            viewModel.onEvent(ProfileEvent.genderChanged("wanita"))
                        }
                    }
                }
            )
        }
    }
}

@Composable
fun SkinChooser(
    headerTextStyle: TextStyle = MaterialTheme.typography.headlineSmall,
    headerColor: Color = Primary,
    viewModel: ProfileViewModel,
    warna_kulit: String
) {

    var skinColor by remember {
         mutableStateOf(warna_kulit)
    }

    Column{
        Text(text = "Warna Kulit", style = headerTextStyle, color = headerColor)

        Row (modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
        ) {
            Button(
                modifier = Modifier
                    .width(60.dp)
                    .height(60.dp)
                    .padding(end = 16.dp),
                onClick = {
                    viewModel.onEvent(ProfileEvent.skinChanged("bright"))
                    skinColor = "bright"
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Bright
                ),
                shape = RoundedCornerShape(6.dp),
                border = BorderStroke(
                    width = 2.dp,
                    color = if(skinColor == "bright") Purple40 else Bright
                )
            ) {}
            Button(
                modifier = Modifier
                    .width(60.dp)
                    .height(60.dp)
                    .padding(end = 16.dp),
                onClick = {
                    viewModel.onEvent(ProfileEvent.skinChanged("slightly bright"))
                    skinColor = "slightly bright"
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = SlightlyBright
                ),
                shape = RoundedCornerShape(6.dp),
                border = BorderStroke(
                    width = 2.dp,
                    color = if(skinColor == "slightly bright") Purple40 else SlightlyBright
                )
            ) {}
            Button(
                modifier = Modifier
                    .width(60.dp)
                    .height(60.dp)
                    .padding(end = 16.dp),
                onClick = {
                    viewModel.onEvent(ProfileEvent.skinChanged("normal"))
                    skinColor = "normal"
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Normal
                ),
                shape = RoundedCornerShape(6.dp),
                border = BorderStroke(
                    width = 2.dp,
                    color = if(skinColor == "normal") Purple40 else Normal
                )
            ) {}
            Button(
                modifier = Modifier
                    .width(60.dp)
                    .height(60.dp)
                    .padding(end = 16.dp),
                onClick = {
                    viewModel.onEvent(ProfileEvent.skinChanged("slightly dark"))
                    skinColor = "slightly dark"
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = SlightlyDark
                ),
                shape = RoundedCornerShape(6.dp),
                border = BorderStroke(
                    width = 2.dp,
                    color = if(skinColor == "slightly dark") Purple40 else SlightlyDark
                )
            ) {}
            Button(
                modifier = Modifier
                    .width(60.dp)
                    .height(60.dp)
                    .padding(end = 16.dp),
                onClick = {
                    viewModel.onEvent(ProfileEvent.skinChanged("dark"))
                    skinColor = "dark"
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Dark
                ),
                shape = RoundedCornerShape(6.dp),
                border = BorderStroke(
                    width = 2.dp,
                    color = if(skinColor == "dark") Purple40 else Dark
                )
            ) {}
        }
    }
}