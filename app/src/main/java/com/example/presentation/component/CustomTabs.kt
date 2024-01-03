package com.example.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabPosition
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.presentation.ui.theme.Black
import com.example.presentation.ui.theme.Icon
import com.example.presentation.ui.theme.White

@Composable
fun CustomTabs(
    modifier: Modifier = Modifier
) {

    var selectedIndex by remember {
        mutableStateOf(0)
    }

    val list = listOf("Saved", "Post")

    TabRow(selectedTabIndex = selectedIndex,
        backgroundColor = White,
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .clip(RoundedCornerShape(50))
            .padding(1.dp)
            .border(1.dp, color = Black, shape = RoundedCornerShape(50))
            .width(250.dp),
        indicator = { tabPositions: List<TabPosition> ->
            Box {}
        }
    ) {
        list.forEachIndexed { index, text ->
            val selected = selectedIndex == index
            Tab(
                modifier = if (selected) Modifier
                    .clip(RoundedCornerShape(50))
                    .background(
                        Icon
                    )
                else Modifier
                    .clip(RoundedCornerShape(50))
                    .background(
                        White
                    ),
                selected = selected,
                onClick = {
                    selectedIndex = index
                },
                text = { Text(text = text, color = Black) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CustomTabsPreview() {
    CustomTabs()
}