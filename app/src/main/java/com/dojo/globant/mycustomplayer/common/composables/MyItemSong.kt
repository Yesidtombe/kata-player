package com.dojo.globant.mycustomplayer.common.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MyItemSong() {
    Row (Modifier.fillMaxHeight()) {
        Text(text = "This is a song")
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun MyPreview() {
    MyItemSong()
}