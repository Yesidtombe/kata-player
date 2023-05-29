package com.dojo.globant.mycustomplayer.feature.home.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dojo.globant.mycustomplayer.common.composables.MyItemSong
import com.dojo.globant.mycustomplayer.ui.theme.BgMainScreen

@Composable
fun HomeScreen() {

    val listItems = listOf(
        "Don Omar",
        "Romeo Santos",
        "Eminem",
        "Shakira",
        "Karol G",
        "Juanes",
        "Carlos Vives",
        "Alkolyrics"
    )

    Scaffold(containerColor = BgMainScreen) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                items(listItems) {
                    ItemArtist(it)
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            LazyColumn(
               verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                items(10) {
                    MyItemSong()
                }
            }
        }
    }
}

@Composable
fun ItemArtist(artistName: String) {
    TextButton(
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(),
        onClick = { /*TODO*/ }
    ) {
        Text(
            text = artistName
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun MyHomeScreenPreview() {
    HomeScreen()
}
