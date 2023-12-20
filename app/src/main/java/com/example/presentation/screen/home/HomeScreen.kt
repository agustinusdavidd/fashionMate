@file:OptIn(ExperimentalMaterial3Api::class)

package home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NotificationsNone
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.fashion_mate.R
import com.example.presentation.component.ImageCard
import com.example.presentation.component.TextHeader

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable fun HomeScreen() {

    var text by remember {
        mutableStateOf("")
    }
    var isActive by remember {
        mutableStateOf(false)
    }
    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }

    val chipItemsList = listOf("Untuk anda", "Pria", "Wanita", "Semua")

    var selectedChipItem by remember {
        mutableStateOf(chipItemsList[0])
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 24.dp)
            .padding(bottom = 24.dp)
    ) {

        Column(modifier = Modifier
            .fillMaxWidth()
        ){
            Row(modifier = Modifier.padding(horizontal = 24.dp)){
                Column {
                    TextHeader(
                        headerText = "Hi, Dito Rifadli Febrian",
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
                    ){
                        Icon(
                            imageVector = Icons.Default.NotificationsNone,
                            contentDescription = null,
                            Modifier.size(30.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.fillMaxWidth())

            LazyRow(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)) {
                items(chipItemsList){
                    FilterChip(
                        modifier = Modifier
                            .padding(end = 6.dp)
                            .padding(top = 6.dp),
                        selected = (it == selectedChipItem),
                        onClick = {
                            selectedChipItem = it
                        },
                        label = {
                            Text(text = it)
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                content = {
                    items(6) {
                        Box (
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ){
                            ImageCard(
                                modifier = Modifier
                                    .fillMaxWidth(0.5f),
                                painter = painterResource(id = R.drawable.dummy_cloth),
                                contentDescription = "Baju Dummy"
                            )

                            Spacer(modifier = Modifier.width(8.dp))

                            ImageCard(
                                modifier = Modifier
                                    .fillMaxWidth(0.5f),
                                painter = painterResource(id = R.drawable.dummy_cloth),
                                contentDescription = "Baju Dummy"
                            )
                        }
                    }
                }
            )
        }
    }
}
