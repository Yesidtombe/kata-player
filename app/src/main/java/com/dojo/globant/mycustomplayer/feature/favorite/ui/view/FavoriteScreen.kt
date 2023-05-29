package com.dojo.globant.mycustomplayer.feature.favorite.ui.view

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.dojo.globant.mycustomplayer.R

@Composable
fun FavoriteScreen() {
    Text(text = stringResource(id = R.string.favorite_text_label))
}