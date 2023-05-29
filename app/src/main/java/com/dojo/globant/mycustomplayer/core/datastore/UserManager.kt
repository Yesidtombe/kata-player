package com.dojo.globant.mycustomplayer.core.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

private val Context.dataUser: DataStore<Preferences> by preferencesDataStore(name = "user")

class UserManager(private val context: Context) {

    companion object {
        private val USER_FAVORITE_SONGS_KEY = stringSetPreferencesKey("USER_FAVORITE_SONGS")
    }

    suspend fun saveFavoriteSongs(idsFavoriteSongs: Set<String>) {
        context.dataUser.edit {
            it[USER_FAVORITE_SONGS_KEY] = idsFavoriteSongs
        }
    }

    suspend fun getFavoriteSongs(): Set<String> {
        val prefs = context.dataUser.data.first()
        return prefs[USER_FAVORITE_SONGS_KEY] ?: setOf()
    }
}