package com.dojo.globant.mycustomplayer.common.composables

import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MyFavoriteSong() {
    Card {
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MyPreviewFavorite() {
    MyFavoriteSong()
}