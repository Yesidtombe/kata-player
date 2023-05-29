package com.dojo.globant.mycustomplayer.common.util

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

/**
 * This class will help to get the strings without having access to the context
 */

sealed class UiText {
    data class DynamicString(val value: String): UiText()
    class StringResource(@StringRes val id: Int, vararg val args: Any): UiText()

    @Composable
    fun asString(): String {
        return when (this) {
            is DynamicString -> value
            is StringResource -> stringResource(id = id, formatArgs = args)
        }
    }

    fun asString(context: Context): String {
        return when (this) {
            is DynamicString -> value
            is StringResource -> context.getString(id, *args)
        }
    }
}