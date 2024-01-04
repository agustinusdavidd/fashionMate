package com.example.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.presentation.data.Profile.ProfileEvent
import com.example.presentation.data.Profile.ProfileViewModel
import com.example.presentation.utils.Models.Pakaian
import com.example.presentation.utils.Models.UploadCloth

@Composable
fun ImageCard(
    modifier: Modifier = Modifier,
    painter: Painter,
    contentDescription: String,
    hide: Boolean,
    enabled: Boolean,
    viewModel: ProfileViewModel,
    photo_url: String
) {

    var checked by remember {
        mutableStateOf(false)
    }

    Card (
        modifier = Modifier
            .height(235.dp)
            .width(155.dp)
            .padding(horizontal = 4.dp)
            .padding(bottom = 8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = 8.dp
    ){
        Box {

            Image (
                painter = painter,
                contentDescription = contentDescription,
                contentScale = ContentScale.Crop
            )

            IconToggleButton (
                modifier = Modifier.height(36.dp),
                checked = checked,
                onCheckedChange = {
                    checked = it
                    viewModel.onEvent(ProfileEvent.clothSaved(photo_url))
                },
                enabled = enabled
            ) {
                Icon(
                    imageVector = if(checked){
                        Icons.Filled.Bookmark
                    } else {
                        Icons.Outlined.BookmarkBorder
                    },
                    contentDescription = "Save Style",
                    tint = if(checked){
                        Color.LightGray
                    } else if (hide) {
                        Color.Transparent
                    } else {
                        Color.White
                    }
                )
            }
        }
    }
}

@Composable
fun ListPakaian(
    pakaian: MutableList<Pakaian>,
    viewModel: ProfileViewModel
) {
    LazyVerticalGrid(columns = GridCells.Fixed(2)){
        items(pakaian) {
            ImageCard(
                modifier = Modifier
                    .fillMaxWidth(0.5f),
                painter = rememberAsyncImagePainter(
                    model = it.photo_url,
                    contentScale = ContentScale.Crop
                ),
                contentDescription = "Gaya Berpakaian",
                hide = false,
                enabled = true,
                viewModel = viewModel,
                photo_url = it.photo_url!!
            )
        }
    }
}

@Composable
fun ListUploadPakaian(
    pakaian: MutableList<UploadCloth>,
    viewModel: ProfileViewModel
) {
    LazyVerticalGrid(columns = GridCells.Fixed(2)){
        items(pakaian) {
            ImageCard(
                modifier = Modifier
                    .fillMaxWidth(0.5f),
                painter = rememberAsyncImagePainter(
                    model = it.photo_url,
                    contentScale = ContentScale.Crop
                ),
                contentDescription = "Gaya Berpakaian",
                hide = true,
                enabled = false,
                viewModel = viewModel,
                photo_url = it.photo_url
            )
        }
    }
}